package com.mvc.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 抓取网页工具类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2014-10-18 下午4:34:59 
 * @version 	1.0
 */
public class HttpClientUtil {
	
	/**
	 * 获取网页内容
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param strUrl
	 * @return
	 */
	public static String getPageContent(String strUrl) {
		
		return getPageContent(strUrl, "post");
    }
	
	/**
	 * 获取网页内容
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param strUrl
	 * @param strPostRequest
	 * @return
	 */
	public static String getPageContent(String strUrl, String strPostRequest) {
		
		return getPageContent(strUrl, strPostRequest, 100500);
    }
	
	/**
	 * 获取网页内容
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param strUrl
	 * @param strPostRequest
	 * @param maxLength
	 * @return
	 */
	public static String getPageContent(String strUrl, String strPostRequest, int maxLength) {
		
		return getPageContent(strUrl, strPostRequest, maxLength, "utf-8");
    }
	
	/**
	 * 获取网页内容
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param strUrl
	 * @param strPostRequest
	 * @param maxLength
	 * @param encoding
	 * @return
	 */
	public static String getPageContent(String strUrl, String strPostRequest, int maxLength, String encoding) {
        // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");//连接主机超时设置
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");//从主机读取数据超时设置
        try {
            URL newUrl = new URL(strUrl);
            HttpURLConnection hConnect = (HttpURLConnection) newUrl.openConnection();
            // POST方式的额外数据
            if (strPostRequest.length() > 0) {
                hConnect.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(hConnect.getOutputStream(), encoding);
                out.write(strPostRequest);
                out.flush();
                out.close();
            }
            // 读取内容
            BufferedReader rd = new BufferedReader(new InputStreamReader(hConnect.getInputStream(), encoding));
            int ch;
            for (int length = 0; (ch = rd.read()) > -1 && (maxLength <= 0 || length < maxLength); length ++) {
                buffer.append((char) ch);
            }
            rd.close();
            hConnect.disconnect();
            
            return buffer.toString().trim();
        } catch (Exception e) {
            return null;
        }
    }
}
