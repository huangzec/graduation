package com.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.JudgeDao;
import com.mvc.entity.Reviewresult;
import com.mvc.exception.VerifyException;

/**
 * 课题评审服务层
 * @author huangzec@foxmail.com
 *
 */
@Service
public class JudgeService {
	
	@Autowired
	private JudgeDao judgeDao;

	/**
	 * 添加一个评审结果
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-8 下午12:02:53
	 * @return void
	 * @throws VerifyException 
	 */
	public void addOneReviewResult(Reviewresult reviewResult) throws VerifyException {
		judgeDao.save(reviewResult);
	}

	/**
	 * 通过where条件获取一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-8 下午12:08:16
	 * @return Reviewresult
	 * @throws VerifyException 
	 */
	public Reviewresult getRecordByWhere(String where) throws VerifyException {
		return judgeDao.getOne(where);
	}

	/**
	 * 获取总数
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-8 下午04:38:39
	 * @return Map<String,Integer>
	 */
	public Map<String, String> getCountByWhere(String sql) {
		return judgeDao.getCountByWhere(sql);
	}

	/**
	 * 通过where条件获取记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-8 下午08:46:20
	 * @return List<Reviewresult>
	 * @throws VerifyException 
	 */
	public List<Reviewresult> getSomeRows(String where) throws VerifyException {
		return judgeDao.getAll(where);
	}

}
