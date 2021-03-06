package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.TbclassDao;
import com.mvc.entity.Tbclass;
import com.mvc.exception.VerifyException;

/**
 * 班级服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class TbclassService {

	@Autowired
	private TbclassDao tbclassDao;

	/**
	 * 添加一个班级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午11:17:03
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOneTbclass(Tbclass tbclass) throws VerifyException {
		tbclassDao.save(tbclass);		
	}

	/**
	 * 通过ID获取一个班级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午11:17:41
	 * @return Tbclass
	 */
	public Tbclass getOneById(int id) {
		
		return tbclassDao.getById(id);
	}

	/**
	 * 删除一个班级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午02:39:41
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneTbclass(Tbclass tbclass) throws VerifyException {
		tbclassDao.remove(tbclass);		
	}

	/**
	 * 编辑一个班级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午03:12:10
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneTbclass(Tbclass tbclass) throws VerifyException {
		tbclassDao.update(tbclass);		
	}

	/**
	 * 获取所有的班级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午02:37:47
	 * @return Object
	 * @throws VerifyException 
	 */
	public Object getAllRowsByWhere(String where) throws VerifyException {
		
		return tbclassDao.getAll(where);
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午02:47:54
	 * @return List<Tbclass>
	 * @throws VerifyException 
	 */
	public List<Tbclass> getAllRows(String where) throws VerifyException {
		
		return tbclassDao.getAll(where);
	}

	public List<Tbclass> getAllByConn(String claWhere) {
		return tbclassDao.getAllByConn(claWhere);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param string
	 * @return
	 * @throws VerifyException 
	 */
	public Tbclass getRecordByWhere(String where) throws VerifyException {
		return tbclassDao.getOne(where);
	}
	
}
