package by.ladyka.club.entity.menu;

import by.ladyka.club.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import java.util.List;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@Table(name = APP_TABLE_PREFIX + "menu_category")
@EntityListeners(AuditingEntityListener.class)
public class MenuCategory extends AbstractEntity {
    private String name;
    private Boolean active;
    private List<MenuItem> items;
    private List<MenuCategory> children;
    private MenuCategory parent;

}