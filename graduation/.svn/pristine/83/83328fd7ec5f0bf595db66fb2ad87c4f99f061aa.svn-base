package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "graduation")
public class Student implements java.io.Serializable {

	// Fields

	private String stuId;
	private Integer gdiId;
	private Integer opiId;
	private Integer trId;
	private Integer gtId;
	private Integer gtrId;
	private Integer goId;
	private Integer tdId;
	private Integer sttId;
	private Integer claId;
	private Integer gaId;
	private String stuName;
	private String stuSex;
	private String stuTel;
	private String stuEmail;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String stuId, String stuEmail) {
		this.stuId = stuId;
		this.stuEmail = stuEmail;
	}

	/** full constructor */
	public Student(String stuId, Integer gdiId, Integer opiId, Integer trId,
			Integer gtId, Integer gtrId, Integer goId, Integer tdId,
			Integer sttId, Integer claId, Integer gaId, String stuName,
			String stuSex, String stuTel, String stuEmail) {
		this.stuId = stuId;
		this.gdiId = gdiId;
		this.opiId = opiId;
		this.trId = trId;
		this.gtId = gtId;
		this.gtrId = gtrId;
		this.goId = goId;
		this.tdId = tdId;
		this.sttId = sttId;
		this.claId = claId;
		this.gaId = gaId;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuTel = stuTel;
		this.stuEmail = stuEmail;
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

	@Column(name = "gdi_ID")
	public Integer getGdiId() {
		return this.gdiId;
	}

	public void setGdiId(Integer gdiId) {
		this.gdiId = gdiId;
	}

	@Column(name = "opi_ID")
	public Integer getOpiId() {
		return this.opiId;
	}

	public void setOpiId(Integer opiId) {
		this.opiId = opiId;
	}

	@Column(name = "tR_ID")
	public Integer getTrId() {
		return this.trId;
	}

	public void setTrId(Integer trId) {
		this.trId = trId;
	}

	@Column(name = "gt_ID")
	public Integer getGtId() {
		return this.gtId;
	}

	public void setGtId(Integer gtId) {
		this.gtId = gtId;
	}

	@Column(name = "gtr_ID")
	public Integer getGtrId() {
		return this.gtrId;
	}

	public void setGtrId(Integer gtrId) {
		this.gtrId = gtrId;
	}

	@Column(name = "go_ID")
	public Integer getGoId() {
		return this.goId;
	}

	public void setGoId(Integer goId) {
		this.goId = goId;
	}

	@Column(name = "tD_ID")
	public Integer getTdId() {
		return this.tdId;
	}

	public void setTdId(Integer tdId) {
		this.tdId = tdId;
	}

	@Column(name = "stt_ID")
	public Integer getSttId() {
		return this.sttId;
	}

	public void setSttId(Integer sttId) {
		this.sttId = sttId;
	}

	@Column(name = "cla_ID")
	public Integer getClaId() {
		return this.claId;
	}

	public void setClaId(Integer claId) {
		this.claId = claId;
	}

	@Column(name = "gA_ID")
	public Integer getGaId() {
		return this.gaId;
	}

	public void setGaId(Integer gaId) {
		this.gaId = gaId;
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

}