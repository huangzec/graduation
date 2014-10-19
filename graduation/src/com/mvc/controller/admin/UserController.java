package com.mvc.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.MD5Util;
import com.mvc.entity.User;
import com.mvc.service.UserService;


@Controller
@RequestMapping(value="user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 修改密码
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 上午10:45:06
	 * @return ModelAndView
	 */
	@RequestMapping(value="repassword.do")
	public ModelAndView repassword(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/common/repassword");
		
		return mav;
	}
	
	/**
	 * 密码修改
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 上午10:49:23
	 * @return ModelAndView
	 */
	@RequestMapping(value="repwd.do")
	public ModelAndView repwd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		String oldpassword 	= request.getParameter("oldpassword");
		String password		= request.getParameter("password");
		if(!this._verifyPassword(request))
		{
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String userid 	= (String) request.getSession().getAttribute("user_id");
		User user 	= userService.getOneUser("from User where username='" + userid + "'");
		if(user == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		if(!user.getPassword().equals(MD5Util.MD5(oldpassword))) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "原密码不正确");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		user.setPassword(MD5Util.MD5(password));
		try{
			userService.editOneUser(user);
			mav.addObject("statusCode", 200);
			mav.addObject("message", "密码修改成功");
			mav.addObject("callbackType", "closeCurrent");
			
			Integer userstatus = user.getPermissions();
			if(userstatus == 1 || userstatus == 2){
				mav.addObject("navTabId", "");
				mav.addObject("forwardUrl", "/admin/index.do");
			}else{
				mav.addObject("forwardUrl", "/user/index.do");
			}
		}catch (Exception e) {
			mav.addObject("navTabId", 300);
			mav.addObject("message", "密码修改失败");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}

	/**
	 * 密码格式要求格式确认
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 上午10:49:23
	 * @return ModelAndView
	 */
	protected boolean _verifyPassword(HttpServletRequest request) {
		String oldpassword 	= request.getParameter("oldpassword");
		String password 	= request.getParameter("password");
		String repassword 	= request.getParameter("repassword");
		String code 		= request.getParameter("code");
		if(oldpassword.equals("")) {
			request.setAttribute("message", "原密码不能为空");
			return false;
		}
		if(password.equals("")) {
			request.setAttribute("message", "新密码不能为空");
			return false;
		}
		if(repassword.equals("")) {
			request.setAttribute("message", "确认密码不能为空");
			return false;
		}
		if(!password.equals(repassword)) {
			request.setAttribute("message", "密码不一致");
			return false;
		}
		if(!code.equals(request.getSession().getAttribute("rand"))) {
			request.setAttribute("message", "验证码不正确");
			return false;
		}
		if(password.length() > 32 || password.length() < 6) {
			request.setAttribute("message", "新密码长度在6~32个字符之间");
			return false;
		}
		
		return true;
	}
	
}
