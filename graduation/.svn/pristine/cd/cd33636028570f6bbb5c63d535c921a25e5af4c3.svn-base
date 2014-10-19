package com.mvc.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * @author huangzec@foxmail.com
 *
 */
public class StringUtil {
	
	/**
	 * 剪切字符串
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 上午10:52:54
	 * @return String
	 */
	public static String cutString(String str, int length)
	{
		if(null != str && length < str.length()) {
			return str.substring(0, length) + "...";
		}
		
		return str;
	}
	
	/**
	 * html 替换表
	 */
	private static String[][] _htmlCharMap 	= new String[][]{
		new String [] {"&", "<", ">", "\"", "'", "/"},
        new String[] {"&amp;", "&lt;", "&gt;", "&quot;", "&#x27;", "&#x2F;"}
	};
	
	/**
	 * html代码转码
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-28 下午04:14:52
	 * @return String
	 */
	public static String encodeHtml(String str)
	{
		if(null == str) {
			return "";
		}
		for(int i = 0; i < _htmlCharMap[0].length; i ++) {
			str 	= str.replace(_htmlCharMap[0][i], _htmlCharMap[1][i]);
		}
		
		return str;
	}
	
	/**
	 * html 代码解码
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-28 下午04:18:26
	 * @return String
	 */
	public static String decodeHtml(String str)
	{
		if(null == str) {
			return "";
		}
		for(int i = 0; i < _htmlCharMap[0].length; i ++) {
			str 	= str.replace(_htmlCharMap[1][i], _htmlCharMap[0][i]);
		}
		
		return str;
	}
	
	/**
	 * 字符串首字母大写
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 下午08:09:58
	 * @return String
	 */
	public static String ufirst(String str)
	{
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	
	/**
	 * 字符串首字母小写
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-4 上午09:50:41
	 * @return String
	 */
	public static String lfirst(String str)
	{
		byte[] chars = str.getBytes();
		if(chars[0] >= 'A' && chars[0] <= 'Z') {
			chars[0] = (byte) ((char) chars[0] - 'A' + 'a');
		}
		
		return new String(chars);
	}
	
	/**
	 * 替换掉下划线并让紧跟它后面的字母大写
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 下午08:34:07
	 * @return String
	 */
	public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)  
	{  
	   String newString = "";  
	   int first = 0;  
	   while(srcStr.indexOf(org)!=-1)  
	   {
		   first = srcStr.indexOf(org);  
		   if(first != srcStr.length()) {  
			   newString = newString + srcStr.substring(0, first) + ob;  
			   srcStr = srcStr.substring(first + org.length(), srcStr.length());  
			   srcStr = ufirst(srcStr);  
		   }  
	   }  
	   newString = newString + srcStr;  
	   
	   return newString;  
	} 
	
	/**
	 * 
	 * 字符串隔开 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-15 上午11:20:03
	 * @return String[]
	 */
	public static String[] splitString(String str){
		String[] arrayStr = null;
		return arrayStr = str.split(",");
	}
	
}
