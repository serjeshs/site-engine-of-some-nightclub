package by.havefun.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.havefun.dao.PageDAO;
import by.havefun.entity.AppUser;
import by.havefun.entity.Event;
import by.havefun.entity.Page;

@Controller
@RequestMapping(value = "p")
public class PagesController extends AbstractController {

    @Autowired
    PageDAO pageDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getMain(Model model, Principal principal) {
        Page page = pageDAO.get("main");

        if (page == null) {
            return "404";
        }
        setRequiedName(model, principal, page.getTitle());
        model.addAttribute("page", page);
        
        List<Event> events = eventDao.getEventsAfter("", null, null);
        model.addAttribute("events", events);
        model.addAttribute("haveEvents", true);
        return "page";
    }
    
    @RequestMapping(value = "{pageURL}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String get(Model model, Principal principal, @PathVariable(value = "pageURL") String pageURL) {
        Page page = pageDAO.get(pageURL);

        if (page == null) {
            return get404(model, principal);
        }
        setRequiedName(model, principal, page.getTitle());
        model.addAttribute("page", page);
        return "page";
    }
    
    @RequestMapping(value = "{pageURL}/edit", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String editPage(Model model, Principal principal, @PathVariable(value = "pageURL") String pageURL) {
        Page page = pageDAO.get(pageURL);

        if (page == null) {
            switch (appUserDao.getAppUserFromEmail(principal.getName()).getRole()) {
            case AppUser.ADMIN:
            case AppUser.MANAGER:
                page = new Page();
                page.setId(0);
                page.setUriName(pageURL);
                break;
            default:
                return get404(model, principal);
            }
        }
        setRequiedName(model, principal, page.getTitle());
        model.addAttribute("page", page);
        return "pageEdit";
    }
    
    @RequestMapping(value = "{pageURL}/edit", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String edit(Model model, Principal principal, @PathVariable(value = "pageURL") String pageURL,
            int id,
            String text,
            String title,
            String uriName) {
        if (principal == null) {
            return get404(model, principal);
        } 
        Page page = pageDAO.update(id,title,text,uriName,principal.getName());

        if (page == null) {
            return get404(model, principal);
        }
        setRequiedName(model, principal, page.getTitle());
        model.addAttribute("page", page);
        
        return "page";
    }
}
