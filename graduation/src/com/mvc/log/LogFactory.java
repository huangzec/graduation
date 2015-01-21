package com.mvc.log;

/**
 * 日志工厂类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2015-1-4 上午10:45:24 
 * @version 	1.0
 */
public abstract class LogFactory {

	/**
	 * 将日志写入到文件中
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param info
	 */
	public abstract void write(String info);
}
