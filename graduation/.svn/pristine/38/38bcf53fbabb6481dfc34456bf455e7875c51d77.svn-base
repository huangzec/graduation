package com.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mvc.common.Pagination;
import com.mvc.entity.User;

public class EntityDaoImpl extends HibernateDaoSupport implements EntityDao{
	public List<Object> createQuery(final String queryString) {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createQuery(queryString);
						List<Object> rows = query.list();
						return rows;
					}
				});
	}
	
	public List<User> createUserQuery(final String queryString) {
		return (List<User>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createQuery(queryString);
						List<User> rows = query.list();
						return rows;
					}
				});
	}
	
	
	public Object save(final Object model) {
		return  getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						session.save(model);
						return null;
					}
				});
	}
	public void update(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.update(model);
				return null;
			}
		});
	}
	public void delete(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.delete(model);
				return null;
			}
		});
	}

	public User getRecordByNameAndPwd(String userName, String passWord) {
		String hql = "from User where adminInd = '" + userName + "' and passWord = '" + passWord + "'";
		List<User> list = getHibernateTemplate().find(hql);
		if(list == null || list.size() < 1)
		{
			return null;
		}
		return list.get(0);
	}

	public User getRecordById(Integer id) {
		
		return getHibernateTemplate().get(User.class, id);
	}

	public void editOneUser(User user) {
		getHibernateTemplate().update(user);
		
	}

	public void addOneUser(User user) {
		getHibernateTemplate().save(user);
		
	}

	public List<User> getAllRecordByPages(Pagination pagination) {
		String hql = "from User order by id desc";
		List<User> list = getHibernateTemplate().find(hql);
		System.out.println("list.size() = " + list.size());
		if(list == null || list.size() < 1) {
			return null;
		}
		
		int size 	= pagination.getSize();
		int pagenum = pagination.getCurrentPage();
		List<User> listReturn = new ArrayList<User>();
		listReturn.clear();
		int currentPageStart 	= (pagenum - 1) * size;
		int currentPageEnd 		= pagenum * size - 1;
		while(currentPageStart <= currentPageEnd && currentPageStart < list.size())
		{
			User user = (User) list.get(currentPageStart);
			listReturn.add(user);
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

	public void removeOneUser(User user) {
		getHibernateTemplate().delete(user);
		
	}

	public List<User> getAllRecordByPages(String where, Pagination pagination) {
		String hql = "from User where adminInd like '%" + where + "%' order by id desc";
		List<User> list = getHibernateTemplate().find(hql);
		if(list == null || list.size() < 1) {
			return null;
		}
		
		int size 	= pagination.getSize();
		int pagenum = pagination.getCurrentPage();
		List<User> listReturn = new ArrayList<User>();
		listReturn.clear();
		int currentPageStart 	= (pagenum - 1) * size;
		int currentPageEnd 		= pagenum * size - 1;
		while(currentPageStart <= currentPageEnd && currentPageStart < list.size())
		{
			User user = (User) list.get(currentPageStart);
			listReturn.add(user);
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
}
