package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.GradethreeDao;
import com.mvc.entity.Gradethree;

/**
 * 毕业答辩成绩模块服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class GradethreeService {

	@Autowired
	private GradethreeDao gradethreeDao;

	/**
	 * 添加
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-03 21:49:28
	 * @return void
	 */
	public void addOne(Gradethree gradethree) {
		gradethreeDao.save(gradethree);		
	}

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-03 21:49:28
	 * @return Gradethree
	 */
	public Gradethree getOneRecordById(int id) {
		
		return gradethreeDao.getById(id);
	}

	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-03 21:49:28
	 * @return void
	 */
	public void removeOneGradethree(Gradethree gradethree) {
		gradethreeDao.remove(gradethree);		
	}

	/**
	 * 编辑
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-03 21:49:28
	 * @return void
	 */
	public void editOneGradethree(Gradethree gradethree) {
		gradethreeDao.update(gradethree);		
	}

	/**
	 * 获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-03 21:49:28
	 * @return List<Gradethree>
	 */
	public List<Gradethree> getAllRowsByWhere(String where) {
		
		return gradethreeDao.getAll(where);
	}
	
	/**
	 * 分页获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-03 21:49:28
	 * @return List<Gradethree>
	 */
	public List<Gradethree> getAllRecordByPages(String where, Pagination pagination) {
		return gradethreeDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-4 上午11:17:44
	 * @return Gradethree
	 */
	public Gradethree getRecordByWhere(String where) {
		return gradethreeDao.getOne(where);
	}

	/**
	 * 带返回值的添加
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param gradethree
	 * @return
	 */
	public int addOneReturn(Gradethree gradethree) {
		return gradethreeDao.saveReturn(gradethree);
	}
	
}
