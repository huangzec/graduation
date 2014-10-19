package com.mvc.test;

import org.junit.Test;

import com.mvc.dao.ProfessionDao;


public class ProfessionDaoTest {

	@Test
	public void getLinkedRecordByWhere()
	{
		ProfessionDao pro = new ProfessionDao();
		String where = "select * from profession where `pro_ID` = (" +
				"select pro_ID from tbclass where cla_ID = (" +
				"select cla_ID from student where stu_ID = 1106401001))";
		pro.getLinkedRecordByWhere(where);
	}
}
