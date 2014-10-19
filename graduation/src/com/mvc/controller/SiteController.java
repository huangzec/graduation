package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.MD5Util;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.User;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.MessageService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.TeacherService;
import com.mvc.service.UserService;

/**
 * 前台控制器
 * @author huangzec@foxmail.com
 *
 */
@Controller
public class SiteController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OpentopicinfoService opentopicinfoService;
	
	@Autowired
	private GraduateinfoService graduateinfoService;

	/**
	 * 
	 * 根据用户选择身份进入不同主页 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午3:58:55
	 * @return ModelAndView
	 */
	@RequestMapping(value="/user/index.do")
	public ModelAndView loginIndex(HttpServletRequest request, HttpServletResponse response){
		Integer userStatus = (Integer) request.getSession().getAttribute("user_status");
		// 如果是学生　进入学生主页
		// 如果是教师　进入教师主页
		if(userStatus != null && userStatus == 1){ 
			
			return new ModelAndView("user/student/index");
		}else if(userStatus != null && userStatus == 2){
			request.getSession().setAttribute("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
			_assignTotalUnreadMessage(request, (String) request.getSession().getAttribute("user_id"));
			_assignIsOpentopicinfoHeaderMan(request);
			_assignIsGraduationinfoHeaderMan(request);
			
			return new ModelAndView("user/teacher/index");
		}else {
			
			return new ModelAndView("user/login");
		}
	}

	/**
	 * 加载未读消息总数
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param request 
	 * @date 2014-8-4 下午12:07:01
	 * @return void
	 */
	private void _assignTotalUnreadMessage(HttpServletRequest request, String id) 
	{
		request.setAttribute(
				"total_message",
				messageService.getAllRecords("from Message where toId = '" + id + "' AND status = 1")
		);
		
	}
	
	/**
	 * 加载是否是开题答辩评审组长
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-29 下午08:09:56
	 * @return void
	 */
	private void _assignIsOpentopicinfoHeaderMan(HttpServletRequest request)
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Opentopicinfo where departmentId = '" + 
			department.getDeptId() + "' AND headerman = '" + 
			request.getSession().getAttribute("user_id") + 
			"' AND status = '1'";
		
		request.getSession().setAttribute(
				"headerman", 
				opentopicinfoService.getRecordByWhere(where)
				);
	}
	
	/**
	 * 加载是否是毕业答辩评审组长
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 下午04:50:35
	 * @return void
	 */
	private void _assignIsGraduationinfoHeaderMan(HttpServletRequest request)
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Graduateinfo where departmentId = '" + 
			department.getDeptId() + "' AND headerman = '" + 
			request.getSession().getAttribute("user_id") + 
			"' AND status = '1'";
		
		request.getSession().setAttribute(
				"grad-headerman", 
				graduateinfoService.getRecordByWhere(where)
				);
	}
	
	/**
	 * 修改密码
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-30 下午05:32:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/user/site/repassword.do")
	public ModelAndView repassword(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/repassword");
		
		return mav;
	}
	
	/**
	 * 重置密码
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-30 下午05:36:08
	 * @return ModelAndView
	 */
	@RequestMapping(value="/user/site/repwd.do")
	public ModelAndView repwd(HttpServletRequest request, HttpServletResponse response)
	{
		if(!_verifyData(request)) {
			return new ModelAndView("forward:/user/site/repassword.do");
		}
		User user 	= userService.getOneUser("from User where userName = '" + request.getSession().getAttribute("user_id") + "'");
		if(Verify.isEmpty(user)) {
			return new ModelAndView("forward:/user/site/repassword.do", "message", "用户不存在");
		}
		if(!user.getPassword().equals(MD5Util.MD5(request.getParameter("oldpassword")))) {
			return new ModelAndView("forward:/user/site/repassword.do", "message", "原密码不正确");
		}
		user.setPassword(MD5Util.MD5(request.getParameter("password")));
		try{
			userService.editOneUser(user);
			request.setAttribute("message", "密码修改成功");
		}catch (Exception e) {
			request.setAttribute("message", "密码修改失败");
		}
		
		return new ModelAndView("forward:/user/site/repassword.do");
	}

	/**
	 * 验证数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-30 下午07:58:54
	 * @return boolean
	 */
	private boolean _verifyData(HttpServletRequest request) {
		if(Verify.isEmpty(request.getParameter("oldpassword"))) {
			request.setAttribute("message", "原密码不能为空");
			
			return false;
		}
		if(Verify.isEmpty(request.getParameter("password"))) {
			request.setAttribute("message", "新密码不能为空");
			
			return false;
		}
		if(Verify.isEmpty(request.getParameter("repassword"))) {
			request.setAttribute("message", "确认密码不能为空");
			
			return false;
		}
		if(!request.getParameter("password").equals(request.getParameter("repassword"))) {
			request.setAttribute("message", "新密码跟确认密码不一致");
			
			return false;
		}
		
		return true;
	}
}
