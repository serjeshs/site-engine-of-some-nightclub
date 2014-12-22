package by.havefun.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.havefun.entity.Event;

@Controller
@RequestMapping(value = "p")
public class PagesController extends AbstractController {

    @RequestMapping(value = "main", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String get(Model model, Principal principal, String dateTime, Integer regionId, Integer placeId) {
        List<Event> events = eventDao.getEventsAfter(dateTime, regionId, placeId);
        model.addAttribute("events", events);
        setRequiedName(model, principal, "Главная страница");
        return "events";
    }
}
