package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.LinkeddataApplyTopicapply;

@Repository
public class LinkeddataApplyTopicapplyDao extends BaseDao<LinkeddataApplyTopicapply> {

	/**
	 * 添加一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicapply
	 * @return
	 */
	public int saveReturn(LinkeddataApplyTopicapply linkeddataApplyTopicapply) {
		try {
			this.getHibernateTemplate().save(linkeddataApplyTopicapply);
			
			return linkeddataApplyTopicapply.getId();
		} catch (Exception e) {
			return 0;
		}
	}

}
