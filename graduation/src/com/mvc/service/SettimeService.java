package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.SettimeDao;
import com.mvc.entity.Settime;
import com.mvc.exception.VerifyException;

/**
 * 设置时间服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class SettimeService {

	@Autowired
	private SettimeDao settimeDao;

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午09:36:48
	 * @return Settime
	 * @throws VerifyException 
	 */
	public Settime getOneByWhere(String where) throws VerifyException {
		
		return settimeDao.getOne(where);
	}

	/**
	 * 添加一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午09:42:46
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOneSettime(Settime settime) throws VerifyException {
		settimeDao.save(settime);		
	}

	/**
	 * 获取时间安排列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 上午10:32:34
	 * @return List<Settime>
	 * @throws VerifyException 
	 */
	public List<Settime> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		
		return settimeDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午06:05:44
	 * @return Settime
	 */
	public Settime getRecordById(int id) {
		return settimeDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午06:07:40
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOne(Settime settime) throws VerifyException {
		settimeDao.remove(settime);
	}

	/**
	 * 修改一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午08:00:43
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOne(Settime settime) throws VerifyException {
		settimeDao.update(settime);
	}
		
}
