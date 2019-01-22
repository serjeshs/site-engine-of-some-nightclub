package by.ladyka.club.dto.report;

import by.ladyka.club.dto.menu.TicketOrderDto;
import lombok.Data;

@Data
public class TicketOrderReportDto extends TicketOrderDto {

	private String eventImageUrl;

	public TicketOrderReportDto(TicketOrderDto dto) {
		super(
				dto.getId(),
				dto.getFood(),
				dto.getFoodPrice(),
				dto.getName(),
				dto.getSurname(),
				dto.getEmail(),
				dto.getPhone(),
				dto.getEvent(),
				dto.getEventName(),
				dto.getArrivalTime(),
				dto.getPeople(),
				dto.getDescription(),
				dto.getTableNumber(),
				dto.getUuid(),
				dto.getPayStatus(),
				dto.getTotalMoney(),
				dto.getDance(),
				dto.getTables(),
				dto.getEnterTime(),
				dto.getAcceptor()
		);
	}
}
