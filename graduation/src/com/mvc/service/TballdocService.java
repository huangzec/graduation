package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TballdocDao;
import com.mvc.entity.Tballdoc;
import com.mvc.exception.VerifyException;

/**
 * 所有材料信息表服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TballdocService {

	@Autowired
	private TballdocDao tballdocDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-28 09:09:44
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Tballdoc tballdoc) throws VerifyException {
		tballdocDao.save(tballdoc);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-28 09:09:44
	 * @return Tballdoc
	 */
	public Tballdoc getOneRecordById(int id) {
		
		return tballdocDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-28 09:09:44
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTballdoc(Tballdoc tballdoc) throws VerifyException {
		tballdocDao.remove(tballdoc);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-28 09:09:44
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTballdoc(Tballdoc tballdoc) throws VerifyException {
		tballdocDao.update(tballdoc);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-28 09:09:44
	 * @return List<Tballdoc>
	 * @throws VerifyException 
	 */
	public List<Tballdoc> getAllRowsByWhere(String where) throws VerifyException {
		
		return tballdocDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-28 09:09:44
	 * @return List<Tballdoc>
	 * @throws VerifyException 
	 */
	public List<Tballdoc> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return tballdocDao.getAllRecordByPages(where, pagination);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Tballdoc getRecordByWhere(String where) throws VerifyException {
		return tballdocDao.getOne(where);
	}
	
}
