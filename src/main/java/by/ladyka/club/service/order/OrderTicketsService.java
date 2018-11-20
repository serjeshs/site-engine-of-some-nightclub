package by.ladyka.club.service.order;

import by.ladyka.club.dto.TicketTableDto;
import by.ladyka.club.dto.TicketsOrderDto;

import java.util.List;

public interface OrderTicketsService {
	List<TicketTableDto> getTables(Long eventId);

	String bookAndPay(TicketsOrderDto dto);
}
