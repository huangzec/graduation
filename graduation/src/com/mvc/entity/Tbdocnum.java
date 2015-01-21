package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tbdocnum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbdocnum")
public class Tbdocnum implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer docnum;
	private String numstate;
	private String stuId;

	// Constructors

	/** default constructor */
	public Tbdocnum() {
	}

	/** full constructor */
	public Tbdocnum(Integer docnum, String numstate, String stuId) {
		this.docnum = docnum;
		this.numstate = numstate;
		this.stuId = stuId;
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

	@Column(name = "docnum")
	public Integer getDocnum() {
		return this.docnum;
	}

	public void setDocnum(Integer docnum) {
		this.docnum = docnum;
	}

	@Column(name = "numstate", length = 20)
	public String getNumstate() {
		return this.numstate;
	}

	public void setNumstate(String numstate) {
		this.numstate = numstate;
	}

	@Column(name = "stu_ID", length = 12)
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

}