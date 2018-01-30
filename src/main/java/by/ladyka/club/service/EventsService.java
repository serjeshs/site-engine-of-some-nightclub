package by.ladyka.club.service;

import by.ladyka.club.dto.AppUser;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventRelevantDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface EventsService {
    List<EventDTO> getEventsBetween(LocalDateTime after, LocalDateTime before);
    List<EventRelevantDTO> getRelevantEvents(AppUser user);
}
