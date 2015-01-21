package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tballdoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tballdoc")
public class Tballdoc implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stuId;
	private String deptId;
	private String docTitle;
	private String docContent;
	private String filePath;
	private String createTime;
	private String docState;

	// Constructors

	/** default constructor */
	public Tballdoc() {
	}

	/** minimal constructor */
	public Tballdoc(String stuId, String deptId, String createTime) {
		this.stuId = stuId;
		this.deptId = deptId;
		this.createTime = createTime;
	}

	/** full constructor */
	public Tballdoc(String stuId, String deptId, String docTitle, String docContent, String filePath, String createTime, String docState) {
		this.stuId = stuId;
		this.deptId = deptId;
		this.docTitle = docTitle;
		this.docContent = docContent;
		this.filePath = filePath;
		this.createTime = createTime;
		this.docState = docState;
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

	@Column(name = "stu_ID", nullable = false, length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "dept_ID", nullable = false, length = 10)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "doc_Title")
	public String getDocTitle() {
		return this.docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	@Column(name = "doc_Content")
	public String getDocContent() {
		return this.docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	@Column(name = "file_Path")
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "doc_state", length = 20)
	public String getDocState() {
		return this.docState;
	}

	public void setDocState(String docState) {
		this.docState = docState;
	}

}