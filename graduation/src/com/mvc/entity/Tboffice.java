package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tboffice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tboffice")
public class Tboffice implements java.io.Serializable {

	// Fields

	private String offId;
	private String offName;
	private String offSex;
	private String offTel;
	private String offEmail;

	// Constructors

	/** default constructor */
	public Tboffice() {
	}

	/** minimal constructor */
	public Tboffice(String offId, String offEmail) {
		this.offId = offId;
		this.offEmail = offEmail;
	}

	/** full constructor */
	public Tboffice(String offId, String offName, String offSex, String offTel, String offEmail) {
		this.offId = offId;
		this.offName = offName;
		this.offSex = offSex;
		this.offTel = offTel;
		this.offEmail = offEmail;
	}

	// Property accessors
	@Id
	@Column(name = "off_ID", unique = true, nullable = false, length = 12)
	public String getOffId() {
		return this.offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	@Column(name = "off_Name", length = 16)
	public String getOffName() {
		return this.offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	@Column(name = "off_Sex", length = 4)
	public String getOffSex() {
		return this.offSex;
	}

	public void setOffSex(String offSex) {
		this.offSex = offSex;
	}

	@Column(name = "off_Tel", length = 15)
	public String getOffTel() {
		return this.offTel;
	}

	public void setOffTel(String offTel) {
		this.offTel = offTel;
	}

	@Column(name = "off_Email", nullable = false, length = 30)
	public String getOffEmail() {
		return this.offEmail;
	}

	public void setOffEmail(String offEmail) {
		this.offEmail = offEmail;
	}

}