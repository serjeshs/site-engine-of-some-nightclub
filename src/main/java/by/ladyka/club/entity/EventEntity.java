package by.ladyka.club.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "club_event")
@EntityListeners(AuditingEntityListener.class)
public class EventEntity extends AbstractEntity {
	public static final String startEventFieldName = "startEvent";
	@Lob
	private String costText;
	@Lob
	private String description;
	private LocalDateTime startEvent;
	private LocalDateTime endEvent;
	private String name;
	private String coverUri;
	private Boolean recommendation;
	private Boolean republicPay;
	private BigDecimal costDance;
	private BigDecimal costTablePlace;

	//TODO remove me
	private String alias;
	private BigDecimal cost;
	private String buyTicketUrl;

	public EventEntity(Long eventId) {
		setId(eventId);
	}
}
