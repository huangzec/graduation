package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "department")
public class Department implements java.io.Serializable {

	// Fields

	private String deptId;
	private String deptName;
	private String majorType;

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String deptId, String deptName, String majorType) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.majorType = majorType;
	}

	// Property accessors
	@Id
	@Column(name = "dept_ID", unique = true, nullable = false, length = 10)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_Name", nullable = false, length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "major_type", nullable = false)
	public String getMajorType() {
		return this.majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

}