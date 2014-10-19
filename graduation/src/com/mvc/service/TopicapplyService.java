package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TopicapplyDao;
import com.mvc.entity.Topicapply;

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
	 */
	public void addOne(Topicapply topicapply) {
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
	 */
	public void removeOneTopicapply(Topicapply topicapply) {
		topicapplyDao.remove(topicapply);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return void
	 */
	public void editOneTopicapply(Topicapply topicapply) {
		topicapplyDao.update(topicapply);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return Object
	 */
	public Object getAllRowsByWhere(String where) {
		
		return topicapplyDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 11:33:35
	 * @return List<Topicapply>
	 */
	public List<Topicapply> getAllRecordByPages(String where, Pagination pagination) {
		return topicapplyDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-8 下午08:16:34
	 * @return Topicapply
	 */
	public Topicapply getRecordByWhere(String where) {
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