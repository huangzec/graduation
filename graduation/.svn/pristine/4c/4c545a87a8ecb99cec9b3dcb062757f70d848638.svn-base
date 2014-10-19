package com.mvc.dao;

import java.util.List;
import java.util.Map;

import com.mvc.entity.Message;

/**
 * 消息模块DAO
 * @author huangzec@foxmail.com
 *
 */
public class MessageDao extends BaseDao<Message>{

	/**
	 * 得到Map类型的所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-4 上午10:19:44
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> getAllRecords(String where) {
		
		return this.getHibernateTemplate().find(where);
	}

}
