package com.mvc.test;

import org.junit.Test;

import com.mvc.common.Verify;

/**
 * 验证测试类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2014-10-24 下午9:04:24 
 * @version 	1.0
 */
public class VerifyTest {

	/*@Test
	public void isNumberTest()
	{
		System.out.println(Verify.isNumber("1514544"));
	}
	
	@Test
	public void isEmailTest()
	{
		System.out.println(Verify.isEmail("fdsfsdf6@qq.com"));
	}*/
	
	@Test
	public void isScoreTest()
	{
		String str = "85544455.051548154";
		System.out.println(Verify.isScore(str));
	}
}
