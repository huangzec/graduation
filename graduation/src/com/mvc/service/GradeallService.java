package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GradeallDao;
import com.mvc.entity.Gradeall;
import com.mvc.exception.VerifyException;

/**
 * 服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class GradeallService {

	@Autowired
	private GradeallDao gradeallDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 17:41:47
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Gradeall gradeall) throws VerifyException {
		gradeallDao.save(gradeall);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 17:41:47
	 * @return Gradeall
	 */
	public Gradeall getOneRecordById(int id) {
		
		return gradeallDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 17:41:47
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneGradeall(Gradeall gradeall) throws VerifyException {
		gradeallDao.remove(gradeall);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 17:41:47
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneGradeall(Gradeall gradeall) throws VerifyException {
		gradeallDao.update(gradeall);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 17:41:47
	 * @return List<Gradeall>
	 * @throws VerifyException 
	 */
	public List<Gradeall> getAllRowsByWhere(String where) throws VerifyException {
		
		return gradeallDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 17:41:47
	 * @return List<Gradeall>
	 * @throws VerifyException 
	 */
	public List<Gradeall> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return gradeallDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 
	 * 通过where获取一条记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-2 下午05:45:33
	 * @return Gradeall
	 * @throws VerifyException 
	 */
	public Gradeall getRecordByWhere(String where) throws VerifyException {
		return gradeallDao.getOne(where);
	}

	/**
	 * 带返回值的添加
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param gradeall
	 * @return
	 */
	public int addOneReturn(Gradeall gradeall) {
		return gradeallDao.saveReturn(gradeall);
	}
	
}
