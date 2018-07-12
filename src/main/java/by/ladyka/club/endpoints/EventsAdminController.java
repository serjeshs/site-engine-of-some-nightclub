package by.ladyka.club.endpoints;

import by.ladyka.club.dto.BaseListResultDto;
import by.ladyka.club.dto.EventDTO;
import by.ladyka.club.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventsAdminController {

	@Autowired
	EventsService eventsService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity get(Principal principal, HttpServletRequest httpServletRequest, @RequestParam(required = false) String sort, String order, Integer page, Integer size, @RequestParam(required = false) String filter) {
		final List<EventDTO> events = eventsService.getEvents(sort, order, page, size, filter);
		long total = eventsService.getTotalEvents(filter);
		return new ResponseEntity<BaseListResultDto<EventDTO>>(new BaseListResultDto<>(events, total), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity save(Principal principal, HttpServletRequest httpServletRequest, @RequestBody EventDTO event) {
		return new ResponseEntity<EventDTO>(eventsService.save(event), HttpStatus.OK);
	}
}
