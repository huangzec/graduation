package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StuTeaTopic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stu_tea_topic")
public class StuTeaTopic implements java.io.Serializable {

	// Fields

	private Integer sttId;
	private String stuId;
	private String teaId;
	private String teaTeaId;
	private Boolean sttStatus;

	// Constructors

	/** default constructor */
	public StuTeaTopic() {
	}

	/** full constructor */
	public StuTeaTopic(String stuId, String teaId, String teaTeaId,
			Boolean sttStatus) {
		this.stuId = stuId;
		this.teaId = teaId;
		this.teaTeaId = teaTeaId;
		this.sttStatus = sttStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "stt_ID", unique = true, nullable = false)
	public Integer getSttId() {
		return this.sttId;
	}

	public void setSttId(Integer sttId) {
		this.sttId = sttId;
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

	@Column(name = "Tea_tea_ID", length = 12)
	public String getTeaTeaId() {
		return this.teaTeaId;
	}

	public void setTeaTeaId(String teaTeaId) {
		this.teaTeaId = teaTeaId;
	}

	@Column(name = "stt_Status")
	public Boolean getSttStatus() {
		return this.sttStatus;
	}

	public void setSttStatus(Boolean sttStatus) {
		this.sttStatus = sttStatus;
	}

}