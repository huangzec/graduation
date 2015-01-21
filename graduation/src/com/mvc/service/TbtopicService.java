package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.SelectfirstDao;
import com.mvc.dao.TbtopicDao;
import com.mvc.entity.Tbtopic;
import com.mvc.exception.VerifyException;

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
	 * @throws VerifyException 
	 */
	public List<Tbtopic> getAll(String sql) throws VerifyException {
		return tbtopicDao.getAll(sql);
	}

	/**
	 * 
	 * 通过课题编号获取一条课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:54:47
	 * @return Tbtopic
	 * @throws VerifyException 
	 */
	public Tbtopic getByTopId(String topId) throws VerifyException {
		return tbtopicDao.getOne("from Tbtopic where topId='" + topId + "'");
	}

	/**
	 * 
	 * 添加一条课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 下午11:54:11
	 * @return void
	 * @throws VerifyException 
	 */
	public void save(Tbtopic topic) throws VerifyException {
		tbtopicDao.save(topic);
	}

	/**
	 * 
	 * 分页显示所有课题记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-28 上午9:45:56
	 * @return List<Tbtopic>
	 * @throws VerifyException 
	 */
	public List<Tbtopic> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return tbtopicDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 编辑一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-9 上午10:08:19
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneRecord(Tbtopic tbtopic) throws VerifyException {
		tbtopicDao.update(tbtopic);		
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-25 上午11:20:05
	 * @return Tbtopic
	 * @throws VerifyException 
	 */
	public Tbtopic getRecordByWhere(String where) throws VerifyException {
		return tbtopicDao.getOne(where);
	}

	/**
	 * 
	 * delete one topic info 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-12 下午05:52:43
	 * @return void
	 * @throws VerifyException 
	 */
	public void remove(Tbtopic topic) throws VerifyException {
		tbtopicDao.remove(topic);
	}

}
