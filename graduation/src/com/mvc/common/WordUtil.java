package com.mvc.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成word文档工具类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2014-10-14 下午5:30:11 
 * @version 	1.0
 */
public class WordUtil {
	private Configuration configuration = null;

	/**
	 * 构造方法
	 *
	 * @author huangzec <huangzec@foxmail.com>
	 */
	public WordUtil() {
		if(null == configuration) {
			configuration = new Configuration();
			configuration.setDefaultEncoding("gbk");
		}
	}

	/**
	 * 创建doc文件
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @throws IOException 
	 */
	public void createDoc(String path, Map<String, Object> dataMap) throws IOException {
		//要填入模本的数据文件
		dataMap.put("image", getImageStr(DirUtil.fixPath(path + "images/") + "wordlogo.jpg"));
		//设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		//模板文件路径
		File file = new File(DirUtil.fixPath(path + "uploadfiles/"));
		configuration.setDirectoryForTemplateLoading(file);
		Template t=null;
		try {
			//topreport.ftl为要装载的模板
			t = configuration.getTemplate("topreport.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//输出文档路径及名称
		File outFile = new File("C:/Users/Administrator/Desktop/outFile.doc");
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 
        try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 得到图片
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @return
	 */
	private String getImageStr(String path) {
        String imgFile = path;
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
