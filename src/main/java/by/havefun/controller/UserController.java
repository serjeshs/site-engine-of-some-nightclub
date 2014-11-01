package by.havefun.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.havefun.exception.RegistrationException;

@Controller
public class UserController extends AbstractController {
	
	@RequestMapping(value = "registration", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String register(Model model, Principal principal) {
		setRequiedName(model, principal, "Регистрация нового пользователя");
		model.addAttribute("Email", "Email");
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
			return "result";
		} else {
			model.addAttribute("Email", email);
			model.addAttribute("nick", nick);
			return "register";
		}
		
	}
	
	@RequestMapping(value = "confirm", produces = "text/plain;charset=UTF-8")
	public String confirm(int userId, String tocken, Model model, Principal principal) {
		setRequiedName(model, principal, "Подтверждение почтового ящика");
		model.addAttribute("result", appUserDao.confirmEmail(userId,tocken));
		return "result";
	}
	
	@RequestMapping(value = "login", produces = "text/plain;charset=UTF-8")
	public String login(Model model, Principal principal) {
		setRequiedName(model, principal, "Авторизация");
		return "login";
	}
	
	
	@RequestMapping(value = "loginerror", produces = "text/plain;charset=UTF-8")
	public String loginerror(Model model, Principal principal) {
		setRequiedName(model, principal, "Авторизация");
		model.addAttribute("result", "Логин или пароль не верны");
		return "login";
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
