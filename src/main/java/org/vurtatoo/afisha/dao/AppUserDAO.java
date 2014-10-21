package org.vurtatoo.afisha.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vurtatoo.afisha.domain.AppUser;
import org.vurtatoo.afisha.domain.Region;
import org.vurtatoo.afisha.exception.RegistrationException;

import by.q64.promo.utils.mail.EmailManager;

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

	public String registerUser(String email, String nick, String password) throws RegistrationException {
		
		AppUser appUser = null;
		if (baseDAO.getEntitys(AppUser.class, Restrictions.eq(AppUser.COL_EMAIL, email)).size() > 0) {
			throw new RegistrationException("Данный емеил уже имеет аккуант.");
		}
		
		if (baseDAO.getEntitys(AppUser.class, Restrictions.eq(AppUser.COL_NICK, nick)).size() > 0) {
			throw new RegistrationException("Данный никнейм уже используется.");
		}
		
		LocalDateTime birthday = LocalDateTime.now();
		String photoURI = "https://pp.vk.me/c11265/u6704769/-6/w_b9000659.jpg";
		String fathername = "Анонимович";
		String firstname = "Анонимий";
		String surname = "Анонимов";
		String region_Name = "Не определено";
		int vkId = 0;
		String vkTocken = RandomStringUtils.randomAlphanumeric(new Random().nextInt(15)+10);
		Region region = new Region().setId(1);
		appUser = new AppUser(birthday, email, fathername, firstname, nick, password, photoURI, region_Name, AppUser.NOCONFIRM, surname, vkId, vkTocken, region);
		baseDAO.saveOrUpdate(appUser);
		
		EmailManager.send(email, "AFISHA |", 
				"Спасибо, что зарегистрировались на нашем сайте, подтвердите емеил перейдя по ссылке. "
				+ "http://ladyka.tk/afisha/confirm?userId=" + appUser.getId() + "&tocken=" + vkTocken);
		return "Пользователь зарегистрирован, активируйте ваш аккуант, ссылка пришла Вам в почтовый ящик";
	}

	public String confirmEmail(int userId, String tocken) {
		AppUser appUser = baseDAO.getEntity(AppUser.class, userId);
		if ((appUser.getRole().contains(AppUser.NOCONFIRM)) && (tocken.contains(appUser.getVkTocken()))) {
			appUser.setRole(AppUser.USER);
			baseDAO.saveOrUpdate(appUser);
			return "Емеил подтверждён";
		} 
	    return "Ссылка устарела";
    }
}
