package com.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.common.Pagination;
import com.mvc.entity.User;

@Repository
public interface EntityDao {
	public List<Object> createQuery(final String queryString);
	public List<User> createUserQuery(final String queryString);
	public Object save(final Object model);
	public void update(final Object model);
	public void delete(final Object model);
	public User getRecordByNameAndPwd(String userName, String passWord);
	public User getRecordById(Integer id);
	public void editOneUser(User user);
	public void addOneUser(User user);
	public List<User> getAllRecordByPages(Pagination pagination);
	public void removeOneUser(User user);
	public List<User> getAllRecordByPages(String where, Pagination pagination);
}
