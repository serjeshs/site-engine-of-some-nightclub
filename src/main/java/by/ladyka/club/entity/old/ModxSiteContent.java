package by.ladyka.club.entity.old;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "modx_site_content")
@Setter
@Getter
public class ModxSiteContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;
	private String contenttype;
	private String pagetitle;
	private String longtitle;
	private String description;
	private String alias;
	private String link_attributes;
	private Long published;
	private Long pub_date;
	private Long unpub_date;
	private Long parent;
	private Long isfolder;
	private String introtext;
	private String content;
	private Long richtext;
	private Long template;
	private Long menuindex;
	private Long searchable;
	private Long cacheable;
	private Long createdby;
	private Long createdon;
	private Long editedby;
	private Long editedon;
	private Long deleted;
	private Long deletedon;
	private Long deletedby;
	private Long publishedon;
	private Long publishedby;
	private String menutitle;
	private Long donthit;
	private Long haskeywords;
	private Long hasmetatags;
	private Long privateweb;
	private Long privatemgr;
	private Long content_dispo;
	private Long hidemenu;
	private Long alias_visible;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "content")
	private List<ModxSiteTmplVarContentValues> contentValues = new LinkedList<>();
}
