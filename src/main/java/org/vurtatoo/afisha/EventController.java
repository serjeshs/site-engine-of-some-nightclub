package org.vurtatoo.afisha;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vurtatoo.afisha.domain.Event;

@Controller
public class EventController extends AbstractController {
		
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String get(Model model, Principal principal) {
		model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
		setRequiedName(model, principal,"Main Page");
	    return "events";
    }
	
	
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
    public @ResponseBody List<Event> getJson() {
	    return eventDao.getEventsAfter(LocalDateTime.now());
    }
	
	@RequestMapping(value = "event/add", method = RequestMethod.POST)
	public String addEventAction(Model model, String name, Principal principal, String description, String startEvent, String endEvent, int cost, String costText, int Place_id, int id) {
		LocalDateTime startEventTime = LocalDateTime.parse(startEvent);
		LocalDateTime endEventTime = LocalDateTime.parse(endEvent);
		model.addAttribute("event",eventDao.addEvent(id,name, description, startEventTime, endEventTime, cost, costText, Place_id));
		setRequiedName(model, principal, name);
		return "event";
	}
	
	@RequestMapping(value = "event/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getEvent(Model model , Principal principal, @PathVariable(value = "id") int id) {
		Event event = eventDao.getEvent(id);
		model.addAttribute("event", event);
		setRequiedName(model, principal, event.getName());
	    return "event";
    }
	
	@RequestMapping(value = "event", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getEventFull(Model model , Principal principal, int id) {
		Event event = eventDao.getEvent(id);
		model.addAttribute("event", event);
		setRequiedName(model, principal, event.getName());
	    return "event";
    }
	
	@RequestMapping(value = "event/add", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String addEvent(Model model, Principal principal) {
		model.addAttribute("places",placeDAO.getPlaces());
		//model.addAttribute("regions",regionDAO.getRegions());
		model.addAttribute("eventid",0);
		setRequiedName(model, principal, "Добавление нового события");
		return "eventedit";
	}
	
	
	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getevents(Model model, Principal principal) {
		model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
		setRequiedName(model, principal,"Events");
	    return "events";
    }

}
