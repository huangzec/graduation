package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TopicapplyDao;
import com.mvc.entity.Topicapply;
import com.mvc.exception.VerifyException;

/**
 * 开题答辩申请书服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TopicapplyService {

	@Autowired
	private TopicapplyDao topicapplyDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Topicapply topicapply) throws VerifyException {
		topicapplyDao.save(topicapply);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return Topicapply
	 */
	public Topicapply getOneRecordById(int id) {
		
		return topicapplyDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTopicapply(Topicapply topicapply) throws VerifyException {
		topicapplyDao.remove(topicapply);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTopicapply(Topicapply topicapply) throws VerifyException {
		topicapplyDao.update(topicapply);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return Object
	 * @throws VerifyException 
	 */
	public Object getAllRowsByWhere(String where) throws VerifyException {
		
		return topicapplyDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return List<Topicapply>
	 * @throws VerifyException 
	 */
	public List<Topicapply> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return topicapplyDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-8 下午08:16:34
	 * @return Topicapply
	 * @throws VerifyException 
	 */
	public Topicapply getRecordByWhere(String where) throws VerifyException {
		return topicapplyDao.getOne(where);
	}

	/**
	 * 保存一条记录，返回ID
	 * @param topicapply
	 * @return
	 */
	public int addOneReturn(Topicapply topicapply) {
		return topicapplyDao.addOneReturn(topicapply);
	}
	
}
