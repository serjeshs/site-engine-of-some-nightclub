package by.ladyka.club.service;

import by.ladyka.club.dto.menu.MenuItemPriceDto;
import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import by.ladyka.club.entity.menu.MenuItem;

import java.util.List;

public interface MenuService {
	MenuPageDto mainPage();

	boolean init();

	MenuOrderDto order(MenuOrderDto order);

	List<MenuOrderDto> orders(Long eventId);

	List<Integer> getAvailableTables(Long eventId);

	MenuOrderDto getOrder(Long orderId);

	MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem);
}
