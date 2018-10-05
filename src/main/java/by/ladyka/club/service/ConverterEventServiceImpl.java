package by.ladyka.club.service;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.dto.EventRelevantDTO;
import by.ladyka.club.entity.Event;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ConverterEventServiceImpl implements ConverterEventService {

	@Override
	public EventDTO toEventDto(Event entity) {
		EventDTO eventDTO = new EventDTO();
		BeanUtils.copyProperties(entity, eventDTO);
		return eventDTO;
	}

	@Override
	public EventRelevantDTO toEventRelevantDto(Event event) {
		EventRelevantDTO dto = new EventRelevantDTO();
		dto.setId(event.getId());
		dto.setCoverUri(event.getCoverUri());
		dto.setName(event.getName());
		dto.setStartEvent(event.getStartEvent());
		return dto;
	}

	public Event toEntity(EventDTO dto, Event target) {
		BeanUtils.copyProperties(dto,target);
		return target;
	}

}
