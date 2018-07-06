package by.ladyka.club.endpoints;

import by.ladyka.club.dto.UserDto;
import by.ladyka.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity get(Principal principal, HttpServletRequest httpServletRequest) {
		return new ResponseEntity<>(getUserDto(principal), HttpStatus.OK);
	}

	private UserDto getUserDto(Principal principal) {
		return (principal != null) ? userService.getUser(principal.getName()) : new UserDto();
	}

}
