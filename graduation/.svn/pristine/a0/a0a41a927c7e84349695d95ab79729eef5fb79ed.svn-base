package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.LinkeddataApplyTopicfinish;

@Repository
public class LinkeddataApplyTopicfinishDao extends BaseDao<LinkeddataApplyTopicfinish> {

	/**
	 * 添加一条记录，返回结果
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicfinish
	 * @return
	 */
	public int saveReturn(LinkeddataApplyTopicfinish linkeddataApplyTopicfinish) {
		try {
			this.getHibernateTemplate().save(linkeddataApplyTopicfinish);
			
			return linkeddataApplyTopicfinish.getId();
		} catch (Exception e) {
			return 0;
		}
	}

}
