package com.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.TbofficeDao;
import com.mvc.entity.Tboffice;

/**
 * 教务处管理员服务层
 * 
 * @author Happy_Jqc@163.com
 *
 */

@Service
public class TbofficeService {
	
	@Autowired
	private TbofficeDao tbofficeDao;

	/**
	 * 获取一个教务处管理员
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午08:30:29
	 * @return Tboffice
	 */
	public Tboffice getOneTbOffice(String where) {
		System.out.println(where);
		return tbofficeDao.getOne(where);
	}

	/**
	 * 
	 * 更新教务处管理员信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 下午7:30:14
	 * @return void
	 */
	public void editOffice(Tboffice tboffice) {
		tbofficeDao.update(tboffice);
	}

}
