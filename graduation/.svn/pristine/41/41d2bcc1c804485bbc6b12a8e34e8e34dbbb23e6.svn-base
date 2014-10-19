package com.mvc.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;
import java.util.regex.Matcher;

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
	 */
	public static void create(String path, Boolean override) throws NotFoundException, IOException
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
	public static File isExists(String path) throws NotFoundException {
		File file 	= new File(path);
		if(!file.exists()) {
			throw new NotFoundException("文件不存在 " + path);
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
}
