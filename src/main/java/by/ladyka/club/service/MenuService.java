package by.ladyka.club.service;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.dto.menu.MenuPageDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MenuService {
    MenuPageDto mainPage();

    boolean init();

    MenuOrderDto order(MenuOrderDto order);
}
