package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.MediaReportDto;
import by.ladyka.club.entity.Event;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.repository.EventReportRepository;
import by.ladyka.club.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {
  @Autowired
  private EventReportRepository eventReportRepository;
  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private ConverterEventService converterEventService;

  @Override
  public List<MediaReportDto> summary() {
    return eventReportRepository.findAllByVisibleIsTrueOrderByStartEventDesc()
            .stream().map(this::convert).collect(Collectors.toList());
  }

  @Override
  public List<EventDTO> getEvents(Long page) {
    Sort.Order orderByStartEvent = new Sort.Order(Sort.Direction.ASC, Event.startEventFieldName);
    Sort sort = Sort.by(orderByStartEvent);
    Pageable pg = PageRequest.of(page.intValue(), 10, sort);

    return eventRepository.findAll(pg).stream().map(event -> converterEventService.toEventDto(event)).collect(Collectors.toList());
  }

  private MediaReportDto convert(EventReportEntity entity) {
    MediaReportDto dto = new MediaReportDto();
    dto.setId(entity.getId());
    dto.setStartEvent(entity.getStartEvent());
    dto.setCoverUri(entity.getCoverUri());
    dto.setName(entity.getName());
    return dto;
  }
}
