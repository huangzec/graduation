package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Topicapply;

@Repository
public class TopicapplyDao extends BaseDao<Topicapply> {

	/**
	 * 保存一条记录，返回ID
	 * @param topicapply
	 * @return
	 */
	public int addOneReturn(Topicapply topicapply) {
		try {
			this.getHibernateTemplate().save(topicapply);
			
			return topicapply.getId();
		} catch (Exception e) {
			return 0;
		}
	}

}
