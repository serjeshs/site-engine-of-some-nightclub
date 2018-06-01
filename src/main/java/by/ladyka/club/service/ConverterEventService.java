package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;

public interface ConverterEventService {
	EventDTO toEventDto(Event entity);

	EventGalleryDTO toEventGalleryDto(Event event);

	EventRelevantDTO toEventRelevantDto(Event event);

	Event toEntity(EventDTO event);

	Event toEntity(EventDTO dto, Event target);
}
