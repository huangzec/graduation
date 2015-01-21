package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TopicorderreviewDao;
import com.mvc.entity.Topicorderreview;
import com.mvc.exception.VerifyException;

/**
 * 课题评审教师安排服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TopicorderreviewService {

	@Autowired
	private TopicorderreviewDao topicorderreviewDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Topicorderreview topicorderreview) throws VerifyException {
		topicorderreviewDao.save(topicorderreview);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return Topicorderreview
	 */
	public Topicorderreview getOneRecordById(int id) {
		
		return topicorderreviewDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTopicorderreview(Topicorderreview topicorderreview) throws VerifyException {
		topicorderreviewDao.remove(topicorderreview);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTopicorderreview(Topicorderreview topicorderreview) throws VerifyException {
		topicorderreviewDao.update(topicorderreview);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return List<Topicorderreview>
	 * @throws VerifyException 
	 */
	public List<Topicorderreview> getAllRowsByWhere(String where) throws VerifyException {
		
		return topicorderreviewDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return List<Topicorderreview>
	 * @throws VerifyException 
	 */
	public List<Topicorderreview> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return topicorderreviewDao.getAllRecordByPages(where, pagination);
	}
	
	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Topicorderreview getRecordByWhere(String where) throws VerifyException {
		return topicorderreviewDao.getOne(where);
	}
	
}
