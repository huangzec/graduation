package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Opentopicscore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "opentopicscore")
public class Opentopicscore implements java.io.Serializable {

	// Fields

	private Integer id;
	private String studentId;
	private String departmentId;
	private Double score;
	private String createTime;

	// Constructors

	/** default constructor */
	public Opentopicscore() {
	}

	/** full constructor */
	public Opentopicscore(String studentId, String departmentId, Double score, String createTime) {
		this.studentId = studentId;
		this.departmentId = departmentId;
		this.score = score;
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

	@Column(name = "student_id", nullable = false)
	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "department_id", nullable = false)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "score", nullable = false, precision = 22, scale = 0)
	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}