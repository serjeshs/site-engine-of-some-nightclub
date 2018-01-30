package by.ladyka.club.entity.menu;

import lombok.Getter;
import lombok.Setter;
import by.ladyka.club.entity.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import java.util.List;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "menu_item")
@EntityListeners(AuditingEntityListener.class)
public class MenuItem extends AbstractEntity {
    private String name;
    private String description;
    private String descriptionProportions;
    private MenuCategory category;
    private Boolean active;
    private List<MenuItemPrice> prices;
}