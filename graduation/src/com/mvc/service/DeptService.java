package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.DeptDao;
import com.mvc.entity.Department;
import com.mvc.exception.VerifyException;

@Service
public class DeptService {

	@Autowired
	private DeptDao deptDao;
	
	/**
	 * 
	 * 添加一个系部 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 上午11:34:17
	 * @return void
	 * @throws VerifyException 
	 */
	public void save(Department dept) throws VerifyException {
		deptDao.save(dept);
	}

	/**
	 * 
	 * 删除一条系部信息记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午5:50:37
	 * @return void
	 * @throws VerifyException 
	 */
	public void remove(Department dept) throws VerifyException {
		deptDao.remove(dept);
	}

	/**
	 * 
	 * 通过deptId查找系部 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午5:51:00
	 * @return Department
	 * @throws VerifyException 
	 */
	public Department getOneDept(String deptId) throws VerifyException {
		return deptDao.getOne("from Department where deptId='" + deptId + "'");
	}

	/**
	 * 
	 * 更新一条系部信息记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午7:33:42
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneDept(Department dept) throws VerifyException {
		deptDao.update(dept);
	}

	/**
	 * 
	 * 通过分页获取所有记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 下午7:10:44
	 * @return List<Department>
	 * @throws VerifyException 
	 */
	public List<Department> getAllRecordByPages(String where,
			Pagination pagination) throws VerifyException {
		return deptDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 普通查询所有系部记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 下午7:25:42
	 * @return List<Department>
	 * @throws VerifyException 
	 */
	public List<Department> getAll(String sql) throws VerifyException {
		return deptDao.getAll(sql);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Department getRecordByWhere(String where) throws VerifyException {
		return deptDao.getOne(where);
	}

}
