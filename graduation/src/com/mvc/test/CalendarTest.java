package com.mvc.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.mvc.common.HResponse;

public class CalendarTest {

	@SuppressWarnings("static-access")
	@Test
	public void testWeek()
	{
		String str[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};//字符串数组  
        Calendar rightNow = Calendar.getInstance();  
        int day = rightNow.get(rightNow.DAY_OF_WEEK);//获取时间  
        System.out.println("今天是"+str[day-1]);//通过数组把周几输出  
        Date now=new Date();  
        System.out.println("现在的时间是："+now); 
	}
	
	@Test
	public void testArrayArray()
	{
		String str[][] = {{"jfjjf", "jfjj"}, {"jjjfjf"}, {"jfjsdlfjj"}};
		System.out.println(str[0].length);
	}
	
	@Test
	public void testGetCurrentYear()
	{
		System.out.println(HResponse.getCurrentYear());
	}
}
