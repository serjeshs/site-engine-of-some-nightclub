package by.ladyka.club.entity.menu;

import by.ladyka.club.entity.AbstractEntity;
import by.ladyka.club.entity.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "menu_order")
@EntityListeners(AuditingEntityListener.class)
public class MenuOrder extends AbstractEntity {
    private String name;
    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    private Event event;

    private String arrivalTime;
    private Integer people;
    private String bcCode;
    private String description;
    private Integer tableNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    List<MenuItemPricesHasOrders> itemPricesHasOrders;

}
