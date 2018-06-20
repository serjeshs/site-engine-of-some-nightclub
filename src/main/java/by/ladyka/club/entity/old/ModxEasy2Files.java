package by.ladyka.club.entity.old;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "modx_easy2_files")
@Setter
@Getter
public class ModxEasy2Files {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dir_id")
	private ModxEasy2Dirs dir;

	private String filename;
	private String size;
	private Long width;
	private Long height;
	private String alias;
	private String summary;
	private String tag;
	private String description;
	private java.sql.Timestamp date_added;
	private Long added_by;
	private java.sql.Timestamp last_modified;
	private Long modified_by;
	private Long comments;
	private Long status;
	private String redirect_link;
}
