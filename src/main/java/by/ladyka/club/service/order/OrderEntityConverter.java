package by.ladyka.club.service.order;

import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.dto.tikets.TablePlaceDto;
import by.ladyka.club.dto.tikets.TicketTableDto;
import by.ladyka.club.dto.tikets.TicketsOrderDto;
import by.ladyka.club.entity.order.OrderEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEntityConverter {
	public TicketOrderDto toDto(OrderEntity entity, boolean deps) {
		TicketOrderDto dto = new TicketOrderDto();
		BeanUtils.copyProperties(entity, dto);
		if (entity.getEnterTime() != null) {
			dto.setAcceptor(entity.getAcceptor().getPublishName());
			dto.setEnterTime(entity.getEnterTime());
		}
		if (deps) {
			dto.setTables(entity.getTableNumbers()
					.stream()
					.map(ent -> new TablePlaceDto(ent.getTableNumber(), ent.getPlace()))
					.collect(Collectors.toList()));
		}
		return dto;
	}

	public TicketsOrderDto toTicketsOrderDto(OrderEntity entity, boolean deps) {
		TicketsOrderDto dto = new TicketsOrderDto();
		BeanUtils.copyProperties(entity, dto);
		if (deps) {
//			dto.setTables(entity.getTableNumbers()
//					.stream()
//					.map(ent -> new TicketTableDto(ent.getTableNumber(), ent.getPlace()))
//					.collect(Collectors.toList()));
		}
		return dto;
	}

	public List<TicketsOrderDto> toTicketsOrderDtos(List<OrderEntity> tickets, boolean deps) {
		return tickets.stream().map(t -> toTicketsOrderDto(t, deps)).collect(Collectors.toList());
	}
}
