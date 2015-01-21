package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GraduateinfoDao;
import com.mvc.entity.Graduateinfo;
import com.mvc.exception.VerifyException;

/**
 * 毕业答辩服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class GraduateinfoService {

	@Autowired
	private GraduateinfoDao graduateinfoDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOne(Graduateinfo graduateinfo) throws VerifyException {
		graduateinfoDao.save(graduateinfo);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return Graduateinfo
	 */
	public Graduateinfo getOneRecordById(int id) {
		
		return graduateinfoDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneGraduateinfo(Graduateinfo graduateinfo) throws VerifyException {
		graduateinfoDao.remove(graduateinfo);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneGraduateinfo(Graduateinfo graduateinfo) throws VerifyException {
		graduateinfoDao.update(graduateinfo);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return List<Graduateinfo>
	 * @throws VerifyException 
	 */
	public List<Graduateinfo> getAllRowsByWhere(String where) throws VerifyException {
		
		return graduateinfoDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return List<Graduateinfo>
	 * @throws VerifyException 
	 */
	public List<Graduateinfo> getAllRecordByPages(String where, Pagination pagination) throws VerifyException {
		return graduateinfoDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件得到一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-23 下午04:18:46
	 * @return Graduateinfo
	 * @throws VerifyException 
	 */
	public Graduateinfo getRecordByWhere(String where) throws VerifyException {
		
		return graduateinfoDao.getOne(where);
	}

	/**
	 * 带返回值的添加方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param graduateinfo
	 * @return
	 */
	public int addOneReturn(Graduateinfo graduateinfo) {
		return graduateinfoDao.saveReturn(graduateinfo);
	}
	
}
