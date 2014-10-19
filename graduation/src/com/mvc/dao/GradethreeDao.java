package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Gradethree;

@Repository
public class GradethreeDao extends BaseDao<Gradethree> {

	/**
	 * 带返回值的添加
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param gradethree
	 * @return
	 */
	public int saveReturn(Gradethree gradethree) {
		this.getHibernateTemplate().save(gradethree);
		
		return gradethree.getGtrId();
	}

}
