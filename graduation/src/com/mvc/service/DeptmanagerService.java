package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.DeptmanagerDao;
import com.mvc.entity.Deptmanager;
import com.mvc.exception.VerifyException;

@Service
public class DeptmanagerService {

	@Autowired
	private DeptmanagerDao deptmanagerDao;

	/**
	 * 获取一个系部管理员
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-11 上午11:34:15
	 * @return Deptmanager
	 * @throws VerifyException 
	 */
	public Deptmanager getOneDeptManager(String where) throws VerifyException
	{
		return deptmanagerDao.getOne(where);
	}

	/**
	 * 编辑一个系部管理员
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午05:33:47
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneDeptmanager(Deptmanager deptmanager) throws VerifyException {
		deptmanagerDao.update(deptmanager);
	}

	/**
	 * 
	 * 添加一个系部管理员 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午9:33:29
	 * @return void
	 * @throws VerifyException 
	 */
	public void save(Deptmanager deptmanager) throws VerifyException {
		deptmanagerDao.save(deptmanager);
	}

	/**
	 * 
	 * 获取系部管理员所有记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 上午11:27:03
	 * @return List<Deptmanager>
	 * @throws VerifyException 
	 */
	public List<Deptmanager> getAll(String sql) throws VerifyException {
		return deptmanagerDao.getAll(sql);
	}

	/**
	 * 
	 * 根据dmId获取一条记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 上午11:47:08
	 * @return Deptmanager
	 * @throws VerifyException 
	 */
	public Deptmanager getOne(String dmId) throws VerifyException {
		return deptmanagerDao.getOne("from Deptmanager where dmId='" + dmId + "'");
	}

	/**
	 * 
	 * 删除一条系部管理员信息记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 上午11:47:33
	 * @return void
	 * @throws VerifyException 
	 */
	public void remove(Deptmanager deptMgr) throws VerifyException {
		deptmanagerDao.remove(deptMgr);
	}

	/**
	 * 
	 * 分页获取所有记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 下午4:06:30
	 * @return List<Deptmanager>
	 * @throws VerifyException 
	 */
	public List<Deptmanager> getAllRecordByPages(String where,
			Pagination pagination) throws VerifyException {
		return deptmanagerDao.getAllRecordByPages(where, pagination);
	}

	
}
