package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GradeoneDao;
import com.mvc.entity.Gradeone;
import com.mvc.exception.VerifyException;

/**
 * 成绩表一服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class GradeoneService {

	@Autowired
	private GradeoneDao gradeoneDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Gradeone gradeone) throws VerifyException {
		gradeoneDao.save(gradeone);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return Gradeone
	 */
	public Gradeone getOneRecordById(int id) {
		
		return gradeoneDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneGradeone(Gradeone gradeone) throws VerifyException {
		gradeoneDao.remove(gradeone);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneGradeone(Gradeone gradeone) throws VerifyException {
		gradeoneDao.update(gradeone);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return List<Gradeone>
	 * @throws VerifyException 
	 */
	public List<Gradeone> getAllRowsByWhere(String where) throws VerifyException {
		
		return gradeoneDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return List<Gradeone>
	 * @throws VerifyException 
	 */
	public List<Gradeone> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return gradeoneDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午10:29:19
	 * @return Gradeone
	 * @throws VerifyException 
	 */
	public Gradeone getRecordByWhere(String where) throws VerifyException {
		return gradeoneDao.getOne(where);
	}
	
}
