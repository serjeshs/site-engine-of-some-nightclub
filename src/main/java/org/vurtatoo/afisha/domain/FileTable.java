package org.vurtatoo.afisha.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FileTable database table.
 * 
 */
@Entity
@NamedQuery(name="FileTable.findAll", query="SELECT f FROM FileTable f")
public class FileTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String filename;

	private String filepath;

	private String hashcode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timeAdd;

	//bi-directional many-to-one association to AppUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AppUser_id")
	private AppUser appUser;

	public FileTable() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getHashcode() {
		return this.hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	public Date getTimeAdd() {
		return this.timeAdd;
	}

	public void setTimeAdd(Date timeAdd) {
		this.timeAdd = timeAdd;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}