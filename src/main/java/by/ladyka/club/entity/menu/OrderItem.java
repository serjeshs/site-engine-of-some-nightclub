package by.ladyka.club.entity.menu;

import lombok.Getter;
import lombok.Setter;
import by.ladyka.club.entity.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "order_item")
@EntityListeners(AuditingEntityListener.class)
public class OrderItem extends AbstractEntity {
    private MenuItemPrice menuItemPrice;
    private Order order;
    private Long count;
}
