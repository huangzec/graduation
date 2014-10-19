package com.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.MessageDao;
import com.mvc.entity.Message;

/**
 * 消息服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;

	/**
	 * 获取消息分页列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 上午09:33:45
	 * @return List<Message>
	 */
	public List<Message> getAllRecordByPages(String where, Pagination pagination) {
		
		return messageDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 得到所有消息记录ListMap
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午09:49:44
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> getAllRecords(String where) {
		
		return messageDao.getAllRecords(where);
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-4 上午10:59:28
	 * @return Message
	 */
	public Message getRecordById(int id) {
		
		return messageDao.getById(id);
	}

	/**
	 * 编辑一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-4 上午11:51:19
	 * @return void
	 */
	public void editOneRecord(Message message) {
		messageDao.update(message);		
	}

	/**
	 * 保存一条消息记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-28 上午11:13:19
	 * @return void
	 */
	public void saveMessage(Message message) {
		messageDao.save(message);
	}

}
