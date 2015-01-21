package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyGradethreeDao;
import com.mvc.entity.LinkeddataApplyGradethree;
import com.mvc.exception.VerifyException;

/**
 * 答辩申请与毕业答辩成绩表三关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyGradethreeService {

	@Autowired
	private LinkeddataApplyGradethreeDao linkeddataApplyGradethreeDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataApplyGradethree linkeddataApplyGradethree) throws VerifyException {
		linkeddataApplyGradethreeDao.save(linkeddataApplyGradethree);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return LinkeddataApplyGradethree
	 */
	public LinkeddataApplyGradethree getOneRecordById(int id) {
		
		return linkeddataApplyGradethreeDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataApplyGradethree(LinkeddataApplyGradethree linkeddataApplyGradethree) throws VerifyException {
		linkeddataApplyGradethreeDao.remove(linkeddataApplyGradethree);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataApplyGradethree(LinkeddataApplyGradethree linkeddataApplyGradethree) throws VerifyException {
		linkeddataApplyGradethreeDao.update(linkeddataApplyGradethree);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return List<LinkeddataApplyGradethree>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyGradethree> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataApplyGradethreeDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return List<LinkeddataApplyGradethree>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyGradethree> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return linkeddataApplyGradethreeDao.getAllRecordByPages(where, pagination);
	}
	
}
