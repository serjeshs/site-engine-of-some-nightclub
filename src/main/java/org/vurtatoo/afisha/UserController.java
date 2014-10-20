package org.vurtatoo.afisha;

import org.springframework.stereotype.Controller;
import org.vurtatoo.afisha.domain.AppUser;

@Controller
public class UserController extends AbstractController {
	
	public String register() {
		AppUser newAppUser = appUserDao.registerUser();
		return "";
	}
	
	public String nonfirm() {
		return "";
	}
	
	public String resendPassword() {
		return "";
	}
	
	public String changePassword() {
		return "";
	}
	
	public String sendInvite() {
		return "";
	}
}
