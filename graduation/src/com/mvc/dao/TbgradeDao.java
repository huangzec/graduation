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

import com.mvc.entity.Tbgrade;

public class TbgradeDao extends BaseDao<Tbgrade>{

	public List<Tbgrade> getAllByConn(final String graWhere) {
		final List<Tbgrade> gradelist = new ArrayList<Tbgrade>();
		this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Connection conn = session.connection();
				PreparedStatement ps = conn.prepareStatement(graWhere);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Tbgrade tbgrade = new Tbgrade();
					tbgrade.setGraId(Integer.parseInt(rs.getString("gra_ID")));
					tbgrade.setDeptId(rs.getString("dept_ID"));
					tbgrade.setGraNumber(rs.getString("gra_Number"));
					gradelist.add(tbgrade);
				}
				return gradelist;
			}
		});
		return gradelist;
	}
	
}
