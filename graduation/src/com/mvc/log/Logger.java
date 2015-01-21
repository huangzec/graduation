package com.mvc.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mvc.common.AopProxy;
import com.mvc.common.HConfig;
import com.mvc.common.HResponse;
import com.mvc.common.Reflect;

/**
 * 日志信息类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2015-1-4 上午10:47:45 
 * @version 	1.0
 */
public class Logger {

	/**
	 * 提示级别：成功
	 */
	public static String L_SUCCESS 	= "success";
	
	/**
	 * 提示级别：信息
	 */
	public static String L_INFO 	= "info";

	/**
	 * 提示级别：注意
	 */
	public static String L_NOTICE 	= "notice";

	/**
	 * 提示级别：警告
	 */
	public static String L_WARN 	= "warn";

	/**
	 * 提示级别：错误
	 */
	public static String L_ERROR 	= "error";
	
	/**
	 * 系统输出配置
	 */
	protected static HashMap<String, String> _sysOutCfg 	= null;
	
	/**
	 * 日志输出配置
	 */
	protected static Map<String, String> _outMap 	= new HashMap<String, String>();
	
	/**
	 * 静态初始化代码块
	 */
	static{
		_outMap.put("O_FILE", "com.mvc.log.LogToFile");
		_outMap.put("O_CONSOLE", "com.mvc.log.LogToConsole");
	}

	/**
	 * 写入信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param message
	 */
	public static void write(String message)
	{
		Logger.write(message, Logger.L_NOTICE);
	}
	
	/**
	 * 写入日志
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param ex
	 * @param level
	 */
	public static void write(Throwable ex, String level)
	{
		
	}
	
	/**
	 * 日志写方法
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param message
	 * @param level
	 */
	public static void write(String message, String level)
	{
		if(null == message)
		{
			return;
		}
		message 	= _formatLogInfo(message, level);
		try {
			LogToFile logToFile = new LogToFile();
			logToFile.write(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到日志级别对应的输出类
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param outLogClass
	 * @param message
	 */
	protected static void _execLogWrite(String outLogClass, String message) 
	{
		try {
			new AopProxy(Reflect.getProxy(outLogClass, true)).invoke("write", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 格式化日志信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param message
	 * @param level
	 * @return
	 */
	private static String _formatLogInfo(String message, String level) 
	{		
		return String.format("【%s】【%s】%s", level, HResponse.formatDateTime(new Date()), message);
	}
	
	/**
	 * 初始化系统输出配置
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 */
	protected static void _initSysOutCfg()
	{
		if(null != Logger._sysOutCfg && !Logger._sysOutCfg.isEmpty() && Logger._sysOutCfg.size() > 0) {
			return;
		}
		Logger._sysOutCfg 	= new HashMap<String, String>();
		Iterator<Map.Entry<String, String>> outCfgIter = Logger._outMap.entrySet().iterator();
		while(outCfgIter.hasNext()) {
			Map.Entry<String, String> outCfgItem 	= (Map.Entry<String, String>) outCfgIter.next();
			if(null == HConfig.getSysCfg(outCfgItem.getKey())) {
				continue;
			}
			Logger._sysOutCfg.put(
					outCfgItem.getKey(), 
					HConfig.getSysCfg(outCfgItem.getKey()).trim()
			);
		}System.out.println(Logger._sysOutCfg);
	}
}
