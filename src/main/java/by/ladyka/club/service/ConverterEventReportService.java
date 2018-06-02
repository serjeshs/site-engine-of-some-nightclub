package by.ladyka.club.service;

import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.entity.EventReportEntity;

public interface ConverterEventReportService {
	EventGalleryDTO toEventGalleryDto(EventReportEntity entity);
}
