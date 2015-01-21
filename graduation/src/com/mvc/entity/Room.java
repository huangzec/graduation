package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "room")
public class Room implements java.io.Serializable {

	// Fields

	private Integer id;
	private String number;
	private String name;
	private String parentId;
	private String usable;
	private String createTime;

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** minimal constructor */
	public Room(String number, String parentId, String usable, String createTime) {
		this.number = number;
		this.parentId = parentId;
		this.usable = usable;
		this.createTime = createTime;
	}

	/** full constructor */
	public Room(String number, String name, String parentId, String usable, String createTime) {
		this.number = number;
		this.name = name;
		this.parentId = parentId;
		this.usable = usable;
		this.createTime = createTime;
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

	@Column(name = "number", nullable = false)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	@Column(name = "usable", nullable = false)
	public String getUsable() {
		return this.usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}