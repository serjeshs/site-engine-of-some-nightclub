package by.ladyka.club.endpoints;

import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.service.ConverterEventService;
import by.ladyka.club.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/event")
public class EventsHtmlController {

    @Autowired
    private EventsService eventService;

    @Autowired
    private ConverterEventService converterEventService;

    @GetMapping("/{eventId}")
    public ModelAndView get(@PathVariable long eventId) {

        EventEntity event = eventService.getEventById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ModelAndView modelAndView = new ModelAndView("/event/event");
        modelAndView.addObject("event", converterEventService.toEventDto(event));
        return modelAndView;

    }
}
