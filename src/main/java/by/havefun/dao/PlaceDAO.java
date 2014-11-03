package by.havefun.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.entity.AppUser;
import by.havefun.entity.Place;

@Service
@Transactional
public class PlaceDAO extends BaseDAO{

	@Autowired
	AppUserDAO appUserDAO;

	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 * @param email
	 * @return
	 */
	public List<Place> getPlaces(String email) {
		AppUser user = appUserDAO.getAppUserFromEmail(email);
		switch (user.getRole()) {
		case AppUser.ADMIN:
			return getListEntity(Place.class);
		case AppUser.MANAGER: {
			//TODO LAZY Exception fix  please
			List<Place> returnValue = user.getPlaces();
			for (Place place : returnValue) {
	            logger.info(place.toString());
            }
			return returnValue;
		}
		default:
			logger.warn("DEFAULT GET PLASES. FIX IT !!!!");
			return getListEntity(Place.class);
		}
	    
    }

}
