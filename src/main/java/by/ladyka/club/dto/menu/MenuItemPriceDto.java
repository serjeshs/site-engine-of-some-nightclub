package by.ladyka.club.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class MenuItemPriceDto {
	private Long id;
	private Long itemPriceId;
	private Long categoryId;
	private String name;
	private String description;
	private String descriptionProportions;
	private BigDecimal price;
}
