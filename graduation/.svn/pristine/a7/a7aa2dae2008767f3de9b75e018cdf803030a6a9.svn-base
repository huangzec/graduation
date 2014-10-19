package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.mvc.entity.Student;

public class StudentDao extends BaseDao<Student> {

	/**
	 * 获取学生相关的信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-16 下午07:52:23
	 * @return Student
	 */
	public Map<String, String> getStudentInfo(final String where) {
		final Map<String, String> map 	= new LinkedHashMap<String, String>();
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Connection con = session.connection();  
                PreparedStatement ps = con.prepareStatement(where);
                ResultSet rs = ps.executeQuery();
              //检索此 ResultSet 对象的列的编号、类型和属性。 
                ResultSetMetaData rsmd = rs.getMetaData();  
                //得到当前的列数
                int colCount = rsmd.getColumnCount();    
                while(rs.next())  
                {
	                for(int i = 1; i <= colCount; i ++)  
	                {
	                	map.put(rsmd.getColumnName(i), rs.getString(i));
	                 }
                }
				return map;
			}
		});
		
		return map;
	}

}
