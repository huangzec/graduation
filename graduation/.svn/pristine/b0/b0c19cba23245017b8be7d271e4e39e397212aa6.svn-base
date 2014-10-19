package com.mvc.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.RequestSetAttribute;

/**
 * 后台首页
 * @author huangzec@foxmail.com
 *
 */
@Controller
public class IndexController {
	
	/**
	 * 
	 * 首页默认执行的方法 
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 上午10:38:27
	 * @return void
	 */
	@RequestMapping(value="/admin/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		//不同的角色加载不同的主页
		System.out.println("index.do");
		Integer userStatus 	= (Integer) request.getSession().getAttribute("user_status");
		System.out.println("status" + userStatus);
		if(userStatus != null && userStatus == 3){
			//如果是系部管理员，则跳转到系部管理员的主页
			return new ModelAndView("admin/deptmanager/index");
		} else if(userStatus != null && userStatus == 4) {
			
			return new ModelAndView("admin/office/index");
		}
		
		RequestSetAttribute.requestSetAttribute(
				request, 300, "", "您不具有该权限！", "", "");
		return new ModelAndView("public/ajaxDone");
	}
	
	
}
