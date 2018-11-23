package by.ladyka.club.endpoints;

import by.ladyka.club.dto.tikets.TicketTableDto;
import by.ladyka.club.dto.tikets.TicketsOrderDto;
import by.ladyka.club.service.order.OrderTicketsService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping(value = "/api/tickets/")
@Api(description = "Operations Tickets", tags = "Tickets")
public class OrderTicketsController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private OrderTicketsService orderTicketsService;

	@GetMapping("tables")
	public @ResponseBody
	List<TicketTableDto> get(Principal principal, HttpServletRequest httpServletRequest, Long eventId) {
		return orderTicketsService.getTables(eventId);
	}

	@PostMapping("bookandpay")
	public @ResponseBody
	Map<String, Object> bookAndPay(Principal principal, HttpServletRequest httpServletRequest, @RequestBody TicketsOrderDto dto) {
		Map<String, Object> result = new TreeMap<>();
		result.put("input", dto);
		try {
			result.put("success", true);
			result.put("data", orderTicketsService.bookAndPay(dto));
		} catch (Exception ex) {
			result.put("message", ex.getLocalizedMessage());
			result.put("success", false);
			logger.error("Error", ex);
		}
		return result;
	}

}
