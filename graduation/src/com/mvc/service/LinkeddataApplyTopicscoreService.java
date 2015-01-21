package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicscoreDao;
import com.mvc.entity.LinkeddataApplyTopicscore;
import com.mvc.exception.VerifyException;

/**
 * 答辩申请与开题答辩成绩关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyTopicscoreService {

	@Autowired
	private LinkeddataApplyTopicscoreDao linkeddataApplyTopicscoreDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 11:44:19
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataApplyTopicscore linkeddataApplyTopicscore) throws VerifyException {
		linkeddataApplyTopicscoreDao.save(linkeddataApplyTopicscore);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 11:44:19
	 * @return LinkeddataApplyTopicscore
	 */
	public LinkeddataApplyTopicscore getOneRecordById(int id) {
		
		return linkeddataApplyTopicscoreDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 11:44:19
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataApplyTopicscore(LinkeddataApplyTopicscore linkeddataApplyTopicscore) throws VerifyException {
		linkeddataApplyTopicscoreDao.remove(linkeddataApplyTopicscore);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 11:44:19
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataApplyTopicscore(LinkeddataApplyTopicscore linkeddataApplyTopicscore) throws VerifyException {
		linkeddataApplyTopicscoreDao.update(linkeddataApplyTopicscore);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 11:44:19
	 * @return List<LinkeddataApplyTopicscore>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicscore> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataApplyTopicscoreDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 11:44:19
	 * @return List<LinkeddataApplyTopicscore>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicscore> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return linkeddataApplyTopicscoreDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-13 下午12:15:31
	 * @return LinkeddataApplyTopicscore
	 * @throws VerifyException 
	 */
	public LinkeddataApplyTopicscore getRecordByWhere(String where) throws VerifyException {
		return linkeddataApplyTopicscoreDao.getOne(where);
	}
	
}
