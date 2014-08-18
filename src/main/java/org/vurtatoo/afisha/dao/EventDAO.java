package org.vurtatoo.afisha.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vurtatoo.afisha.domain.AppUser;
import org.vurtatoo.afisha.domain.Event;
import org.vurtatoo.afisha.domain.Place;
import org.vurtatoo.afisha.domain.Region;

@Service
@Transactional
public class EventDAO {

	@Autowired
	BaseDAO baseDAO;

	@SuppressWarnings("unchecked")
    public List<Event> getEventsAfter(LocalDateTime localDateTime) {
		java.time.format.DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Criteria criteria = baseDAO.getCriteria(Event.class);
		criteria.add(Restrictions.sqlRestriction(" endEvent > '" + dateTimeFormatter.format(localDateTime) + "'"));
	    return (List<Event>) criteria.list();
    }

	public Event addEvent(int id, String name, String description, LocalDateTime startEvent, LocalDateTime endEvent, int cost, String costText, int Place_id) {
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
	    Place place = baseDAO.getEntity(Place.class, Place_id);
	    event.setPlace_Name(place.getName());
	    int Region_id = place.getRegion();
		event.setRegion(Region_id);
	    event.setRegion_Name(baseDAO.getEntity(Region.class,Region_id).getRegionName());
	    baseDAO.saveOrUpdate(event);
	    return event;
    }

	public Event getEvent(int id) {
	    return baseDAO.getEntity(Event.class, id);
    }
}
