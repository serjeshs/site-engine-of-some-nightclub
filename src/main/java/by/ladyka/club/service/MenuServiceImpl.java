package by.ladyka.club.service;

import by.ladyka.club.dto.menu.MenuCategoryDto;
import by.ladyka.club.dto.menu.MenuItemPriceDto;
import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import by.ladyka.club.entity.MenuOrderEntity;
import by.ladyka.club.entity.menu.MenuCategory;
import by.ladyka.club.entity.menu.MenuItem;
import by.ladyka.club.entity.menu.MenuItemPrice;
import by.ladyka.club.repository.menu.MenuCategoryRepository;
import by.ladyka.club.repository.menu.MenuItemPriceRepository;
import by.ladyka.club.repository.menu.MenuItemRepository;
import by.ladyka.club.repository.menu.MenuOrderRepository;
import by.ladyka.club.utils.converters.MenuOrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
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


    @Override
    public MenuPageDto mainPage() {
        return new MenuPageDto(
                menuCategoryRepository.findAllByParentIsNull().stream().map(this::convertToMenuCategoryDto).collect(Collectors.toList()),
                eventsService.getEventsBetween(LocalDateTime.now(), LocalDateTime.now().plusMonths(2L))
                );
    }

    @Override
    public boolean init() {
        MenuCategory hotEat = new MenuCategory();
        hotEat.setName("ГОРЯЧИЕ БЛЮДА");
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
        slicing =  menuCategoryRepository.saveAndFlush(slicing);
        MenuItem meal = new MenuItem();
        meal.setName("Ассорти мясное");
        meal.setActive(Boolean.TRUE);
        meal.setDescription("(Балык с/к, ветчина в/к, колбаса с/к, грудинка к/в, огурец свежий, томат, горчица, маслины, зелень)");
        meal.setDescriptionProportions("200/80/20/20");
        meal.setCategory(slicing);
        meal =  menuItemRepository.saveAndFlush(meal);
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

    private MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem) {
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
        MenuOrderEntity entity = menuOrderConverter.toEntity(order);
        menuOrderRepository.save(entity);
        return menuOrderConverter.toDto(entity);
    }
}
