package by.ladyka.club.endpoints;

import by.ladyka.club.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value = "api/news/")
public class NewsController {
	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "summary", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity summary(Principal principal, HttpServletRequest httpServletRequest) {
		return new ResponseEntity<>(newsService.summary(), HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity byIdByPath(@PathVariable(value = "id") Long id, Principal principal, HttpServletRequest httpServletRequest) {
		return new ResponseEntity<>(newsService.byId(id), HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity byId(@RequestParam(value = "id") Long id, Principal principal, HttpServletRequest httpServletRequest) {
		return new ResponseEntity<>(newsService.byId(id), HttpStatus.OK);
	}
}
