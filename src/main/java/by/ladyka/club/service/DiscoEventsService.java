package by.ladyka.club.service;

import by.ladyka.club.dto.shared.BaseListResultDto;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.DayOfWeek.*;

@Service
@Scope(value = "periodical")
public class DiscoEventsService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	private ConverterEventService converterEventService;

	private BaseListResultDto<EventDTO> discoEvents = null;

	public BaseListResultDto<EventDTO> getDiscoEvents() {
		if (discoEvents == null) {
			List<EventDTO> list = new ArrayList<>();
			final int day = LocalDate.now().getDayOfWeek().getValue();
			if (day < SATURDAY.getValue()) {
				list.add(getEventDiscoDTO(FRIDAY));
				list.add(getEventDiscoDTO(SATURDAY));
				list.add(getEventDiscoDTO(SUNDAY));
			} else if (day < SUNDAY.getValue()) {
				list.add(getEventDiscoDTO(SATURDAY));
				list.add(getEventDiscoDTO(SUNDAY));
				list.add(getEventDiscoDTO(FRIDAY));
			} else {
				list.add(getEventDiscoDTO(SUNDAY));
				list.add(getEventDiscoDTO(FRIDAY));
				list.add(getEventDiscoDTO(SATURDAY));
			}
			this.discoEvents = new BaseListResultDto<>(list);
		}
		return discoEvents;
	}

	private EventDTO getEventDiscoDTO(DayOfWeek day) {
		final LocalDateTime startDiscoEvent = LocalDate.now().plusDays(day.getValue() - LocalDate.now().atStartOfDay().getDayOfWeek().getValue()).atStartOfDay().plusHours(22L);
		List<EventEntity> days = getAllByStartEventBetween(startDiscoEvent);
		return converterEventService.toEventDto(days.get(0));
	}

	private List<EventEntity> getAllByStartEventBetween(LocalDateTime startDiscoEvent) {
		List<EventEntity> days = eventRepository.findAllByStartEventBetweenAndVisibleTrue(startDiscoEvent, startDiscoEvent.plusHours(4L));
		if (CollectionUtils.isEmpty(days)) {
			return getAllByStartEventBetween(startDiscoEvent.minusWeeks(1L));
		} else {
			return days;
		}
	}
}
