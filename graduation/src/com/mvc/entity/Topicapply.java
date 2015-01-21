package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Topicapply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topicapply")
public class Topicapply implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String topicId;
	private String parentId;
	private String openTime;
	private String knowing;
	private String teacherIdea;
	private String paperIdea;
	private String departmentIdea;
	private String createTime;

	// Constructors

	/** default constructor */
	public Topicapply() {
	}

	/** minimal constructor */
	public Topicapply(String userId, String topicId, String parentId, String openTime, String createTime) {
		this.userId = userId;
		this.topicId = topicId;
		this.parentId = parentId;
		this.openTime = openTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public Topicapply(String userId, String topicId, String parentId, String openTime, String knowing, String teacherIdea, String paperIdea,
			String departmentIdea, String createTime) {
		this.userId = userId;
		this.topicId = topicId;
		this.parentId = parentId;
		this.openTime = openTime;
		this.knowing = knowing;
		this.teacherIdea = teacherIdea;
		this.paperIdea = paperIdea;
		this.departmentIdea = departmentIdea;
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

	@Column(name = "user_id", nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "topic_id", nullable = false)
	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Column(name = "parent_id", nullable = false)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "open_time", nullable = false)
	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "knowing", length = 65535)
	public String getKnowing() {
		return this.knowing;
	}

	public void setKnowing(String knowing) {
		this.knowing = knowing;
	}

	@Column(name = "teacher_idea", length = 65535)
	public String getTeacherIdea() {
		return this.teacherIdea;
	}

	public void setTeacherIdea(String teacherIdea) {
		this.teacherIdea = teacherIdea;
	}

	@Column(name = "paper_idea", length = 65535)
	public String getPaperIdea() {
		return this.paperIdea;
	}

	public void setPaperIdea(String paperIdea) {
		this.paperIdea = paperIdea;
	}

	@Column(name = "department_idea", length = 65535)
	public String getDepartmentIdea() {
		return this.departmentIdea;
	}

	public void setDepartmentIdea(String departmentIdea) {
		this.departmentIdea = departmentIdea;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}