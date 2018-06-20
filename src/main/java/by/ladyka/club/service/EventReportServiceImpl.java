package by.ladyka.club.service;

import by.ladyka.club.dto.EventReportDto;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.entity.EventReportImageEntity;
import by.ladyka.club.repository.EventReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EventReportServiceImpl implements EventReportService {
	@Autowired
	EventReportRepository eventReportRepository;

	@Override
	public EventReportDto getEventReport(Long id) {
		EventReportDto dto = new EventReportDto();
		final EventReportEntity report = eventReportRepository.getOne(id);
		dto.setCoverUri(report.getCoverUri());
		dto.setName(report.getName());
		dto.setStartEvent(report.getStartEvent());
		dto.setImages(report.getImages().stream().map(EventReportImageEntity::getImageUrl).collect(Collectors.toSet()));
		return dto;
	}
}
