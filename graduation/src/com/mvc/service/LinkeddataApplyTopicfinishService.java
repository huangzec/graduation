package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicfinishDao;
import com.mvc.entity.LinkeddataApplyTopicfinish;
import com.mvc.exception.VerifyException;

/**
 * 毕业答辩相关材料关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyTopicfinishService {

	@Autowired
	private LinkeddataApplyTopicfinishDao linkeddataApplyTopicfinishDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 10:26:31
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataApplyTopicfinish linkeddataApplyTopicfinish) throws VerifyException {
		linkeddataApplyTopicfinishDao.save(linkeddataApplyTopicfinish);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 10:26:31
	 * @return LinkeddataApplyTopicfinish
	 */
	public LinkeddataApplyTopicfinish getOneRecordById(int id) {
		
		return linkeddataApplyTopicfinishDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 10:26:31
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataApplyTopicfinish(LinkeddataApplyTopicfinish linkeddataApplyTopicfinish) throws VerifyException {
		linkeddataApplyTopicfinishDao.remove(linkeddataApplyTopicfinish);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 10:26:31
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataApplyTopicfinish(LinkeddataApplyTopicfinish linkeddataApplyTopicfinish) throws VerifyException {
		linkeddataApplyTopicfinishDao.update(linkeddataApplyTopicfinish);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 10:26:31
	 * @return List<LinkeddataApplyTopicfinish>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicfinish> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataApplyTopicfinishDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 10:26:31
	 * @return List<LinkeddataApplyTopicfinish>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyTopicfinish> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return linkeddataApplyTopicfinishDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 添加一条记录，返回结果
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicfinish
	 * @return
	 */
	public int addOneReturn(LinkeddataApplyTopicfinish linkeddataApplyTopicfinish) {
		return linkeddataApplyTopicfinishDao.saveReturn(linkeddataApplyTopicfinish);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkWhere
	 * @return
	 * @throws VerifyException 
	 */
	public LinkeddataApplyTopicfinish getRecordByWhere(String where) throws VerifyException {
		return linkeddataApplyTopicfinishDao.getOne(where);
	}
	
}
