package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyGradeallDao;
import com.mvc.entity.LinkeddataApplyGradeall;

/**
 * 答辩申请与毕业答辩总成绩表关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyGradeallService {

	@Autowired
	private LinkeddataApplyGradeallDao linkeddataApplyGradeallDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 22:03:15
	 * @return void
	 */
	public void addOne(LinkeddataApplyGradeall linkeddataApplyGradeall) {
		linkeddataApplyGradeallDao.save(linkeddataApplyGradeall);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 22:03:15
	 * @return LinkeddataApplyGradeall
	 */
	public LinkeddataApplyGradeall getOneRecordById(int id) {
		
		return linkeddataApplyGradeallDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 22:03:15
	 * @return void
	 */
	public void removeOneLinkeddataApplyGradeall(LinkeddataApplyGradeall linkeddataApplyGradeall) {
		linkeddataApplyGradeallDao.remove(linkeddataApplyGradeall);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 22:03:15
	 * @return void
	 */
	public void editOneLinkeddataApplyGradeall(LinkeddataApplyGradeall linkeddataApplyGradeall) {
		linkeddataApplyGradeallDao.update(linkeddataApplyGradeall);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 22:03:15
	 * @return List<LinkeddataApplyGradeall>
	 */
	public List<LinkeddataApplyGradeall> getAllRowsByWhere(String where) {
		
		return linkeddataApplyGradeallDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-12 22:03:15
	 * @return List<LinkeddataApplyGradeall>
	 */
	public List<LinkeddataApplyGradeall> getAllRecordByPages(String where, Pagination pagination) {
		return linkeddataApplyGradeallDao.getAllRecordByPages(where, pagination);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 */
	public LinkeddataApplyGradeall getRecordByWhere(String where) {
		return linkeddataApplyGradeallDao.getOne(where);
	}
	
}
