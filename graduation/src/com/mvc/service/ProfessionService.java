package com.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.Pagination;
import com.mvc.dao.ProfessionDao;
import com.mvc.entity.Profession;
import com.mvc.exception.VerifyException;

/**
 * 专业服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class ProfessionService {

	@Autowired
	private ProfessionDao professionDao;

	/**
	 * 通过ID获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午03:40:42
	 * @return Profession
	 */
	public Profession getOneById(String id) {
		
		return professionDao.getById(id);
	}

	/**
	 * 添加一个专业
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午03:49:14
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOneProfession(Profession profession) throws VerifyException {
		professionDao.save(profession);		
	}

	/**
	 * 专业列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午04:06:34
	 * @return List<Profession>
	 * @throws VerifyException 
	 */
	public List<Profession> getAllRecordByPages(String where,
			Pagination pagination) throws VerifyException {
		
		return professionDao.getAllRecordByPages(where, pagination);
	}

	/**
	 * 删除一个专业
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午05:08:39
	 * @return void
	 * @throws VerifyException 
	 */
	public void removeOneProfession(Profession profession) throws VerifyException {
		professionDao.remove(profession);		
	}

	/**
	 * 编辑一个专业
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午07:05:00
	 * @return void
	 * @throws VerifyException 
	 */
	public void editOneProfession(Profession profession) throws VerifyException {
		professionDao.update(profession);		
	}

	/**
	 * 通过where条件获取所有记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午10:34:30
	 * @return List
	 * @throws VerifyException 
	 */
	public List getAllRowsByWhere(String where) throws VerifyException {
		
		return professionDao.getAll(where);
	}

	/**
	 * 通过where条件得到关联的记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-13 下午05:59:31
	 * @return Profession
	 */
	public Map<String, String> getLinkedRecordByWhere(String prowhere) {
		
		return professionDao.getLinkedRecordByWhere(prowhere);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param where
	 * @return
	 * @throws VerifyException 
	 */
	public Profession getRecordByWhere(String where) throws VerifyException {
		
		return professionDao.getOne(where);
	}
	
}
