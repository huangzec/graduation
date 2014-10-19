package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicapplyDao;
import com.mvc.entity.LinkeddataApplyTopicapply;

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
	 */
	public void addOne(LinkeddataApplyTopicapply linkeddataApplyTopicapply) {
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
	 */
	public void removeOneLinkeddataApplyTopicapply(LinkeddataApplyTopicapply linkeddataApplyTopicapply) {
		linkeddataApplyTopicapplyDao.remove(linkeddataApplyTopicapply);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return void
	 */
	public void editOneLinkeddataApplyTopicapply(LinkeddataApplyTopicapply linkeddataApplyTopicapply) {
		linkeddataApplyTopicapplyDao.update(linkeddataApplyTopicapply);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return List<LinkeddataApplyTopicapply>
	 */
	public List<LinkeddataApplyTopicapply> getAllRowsByWhere(String where) {
		
		return linkeddataApplyTopicapplyDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:38:26
	 * @return List<LinkeddataApplyTopicapply>
	 */
	public List<LinkeddataApplyTopicapply> getAllRecordByPages(String where, Pagination pagination) {
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
	
}
