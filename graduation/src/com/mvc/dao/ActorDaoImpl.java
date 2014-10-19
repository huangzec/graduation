package com.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mvc.common.Pagination;

public class ActorDaoImpl extends HibernateDaoSupport implements ActorDao {

	/*public List<Actor> getAllRecordByPages(String where, Pagination pagination) {
		
		String hql = "from Actor where " + where + " order by id desc";
		List<Actor> list = getHibernateTemplate().find(hql);
		if(list == null || list.size() < 1) {
			return null;
		}
		
		int size 	= pagination.getSize();
		int pagenum = pagination.getCurrentPage();
		List<Actor> listReturn = new ArrayList<Actor>();
		listReturn.clear();
		int currentPageStart 	= (pagenum - 1) * size;
		int currentPageEnd 		= pagenum * size - 1;
		while(currentPageStart <= currentPageEnd && currentPageStart < list.size())
		{
			Actor actor = (Actor) list.get(currentPageStart);
			listReturn.add(actor);
			currentPageStart++;
		}
		pagination.setTotalRecord(list.size());
		int totalPage = list.size()/size;
		if(totalPage * size < list.size()) {
			totalPage++;
		}
		pagination.setTotalPage(totalPage);
		
		return listReturn;
	}
*/
}
