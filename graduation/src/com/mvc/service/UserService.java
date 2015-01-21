package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.common.Pagination;
import com.mvc.dao.EntityDao;
import com.mvc.dao.UserDao;
import com.mvc.entity.Student;
import com.mvc.entity.User;
import com.mvc.exception.VerifyException;

@Service
public class UserService {
	@Autowired
	private EntityDao entityDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public List<User> getUserList(){
	
		StringBuffer sff = new StringBuffer();
		sff.append("select a from ").append(User.class.getSimpleName()).append(" a ");
		List<User> list = entityDao.createUserQuery(sff.toString());
		return list;
	}
	
	public void save(Student st){
		entityDao.save(st);
	}
	public void delete(Object obj){
		entityDao.delete(obj);
	}

	public User getRecordByNameAndPwd(String userName, String passWord) {
		
		return entityDao.getRecordByNameAndPwd(userName, passWord);
	}

	public User getRecordById(Integer id) {
		
		return entityDao.getRecordById(id);
	}

	/**
	 * 修改一个用户信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午02:04:09
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneUser(User user) throws VerifyException {
		userDao.update(user);
		
	}

	/**
	 * 添加一个用户
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午09:32:26
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOneUser(User user) throws VerifyException {
		userDao.save(user);
		
	}

	public void save(User user) {
		entityDao.save(user);
		
	}

	public List<User> getAllRecordByPages(Pagination pagination) {
		
		return entityDao.getAllRecordByPages(pagination);
	}

	public void removeOneUser(User user) {
		entityDao.removeOneUser(user);
		
	}

	public List<User> getAllRecordByPages(String where, Pagination pagination) {
		 
		return entityDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * @throws VerifyException *********/
	
	public User getOneUser(String where) throws VerifyException {
		//得到一个用户
		return userDao.getOne(where);
	}

	/**
	 * 通过ID获取一个用户
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午01:49:29
	 * @return User
	 */
	public User getOneUserById(Integer id) {
		
		return userDao.getById(id);
	}
}
