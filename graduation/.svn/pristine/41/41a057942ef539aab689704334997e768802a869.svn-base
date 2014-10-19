package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GradeoneDao;
import com.mvc.entity.Gradeone;

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
	 */
	public void addOne(Gradeone gradeone) {
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
	 */
	public void removeOneGradeone(Gradeone gradeone) {
		gradeoneDao.remove(gradeone);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return void
	 */
	public void editOneGradeone(Gradeone gradeone) {
		gradeoneDao.update(gradeone);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return List<Gradeone>
	 */
	public List<Gradeone> getAllRowsByWhere(String where) {
		
		return gradeoneDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-24 14:14:55
	 * @return List<Gradeone>
	 */
	public List<Gradeone> getAllRecordByPages(String where, Pagination pagination) {
		return gradeoneDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午10:29:19
	 * @return Gradeone
	 */
	public Gradeone getRecordByWhere(String where) {
		return gradeoneDao.getOne(where);
	}
	
}
