package by.havefun.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.entity.AppUser;


@Service
@Transactional
public class AppUserLikeEventDAO extends BaseDAO{
	
	@Autowired
	AppUserDAO appUserDAO;
	
	public static final int NOTHING = 0;
	public static final int BETHERE = 1;
	public static final int MAYATTEND = 2;
	public static final int DIZLIKE = 3;
	
	@SuppressWarnings("unchecked")
    public int getStatus(int appUserId, int EventId) {
		SQLQuery query = createSQLQuery("select STATUS from appuser_like_event where appuser_id = " + appUserId + " and event_id = " + EventId);
		List<Object> l = query.list();
		if (l.size() == 0) {
			return NOTHING;
		}
		
		return (int) l.get(0);
	}

	public int getStatus(String email, int id) {
	    return getStatus(appUserDAO.getAppUserFromEmail(email).getId(), id);
	    
    }

	public int getStatus(String like) {
	    switch (like) {
		case "NOTHING":
			return NOTHING;
		case "BETHERE":
			return BETHERE;
		case "MAYATTEND":
			return MAYATTEND;
		case "DIZLIKE":
			return DIZLIKE;
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
