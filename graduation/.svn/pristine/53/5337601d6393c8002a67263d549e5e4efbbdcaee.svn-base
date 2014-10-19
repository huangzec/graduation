package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Topicreport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topicreport", catalog = "graduation")
public class Topicreport implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stuId;
	private String departmentId;
	private String meaning;
	private String content;
	private String research;
	private String deadline;
	private String referencesl;
	private String teacherView;
	private String meeting;
	private String judgeView;
	private String departmentView;
	private String createTime;

	// Constructors

	/** default constructor */
	public Topicreport() {
	}

	/** minimal constructor */
	public Topicreport(String departmentId, String createTime) {
		this.departmentId = departmentId;
		this.createTime = createTime;
	}

	/** full constructor */
	public Topicreport(String stuId, String departmentId, String meaning,
			String content, String research, String deadline,
			String referencesl, String teacherView, String meeting,
			String judgeView, String departmentView, String createTime) {
		this.stuId = stuId;
		this.departmentId = departmentId;
		this.meaning = meaning;
		this.content = content;
		this.research = research;
		this.deadline = deadline;
		this.referencesl = referencesl;
		this.teacherView = teacherView;
		this.meeting = meeting;
		this.judgeView = judgeView;
		this.departmentView = departmentView;
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

	@Column(name = "stu_id")
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

	@Column(name = "meaning", length = 65535)
	public String getMeaning() {
		return this.meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "research", length = 65535)
	public String getResearch() {
		return this.research;
	}

	public void setResearch(String research) {
		this.research = research;
	}

	@Column(name = "deadline", length = 65535)
	public String getDeadline() {
		return this.deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	@Column(name = "referencesl", length = 65535)
	public String getReferencesl() {
		return this.referencesl;
	}

	public void setReferencesl(String referencesl) {
		this.referencesl = referencesl;
	}

	@Column(name = "teacher_view", length = 65535)
	public String getTeacherView() {
		return this.teacherView;
	}

	public void setTeacherView(String teacherView) {
		this.teacherView = teacherView;
	}

	@Column(name = "meeting")
	public String getMeeting() {
		return this.meeting;
	}

	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}

	@Column(name = "judge_view", length = 65535)
	public String getJudgeView() {
		return this.judgeView;
	}

	public void setJudgeView(String judgeView) {
		this.judgeView = judgeView;
	}

	@Column(name = "department_view", length = 65535)
	public String getDepartmentView() {
		return this.departmentView;
	}

	public void setDepartmentView(String departmentView) {
		this.departmentView = departmentView;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}