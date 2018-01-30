package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;
import by.ladyka.club.entity.old.ModxSiteContent;

public interface ConverterService {
    Boolean convertDataBase();
    EventDTO toEventDto(Event entity);
    EventGalleryDTO toEventGalleryDto(Event event);
    EventRelevantDTO toEventRelevantDto(Event event);
}
