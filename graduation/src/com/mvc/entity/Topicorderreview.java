package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Topicorderreview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topicorderreview")
public class Topicorderreview implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String departmentId;
	private String judgeTea;
	private String judgeYear;
	private String createTime;

	// Constructors

	/** default constructor */
	public Topicorderreview() {
	}

	/** minimal constructor */
	public Topicorderreview(String departmentId, String judgeTea,
			String judgeYear, String createTime) {
		this.departmentId = departmentId;
		this.judgeTea = judgeTea;
		this.judgeYear = judgeYear;
		this.createTime = createTime;
	}

	/** full constructor */
	public Topicorderreview(String name, String departmentId, String judgeTea,
			String judgeYear, String createTime) {
		this.name = name;
		this.departmentId = departmentId;
		this.judgeTea = judgeTea;
		this.judgeYear = judgeYear;
		this.createTime = createTime;
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

	@Column(name = "department_id", nullable = false)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "judge_tea", nullable = false, length = 65535)
	public String getJudgeTea() {
		return this.judgeTea;
	}

	public void setJudgeTea(String judgeTea) {
		this.judgeTea = judgeTea;
	}

	@Column(name = "judge_year", nullable = false)
	public String getJudgeYear() {
		return this.judgeYear;
	}

	public void setJudgeYear(String judgeYear) {
		this.judgeYear = judgeYear;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}