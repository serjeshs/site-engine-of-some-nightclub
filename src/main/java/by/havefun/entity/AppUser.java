package by.havefun.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import by.havefun.security.KeccakUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the AppUser database table.
 * 
 */
@Entity
@NamedQuery(name="AppUser.findAll", query="SELECT a FROM AppUser a")
public class AppUser implements Serializable {
	
	private static final long serialVersionUID = 32L;
	
	public static final String ADMIN = "ADMIN";
	public static final String MANAGER = "MANAGER";
	public static final String USER = "USER";	
	public static final String BAN = "BAN";
	public static final String NOCONFIRM = "NOCONFIRM";
	public static final String GUEST = "GUEST";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp birthday;

	public static final String COL_EMAIL = "email";
	private String email;

	private String fathername;

	private String firstname;

	public static final String COL_NICK = "nick";
	private String nick;

	@JsonIgnore
	private String password;

	@Lob
	private String photoURI;

	@Column(name="Region_Name")
	private String region_Name;

	private String role;

	private String surname;

	private int vkId;

	private String vkTocken;
	
	private int lang;


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

	@JsonIgnore
	@ManyToMany(mappedBy="appUsers")
	private List<Place> places;
	
	@OneToMany(mappedBy="appuser")
        private List<AppUserLikeEvent> appuserLikeEvents;

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
		this.password = KeccakUtil.getHash(password);
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

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
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

	public List<Place> getPlaces() {
		if (this.places == null) {
			return new LinkedList<Place>();
		} else {
			return this.places;
		}
		
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	

        public List<AppUserLikeEvent> getAppuserLikeEvents() {
                return this.appuserLikeEvents;
        }

        public void setAppuserLikeEvents(List<AppUserLikeEvent> appuserLikeEvents) {
                this.appuserLikeEvents = appuserLikeEvents;
        }

        public AppUserLikeEvent addAppuserLikeEvent(AppUserLikeEvent appuserLikeEvent) {
                getAppuserLikeEvents().add(appuserLikeEvent);
                appuserLikeEvent.setAppuser(this);

                return appuserLikeEvent;
        }

        public AppUserLikeEvent removeAppuserLikeEvent(AppUserLikeEvent appuserLikeEvent) {
                getAppuserLikeEvents().remove(appuserLikeEvent);
                appuserLikeEvent.setAppuser(null);

                return appuserLikeEvent;
        }
	
	public AppUser(LocalDateTime birthday, String email, String fathername,
			String firstname, String nick, String password, String photoURI,
			String region_Name, String role, String surname, int vkId,
			String vkTocken, Region region) {
		super();
		this.birthday = Timestamp.valueOf(birthday);
		this.email = email;
		this.fathername = fathername;
		this.firstname = firstname;
		this.nick = nick;
		//this.password = password;
		setPassword(password);
		this.photoURI = photoURI;
		this.region_Name = region_Name;
		this.role = role;
		this.surname = surname;
		this.vkId = vkId;
		this.vkTocken = vkTocken;
		this.region = region;
	}

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }



}