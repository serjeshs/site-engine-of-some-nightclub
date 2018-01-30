package by.ladyka.club.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class MenuItemPriceDto {
    private Long itemPriceId;
    private String name;
    private String description;
    private String descriptionProportions;
    private BigDecimal price;
}
