package by.ladyka.club.dto.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MenuCategoryDto {
	private Long id;
	private String name;
	private String description;
	private Long parentId;
	private List<MenuCategoryDto> categories;
	private List<MenuItemPriceDto> menuItems;
}
