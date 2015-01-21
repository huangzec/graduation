package com.mvc.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.mvc.exception.VerifyException;
import com.mvc.log.Logger;

public class PropertiesUtils {
	
	/**
	 * 属性文件对象
	 */
	private Properties _prop;

	/**
	 * 属性文件路径
	 */
	private String _filePath;
	
	/**
	 * 构造函数
	 *
	 * @author huangzec <huangzec@foxmail.com>
	 * @param sysCfgFilePath
	 */
	public PropertiesUtils(String filePath) 
	{
		_filePath 	= filePath;
		_prop 		= new Properties();
	}

	/**
	 * 得到属性配置项的值
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param item
	 * @param string
	 * @return
	 */
	public String getAttr(String item, String encoding) 
	{
		if(_prop.contains(item)) {
			return StringUtil.rawToEncode(_prop.getProperty(item), encoding);
		}
		
		return null;
	}

	/**
	 * 文件属性解析
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @return
	 * @throws VerifyException 
	 */
	public PropertiesUtils parse() throws VerifyException 
	{
		try {
			File propFile 	= FileUtil.isExists(_filePath);
			FileInputStream inPropStream 	= new FileInputStream(propFile);
			_prop.load(inPropStream);
		} catch (Exception e) {
			Logger.write(e.getMessage(), Logger.L_ERROR);
			throw new VerifyException(e.getMessage());
		}
		
		return this;
	}

}
