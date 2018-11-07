package by.ladyka.club.entity.order;

import by.ladyka.bepaid.dto.GatewayStatus;
import by.ladyka.club.entity.AbstractEntity;
import by.ladyka.club.entity.Event;
import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "ticket_order")
@EntityListeners(AuditingEntityListener.class)
@ToString
public class OrderEntity extends AbstractEntity {
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	List<MenuItemPricesHasOrders> itemPricesHasOrders;
	private String name;
	private String surname;
	private String email;
	private String phone;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventId")
	private Event event;
	private String arrivalTime;
	private String description;

	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid = UUID.randomUUID().toString();
	private String token;

	@Enumerated(EnumType.STRING)
	private GatewayStatus payStatus;
	private String uid;



	//Paid Values
	private Long dance;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity")
	private List<OrderItemEntity> tableNumbers;


//	private Integer people;
//	private Integer tableNumber;

}
