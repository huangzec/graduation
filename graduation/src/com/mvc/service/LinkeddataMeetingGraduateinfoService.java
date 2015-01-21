package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.LinkeddataMeetingGraduateinfoDao;
import com.mvc.entity.LinkeddataMeetingGraduateinfo;
import com.mvc.exception.VerifyException;

/**
 * 会议记录表与毕业答辩信息表关联服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class LinkeddataMeetingGraduateinfoService {

	@Autowired
	private LinkeddataMeetingGraduateinfoDao linkeddataMeetingGraduateinfoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-06 10:08:39
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(LinkeddataMeetingGraduateinfo linkeddataMeetingGraduateinfo) throws VerifyException {
		linkeddataMeetingGraduateinfoDao.save(linkeddataMeetingGraduateinfo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-06 10:08:39
	 * @return LinkeddataMeetingGraduateinfo
	 */
	public LinkeddataMeetingGraduateinfo getOneRecordById(int id) {
		
		return linkeddataMeetingGraduateinfoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-06 10:08:39
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneLinkeddataMeetingGraduateinfo(LinkeddataMeetingGraduateinfo linkeddataMeetingGraduateinfo) throws VerifyException {
		linkeddataMeetingGraduateinfoDao.remove(linkeddataMeetingGraduateinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-06 10:08:39
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneLinkeddataMeetingGraduateinfo(LinkeddataMeetingGraduateinfo linkeddataMeetingGraduateinfo) throws VerifyException {
		linkeddataMeetingGraduateinfoDao.update(linkeddataMeetingGraduateinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-06 10:08:39
	 * @return List<LinkeddataMeetingGraduateinfo>
	 * @throws VerifyException 
	 */
	public List<LinkeddataMeetingGraduateinfo> getAllRowsByWhere(String where) throws VerifyException {
		
		return linkeddataMeetingGraduateinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-06 10:08:39
	 * @return List<LinkeddataMeetingGraduateinfo>
	 * @throws VerifyException 
	 */
	public List<LinkeddataMeetingGraduateinfo> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return linkeddataMeetingGraduateinfoDao.getAllRecordByPages(where, pagination);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public LinkeddataMeetingGraduateinfo getRecordByWhere(String where) throws VerifyException {
		return linkeddataMeetingGraduateinfoDao.getOne(where);
	}
	
}
