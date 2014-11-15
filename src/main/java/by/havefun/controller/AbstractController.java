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
import by.havefun.utils.file.LocalFile;
import by.havefun.utils.view.MenuItems;

public abstract class AbstractController {
	
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
		try {
			by.havefun.entity.AppUser appUser = appUserDao.getAppUserFromEmail(principal.getName());
			model.addAttribute("user", true);
			model.addAttribute("guest", false);
			model.addAttribute("appUserName", appUser.getNick());
		} catch (NullPointerException exception) {
			model.addAttribute("guest", true);
			model.addAttribute("user", false);
		}
		
		List<MenuItems> menuItems = new LinkedList<MenuItems>();
		menuItems.add(new MenuItems("", "Главная"));
		menuItems.add(new MenuItems("events", "Все предстоящие события"));
//		if (principal != null) {
//			menuItems.add(new MenuItems("my/events", "Интересующие меня события"));
//		}
		menuItems.add(new MenuItems("contacts", "Контакты"));
		model.addAttribute("menuItems",menuItems);
		
		model.addAttribute("timeNow", LocalDateTime.now().toString());
		model.addAttribute("title", title);
		model.addAttribute("appName", "afisha");
	}
}
