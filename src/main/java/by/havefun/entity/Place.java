package by.havefun.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;



/**
 * The persistent class for the Place database table.
 * 
 */
@Entity
@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String address;

	@Lob
	private String contactInfo;

	private String coordinates;

	@Lob
	private String descriotion;

	private String name;

	@Column(name="Region_Name")
	private String region_Name;
	
	@Column(name="Region_id")
	private int region;


	//bi-directional many-to-many association to Appuser
	@ManyToMany
	@JoinTable(
		name="appuser_has_place"
		, joinColumns={
			@JoinColumn(name="place_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="appuser_id")
			}
		)
	private List<AppUser> appUsers;

	
	public Place() {
	}

	public int getId() {
		return this.id;
	}

	public Place setId(int id) {
		this.id = id;
		return this;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactInfo() {
		return this.contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getDescriotion() {
		return this.descriotion;
	}

	public void setDescriotion(String descriotion) {
		this.descriotion = descriotion;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion_Name() {
		return this.region_Name;
	}

	public void setRegion_Name(String region_Name) {
		this.region_Name = region_Name;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public List<AppUser> getAppUsers() {
	    return appUsers;
    }

	public void setAppUsers(List<AppUser> appUsers) {
	    this.appUsers = appUsers;
    }


}