package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.RevieworderDao;
import com.mvc.entity.Revieworder;
import com.mvc.exception.VerifyException;

/**
 * 毕业评阅安排服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class RevieworderService {

	@Autowired
	private RevieworderDao revieworderDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Revieworder revieworder) throws VerifyException {
		revieworderDao.save(revieworder);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return Revieworder
	 */
	public Revieworder getOneRecordById(int id) {
		
		return revieworderDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneRevieworder(Revieworder revieworder) throws VerifyException {
		revieworderDao.remove(revieworder);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneRevieworder(Revieworder revieworder) throws VerifyException {
		revieworderDao.update(revieworder);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return List<Revieworder>
	 * @throws VerifyException 
	 */
	public List<Revieworder> getAllRowsByWhere(String where) throws VerifyException {
		
		return revieworderDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return List<Revieworder>
	 * @throws VerifyException 
	 */
	public List<Revieworder> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return revieworderDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午09:49:18
	 * @return Revieworder
	 * @throws VerifyException 
	 */
	public Revieworder getRecordByWhere(String where) throws VerifyException {
		return revieworderDao.getOne(where);
	}
	
}
