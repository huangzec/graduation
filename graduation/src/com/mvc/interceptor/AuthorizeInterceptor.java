package com.mvc.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvc.author.enterController;



public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
	
		System.out.println("开始访问action--"+request.getRequestURI());
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
		
		System.out.println("访问结束,可关闭--"+request.getRequestURI());
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