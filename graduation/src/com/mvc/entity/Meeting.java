package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Meeting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "meeting")
public class Meeting implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String parentId;
	private String startTime;
	private String place;
	private String person;
	private String content;
	private String hoster;
	private String recorder;

	// Constructors

	/** default constructor */
	public Meeting() {
	}

	/** minimal constructor */
	public Meeting(String parentId) {
		this.parentId = parentId;
	}

	/** full constructor */
	public Meeting(String name, String parentId, String startTime, String place, String person, String content, String hoster, String recorder) {
		this.name = name;
		this.parentId = parentId;
		this.startTime = startTime;
		this.place = place;
		this.person = person;
		this.content = content;
		this.hoster = hoster;
		this.recorder = recorder;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "parent_id", nullable = false)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "start_time")
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "place")
	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "person")
	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "hoster")
	public String getHoster() {
		return this.hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
	}

	@Column(name = "recorder")
	public String getRecorder() {
		return this.recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

}