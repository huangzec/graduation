package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tbclass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbclass")
public class Tbclass implements java.io.Serializable {

	// Fields

	private String claId;
	private String claName;
	private String proId;

	// Constructors

	/** default constructor */
	public Tbclass() {
	}

	/** minimal constructor */
	public Tbclass(String claId) {
		this.claId = claId;
	}

	/** full constructor */
	public Tbclass(String claId, String claName, String proId) {
		this.claId = claId;
		this.claName = claName;
		this.proId = proId;
	}

	// Property accessors
	@Id
	@Column(name = "cla_ID", unique = true, nullable = false)
	public String getClaId() {
		return this.claId;
	}

	public void setClaId(String claId) {
		this.claId = claId;
	}

	@Column(name = "cla_Name")
	public String getClaName() {
		return this.claName;
	}

	public void setClaName(String claName) {
		this.claName = claName;
	}

	@Column(name = "pro_ID", length = 10)
	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

}