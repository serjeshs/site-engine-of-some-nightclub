package by.ladyka.club.service;

import by.ladyka.club.dto.AppUser;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;
import by.ladyka.club.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
public class EventsServiceImpl implements EventsService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ConverterEventService converterEventService;

	@Override
	public List<EventDTO> getEventsBetween(LocalDateTime after, LocalDateTime before) {
		return eventRepository
				.findAllByStartEventBetweenAndVisibleTrue(after, before)
				.stream()
				.map(converterEventService::toEventDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<EventDTO> getEventsAfter(LocalDateTime time) {
		return eventRepository
				.findAllByStartEventGreaterThanAndVisibleTrueOrderByStartEventAsc(time)
				.stream()
				.map(converterEventService::toEventDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<EventRelevantDTO> getRelevantEvents(AppUser user) {
		return eventRepository
				.findByRecommendationAndStartEventGreaterThanAndVisibleTrueOrderByStartEventAsc(Boolean.TRUE, LocalDateTime.now().minusHours(5L))
				.stream()
				.map(converterEventService::toEventRelevantDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<EventDTO> getEvents(String sort, String order, Integer page, Integer size, String filter) {
		Sort.Direction direction;
		if (!StringUtils.isEmpty(order)) {
			direction = Sort.Direction.valueOf(order.toUpperCase());
		} else {
			direction = Sort.Direction.DESC;
		}
		Pageable pg = PageRequest.of(page, size, Sort.by(new Sort.Order(direction, sort)));
		return eventRepository.findByDescriptionContainingOrNameContainingOrCostTextContainingAndVisibleTrue(filter, filter, filter, pg).stream().map(event -> converterEventService.toEventDto(event)).collect(Collectors.toList());
	}

	@Override
	public EventDTO getEvent(Long id) {
		return converterEventService.toEventDto(eventRepository.findById(id).orElse(new Event()));
	}

	@Override
	public long getTotalEvents(String filter) {
		return eventRepository.countByDescriptionContainingOrNameContainingOrCostTextContainingAndVisibleTrue(filter, filter, filter);
	}

	@Override
	public void delete(Long id) {
		eventRepository.findById(id).ifPresent(event -> {
			event.setVisible(Boolean.FALSE);
			eventRepository.save(event);
		});
	}

	@Override
	public Optional<Event> getEventById(Long event) {
		return eventRepository.findById(event);
	}

	@Override
	public EventDTO save(EventDTO dto) {
		Event entity;
		if (dto.getId() == null || dto.getId() < 1) {
			entity = new Event();
		} else {
			entity = eventRepository.findById(dto.getId()).orElseGet(Event::new);
		}
		entity = converterEventService.toEntity(dto, entity);
		entity = eventRepository.save(entity);
		return converterEventService.toEventDto(entity);
	}
}
