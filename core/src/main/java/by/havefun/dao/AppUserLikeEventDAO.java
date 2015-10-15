package by.havefun.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.entity.AppUser;
import by.havefun.entity.AppUserLikeEvent;


@Service
@Transactional
public class AppUserLikeEventDAO extends BaseDAO{
	
	@Autowired
	AppUserDAO appUserDAO;
	
	@SuppressWarnings("unchecked")
    public int getStatus(int appUserId, int EventId) {
		SQLQuery query = createSQLQuery("select STATUS from appuser_like_event where appuser_id = " + appUserId + " and event_id = " + EventId);
		List<Object> l = query.list();
		if (l.size() == 0) {
			return AppUserLikeEvent.NOTHING;
		}
		
		return (int) l.get(0);
	}

	public int getStatus(String email, int id) {
	    return getStatus(appUserDAO.getAppUserFromEmail(email).getId(), id);
	    
    }

	public int getStatus(String like) {
	    switch (like) {
		case "NOTHING":
			return AppUserLikeEvent.NOTHING;
		case "BETHERE":
			return AppUserLikeEvent.BETHERE;
		case "MAYATTEND":
			return AppUserLikeEvent.MAYATTEND;
		case "DIZLIKE":
			return AppUserLikeEvent.DIZLIKE;
		}
	    return -1;
    }

	public void delLike(String email, int eventId) {
	    AppUser aU = appUserDAO.getAppUserFromEmail(email);
	    SQLQuery query = createSQLQuery("DELETE FROM `appuser_like_event` WHERE `appuser_id`='" + aU.getId() + "' and`event_id`='" + eventId + "'");
		System.out.println(query.executeUpdate());

	    
    }

	public void addLike(String email, int eventId, int likeID) {
		AppUser aU = appUserDAO.getAppUserFromEmail(email);
		try {
			SQLQuery query = createSQLQuery("INSERT INTO `appuser_like_event` (`appuser_id`, `event_id`, `STATUS`) VALUES ('" + aU.getId() + "', '" + eventId + "', '" + likeID + "')");
			System.out.println(query.executeUpdate());
		} catch (ConstraintViolationException ex) {
			delLike(email, eventId);
			addLike(email, eventId, likeID);
		}
    }

}
