package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher")
public class Teacher implements java.io.Serializable {

	// Fields

	private String teaId;
	private String deptId;
	private String teaName;
	private String teaSex;
	private String teaPos;
	private String status;
	private Boolean teaJudge;
	private Boolean teaGroup;
	private String teaTel;
	private String teaEmail;
	private String imagePath;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String teaId, String status, String teaEmail) {
		this.teaId = teaId;
		this.status = status;
		this.teaEmail = teaEmail;
	}

	/** full constructor */
	public Teacher(String teaId, String deptId, String teaName, String teaSex,
			String teaPos, String status, Boolean teaJudge, Boolean teaGroup,
			String teaTel, String teaEmail, String imagePath) {
		this.teaId = teaId;
		this.deptId = deptId;
		this.teaName = teaName;
		this.teaSex = teaSex;
		this.teaPos = teaPos;
		this.status = status;
		this.teaJudge = teaJudge;
		this.teaGroup = teaGroup;
		this.teaTel = teaTel;
		this.teaEmail = teaEmail;
		this.imagePath = imagePath;
	}

	// Property accessors
	@Id
	@Column(name = "tea_ID", unique = true, nullable = false, length = 12)
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	@Column(name = "dept_ID", length = 10)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "tea_Name", length = 16)
	public String getTeaName() {
		return this.teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	@Column(name = "tea_Sex", length = 4)
	public String getTeaSex() {
		return this.teaSex;
	}

	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}

	@Column(name = "tea_Pos", length = 20)
	public String getTeaPos() {
		return this.teaPos;
	}

	public void setTeaPos(String teaPos) {
		this.teaPos = teaPos;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "tea_Judge")
	public Boolean getTeaJudge() {
		return this.teaJudge;
	}

	public void setTeaJudge(Boolean teaJudge) {
		this.teaJudge = teaJudge;
	}

	@Column(name = "tea_Group")
	public Boolean getTeaGroup() {
		return this.teaGroup;
	}

	public void setTeaGroup(Boolean teaGroup) {
		this.teaGroup = teaGroup;
	}

	@Column(name = "tea_Tel", length = 15)
	public String getTeaTel() {
		return this.teaTel;
	}

	public void setTeaTel(String teaTel) {
		this.teaTel = teaTel;
	}

	@Column(name = "tea_Email", nullable = false, length = 30)
	public String getTeaEmail() {
		return this.teaEmail;
	}

	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}

	@Column(name = "image_path")
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}