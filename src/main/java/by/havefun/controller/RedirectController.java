package by.havefun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController extends AbstractController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "redirect:/p/main";
    }
    
    @RequestMapping(value = "blog", method = RequestMethod.GET)
    public String blog() {
        return "redirect:/blog/main";
    }
}
