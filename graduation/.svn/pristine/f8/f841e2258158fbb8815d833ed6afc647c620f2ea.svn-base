package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tbgrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbgrade", catalog = "graduation")
public class Tbgrade implements java.io.Serializable {

	// Fields

	private Integer graId;
	private String deptId;
	private String graNumber;

	// Constructors

	/** default constructor */
	public Tbgrade() {
	}

	/** minimal constructor */
	public Tbgrade(String graNumber) {
		this.graNumber = graNumber;
	}

	/** full constructor */
	public Tbgrade(String deptId, String graNumber) {
		this.deptId = deptId;
		this.graNumber = graNumber;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gra_ID", unique = true, nullable = false)
	public Integer getGraId() {
		return this.graId;
	}

	public void setGraId(Integer graId) {
		this.graId = graId;
	}

	@Column(name = "dept_ID", length = 10)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "gra_Number", nullable = false, length = 8)
	public String getGraNumber() {
		return this.graNumber;
	}

	public void setGraNumber(String graNumber) {
		this.graNumber = graNumber;
	}

}