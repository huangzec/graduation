package com.mvc.common;

import com.mvc.exception.VerifyException;
import com.mvc.log.Logger;

/**
 * 系统配置工具类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2015-1-4 上午11:22:09 
 * @version 	1.0
 */
public class HConfig {
	
	/**
	 * 网站根路径
	 */
	public static String ROOT_PATH = "";
	
	/**
	 * 当前系统配置文件所在位置
	 */
	public static String SYS_CFG_FILE_PATH 	= "config/Config.properties";
	
	/**
	 * 文件大小
	 */
	public static double MAX_SIZE = 5.0;
	
	/**
	 * 静态初始化代码块
	 */
	static{System.out.println("first");
		try {
			loadCfgFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 属性对象
	 */
	private static PropertiesUtils _prop 	= null;

	/**
	 * 得到系统配置项
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param item
	 * @return
	 */
	public static String getSysCfg(String item)
	{
		if(null == _prop.getAttr(item, "UTF-8")) {
			Logger.write("没有找到系统配置项：" + item, Logger.L_WARN);
			return null;
		}
		
		return _prop.getAttr(item, "UTF-8").trim();
	}
	
	/**
	 * 加载系统配置文件
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @throws VerifyException 
	 */
	public static void loadCfgFile() throws VerifyException
	{
		HConfig.loadCfgFile(Path.getSrcPath().replace("%20", " ") + HConfig.SYS_CFG_FILE_PATH, false);
	}

	/**
	 * 重载系统配置文件
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param string
	 * @param b
	 * @throws VerifyException 
	 */
	public static void loadCfgFile(String sysCfgFilePath, boolean isReload) throws VerifyException 
	{
		try {
			if(null == _prop || true == isReload) {
				FileUtil.isExists(sysCfgFilePath);
				_prop 	= new PropertiesUtils(sysCfgFilePath);
				_prop.parse();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
