package com.mvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mvc.common.Pagination;
import com.mvc.entity.Department;
import com.mvc.entity.User;

/**
 * 基类-->所有daoImpl方法的父类，模板化操作类
 * 
 * @author Happy_Jqc@163.com
 *
 */
public class BaseDao<T> extends HibernateDaoSupport{//提供DAO的泛型类
//	@Autowired
//	private HibernateTemplate hibernateTemplate; //注入Hibernate模板类
	
	private Class<T> entityClass; //DAO的泛型类，即子类所指定的T所对应的类型

	/**
	 * 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-10 上午11:05:25
	 *
	 */
	public BaseDao() {//通过反射方式获取子类Dao对应的泛型实体类
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}
	
	/**
	 * 
	 * 通过ID查询一条记录
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-10 上午11:03:22
	 * @return T
	 */
	public T getById(Serializable id){
		return (T) this.getHibernateTemplate().get(entityClass, id); //直接使用entityClass
	}
	
	/**
	 * 
	 * 保存一条数据
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-10 上午11:03:35
	 * @return void
	 */
	public void save(T entity){
		this.getHibernateTemplate().save(entity);
	}
	
	/**
	 * 
	 * 更新一条记录
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-10 上午11:03:39
	 * @return void
	 */
	public void update(T entity){
		this.getHibernateTemplate().update(entity);
	}
	
	/**
	 * 
	 * 删除一条记录
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-10 上午11:03:43
	 * @return void
	 */
	public void remove(T entity){
		this.getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午09:01:16
	 * @return T
	 */
	public T getOne(String where){
		List<T> list = this.getHibernateTemplate().find(where);
		if(list == null || list.size() < 1) {
			return null;
		}
		
		return (T) list.get(0);
	}

	/**
	 * 分页查询
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午04:05:06
	 * @return List<T>
	 */
	public List<T> getAllRecordByPages(String where, Pagination pagination)
	{
		List<T> list = getHibernateTemplate().find(where);
		if(list == null || list.size() < 1) {
			return null;
		}
		int size 	= pagination.getSize();
		int pagenum = pagination.getCurrentPage();
		List<T> listReturn = new ArrayList<T>();
		listReturn.clear();
		int currentPageStart 	= (pagenum - 1) * size;
		int currentPageEnd 		= pagenum * size - 1;
		while(currentPageStart <= currentPageEnd && currentPageStart < list.size())
		{
			T entity = (T) list.get(currentPageStart);
			listReturn.add(entity);
			currentPageStart++;
		}
		pagination.setTotalRecord(list.size());
		int totalPage = list.size()/size;
		if(totalPage * size < list.size()) {
			totalPage++;
		}
		pagination.setTotalPage(totalPage);
		
		return listReturn;
	}
	
	public List<T> getAll(String sql) {
		return this.getHibernateTemplate().find(sql);
	}

}