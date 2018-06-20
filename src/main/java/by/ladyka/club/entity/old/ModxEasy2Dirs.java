package by.ladyka.club.entity.old;

import by.ladyka.club.entity.menu.MenuItemPricesHasOrders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "modx_easy2_dirs")
@Setter
@Getter
public class ModxEasy2Dirs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private Long id;

	private Long parent_id;
	private Long cat_left;
	private Long cat_right;
	private Long cat_level;
	private String cat_name;
	private String cat_alias;
	private String cat_summary;
	private String cat_tag;
	private String cat_description;
	private java.sql.Timestamp date_added;
	private Long added_by;
	private java.sql.Timestamp last_modified;
	private Long cat_visible;
	private Long modified_by;
	private String cat_redirect_link;
	private Long cat_thumb_id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dir")
	List<ModxEasy2Files> modxEasy2Files;
}
