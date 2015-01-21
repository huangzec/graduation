package com.mvc.log;

import java.io.File;

import com.mvc.common.DirUtil;
import com.mvc.common.FileUtil;
import com.mvc.common.HConfig;
import com.mvc.exception.VerifyException;

public class LogToFile extends LogFactory {
	
	/**
	 * 日志保存路径
	 */
	protected static String _logFilePath;
	
	static{
		_logFilePath 	= DirUtil.fixPath(HConfig.ROOT_PATH) + "runtime/log/error.log";
	}

	/**
	 * 写入日志 
	 */
	@Override
	public void write(String message) {
		try {
			File file 	= new File(_logFilePath);
			if(!file.exists()) {
				FileUtil.write(_logFilePath, message);
				return;
			}
			if(Double.parseDouble("10") < FileUtil.byteToMb(FileUtil.size(_logFilePath))) {
				_tagLogFile();
			}
			FileUtil.append(_logFilePath, message + "\r\n", true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("写入日志文件错误：" + _logFilePath);
		}
	}

	/**
	 * 归档日志文件
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @throws VerifyException 
	 */
	private void _tagLogFile() throws VerifyException 
	{
		FileUtil.move(_logFilePath, _logFilePath + ".tag-" + System.currentTimeMillis(), true);
	}

}
