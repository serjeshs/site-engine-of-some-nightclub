package by.ladyka.club.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuCategoryDto {
	private String name;
	private String description;
	private List<MenuCategoryDto> categories;
	private List<MenuItemPriceDto> menuItems;
}
