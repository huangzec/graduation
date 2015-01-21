package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Opentopicinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "opentopicinfo")
public class Opentopicinfo implements java.io.Serializable {

	// Fields

	private Integer opiId;
	private String name;
	private String stuId;
	private String departmentId;
	private String opiDate;
	private String opiPlace;
	private String opiNumber;
	private String judge;
	private String headerman;
	private String begroup;
	private String content;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Opentopicinfo() {
	}

	/** minimal constructor */
	public Opentopicinfo(String departmentId, String judge, String headerman,
			String status, String createTime) {
		this.departmentId = departmentId;
		this.judge = judge;
		this.headerman = headerman;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public Opentopicinfo(String name, String stuId, String departmentId,
			String opiDate, String opiPlace, String opiNumber, String judge,
			String headerman, String begroup, String content, String status,
			String createTime) {
		this.name = name;
		this.stuId = stuId;
		this.departmentId = departmentId;
		this.opiDate = opiDate;
		this.opiPlace = opiPlace;
		this.opiNumber = opiNumber;
		this.judge = judge;
		this.headerman = headerman;
		this.begroup = begroup;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "opi_ID", unique = true, nullable = false)
	public Integer getOpiId() {
		return this.opiId;
	}

	public void setOpiId(Integer opiId) {
		this.opiId = opiId;
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

	@Column(name = "opi_Date")
	public String getOpiDate() {
		return this.opiDate;
	}

	public void setOpiDate(String opiDate) {
		this.opiDate = opiDate;
	}

	@Column(name = "opi_Place", length = 20)
	public String getOpiPlace() {
		return this.opiPlace;
	}

	public void setOpiPlace(String opiPlace) {
		this.opiPlace = opiPlace;
	}

	@Column(name = "opi_Number")
	public String getOpiNumber() {
		return this.opiNumber;
	}

	public void setOpiNumber(String opiNumber) {
		this.opiNumber = opiNumber;
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

	@Column(name = "begroup")
	public String getBegroup() {
		return this.begroup;
	}

	public void setBegroup(String begroup) {
		this.begroup = begroup;
	}

	@Column(name = "content", length = 65535)
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