package by.havefun.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.domain.AppUser;
import by.havefun.domain.Event;
import by.havefun.domain.Place;
import by.havefun.domain.Region;
@Service
@Transactional
public class EventDAO extends BaseDAO {

	public List<Event> getEventsAfter(LocalDateTime localDateTime) {
		java.time.format.DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Criteria criteria = getCriteria(Event.class);
		criteria.add(Restrictions.sqlRestriction(" endEvent > '" + dateTimeFormatter.format(localDateTime) + "'"));
		criteria.addOrder(Order.asc("endEvent"));
	    return (List<Event>) criteria.list();
    }

	public Event addEvent(int id, String name, String description, LocalDateTime startEvent, LocalDateTime endEvent, int cost, String costText, int Place_id, String imageUri) {
		Event event;
		if (id == 0 ) {
			 event = new Event();
		} else {
			event = getEvent(id);
		}
	    event.setAppUser(new AppUser().setId(1));
	    event.setCost(cost);
	    event.setCostText(costText);
	    event.setDecription(description);
	    event.setStartEvent(startEvent);
	    event.setEndEvent(endEvent);
	    event.setName(name);
	    event.setPlace(Place_id);
	    event.setImageUri(imageUri);
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
}
