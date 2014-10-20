package org.vurtatoo.afisha;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vurtatoo.afisha.domain.AppUser;
import org.vurtatoo.afisha.exception.RegistrationException;

import by.q64.promo.utils.mail.EmailManager;

@Controller
public class UserController extends AbstractController {
	
	@RequestMapping(value = "registration", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String register(Model model, Principal principal) {
		setRequiedName(model, principal, "Регистрация нового пользователя");
		model.addAttribute("Email", "E-mail");
		model.addAttribute("nick", "Никнейм");
		return "register";
	}
	
	@RequestMapping(value = "registration", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String registerDo(String email, String nick, String password1, String password2, Model model, Principal principal) {
		boolean allOk = false;
		setRequiedName(model, principal, "Регистрация нового пользователя");
		String resultMessage;
		String password;
		if (password1.contains(password2)) {
			password = password2;
			try {
		        resultMessage = appUserDao.registerUser(email, nick, password);
		        allOk = true;
	        } catch (RegistrationException e) {
		        resultMessage = e.getMessage();
		        allOk = false;
	        }
		}  else {
			resultMessage = "Введённые пароли не совпадают";
		}
		model.addAttribute("result", resultMessage);
		if (allOk) {
			return "rezult";
		} else {
			model.addAttribute("Email", email);
			model.addAttribute("nick", nick);
			return "register";
		}
		
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
	
	public String forgotPassword() {
		return "";
	}
}
