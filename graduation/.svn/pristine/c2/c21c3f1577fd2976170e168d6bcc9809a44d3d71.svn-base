package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Topicreport;

@Repository
public class TopicreportDao extends BaseDao<Topicreport> {

	/**
	 * 保存一条记录，返回ID
	 * @param topicreport
	 * @return
	 */
	public int addOneReturn(Topicreport topicreport) {
		try {
			this.getHibernateTemplate().save(topicreport);
			
			return topicreport.getId();
		} catch (Exception e) {
			System.out.println("info ----- > " + e.getMessage());
			
			return 0;
		}
	}

}
