package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TeacherDao;
import com.mvc.entity.Teacher;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	
	/**
	 * 添加单个教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午01:56:18
	 * @return void
	 */
	public void addOneTeacher(Teacher teacher) {
		teacherDao.save(teacher);
	}


	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午02:09:05
	 * @return Teacher
	 */
	public Teacher getOneTeacherById(String id) {
		
		return teacherDao.getById(id);
	}

	/**
	 * 分页查询
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午03:59:40
	 * @return List<Teacher>
	 */
	public List<Teacher> getAllRecordByPages(String where, Pagination pagination) {
		
		return teacherDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 删除单个教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午08:37:05
	 * @return void
	 */
	public void removeOneTeacher(Teacher teacher) {
		teacherDao.remove(teacher);
	}


	/**
	 * 编辑单条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-14 下午10:27:39
	 * @return void
	 */
	public void editOneTeacher(Teacher teacher) {
		teacherDao.update(teacher);
	}


	public Teacher getOneByWhere(String where) {
		return teacherDao.getOne(where);
	}


	/**
	 * 获取所有教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-28 下午09:39:48
	 * @return List<Teacher>
	 */
	public List<Teacher> getAllRows(String where) {
		return teacherDao.getAll(where);
	}

}
