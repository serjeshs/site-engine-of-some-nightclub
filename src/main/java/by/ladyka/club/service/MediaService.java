package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.MediaReportDto;

import java.util.List;

public interface MediaService {
    List<MediaReportDto> summary();

    List<EventDTO> getEvents(Long page);
}
