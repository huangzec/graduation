package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.SelectfirstDao;
import com.mvc.dao.TbtopicDao;
import com.mvc.entity.Tbtopic;

/**
 * 课题服务层
 * 
 * @author Happy_Jqc@163.com
 *
 */

@Service
public class TbtopicService {

	@Autowired
	private TbtopicDao tbtopicDao;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	/**
	 * 
	 * 获取所有课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:40:40
	 * @return List<Tbtopic>
	 */
	public List<Tbtopic> getAll(String sql) {
		return tbtopicDao.getAll(sql);
	}

	/**
	 * 
	 * 通过课题编号获取一条课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:54:47
	 * @return Tbtopic
	 */
	public Tbtopic getByTopId(String topId) {
		return tbtopicDao.getOne("from Tbtopic where topId='" + topId + "'");
	}

	/**
	 * 
	 * 添加一条课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 下午11:54:11
	 * @return void
	 */
	public void save(Tbtopic topic) {
		tbtopicDao.save(topic);
	}

	/**
	 * 
	 * 分页显示所有课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-28 上午9:45:56
	 * @return List<Tbtopic>
	 */
	public List<Tbtopic> getAllRecordByPages(String where, Pagination pagination) {
		return tbtopicDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 编辑一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-9 上午10:08:19
	 * @return void
	 */
	public void editOneRecord(Tbtopic tbtopic) {
		tbtopicDao.update(tbtopic);		
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-25 上午11:20:05
	 * @return Tbtopic
	 */
	public Tbtopic getRecordByWhere(String where) {
		return tbtopicDao.getOne(where);
	}

}
