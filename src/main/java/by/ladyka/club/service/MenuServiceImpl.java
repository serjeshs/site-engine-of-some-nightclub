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

import java.math.BigDecimal;
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
				menuCategoryRepository.findAllByParentIsNull().stream().map(this::convertToMenuCategoryDto).collect(Collectors.toList()),
				eventsService.getEventsAfter(LocalDateTime.now())
		);
	}

	@Deprecated
	@Override
	public boolean init() {
		MenuCategory categoryGeneral = new MenuCategory();
		categoryGeneral.setName("General");
		categoryGeneral = menuCategoryRepository.saveAndFlush(categoryGeneral);
		MenuCategory hotEat = new MenuCategory();
		hotEat.setName("ГОРЯЧИЕ БЛЮДА");
		hotEat.setParent(categoryGeneral);
		menuCategoryRepository.saveAndFlush(hotEat);
		MenuItem chicken = new MenuItem();
		chicken.setActive(Boolean.TRUE);
		chicken.setName("Куриные наггетсы с картофелем фри и овощами");
		chicken.setDescription("(Наггетсы  п/ф, картофель фри, огурец, салат ,соус, зелень)");
		chicken.setDescriptionProportions("370/50");
		chicken.setCategory(hotEat);
		chicken = menuItemRepository.saveAndFlush(chicken);
		MenuItemPrice chickenItemPrice = new MenuItemPrice(true, LocalDateTime.now(), null, new BigDecimal(15), chicken);
		chickenItemPrice = menuItemPriceRepository.saveAndFlush(chickenItemPrice);
//        chicken = menuItemRepository.saveAndFlush(chicken);
//        hotEat.setItems(Collections.singletonList(chicken));
//        menuCategoryRepository.saveAndFlush(hotEat);

		MenuCategory slicing = new MenuCategory();
		slicing.setName("НАРЕЗКИ");
		slicing.setParent(categoryGeneral);
		slicing = menuCategoryRepository.saveAndFlush(slicing);
		MenuItem meal = new MenuItem();
		meal.setName("Ассорти мясное");
		meal.setActive(Boolean.TRUE);
		meal.setDescription("(Балык с/к, ветчина в/к, колбаса с/к, грудинка к/в, огурец свежий, томат, горчица, маслины, зелень)");
		meal.setDescriptionProportions("200/80/20/20");
		meal.setCategory(slicing);
		meal = menuItemRepository.saveAndFlush(meal);
		MenuItemPrice mealPrice = new MenuItemPrice(true, LocalDateTime.now(), null, new BigDecimal(15), meal);
		mealPrice = menuItemPriceRepository.saveAndFlush(mealPrice);
		meal.setPrices(Collections.singletonList(mealPrice));
//        meal = menuItemRepository.saveAndFlush(meal);

		MenuItem vegetables = new MenuItem();
		vegetables.setName("Ассорти овощное");
		vegetables.setDescription("(Томат, огурец свежий, перец свежий, маслины, зелень)");
		vegetables.setDescriptionProportions("190/20/2");
		vegetables.setActive(Boolean.TRUE);
		vegetables.setCategory(slicing);
		menuItemRepository.saveAndFlush(vegetables);
		MenuItemPrice vegetablesItemPrice = new MenuItemPrice(true, LocalDateTime.now(), null, new BigDecimal(10), vegetables);
		menuItemPriceRepository.saveAndFlush(vegetablesItemPrice);
		vegetables.setPrices(Collections.singletonList(vegetablesItemPrice));
//        menuItemRepository.saveAndFlush(vegetables);

		MenuItem cheese = new MenuItem();
		cheese.setName("Ассорти сырное");
		cheese.setDescription("(Сыр твёрдый Джугас, сыр Фетаки, сыр Дор-Блю, виноград, тосты, зелень)");
		cheese.setDescriptionProportions("150/50/50/2");
		cheese.setActive(Boolean.TRUE);
		cheese.setCategory(slicing);
		menuItemRepository.saveAndFlush(cheese);
		MenuItemPrice cheeseItemPrice = new MenuItemPrice(true, LocalDateTime.now(), null, new BigDecimal(15), cheese);
		menuItemPriceRepository.saveAndFlush(cheeseItemPrice);
		cheese.setPrices(Collections.singletonList(cheeseItemPrice));
//        menuItemRepository.saveAndFlush(cheese);

		MenuItem fruts = new MenuItem();
		fruts.setName("Ассорти фруктовое");
		fruts.setDescription("(Апельсин, виноград, киви, яблоко)");
		fruts.setDescriptionProportions("1/400");
		fruts.setActive(Boolean.TRUE);
		fruts.setCategory(slicing);
		menuItemRepository.saveAndFlush(fruts);
		MenuItemPrice frutsItemPrice = new MenuItemPrice(true, LocalDateTime.now(), null, new BigDecimal(10), fruts);
		menuItemPriceRepository.saveAndFlush(frutsItemPrice);
		fruts.setPrices(Collections.singletonList(frutsItemPrice));
//        menuItemRepository.saveAndFlush(fruts);


//        slicing.setItems(Arrays.asList(meal, vegetables, cheese, fruts));
		//      menuCategoryRepository.saveAndFlush(slicing);
		return true;
	}

	private MenuCategoryDto convertToMenuCategoryDto(MenuCategory menuCategory) {
		MenuCategoryDto dto = new MenuCategoryDto();
		dto.setName(menuCategory.getName());
		dto.setDescription(menuCategory.getDescription());
		List<MenuItemPriceDto> menuItems = menuCategory.getItems().stream()
				.map(this::convertToMenuItemPriceDto).filter(Objects::nonNull).collect(Collectors.toList());
		dto.setMenuItems(menuItems);
		List<MenuCategoryDto> categoryDtos = menuCategory.getChildren().stream()
				.map(this::convertToMenuCategoryDto).filter(Objects::nonNull).collect(Collectors.toList());
		dto.setCategories(categoryDtos);
		return dto;
	}

	public MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem) {
		MenuItemPriceDto dto = new MenuItemPriceDto();
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
