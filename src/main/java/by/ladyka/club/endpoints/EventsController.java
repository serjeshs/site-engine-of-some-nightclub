package by.ladyka.club.endpoints;

import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsController {

	@Autowired
	private EventsService eventsService;

	@GetMapping
	public @ResponseBody
	ResponseEntity get(Principal principal, HttpServletRequest httpServletRequest, Long id) {
		return new ResponseEntity<EventDTO>(eventsService.getEvent(id), HttpStatus.OK);
	}
}
