package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Profession entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profession")
public class Profession implements java.io.Serializable {

	// Fields

	private String proId;
	private Integer graId;
	private String proName;
	private String deptId;

	// Constructors

	/** default constructor */
	public Profession() {
	}

	/** minimal constructor */
	public Profession(String proId, String proName) {
		this.proId = proId;
		this.proName = proName;
	}

	/** full constructor */
	public Profession(String proId, Integer graId, String proName, String deptId) {
		this.proId = proId;
		this.graId = graId;
		this.proName = proName;
		this.deptId = deptId;
	}

	// Property accessors
	@Id
	@Column(name = "pro_ID", unique = true, nullable = false, length = 10)
	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	@Column(name = "gra_ID")
	public Integer getGraId() {
		return this.graId;
	}

	public void setGraId(Integer graId) {
		this.graId = graId;
	}

	@Column(name = "pro_Name", nullable = false, length = 50)
	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Column(name = "dept_ID")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}