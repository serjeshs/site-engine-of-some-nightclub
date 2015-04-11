package by.havefun.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import by.havefun.dao.AppUserDAO;
import by.havefun.dao.AppUserLikeEventDAO;
import by.havefun.dao.EventDAO;
import by.havefun.dao.PlaceDAO;
import by.havefun.dao.RegionDAO;
import by.havefun.service.lang.LanguageService;
import by.havefun.utils.file.LocalFile;
import by.havefun.utils.view.MenuItems;

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
	

	protected void setRequiedName(Model model, Principal principal, String title) {
	    if (principal != null) {
	        by.havefun.entity.AppUser appUser = appUserDao.getAppUserFromEmail(principal.getName());
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
		menuItems.add(new MenuItems("", "Главная"));
		//TODO Переделать дизайн, когда 4 элемента всё валится.
		//menuItems.add(new MenuItems("events", "Все предстоящие события"));
		if (principal != null) {
			menuItems.add(new MenuItems("my/events", "Интересующие меня события"));
		}
		menuItems.add(new MenuItems("contacts", "Контакты"));
		model.addAttribute("menuItems",menuItems);
		
		model.addAttribute("timeNow", LocalDateTime.now().toString());
		model.addAttribute("title", title);
		model.addAttribute("appName", APP_NAME);
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
