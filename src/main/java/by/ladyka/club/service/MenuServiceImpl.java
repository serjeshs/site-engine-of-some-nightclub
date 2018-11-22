package by.ladyka.club.service;

import by.ladyka.bepaid.BePaidApi;
import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.club.dto.menu.MenuCategoryDto;
import by.ladyka.club.dto.menu.MenuItemPriceDto;
import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.entity.menu.*;
import by.ladyka.club.repository.menu.*;
import by.ladyka.club.service.email.EmailService;
import by.ladyka.club.utils.converters.MenuOrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
	private static final int MAX_TABLE_NUMBER = 26;
	@Autowired
	private MenuCategoryRepository menuCategoryRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private MenuItemPriceRepository menuItemPriceRepository;
	@Autowired
	private EventsService eventsService;
	@Autowired
	private MenuOrderRepository menuOrderRepository;
	@Autowired
	private MenuOrderConverter menuOrderConverter;
	@Autowired
	private MenuItemPriceHasOrdersRepository menuItemPriceHasOrdersRepository;
	@Autowired
	private EmailService emailService;

	@Autowired
	private BePaidApi bePaidApi;


	@Override
	public MenuPageDto mainPage() {
		return new MenuPageDto(
				getFood(),
				eventsService.getEventsAfterAndRepublicPayTrue(LocalDateTime.now())
		);
	}

	public List<MenuCategoryDto> getFood() {
		return menuCategoryRepository.findAllByParentIsNull().stream().map(this::convertToMenuCategoryDto).collect(Collectors.toList());
	}

	@Override
	public MenuCategoryDto saveCategory(MenuCategoryDto dto) {
		MenuCategory mc = menuCategoryRepository.findById(dto.getId() != null ? dto.getId() : 0L).orElseGet(MenuCategory::new);
		mc.setName(dto.getName());
		if ((dto.getParentId() != null) && (dto.getParentId() > 0)) {
			menuCategoryRepository.findById(dto.getParentId()).ifPresent(mc::setParent);
		}
		mc.setCategoryType(1);
		mc.setDescription(dto.getDescription());
		return convertToMenuCategoryDto(menuCategoryRepository.save(mc));
	}

	@Override
	public MenuItemPriceDto saveMenuItemPrice(MenuItemPriceDto dto) {
		final MenuCategory menuCategory = menuCategoryRepository.findById(dto.getCategoryId()).orElseThrow(RuntimeException::new);
		MenuItem menuItem = menuItemRepository.findById(dto.getId()).orElse(new MenuItem());
		menuItem.setName(dto.getName());
		menuItem.setDescription(dto.getDescription());
		menuItem.setDescriptionProportions(dto.getDescriptionProportions());
		menuItem.setCategory(menuCategory);
		menuItemRepository.save(menuItem);
		MenuItemPrice menuItemPrice = getMenuItemPrice(menuItem);
		if (menuItemPrice == null) {
			menuItemPrice = updateMenuItemPrice(dto, menuItem, new MenuItemPrice());
		}
		if (!menuItemPrice.getValue().setScale(2, BigDecimal.ROUND_CEILING).equals(dto.getPrice().setScale(2, BigDecimal.ROUND_CEILING))) {
			terminate(menuItemPrice);
			menuItemPrice = updateMenuItemPrice(dto, menuItem, new MenuItemPrice());
		}
		menuItemPriceRepository.save(menuItemPrice);
		return dto;
	}

	@Override
	public BigDecimal getAmount(TicketOrderDto menuOrder) {
		BigDecimal total = new BigDecimal(0);
		try {
			//		for (let foodKey in this.order.food) {
			for (Long itemPriceId : menuOrder.getFood().keySet()) {
//			total += this.order.foodPrice[foodKey] * this.order.food[foodKey];
				final Integer countFood = menuOrder.getFood().get(itemPriceId);
				final BigDecimal priceFood = menuOrder.getFoodPrice().get(itemPriceId);
				total = total.add(priceFood.multiply(new BigDecimal(countFood)));
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return total;
	}

	@Override
	public void setToken(String token, Long orderId) {
		MenuOrder order = menuOrderRepository.getOne(orderId);
		order.setToken(token);
		menuOrderRepository.save(order);
	}

	@Override
	public TicketOrderDto getOrder(String uuid) {
		final MenuOrder order = menuOrderRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("UUID is invalid"));
		return menuOrderConverter.toDto(order, true);
	}

	@Override
	public GatewayStatus getStatus(Long orderId) {
		MenuOrder order = menuOrderRepository.getOne(orderId);
		if (order.getPayStatus() != GatewayStatus.successful) {
			try {
				GatewayStatus status = bePaidApi.getTransactionState(order.getToken(), UUID.randomUUID().toString());
				if (!order.getPayStatus().equals(status)) {
					order.setPayStatus(status);
					order = menuOrderRepository.save(order);
				}
			} catch (NoSuchAlgorithmException | IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return order.getPayStatus();
	}

	private void terminate(MenuItemPrice menuItemPrice) {
		menuItemPrice.setEndTime(LocalDateTime.now());
		menuItemPrice.setVisible(false);
		menuItemPriceRepository.save(menuItemPrice);
	}

	private MenuItemPrice updateMenuItemPrice(MenuItemPriceDto dto, MenuItem menuItem, MenuItemPrice menuItemPrice) {
		menuItemPrice.setVisible(true);
		menuItemPrice.setValue(dto.getPrice());
		menuItemPrice.setStartTime(LocalDateTime.now());
		menuItemPrice.setItem(menuItem);
		return menuItemPrice;
	}

	private MenuCategoryDto convertToMenuCategoryDto(MenuCategory menuCategory) {
		MenuCategoryDto dto = new MenuCategoryDto();
		dto.setId(menuCategory.getId());
		dto.setName(menuCategory.getName());
		dto.setDescription(menuCategory.getDescription());
		if (menuCategory.getParent() != null)
			dto.setParentId(menuCategory.getParent().getId());
		if (!CollectionUtils.isEmpty(menuCategory.getItems())) {
			List<MenuItemPriceDto> menuItems = menuCategory.getItems().stream()
					.map(this::convertToMenuItemPriceDto).filter(Objects::nonNull).collect(Collectors.toList());
			dto.setMenuItems(menuItems);
		} else {
			dto.setMenuItems(Collections.emptyList());
		}
		if (!CollectionUtils.isEmpty(menuCategory.getChildren())) {
			List<MenuCategoryDto> categoryDtos = menuCategory.getChildren().stream()
					.map(this::convertToMenuCategoryDto).filter(Objects::nonNull).collect(Collectors.toList());
			dto.setCategories(categoryDtos);
		} else {
			dto.setCategories(Collections.emptyList());
		}
		return dto;
	}

	public MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem) {
		MenuItemPriceDto dto = new MenuItemPriceDto();
		MenuItemPrice menuItemPrice = getMenuItemPrice(menuItem);
		if (menuItemPrice == null) {
			return null;
		} else {
			dto.setItemPriceId(menuItemPrice.getId());
			dto.setPrice(menuItemPrice.getValue());
		}
		dto.setId(menuItem.getId());
		dto.setName(menuItem.getName());
		dto.setDescription(menuItem.getDescription());
		dto.setDescriptionProportions(menuItem.getDescriptionProportions());
		dto.setCategoryId(menuItem.getCategory().getId());
		return dto;
	}

	private MenuItemPrice getMenuItemPrice(MenuItem menuItem) {
		final List<MenuItemPrice> prices = menuItem.getPrices();
		if (!CollectionUtils.isEmpty(prices)) {
			return prices.stream().filter(this::active).findFirst().orElse(null);
		} else {
			return null;
		}

	}

	private boolean active(MenuItemPrice menuItemPrice) {
		return menuItemPrice.getEndTime() == null;
	}

	@Override
	public TicketOrderDto order(TicketOrderDto order) {
		MenuOrder orderEntity = menuOrderConverter.toEntity(order);
		final Optional<EventEntity> eventById = eventsService.getEventById(order.getEvent());
		if (eventById.isPresent()) {
			orderEntity.setEventEntity(eventById.get());
		}
		orderEntity = menuOrderRepository.saveAndFlush(orderEntity);
		List<Long> pricesId = new ArrayList<>(order.getFood().keySet());
		final List<MenuItemPrice> pricesById = menuItemPriceRepository.findAllById(pricesId);
		final MenuOrder finalOrderEntity = orderEntity;
		final List<MenuItemPricesHasOrders> itemPricesHasOrders = pricesById.stream().map(price -> {
			MenuItemPricesHasOrders orderFoodItem = new MenuItemPricesHasOrders();
			orderFoodItem.setCount(order.getFood().get(price.getId()));
			orderFoodItem.setItemPrice(price);
			orderFoodItem.setOrder(finalOrderEntity);
			return orderFoodItem;
		}).collect(Collectors.toList());
		orderEntity.setItemPricesHasOrders(itemPricesHasOrders);
		menuItemPriceHasOrdersRepository.saveAll(itemPricesHasOrders);
		orderEntity = menuOrderRepository.saveAndFlush(orderEntity);
		//if this new order, send email
		if (order.getId() == null) {
			emailService.sendOrderToOwner(orderEntity);
		}
		return menuOrderConverter.toDto(orderEntity, true);
	}

	@Override
	public List<TicketOrderDto> orders(Long eventId) {
		final List<MenuOrder> byEvent = menuOrderRepository.findByEventEntity_Id(eventId);
		return menuOrderConverter.toDto(byEvent);
	}

	@Override
	public List<Integer> getAvailableTables(Long eventId) {
		List<Integer> integers = new ArrayList<>();
		final List<TicketOrderDto> orders = orders(eventId);
		final List<Integer> busyTables = orders.stream().map(TicketOrderDto::getTableNumber).collect(Collectors.toList());
		for (int i = 1; i < MAX_TABLE_NUMBER; i++) {
			if (!busyTables.contains(i)) {
				integers.add(i);
			}
		}
		return integers;
	}

	@Override
	public TicketOrderDto getOrder(Long orderId) {
		TicketOrderDto orderDto;
		final Optional<MenuOrder> byId = menuOrderRepository.findById(orderId);
		if (byId.isPresent()) {
			MenuOrder entity = byId.get();
			if (entity.getPayStatus() != GatewayStatus.successful && !StringUtils.isEmpty(entity.getToken())) {
				try {
					bePaidApi.getTransactionState(entity.getToken(), entity.getId() + "checkstatus" + UUID.randomUUID().toString());
				} catch (NoSuchAlgorithmException | IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
			orderDto = menuOrderConverter.toDto(byId.get(), true);
		} else {
			orderDto = new TicketOrderDto();
		}
		return orderDto;
	}
}
