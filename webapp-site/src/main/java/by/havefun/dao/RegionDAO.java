package by.havefun.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.entity.Region;
@Service
@Transactional
public class RegionDAO extends BaseDAO{
	
	public List<Region> getRegions() {
	    return getListEntity(Region.class);
    }
}
