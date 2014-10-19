package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.mvc.entity.Reviewresult;

public class JudgeDao extends BaseDao<Reviewresult>{

	/**
	 * 获取Map类型的数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-8 下午04:39:47
	 * @return Map<String,Integer>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getCountByWhere(String sql) {
		final Map<String, String> map = new LinkedHashMap<String, String>();
		//Integer count = (Integer) getHibernateTemplate().find(sql).listIterator().next();
		final String hql = "select status, count(*) from reviewresult where top_ID =  '20140804170649' group by status asc";
		//List list = getHibernateTemplate().find(hql);
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println("hql " + hql);
				Connection con = session.connection();  
                PreparedStatement ps = con.prepareStatement(hql); 
                System.out.println("con " + con);
                ResultSet rs = ps.executeQuery(); 
                System.out.println("rs " + rs);
				while(rs.next()) {
					map.put("status", rs.getString("status"));
					//map.put("count", rs.getString("count"));
					System.out.println("record " + rs.getString("status"));
					System.out.println("record " + rs.next());
				}
				return map;
			}
		});
		System.out.println("list is ");
		//if(list != null) {
			//System.out.println("list " + list.get(0));
		//}
		
		return map;
	}

}
