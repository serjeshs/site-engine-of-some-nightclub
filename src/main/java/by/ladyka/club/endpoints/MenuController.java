package by.ladyka.club.endpoints;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value = "api/menu/")
public class MenuController {

	private static final Logger logger = LogManager.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "summary", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity summary(Principal principal, HttpServletRequest httpServletRequest) {
		return new ResponseEntity<>(menuService.mainPage(), HttpStatus.OK);
	}

	@RequestMapping(value = "order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity order(Principal principal, HttpServletRequest httpServletRequest, @RequestBody MenuOrderDto dto) {
		return new ResponseEntity<>(menuService.order(dto), HttpStatus.OK);
	}

	@RequestMapping(value = "order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity getOrder(Principal principal, HttpServletRequest httpServletRequest, Long orderId) {
		return new ResponseEntity<>(menuService.getOrder(orderId), HttpStatus.OK);
	}

	@RequestMapping(value = "availibletables", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity tabels(Principal principal, HttpServletRequest httpServletRequest, Long eventId) {
		return new ResponseEntity<>(menuService.getAvailableTables(eventId), HttpStatus.OK);
	}



	@RequestMapping(value = "admin/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity order(Principal principal, HttpServletRequest httpServletRequest, Long eventId) {
		return new ResponseEntity<>(menuService.orders(eventId), HttpStatus.OK);
	}
}
