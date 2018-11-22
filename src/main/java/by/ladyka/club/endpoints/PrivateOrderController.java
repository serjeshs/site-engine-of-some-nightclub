package by.ladyka.club.endpoints;

import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.service.MenuService;
import by.ladyka.club.service.order.OrderTicketsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/private/orders")
@AllArgsConstructor
public class PrivateOrderController {

	private MenuService menuService;
	private OrderTicketsService orderTicketsService;

	@GetMapping(value = "/{uuid}")
	public TicketOrderDto order(@PathVariable String uuid) {
		try {
			return orderTicketsService.getOrder(uuid);
		} catch (RuntimeException ex) {
			return menuService.getOrder(uuid);
		}
	}

}
