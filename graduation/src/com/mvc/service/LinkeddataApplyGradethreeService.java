package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyGradethreeDao;
import com.mvc.entity.LinkeddataApplyGradethree;

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
	 */
	public void addOne(LinkeddataApplyGradethree linkeddataApplyGradethree) {
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
	 */
	public void removeOneLinkeddataApplyGradethree(LinkeddataApplyGradethree linkeddataApplyGradethree) {
		linkeddataApplyGradethreeDao.remove(linkeddataApplyGradethree);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return void
	 */
	public void editOneLinkeddataApplyGradethree(LinkeddataApplyGradethree linkeddataApplyGradethree) {
		linkeddataApplyGradethreeDao.update(linkeddataApplyGradethree);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return List<LinkeddataApplyGradethree>
	 */
	public List<LinkeddataApplyGradethree> getAllRowsByWhere(String where) {
		
		return linkeddataApplyGradethreeDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 20:18:38
	 * @return List<LinkeddataApplyGradethree>
	 */
	public List<LinkeddataApplyGradethree> getAllRecordByPages(String where, Pagination pagination) {
		return linkeddataApplyGradethreeDao.getAllRecordByPages(where, pagination);
	}
	
}
