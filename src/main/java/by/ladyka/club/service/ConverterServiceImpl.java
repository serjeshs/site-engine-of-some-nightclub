package by.ladyka.club.service;

import by.ladyka.club.EventStatus;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;
import by.ladyka.club.entity.old.ModxSiteContent;
import by.ladyka.club.entity.old.ModxSiteTmplVarContentValues;
import by.ladyka.club.entity.old.ModxSiteTmplVars;
import by.ladyka.club.repository.EventRepository;
import by.ladyka.club.repository.ModxSiteContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import static by.ladyka.club.EventStatus.DONE;
import static by.ladyka.club.EventStatus.DRAFT;
import static by.ladyka.club.EventStatus.PENDING;
import static by.ladyka.club.entity.old.ModxSiteTmplVars.*;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final ModxSiteContentRepository modxSiteContentRepository;
    private final EventRepository eventRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofPattern("dd-MM-yyyy H:mm:ss");

    @Autowired
    public ConverterServiceImpl(ModxSiteContentRepository modxSiteContentRepository, EventRepository eventRepository) {
        this.modxSiteContentRepository = modxSiteContentRepository;
        this.eventRepository = eventRepository;
    }

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

    @Override
    public Boolean convertDataBase() {
        try {
            modxSiteContentRepository.findAll()
                    .stream()
                    .map(this::convert)
                    .filter(Objects::nonNull)
                    .forEach(event -> {
                        try {
                            eventRepository.save(event);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Event convert(ModxSiteContent modxSiteContent) {
        if (modxSiteContent.getTemplate() == 7) {
            final List<ModxSiteTmplVarContentValues> contentValues = modxSiteContent.getContentValues();
            Event event = new Event();
            event.setId(modxSiteContent.getId());
            event.setCostMinimum(new BigDecimal(0));
            event.setCostText(get(contentValues, price));
            event.setCoverUri("http://republic-club.by/" + get(contentValues, img_));
            event.setName(modxSiteContent.getPagetitle());
            event.setDescription(modxSiteContent.getContent());
            event.setStartEvent(toLDT(get(contentValues, event_date)));
            event.setEndEvent(toLDT(get(contentValues, event_end_date)));
            event.setStatus(getStatus(event.getStartEvent()).getCode());
            return event;
        } else {
            return null;
        }

    }

    private EventStatus getStatus(LocalDateTime startEvent) {
        if (startEvent == null) {
            return DRAFT;
        }
        int c = LocalDateTime.now().compareTo(startEvent);
        return (c < 0) ? DONE : PENDING;
    }

    private LocalDateTime toLDT(String s) {

        if (s == null) {
            return null;
        }
        try {
            return LocalDateTime.parse(s, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            try {
                return LocalDateTime.parse(s, dateTimeFormatterShort);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private String get(List<ModxSiteTmplVarContentValues> contentValues, ModxSiteTmplVars var) {
        return contentValues
                .stream()
                .filter(contentValue -> Objects.equals(contentValue.getTmplvarid(), var.getId()))
                .findFirst()
                .map(ModxSiteTmplVarContentValues::getValue)
                .orElse(null);
    }
}
