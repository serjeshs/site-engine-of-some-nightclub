package by.havefun.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cost;

	@Lob
	private String costText;

	@Lob
	@Column(name="decription")
	private String description;

	
	private Timestamp startEvent;
	public static final String COL_START_EVENT = "startEvent";

	private Timestamp endEvent;
	public static final String COL_END_EVENT = "endEvent";

	private String name;

	@Column(name="Place_Name")
	private String place_Name;

	@Column(name="Region_Name")
	private String region_Name;

	@Column(name="Place_id")
	private int place;
	public static final String COL_PLACE_ID = "place";

	@Column(name="Region_id")
	private int region;
	public static final String COL_REGION_ID = "region";
	
	@Column(name="IMAGE_EVENT_URI")
	private String imageUri;



	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Adder_AppUser_id")
	private AppUser appUser;

	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="Event_has_Category"
		, joinColumns={
			@JoinColumn(name="Event_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Category_id")
			}
		)
	private List<Category> categories;

	public Event() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getCostText() {
		return this.costText;
	}

	public void setCostText(String costText) {
		this.costText = costText;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDecription(String description) {
		this.description = description;
	}

	public LocalDateTime getEndEventLDT() {
		return this.endEvent.toLocalDateTime();
	}

	public void setEndEvent(Timestamp endEvent) {
		this.endEvent = endEvent;
	}
	
	public Timestamp getEndEvent() {
		return this.endEvent;
	}

	public void setEndEvent(LocalDateTime endEvent) {
		this.endEvent = Timestamp.valueOf(endEvent);
	}
	
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace_Name() {
		return this.place_Name;
	}

	public void setPlace_Name(String place_Name) {
		this.place_Name = place_Name;
	}

	public String getRegion_Name() {
		return this.region_Name;
	}

	public void setRegion_Name(String region_Name) {
		this.region_Name = region_Name;
	}

	public LocalDateTime getStartEventLDT() {
		return this.startEvent.toLocalDateTime();
	}

	public void setStartEvent(Timestamp startEvent) {
		this.startEvent = startEvent;
	}
	
	public Timestamp getStartEvent() {
		return this.startEvent;
	}

	public void setStartEvent(LocalDateTime startEvent) {
		this.startEvent = Timestamp.valueOf(startEvent);
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public int getPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getRegion() {
		return this.region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getImageUri() {
	    return imageUri;
    }

	public void setImageUri(String imageUri) {
	    this.imageUri = imageUri;
    }

	public Event(int cost, String costText, String description,
			Timestamp startEvent, Timestamp endEvent, String name,
			String place_Name, String region_Name, int place, int region,
			String imageUri, AppUser appUser) {
		super();
		this.cost = cost;
		this.costText = costText;
		this.description = description;
		this.startEvent = startEvent;
		this.endEvent = endEvent;
		this.name = name;
		this.place_Name = place_Name;
		this.region_Name = region_Name;
		this.place = place;
		this.region = region;
		this.imageUri = imageUri;
		this.appUser = appUser;
	}

}