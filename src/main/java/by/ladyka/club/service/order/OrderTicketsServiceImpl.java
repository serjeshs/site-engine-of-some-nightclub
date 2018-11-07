package by.ladyka.club.service.order;

import by.ladyka.club.dto.TicketPlaceDto;
import by.ladyka.club.dto.TicketPlaceStatus;
import by.ladyka.club.dto.TicketTableDto;
import by.ladyka.club.repository.OrderItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTicketsServiceImpl implements OrderTicketsService {
	@Autowired
	private OrderItemEntityRepository repository;

	@Override
	public List<TicketTableDto> getTables(Long eventId) {
		List<TicketTableDto> tables = new ArrayList<>();
		for (int table = 1; table < 26; table++) {
			TicketTableDto ticketTableDto = new TicketTableDto();
			ticketTableDto.setTableNumber(table);
			List<TicketPlaceDto> places = new ArrayList<>();
			for (int placeNumber = 1; placeNumber < 5; placeNumber++) {
				TicketPlaceDto place = new TicketPlaceDto();
				place.setStatus(TicketPlaceStatus.FREE);
				place.setPlaceNumber(placeNumber);
				places.add(place);
			}
			ticketTableDto.setPlaces(places);
			tables.add(ticketTableDto);
		}
		repository.findByOrderEntityEventId(eventId).forEach(orderItemEntity -> {
			for (TicketTableDto table : tables) {
				if (table.getTableNumber() == orderItemEntity.getTableNumber()) {
					List<TicketPlaceDto> places = table.getPlaces();
					for (TicketPlaceDto place : places) {
						if (place.getPlaceNumber() == orderItemEntity.getPlace()) {
							place.setStatus(TicketPlaceStatus.BUSY);
						}
					}
				}
			}
		});
		return tables;
	}
}
