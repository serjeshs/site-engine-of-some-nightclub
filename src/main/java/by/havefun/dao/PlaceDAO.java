package by.havefun.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.domain.Place;
@Service
@Transactional
public class PlaceDAO extends BaseDAO{


	public List<Place> getPlaces() {
	    return getListEntity(Place.class);
    }

}
