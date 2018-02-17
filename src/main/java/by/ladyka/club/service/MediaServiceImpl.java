package by.ladyka.club.service;

import by.ladyka.club.dto.MediaReportDto;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.repository.EventReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private EventReportRepository eventReportRepository;

    @Override
    public List<MediaReportDto> summary() {
        return eventReportRepository.findAllByVisibleIsTrueOrderByStartEventDesc()
                .stream().map(this::convert).collect(Collectors.toList());
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
