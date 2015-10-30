package by.havefun.api.controller.controller;

import by.havefun.dao.*;
import by.havefun.entity.AppUser;
import by.havefun.service.lang.LanguageService;
import by.havefun.utils.file.LocalFile;
import by.havefun.utils.view.MenuItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractController {

	private String APP_NAME = "afisha";
	
	@Autowired
	EventDAO eventDao;
	
	@Autowired
	PlaceDAO placeDAO;
	
	@Autowired
	RegionDAO regionDAO;
	
	@Autowired
	AppUserDAO appUserDao;
	
	@Autowired
	AppUserLikeEventDAO appUserLikeEventDAO;
	
	@Autowired
	LocalFile localFile;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	protected AppUser setRequiedName(Model model, Principal principal, String title) {
		AppUser appUser = null;
	    if (principal != null) {
	        appUser =  appUserDao.getAppUserFromEmail(principal.getName());
            model.addAttribute("user", true);
            model.addAttribute("guest", false);
            model.addAttribute("appUserName", appUser.getNick());
            model.addAttribute("lang_template", LanguageService.getLangMap(appUser.getLang()));
	    }  else {
	        model.addAttribute("guest", true);
            model.addAttribute("user", false);
            model.addAttribute("lang_template", LanguageService.getLangMap(LanguageService.LANG_BEL));
	    }
		
		List<MenuItems> menuItems = new LinkedList<MenuItems>();
		menuItems.add(new MenuItems("events", "Все события"));
		menuItems.add(new MenuItems("blog", "Блог"));
		//TODO Переделать дизайн, когда 4 элемента всё валится.
		//menuItems.add(new MenuItems("events", "Все предстоящие события"));
		if (principal != null) {
			menuItems.add(new MenuItems("my/events", "Мои события"));
		}
		menuItems.add(new MenuItems("contacts", "Контакты"));
		model.addAttribute("menuItems", menuItems);
		
		model.addAttribute("timeNow", LocalDateTime.now().toString());
		model.addAttribute("title", title);
		model.addAttribute("appName", APP_NAME);
		return appUser;
	}
	
	protected String get404(Model model,Principal principal) {
        setRequiedName(model, principal, "404");
        return "404";
    }

//
//	protected String simpleRedirect(String url ) {
//		return "<!DOCTYPE HTML>"
//				+ "<html>"
//				+ "<head>"
//				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
//				+ "<META HTTP-EQUIV=\"REFRESH\" CONTENT=\"0;URL=/" + APP_NAME + "/" + url +"\">"
//				+ "<title></title>"
//				+ "</head>"
//				+ "<body></body></html>";
//	}

	protected String simpleRedirect(String url, Model model ) {
		model.addAttribute("regirect",url);
		return "simpleredirect";
	}



}
