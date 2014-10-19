package com.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.entity.User;

@Repository
public class UserDao extends BaseDao<User>{

	/**
	 * 得到一个用户
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午08:08:08
	 * @return User
	 */
	public User getOneUser(String userName) {
		List list = this.getHibernateTemplate().find("from User where username = '" + userName + "'");
		if(list == null || list.size() < 1) {
			return null;
		}
		
		return (User) list.get(0);
	}
}
