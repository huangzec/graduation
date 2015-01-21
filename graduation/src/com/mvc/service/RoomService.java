package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.RoomDao;
import com.mvc.entity.Room;
import com.mvc.exception.VerifyException;

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
	 * @throws VerifyException 
	 */
	public void addOne(Room room) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	public void removeOneRoom(Room room) throws VerifyException {
		roomDao.remove(room);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneRoom(Room room) throws VerifyException {
		roomDao.update(room);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return Object
	 * @throws VerifyException 
	 */
	public Object getAllRowsByWhere(String where) throws VerifyException {
		
		return roomDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return List<Room>
	 * @throws VerifyException 
	 */
	public List<Room> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return roomDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-16 下午04:59:59
	 * @return Room
	 * @throws VerifyException 
	 */
	public Room getOneRecordByWhere(String where) throws VerifyException {
		return roomDao.getOne(where);
	}
	
}
