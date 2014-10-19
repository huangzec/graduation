package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.mvc.entity.Tbclass;

public class TbclassDao extends BaseDao<Tbclass>{

	public List<Tbclass> getAllByConn(final String claWhere) {
		final List<Tbclass> classlist = new ArrayList<Tbclass>();
		this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Connection conn = session.connection();
				PreparedStatement ps = conn.prepareStatement(claWhere);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Tbclass tbclass = new Tbclass();
					tbclass.setClaId(Integer.parseInt(rs.getString("cla_ID")));
					tbclass.setClaName(rs.getString("cla_Name"));
					tbclass.setProId(rs.getString("pro_ID"));
					classlist.add(tbclass);
				}
				return classlist;
			}
		});
		return classlist;
	}

}
