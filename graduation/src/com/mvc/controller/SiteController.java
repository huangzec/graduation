package com.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.HResponse;
import com.mvc.common.MD5Util;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.controller.admin.StudentController;
import com.mvc.controller.admin.TeacherController;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Apply;
import com.mvc.entity.Department;
import com.mvc.entity.Gradethree;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Revieworder;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Tbtopic;
import com.mvc.entity.Teacher;
import com.mvc.entity.Topicorderreview;
import com.mvc.entity.User;
import com.mvc.exception.VerifyException;
import com.mvc.service.ApplyService;
import com.mvc.service.GradethreeService;
import com.mvc.service.GradetwoService;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.MessageService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.RevieworderService;
import com.mvc.service.StudentService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TbtopicService;
import com.mvc.service.TeacherService;
import com.mvc.service.TopicorderreviewService;
import com.mvc.service.UserService;

/**
 * 前台控制器
 * @author huangzec@foxmail.com
 *
 */
@Controller
public class SiteController {
	private String file;
	private File image_path;
	private String image_pathFileName;
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public File getImage_path() {
		return image_path;
	}

	public void setImage_path(File image_path) {
		this.image_path = image_path;
	}

	public String getImage_pathFileName() {
		return image_pathFileName;
	}

	public void setImage_pathFileName(String image_pathFileName) {
		this.image_pathFileName = image_pathFileName;
	}

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OpentopicinfoService opentopicinfoService;
	
	@Autowired
	private GraduateinfoService graduateinfoService;

	@Autowired
	private TopicorderreviewService topicorderreviewService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private OpentopicscoreService opentopicscoreService;
	
	@Autowired
	private GradetwoService gradetwoService;
	
	@Autowired
	private GradethreeService gradethreeService;
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	@Autowired
	private TbgradeService tbgradeService;
	
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
		// 如果是学生　进入学生主页
		// 如果是教师　进入教师主页
		try {
			Integer userStatus = (Integer) request.getSession().getAttribute("user_status");
			System.out.println("userstatus " + userStatus);
			if(!Verify.isEmpty(userStatus)) {
				System.out.println("判断正确吗" + (userStatus == 2));
			}
			if(!Verify.isEmpty(userStatus) && userStatus == 1){ 
				request.getSession().setAttribute("student", studentService.getOneById(request.getSession().getAttribute("user_id").toString()));
				_assignTotalUnreadMessage(request, request.getSession().getAttribute("user_id").toString());
				_assignTotalMessage(request, request.getSession().getAttribute("user_id").toString());
				_assignTopicStatus(request);
				_assignOpentopicStatus(request);
				_assignGraduateStatus(request);
				
				return new ModelAndView("user/student/index");
			}else if(!Verify.isEmpty(userStatus) && userStatus == 2){
				//(new Thread(new myThread())).start();
				request.getSession().setAttribute("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
				_assignTotalUnreadMessage(request, (String) request.getSession().getAttribute("user_id"));
				_assignTotalMessage(request, request.getSession().getAttribute("user_id").toString());
				_assignIstopicJudgeMan(request);
				_assignIsOpentopicinfoHeaderMan(request);
				_assignIsGraduationinfoHeaderMan(request);
				_assignTbgradeList(request);
				
				return new ModelAndView("user/teacher/index");
			}else {
				request.setAttribute("message", "服务器繁忙，请稍后再试");
				System.out.println("没权限操作 ");
				
				return new ModelAndView("user/login");
			}
		} catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
			e.printStackTrace();
			System.out.println("抛出异常，信息：" + e.getMessage());
			
			return new ModelAndView("user/login");
		}
	}
	
	/**
	 * 加载全部消息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param id
	 */
	private void _assignTotalMessage(HttpServletRequest request, String id) 
	{
		request.setAttribute(
				"list",
				messageService.getAllRecords("from Message where toId = '" + id + "' order by createTime desc ")
		);
		
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
	 * 加载是否是课题评审教师
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	private void _assignIstopicJudgeMan(HttpServletRequest request)
	{
		try {
			Department department = (Department) request.getSession().getAttribute("dept");
			String userId 	= (String) request.getSession().getAttribute("user_id");
			String curYear 	= HResponse.getCurrentYear();
			String where 	= "from Topicorderreview where departmentId = '" + 
					department.getDeptId() + "' AND judgeYear = '" + curYear + "' ";
			Topicorderreview topicorderreview = topicorderreviewService.getRecordByWhere(where);
			if(Verify.isEmpty(topicorderreview)) {
				return;
			}
			if(Verify.isEmpty(topicorderreview.getJudgeTea())) {
				return;
			}
			boolean bool = false;
			String[] ids = topicorderreview.getJudgeTea().split(",");
			for(int i = 0; i < ids.length; i ++) {
				if(ids[i].trim().equals(userId.trim())) {
					bool = true;
				}
			}
			if(bool) {
				request.getSession().setAttribute("topicjudgeteacher", userId.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			Department department = (Department) request.getSession().getAttribute("dept");
			String where = "from Opentopicinfo where departmentId = '" + 
				department.getDeptId() + "' AND headerman = '" + 
				request.getSession().getAttribute("user_id").toString() + 
				"' AND status = '1'";
			
			request.getSession().setAttribute(
					"headerman", 
					opentopicinfoService.getRecordByWhere(where)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载是否是毕业答辩评审组长
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 下午04:50:35
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignIsGraduationinfoHeaderMan(HttpServletRequest request) throws VerifyException
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
	 * 加载课题进度
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignTopicStatus(HttpServletRequest request) throws VerifyException
	{
		//TODO:
		_assignSubmitTopic(request);
		_assignTopicTask(request);
		_assignTopicSelect(request);
	}
	
	/**
	 * 加载开题答辩进度
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignOpentopicStatus(HttpServletRequest request) throws VerifyException
	{
		_assignApply("1", request);
		_assignOpentopicScore(request);
	}
	
	/**
	 * 加载毕业答辩进度
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignGraduateStatus(HttpServletRequest request) throws VerifyException
	{
		_assignApply("2", request);
		_assignReviewOrderScore(request);
		_assignGraduateScore(request);
	}
	
	/**
	 * 加载答辩申请
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param applyType
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignApply(String applyType, HttpServletRequest request) throws VerifyException
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String userId 	= (String) request.getSession().getAttribute("user_id");
		//取最近的记录
		String where 	= "from Apply where departmentId = '" + department.getDeptId() + 
				"' AND type = '" + applyType + "' AND userId = '" + userId + "' order by createTime desc ";
		Apply apply 	= applyService.getRecordByWhere(where);
		if(applyType.equals("1")) {
			request.setAttribute("opentopic_apply", apply);	
		} else {
			request.setAttribute("graduate_apply", apply);			
		}
	}
	
	/**
	 * 加载开题答辩成绩
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignOpentopicScore(HttpServletRequest request) throws VerifyException
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String userId 	= (String) request.getSession().getAttribute("user_id");
		String where 	= "from Opentopicscore where departmentId = '" + department.getDeptId() + 
				"' AND studentId = '" + userId + "' order by createTime desc ";
		Opentopicscore opentopicscore = opentopicscoreService.getRecordByWhere(where);
		
		request.setAttribute("opentopicscore", opentopicscore);
	}
	
	/**
	 * 加载毕业评阅教师成绩，表二成绩
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignReviewOrderScore(HttpServletRequest request) throws VerifyException
	{
		String userId 	= (String) request.getSession().getAttribute("user_id");
		String where 	= "from Gradetwo where stuId = '" + userId + "' order by createTime desc ";
		Gradetwo gradetwo = gradetwoService.getRecordByWhere(where);
		
		request.setAttribute("revieworderscore", gradetwo);
	}
	
	/**
	 * 加载毕业答辩成绩，表三成绩
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignGraduateScore(HttpServletRequest request) throws VerifyException
	{
		String userId 	= (String) request.getSession().getAttribute("user_id");
		String where 	= "from Gradethree where stuId = '" + userId + "' order by createTime desc ";
		Gradethree gradethree = gradethreeService.getRecordByWhere(where);
		
		request.setAttribute("graduatescore", gradethree);
	}
	
	/**
	 * 加载提交的课题
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignSubmitTopic(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userId 			= (String) request.getSession().getAttribute("user_id");
		String where			= "from Tbtopic where deptId = '" + department.getDeptId() + 
				"' AND topCommitId = '" + userId.trim() + "' ";
		List<Tbtopic> topicList = tbtopicService.getAll(where);
		
		request.setAttribute("topicList", topicList);
	}
	
	/**
	 * 加载课题任务人
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignTopicTask(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userId 			= (String) request.getSession().getAttribute("user_id");
		String where 			= "from Selectfirst where deptId = '" + department.getDeptId() + 
				"' AND stuId = '" + userId + "' AND selStatus = '1' ";
		Selectfirst selectfirst = selectfirstDao.getOne(where);
		
		request.setAttribute("selectfirst", selectfirst);
	}
	
	/**
	 * 加载是否有课题选择记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignTopicSelect(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userId 			= (String) request.getSession().getAttribute("user_id");
		String where 			= "from Selectfirst where deptId = '" + department.getDeptId() + 
				"' AND stuId = '" + userId + "' ";
		List<Selectfirst> selList 	= selectfirstDao.getAll(where);
		
		request.setAttribute("selList", selList);
	}
	
	/**
	 * 加载年级列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException
	 */
	private void _assignTbgradeList(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String where 	= "from Tbgrade where deptId = '" + department.getDeptId() + 
				"' order by graNumber desc ";
		
		request.setAttribute("gradeList", tbgradeService.getAllRowsByWhere(where));
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/user/site/repwd.do")
	public ModelAndView repwd(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		if(!_verifyData(request)) {
			return new ModelAndView("forward:/user/site/repassword.do");
		}
		String where = "from User where userName = '" + request.getSession().getAttribute("user_id").toString() + "'";
		User user 	= userService.getOneUser(where);
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
	
	/**
	 * 学生个人信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/site/stuperfile.do")
	public ModelAndView stuPerfile(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/student/perfile");
		String id 	= (String) request.getSession().getAttribute("user_id");
		try {
			mav.addObject("student", studentService.getOneById(id));
			StudentController._assignSexMap(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 修改学生信息视图
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/site/stueditfileview.do")
	public ModelAndView stuEditfileView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/student/perfile-edit");
		String id 	= (String) request.getSession().getAttribute("user_id");
		try {
			mav.addObject("student", studentService.getOneById(id));
			StudentController._assignSexMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mav;
	}
	
	/**
	 * 修改学生信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/site/stueditfile.do")
	public ModelAndView stuEditfile(HttpServletRequest request, HttpServletResponse response)
	{
		String id 		= (String) request.getSession().getAttribute("user_id");
		String sex 		= request.getParameter("sex");
		String phone 	= request.getParameter("phone");
		String email 	= request.getParameter("email");
		if(Verify.isEmpty(email)) {
			return new ModelAndView("forward:/user/site/stueditfileview.do", "message", "电子邮箱不能为空");
		}
		if(!Verify.isEmail(email.trim())) {
			return new ModelAndView("forward:/user/site/stueditfileview.do", "message", "电子邮箱格式不正确");
		}
		Student student = studentService.getOneById(id);
		if(Verify.isEmpty(student)) {
			return new ModelAndView("forward:/user/site/stueditfileview.do", "message", "记录不存在");
		}
		try {
			student.setStuSex(sex);
			student.setStuTel(phone);
			student.setStuEmail(email.trim());
			saveImage_path(request, student);
			
			studentService.editOneStudent(student);
			
			return new ModelAndView("redirect:/user/site/stuperfile.do");
		} catch (Exception e) {
			return new ModelAndView("forward:/user/site/stueditfileview.do", "message", "服务器繁忙,请稍后再试");
		}
	}
	
	/**
	 * 教师个人信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/site/teaperfile.do")
	public ModelAndView perfile(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/perfile");
		String id 	= (String) request.getSession().getAttribute("user_id");
		try {
			mav.addObject("teacher", teacherService.getOneTeacherById(id));
			TeacherController.assignSexMapMap(request);
			TeacherController.assignTeacherposMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 修改教师信息视图
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/site/teaeditfileview.do")
	public ModelAndView teaEditfileView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/perfile-edit");
		String id 	= (String) request.getSession().getAttribute("user_id");
		try {
			mav.addObject("teacher", teacherService.getOneTeacherById(id));
			TeacherController.assignTeacherposListMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mav;
	}
	
	/**
	 * 修改教师个人信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/site/teaeditfile.do")
	public ModelAndView teaEditfile(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("message", "信息修改成功");
		String id 		= (String) request.getSession().getAttribute("user_id");
		String sex 		= request.getParameter("sex");
		String pos 		= request.getParameter("pos");
		String phone 	= request.getParameter("phone");
		String email 	= request.getParameter("email");
		if(Verify.isEmpty(email)) {
			return new ModelAndView("forward:/user/site/teaeditfileview.do", "message", "电子邮箱不能为空");
		}
		if(!Verify.isEmail(email.trim())) {
			return new ModelAndView("forward:/user/site/teaeditfileview.do", "message", "电子邮箱格式不正确");
		}
		Teacher teacher = teacherService.getOneTeacherById(id);
		if(Verify.isEmpty(teacher)) {
			return new ModelAndView("forward:/user/site/teaeditfileview.do", "message", "记录不存在");
		}
		try {
			teacher.setTeaSex(sex);
			teacher.setTeaPos(pos);
			teacher.setTeaTel(phone);
			teacher.setTeaEmail(email.trim());
			saveImage_path(request, teacher);
			teacherService.editOneTeacher(teacher);
			
			return new ModelAndView("redirect:/user/site/teaperfile.do");
		} catch (Exception e) {
			return new ModelAndView("forward:/user/site/teaeditfileview.do", "message", "服务器繁忙,请稍后再试");
		}
	}
	
	/**
	 * 头像上传
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param obj
	 */
	protected void saveImage_path(HttpServletRequest request, Object obj) 
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 /** 页面控件的文件流* */
        MultipartFile multipartFile = multipartRequest.getFile("image_path");
        if(multipartFile.getSize() <= 0)
        {
        	return;
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        /** 构建文件保存的目录* */
        String logoPathDir = "uploadfiles/" + dateformat.format(new Date());
        String tempPathDir = logoPathDir;
        /** 得到文件保存目录的真实路径* */
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        /** 获取文件的后缀* */
        String suffix = multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf("."));
        /** 使用UUID生成文件名称* */
        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        /** 拼成完整的文件保存路径加文件* */
        String fileName = logoRealPathDir + File.separator + logImageName;
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
			Class cls 		= obj.getClass();
			Field imagePathfield 	= cls.getDeclaredField("imagePath");
			imagePathfield.setAccessible(true);
			Method imagePathmethod 	= cls.getDeclaredMethod("set" + StringUtil.ufirst("imagePath"), String.class);
			imagePathmethod.invoke(obj, tempPathDir + "/" + logImageName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class myThread implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("mythread");
		
	}
	
}