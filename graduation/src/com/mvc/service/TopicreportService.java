package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.TopicreportDao;
import com.mvc.entity.Topicreport;
import com.mvc.exception.VerifyException;

/**
 * 开题报告书服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TopicreportService {

	@Autowired
	private TopicreportDao topicreportDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 16:12:54
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Topicreport topicreport) throws VerifyException {
		topicreportDao.save(topicreport);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 16:12:54
	 * @return Topicreport
	 */
	public Topicreport getOneRecordById(int id) {
		
		return topicreportDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 16:12:54
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTopicreport(Topicreport topicreport) throws VerifyException {
		topicreportDao.remove(topicreport);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 16:12:54
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTopicreport(Topicreport topicreport) throws VerifyException {
		topicreportDao.update(topicreport);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 16:12:54
	 * @return Object
	 * @throws VerifyException 
	 */
	public Object getAllRowsByWhere(String where) throws VerifyException {
		
		return topicreportDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-14 16:12:54
	 * @return List<Topicreport>
	 * @throws VerifyException 
	 */
	public List<Topicreport> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return topicreportDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 下午03:26:37
	 * @return Topicreport
	 * @throws VerifyException 
	 */
	public Topicreport getRecordByWhere(String where) throws VerifyException {
		return topicreportDao.getOne(where);
	}

	/**
	 * 保存一条记录，返回ID
	 * @param topicreport
	 * @return int
	 */
	public int addOneReturn(Topicreport topicreport) {
		return topicreportDao.addOneReturn(topicreport);
	}
	
}
