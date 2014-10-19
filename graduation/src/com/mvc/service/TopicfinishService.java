package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TopicfinishDao;
import com.mvc.entity.Topicfinish;

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
	 */
	public void addOne(Topicfinish topicfinish) {
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
	 */
	public void removeOneTopicfinish(Topicfinish topicfinish) {
		topicfinishDao.remove(topicfinish);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return void
	 */
	public void editOneTopicfinish(Topicfinish topicfinish) {
		topicfinishDao.update(topicfinish);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return Object
	 */
	public List<Topicfinish> getAllRowsByWhere(String where) {
		
		return topicfinishDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 20:06:01
	 * @return List<Topicfinish>
	 */
	public List<Topicfinish> getAllRecordByPages(String where, Pagination pagination) {
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
	 */
	public Topicfinish getRecordByWhere(String where) {
		return topicfinishDao.getOne(where);
	}
	
}