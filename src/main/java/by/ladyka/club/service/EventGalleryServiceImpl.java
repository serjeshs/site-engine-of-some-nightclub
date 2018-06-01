package by.ladyka.club.service;

import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static by.ladyka.club.EventStatus.PENDING;
import static java.time.LocalDateTime.now;

@Service
public class EventGalleryServiceImpl implements EventGalleryService {

	private final EventRepository eventRepository;
	private final ConverterEventService converterEventService;

	@Autowired
	public EventGalleryServiceImpl(EventRepository eventRepository, ConverterEventService converterEventService) {
		this.eventRepository = eventRepository;
		this.converterEventService = converterEventService;
	}

	@Override
	public List<EventGalleryDTO> getLatestGalleryEvents(int count) {
		//TDOD WRONG !!!!
		return eventRepository
				.findAllByStartEventBetweenAndStatusGreaterThanEqual(now().plusMonths(1L), now().plusMonths(2L), PENDING.getCode())
				.stream()
				.map(converterEventService::toEventGalleryDto)
				.collect(Collectors.toList());
	}
}
