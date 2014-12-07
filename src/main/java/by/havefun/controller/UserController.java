package by.havefun.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import by.havefun.entity.AppUser;
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
	
	@RequestMapping(value = "sendrestorepassword", produces = "text/plain;charset=UTF-8")
	public String sendrestorepassword(Model model, Principal principal, String email) {
		String result = appUserDao.sendRestorePasswordLink(email);
		setRequiedName(model, principal, result);
		model.addAttribute("result", result);
		return "result";
	}
	
	
	@RequestMapping(value = "setnewpassword", produces = "text/plain;charset=UTF-8")
	public String setnewpassword(Model model, Principal principal, String tocken, String userId) {
		if (principal != null) {
			setRequiedName(model, principal, "Изменение пароля");
			// Задаётся пароль из личного кабинета
			model.addAttribute("changePassword", true);
			return "changePassword";
		} else {
			if (appUserDao.canChangePassword(tocken, userId)) {
				setRequiedName(model, principal, "Введите новый пароль");

				// Просто задаётся новый пароль, по токену
				model.addAttribute("changePassword", false);

				model.addAttribute("tocken", tocken);
				model.addAttribute("userId", userId);

				return "changePassword";
			} else {
				setRequiedName(model, principal, "Ссылка испорчена или устарела!.");
				model.addAttribute("result", "Ссылка испорчена или устарела!.");
				return "result";
			}
		}
	}
	
	
	
	
	
	
	@RequestMapping(value = "updatepassword", produces = "text/plain;charset=UTF-8")
	public String updatepassword(Model model, Principal principal, String email,String tocken, String password_new1,String password_new2,String password_old) {
		String result;
		if (password_new1.contentEquals(password_new2)) {
			if (principal != null) {
				result = appUserDao.updatePassword(principal.getName(),password_old,password_new1);
			} else {
				result = appUserDao.updatePasswordAnon(email,tocken,password_new1);
			}
			
		} else {
			result = "Введённые пароли не совпадают";
		}
		
		setRequiedName(model, principal, result);
		model.addAttribute("result", result);
		return "result";
	}
	
	public String sendInvite() {
		return "";
	}	
	
	
	
	
	@RequestMapping(value = "saveprofileupdate", method = RequestMethod.POST)
	public String saveprofileupdate(Model model, Principal principal, String email, String fathername, String firstname, String nick, String surname, Integer lang,
	        HttpServletRequest request, MultipartFile file) {
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("photo");
            String imageUri = null;
            if (!multipartFile.isEmpty()) {
                imageUri = FileController.getRelativePath(localFile.fileAdd(multipartFile, principal.getName()));;
            }
		setRequiedName(model, principal, "Личный кабинет пользователя");
		String resultMessage;
		AppUser appUser = null;
		try {
			appUser = appUserDao.updateProfile(principal.getName(),email,fathername,firstname,nick,imageUri,surname,lang);
			resultMessage = "Обновлено";
		} catch (Exception ex) {
			resultMessage = ex.getLocalizedMessage();
			appUser = appUserDao.getAppUserFromEmail(principal.getName());
		}
		model.addAttribute("result", resultMessage); 
		model.addAttribute("appUser",appUser);
		return "profile";
		
	}
	
	@RequestMapping(value = "profile", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String profile(Model model, Principal principal) {
		setRequiedName(model, principal, "Личный кабинет пользователя");
		AppUser appUser = appUserDao.getAppUserFromEmail(principal.getName());
		model.addAttribute("appUser",appUser);
		return "profile";
	}
}
