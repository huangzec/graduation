package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyGraduateinfoDao;
import com.mvc.entity.LinkeddataApplyGraduateinfo;

/**
 * 答辩申请与毕业答辩信息表关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyGraduateinfoService {

	@Autowired
	private LinkeddataApplyGraduateinfoDao linkeddataApplyGraduateinfoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return void
	 */
	public void addOne(LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo) {
		linkeddataApplyGraduateinfoDao.save(linkeddataApplyGraduateinfo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return LinkeddataApplyGraduateinfo
	 */
	public LinkeddataApplyGraduateinfo getOneRecordById(int id) {
		
		return linkeddataApplyGraduateinfoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return void
	 */
	public void removeOneLinkeddataApplyGraduateinfo(LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo) {
		linkeddataApplyGraduateinfoDao.remove(linkeddataApplyGraduateinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return void
	 */
	public void editOneLinkeddataApplyGraduateinfo(LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo) {
		linkeddataApplyGraduateinfoDao.update(linkeddataApplyGraduateinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return List<LinkeddataApplyGraduateinfo>
	 */
	public List<LinkeddataApplyGraduateinfo> getAllRowsByWhere(String where) {
		
		return linkeddataApplyGraduateinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return List<LinkeddataApplyGraduateinfo>
	 */
	public List<LinkeddataApplyGraduateinfo> getAllRecordByPages(String where, Pagination pagination) {
		return linkeddataApplyGraduateinfoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 */
	public LinkeddataApplyGraduateinfo getRecordByWhere(String where) {
		return linkeddataApplyGraduateinfoDao.getOne(where);
	}
	
}
