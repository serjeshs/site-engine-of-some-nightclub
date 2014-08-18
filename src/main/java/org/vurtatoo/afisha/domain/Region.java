package org.vurtatoo.afisha.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Region database table.
 * 
 */
@Entity
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String coordinates;

	private String regionName;

	//bi-directional many-to-one association to AppUser
	@OneToMany(mappedBy="region")
	private List<AppUser> appUsers;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="region")
	private List<Event> events;

	//bi-directional many-to-one association to Place
	@OneToMany(mappedBy="region")
	private List<Place> places;

	public Region() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<AppUser> getAppUsers() {
		return this.appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	public AppUser addAppUser(AppUser appUser) {
		getAppUsers().add(appUser);
		appUser.setRegion(this);

		return appUser;
	}

	public AppUser removeAppUser(AppUser appUser) {
		getAppUsers().remove(appUser);
		appUser.setRegion(null);

		return appUser;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setRegion(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setRegion(null);

		return event;
	}

	public List<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place addPlace(Place place) {
		getPlaces().add(place);
		place.setRegion(this);

		return place;
	}

	public Place removePlace(Place place) {
		getPlaces().remove(place);
		place.setRegion(null);

		return place;
	}

}