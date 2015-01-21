package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Settime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "settime")
public class Settime implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String deptId;
	private String graNumber;
	private String startTime;
	private String endTime;
	private String mark;

	// Constructors

	/** default constructor */
	public Settime() {
	}

	/** full constructor */
	public Settime(String name, String deptId, String graNumber, String startTime, String endTime, String mark) {
		this.name = name;
		this.deptId = deptId;
		this.graNumber = graNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.mark = mark;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "dept_ID")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "gra_number")
	public String getGraNumber() {
		return this.graNumber;
	}

	public void setGraNumber(String graNumber) {
		this.graNumber = graNumber;
	}

	@Column(name = "start_time")
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time")
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "mark")
	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}