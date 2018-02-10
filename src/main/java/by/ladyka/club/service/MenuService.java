package by.ladyka.club.service;

import by.ladyka.club.dto.MenuCategoryDto;

import java.util.List;

public interface MenuService {
    List<MenuCategoryDto> mainPage();

    boolean init();
}
