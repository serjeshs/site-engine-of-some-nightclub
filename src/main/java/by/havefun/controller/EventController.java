package by.havefun.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import by.havefun.entity.Event;

@Controller
public class EventController extends AbstractController {

    /**
     * Main Page. List events.
     * 
     * @param model
     * @param principal
     * @return Main page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String get(Model model, Principal principal) {
        model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
        setRequiedName(model, principal, "Main Page");
        return "events";
    }
    
    //temp
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView getTestPage() {
		return new ModelAndView("test");
    }

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime startEventTime = LocalDateTime.parse(startEvent,formatter);
            LocalDateTime endEventTime = LocalDateTime.parse(endEvent,formatter);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("photo");
            String imageUri = null;
            if (!multipartFile.isEmpty()) {
                imageUri = FileController.getRelativePath(localFile.fileAdd(multipartFile, principal.getName()));;
            }
            model.addAttribute("event", eventDao.addEvent(id, name, description, startEventTime, endEventTime, cost, costText, Place_id, imageUri, principal.getName()));
            setRequiedName(model, principal, name);
            model.addAttribute("canEdit", true);
            return "event";
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

    /**
     * List of upcoming events
     * 
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/events", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getevents(Model model, Principal principal) {
        model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
        setRequiedName(model, principal, "Events");
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
        return "redirect:/event/" + eventId;
    }
}
