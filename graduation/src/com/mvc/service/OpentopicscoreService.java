package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.OpentopicscoreDao;
import com.mvc.entity.Opentopicscore;
import com.mvc.exception.VerifyException;

/**
 * 开题答辩分数服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class OpentopicscoreService {

	@Autowired
	private OpentopicscoreDao opentopicscoreDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Opentopicscore opentopicscore) throws VerifyException {
		opentopicscoreDao.save(opentopicscore);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return Opentopicscore
	 */
	public Opentopicscore getOneRecordById(int id) {
		
		return opentopicscoreDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneOpentopicscore(Opentopicscore opentopicscore) throws VerifyException {
		opentopicscoreDao.remove(opentopicscore);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneOpentopicscore(Opentopicscore opentopicscore) throws VerifyException {
		opentopicscoreDao.update(opentopicscore);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return List<Opentopicscore>
	 * @throws VerifyException 
	 */
	public List<Opentopicscore> getAllRowsByWhere(String where) throws VerifyException {
		
		return opentopicscoreDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return List<Opentopicscore>
	 * @throws VerifyException 
	 */
	public List<Opentopicscore> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return opentopicscoreDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 通过where获取一条记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 下午04:20:48
	 * @return void
	 * @throws VerifyException 
	 */
	public Opentopicscore getRecordByWhere(String where) throws VerifyException {
		return opentopicscoreDao.getOne(where);
	}

	/**
	 * 带返回值的添加方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param ops
	 * @return
	 */
	public int addOneReturn(Opentopicscore ops) {
		return opentopicscoreDao.saveReturn(ops);
	}
	
}
