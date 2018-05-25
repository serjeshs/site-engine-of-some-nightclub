package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;
import by.ladyka.club.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ConverterEventServiceImpl implements ConverterEventService {

    private final static Logger logger = LoggerFactory.getLogger(ConverterEventServiceImpl.class);

    @Override
    public EventDTO toEventDto(Event entity) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(entity.getId());
        eventDTO.setName(entity.getName());
        eventDTO.setDescription(entity.getDescription());
        eventDTO.setStartEvent(entity.getStartEvent());
        eventDTO.setEndEvent(entity.getEndEvent());
        eventDTO.setCost(entity.getCostMinimum());
        eventDTO.setCostText(entity.getCostText());
        eventDTO.setCoverUri(entity.getCoverUri());
        eventDTO.setStatus(entity.getStatus());
        return eventDTO;
    }

    @Override
    public EventGalleryDTO toEventGalleryDto(Event event) {
        EventGalleryDTO dto = new EventGalleryDTO();
        dto.setId(event.getId());
        dto.setCoverUri(event.getCoverUri());
        dto.setName(event.getName());
        dto.setStartEvent(event.getStartEvent());
        return dto;
    }

    @Override
    public EventRelevantDTO toEventRelevantDto(Event event) {
        EventRelevantDTO dto = new EventRelevantDTO();
        dto.setId(event.getId());
        dto.setCoverUri(event.getCoverUri());
        dto.setName(event.getName());
        dto.setStartEvent(event.getStartEvent());
        return dto;
    }

}
