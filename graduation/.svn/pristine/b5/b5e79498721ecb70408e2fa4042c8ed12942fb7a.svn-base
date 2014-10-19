package com.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Tboffice;
import com.mvc.entity.User;

@Repository
public class TbofficeDao extends BaseDao<Tboffice> {

	/**
	 * 获取一个教务处管理员
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午08:33:18
	 * @return Tboffice
	 */
	public Tboffice getOneTboffice(String userName) {
		List list = this.getHibernateTemplate().find("from Tboffice where offId = '" + userName + "'");
		if(list == null || list.size() < 1) {
			return null;
		}
		
		return (Tboffice) list.get(0);
	}

}
