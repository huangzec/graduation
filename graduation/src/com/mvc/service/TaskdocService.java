package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.SelectfirstDao;
import com.mvc.dao.TaskdocDao;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Taskdoc;
import com.mvc.exception.VerifyException;

/**
 * 毕业设计任务书服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TaskdocService {

	@Autowired
	private TaskdocDao taskdocDao;
	
	@Autowired
	private SelectfirstDao selectfirstDao;

	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-13 上午11:38:12
	 * @return List<Taskdoc>
	 * @throws VerifyException 
	 */
	public List<Taskdoc> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return taskdocDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-27 下午05:57:48
	 * @return Taskdoc
	 * @throws VerifyException 
	 */
	public Taskdoc getRecordByWhere(String where) throws VerifyException {
		return taskdocDao.getOne(where);
	}

	/**
	 * 保存任务书
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-27 下午07:17:44
	 * @return void
	 * @throws VerifyException 
	 */
	public void saveOneTaskdoc(Taskdoc taskdoc) throws VerifyException {
		taskdocDao.save(taskdoc);
	}

	/**
	 * 编辑任务书
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-27 下午07:21:51
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTaskdoc(Taskdoc taskdoc) throws VerifyException {
		taskdocDao.update(taskdoc);
	}

	/**
	 * 获取课题选择结果
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 上午08:58:35
	 * @return List<Selectfirst>
	 * @throws VerifyException 
	 */
	public List<Selectfirst> getAllSelefRecordByPages(String where, Pagination pagination) throws VerifyException {
		return selectfirstDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取所有记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public List<Taskdoc> getAllRows(String where) throws VerifyException {
		return taskdocDao.getAll(where);
	}
}
