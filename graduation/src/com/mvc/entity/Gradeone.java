package com.mvc.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gradeone entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gradeone", catalog = "graduation")
public class Gradeone implements java.io.Serializable {

	// Fields

	private Integer goId;
	private String stuId;
	private Float goOne;
	private Float goTwo;
	private Float goThree;
	private Float goFour;
	private Float goFive;
	private Float goAll;
	private String content;
	private String createTime;
	private String status;

	// Constructors

	/** default constructor */
	public Gradeone() {
	}

	/** minimal constructor */
	public Gradeone(Integer goId, String createTime, String status) {
		this.goId = goId;
		this.createTime = createTime;
		this.status = status;
	}

	/** full constructor */
	public Gradeone(Integer goId, String stuId, Float goOne, Float goTwo, Float goThree, Float goFour, Float goFive, Float goAll, String content,
			String createTime, String status) {
		this.goId = goId;
		this.stuId = stuId;
		this.goOne = goOne;
		this.goTwo = goTwo;
		this.goThree = goThree;
		this.goFour = goFour;
		this.goFive = goFive;
		this.goAll = goAll;
		this.content = content;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "go_ID", unique = true, nullable = false)
	public Integer getGoId() {
		return this.goId;
	}

	public void setGoId(Integer goId) {
		this.goId = goId;
	}

	@Column(name = "stu_ID", length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "go_One", precision = 12, scale = 0)
	public Float getGoOne() {
		return this.goOne;
	}

	public void setGoOne(Float goOne) {
		this.goOne = goOne;
	}

	@Column(name = "go_Two", precision = 12, scale = 0)
	public Float getGoTwo() {
		return this.goTwo;
	}

	public void setGoTwo(Float goTwo) {
		this.goTwo = goTwo;
	}

	@Column(name = "go_Three", precision = 12, scale = 0)
	public Float getGoThree() {
		return this.goThree;
	}

	public void setGoThree(Float goThree) {
		this.goThree = goThree;
	}

	@Column(name = "go_Four", precision = 12, scale = 0)
	public Float getGoFour() {
		return this.goFour;
	}

	public void setGoFour(Float goFour) {
		this.goFour = goFour;
	}

	@Column(name = "go_Five", precision = 12, scale = 0)
	public Float getGoFive() {
		return this.goFive;
	}

	public void setGoFive(Float goFive) {
		this.goFive = goFive;
	}

	@Column(name = "go_All", precision = 12, scale = 0)
	public Float getGoAll() {
		return this.goAll;
	}

	public void setGoAll(Float goAll) {
		this.goAll = goAll;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}