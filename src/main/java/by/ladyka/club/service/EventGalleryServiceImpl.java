package by.ladyka.club.service;

import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.repository.EventReportRepository;
import by.ladyka.club.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static by.ladyka.club.EventStatus.PENDING;
import static java.time.LocalDateTime.now;

@Service
public class EventGalleryServiceImpl implements EventGalleryService {
	@Autowired
	private EventReportRepository eventReportRepository;
	@Autowired
	private ConverterEventReportService converterEventReportService;


	@Override
	public List<EventGalleryDTO> getLatestGalleryEvents(int count) {
		final List<EventReportEntity> reports = eventReportRepository.findFirst10ByVisibleIsTrueOrderByStartEventDesc();
		return reports.stream().map(converterEventReportService::toEventGalleryDto)
				.collect(Collectors.toList());
	}
}
