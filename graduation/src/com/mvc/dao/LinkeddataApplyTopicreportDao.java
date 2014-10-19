package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.LinkeddataApplyTopicreport;

@Repository
public class LinkeddataApplyTopicreportDao extends BaseDao<LinkeddataApplyTopicreport> {

	/**
	 * 添加记录，带返回结果
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicreport
	 * @return
	 */
	public int saveReturn(LinkeddataApplyTopicreport linkeddataApplyTopicreport) {
		try {
			this.getHibernateTemplate().save(linkeddataApplyTopicreport);
			
			return linkeddataApplyTopicreport.getId();
		} catch (Exception e) {
			return 0;
		}
	}

}
