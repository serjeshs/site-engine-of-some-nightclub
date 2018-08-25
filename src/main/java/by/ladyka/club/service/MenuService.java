package by.ladyka.club.service;

import by.ladyka.club.dto.menu.MenuCategoryDto;
import by.ladyka.club.dto.menu.MenuItemPriceDto;
import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import by.ladyka.club.entity.menu.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public interface MenuService {
	MenuPageDto mainPage();

	MenuOrderDto order(MenuOrderDto order);

	List<MenuOrderDto> orders(Long eventId);

	List<Integer> getAvailableTables(Long eventId);

	MenuOrderDto getOrder(Long orderId);

	MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem);

	List<MenuCategoryDto> getFood();

	MenuCategoryDto saveCategory(MenuCategoryDto dto);

	MenuItemPriceDto saveMenuItemPrice(MenuItemPriceDto dto);

	BigDecimal getAmount(MenuOrderDto menuOrder);

	void setToken(String token, Long orderId);

	MenuOrderDto getOrder(String uuid);
}
