package org.vurtatoo.afisha.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
	private String decription;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endEvent;

	private String name;

	@Column(name="Place_Name")
	private String place_Name;

	@Column(name="Region_Name")
	private String region_Name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startEvent;


	//bi-directional many-to-one association to AppUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Adder_AppUser_id")
	private AppUser appUser;

	//bi-directional many-to-one association to Place
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Place_id")
	private Place place;

	//bi-directional many-to-one association to Region
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Region_id")
	private Region region;

	//bi-directional many-to-many association to Category
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

	public String getDecription() {
		return this.decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Date getEndEvent() {
		return this.endEvent;
	}

	public void setEndEvent(Date endEvent) {
		this.endEvent = endEvent;
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

	public Date getStartEvent() {
		return this.startEvent;
	}

	public void setStartEvent(Date startEvent) {
		this.startEvent = startEvent;
	}


	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}