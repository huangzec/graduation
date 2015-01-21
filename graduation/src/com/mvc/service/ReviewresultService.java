package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.ReviewresultDao;
import com.mvc.entity.Reviewresult;
import com.mvc.exception.VerifyException;

/**
 * 课题评审服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class ReviewresultService {

	@Autowired
	private ReviewresultDao reviewresultDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-20 21:24:04
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Reviewresult reviewresult) throws VerifyException {
		reviewresultDao.save(reviewresult);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-20 21:24:04
	 * @return Reviewresult
	 */
	public Reviewresult getOneRecordById(int id) {
		
		return reviewresultDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-20 21:24:04
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneReviewresult(Reviewresult reviewresult) throws VerifyException {
		reviewresultDao.remove(reviewresult);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-20 21:24:04
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneReviewresult(Reviewresult reviewresult) throws VerifyException {
		reviewresultDao.update(reviewresult);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-20 21:24:04
	 * @return List<Reviewresult>
	 * @throws VerifyException 
	 */
	public List<Reviewresult> getAllRowsByWhere(String where) throws VerifyException {
		
		return reviewresultDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-20 21:24:04
	 * @return List<Reviewresult>
	 * @throws VerifyException 
	 */
	public List<Reviewresult> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return reviewresultDao.getAllRecordByPages(where, pagination);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Reviewresult getRecordByWhere(String where) throws VerifyException {
		return reviewresultDao.getOne(where);
	}
	
}
