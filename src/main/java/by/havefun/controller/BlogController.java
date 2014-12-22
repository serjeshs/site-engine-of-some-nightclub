package by.havefun.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/blog/")
public class BlogController extends AbstractController {
    
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String getMainPage(Model model, Principal principal) {
        setRequiedName(model, principal, "BLOG");
        model.addAttribute("result", "BLOGINDEX2");
        return "result";
    }
}
