package org.vurtatoo.afisha;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vurtatoo.afisha.dao.AppUserDAO;
import org.vurtatoo.afisha.dao.EventDAO;
import org.vurtatoo.afisha.dao.PlaceDAO;
import org.vurtatoo.afisha.dao.RegionDAO;
import org.vurtatoo.afisha.domain.Event;

@Controller
public class EventController {

	@Autowired
	EventDAO eventDao;
	
	@Autowired
	PlaceDAO placeDAO;
	
	@Autowired
	RegionDAO regionDAO;
	
	@Autowired
	AppUserDAO appUserDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String get(Model model, Principal principal) {
		model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
		setUserName(model, principal);
	    return "index";
    }
	
	
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
    public @ResponseBody List<Event> getJson() {
	    return eventDao.getEventsAfter(LocalDateTime.now());
    }
	
	@RequestMapping(value = "event/add", method = RequestMethod.POST)
	public String addEventAction(Model model, String name, String description, String startEvent, String endEvent, int cost, String costText, int Place_id, int id) {
		LocalDateTime startEventTime = LocalDateTime.parse(startEvent);
		LocalDateTime endEventTime = LocalDateTime.parse(endEvent);
		model.addAttribute("event",eventDao.addEvent(id,name, description, startEventTime, endEventTime, cost, costText, Place_id));
		return "event";
	}
	
	@RequestMapping(value = "event/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getEvent(Model model , @PathVariable(value = "id") int id) {
		Event event = eventDao.getEvent(id);
		model.addAttribute("event", event);
	    return "event";
    }
	
	@RequestMapping(value = "event/add", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String addEvent(Model model) {
		model.addAttribute("places",placeDAO.getPlaces());
		//model.addAttribute("regions",regionDAO.getRegions());
		model.addAttribute("eventid",0);
		return "eventedit";
	}
	
	
	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getevents(Model model, Principal principal) {
		model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
		setUserName(model, principal);
	    return "events";
    }
	
	private void setUserName(Model model, Principal principal) {
		try {
			org.vurtatoo.afisha.domain.AppUser appUser = appUserDao.getAppUserFromEmail(principal.getName());
			model.addAttribute("user", true);
			model.addAttribute("guest", false);
			model.addAttribute("appUserName", appUser.getNick());
		} catch (NullPointerException exception) {
			model.addAttribute("guest", true);
			model.addAttribute("user", false);
		}
		
		List<MenuItems> menuItems = new LinkedList<MenuItems>();
		menuItems.add(new MenuItems("events", "Предстоящие события"));
		
		menuItems.add(new MenuItems("about.html", "about"));
		//menuItems.add(new MenuItems("works.html", "works"));
		//menuItems.add(new MenuItems("clients.html", "clients"));
		//menuItems.add(new MenuItems("blog.html", "blog"));
		menuItems.add(new MenuItems("contacts.html", "contacts"));
		
		model.addAttribute("menuItems",menuItems);
	}
}
