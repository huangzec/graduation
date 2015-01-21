package com.mvc.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;
import java.util.regex.Matcher;

import com.mvc.exception.VerifyException;

import javassist.NotFoundException;

/**
 * 文件操作类
 * @author huangzec@foxmail.com
 *
 */
public class FileUtil {

	/**
	 * 打开文件到输入流
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午01:23:40
	 * @return FileInputStream
	 */
	public static FileInputStream open(String path) throws NotFoundException
	{
		try {
			return new FileInputStream(path);
		} catch (FileNotFoundException e) {
			throw new NotFoundException("文件打开失败, 打开失败的文件路径为: " + path);
		}
	}
	
	/**
	 * 得到文件内容
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午01:41:08
	 * @return StringBuffer
	 * @throws NotFoundException 
	 * @throws IOException 
	 */
	public static StringBuffer getContent(String path) throws NotFoundException, IOException
	{
		path 					= fixPath(path);
		FileInputStream file 	= new FileInputStream(path);
		try {
			StringBuffer content 		= new StringBuffer();
			InputStreamReader fReader 	= new InputStreamReader(file, "UTF-8");
			BufferedReader bfReader 	= new BufferedReader(fReader);
			String line 				= bfReader.readLine();
			while(null != line) {
				content.append(line + "\r\n");
				line 	= bfReader.readLine();
			}
			bfReader.close();
			fReader.close();
			
			return content;
		} catch (FileNotFoundException e) {
			throw new NotFoundException("文件打开失败，打开失败文件的路径为: " + path);
		}
	}
	
	/**
	 * 创建文件
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午01:54:28
	 * @return void
	 * @throws NotFoundException 
	 * @throws IOException 
	 * @throws VerifyException 
	 */
	public static void create(String path, Boolean override) throws NotFoundException, IOException, VerifyException
	{
		File file 	= FileUtil.isExists(path);
		if(override) {
			try{
				FileUtil.delete(path);
			}catch (Exception e) {
				
			}
		}
		if(!file.getParentFile().exists()&&!file.getParentFile().mkdir()) {
			throw new IOException("不能创建对应的文件或目录");
		}
		if(!file.createNewFile()) {
			throw new IOException("创建新文件或目录失败");
		}
	}
	
	public static void write(String path, String content) throws IOException
	{
		try {
			path 		= fixPath(path);
			File file 	= new File(path);
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			FileOutputStream stream 	= new FileOutputStream(file);
			OutputStreamWriter writer 	= new OutputStreamWriter(stream, "UTF-8");
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("文件操作失败 ，信息 : " + e.getMessage() + " 文件： " + path);
		}
	}

	/**
	 * 删除文件
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午02:15:26
	 * @return void
	 */
	public static void delete(String path) {
		if(null == path) {
			return;
		}
		try {
			File file 	= FileUtil.isExists(path);
			if(file.isFile() && !file.delete()) {
				throw new IOException("抱歉，删除文件失败 " + path);
			}
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * 文件是否存在
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午02:11:01
	 * @return File
	 * @throws NotFoundException 
	 */
	public static File isExists(String path) throws VerifyException {
		File file 	= new File(path);
		if(!file.exists()) {
			throw new VerifyException("文件不存在 " + path);
		}
		
		return file;
	}

	/**
	 * 修复文件路径
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-2 下午01:30:25
	 * @return String
	 */
	public static String fixPath(String path) {
		if(!"/".equals(File.separator)) {
			return path;
		}
		
		return path.replaceAll("/", Matcher.quoteReplacement(File.separator));
	}

	/**
	 * 移动指定的文件 
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param _logFilePath
	 * @param string
	 * @param b
	 * @throws VerifyException 
	 */
	public static void move(String fromPath, String toPath, boolean isCover) throws VerifyException 
	{
		File to 	= new File(toPath);
		File from 	= FileUtil.isExists(fromPath);
		if(to.exists()) {
			if(!isCover) {
				throw new VerifyException("文件已经存在！路径为：" + toPath);
			}
			delete(toPath);
		}
		if(false == from.renameTo(to)) {
			throw new VerifyException("文件上传失败，请查看是否磁盘还有空间！");
		}
	}

	/**
	 * 得到文件大小
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param _logFilePath
	 * @return
	 * @throws VerifyException 
	 */
	public static double size(String path) throws VerifyException 
	{
		File file 	= new File(path);
		if(!file.exists()) {
			throw new VerifyException("没有找到对应的文件");
		}
		
		return file.length();
	}

	/**
	 * 字节到M的单位转换
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param size
	 * @return
	 */
	public static double byteToMb(double bytes) 
	{
		return bytes / 1024 / 1024;
	}

	/**
	 * 追加文件内容到文件
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param path
	 * @param content
	 * @param isNew
	 * @throws VerifyException 
	 */
	public static void append(String path, String content, boolean isNew) throws VerifyException 
	{
		try {
			File file 	= new File(path);
			if(!file.exists()) {
				if(false == isNew) {
					throw new VerifyException("文件不存在");
				}
				file.createNewFile();
			}
			FileWriter writer 	= new FileWriter(path, true);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			throw new VerifyException("文件操作失败，信息：" + e.getMessage() + "，文件：" + path);
		}
	}
}
