package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gradeall entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gradeall")
public class Gradeall implements java.io.Serializable {

	// Fields

	private Integer gaId;
	private String stuId;
	private String departmentId;
	private Float gaGrade;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Gradeall() {
	}

	/** minimal constructor */
	public Gradeall(String departmentId, String status, String createTime) {
		this.departmentId = departmentId;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public Gradeall(String stuId, String departmentId, Float gaGrade, String status, String createTime) {
		this.stuId = stuId;
		this.departmentId = departmentId;
		this.gaGrade = gaGrade;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gA_ID", unique = true, nullable = false)
	public Integer getGaId() {
		return this.gaId;
	}

	public void setGaId(Integer gaId) {
		this.gaId = gaId;
	}

	@Column(name = "stu_ID", length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "department_id", nullable = false)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "gA_Grade", precision = 12, scale = 0)
	public Float getGaGrade() {
		return this.gaGrade;
	}

	public void setGaGrade(Float gaGrade) {
		this.gaGrade = gaGrade;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}