package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gradetwo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gradetwo")
public class Gradetwo implements java.io.Serializable {

	// Fields

	private Integer gtId;
	private String stuId;
	private Float gtOne;
	private Float gtTwo;
	private Float gtThree;
	private Float gtFour;
	private Float gtFive;
	private Float gtSix;
	private Float gtAll;
	private String content;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Gradetwo() {
	}

	/** minimal constructor */
	public Gradetwo(String status, String createTime) {
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public Gradetwo(String stuId, Float gtOne, Float gtTwo, Float gtThree, Float gtFour, Float gtFive, Float gtSix, Float gtAll, String content, String status,
			String createTime) {
		this.stuId = stuId;
		this.gtOne = gtOne;
		this.gtTwo = gtTwo;
		this.gtThree = gtThree;
		this.gtFour = gtFour;
		this.gtFive = gtFive;
		this.gtSix = gtSix;
		this.gtAll = gtAll;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gt_ID", unique = true, nullable = false)
	public Integer getGtId() {
		return this.gtId;
	}

	public void setGtId(Integer gtId) {
		this.gtId = gtId;
	}

	@Column(name = "stu_ID", length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "gt_One", precision = 12, scale = 0)
	public Float getGtOne() {
		return this.gtOne;
	}

	public void setGtOne(Float gtOne) {
		this.gtOne = gtOne;
	}

	@Column(name = "gt_Two", precision = 12, scale = 0)
	public Float getGtTwo() {
		return this.gtTwo;
	}

	public void setGtTwo(Float gtTwo) {
		this.gtTwo = gtTwo;
	}

	@Column(name = "gt_Three", precision = 12, scale = 0)
	public Float getGtThree() {
		return this.gtThree;
	}

	public void setGtThree(Float gtThree) {
		this.gtThree = gtThree;
	}

	@Column(name = "gt_Four", precision = 12, scale = 0)
	public Float getGtFour() {
		return this.gtFour;
	}

	public void setGtFour(Float gtFour) {
		this.gtFour = gtFour;
	}

	@Column(name = "gt_Five", precision = 12, scale = 0)
	public Float getGtFive() {
		return this.gtFive;
	}

	public void setGtFive(Float gtFive) {
		this.gtFive = gtFive;
	}
	
	@Column(name = "gt_Six", precision = 12, scale = 0)
	public Float getGtSix() {
		return this.gtSix;
	}

	public void setGtSix(Float gtSix) {
		this.gtSix = gtSix;
	}

	@Column(name = "gt_All", precision = 12, scale = 0)
	public Float getGtAll() {
		return this.gtAll;
	}

	public void setGtAll(Float gtAll) {
		this.gtAll = gtAll;
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