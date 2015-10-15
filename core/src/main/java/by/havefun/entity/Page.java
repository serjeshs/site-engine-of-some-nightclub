package by.havefun.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The persistent class for the page database table.
 * 
 */
@Entity
@Table(name="page")
@NamedQuery(name="Page.findAll", query="SELECT p FROM Page p")
public class Page implements Serializable {
    
	private static final long serialVersionUID = 439L;
	public static final String COL_URI = "uriName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp created;

	private Timestamp modifed;

	@Lob
	private String text;

	private String title;

	
	private String uriName;

	//bi-directional many-to-one association to AppUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Creator_AppUser_id")
	private AppUser appUser;

	public Page() {
	}

	public int getId() {
		return this.id;
	}

	public Page setId(int id) {
		this.id = id;
		return this;
	}

	public LocalDateTime getCreated() {
		return this.created.toLocalDateTime();
	}

	public void setCreated(LocalDateTime created) {
		this.created = Timestamp.valueOf(created);
	}

	public LocalDateTime getModifed() {
		return this.modifed.toLocalDateTime();
	}

	public void setModifed(LocalDateTime modifed) {
		this.modifed = Timestamp.valueOf(modifed);
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

	public String getUriName() {
		return this.uriName;
	}

	public void setUriName(String uriName) {
		this.uriName = uriName;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}