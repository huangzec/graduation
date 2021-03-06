package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.ApplyDao;
import com.mvc.entity.Apply;
import com.mvc.exception.VerifyException;

/**
 * 开题答辩、毕业答辩申请服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class ApplyService {

	@Autowired
	private ApplyDao applyDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:53
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Apply apply) throws VerifyException {
		applyDao.save(apply);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:53
	 * @return Apply
	 */
	public Apply getOneRecordById(int id) {
		
		return applyDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:53
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneApply(Apply apply) throws VerifyException {
		applyDao.remove(apply);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:53
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneApply(Apply apply) throws VerifyException {
		applyDao.update(apply);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:53
	 * @return Object
	 * @throws VerifyException 
	 */
	public Object getAllRowsByWhere(String where) throws VerifyException {
		
		return applyDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:53
	 * @return List<Apply>
	 * @throws VerifyException 
	 */
	public List<Apply> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return applyDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取单条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-5 下午02:48:44
	 * @return Apply
	 * @throws VerifyException 
	 */
	public Apply getRecordByWhere(String where) throws VerifyException {
		return applyDao.getOne(where);
	}

	/**
	 * 添加一条记录，返回记录ID
	 * @param apply
	 * @return
	 */
	public int addOneReturn(Apply apply) {
		return applyDao.saveReturn(apply);
	}

	/**
	 * 获取所有记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public List<Apply> getAllRows(String where) throws VerifyException {
		
		return applyDao.getAll(where);
	}
	
}
