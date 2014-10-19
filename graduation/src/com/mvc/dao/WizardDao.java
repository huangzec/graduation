package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class WizardDao extends HibernateDaoSupport {

	/**
	 * 获取所有的表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 上午11:01:30
	 * @return List<Map<String,String>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getAllTables()
	{
		final String where = "show tables";
		return getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<Map<String, String>> list = null;
				Connection con = session.connection();  
                PreparedStatement ps = con.prepareStatement(where); 
                ResultSet rs = ps.executeQuery(); 
                
                ResultSetMetaData md 	= rs.getMetaData();
        		int columnCount 		= md.getColumnCount();// 获取每条记录的总列数
        		rs.last();
        		int resultNum 			= rs.getRow();// 获取返回结果的行数
        		if (resultNum > 0) {// 如果结果行数大于0,list不为空
        			list 		= new ArrayList<Map<String, String>>(resultNum);
    				Map<String, String> map = null;
        			rs.beforeFirst();
        			int j =1;
        			while (rs.next()) {
        				map 	= new HashMap<String, String>();
        				for (int i = 1; i <= columnCount; i++) {
        					map.put("id", j + "");
        					map.put("name", rs.getString(i));
        				}
        				j ++;
        				list.add(map);
        			}
        		}
				
				return list;
			}
		});
	}
}
