package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataApplyGraduateinfoDao;
import com.mvc.entity.LinkeddataApplyGraduateinfo;
import com.mvc.exception.VerifyException;

/**
 * 答辩申请与毕业答辩信息表关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataApplyGraduateinfoService {

	@Autowired
	private LinkeddataApplyGraduateinfoDao linkeddataApplyGraduateinfoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo) throws VerifyException {
		linkeddataApplyGraduateinfoDao.save(linkeddataApplyGraduateinfo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return LinkeddataApplyGraduateinfo
	 */
	public LinkeddataApplyGraduateinfo getOneRecordById(int id) {
		
		return linkeddataApplyGraduateinfoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataApplyGraduateinfo(LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo) throws VerifyException {
		linkeddataApplyGraduateinfoDao.remove(linkeddataApplyGraduateinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataApplyGraduateinfo(LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo) throws VerifyException {
		linkeddataApplyGraduateinfoDao.update(linkeddataApplyGraduateinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return List<LinkeddataApplyGraduateinfo>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyGraduateinfo> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataApplyGraduateinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-11 16:19:51
	 * @return List<LinkeddataApplyGraduateinfo>
	 * @throws VerifyException 
	 */
	public List<LinkeddataApplyGraduateinfo> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return linkeddataApplyGraduateinfoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public LinkeddataApplyGraduateinfo getRecordByWhere(String where) throws VerifyException {
		return linkeddataApplyGraduateinfoDao.getOne(where);
	}
	
}
