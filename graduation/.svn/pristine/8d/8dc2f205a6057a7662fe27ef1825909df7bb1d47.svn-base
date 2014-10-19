package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Deptmanager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "deptmanager", catalog = "graduation")
public class Deptmanager implements java.io.Serializable {

	// Fields

	private String dmId;
	private String deptId;
	private String dmName;
	private String dmSex;
	private String dmTel;
	private String dmEmail;

	// Constructors

	/** default constructor */
	public Deptmanager() {
	}

	/** minimal constructor */
	public Deptmanager(String dmId, String dmEmail) {
		this.dmId = dmId;
		this.dmEmail = dmEmail;
	}

	/** full constructor */
	public Deptmanager(String dmId, String deptId, String dmName, String dmSex,
			String dmTel, String dmEmail) {
		this.dmId = dmId;
		this.deptId = deptId;
		this.dmName = dmName;
		this.dmSex = dmSex;
		this.dmTel = dmTel;
		this.dmEmail = dmEmail;
	}

	// Property accessors
	@Id
	@Column(name = "dm_ID", unique = true, nullable = false, length = 12)
	public String getDmId() {
		return this.dmId;
	}

	public void setDmId(String dmId) {
		this.dmId = dmId;
	}

	@Column(name = "dept_ID", length = 10)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dm_Name", length = 16)
	public String getDmName() {
		return this.dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	@Column(name = "dm_Sex", length = 4)
	public String getDmSex() {
		return this.dmSex;
	}

	public void setDmSex(String dmSex) {
		this.dmSex = dmSex;
	}

	@Column(name = "dm_Tel", length = 15)
	public String getDmTel() {
		return this.dmTel;
	}

	public void setDmTel(String dmTel) {
		this.dmTel = dmTel;
	}

	@Column(name = "dm_Email", nullable = false, length = 30)
	public String getDmEmail() {
		return this.dmEmail;
	}

	public void setDmEmail(String dmEmail) {
		this.dmEmail = dmEmail;
	}

}