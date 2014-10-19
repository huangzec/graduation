package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Taskdoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "taskdoc", catalog = "graduation")
public class Taskdoc implements java.io.Serializable {

	// Fields

	private Integer tdId;
	private String title;
	private String stuId;
	private String teaId;
	private String source;
	private String tdConRequest;
	private String tdDevTools;
	private String tdRefMaterial;
	private String tdWorkPlane;
	private String receiptTime;
	private String finishTime;
	private String createTime;

	// Constructors

	/** default constructor */
	public Taskdoc() {
	}

	/** minimal constructor */
	public Taskdoc(String createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public Taskdoc(String title, String stuId, String teaId, String source,
			String tdConRequest, String tdDevTools, String tdRefMaterial,
			String tdWorkPlane, String receiptTime, String finishTime,
			String createTime) {
		this.title = title;
		this.stuId = stuId;
		this.teaId = teaId;
		this.source = source;
		this.tdConRequest = tdConRequest;
		this.tdDevTools = tdDevTools;
		this.tdRefMaterial = tdRefMaterial;
		this.tdWorkPlane = tdWorkPlane;
		this.receiptTime = receiptTime;
		this.finishTime = finishTime;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tD_ID", unique = true, nullable = false)
	public Integer getTdId() {
		return this.tdId;
	}

	public void setTdId(Integer tdId) {
		this.tdId = tdId;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "stu_ID", length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "tea_ID", length = 12)
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	@Column(name = "source")
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "tD_conRequest", length = 65535)
	public String getTdConRequest() {
		return this.tdConRequest;
	}

	public void setTdConRequest(String tdConRequest) {
		this.tdConRequest = tdConRequest;
	}

	@Column(name = "tD_devTools", length = 65535)
	public String getTdDevTools() {
		return this.tdDevTools;
	}

	public void setTdDevTools(String tdDevTools) {
		this.tdDevTools = tdDevTools;
	}

	@Column(name = "tD_refMaterial", length = 65535)
	public String getTdRefMaterial() {
		return this.tdRefMaterial;
	}

	public void setTdRefMaterial(String tdRefMaterial) {
		this.tdRefMaterial = tdRefMaterial;
	}

	@Column(name = "tD_workPlane", length = 65535)
	public String getTdWorkPlane() {
		return this.tdWorkPlane;
	}

	public void setTdWorkPlane(String tdWorkPlane) {
		this.tdWorkPlane = tdWorkPlane;
	}

	@Column(name = "receipt_time")
	public String getReceiptTime() {
		return this.receiptTime;
	}

	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}

	@Column(name = "finish_time")
	public String getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}