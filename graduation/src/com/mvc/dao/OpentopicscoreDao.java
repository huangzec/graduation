package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Opentopicscore;

@Repository
public class OpentopicscoreDao extends BaseDao<Opentopicscore> {

	/**
	 * 带返回值的添加方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param ops
	 * @return
	 */
	public int saveReturn(Opentopicscore ops) {
		this.getHibernateTemplate().save(ops);
		
		return ops.getId();
	}

}
