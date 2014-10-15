package org.vurtatoo.afisha;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.q64.promo.utils.mail.EmailManager;

@Controller
public class ContactController extends AbstractController {

	@RequestMapping(value = "contacts", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getContacts(Model model, Principal principal) {
		model.addAttribute("events", eventDao.getEventsAfter(LocalDateTime.now()));
		setRequiedName(model, principal,"Contacts");
	    return "contacts";
    }
	
	
	@RequestMapping(value = "contactsemail", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String get(Model model, Principal principal,String Name,String Email,String Phone, String Message) {
		String messageBody = Message + "<br>/n\n" + Phone + "<br>/n\nREPLY : " + Email;
		EmailManager.send("afisha@ladyka.tk", "Message from Afisha " + Name, messageBody );
	    return "DONE";
    }
	
}
