package org.vurtatoo.afisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vurtatoo.afisha.domain.Place;

@Service
@Transactional
public class PlaceDAO {

	@Autowired
	BaseDAO baseDAO;

	public List<Place> getPlaces() {
	    return baseDAO.getListEntity(Place.class);
    }

}
