package by.ladyka.club.service;

import by.ladyka.club.dto.EventGalleryDTO;
import by.ladyka.club.entity.EventReportEntity;
import by.ladyka.club.repository.EventReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
