package by.havefun.api.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController extends AbstractController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage1() {
        return "redirect:/index.html";
    }
    
    @RequestMapping(value = "/p", method = RequestMethod.GET)
    public String mainPage2() {
        return "redirect:/p/";
    }
    
    @RequestMapping(value = "blog", method = RequestMethod.GET)
    public String blog() {
        return "redirect:/blog/main";
    }
}
