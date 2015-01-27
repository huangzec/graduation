package com.mvc.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.HResponse;
import com.mvc.common.MD5Util;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Deptmanager;
import com.mvc.entity.Message;
import com.mvc.entity.Student;
import com.mvc.entity.Teacher;
import com.mvc.entity.User;
import com.mvc.exception.VerifyException;
import com.mvc.service.DeptmanagerService;
import com.mvc.service.MessageService;
import com.mvc.service.StudentService;
import com.mvc.service.TeacherService;
import com.mvc.service.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private DeptmanagerService deptmanagerService;
	
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
	
	/**
	 * 重置系部管理员、教师、学生密码
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/index/resetpwd.do")
	public ModelAndView resetpwd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/common/resetpwd");
		String permissions 	= request.getParameter("permissions");
		String id 			= request.getParameter("id");
		String name 		= "";
		try {
			String where = "from User where username = '" + id + "' AND permissions = " + Integer.parseInt(permissions);
			User user 	= userService.getOneUser(where);
			if(Verify.isEmpty(user)) {
				//如果用户还没注册，则先自动注册
				user = new User();
				user.setUsername(id.trim());
				user.setPassword(MD5Util.MD5(id.trim()));
				user.setPermissions(Integer.parseInt(permissions));
				userService.addOneUser(user);
			}
			if(permissions.trim().equals("1")) {
				//如果是学生
				Student stu = studentService.getOneById(id.trim());
				if(!Verify.isEmpty(stu)) {
					name = "学生 " + stu.getStuId() + "【" + stu.getStuName() + "】";					
				}
			} else if(permissions.trim().equals("2")) {
				//如果是教师
				Teacher teacher = teacherService.getOneTeacherById(id.trim());
				if(!Verify.isEmpty(teacher)) {
					name  = "教师 " + teacher.getTeaId() + "【" + teacher.getTeaName() + "】";
				}
			} else if(permissions.trim().equals("3")) {
				//如果是系部管理员
				Deptmanager deptmanager = deptmanagerService.getOne(id.trim());
				if(!Verify.isEmpty(deptmanager)) {
					name  = "系部管理员 " + deptmanager.getDmId() + "【" + deptmanager.getDmName() + "】";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("permissions", permissions);
		
		return mav;
	}
	
	/**
	 * 执行重置系部管理员、教师、学生密码
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/index/reset.do")
	public ModelAndView reset(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		String id 			= request.getParameter("id");
		String password		= request.getParameter("password");
		String permissions 	= request.getParameter("permissions");
		if(!this._verifyData(request))
		{
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			String where = "from User where username = '" + id.trim() + 
					"' AND permissions = " + Integer.parseInt(permissions);
			User user 	= userService.getOneUser(where);
			if(Verify.isEmpty(user)) {
				user = new User();
				user.setUsername(id.trim());
				user.setPassword(MD5Util.MD5(password.trim()));
				user.setPermissions(Integer.parseInt(permissions));
				userService.addOneUser(user);
				mav.addObject("statusCode", 200);
				mav.addObject("message", "密码重置成功");
				mav.addObject("callbackType", "closeCurrent");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			user.setPassword(MD5Util.MD5(password.trim()));
			userService.editOneUser(user);
			mav.addObject("statusCode", 200);
			mav.addObject("message", "密码重置成功");
			mav.addObject("callbackType", "closeCurrent");
		}catch (Exception e) {
			mav.addObject("navTabId", 300);
			mav.addObject("message", "密码重置失败");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}

	/**
	 * 验证修改密码数据
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @return
	 */
	private boolean _verifyData(HttpServletRequest request) 
	{

		String id 			= request.getParameter("id");
		String password		= request.getParameter("password");
		String permissions 	= request.getParameter("permissions");
		if(Verify.isEmpty(id)) {
			request.setAttribute("message", "编号不能为空");
			
			return false;
		}
		if(Verify.isEmpty(password)) {
			request.setAttribute("message", "新密码不能为空");
			
			return false;
		}
		if(Verify.isEmpty(permissions)) {
			request.setAttribute("message", "用户类型不能为空");
			
			return false;
		}
		if(Verify.isStrLen(permissions, 4, 32)) {
			request.setAttribute("message", "密码长度在4~32个字符之间");
			
			return false;
		}
		
		return true;
	}
}
