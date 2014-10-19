package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.RoomDao;
import com.mvc.entity.Room;

/**
 * 教室服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class RoomService {

	@Autowired
	private RoomDao roomDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return void
	 */
	public void addOne(Room room) {
		roomDao.save(room);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return Room
	 */
	public Room getOneRecordById(int id) {
		
		return roomDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return void
	 */
	public void removeOneRoom(Room room) {
		roomDao.remove(room);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return void
	 */
	public void editOneRoom(Room room) {
		roomDao.update(room);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return Object
	 */
	public Object getAllRowsByWhere(String where) {
		
		return roomDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return List<Room>
	 */
	public List<Room> getAllRecordByPages(String where, Pagination pagination) {
		return roomDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-16 下午04:59:59
	 * @return Room
	 */
	public Room getOneRecordByWhere(String where) {
		return roomDao.getOne(where);
	}
	
}
