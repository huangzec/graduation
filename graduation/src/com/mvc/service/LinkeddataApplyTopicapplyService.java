package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicapplyDao;
import com.mvc.entity.LinkeddataApplyTopicapply;
import com.mvc.exception.VerifyException;

/**
 * 答辩申请与答辩申请表关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyTopicapplyService {

	@Autowired
	private LinkeddataApplyTopicapplyDao linkeddataApplyTopicapplyDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataApplyTopicapply linkeddataApplyTopicapply) throws VerifyException {
		linkeddataApplyTopicapplyDao.save(linkeddataApplyTopicapply);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return LinkeddataApplyTopicapply
	 */
	public LinkeddataApplyTopicapply getOneRecordById(int id) {
		
		return linkeddataApplyTopicapplyDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataApplyTopicapply(LinkeddataApplyTopicapply linkeddataApplyTopicapply) throws VerifyException {
		linkeddataApplyTopicapplyDao.remove(linkeddataApplyTopicapply);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataApplyTopicapply(LinkeddataApplyTopicapply linkeddataApplyTopicapply) throws VerifyException {
		linkeddataApplyTopicapplyDao.update(linkeddataApplyTopicapply);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return List<LinkeddataApplyTopicapply>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicapply> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataApplyTopicapplyDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return List<LinkeddataApplyTopicapply>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicapply> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return linkeddataApplyTopicapplyDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 添加一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicapply
	 * @return
	 */
	public int addOneReturn(LinkeddataApplyTopicapply linkeddataApplyTopicapply) {
		return linkeddataApplyTopicapplyDao.saveReturn(linkeddataApplyTopicapply);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkWhere
	 * @return
	 * @throws VerifyException 
	 */
	public LinkeddataApplyTopicapply getRecordByWhere(String where) throws VerifyException {
		
		return linkeddataApplyTopicapplyDao.getOne(where);
	}
	
}
