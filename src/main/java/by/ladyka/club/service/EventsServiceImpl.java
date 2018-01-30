package by.ladyka.club.service;

import by.ladyka.club.dto.AppUser;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static by.ladyka.club.EventStatus.PENDING;
import static java.time.LocalDateTime.now;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ConverterService converterService;

    @Override
    public List<EventDTO> getEventsBetween(LocalDateTime after, LocalDateTime before) {
        return eventRepository
                .findAllByStartEventBetweenAndStatusGreaterThanEqual(after, before, PENDING.getCode())
                .stream()
                .map(converterService::toEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventRelevantDTO> getRelevantEvents(AppUser user) {
        return eventRepository
                .findAllByStartEventBetweenAndStatusGreaterThanEqual(now().plusMonths(1L), now().plusMonths(2L), PENDING.getCode())
                .stream()
                .map(converterService::toEventRelevantDto)
                .collect(Collectors.toList());
    }
}
