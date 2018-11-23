package by.ladyka.club.entity.menu;

import by.ladyka.club.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static by.ladyka.club.ClubApplication.APP_TABLE_PREFIX;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = APP_TABLE_PREFIX + "menu_item")
@EntityListeners(AuditingEntityListener.class)
public class MenuItem extends AbstractEntity {
	private String name;
	private String description;
	private String descriptionProportions;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private MenuCategory category;

	@OneToMany(mappedBy = "item")
	private List<MenuItemPrice> prices = new ArrayList<>();

}