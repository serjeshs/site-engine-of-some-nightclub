package by.ladyka.club.service;

import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.club.dto.menu.MenuCategoryDto;
import by.ladyka.club.dto.menu.MenuItemPriceDto;
import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import by.ladyka.club.entity.menu.MenuItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MenuService {
	MenuPageDto mainPage();

	TicketOrderDto order(TicketOrderDto order);

	List<TicketOrderDto> orders(Long eventId);

	List<Integer> getAvailableTables(Long eventId);

	TicketOrderDto getOrder(Long orderId);

	MenuItemPriceDto convertToMenuItemPriceDto(MenuItem menuItem);

	List<MenuCategoryDto> getFood();

	MenuCategoryDto saveCategory(MenuCategoryDto dto);

	MenuItemPriceDto saveMenuItemPrice(MenuItemPriceDto dto);

	BigDecimal getAmount(TicketOrderDto menuOrder);

	void setToken(String token, Long orderId);

	TicketOrderDto getOrder(String uuid);

	GatewayStatus getStatus(Long orderId);

	GatewayStatus getStatus(String uId) throws NoSuchAlgorithmException, IOException, URISyntaxException;
}
