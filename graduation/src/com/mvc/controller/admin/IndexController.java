package com.mvc.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.RequestSetAttribute;
import com.mvc.entity.Message;
import com.mvc.exception.VerifyException;
import com.mvc.service.MessageService;

/**
 * 后台首页
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin")
public class IndexController {
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 
	 * 首页默认执行的方法 
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 上午10:38:27
	 * @return void
	 */
	@RequestMapping(value="/index.do")
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
	
	/**
	 * 教务管理员模块中的我的主页
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/index/office.do")
	public ModelAndView manager(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/office/main");
		String userId = (String) request.getSession().getAttribute("user_id");
		String where 	= "from Message where toId = '" + userId + "' ";//总消息
		String unread 	= where + " AND status = '1' order by createTime desc ";
		where += " order by createTime desc ";
		List<Message> messageList 	= messageService.getAllRows(where);
		List<Message> unreadList 	= messageService.getAllRows(unread);
		mav.addObject("messageList", messageList);
		mav.addObject("unreadList", unreadList);
		
		return mav;
	}
	
	/**
	 * 我的主页
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/index/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/deptmanager/main");
		String userId = (String) request.getSession().getAttribute("user_id");
		String where 	= "from Message where toId = '" + userId + "' ";//总消息
		String unread 	= where + " AND status = '1' order by createTime desc ";
		where += " order by createTime desc ";
		List<Message> messageList 	= messageService.getAllRows(where);
		List<Message> unreadList 	= messageService.getAllRows(unread);
		mav.addObject("messageList", messageList);
		mav.addObject("unreadList", unreadList);
		
		return mav;
	}
}
