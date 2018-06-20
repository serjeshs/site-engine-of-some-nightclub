package by.ladyka.club.service;

import by.ladyka.club.dto.EventReportDto;
import org.springframework.http.HttpStatus;

public interface EventReportService {
	EventReportDto getEventReport(Long id);
}
