package com.mvc.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 路径提取工具类
 * @author huangzec@foxmail.com
 *
 */

public class Path {

	/**
	 * 得到根目录Src所在目录全路径
	 *  如 /D:/Program%20Files/Apache%20Software%20
	 *  Foundation/Tomcat%206.0/webapps/graduation/WEB-INF/classes/
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 下午09:15:10
	 * @return String
	 */
	public static String getSrcPath()
	{
		return (new Path()).getClass().getResource("/").getPath();
	}
	
	/**
	 * 得到网站根路径
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 下午09:37:28
	 * @return String
	 */
	public static String getWebRootPath(HttpServletRequest request)
	{
		return request.getSession().getServletContext().getRealPath("");
	}
	
	/**
	 * 得到当前网站的域名
	 *  如 localhost8080
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-1 下午09:44:49
	 * @return String
	 */
	public static String getSiteUrl(HttpServletRequest request)
	{
		return request.getServerName() + request.getServerPort();
	}
}
