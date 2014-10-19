package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Topicfinish;

@Repository
public class TopicfinishDao extends BaseDao<Topicfinish> {

	/**
	 * 保存一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param topicfinish
	 * @return
	 */
	public int saveReturn(Topicfinish topicfinish) {
		try {
			this.getHibernateTemplate().save(topicfinish);
			
			return topicfinish.getId();
		} catch (Exception e) {
			return 0;
		}
	}

}
