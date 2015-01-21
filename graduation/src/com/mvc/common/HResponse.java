package com.mvc.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求响应类
 * @author huangzec@foxmail.com
 *
 */
public class HResponse {

	/**
	 * 格式化数据值
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午01:52:00
	 * @return String
	 */
	public static String formatValue(String field, String value, HttpServletRequest request)
	{
		if(Verify.isEmpty(value) || "null".equals(value)) {
			return "";
		}
		
		return _isFormatMap(field, value, request);
	}
	
	/**
	 * 格式化空值
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-14 下午03:45:07
	 * @return String
	 */
	public static String formatEmpty(String value, HttpServletRequest request)
	{
		if(Verify.isEmpty(value) || "null".equals(value)) {
			return "";
		}
		
		return value;
	}

	/**
	 * 是不是Map类的格式化数据 
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-3 下午01:53:13
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private static String _isFormatMap(String field, String value,HttpServletRequest request)
	{
		Map<String, Map<String, String>> dataMap = (Map<String, Map<String, String>>) request.getAttribute(field + "_map");
		if(null != dataMap) {
			return null != dataMap.get(value) ? dataMap.get(value).get("name") : "无";
		}
		
		return value;
	}
	
	/**
	 * 格式化特定的评审教师数据
	 * 123,456,789 => 123【张三】，456【李四】，789【王五】
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午04:33:44
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String formatJudgeValue(String value, HttpServletRequest request)
	{
		if(Verify.isEmpty(value) || "null".equals(value)) {
			return "";
		}
		Map<String, Map<String, String>> dataMap = (Map<String, Map<String, String>>) request.getAttribute("judge_map");
		if(null != dataMap) {
			String[] ids 	= value.split(",");
			StringBuffer name 	= new StringBuffer();
			for(int i = 0; i < ids.length; i++) {
				name.append(null != dataMap.get(ids[i]) ? (ids[i] + "【" + dataMap.get(ids[i]).get("name") + "】") : "无").append(",");
			}
			name.deleteCharAt(name.length() - 1);
			
			return name.toString();
		}
		
		return value;
	}
	
	/**
	 * 格式化时间日期
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-5 下午09:30:25
	 * @return String
	 */
	public static String formatDateTime(Date date)
	{		
		return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取系统当前年份
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @return
	 */
	public static String getCurrentYear()
	{
		return formatDateTime(new Date(), "yyyy");
	}
	
	/**
	 * 格式化日期时间
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateTime(Date date, String format)
	{
		if(Verify.isEmpty(date)) {
			return "";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			
			return "";
		}
	}
	
	/**
	 * 格式化日期
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-13 下午04:52:12
	 * @return String
	 */
	public static String formatDate(Date date)
	{		
		return formatDateTime(date, "yyyy-MM-dd");
	}
	
	/**
	 * 格式化特定的时间格式
	 * **年**月**日
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-14 下午04:16:48
	 * @return String
	 */
	public static String formatDateTime(String value)
	{
		if(Verify.isEmpty(value) || "null".equals(value)) {
			return "";
		}
		/**
		 * 去掉时分秒
		 */
		String[] temp = value.split(" ");
		value = temp[0].replaceFirst("-", " 年 ").replaceFirst("-", " 月 ") + " 日 ";
		
		return value;
	}
	
	/**
	 * 今天是星期几
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String todayOfWeek()
	{
		String[] str 		= {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar rightNow 	= Calendar.getInstance();  
        int day 			= rightNow.get(rightNow.DAY_OF_WEEK);
        
        return str[day-1];
	}
	
	/**
	 * 执行成功的json信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 下午12:04:13
	 * @return void
	 */
	public static void okJSON(HttpServletResponse response)
	{
		okJSON("操作成功", response);
	}
	
	/**
	 * 执行成功的json信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 下午12:01:39
	 * @return void
	 */
	public static void okJSON(String message, HttpServletResponse response)
	{
		okJSON(message, null, response);
	}
	
	/**
	 * 执行成功的json信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 上午11:56:42
	 * @return void
	 */
	public static void okJSON(String message, String data, HttpServletResponse response)
	{
		write("{\"rs\": true, \"message\": \"" + message + "\", \"data\": " + data + "}", response);
	}
	
	/**
	 * 执行失败的json信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 下午12:00:17
	 * @return void
	 */
	public static void errorJSON(HttpServletResponse response)
	{
		errorJSON("执行失败", response);
	}
	
	/**
	 * 执行失败的json信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 上午11:58:16
	 * @return void
	 */
	public static void errorJSON(String message, HttpServletResponse response)
	{
		write("{\"rs\": false, \"message\": \"" + message + "\"}", response);
	}
	
	/**
	 * 向浏览器写入内容
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 上午11:49:03
	 * @return void
	 */
	public static void write(String content, HttpServletResponse response)
	{
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
