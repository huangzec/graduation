package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gradethree entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gradethree")
public class Gradethree implements java.io.Serializable {

	// Fields

	private Integer gtrId;
	private String stuId;
	private Float gtrOne;
	private Float gtrTwo;
	private Float gtrThree;
	private Float gtrFour;
	private Float gtrFive;
	private Float gtrSix;
	private Float gtrAll;
	private String content;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Gradethree() {
	}

	/** minimal constructor */
	public Gradethree(String status, String createTime) {
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public Gradethree(String stuId, Float gtrOne, Float gtrTwo, Float gtrThree,
			Float gtrFour, Float gtrFive, Float gtrSix, Float gtrAll,
			String content, String status, String createTime) {
		this.stuId = stuId;
		this.gtrOne = gtrOne;
		this.gtrTwo = gtrTwo;
		this.gtrThree = gtrThree;
		this.gtrFour = gtrFour;
		this.gtrFive = gtrFive;
		this.gtrSix = gtrSix;
		this.gtrAll = gtrAll;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gtr_ID", unique = true, nullable = false)
	public Integer getGtrId() {
		return this.gtrId;
	}

	public void setGtrId(Integer gtrId) {
		this.gtrId = gtrId;
	}

	@Column(name = "stu_ID", length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "gtr_One", precision = 12, scale = 0)
	public Float getGtrOne() {
		return this.gtrOne;
	}

	public void setGtrOne(Float gtrOne) {
		this.gtrOne = gtrOne;
	}

	@Column(name = "gtr_Two", precision = 12, scale = 0)
	public Float getGtrTwo() {
		return this.gtrTwo;
	}

	public void setGtrTwo(Float gtrTwo) {
		this.gtrTwo = gtrTwo;
	}

	@Column(name = "gtr_Three", precision = 12, scale = 0)
	public Float getGtrThree() {
		return this.gtrThree;
	}

	public void setGtrThree(Float gtrThree) {
		this.gtrThree = gtrThree;
	}

	@Column(name = "gtr_Four", precision = 12, scale = 0)
	public Float getGtrFour() {
		return this.gtrFour;
	}

	public void setGtrFour(Float gtrFour) {
		this.gtrFour = gtrFour;
	}

	@Column(name = "gtr_Five", precision = 12, scale = 0)
	public Float getGtrFive() {
		return this.gtrFive;
	}

	public void setGtrFive(Float gtrFive) {
		this.gtrFive = gtrFive;
	}

	@Column(name = "gtr_Six", precision = 12, scale = 0)
	public Float getGtrSix() {
		return this.gtrSix;
	}

	public void setGtrSix(Float gtrSix) {
		this.gtrSix = gtrSix;
	}

	@Column(name = "gtr_All", precision = 12, scale = 0)
	public Float getGtrAll() {
		return this.gtrAll;
	}

	public void setGtrAll(Float gtrAll) {
		this.gtrAll = gtrAll;
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