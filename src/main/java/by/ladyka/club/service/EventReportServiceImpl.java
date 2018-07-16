package by.ladyka.club.service;

import by.ladyka.club.dto.EventReportDto;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.entity.EventReportImageEntity;
import by.ladyka.club.repository.EventReportImageRepository;
import by.ladyka.club.repository.EventReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class EventReportServiceImpl implements EventReportService {
	@Autowired
	EventReportRepository eventReportRepository;
	@Autowired
	private EventReportImageRepository eventReportImageRepository;

	@Override
	public EventReportDto getEventReport(Long id) {
		return toDto(eventReportRepository.findById(id).orElseThrow(RuntimeException::new));
	}

	private EventReportDto toDto(EventReportEntity report) {
		EventReportDto dto = new EventReportDto();
		dto.setId(report.getId());
		dto.setCoverUri(report.getCoverUri());
		dto.setName(report.getName());
		dto.setStartEvent(report.getStartEvent());
		if (!CollectionUtils.isEmpty(report.getImages())) {
			dto.setImages(report.getImages().stream().map(EventReportImageEntity::getImageUrl).collect(Collectors.toSet()));
		} else {
			dto.setImages(Collections.emptySet());
		}
		return dto;
	}

	@Override
	public EventReportDto save(EventReportDto dto) {
		EventReportEntity entity = eventReportRepository.findById((dto.getId() != null) ? dto.getId() : -1).orElse(new EventReportEntity());
		entity.setName(dto.getName());
		entity.setVisible(true);
		entity.setStartEvent(dto.getStartEvent());
		entity.setCoverUri("/files/2018/07/06/cover.jpg");
		entity = eventReportRepository.save(entity);
		return toDto(entity);
	}

	@Override
	public Boolean addFileToReport(Long id, String filePath) {
		EventReportImageEntity e = new EventReportImageEntity();
		final EventReportEntity eventReportEntity = eventReportRepository.findById(id).orElseThrow(RuntimeException::new);
		e.setEventReport(eventReportEntity);
		e.setImageUrl(filePath);
		e.setHeight(-1L);
		e.setSize("-1L");
		e.setWidth(-1L);
		eventReportImageRepository.save(e);
		return true;
	}
}
