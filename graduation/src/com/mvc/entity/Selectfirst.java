package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Selectfirst entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "selectfirst")
public class Selectfirst implements java.io.Serializable {

	// Fields

	private Integer selId;
	private String stuId;
	private String selKnowing;
	private String selStatus;
	private String teaId;
	private String deptId;

	// Constructors

	/** default constructor */
	public Selectfirst() {
	}

	/** full constructor */
	public Selectfirst(String stuId, String selKnowing,
			String selStatus, String teaId, String deptId) {
		this.stuId = stuId;
		this.selKnowing = selKnowing;
		this.selStatus = selStatus;
		this.teaId = teaId;
		this.deptId = deptId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sel_ID", unique = true, nullable = false)
	public Integer getSelId() {
		return this.selId;
	}

	public void setSelId(Integer selId) {
		this.selId = selId;
	}

	@Column(name = "stu_ID", nullable = false, length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "sel_knowing", length = 65535)
	public String getSelKnowing() {
		return this.selKnowing;
	}

	public void setSelKnowing(String selKnowing) {
		this.selKnowing = selKnowing;
	}

	@Column(name = "sel_status", length = 10)
	public String getSelStatus() {
		return this.selStatus;
	}

	public void setSelStatus(String selStatus) {
		this.selStatus = selStatus;
	}

	@Column(name = "tea_ID", length = 12)
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}
	
	@Column(name = "dept_ID", length = 10)
	public String getDeptId(){
		return this.deptId;
	}
	
	public void setDeptId(String deptId){
		this.deptId = deptId;
	}
	
	private Tbtopic tbtopic;

	@ManyToOne
	@JoinColumn(name="top_ID")
	public Tbtopic getTbtopic() {
		return tbtopic;
	}

	public void setTbtopic(Tbtopic tbtopic) {
		this.tbtopic = tbtopic;
	}
}