package by.ladyka.club.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_ticket_price")
@EntityListeners(AuditingEntityListener.class)
public class EventTicketPriceEntity  extends AbstractEntity {
    private BigDecimal cost;
    private BigDecimal quantity;
    @Enumerated(EnumType.STRING)
    private EventTicketPriceType type;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;
    private LocalDateTime startActiveTime;
    private LocalDateTime endActiveTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity modifiedBy;
}
