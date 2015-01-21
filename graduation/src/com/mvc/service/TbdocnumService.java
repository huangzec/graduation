package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TbdocnumDao;
import com.mvc.entity.Tbdocnum;
import com.mvc.exception.VerifyException;

/**
 * 文件数量信息表服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TbdocnumService {

	@Autowired
	private TbdocnumDao tbdocnumDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-16 19:40:40
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Tbdocnum tbdocnum) throws VerifyException {
		tbdocnumDao.save(tbdocnum);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-16 19:40:40
	 * @return Tbdocnum
	 */
	public Tbdocnum getOneRecordById(int id) {
		
		return tbdocnumDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-16 19:40:40
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTbdocnum(Tbdocnum tbdocnum) throws VerifyException {
		tbdocnumDao.remove(tbdocnum);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-16 19:40:40
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTbdocnum(Tbdocnum tbdocnum) throws VerifyException {
		tbdocnumDao.update(tbdocnum);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-16 19:40:40
	 * @return List<Tbdocnum>
	 * @throws VerifyException 
	 */
	public List<Tbdocnum> getAllRowsByWhere(String where) throws VerifyException {
		
		return tbdocnumDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-16 19:40:40
	 * @return List<Tbdocnum>
	 * @throws VerifyException 
	 */
	public List<Tbdocnum> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return tbdocnumDao.getAllRecordByPages(where, pagination);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Tbdocnum getRecordByWhere(String where) throws VerifyException {
		return tbdocnumDao.getOne(where);
	}
	
}
