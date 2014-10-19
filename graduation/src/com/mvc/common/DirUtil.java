package com.mvc.common;

import java.io.File;
import java.util.regex.Matcher;

/**
 * 文件目录操作类
 * @author huangzec@foxmail.com
 *
 */
public class DirUtil {

	/**
	 * 修正目录路径
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午04:01:32
	 * @return String
	 */
	public static String fixPath(String path)
	{
		if(!"/".equals(File.separator)) {
			path 	= path.replaceAll("/", Matcher.quoteReplacement(File.separator));
		}
		if(!path.endsWith(File.separator)) {
			return path + File.separator;
		}
		
		return path;
	}
}
