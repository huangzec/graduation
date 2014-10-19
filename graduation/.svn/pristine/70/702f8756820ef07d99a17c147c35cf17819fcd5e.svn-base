package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Apply;

@Repository
public class ApplyDao extends BaseDao<Apply> {
	
	/**
	 * 添加一条记录，返回id
	 * @param apply
	 * @return
	 */
	public int saveReturn(Apply apply)
	{
		try {
			this.getHibernateTemplate().save(apply);
			
			return apply.getId();
		} catch (Exception e) {
			return 0;
		}
	}

}
