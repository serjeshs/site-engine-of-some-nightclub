package by.ladyka.club.dto.menu;

import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.club.dto.tikets.TablePlaceDto;
import by.ladyka.club.entity.EventEntity;
import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import by.ladyka.club.entity.order.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Deprecated // TicketsOrderDto
public class TicketOrderDto {
	private Long id;
	private Map<Long, Integer> food = new LinkedHashMap<>();
	private Map<Long, BigDecimal> foodPrice = new LinkedHashMap<>();
	private String name;
	private String surname;
	private String email;
	private String phone;
	private Long event;
	private String eventName;
	private String arrivalTime;
	private Integer people;
	private String description;
	private Integer tableNumber;
	private String uuid = UUID.randomUUID().toString();
	private String payStatus;

	private int dance;
	private List<TablePlaceDto> tables = new ArrayList<>();
}
