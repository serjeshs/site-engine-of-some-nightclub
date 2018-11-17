package by.ladyka.club.utils.converters;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import by.ladyka.club.entity.menu.MenuOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuOrderConverter {
	public MenuOrder toEntity(MenuOrderDto dto) {
		MenuOrder entity = new MenuOrder();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	public MenuOrderDto toDto(MenuOrder entity, boolean dependencies) {
		MenuOrderDto dto = new MenuOrderDto();
		BeanUtils.copyProperties(entity, dto);
		dto.setEventName(entity.getEventEntity().getName());
		if (entity.getPayStatus() != null) {
			dto.setPayStatus(entity.getPayStatus().name());
		}
		if (dependencies) {
			final List<MenuItemPricesHasOrders> itemPricesHasOrders = entity.getItemPricesHasOrders();
			Map<Long, Integer> food = new LinkedHashMap<>();
			Map<Long, BigDecimal> foodPrice = new LinkedHashMap<>();
			itemPricesHasOrders.forEach(item -> {
				food.put(item.getItemPrice().getId(), item.getCount());
				foodPrice.put(item.getItemPrice().getId(), item.getItemPrice().getValue());
			});
			dto.setFood(food);
			dto.setFoodPrice(foodPrice);
			dto.setEvent(entity.getEventEntity().getId());
		}
		return dto;
	}

	public List<MenuOrderDto> toDto(List<MenuOrder> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}

	private MenuOrderDto toDto(MenuOrder menuOrder) {
		return toDto(menuOrder, false);
	}
}
