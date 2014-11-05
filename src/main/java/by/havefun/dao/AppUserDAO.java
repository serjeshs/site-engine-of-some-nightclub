package by.havefun.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.entity.AppUser;
import by.havefun.entity.Region;
import by.havefun.exception.RegistrationException;
import by.havefun.security.KeccakUtil;
import by.havefun.utils.email.EmailManager;

@Service
@Transactional
public class AppUserDAO extends BaseDAO {

    public List<AppUser> getList() {
        return getListEntity(AppUser.class);
    }

    public AppUser getAppUserFromEmail(String email) {
        try {
            Criteria criteria = getCriteria(AppUser.class);
            criteria.add(Restrictions.like("email", email));
            return (AppUser) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AppUser();
        }
    }

    public String registerUser(String email, String nick, String password) throws RegistrationException {

        AppUser appUser = null;
        if (getEntitys(AppUser.class, Restrictions.eq(AppUser.COL_EMAIL, email)).size() > 0) {
            throw new RegistrationException("Данный емеил уже имеет аккуант.");
        }

        if (getEntitys(AppUser.class, Restrictions.eq(AppUser.COL_NICK, nick)).size() > 0) {
            throw new RegistrationException("Данный никнейм уже используется.");
        }

        LocalDateTime birthday = LocalDateTime.now();
        String photoURI = "https://pp.vk.me/c11265/u6704769/-6/w_b9000659.jpg";
        String fathername = "Анонимович";
        String firstname = "Анонимий";
        String surname = "Анонимов";
        String region_Name = "Не определено";
        int vkId = 0;
        String vkTocken = RandomStringUtils.randomAlphanumeric(new Random().nextInt(15) + 10);
        Region region = new Region().setId(1);
        appUser = new AppUser(birthday, email, fathername, firstname, nick, password, photoURI, region_Name, AppUser.NOCONFIRM, surname, vkId, vkTocken, region);
        saveOrUpdate(appUser);

        EmailManager.send(email, "AFISHA |",
                "Спасибо, что зарегистрировались на нашем сайте, подтвердите емеил перейдя по ссылке. "
                        + "http://havefun.by/afisha/confirm?userId=" + appUser.getId() + "&tocken=" + vkTocken);
        return "Пользователь зарегистрирован, активируйте ваш аккуант, ссылка пришла Вам в почтовый ящик";
    }

    public String confirmEmail(int userId, String tocken) {
        AppUser appUser = getEntity(AppUser.class, userId);
        if ((appUser.getRole().contains(AppUser.NOCONFIRM)) && (tocken.contains(appUser.getVkTocken()))) {
            appUser.setRole(AppUser.USER);
            saveOrUpdate(appUser);
            return "Емеил подтверждён";
        }
        return "Ссылка устарела";
    }

    public String sendRestorePasswordLink(String email) {
        AppUser appUser = getAppUserFromEmail(email);
        if (appUser != null) {
            EmailManager.send(email, "AFISHA | Восстановление пароля",
                    "Перейдите по ссылке для восстановления пароля. "
                            + "http://havefun.by/afisha/setnewpassword?userId=" + email + "&tocken=" + getTocken(appUser));
            return "Пароль выслан Вам на емеил";
        } else {
            return "Пользователя с данным емеилом не существует.";
        }
    }

    public boolean canChangePassword(String tocken, String email) {
        AppUser appUser = getAppUserFromEmail(email);
        if (appUser != null) {
            return getTocken(appUser).contentEquals(tocken);
        }
        return false;
    }

    public String updatePasswordAnon(String email, String tocken, String passwordNew) {
        AppUser appUser = getAppUserFromEmail(email);
        if (appUser != null) {
            if (getTocken(appUser).contentEquals(tocken)) {
                appUser.setPassword(passwordNew);
                saveOrUpdate(appUser);
                return "Пароль изменён";
            }
        }
        return "Ссылка устарела, попробуйте ещё раз или обратитесь в форму поддержки.";
    }

    private String getTocken(AppUser appUser) {
        return KeccakUtil.getHash(appUser.getPassword()).substring(2, 17);
    }

    public String updatePassword(String email, String passwordOld, String passwordNew) {
        AppUser appUser = getAppUserFromEmail(email);
        if (passwordOld.contentEquals(appUser.getPassword())) {
            appUser.setPassword(passwordNew);
            saveOrUpdate(appUser);
            return "Пароль изменён";
        } else {
            return "Старый пароль не верен";
        }

    }

    public AppUser updateProfile(String oldEmail, String email, String fathername, String firstname, String nick, String photoURI, String surname) throws RegistrationException {
        AppUser appUser = getAppUserFromEmail(oldEmail);
        AppUser appUser2 = getAppUserFromEmail(email);
        if (appUser2 != null) {
            // Емеил изменён или остался старый
            if (appUser.getId() == appUser2.getId()) {
                // Остался тот же
            } else {
                throw new RegistrationException(email + "  уже используется другим пользователем.");
            }
        } else {
            appUser.setEmail(email);
        }

        List<AppUser> appUsers = getEntitys(AppUser.class, Restrictions.eq(AppUser.COL_NICK, nick));
        if (appUsers.size() > 0) {
            // найден чувак с таким же ником
            for (AppUser appUser3 : appUsers) {
                if (appUser.getNick().contentEquals(nick)) {
                    // ники у юзеров совпадают
                    if (appUser.getId() == appUser3.getId()) {
                        // просто не изменил ник
                    } else {
                        throw new RegistrationException(nick + " уже используется другим пользователем.");
                    }

                }
            }
        } else {
            // такой ник никем не используется
            appUser.setNick(nick);
        }

        appUser.setFathername(fathername);
        appUser.setFirstname(firstname);
        appUser.setPhotoURI(photoURI);

        if (photoURI != null) {
            appUser.setPhotoURI(photoURI);
        } else
            if (appUser.getPhotoURI() == null) {
                appUser.setPhotoURI("images/Imageevent.jpg");
            }

        appUser.setSurname(surname);
        return appUser;
    }
}
