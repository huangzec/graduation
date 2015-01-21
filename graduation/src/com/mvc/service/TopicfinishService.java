package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TopicfinishDao;
import com.mvc.entity.Topicfinish;
import com.mvc.exception.VerifyException;

/**
 * 毕业答辩相关材料服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TopicfinishService {

	@Autowired
	private TopicfinishDao topicfinishDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Topicfinish topicfinish) throws VerifyException {
		topicfinishDao.save(topicfinish);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return Topicfinish
	 */
	public Topicfinish getOneRecordById(int id) {
		
		return topicfinishDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTopicfinish(Topicfinish topicfinish) throws VerifyException {
		topicfinishDao.remove(topicfinish);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTopicfinish(Topicfinish topicfinish) throws VerifyException {
		topicfinishDao.update(topicfinish);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return Object
	 * @throws VerifyException 
	 */
	public List<Topicfinish> getAllRowsByWhere(String where) throws VerifyException {
		
		return topicfinishDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return List<Topicfinish>
	 * @throws VerifyException 
	 */
	public List<Topicfinish> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return topicfinishDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 保存一条记录，返回ID
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param topicfinish
	 * @return
	 */
	public int addOneReturn(Topicfinish topicfinish) {
		return topicfinishDao.saveReturn(topicfinish);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Topicfinish getRecordByWhere(String where) throws VerifyException {
		return topicfinishDao.getOne(where);
	}
	
}
