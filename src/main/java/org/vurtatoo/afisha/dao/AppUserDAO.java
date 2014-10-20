package org.vurtatoo.afisha.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vurtatoo.afisha.domain.AppUser;
import org.vurtatoo.afisha.domain.Region;

@Service
@Transactional
public class AppUserDAO {

	@Autowired
	BaseDAO baseDAO;
	
	public List<AppUser> getList() {
	    return baseDAO.getListEntity(AppUser.class);
    }

	public AppUser getAppUserFromEmail(String email) {
		try {
			Criteria criteria = baseDAO.getCriteria(AppUser.class);
			criteria.add(Restrictions.like("email",email));
			return (AppUser) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AppUser();
		}
	}

	public AppUser registerUser(String email, String nick, String surname,String password) {
		
		LocalDateTime birthday = LocalDateTime.now();
		String photoURI = "https://pp.vk.me/c11265/u6704769/-6/w_b9000659.jpg";
		String fathername = "Анонимов";
		String firstname = "Анонимий";
		String region_Name = "Не определено";
		int vkId = 0;
		String vkTocken = "NULL";
		Region region = new Region().setId(1);
		AppUser appUser = new AppUser(birthday, email, fathername, firstname, nick, password, photoURI, region_Name, AppUser.NOCONFIRM, surname, vkId, vkTocken, region);
		baseDAO.saveOrUpdate(appUser);
		
		
		
		
		return appUser;
	}
}
