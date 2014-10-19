package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Meeting;

@Repository
public class MeetingDao extends BaseDao<Meeting> {

	/**
	 * 添加一条记录，并返回其id
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午08:02:04
	 * @return int
	 */
	public int addRecord(Meeting meeting) {
		try {
			this.getHibernateTemplate().save(meeting);

			return meeting.getId();
		} catch (Exception e) {
			return 0;
		}
		
		
	}

	/**
	 * 添加一条记录，并返回其id
	 * 如抛出异常，则交给上一级处理
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param meeting
	 * @return
	 */
	public int saveReturn(Meeting meeting) {
		this.getHibernateTemplate().save(meeting);
		
		return meeting.getId();
	}

}
