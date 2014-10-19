package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GradetwoDao;
import com.mvc.entity.Gradetwo;

/**
 * 评阅教师评分服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class GradetwoService {

	@Autowired
	private GradetwoDao gradetwoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return void
	 */
	public void addOne(Gradetwo gradetwo) {
		gradetwoDao.save(gradetwo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return Gradetwo
	 */
	public Gradetwo getOneRecordById(int id) {
		
		return gradetwoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return void
	 */
	public void removeOneGradetwo(Gradetwo gradetwo) {
		gradetwoDao.remove(gradetwo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return void
	 */
	public void editOneGradetwo(Gradetwo gradetwo) {
		gradetwoDao.update(gradetwo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return List<Gradetwo>
	 */
	public List<Gradetwo> getAllRowsByWhere(String where) {
		
		return gradetwoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return List<Gradetwo>
	 */
	public List<Gradetwo> getAllRecordByPages(String where, Pagination pagination) {
		return gradetwoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 通过where获取一条记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 下午05:31:09
	 * @return Gradetwo
	 */
	public Gradetwo getRecordByWhere(String where) {
		return gradetwoDao.getOne(where);
	}
	
}
