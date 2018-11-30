package by.ladyka.club.service.order;

import by.ladyka.bepaid.BePaidApi;
import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.bepaid.dto.PaymentTokenDto;
import by.ladyka.club.config.CustomSettings;
import by.ladyka.club.dto.tikets.*;
import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import by.ladyka.club.entity.order.OrderEntity;
import by.ladyka.club.entity.order.OrderItemEntity;
import by.ladyka.club.repository.OrderEntityRepository;
import by.ladyka.club.repository.OrderItemEntityRepository;
import by.ladyka.club.service.EventsService;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static by.ladyka.club.config.Constants.API_ORDER_BEPAID;

@Service
public class OrderTicketsServiceImpl implements OrderTicketsService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private OrderItemEntityRepository repository;
	@Autowired
	private EventsService eventService;
	@Autowired
	private OrderEntityRepository orderEntityRepository;
	@Autowired
	private BePaidApi bePaidApi;
	@Autowired
	private CustomSettings customSettings;
	@Autowired
	private OrderEntityConverter orderEntityConverter;

	@Override
	public List<TicketTableDto> getTables(Long eventId) {
		List<TicketTableDto> tables = new ArrayList<>();
		for (int table = 1; table < 26; table++) {
			TicketTableDto ticketTableDto = new TicketTableDto();
			ticketTableDto.setTableNumber(table);
			List<TicketPlaceDto> places = new ArrayList<>();
			for (int placeNumber = 1; placeNumber < 5; placeNumber++) {
				TicketPlaceDto place = new TicketPlaceDto();
				place.setStatus(TicketPlaceStatus.FREE);
				place.setPlaceNumber(placeNumber);
				places.add(place);
			}
			ticketTableDto.setPlaces(places);
			tables.add(ticketTableDto);
		}
		repository.findByOrderEntityEventEntityId(eventId).forEach(orderItemEntity -> {
			for (TicketTableDto table : tables) {
				if (table.getTableNumber() == orderItemEntity.getTableNumber()) {
					List<TicketPlaceDto> places = table.getPlaces();
					for (TicketPlaceDto place : places) {
						if (place.getPlaceNumber() == orderItemEntity.getPlace()) {
							place.setStatus(TicketPlaceStatus.BUSY);
						}
					}
				}
			}
		});
		return tables;
	}

	@Override
	public String bookAndPay(@Valid TicketsOrderDto dto) {
		OrderEntity orderEntity = storeOrder(dto);
		PaymentTokenDto paymentTokenDto = getPaymentTokenDto(orderEntity);
		final String bePaidUrl = paymentTokenDto.getCheckout().getRedirectUrl();
		final String token = paymentTokenDto.getCheckout().getToken();
		orderEntity.setToken(token);
		orderEntityRepository.save(orderEntity);
		return bePaidUrl;
	}

	@Override
	public boolean updateStatus(String uuid, GatewayStatus gatewayStatus, String uid, String token) {
		try {
			orderEntityRepository.findByUuid(uuid).ifPresent(orderEntity -> {
				if (StringUtils.equals(orderEntity.getToken(), token)) {
					orderEntity.setPayStatus(gatewayStatus);
					orderEntity.setUid(uid);
					orderEntityRepository.save(orderEntity);
				} else {
					throw new RuntimeException("Token is invalid");
				}
			});
			return true;
		} catch (RuntimeException ex) {
			logger.error("FAIL", ex);
			return false;
		}

	}

	@Override
	public TicketOrderDto getOrder(String uuid) {

		final OrderEntity orderEntity = orderEntityRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("UUID is invalid"));
		return orderEntityConverter.toDto(orderEntity, true);

	}

	@Override
	public EventTicketsReportDto getReport(Long eventId) {
		EventTicketsReportDto reportDto = new EventTicketsReportDto();
		final List<OrderEntity> orders = orderEntityRepository.findAllByEventEntityId(eventId);
		int dc = orders
				.stream()
				.map(OrderEntity::getDance)
				.mapToInt(Integer::intValue)
				.sum();
		reportDto.setDanceCount(dc);
		int tpc = orders
				.stream()
				.map(OrderEntity::getTableNumbers)
				.map(List::size)
				.mapToInt(Integer::intValue)
				.sum();
		reportDto.setTablePlacesCount(tpc);
		return reportDto;
	}

	private OrderEntity storeOrder(@Valid TicketsOrderDto dto) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setName(dto.getName());
		orderEntity.setSurname(dto.getSurname());
		orderEntity.setEmail(dto.getEmail());
		orderEntity.setPhone(dto.getPhone());
		orderEntity.setDescription(dto.getDescription());
		orderEntity.setDance(dto.getDanceFloor());
		final List<OrderItemEntity> collect = dto.getTables()
				.stream().map(ticketTableDto -> {
					final int tableNumber = ticketTableDto.getTableNumber();
					return ticketTableDto.getPlaces().stream()
							.filter(place -> TicketPlaceStatus.BOOKING.equals(place.getStatus()))
							.map(place -> {
								OrderItemEntity orderItemEntity = new OrderItemEntity();
								orderItemEntity.setOrderEntity(orderEntity);
								orderItemEntity.setTableNumber(tableNumber);
								orderItemEntity.setPlace(place.getPlaceNumber());
								return orderItemEntity;
							}).collect(Collectors.toList());
				})
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		orderEntity.setTableNumbers(collect);

		List<MenuItemPricesHasOrders> items = new ArrayList<>();
		orderEntity.setItemPricesHasOrders(items);
		final EventEntity eventEntity = eventService.getEventById(dto.getEvent().getId()).orElseThrow(RuntimeException::new);
		orderEntity.setEventEntity(eventEntity);
		orderEntityRepository.saveAndFlush(orderEntity);
		repository.saveAll(collect);
		return orderEntity;
	}

	private PaymentTokenDto getPaymentTokenDto(OrderEntity orderEntity) {
		final String requestId = OrderEntity.class.getName() + orderEntity.getUuid();
		final String redirectUrl = String.format("%s" + API_ORDER_BEPAID + "/callback/%s", customSettings.getSiteDomain(), orderEntity.getUuid());
		PaymentTokenDto paymentTokenDto = bePaidApi.getPaymentTokenDto(
				getAmount(orderEntity),
				orderEntity.getEmail(),
				orderEntity.getSurname(),
				orderEntity.getName(),
				orderEntity.getPhone(),
				redirectUrl,
				customSettings.getBePaidPaymentTest(),
				String.format("Оплата заказа №%d | RE:Public Club", orderEntity.getId())
		);
		try {
			paymentTokenDto = bePaidApi.getOrderToken(paymentTokenDto, requestId);
		} catch (IOException | URISyntaxException | NoSuchAlgorithmException ex) {
			logger.error("FAIL !!!!", ex);
			throw new RuntimeException(ex);
		}
		return paymentTokenDto;
	}

	private long getAmount(OrderEntity orderEntity) {
		long total = 0;
		final BigDecimal costDance = orderEntity.getEventEntity().getCostDance();
		final BigDecimal costTablePlace = orderEntity.getEventEntity().getCostTablePlace();
		total += costDance.longValue() * orderEntity.getDance();
		total += costTablePlace.longValue() * orderEntity.getTableNumbers().size();
		return total * 100;
	}
}
