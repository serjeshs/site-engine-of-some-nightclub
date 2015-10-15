package by.havefun.controller;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
 

    @RequestMapping(value = "welcomepage", method = RequestMethod.GET)
    public String get(Model model, String language,HttpServletResponse resp, Locale locale) {
        Locale mylocale = new Locale((language == null) ? "kz" : language);
        model.addAttribute("locale", mylocale);
        System.out.println(locale);
        model.addAttribute("language", language);
        model.addAttribute("lang", LocalDateTime.now().toString());
        model.addAttribute("sim", LocalDateTime.now().toString());
        resp.setLocale(mylocale);
        return "WelcomePage";
    }
}