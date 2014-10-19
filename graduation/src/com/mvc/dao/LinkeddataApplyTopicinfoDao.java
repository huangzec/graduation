package com.mvc.dao;

import org.springframework.stereotype.Repository;

import antlr.collections.impl.LList;

import com.mvc.entity.LinkeddataApplyTopicinfo;

@Repository
public class LinkeddataApplyTopicinfoDao extends BaseDao<LinkeddataApplyTopicinfo> {

	/**
	 * 添加一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicinfo
	 * @return
	 */
	public int saveReturn(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) {
		try {
			this.getHibernateTemplate().save(linkeddataApplyTopicinfo);
			
			return linkeddataApplyTopicinfo.getId();
		} catch (Exception e) {
			e.getMessage();
			
			return 0;
		}
	}

}
