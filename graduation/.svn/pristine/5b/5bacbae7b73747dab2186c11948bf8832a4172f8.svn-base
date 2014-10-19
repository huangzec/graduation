package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Graduateinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "graduateinfo", catalog = "graduation")
public class Graduateinfo implements java.io.Serializable {

	// Fields

	private Integer gdiId;
	private String name;
	private String stuId;
	private String departmentId;
	private String gdiDate;
	private String gdiPlace;
	private Integer gdiNumber;
	private String judge;
	private String headerman;
	private String content;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Graduateinfo() {
	}

	/** minimal constructor */
	public Graduateinfo(String departmentId, String judge, String headerman,
			String status, String createTime) {
		this.departmentId = departmentId;
		this.judge = judge;
		this.headerman = headerman;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public Graduateinfo(String name, String stuId, String departmentId,
			String gdiDate, String gdiPlace, Integer gdiNumber, String judge,
			String headerman, String content, String status, String createTime) {
		this.name = name;
		this.stuId = stuId;
		this.departmentId = departmentId;
		this.gdiDate = gdiDate;
		this.gdiPlace = gdiPlace;
		this.gdiNumber = gdiNumber;
		this.judge = judge;
		this.headerman = headerman;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gdi_ID", unique = true, nullable = false)
	public Integer getGdiId() {
		return this.gdiId;
	}

	public void setGdiId(Integer gdiId) {
		this.gdiId = gdiId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "gdi_Date")
	public String getGdiDate() {
		return this.gdiDate;
	}

	public void setGdiDate(String gdiDate) {
		this.gdiDate = gdiDate;
	}

	@Column(name = "gdi_Place", length = 20)
	public String getGdiPlace() {
		return this.gdiPlace;
	}

	public void setGdiPlace(String gdiPlace) {
		this.gdiPlace = gdiPlace;
	}

	@Column(name = "gdi_Number")
	public Integer getGdiNumber() {
		return this.gdiNumber;
	}

	public void setGdiNumber(Integer gdiNumber) {
		this.gdiNumber = gdiNumber;
	}

	@Column(name = "judge", nullable = false)
	public String getJudge() {
		return this.judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	@Column(name = "headerman", nullable = false)
	public String getHeaderman() {
		return this.headerman;
	}

	public void setHeaderman(String headerman) {
		this.headerman = headerman;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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