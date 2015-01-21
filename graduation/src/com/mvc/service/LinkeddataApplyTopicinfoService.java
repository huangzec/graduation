package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicinfoDao;
import com.mvc.entity.LinkeddataApplyTopicinfo;
import com.mvc.exception.VerifyException;

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
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataApplyTopicinfo(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) throws VerifyException {
		linkeddataApplyTopicinfoDao.remove(linkeddataApplyTopicinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataApplyTopicinfo(LinkeddataApplyTopicinfo linkeddataApplyTopicinfo) throws VerifyException {
		linkeddataApplyTopicinfoDao.update(linkeddataApplyTopicinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return List<LinkeddataApplyTopicinfo>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicinfo> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataApplyTopicinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 09:56:50
	 * @return List<LinkeddataApplyTopicinfo>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicinfo> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	public LinkeddataApplyTopicinfo getRecordByWhere(String where) throws VerifyException {
		return linkeddataApplyTopicinfoDao.getOne(where);
	}
	
}
