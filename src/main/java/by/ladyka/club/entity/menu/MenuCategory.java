package by.ladyka.club.entity.menu;

import by.ladyka.club.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = APP_TABLE_PREFIX + "menu_category")
@EntityListeners(AuditingEntityListener.class)
public class MenuCategory extends AbstractEntity {
    private String name;
    private String description;
    private Boolean active;
    private Integer categoryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private MenuCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<MenuCategory> children;

    @OneToMany(mappedBy = "category")
    private List<MenuItem> items;

}