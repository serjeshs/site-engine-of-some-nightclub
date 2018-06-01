package by.ladyka.club.service;

import by.ladyka.club.dto.AppUser;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface AppUserService {
	AppUser build(Principal principal, HttpServletRequest httpServletRequest);
}
