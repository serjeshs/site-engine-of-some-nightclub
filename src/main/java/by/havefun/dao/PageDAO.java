package by.havefun.dao;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.havefun.entity.AppUser;
import by.havefun.entity.Page;

@Service
@Transactional
public class PageDAO extends BaseDAO {
    
    @Autowired
    AppUserDAO appUserDAO;
    
    public Page get(String placeName) {
        return getOneValue(getListEntity(Page.class, Page.COL_URI,placeName));
    }

    public Page update(int id, String title, String text, String uriName, String userEmail) {
        AppUser user = appUserDAO.getAppUserFromEmail(userEmail);
        Page page;
        if (id < 1) {
            page = new Page();
        } else {
            page = getEntity(Page.class, id);
        }
        page.setAppUser(user);
        page.setModifed(LocalDateTime.now());
        page.setTitle(title);
        page.setText(text);
        page.setUriName(uriName);
        saveOrUpdate(page);
        return page;
    }
    
}
