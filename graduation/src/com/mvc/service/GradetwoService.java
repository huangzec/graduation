package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GradetwoDao;
import com.mvc.entity.Gradetwo;
import com.mvc.exception.VerifyException;

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
	 * @throws VerifyException 
	 */
	public void addOne(Gradetwo gradetwo) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	public void removeOneGradetwo(Gradetwo gradetwo) throws VerifyException {
		gradetwoDao.remove(gradetwo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneGradetwo(Gradetwo gradetwo) throws VerifyException {
		gradetwoDao.update(gradetwo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return List<Gradetwo>
	 * @throws VerifyException 
	 */
	public List<Gradetwo> getAllRowsByWhere(String where) throws VerifyException {
		
		return gradetwoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-27 11:39:53
	 * @return List<Gradetwo>
	 * @throws VerifyException 
	 */
	public List<Gradetwo> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return gradetwoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 通过where获取一条记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 下午05:31:09
	 * @return Gradetwo
	 * @throws VerifyException 
	 */
	public Gradetwo getRecordByWhere(String where) throws VerifyException {
		return gradetwoDao.getOne(where);
	}
	
}
