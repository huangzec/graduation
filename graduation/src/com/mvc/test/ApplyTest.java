package com.mvc.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mvc.controller.ApplySiteController;
import com.mvc.entity.Apply;


public class ApplyTest {

	/**
	 * whereIn语句测试
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 下午05:44:05
	 * @return void
	 */
	@Test
	public void whereInTest()
	{
		List<Apply> list = new ArrayList<Apply>();
		Apply stu = new Apply();
		stu.setUserId("1106401001");
		Apply stu1 = new Apply();
		stu1.setUserId("1106401042");
		Apply stu2 = new Apply();
		stu2.setUserId("1106401001");
		list.add(stu);
		list.add(stu1);
		list.add(stu2);
		//ApplySiteController._assignStudentListMap(list);
	}
	
	@Test
	public void test()
	{
		System.out.println("true or false : " + (!true));
		if(false) {
			System.out.println("true");
		}
		if(!true) {
			System.out.println("!true");
		}
		if(!false) {
			System.out.println("!false");
		}
	}
	
	@Test
	public void testWhile()
	{
		int i = 0;
		while(i++ < 4) {
			System.out.println("i 的值为 " + i );
			break;
		}
	}
	
	@Test
	public void testDouble()
	{
		System.out.println(new BigDecimal("99.4").stripTrailingZeros());
	}
}
