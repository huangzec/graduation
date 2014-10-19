package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.MeetingDao;
import com.mvc.entity.Meeting;

/**
 * 会议|会议模块服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class MeetingService {

	@Autowired
	private MeetingDao meetingDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 15:53:16
	 * @return void
	 */
	public void addOne(Meeting meeting) {
		meetingDao.save(meeting);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 15:53:16
	 * @return Meeting
	 */
	public Meeting getOneRecordById(int id) {
		
		return meetingDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 15:53:16
	 * @return void
	 */
	public void removeOneMeeting(Meeting meeting) {
		meetingDao.remove(meeting);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 15:53:16
	 * @return void
	 */
	public void editOneMeeting(Meeting meeting) {
		meetingDao.update(meeting);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 15:53:16
	 * @return List<Meeting>
	 */
	public List<Meeting> getAllRowsByWhere(String where) {
		
		return meetingDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 15:53:16
	 * @return List<Meeting>
	 */
	public List<Meeting> getAllRecordByPages(String where, Pagination pagination) {
		return meetingDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 添加一条记录，并返回其id
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午08:00:47
	 * @return int
	 */
	public int addRecord(Meeting meeting) {
		return meetingDao.saveReturn(meeting);
	}

	/**
	 * 带返回值的添加方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param meeting
	 * @return
	 */
	public int addOneReturn(Meeting meeting) {
		return meetingDao.saveReturn(meeting);
	}
	
}
