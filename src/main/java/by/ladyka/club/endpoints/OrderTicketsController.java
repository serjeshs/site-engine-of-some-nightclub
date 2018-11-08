package by.ladyka.club.endpoints;

import by.ladyka.club.dto.TicketTableDto;
import by.ladyka.club.service.order.OrderTicketsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets/")
@Api(description = "Operations Tickets", tags = "Tickets")
public class OrderTicketsController {
	@Autowired
	private OrderTicketsService orderTicketsService;

	@GetMapping("tables")
	public @ResponseBody
	List<TicketTableDto> get(Principal principal, HttpServletRequest httpServletRequest, Long eventId) {
		return orderTicketsService.getTables(eventId);
	}

}
