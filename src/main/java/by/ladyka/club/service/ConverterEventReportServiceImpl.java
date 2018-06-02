package by.ladyka.club.service;

import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.entity.EventReportEntity;
import org.springframework.stereotype.Service;

@Service
public class ConverterEventReportServiceImpl implements ConverterEventReportService {
	@Override
	public EventGalleryDTO toEventGalleryDto(EventReportEntity entity) {
		EventGalleryDTO dto = new EventGalleryDTO();
		dto.setId(entity.getId());
		dto.setCoverUri(entity.getCoverUri());
		dto.setName(entity.getName());
		dto.setStartEvent(entity.getStartEvent());
		return dto;
	}
}
