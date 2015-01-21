package com.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.common.Pagination;
import com.mvc.dao.EntityDao;
import com.mvc.dao.StudentDao;
import com.mvc.entity.Profession;
import com.mvc.entity.Student;
import com.mvc.exception.VerifyException;

@Service
public class StudentService {
	@Autowired
	private EntityDao entityDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Transactional
	public List<Object> getStudentList(){
	
		StringBuffer sff = new StringBuffer();
		sff.append("select a from ").append(Student.class.getSimpleName()).append(" a ");
		List<Object> list = entityDao.createQuery(sff.toString());
		return list;
	}
	
	public void save(Student st){
		entityDao.save(st);
	}
	public void delete(Object obj){
		entityDao.delete(obj);
	}

	/**
	 * 通过ID获取一个学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午03:14:50
	 * @return Student
	 */
	public Student getOneById(String id) {
		
		return studentDao.getById(id);
	}

	/**
	 * 添加一个学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午03:18:19
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOneStudent(Student student) throws VerifyException {
		studentDao.save(student);		
	}

	/**
	 * 获取学生列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午03:21:52
	 * @return List<Student>
	 * @throws VerifyException 
	 */
	public List<Student> getAllRecordByPages(String where,
			Pagination pagination) throws VerifyException {
		
		return studentDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 删除一个学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午05:53:31
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneStudent(Student student) throws VerifyException {
		studentDao.remove(student);		
	}

	/**
	 * 修改学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午08:25:04
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneStudent(Student student) throws VerifyException {
		studentDao.update(student);		
	}

	/**
	 * 
	 * 通过where获取一条学生记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午3:49:20
	 * @return Student
	 * @throws VerifyException 
	 */
	public Student getOneStu(String where) throws VerifyException {
		return studentDao.getOne(where);
	}

	/**
	 * 通过where条件获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-29 下午05:44:41
	 * @return List<Student>
	 * @throws VerifyException 
	 */
	public List<Student> getAllRows(String where) throws VerifyException {
		return studentDao.getAll(where);
	}

	/**
	 * 获取学生相关联的信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-16 下午07:50:55
	 * @return Student
	 */
	public Map<String, String> getStudentInfo(String where) {
		return studentDao.getStudentInfo(where);
	}
}
