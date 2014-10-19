package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Topicfinish entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topicfinish", catalog = "graduation")
public class Topicfinish implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stuId;
	private String departmentId;
	private String title;
	private String content;
	private String createTime;

	// Constructors

	/** default constructor */
	public Topicfinish() {
	}

	/** minimal constructor */
	public Topicfinish(String departmentId, String createTime) {
		this.departmentId = departmentId;
		this.createTime = createTime;
	}

	/** full constructor */
	public Topicfinish(String stuId, String departmentId, String title,
			String content, String createTime) {
		this.stuId = stuId;
		this.departmentId = departmentId;
		this.title = title;
		this.content = content;
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

	@Column(name = "stu_id", length = 12)
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

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
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

}