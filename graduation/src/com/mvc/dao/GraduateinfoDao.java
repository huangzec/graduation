package com.mvc.dao;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Graduateinfo;

@Repository
public class GraduateinfoDao extends BaseDao<Graduateinfo> {

	/**
	 * 带返回值的添加方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param graduateinfo
	 * @return
	 */
	public int saveReturn(Graduateinfo graduateinfo) {
		this.getHibernateTemplate().save(graduateinfo);
		
		return graduateinfo.getGdiId();
	}

}
