package by.havefun.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import by.havefun.GlobalSettings;
import by.havefun.entity.Event;

@Controller
public class EventController extends AbstractController {
	
	/***************************GETTERS***************************************/
	
	/**
     * List of upcoming events
     * 
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "events", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getevents(Model model, Principal principal, String dateTime, Integer regionId, Integer placeId) {
        List<Event> events = eventDao.getEventsAfter(dateTime, regionId, placeId);
        model.addAttribute("events", events);
        setRequiedName(model, principal, "Все ближайшие события");
        return "events";
    }
    
	/***********************END*GETTERS***************************************/
        
    
    /**
     * Add (id = 0) or edit (id !=0) action event.
     * 
     * @param model
     * @param name
     * @param principal
     * @param description
     * @param startEvent
     * @param endEvent
     * @param cost
     * @param costText
     * @param Place_id
     * @param id
     * @param imageUri
     * @return
     */
    @RequestMapping(value = "event/edit", method = RequestMethod.POST)
    public String editEventAction(Model model, String name, Principal principal, String description, String startEvent, String endEvent, int cost, String costText, int Place_id, int id,
            HttpServletRequest request, MultipartFile file) {
        if ((principal != null) && (eventDao.canEdit(principal.getName(), id))) {
            LocalDateTime startEventTime = null;
            LocalDateTime endEventTime = null;
            try {
                startEventTime = LocalDateTime.parse(startEvent,GlobalSettings.formatter);
                endEventTime = LocalDateTime.parse(endEvent,GlobalSettings.formatter);
            } catch (Exception ex) {
                setRequiedName(model, principal, "Ошибка введённого время.");
                model.addAttribute("result", "Ошибка введённого времени начала или конца мероприятия. Вернитесь назад и проверьте параметры.");
                return "result";
            }
            
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("photo");
            String imageUri = null;
            if (!multipartFile.isEmpty()) {
                imageUri = localFile.fileAdd(multipartFile, principal.getName());
            }
            Event event = eventDao.addEvent(id, name, description, startEventTime, endEventTime, cost, costText, Place_id, imageUri, principal.getName());
			model.addAttribute("event", event );
            setRequiedName(model, principal, name);
            model.addAttribute("canEdit", true);
            return simpleRedirect("event/" + event.getId(),model);
        } else {
            setRequiedName(model, principal, "Доступ запрещён");
            model.addAttribute("result", "Вам не разрешено выполнять данное действие");
            return "result";
        }
    }

    /**
     * Get Event by Id
     * 
     * @param model
     * @param principal
     * @param id
     * @return
     */
    @RequestMapping(value = "event/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getEvent(Model model, Principal principal, @PathVariable(value = "id") int id) {
        Event event = eventDao.getEvent(id);
        model.addAttribute("event", event);
        setRequiedName(model, principal, event.getName());
        if (principal == null) {
            model.addAttribute("canEdit", false);
            model.addAttribute("likeStatus", 0);
        } else {
            model.addAttribute("canEdit", eventDao.canEdit(principal.getName(), id));
            model.addAttribute("likeStatus", appUserLikeEventDAO.getStatus(principal.getName(), id));
        }

        return "event";
    }

    /**
     * Add event page
     * 
     * @param model
     * @param principal
     * @return
     */
    @Transactional
    @RequestMapping(value = "event/add", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String addEvent(Model model, Principal principal) {
        if (principal != null) {
        	model.addAttribute("eventid", 0);
            setRequiedName(model, principal, "Добавление нового события");
            return "eventedit";
        } else {
            setRequiedName(model, principal, "Доступ запрещён");
            model.addAttribute("result", "Вам не разрешено выполнять данное действие");
            return "result";
        }
    }
    
    @Transactional
    @RequestMapping(value = "my/events", method = RequestMethod.GET)
    public String getMyEvents(Model model, Principal principal) {
        List<Event> events = eventDao.getEventsAfter(LocalDateTime.now(), principal.getName());
        for (Event event : events) {
            logger.info(event.toString());
        }
        model.addAttribute("events", events);
        setRequiedName(model, principal, "Мои ближайшие события");
        return "events";
    }

    /**
     * Edit view page
     * 
     * @param model
     * @param principal
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping(value = "event/edit/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getEditEvent(Model model, Principal principal, @PathVariable(value = "id") int id) {
        if ((principal != null) && (eventDao.canEdit(principal.getName(), id))) {
            Event event = eventDao.getEvent(id);
            model.addAttribute("event", event);
            model.addAttribute("eventid", event.getId());
            setRequiedName(model, principal, event.getName());
            return "eventedit";
        } else {
            setRequiedName(model, principal, "Доступ запрещён");
            model.addAttribute("result", "Вам не разрешено выполнять данное действие");
            return "result";
        }
    }

    @RequestMapping(value = "like/{like}/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String like(Model model, Principal principal, @PathVariable(value = "id") int eventId, @PathVariable(value = "like") String like) {
        int likeID = appUserLikeEventDAO.getStatus(like);
        if (likeID == 0) {
            appUserLikeEventDAO.delLike(principal.getName(), eventId);
        } else {
            appUserLikeEventDAO.addLike(principal.getName(), eventId, likeID);
        }
        return simpleRedirect("event/" + eventId,model);
    }
    
    
    @RequestMapping(value = "event/list", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String geteventList(Model model, Principal principal) {
        model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
        setRequiedName(model, principal, "Все ближайшие события");
        return "eventList";
    }
    
    
    /**********************************
     * JSON API
     **********************************/
    

    @RequestMapping(value = "/json")
    public @ResponseBody List<Event>  getEventsJson(Model model, Principal principal, String dateTime, Integer regionId, Integer placeId) {
        return eventDao.getEventsAfter(dateTime, regionId, placeId);
    }
    
    @RequestMapping(value = "event/list/json")
    public @ResponseBody List<Event> geteventListJSON(Principal principal, String date, Integer regionId, Integer placeId) {
        LocalDateTime dateTime = LocalDateTime.parse(date, GlobalSettings.formatter);
           return eventDao.getEventsAfter(dateTime,regionId,placeId);
    }
    
    
}
