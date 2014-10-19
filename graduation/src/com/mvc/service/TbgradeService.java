package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TbgradeDao;
import com.mvc.entity.Tbgrade;

/**
 * 年级服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TbgradeService {

	@Autowired
	private TbgradeDao tbgradeDao;

	/**
	 * 通过where获取一个年级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 上午11:40:06
	 * @return Tbgrade
	 */
	public Tbgrade getRecordByWhere(String where) {
		
		return tbgradeDao.getOne(where);
	}

	/**
	 * 添加一个年级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午12:00:45
	 * @return void
	 */
	public void addOneTbgrade(Tbgrade tbgrade) {
		tbgradeDao.save(tbgrade);
	}

	/**
	 * 获取年级列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午01:01:03
	 * @return List<Tbgrade>
	 */
	public List<Tbgrade> getAllRecordByPages(String where, Pagination pagination) {
		
		return tbgradeDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过ID获取单条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午01:26:18
	 * @return Tbgrade
	 */
	public Tbgrade getOneGradeById(int id) {
		
		return tbgradeDao.getById(id);
	}

	/**
	 * 删除记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午01:28:23
	 * @return void
	 */
	public void removeOneGrade(Tbgrade tbgrade) {
		tbgradeDao.remove(tbgrade);		
	}

	/**
	 * 通过where条件获取所有的年级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午03:00:46
	 * @return Tbgrade
	 */
	public List<Tbgrade> getAllRowsByWhere(String where) {
		
		return tbgradeDao.getAll(where);
	}

	/**
	 * 修改一个年级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午07:55:29
	 * @return void
	 */
	public void editOneGrade(Tbgrade tbgrade) {
		tbgradeDao.update(tbgrade);		
	}

	public List<Tbgrade> getAll(String graWhere) {
		return tbgradeDao.getAllByConn(graWhere);
	}
	
}
