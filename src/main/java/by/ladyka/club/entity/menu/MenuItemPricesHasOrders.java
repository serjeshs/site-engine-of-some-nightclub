package by.ladyka.club.entity.menu;

import by.ladyka.club.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = APP_TABLE_PREFIX + "menu_item_price_has_order")
@EntityListeners(AuditingEntityListener.class)
public class MenuItemPricesHasOrders extends AbstractEntity {
    @Column(nullable = false)
    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private MenuOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemPriceId")
    private MenuItemPrice itemPrice;

}
