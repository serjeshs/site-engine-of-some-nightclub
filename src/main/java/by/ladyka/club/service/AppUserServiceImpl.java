package by.ladyka.club.service;

import by.ladyka.club.dto.AppUser;
import by.ladyka.club.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Override
    public AppUser build(Principal principal, HttpServletRequest httpServletRequest) {
        return new AppUser(new UserEntity());
    }
}
