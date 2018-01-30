package by.ladyka.club.service;

import by.ladyka.club.dto.MenuCategoryDto;
import by.ladyka.club.dto.MenuItemPriceDto;
import by.ladyka.club.entity.menu.MenuCategory;
import by.ladyka.club.entity.menu.MenuItem;
import by.ladyka.club.entity.menu.MenuItemPrice;
import by.ladyka.club.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    public MenuServiceImpl(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public List<MenuCategoryDto> mainPage() {
        return menuCategoryRepository.find().stream().map(this::convertToMenuCategoryDto).collect(Collectors.toList());
    }

    private MenuCategoryDto convertToMenuCategoryDto(MenuCategory menuCategory) {
        MenuCategoryDto dto = new MenuCategoryDto();
        dto.setName(menuCategory.getName());
        List<MenuItemPriceDto> menuItems = menuCategory.getItems().stream().map(this::convertToMenuItemPriceDto).filter(Objects::nonNull).collect(Collectors.toList());
        dto.setMenuItemDtos(menuItems);
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
}
