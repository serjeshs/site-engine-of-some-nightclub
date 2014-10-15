package org.vurtatoo.afisha;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.vurtatoo.afisha.dao.AppUserDAO;
import org.vurtatoo.afisha.dao.EventDAO;
import org.vurtatoo.afisha.dao.PlaceDAO;
import org.vurtatoo.afisha.dao.RegionDAO;

public abstract class AbstractController {
	
	@Autowired
	EventDAO eventDao;
	
	@Autowired
	PlaceDAO placeDAO;
	
	@Autowired
	RegionDAO regionDAO;
	
	@Autowired
	AppUserDAO appUserDao;
	

	protected void setRequiedName(Model model, Principal principal, String title) {
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
		menuItems.add(new MenuItems("", "Главная"));
		menuItems.add(new MenuItems("events", "Предстоящие события"));
		menuItems.add(new MenuItems("contacts", "Контакты"));
		//menuItems.add(new MenuItems("works.html", "works"));
		//menuItems.add(new MenuItems("clients.html", "clients"));
		//menuItems.add(new MenuItems("blog.html", "blog"));
		model.addAttribute("menuItems",menuItems);
		
		model.addAttribute("timeNow", LocalDateTime.now().toString());
		model.addAttribute("title", title);
		model.addAttribute("appName", "afisha");
	}
}
