package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.RevieworderDao;
import com.mvc.entity.Revieworder;

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
	 */
	public void addOne(Revieworder revieworder) {
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
	 */
	public void removeOneRevieworder(Revieworder revieworder) {
		revieworderDao.remove(revieworder);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return void
	 */
	public void editOneRevieworder(Revieworder revieworder) {
		revieworderDao.update(revieworder);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return List<Revieworder>
	 */
	public List<Revieworder> getAllRowsByWhere(String where) {
		
		return revieworderDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return List<Revieworder>
	 */
	public List<Revieworder> getAllRecordByPages(String where, Pagination pagination) {
		return revieworderDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午09:49:18
	 * @return Revieworder
	 */
	public Revieworder getRecordByWhere(String where) {
		return revieworderDao.getOne(where);
	}
	
}
