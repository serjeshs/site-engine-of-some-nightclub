package by.ladyka.club.endpoints;

import by.ladyka.club.dto.menu.MenuOrderDto;
import by.ladyka.club.entity.menu.MenuOrder;
import by.ladyka.club.service.MenuService;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/private/orders")
@AllArgsConstructor
public class OrderController {

	private MenuService menuService;

	@GetMapping(value = "/{uuid}")
	public MenuOrderDto order(@PathVariable String uuid) {
		return menuService.getOrder(uuid);
	}

}
