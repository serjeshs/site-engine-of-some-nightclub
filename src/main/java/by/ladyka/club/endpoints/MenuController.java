package by.ladyka.club.endpoints;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.service.AppUserService;
import by.ladyka.club.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @RequestMapping(value = "order")
    public @ResponseBody
    ResponseEntity order(Principal principal, HttpServletRequest httpServletRequest, @RequestBody MenuOrderDto dto) {
        return new ResponseEntity<>(menuService.order(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "init", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity init(Principal principal, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(menuService.init(), HttpStatus.OK);
    }
}
