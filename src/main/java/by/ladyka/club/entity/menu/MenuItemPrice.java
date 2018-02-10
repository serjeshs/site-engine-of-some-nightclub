package by.ladyka.club.entity.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import by.ladyka.club.entity.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = APP_TABLE_PREFIX + "menu_item_price")
@EntityListeners(AuditingEntityListener.class)
public class MenuItemPrice extends AbstractEntity {
    private Boolean active;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private MenuItem item;

}