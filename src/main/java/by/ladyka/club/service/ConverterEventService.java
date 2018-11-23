package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.EventEntity;

public interface ConverterEventService {
    EventDTO toEventDto(EventEntity entity);
    EventRelevantDTO toEventRelevantDto(EventEntity eventEntity);
    EventEntity toEntity(EventDTO dto, EventEntity target);
}
