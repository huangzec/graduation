package com.mvc.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tbtopic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbtopic", catalog = "graduation")
public class Tbtopic implements java.io.Serializable {

	// Fields

	private String topId;
	private String topCommitId;
	private String topName;
	private Integer topNumber;
	private String topStatus;
	private String topTec;
	private String topFlag;
	private String deptId;
	private String parentId;
	private String completerId;
	private Set<Selectfirst> selectfirsts = new HashSet<Selectfirst>(0);

	// Constructors

	/** default constructor */
	public Tbtopic() {
	}

	/** minimal constructor */
	public Tbtopic(String topId, String topName) {
		this.topId = topId;
		this.topName = topName;
	}

	/** full constructor */
	public Tbtopic(String topId, String topCommitId, String topName,
			Integer topNumber, String topStatus, String topTec, String topFlag,
			String deptId, String parentId, String completerId,
			Set<Selectfirst> selectfirsts) {
		this.topId = topId;
		this.topCommitId = topCommitId;
		this.topName = topName;
		this.topNumber = topNumber;
		this.topStatus = topStatus;
		this.topTec = topTec;
		this.topFlag = topFlag;
		this.deptId = deptId;
		this.parentId = parentId;
		this.completerId = completerId;
		this.selectfirsts = selectfirsts;
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

	@Column(name = "top_Tec", length = 65535)
	public String getTopTec() {
		return this.topTec;
	}

	public void setTopTec(String topTec) {
		this.topTec = topTec;
	}

	@Column(name = "top_Flag", length = 10)
	public String getTopFlag() {
		return this.topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	@Column(name = "dept_ID")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "parent_ID")
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbtopic")
	public Set<Selectfirst> getSelectfirsts() {
		return this.selectfirsts;
	}

	public void setSelectfirsts(Set<Selectfirst> selectfirsts) {
		this.selectfirsts = selectfirsts;
	}

}