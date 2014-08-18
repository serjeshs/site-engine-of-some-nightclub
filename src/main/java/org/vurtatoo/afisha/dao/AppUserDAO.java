package org.vurtatoo.afisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vurtatoo.afisha.domain.AppUser;

@Service
@Transactional
public class AppUserDAO {

	@Autowired
	BaseDAO baseDAO;
	
	public List<AppUser> getList() {
	    return baseDAO.getListEntity(AppUser.class);
    }
}
