package by.ladyka.club.service;

import by.ladyka.club.dto.EventReportDto;

public interface EventReportService {
	EventReportDto getEventReport(Long id);

	EventReportDto save(EventReportDto dto);

	Boolean addFileToReport(Long id, String filePath);
}
