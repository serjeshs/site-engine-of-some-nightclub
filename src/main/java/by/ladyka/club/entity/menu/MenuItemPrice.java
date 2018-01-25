package by.ladyka.club.entity.menu;

import lombok.Getter;
import lombok.Setter;
import by.ladyka.club.entity.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "menu_item_price")
@EntityListeners(AuditingEntityListener.class)
public class MenuItemPrice extends AbstractEntity {
//    @OneToMany(mappedBy = "prices")
//    private MenuItem item;
    private Boolean active;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal value;
}