package com.mvc.author;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.MD5Util;
import com.mvc.entity.Department;
import com.mvc.entity.Deptmanager;
import com.mvc.entity.Profession;
import com.mvc.entity.Student;
import com.mvc.entity.Tboffice;
import com.mvc.entity.Teacher;
import com.mvc.entity.User;
import com.mvc.service.DeptService;
import com.mvc.service.DeptmanagerService;
import com.mvc.service.ProfessionService;
import com.mvc.service.StudentService;
import com.mvc.service.TbofficeService;
import com.mvc.service.TeacherService;
import com.mvc.service.UserService;

@Controller
public class enterController {
	
	static Logger logger = Logger.getLogger(enterController.class);
	private static final String LOGIN = "/user/login.do";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TbofficeService tbofficeService;
	
	@Autowired
	private DeptmanagerService deptmanagerService;
	
	@Autowired
	private DeptService departmentService;
	
	@Autowired
	private StudentService stuService;
	
	@Autowired
	private TeacherService teaService;
	
	@Autowired
	private ProfessionService professionService;

	/**
	 * 检测前台用户是否已经登陆
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-1 下午03:27:52
	 * @return void
	 */
	public static boolean isLogined(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String contextPath=request.getContextPath();
        String  url=request.getServletPath().toString();
        HttpSession session = request.getSession();
        String userID = (String) request.getSession().getAttribute("user_id");
        System.out.println("用户ID为 " + userID);
        //这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
        if (userID == null || userID.equals("")) {
            //被拦截，重定向到login界面
        	response.sendRedirect(contextPath+"/user/signin.do?redirectURL=" + URLEncoder.encode(url));
        	return false;
        }
        
        return true;
	}
	
	/**
	 * 前台用户登陆
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-1 下午03:34:26
	 * @return String
	 */
	@RequestMapping(value="/user/signin.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		
		return new ModelAndView("user/login");
	}
	
	@RequestMapping(value="/user/timeout.do")
	public String enter(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("statusCode", 301);
		request.setAttribute("message", "会话超时，请重新登陆");
		request.setAttribute("navTabId", "");
		request.setAttribute("forwardUrl", "/user/ajaxlogin.do");
		System.out.println("会话超时，请重新登陆");
		//return "public/login_dialog";
		return "public/ajaxTimeout";
	}
	
	@RequestMapping(value="/user/ajaxlogin.do")
	public String ajaxlogin(HttpServletRequest request, HttpServletResponse response)
	{
		return "public/login_dialog";
	}
	
	@RequestMapping(value=LOGIN)
	public ModelAndView login(HttpServletRequest request,ModelMap modelMap){

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		System.out.println(userName);
		User user = userService.getRecordByNameAndPwd(userName, passWord);
		if(user == null)
		{
			logger.error("没有该用户信息");
			return new ModelAndView("index");
		}
		System.out.println("登陆成功");
		this.setUserInfoByLogin(request, user);
		logger.error("登陆成功");
		return new ModelAndView("main");
	}
	
	public void setUserInfoByLogin(HttpServletRequest request, User user)
	{
		/*System.out.println("设置用户先信息 " + user.getId());
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("user_id", user.getId());
		request.getSession().setAttribute("user_adminInd", user.getAdminInd());
		*/
	}

	public static boolean isAdminLogined(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contextPath=request.getContextPath();
		String  url=request.getServletPath().toString();
        HttpSession session = request.getSession();
        String userID = (String) request.getSession().getAttribute("user_id");
        System.out.println("用户ID为 " + userID);
        //这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
        if (userID == null || userID.equals("")) {
            //被拦截，重定向到login界面
            response.sendRedirect(contextPath+"/admin/timeout.do?redirectURL=" + URLEncoder.encode(url));
            return false;
        }
        
        return true;
	}
	
	/**
	 * 
	 * 管理员登陆超时 
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 上午11:26:18
	 * @return String
	 */
	@RequestMapping(value="/admin/timeout.do")
	public String adminEnter(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("statusCode", 301);
		request.setAttribute("message", "会话超时，请重新登陆");
		request.setAttribute("navTabId", "");
		request.setAttribute("forwardUrl", "/admin/ajaxlogin.do");
		System.out.println("会话超时，请重新登陆");
		return "admin/login";
	}
	
	/**
	 * 
	 * 管理员登陆系统 
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午02:37:03
	 * @return ModelAndView
	 */
	@RequestMapping(value="/admin/enter/adminLogin.do")
	public ModelAndView adminLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String status 		= request.getParameter("status");
		String username 	= request.getParameter("username");
		String password 	= request.getParameter("password");
		User user 			= null;
		Tboffice tboffice 	= null;
		Deptmanager deptmanager = null;
		if(!this._verifyLoginData(request, response)) {
			return new ModelAndView("admin/login");
		}
		/**
		 * 如果是教务处管理员
		 */
		if(status.equals("4")) {
			user = this._getOneUser("from User where username = '" + username + "'");
			tboffice = this._getOneTbOffice("from Tboffice where offId = '" + username + "'");
			if(user == null) {//如果用户表为空，就去找教务处管理员表
				if(tboffice == null) {
					return new ModelAndView("admin/login", "message", "用户名或密码错误");
				}
				//将教务处管理员数据添加到用户表
				if((!this._insertOfficeIntoUser(tboffice)) || (!tboffice.getOffId().equals(password))) {
					return new ModelAndView("admin/login", "message", "用户名或密码错误");
				}
				//设置用户信息通过登录
				request.getSession().setAttribute("user_id", tboffice.getOffId());
				request.getSession().setAttribute("user_name", tboffice.getOffName());
				request.getSession().setAttribute("user_status", 4);
				
				return new ModelAndView("forward:/admin/index.do");
			}
			//如果用户不为空
			if(!user.getPassword().equals(MD5Util.MD5(password))) {//密码不正确
				return new ModelAndView("admin/login", "message", "用户名或密码错误");
			}
			//设置用户信息
			if(user != null && tboffice != null){
				request.getSession().setAttribute("user_id", tboffice.getOffId());
				request.getSession().setAttribute("user_name", tboffice.getOffName());
				request.getSession().setAttribute("user_status", 4);
				return new ModelAndView("forward:/admin/index.do");
			}
		} else if(status.equals("3")){//如果选择的是系部管理员
			user = this._getOneUser("from User where username = '" + username + "'");
			deptmanager = this._getOneDeptManager("from Deptmanager where dmId = '" + username + "'");
			if(user == null) {//如果用户表为空，就去找系部管理员表
				if(deptmanager == null) {
					return new ModelAndView("admin/login", "message", "用户名或密码错误");
				}
				//将系部管理员数据添加到用户表
				if((!this._insertDeptmanagerIntoUser(deptmanager)) || (!deptmanager.getDmId().equals(password))) 
				{
					return new ModelAndView("admin/login", "message", "用户名或密码错误");
				}
				/**
				 * 获取系部管理员所属的系部
				 */
				Department department = departmentService.getOneDept(deptmanager.getDeptId());
				if(department == null) {//如果所属系部不存在
					return new ModelAndView("admin/login", "message", "用户名或密码错误");
				}
				request.getSession().setAttribute("department", department);
				//设置用户信息通过登录
				request.getSession().setAttribute("user_id", deptmanager.getDmId().toString());
				request.getSession().setAttribute("user_name", deptmanager.getDmName());
				request.getSession().setAttribute("user_status", 3);
				
				return new ModelAndView("forward:/admin/index.do");
			}
			//如果用户不为空
			if(!user.getPassword().equals(MD5Util.MD5(password))) {//密码不正确
				return new ModelAndView("admin/login", "message", "用户名或密码错误");
			}
			/**
			 * 获取系部管理员所属的系部
			 */
			if(deptmanager == null) {
				return new ModelAndView("admin/login", "message", "用户名或密码错误");
			}
			Department department = departmentService.getOneDept(deptmanager.getDeptId());
			if(department == null) {//如果所属系部不存在
				return new ModelAndView("admin/login", "message", "用户名或密码错误");
			}
			if(user != null && deptmanager != null){
				request.getSession().setAttribute("department", department);
				//ModelMap modelMap = new ModelMap();
				//modelMap.put("department", department);
				//设置用户信息
				
				request.getSession().setAttribute("user_id", deptmanager.getDmId().toString());
				request.getSession().setAttribute("user_name", deptmanager.getDmName());
				request.getSession().setAttribute("user_status", 3);

				return new ModelAndView("forward:/admin/index.do");
			}
		}
		
		return new ModelAndView("admin/login", "message", "用户名或密码错误");
	}
	
	/**
	 * 
	 * 教师、学生登录
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午3:40:21
	 * @return ModelAndView
	 */
	@RequestMapping(value="/user/enter/userlogin.do")
	public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		String status 		= request.getParameter("status");
		String username 	= request.getParameter("username");
		String password 	= request.getParameter("password");
		User user = null;
		Student student = null;
		Teacher teacher = null;

		if(!this._verifyLoginData(request, response)){
			return new ModelAndView("user/login");
		}
		
		user = this._getOneUser("from User where username = '" + username + "'");
		teacher = this._getOneTea("from Teacher where teaId = '" + username + "'");
		student = this._getOneStudent("from Student where stuId = '" + username + "'");
		
		if(user == null){
			if(student != null){
				int stuStatus = 1;
				if((!this._insertStuIntoUser(student)) || (!student.getStuId().equals(password))
						|| stuStatus != Integer.parseInt(status)){
					return new ModelAndView("user/login", "message", "用户名/密码/角色选择错误");
				}
				this._setUserInfo(username, student.getStuName(), stuStatus, request);
				this._setStuDept(student.getClaId(), request);
				return new ModelAndView("forward:/user/index.do");
			}
			else if(teacher != null){
				int teaStatus = 2;
				if(!this._insertTeacIntoUser(teacher) || (!teacher.getTeaId().equals(password))
						|| teaStatus != Integer.parseInt(status)){
					return new ModelAndView("user/login", "message", "用户名/密码/角色选择错误");
				}
				this._setUserInfo(username, teacher.getTeaName(), teaStatus, request);
				this._setTeaDept(request, teacher.getDeptId());
				return new ModelAndView("forward:/user/index.do");
			}else{
				return new ModelAndView("user/login", "message", "用户不存在！");
			}
			
		}else{
			if(user.getPassword().equals(MD5Util.MD5(password)) && user.getPermissions() == Integer.parseInt(status)) {
				request.getSession().setAttribute("user_id", username);
				request.getSession().setAttribute("user_status", Integer.parseInt(status));
				if (teacher != null) {
					request.getSession().setAttribute("user_name", teacher.getTeaName());
					this._setTeaDept(request, teacher.getDeptId());
				}else if(student != null){
					request.getSession().setAttribute("user_name", student.getStuName());
					this._setStuDept(student.getClaId(), request);
				}
				return new ModelAndView("forward:/user/index.do");
			}else{
				System.out.println("it is a question..............");
				return new ModelAndView("user/login", "message", "用户名/密码/角色选择错误");
			}
		}
	}
	
	/**
	 * 
	 * 设置教师系部信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 上午09:04:00
	 * @return void
	 */
	private void _setTeaDept(HttpServletRequest request, String deptId) {
		String where = "from Department where deptId='" + deptId +"'";
		Department dept = departmentService.getAll(where).get(0);
		request.getSession().setAttribute("dept", dept);
	}

	/**
	 * 
	 * 设置学生的系部信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 上午09:00:18
	 * @return void
	 */
	private void _setStuDept(Integer claId, HttpServletRequest request) {
		String where = "from Department where deptId = (select deptId from Profession" +
			" where proId = (select proId from Tbclass where claId='"+ claId + "'))";
		Department dept = departmentService.getAll(where).get(0);
		request.getSession().setAttribute("dept", dept);
	}

	/**
	 * 
	 * 设置用户登录信息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-22 上午9:16:45
	 * @return void
	 */
	private void _setUserInfo(String username, String name, int userStatus, 
			HttpServletRequest request) {
		request.getSession().setAttribute("user_id", username);
		request.getSession().setAttribute("user_name", name);
		request.getSession().setAttribute("user_status", userStatus);
	}

	/**
	 * 
	 * 将一条教师记录插入到user表中 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午8:09:13
	 * @return boolean
	 */
	private boolean _insertTeacIntoUser(Teacher teacher) {
		User user 	= null;
		user = userService.getOneUser("from User where username = '" + teacher.getTeaId() + "'");
		if(user != null) {
			return false;
		}
		try{
			user = new User();
			user.setUsername(teacher.getTeaId());
			user.setPassword(MD5Util.MD5(teacher.getTeaId()));
			user.setPermissions(2);
			userService.addOneUser(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * 获取一条教师记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午8:08:57
	 * @return Teacher
	 */
	private Teacher _getOneTea(String where) {
		return teaService.getOneByWhere(where);
	}

	/**
	 * 
	 * 将一条学生记录添加至user表中 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午3:51:24
	 * @return boolean
	 */
	private boolean _insertStuIntoUser(Student student) {
		User user 	= null;
		user = userService.getOneUser("from User where username = '" + student.getStuId() + "'");
		if(user != null) {
			return false;
		}
		try{
			user = new User();
			user.setUsername(student.getStuId());
			user.setPassword(MD5Util.MD5(student.getStuId()));
			user.setPermissions(1);
			userService.addOneUser(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * 获取一条学生记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-21 下午3:50:49
	 * @return Student
	 */
	private Student _getOneStudent(String where) {
		return stuService.getOneStu(where);
	}

	/**
	 * 将系部管理员数据添加到用户表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-11 上午11:36:24
	 * @return boolean
	 */
	protected boolean _insertDeptmanagerIntoUser(Deptmanager deptmanager)
	{
		User user 	= null;
		user 		= userService.getOneUser("from User where username = '" + deptmanager.getDmId() + "'");
		if(user != null) {
			return false;
		}
		try{
			user = new User();
			user.setUsername(deptmanager.getDmId());
			user.setPassword(MD5Util.MD5(deptmanager.getDmId()));
			user.setPermissions(3);
			userService.addOneUser(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取一个系部管理员
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-11 上午11:18:16
	 * @return Deptmanager
	 */
	protected Deptmanager _getOneDeptManager(String where) 
	{
		return deptmanagerService.getOneDeptManager(where);
	}

	/**
	 * 将教务处管理员添加到用户表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午09:22:47
	 * @return boolean
	 */
	protected boolean _insertOfficeIntoUser(Tboffice tboffice) {
		User user 	= null;
		user 		= userService.getOneUser("from User where username = '" + tboffice.getOffId() + "'");
		if(user != null) {
			return false;
		}
		try{
			user = new User();
			user.setUsername(tboffice.getOffId());
			user.setPassword(MD5Util.MD5(tboffice.getOffId()));
			user.setPermissions(4);
			userService.addOneUser(user);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * 验证登陆提交过来的数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午02:44:04
	 * @return boolean
	 */
	protected boolean _verifyLoginData(HttpServletRequest request, HttpServletResponse response)
	{
		String username 	= request.getParameter("username");
		String password 	= request.getParameter("password");
		String status 		= request.getParameter("status");
		String code 		= request.getParameter("code");
		String vcode 		= (String) request.getSession().getAttribute("rand");
		if(username.equals("")) {
			request.setAttribute("message", "用户名不能为空");
			
			return false;
		}
		if(password.equals("")) {
			request.setAttribute("message", "密码不能为空");
			
			return false;
		}
		if(status.equals("")) {
			request.setAttribute("message", "身份不能为空");
			
			return false;
		}
		if(code.equals("")) {
			request.setAttribute("message", "验证码不能为空");
			
			return false;
		}
		if(!code.equals(vcode)) {
			request.setAttribute("message", "验证码不正确");
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 根据ID获取user表的用户
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午05:20:24
	 * @return User
	 */
	protected User _getOneUser(String where)
	{
		return userService.getOneUser(where);
	}
	
	/**
	 * 根据ID获取tboffice表的教务处管理员
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-10 下午05:27:57
	 * @return Tboffice
	 */
	protected Tboffice _getOneTbOffice(String where)
	{
		return tbofficeService.getOneTbOffice(where);
	}
	
	/**
	 * 管理员退出系统
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-11 下午09:37:12
	 * @return String
	 */
	@RequestMapping(value="/admin/enter/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().removeAttribute("user_id");
		request.getSession().removeAttribute("user_name");
		request.getSession().removeAttribute("user_status");
		
		return "admin/login";
	}
	
	/**
	 * 
	 * 教师、学生用户退出系统 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-23 上午11:44:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="user/enter/loginOut.do")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("user_id");
		request.getSession().removeAttribute("user_name");
		request.getSession().removeAttribute("user_status");
		return new ModelAndView("user/login");
	}
}
