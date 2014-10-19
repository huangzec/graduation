package com.mvc.controller.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.common.HResponse;
import com.mvc.common.HttpClientUtil;

/**
 * 网页数据抓取控制器类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2014-10-18 下午5:12:41 
 * @version 	1.0
 */
@Controller
@RequestMapping(value="/admin/httpclient")
public class HttpClientController {

	/**
	 * 抓取网页内容
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getpagecontent.do")
	public void getPageContent(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			String news = HttpClientUtil.getPageContent("http://jsj.hhtc.edu.cn/");
	        String regex = "class=\"main-top-main-text\">([\\s\\S]*?)</div>";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(news);
	        matcher.find();
	        news = matcher.group();
	        news = news.replace("class=\"main-top-main-text\">", "")
	        		.replace("</div>", "")
	        		.replace("<a href=", "<a target=\"_blank\" href=");
	        
	        HResponse.write(news, response);
		} catch (Exception e) {
			HResponse.write("<ul><li>数据加载失败</li></ul>", response);
		}
		
	}
}
