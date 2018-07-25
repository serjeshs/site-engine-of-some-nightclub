package by.ladyka.club.service;

import by.ladyka.club.dto.AppUser;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventsService {
	List<EventDTO> getEventsBetween(LocalDateTime after, LocalDateTime before);

	List<EventDTO> getEventsAfter(LocalDateTime time);

	List<EventRelevantDTO> getRelevantEvents(AppUser user);

	Optional<Event> getEventById(Long event);

	EventDTO save(EventDTO event);

	List<EventDTO> getEvents(String sort, String order, Integer page, Integer size, String filter);

	EventDTO getEvent(Long id);

	long getTotalEvents(String filter);
}
