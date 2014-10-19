package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyTopicreportDao;
import com.mvc.entity.LinkeddataApplyTopicreport;

/**
 * 答辩申请与答辩报告书关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyTopicreportService {

	@Autowired
	private LinkeddataApplyTopicreportDao linkeddataApplyTopicreportDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:37:55
	 * @return void
	 */
	public void addOne(LinkeddataApplyTopicreport linkeddataApplyTopicreport) {
		linkeddataApplyTopicreportDao.save(linkeddataApplyTopicreport);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:37:55
	 * @return LinkeddataApplyTopicreport
	 */
	public LinkeddataApplyTopicreport getOneRecordById(int id) {
		
		return linkeddataApplyTopicreportDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:37:55
	 * @return void
	 */
	public void removeOneLinkeddataApplyTopicreport(LinkeddataApplyTopicreport linkeddataApplyTopicreport) {
		linkeddataApplyTopicreportDao.remove(linkeddataApplyTopicreport);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:37:55
	 * @return void
	 */
	public void editOneLinkeddataApplyTopicreport(LinkeddataApplyTopicreport linkeddataApplyTopicreport) {
		linkeddataApplyTopicreportDao.update(linkeddataApplyTopicreport);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:37:55
	 * @return List<LinkeddataApplyTopicreport>
	 */
	public List<LinkeddataApplyTopicreport> getAllRowsByWhere(String where) {
		
		return linkeddataApplyTopicreportDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-10 09:37:55
	 * @return List<LinkeddataApplyTopicreport>
	 */
	public List<LinkeddataApplyTopicreport> getAllRecordByPages(String where, Pagination pagination) {
		return linkeddataApplyTopicreportDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 添加记录，带返回结果
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkeddataApplyTopicreport
	 * @return
	 */
	public int addOneReturn(LinkeddataApplyTopicreport linkeddataApplyTopicreport) {
		return linkeddataApplyTopicreportDao.saveReturn(linkeddataApplyTopicreport);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param linkWhere
	 * @return
	 */
	public LinkeddataApplyTopicreport getRecordByWhere(String where) {
		return linkeddataApplyTopicreportDao.getOne(where);
	}
	
}