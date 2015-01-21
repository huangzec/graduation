package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tbtopic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbtopic")
public class Tbtopic implements java.io.Serializable {

	// Fields

	private String topId;
	private String topCommitId;
	private String topName;
	private Integer topNumber;
	private String topStatus;
	private String topContent;
	private String topFlag;
	private String topType;
	private String topSource;
	private String topKeywords;
	private String topYear;
	private String deptId;
	private String parentId;
	private String completerId;

	// Constructors

	/** default constructor */
	public Tbtopic() {
	}

	/** minimal constructor */
	public Tbtopic(String topId, String topName, String topType, String parentId) {
		this.topId = topId;
		this.topName = topName;
		this.topType = topType;
		this.parentId = parentId;
	}

	/** full constructor */
	public Tbtopic(String topId, String topCommitId, String topName, Integer topNumber, String topStatus, String topContent, String topFlag,
			String topType, String topSource, String topKeywords, String topYear, String deptId, String parentId, String completerId) {
		this.topId = topId;
		this.topCommitId = topCommitId;
		this.topName = topName;
		this.topNumber = topNumber;
		this.topStatus = topStatus;
		this.topContent = topContent;
		this.topFlag = topFlag;
		this.topType = topType;
		this.topSource = topSource;
		this.topKeywords = topKeywords;
		this.topYear = topYear;
		this.deptId = deptId;
		this.parentId = parentId;
		this.completerId = completerId;
	}

	// Property accessors
	@Id
	@Column(name = "top_ID", unique = true, nullable = false)
	public String getTopId() {
		return this.topId;
	}

	public void setTopId(String topId) {
		this.topId = topId;
	}

	@Column(name = "top_commitID", length = 12)
	public String getTopCommitId() {
		return this.topCommitId;
	}

	public void setTopCommitId(String topCommitId) {
		this.topCommitId = topCommitId;
	}

	@Column(name = "top_Name", nullable = false, length = 100)
	public String getTopName() {
		return this.topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	@Column(name = "top_Number")
	public Integer getTopNumber() {
		return this.topNumber;
	}

	public void setTopNumber(Integer topNumber) {
		this.topNumber = topNumber;
	}

	@Column(name = "top_Status")
	public String getTopStatus() {
		return this.topStatus;
	}

	public void setTopStatus(String topStatus) {
		this.topStatus = topStatus;
	}

	@Column(name = "top_Content", length = 65535)
	public String getTopContent() {
		return this.topContent;
	}

	public void setTopContent(String topContent) {
		this.topContent = topContent;
	}

	@Column(name = "top_Flag", length = 10)
	public String getTopFlag() {
		return this.topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	@Column(name = "top_Type", nullable = false, length = 10)
	public String getTopType() {
		return this.topType;
	}

	public void setTopType(String topType) {
		this.topType = topType;
	}

	@Column(name = "top_Source")
	public String getTopSource() {
		return this.topSource;
	}

	public void setTopSource(String topSource) {
		this.topSource = topSource;
	}

	@Column(name = "top_Keywords")
	public String getTopKeywords() {
		return this.topKeywords;
	}

	public void setTopKeywords(String topKeywords) {
		this.topKeywords = topKeywords;
	}

	@Column(name = "top_Year")
	public String getTopYear() {
		return this.topYear;
	}

	public void setTopYear(String topYear) {
		this.topYear = topYear;
	}

	@Column(name = "dept_ID")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "parent_ID", nullable = false)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "completer_ID", length = 12)
	public String getCompleterId() {
		return this.completerId;
	}

	public void setCompleterId(String completerId) {
		this.completerId = completerId;
	}

}