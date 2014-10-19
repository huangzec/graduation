package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Revieworder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "revieworder", catalog = "graduation")
public class Revieworder implements java.io.Serializable {

	// Fields

	private Integer id;
	private String studentId;
	private String teacherId;
	private String departmentId;
	private String tbgradeId;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Revieworder() {
	}

	/** full constructor */
	public Revieworder(String studentId, String teacherId, String departmentId,
			String tbgradeId, String status, String createTime) {
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.departmentId = departmentId;
		this.tbgradeId = tbgradeId;
		this.status = status;
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

	@Column(name = "teacher_id", nullable = false)
	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name = "department_id", nullable = false)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "tbgrade_id", nullable = false)
	public String getTbgradeId() {
		return this.tbgradeId;
	}

	public void setTbgradeId(String tbgradeId) {
		this.tbgradeId = tbgradeId;
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