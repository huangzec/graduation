package com.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public abstract class AbstractDao<T> {

	/**  */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	protected HibernateTemplate hibernateTemplate;
	
	
	
	/**
	 * 查询记录总数
	 * @param sql		查询记录的sql语句
	 * @param args		查询参数
	 * @return	返回记录条数
	 */
	public int count(String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, Integer.class, args);
	}
	
}
