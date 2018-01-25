package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuCategoryDto {
    private String name;
    private List<MenuItemPriceDto> menuItemDtos;
}
