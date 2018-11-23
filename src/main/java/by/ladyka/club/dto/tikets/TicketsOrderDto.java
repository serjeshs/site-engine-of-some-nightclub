package by.ladyka.club.dto.tikets;

import by.ladyka.club.dto.EventDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import java.util.List;

@Getter
@Setter
public class TicketsOrderDto {
	private EventDTO event;
	private int danceFloor;
	private int placeSeats;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String description;
	@AssertTrue
	private Boolean rulesCheck;
	@AssertTrue
	private Boolean  offerCheck;
	private List<TicketTableDto> tables;
}
