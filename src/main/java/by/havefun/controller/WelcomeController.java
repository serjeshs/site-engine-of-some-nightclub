package by.havefun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
 

    @RequestMapping(value = "welcomepage", method = RequestMethod.GET)
    public String get(Model model, String language) {
        model.addAttribute("language", language);
        
        return "WelcomePage";
    }
}