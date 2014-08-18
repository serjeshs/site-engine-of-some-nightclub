package org.vurtatoo.afisha.domain;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the AppUser database table.
 * 
 */
@Entity
@NamedQuery(name="AppUser.findAll", query="SELECT a FROM AppUser a")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp birthday;

	private String email;

	private String fathername;

	private String firstname;

	private String nick;

	@JsonIgnore
	private String password;

	@Lob
	private String photoURI;

	@Column(name="Region_Name")
	private String region_Name;

	private int role;

	private String surname;

	private int vkId;

	private String vkTocken;


	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Default_Region_id")
	private Region region;

	@JsonIgnore
	@OneToMany(mappedBy="appUser")
	private List<Event> events;

	@JsonIgnore
	@OneToMany(mappedBy="appUser")
	private List<FileTable> fileTables;

//	@ManyToMany(cascade = ALL, targetEntity = org.vurtatoo.afisha.domain.Event.class)
//	@JoinTable(name = "AppUser_has_Event", 
//			joinColumns = @JoinColumn(name = "AppUser_id", referencedColumnName = "id", nullable = true), 
//			inverseJoinColumns = @JoinColumn(name = "Event_id", referencedColumnName = "id", nullable = false))
//	@OrderBy
//	private List<Event> viewEvents;

	public AppUser() {
	}

	public int getId() {
		return this.id;
	}

	public AppUser setId(int id) {
		this.id = id;
		return this;
	}

	public LocalDateTime getBirthday() {
		return this.birthday.toLocalDateTime();
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = Timestamp.valueOf(birthday);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFathername() {
		return this.fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoURI() {
		return this.photoURI;
	}

	public void setPhotoURI(String photoURI) {
		this.photoURI = photoURI;
	}

	public String getRegion_Name() {
		return this.region_Name;
	}

	public void setRegion_Name(String region_Name) {
		this.region_Name = region_Name;
	}

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getVkId() {
		return this.vkId;
	}

	public void setVkId(int vkId) {
		this.vkId = vkId;
	}

	public String getVkTocken() {
		return this.vkTocken;
	}

	public void setVkTocken(String vkTocken) {
		this.vkTocken = vkTocken;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setAppUser(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setAppUser(null);

		return event;
	}

	public List<FileTable> getFileTables() {
		return this.fileTables;
	}

	public void setFileTables(List<FileTable> fileTables) {
		this.fileTables = fileTables;
	}

	public FileTable addFileTable(FileTable fileTable) {
		getFileTables().add(fileTable);
		fileTable.setAppUser(this);

		return fileTable;
	}

	public FileTable removeFileTable(FileTable fileTable) {
		getFileTables().remove(fileTable);
		fileTable.setAppUser(null);

		return fileTable;
	}



}