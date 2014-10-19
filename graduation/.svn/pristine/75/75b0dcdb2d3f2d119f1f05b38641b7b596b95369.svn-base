package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicinfoDao;
import com.mvc.entity.LinkeddataApplyTopicinfo;

/**
 * 答辩申请与开题答辩信息表关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyTopicinfoService {

	@Autowired
	private LinkeddataApplyTopicinfoDao linkeddataApplyTopicinfoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return void
	 */
	public void addOne(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) {
		linkeddataApplyTopicinfoDao.save(linkeddataApplyTopicinfo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return LinkeddataApplyTopicinfo
	 */
	public LinkeddataApplyTopicinfo getOneRecordById(int id) {
		
		return linkeddataApplyTopicinfoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return void
	 */
	public void removeOneLinkeddataApplyTopicinfo(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) {
		linkeddataApplyTopicinfoDao.remove(linkeddataApplyTopicinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return void
	 */
	public void editOneLinkeddataApplyTopicinfo(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) {
		linkeddataApplyTopicinfoDao.update(linkeddataApplyTopicinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return List<LinkeddataApplyTopicinfo>
	 */
	public List<LinkeddataApplyTopicinfo> getAllRowsByWhere(String where) {
		
		return linkeddataApplyTopicinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return List<LinkeddataApplyTopicinfo>
	 */
	public List<LinkeddataApplyTopicinfo> getAllRecordByPages(String where, Pagination pagination) {
		return linkeddataApplyTopicinfoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 添加一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicinfo
	 * @return
	 */
	public int addOneReturn(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) {
		return linkeddataApplyTopicinfoDao.saveReturn(linkeddataApplyTopicinfo);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 */
	public LinkeddataApplyTopicinfo getRecordByWhere(String where) {
		return linkeddataApplyTopicinfoDao.getOne(where);
	}
	
}
