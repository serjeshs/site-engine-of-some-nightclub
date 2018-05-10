package by.ladyka.club.endpoints;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.service.AppUserService;
import by.ladyka.club.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value = "api/menu/")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity summary(Principal principal, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(menuService.mainPage(), HttpStatus.OK);
    }

    @RequestMapping(value = "order", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity order(Principal principal, HttpServletRequest httpServletRequest, MenuOrderDto order) {
        return new ResponseEntity<>(menuService.order(order), HttpStatus.OK);
    }

    @RequestMapping(value = "init", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity init(Principal principal, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(menuService.init(), HttpStatus.OK);
    }
}
