package com.mvc.interceptor;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvc.author.enterController;
import com.mvc.common.HConfig;
import com.mvc.common.HResponse;
import com.mvc.exception.VerifyException;



public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 加载系统配置项
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	private void _loadSysCfg(HttpServletRequest request)
	{
		HConfig.ROOT_PATH 	= request.getSession().getServletContext().getRealPath("");
		try {
			HConfig.loadCfgFile();
		} catch (VerifyException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
	
		_loadSysCfg(request);
		System.out.println(HResponse.formatDateTime(new Date()) + " 开始访问action--"+request.getRequestURI());
		//1、请求到登录页面 放行  
		System.out.println("servletpath " + request.getServletPath());
	    if(request.getServletPath().startsWith("/user/login.do") || request.getServletPath().startsWith("/user/timeout.do")
	    		|| request.getServletPath().startsWith("/user/ajaxlogin.do")) {  
	    	return true;  
	    } else if(request.getServletPath().startsWith("/admin/timeout.do") || request.getServletPath().startsWith("/admin/enter/adminLogin.do")) {
	    	return true;
	    }else if(request.getServletPath().startsWith("/user/signin.do") || request.getServletPath().startsWith("/user/enter/userlogin.do")) {
	    	return true;
	    }else if(request.getServletPath().startsWith("/admin/")) {
	    	return enterController.isAdminLogined(request, response);
	    } else if(request.getServletPath().startsWith("/user/") || request.getServletPath().startsWith("/judge/")){ 
		    //2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求  
		    //3、如果用户已经登录 放行    
			return enterController.isLogined(request, response);
	    }
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println(HResponse.formatDateTime(new Date()) + " 访问结束,可关闭--"+request.getRequestURI());
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("开始返回视图action--"+request.getRequestURI());
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
