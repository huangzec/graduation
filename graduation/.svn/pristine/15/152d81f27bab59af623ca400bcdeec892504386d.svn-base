package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LinkeddataApplyTopicfinish entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "linkeddata_apply_topicscore", catalog = "graduation")
public class LinkeddataApplyTopicscore implements java.io.Serializable {

	// Fields

	private Integer id;
	private String itemId;
	private String relId;
	private String extend;
	private String createTime;

	// Constructors

	/** default constructor */
	public LinkeddataApplyTopicscore() {
	}

	/** full constructor */
	public LinkeddataApplyTopicscore(String itemId, String relId, String extend, String createTime) {
		this.itemId = itemId;
		this.relId = relId;
		this.extend = extend;
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

	@Column(name = "item_id", nullable = false)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name = "rel_id", nullable = false)
	public String getRelId() {
		return this.relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	@Column(name = "extend", nullable = false)
	public String getExtend() {
		return this.extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	@Column(name = "create_time", nullable = false)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}