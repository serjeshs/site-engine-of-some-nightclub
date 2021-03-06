package by.ladyka.club.service;

import by.ladyka.club.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AfishaServiceImpl implements AfishaService {
	private static final int GALLERY_EVENTS_MAIN_PAGE_MAKE_SETTING = 10;
	@Autowired
	private EventsService eventsService;
	@Autowired
	private EventGalleryService eventGalleryService;

	@Override
	public MainPageDTO mainPage(AppUser user) {
		final List<EventDTO> events = eventsService.getEventsAfter(LocalDateTime.now().minusHours(5L));
		final List<EventRelevantDTO> relevant = eventsService.getRelevantEvents(user);
		final List<EventGalleryDTO> gallery = eventGalleryService.getLatestGalleryEvents(GALLERY_EVENTS_MAIN_PAGE_MAKE_SETTING);
		return build(events, relevant, gallery);
	}

	private MainPageDTO build(List<EventDTO> events, List<EventRelevantDTO> relevant, List<EventGalleryDTO> gallery) {
		MainPageDTO mainPageDTO = new MainPageDTO();
		LocalDateTime dateTime = LocalDate.now().atStartOfDay();
		for (EventDTO event : events) {
			final LocalDateTime startEvent = event.getStartEvent();
			final long between = ChronoUnit.HOURS.between(dateTime, startEvent.toLocalDate().atStartOfDay());
			if (between < 24) {
				mainPageDTO.getToday().add(event);
			} else if (between < 24 * 2) {
				mainPageDTO.getTomorrow().add(event);
			} else if (between < 24 * (7 + 7 - LocalDateTime.now().getDayOfWeek().getValue())) {
				mainPageDTO.getCurrentAndNextWeek().add(event);
			} else if (startEvent.getMonthValue() == LocalDateTime.now().getMonthValue()) {
				mainPageDTO.getCurrentMonth().add(event);
			} else if (startEvent.getMonthValue() == LocalDateTime.now().plusMonths(1L).getMonthValue()) {
				mainPageDTO.getNextMonth().add(event);
			} else {
				mainPageDTO.getNextNextMonth().add(event);
			}
		}
		mainPageDTO.setRelevant(relevant);
		mainPageDTO.setGallery(gallery);
		return mainPageDTO;
	}
}
