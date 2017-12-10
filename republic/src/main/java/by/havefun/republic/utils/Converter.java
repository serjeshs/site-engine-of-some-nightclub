package by.havefun.republic.utils;

import by.havefun.entity.Event;
import by.havefun.republic.dto.EventDTO;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class Converter {
    public static EventDTO toEventDTO(Event event) {
        EventDTO dto = new EventDTO();
        BeanUtils.copyProperties(event, dto, "startEvent", "endEvent", "place_Name", "region_Name", "place", "region", "appUser", "categories", "appuserLikeEvents");
        dto.setStartEvent(event.getStartEventLDT());
        dto.setEndEvent(event.getEndEventLDT());
        String status = (event.getStartEventLDT().compareTo(LocalDateTime.now()) > 0) ? "Концерт уже начался" : event.getCostText();
        dto.setStatus(status);
        return dto;
    }
}
