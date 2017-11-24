package by.havefun.dao;

import by.havefun.entity.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static by.havefun.GlobalSettings.formatter;

@Service
@Transactional
public class EventDAO extends BaseDAO {

    @Autowired
    AppUserDAO appUserDAO;

    Logger logger = LoggerFactory.getLogger(getClass());

    public Event addEvent(int eventId, String name, String description, LocalDateTime startEvent, LocalDateTime endEvent, int cost, String costText, int Place_id, String imageUri, String emailOwner) {
        if (!canEdit(emailOwner, eventId)) {
            return null;
        }
        Event event = (eventId == 0) ? new Event() : getEvent(eventId);

//        if (eventId == 0) {
//            event = new Event();
//        } else {
//            event = getEvent(eventId);
//        }

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
        event.setRegion_Name(getEntity(Region.class, Region_id).getRegionName());
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
                return (appUser.getId() == event.getAppUser().getId());
            }

            case AppUser.USER: {
                return (appUser.getId() == event.getAppUser().getId());
            }

            default:
                return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Event> getEventsAfter(LocalDateTime dateTime, String email) {


        Disjunction statusVariants = Restrictions.disjunction();
        statusVariants.add(Restrictions.eq(AppUserLikeEvent.COL_STATUS, AppUserLikeEvent.BETHERE));
        statusVariants.add(Restrictions.eq(AppUserLikeEvent.COL_STATUS, AppUserLikeEvent.MAYATTEND));

        Conjunction appUserLikeCriterion = Restrictions.conjunction();
        appUserLikeCriterion.add(statusVariants);

        //TODO Заджойнить аппюзер
        AppUser appUser = appUserDAO.getAppUserFromEmail(email);
        appUserLikeCriterion.add(Restrictions.eq(AppUserLikeEvent.COL_APPUSER_ID, appUser.getId()));


        Criteria criteriaEvents = getCriteria(Event.class);
        criteriaEvents.createAlias("appuserLikeEvents", "AppUserLikeEvent", JoinType.INNER_JOIN, appUserLikeCriterion);
        if (dateTime != null) {
            criteriaEvents.add(Restrictions.sqlRestriction(Event.COL_END_EVENT + " > '" + formatter.format(dateTime) + "'"));
        }
        return criteriaEvents.list();
    }


    public List<Event> getEventsAfter(LocalDateTime localDateTime) {
        return getEventsAfter(localDateTime, null, null);
    }

    public List<Event> getEventsAfter(String dateTime, Integer regionId, Integer placeId) {
        LocalDateTime eventTime = convert(dateTime);
        return getEventsAfter(eventTime, regionId, placeId);
    }

    public List<Event> getEventsAfter(LocalDateTime startSearchEvent, Integer regionId, Integer placeId) {
        return getEventsBetween(startSearchEvent, null, null, regionId, placeId);
    }

    public List<Event> getEventsBetween(String startSearchEventString, String endSearchEventString, Boolean free, Integer regionId, Integer placeId) {
        LocalDateTime startSearchEvent = convert(startSearchEventString);
        LocalDateTime endSearchEvent = convert(endSearchEventString);
        return getEventsBetween(startSearchEvent, endSearchEvent, free, regionId, placeId);
    }

    private List<Event> getEventsBetween(LocalDateTime startSearchEvent, LocalDateTime endSearchEvent, Boolean free, Integer regionId, Integer placeId) {
        Criteria criteria = getCriteria(Event.class);
        if (startSearchEvent != null) {
            criteria.add(Restrictions.sqlRestriction(Event.COL_START_EVENT + " > '" + formatter.format(startSearchEvent) + "'"));
        }

        if (endSearchEvent != null) {
            criteria.add(Restrictions.sqlRestriction(Event.COL_END_EVENT + " < '" + formatter.format(endSearchEvent) + "'"));
        }
        if (free != null) {
            if (free) {
                criteria.add(Restrictions.eq(Event.COL_COST, 0));
            }
        }

        if (regionId != null) {
            criteria.add(Restrictions.eq(Event.COL_REGION_ID, regionId));
        }
        if (placeId != null) {
            criteria.add(Restrictions.eq(Event.COL_PLACE_ID, placeId));
        }
        criteria.addOrder(Order.asc(Event.COL_START_EVENT));
        return criteria.list();
    }
}
