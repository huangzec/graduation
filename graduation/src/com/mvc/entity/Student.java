package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student")
public class Student implements java.io.Serializable {

	// Fields

	private String stuId;
	private String claId;
	private String stuName;
	private String stuSex;
	private String stuTel;
	private String stuEmail;
	private String status;
	private String imagePath;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String stuId, String stuEmail, String status) {
		this.stuId = stuId;
		this.stuEmail = stuEmail;
		this.status = status;
	}

	/** full constructor */
	public Student(String stuId, String claId, String stuName, String stuSex,
			String stuTel, String stuEmail, String status, String imagePath) {
		this.stuId = stuId;
		this.claId = claId;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuTel = stuTel;
		this.stuEmail = stuEmail;
		this.status = status;
		this.imagePath = imagePath;
	}

	// Property accessors
	@Id
	@Column(name = "stu_ID", unique = true, nullable = false, length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "cla_ID")
	public String getClaId() {
		return this.claId;
	}

	public void setClaId(String claId) {
		this.claId = claId;
	}

	@Column(name = "stu_Name", length = 16)
	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	@Column(name = "stu_Sex", length = 4)
	public String getStuSex() {
		return this.stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	@Column(name = "stu_Tel", length = 15)
	public String getStuTel() {
		return this.stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

	@Column(name = "stu_Email", nullable = false, length = 30)
	public String getStuEmail() {
		return this.stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "image_path")
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}