package by.ladyka.club.service;

import by.ladyka.club.dto.menu.MenuCategoryDto;
import by.ladyka.club.dto.menu.MenuItemPriceDto;
import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import by.ladyka.club.entity.Event;
import by.ladyka.club.entity.menu.*;
import by.ladyka.club.repository.menu.*;
import by.ladyka.club.service.email.EmailService;
import by.ladyka.club.utils.converters.MenuOrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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


	@Override
	public MenuPageDto mainPage() {
		return new MenuPageDto(
				getFood(),
				eventsService.getEventsAfter(LocalDateTime.now())
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
		mc.setActive(true);
		mc.setCategoryType(1);
		mc.setDescription(dto.getDescription());
		return convertToMenuCategoryDto(menuCategoryRepository.save(mc));
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
		}
		if (!CollectionUtils.isEmpty(menuCategory.getChildren())) {
			List<MenuCategoryDto> categoryDtos = menuCategory.getChildren().stream()
					.map(this::convertToMenuCategoryDto).filter(Objects::nonNull).collect(Collectors.toList());
			dto.setCategories(categoryDtos);
		}
		return dto;
	}

	public MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem) {
		MenuItemPriceDto dto = new MenuItemPriceDto();
		dto.setId(menuItem.getId());
		dto.setName(menuItem.getName());
		dto.setDescription(menuItem.getDescription());
		dto.setDescriptionProportions(menuItem.getDescriptionProportions());
		final Optional<MenuItemPrice> first = menuItem.getPrices().stream().filter(this::active).findFirst();
		if (first.isPresent()) {
			final MenuItemPrice menuItemPrice = first.get();
			dto.setItemPriceId(menuItemPrice.getId());
			dto.setPrice(menuItemPrice.getValue());
		} else {
			return null;
		}
		return dto;
	}

	private boolean active(MenuItemPrice menuItemPrice) {
		return menuItemPrice.getEndTime() == null;
	}

	@Override
	public MenuOrderDto order(MenuOrderDto order) {
		MenuOrder orderEntity = menuOrderConverter.toEntity(order);
		final Optional<Event> eventById = eventsService.getEventById(order.getEvent());
		if (eventById.isPresent()) {
			orderEntity.setEvent(eventById.get());
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
	public List<MenuOrderDto> orders(Long eventId) {
		final List<MenuOrder> byEvent = menuOrderRepository.findByEvent_Id(eventId);
		return menuOrderConverter.toDto(byEvent);
	}

	@Override
	public List<Integer> getAvailableTables(Long eventId) {
		List<Integer> integers = new ArrayList<>();
		final List<MenuOrderDto> orders = orders(eventId);
		final List<Integer> busyTables = orders.stream().map(MenuOrderDto::getTableNumber).collect(Collectors.toList());
		for (int i = 1; i < MAX_TABLE_NUMBER; i++) {
			if (!busyTables.contains(i)) {
				integers.add(i);
			}
		}
		return integers;
	}

	@Override
	public MenuOrderDto getOrder(Long orderId) {
		MenuOrderDto orderDto;
		final Optional<MenuOrder> byId = menuOrderRepository.findById(orderId);
		if (byId.isPresent()) {
			orderDto = menuOrderConverter.toDto(byId.get(), true);
		} else {
			orderDto = new MenuOrderDto();
		}
		return orderDto;
	}
}
