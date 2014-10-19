package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Opentopicinfo;

@Repository
public class OpentopicinfoDao extends BaseDao<Opentopicinfo> {

	/**
	 * 添加一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param opentopicinfo
	 * @return
	 */
	public int saveReturn(Opentopicinfo opentopicinfo) {
		try {
			this.getHibernateTemplate().save(opentopicinfo);
			
			return opentopicinfo.getOpiId();
		} catch (Exception e) {
			e.getMessage();
			
			return 0;
		}
	}

}
