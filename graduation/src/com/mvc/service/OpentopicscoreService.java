package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.OpentopicscoreDao;
import com.mvc.entity.Opentopicscore;

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
	 */
	public void addOne(Opentopicscore opentopicscore) {
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
	 */
	public void removeOneOpentopicscore(Opentopicscore opentopicscore) {
		opentopicscoreDao.remove(opentopicscore);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return void
	 */
	public void editOneOpentopicscore(Opentopicscore opentopicscore) {
		opentopicscoreDao.update(opentopicscore);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return List<Opentopicscore>
	 */
	public List<Opentopicscore> getAllRowsByWhere(String where) {
		
		return opentopicscoreDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-30 16:28:59
	 * @return List<Opentopicscore>
	 */
	public List<Opentopicscore> getAllRecordByPages(String where, Pagination pagination) {
		return opentopicscoreDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 通过where获取一条记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 下午04:20:48
	 * @return void
	 */
	public Opentopicscore getRecordByWhere(String where) {
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
