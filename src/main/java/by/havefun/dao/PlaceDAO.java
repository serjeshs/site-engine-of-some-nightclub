package by.havefun.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
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
	
	@SuppressWarnings("unchecked")
	public List<Place> getPlaces(String email, Integer regionId,String query) {
		String role = AppUser.GUEST;
		AppUser user = null;
		if (email != null) {
			user = appUserDAO.getAppUserFromEmail(email);
			role =  user.getRole();
		}
		
		switch (role) {
		case AppUser.MANAGER: {
			//TODO LAZY Exception fix  please
			List<Place> returnValue = user.getPlaces();
			for (Place place : returnValue) {
	            logger.info(place.toString());
            }
			return returnValue;
		}
		case AppUser.ADMIN:
		default:
			logger.warn("DEFAULT GET PLASES. FIX IT !!!!");
			return getPlaceSearchCriteria(regionId, query).list();
		} 
	}
	
	private Criteria getPlaceSearchCriteria(Integer regionId, String query) {
		Criteria criteria = getCriteria(Place.class);
		if (regionId != null) {
			criteria.add(Restrictions.eq(Place.COL_REGION_ID, regionId));
		}
		
		if (query != null) {
			Disjunction disjunction = Restrictions.disjunction();
			query = "%" + query + "%";
			disjunction.add(Restrictions.like(Place.COL_ADDRESS, query));
			disjunction.add(Restrictions.like(Place.COL_CONTACT, query));
			disjunction.add(Restrictions.like(Place.COL_DESCRIPTION, query));
			disjunction.add(Restrictions.like(Place.COL_NAME, query));
			disjunction.add(Restrictions.like(Place.COL_REGION_NAME, query));
			
			criteria.add(disjunction);
		}
		criteria.setMaxResults(10);
		return criteria;
	}

}
