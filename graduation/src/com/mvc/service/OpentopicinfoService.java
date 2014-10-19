package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.OpentopicinfoDao;
import com.mvc.entity.Opentopicinfo;

/**
 * 开题答辩服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class OpentopicinfoService {

	@Autowired
	private OpentopicinfoDao opentopicinfoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return void
	 */
	public void addOne(Opentopicinfo opentopicinfo) {
		opentopicinfoDao.save(opentopicinfo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return Opentopicinfo
	 */
	public Opentopicinfo getOneRecordById(int id) {
		
		return opentopicinfoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return void
	 */
	public void removeOneOpentopicinfo(Opentopicinfo opentopicinfo) {
		opentopicinfoDao.remove(opentopicinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return void
	 */
	public void editOneOpentopicinfo(Opentopicinfo opentopicinfo) {
		opentopicinfoDao.update(opentopicinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return Object
	 */
	public List<Opentopicinfo> getAllRowsByWhere(String where) {
		
		return opentopicinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return List<Opentopicinfo>
	 */
	public List<Opentopicinfo> getAllRecordByPages(String where, Pagination pagination) {
		return opentopicinfoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午08:24:03
	 * @return Opentopicinfo
	 */
	public Opentopicinfo getRecordByWhere(String where) {
		return opentopicinfoDao.getOne(where);
	}

	/**
	 * 添加一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param opentopicinfo
	 * @return
	 */
	public int addOneReturn(Opentopicinfo opentopicinfo) {
		return opentopicinfoDao.saveReturn(opentopicinfo);
	}
	
}