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

	List<EventRelevantDTO> getRelevantEvents(AppUser user);

	Optional<Event> getEventById(Long event);

	EventDTO save(EventDTO event);
	List<EventDTO> getEvents(Long page);
}
