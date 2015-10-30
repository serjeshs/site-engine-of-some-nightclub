package by.havefun.api.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value="/blog/")
public class BlogController extends AbstractController {
    
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String getMainPage(Model model, Principal principal) {
        setRequiedName(model, principal, "BLOG");
        model.addAttribute("result", "Всем привет. Это первая запись из блога нашего сайта с афишами. Тут будут отображены все изменения, которые происходят на сайте. Сейчас добавлена возможность фильтровать события по времени и на наличие свободного входа.");
        return "result";
    }
}
