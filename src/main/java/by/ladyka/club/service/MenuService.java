package by.ladyka.club.service;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface MenuService {
    MenuPageDto mainPage();

    boolean init();

    MenuOrderDto order(MenuOrderDto order);

    List<MenuOrderDto> orders(Long eventId);

    List<Integer> getAvailableTables(Long eventId);

    MenuOrderDto getOrder(Long orderId);
}
