package by.havefun.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.entity.AppUser;
import by.havefun.entity.Event;
import by.havefun.entity.Place;
import by.havefun.entity.Region;
@Service
@Transactional
public class EventDAO extends BaseDAO {

	@Autowired
	AppUserDAO appUserDAO;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public List<Event> getEventsAfter(LocalDateTime localDateTime) {
		java.time.format.DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Criteria criteria = getCriteria(Event.class);
		criteria.add(Restrictions.sqlRestriction(" endEvent > '" + dateTimeFormatter.format(localDateTime) + "'"));
		criteria.addOrder(Order.asc("endEvent"));
	    return ((List<Event>) criteria.list());
    }

	public Event addEvent(int eventId, String name, String description, LocalDateTime startEvent, LocalDateTime endEvent, int cost, String costText, int Place_id, String imageUri,String emailOwner) {
		if (!canEdit(emailOwner, eventId)) {
			return null;
		}
		Event event;
		if (eventId == 0 ) {
			 event = new Event();
		} else {
			event = getEvent(eventId);
		}
	    event.setAppUser(appUserDAO.getAppUserFromEmail(emailOwner));
	    event.setCost(cost);
	    event.setCostText(costText);
	    event.setDecription(description);
	    event.setStartEvent(startEvent);
	    event.setEndEvent(endEvent);
	    event.setName(name);
	    event.setPlace(Place_id);
	    if (imageUri != null) {
	        event.setImageUri(imageUri);
	    } else if (event.getImageUri() == null) {
	        event.setImageUri("images/Imageevent.jpg");
	    }
	    
	    Place place = getEntity(Place.class, Place_id);
	    event.setPlace_Name(place.getName());
	    int Region_id = place.getRegion();
		event.setRegion(Region_id);
	    event.setRegion_Name(getEntity(Region.class,Region_id).getRegionName());
	    saveOrUpdate(event);
	    return event;
    }

	public Event getEvent(int id) {
	    return getEntity(Event.class, id);
    }
	
	public boolean canEdit(String email, int eventId) {
		AppUser appUser = appUserDAO.getAppUserFromEmail(email);
		if ((appUser != null) && (eventId == 0)) 
			return true;
		Event event = getEntity(Event.class, eventId);
		switch (appUser.getRole()) {
		case AppUser.ADMIN:			
			return true;
		case AppUser.MANAGER: {
			//TODO Сделать нормальную проверку, а не этот кал
			List<Place> places = appUser.getPlaces();
			for (Place place : places) {
	            if (place.getId() == event.getPlace()) {
	            	return true;
	            }
            }
		}

		case AppUser.USER: {
			if (appUser.getId() == event.getAppUser().getId()) {
				return true;
			} else {
				return false;
			}
		}

		default:
			return false;
		}
	}

}
