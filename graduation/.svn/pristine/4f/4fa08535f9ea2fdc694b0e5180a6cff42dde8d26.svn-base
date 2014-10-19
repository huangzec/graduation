package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.DeptDao;
import com.mvc.entity.Department;

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
	 */
	public void save(Department dept) {
		deptDao.save(dept);
	}

	/**
	 * 
	 * 删除一条系部信息记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午5:50:37
	 * @return void
	 */
	public void remove(Department dept) {
		deptDao.remove(dept);
	}

	/**
	 * 
	 * 通过deptId查找系部 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午5:51:00
	 * @return Department
	 */
	public Department getOneDept(String deptId) {
		return deptDao.getOne("from Department where deptId='" + deptId + "'");
	}

	/**
	 * 
	 * 更新一条系部信息记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午7:33:42
	 * @return void
	 */
	public void editOneDept(Department dept) {
		deptDao.update(dept);
	}

	/**
	 * 
	 * 通过分页获取所有记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 下午7:10:44
	 * @return List<Department>
	 */
	public List<Department> getAllRecordByPages(String where,
			Pagination pagination) {
		return deptDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 普通查询所有系部记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 下午7:25:42
	 * @return List<Department>
	 */
	public List<Department> getAll(String sql) {
		return deptDao.getAll(sql);
	}

}
