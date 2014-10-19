package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Gradeall;

@Repository
public class GradeallDao extends BaseDao<Gradeall> {

	/**
	 * 带返回值的添加
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param gradeall
	 * @return
	 */
	public int saveReturn(Gradeall gradeall) {
		this.getHibernateTemplate().save(gradeall);
		
		return gradeall.getGaId();
	}

}
