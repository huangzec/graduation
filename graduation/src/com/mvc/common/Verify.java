package com.mvc.common;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * @author huangzec@foxmail.com
 *
 */
public class Verify {
	
	/**
	 * 检测当前对象是不是为空
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午01:48:37
	 * @return boolean
	 */
	public static boolean isEmpty(Object obj)
	{
		if(null == obj) {
			return true;
		}
		if("".equals(obj.toString().trim())) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检测列表是不是为空
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list)
	{
		if(null == list) {
			return true;
		}
		if(0 == list.size()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检测Map是不是为空
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) 
	{
		if(null == map || map.isEmpty() || 1 > map.size()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检测字符串是不是数字
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str)
	{
		String regex = "^\\d+$";
		
		return isMatch(regex, str);
	}
	
	/**
	 * 检测是不是分数
	 * 60.12 , 60 
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param str
	 * @return
	 */
	public static boolean isScore(String str)
	{
		String regex = "^(\\d+)(\\.\\d+)?$";
		
		return isMatch(regex, str);
	}
	
	/**
	 * 验证正则是否匹配
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean isMatch(String regex, String str)
	{
		if(isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str.trim());
		if(!matcher.matches()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 检测字符串长度
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param str
	 * @param min
	 * @param max
	 */
	public static boolean isStrLen(String str, int min, int max)
	{
		if(isEmpty(str)) {
			if(min == 0) {
				return true;
			}
			
			return false;
		}
		if(isEmpty(str) || str.length() < min || str.length() > max) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 检测是不是邮箱
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str)
	{
		String regex = "^[\\w]+@[\\w]+(\\.\\w+)+$";
		
		return isMatch(regex, str);
	}

}
