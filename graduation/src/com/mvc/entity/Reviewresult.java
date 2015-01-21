package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reviewresult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reviewresult")
public class Reviewresult implements java.io.Serializable {

	// Fields

	private Integer id;
	private String topId;
	private String judgeId;
	private String status;

	// Constructors

	/** default constructor */
	public Reviewresult() {
	}

	/** full constructor */
	public Reviewresult(String topId, String judgeId, String status) {
		this.topId = topId;
		this.judgeId = judgeId;
		this.status = status;
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

	@Column(name = "top_ID", nullable = false)
	public String getTopId() {
		return this.topId;
	}

	public void setTopId(String topId) {
		this.topId = topId;
	}

	@Column(name = "judge_ID", nullable = false)
	public String getJudgeId() {
		return this.judgeId;
	}

	public void setJudgeId(String judgeId) {
		this.judgeId = judgeId;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}