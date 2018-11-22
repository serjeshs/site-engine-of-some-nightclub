package by.ladyka.club.service.order;

import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.dto.tikets.TablePlaceDto;
import by.ladyka.club.entity.order.OrderEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderEntityConverter {
	public TicketOrderDto toDto(OrderEntity entity, boolean deps) {
		TicketOrderDto dto = new TicketOrderDto();
		BeanUtils.copyProperties(entity, dto);
		if (deps) {
			dto.setTables(entity.getTableNumbers()
					.stream()
					.map(ent -> new TablePlaceDto(ent.getTableNumber(), ent.getPlace()))
					.collect(Collectors.toList()));
		}
		return dto;
	}
}
