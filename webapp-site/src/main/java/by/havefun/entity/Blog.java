package by.havefun.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the blog database table.
 * 
 */
@Entity
@Table(name="blog")
@NamedQuery(name="Blog.findAll", query="SELECT b FROM Blog b")
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp created;

	private Timestamp modifed;

	@Lob
	private String text;

	private String title;

	//bi-directional many-to-one association to AppUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Creator_AppUser_id")
	private AppUser appUser;

	public Blog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModifed() {
		return this.modifed;
	}

	public void setModifed(Timestamp modifed) {
		this.modifed = modifed;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}