package by.ladyka.club.service.order;

import by.ladyka.club.dto.TicketTableDto;

import java.util.List;

public interface OrderTicketsService {
	List<TicketTableDto> getTables(Long eventId);
}
