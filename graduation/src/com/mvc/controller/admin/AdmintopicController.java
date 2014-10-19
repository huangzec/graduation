package com.mvc.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.RequestSetAttribute;
import com.mvc.entity.Department;
import com.mvc.entity.Teacher;
import com.mvc.service.TeacherService;

/**
 * 后台课题控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/topic")
public class AdmintopicController {
	
	@Autowired
	private TeacherService teacherService;
	
	private List<Teacher> list = new ArrayList<Teacher>();

	public List<Teacher> getList() {
		return list;
	}

	public void setList(List<Teacher> list) {
		this.list = list;
	}

	/**
	 * 课题评审安排
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-30 上午09:42:07
	 * @return ModelAndView
	 */
	@RequestMapping(value="/topicorderview.do")
	public ModelAndView topicOrderView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String where 	= "from Teacher where deptId = '" + department.getDeptId() + "'";
		list 	= teacherService.getAllRows(where);
		mav.addObject("list", list);
		mav.setViewName("admin/topic/judge");
		
		return mav;
	}
	
	/**
	 * 评审安排
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-30 下午03:24:40
	 * @return void
	 * @throws IOException 
	 */
	@RequestMapping(value="/judgeorder.do")
	public void judgeOrder(@RequestParam(value="ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String id = "";
		for(int i = 0; i < ids.length; i++) {
			if(i == 0) {
				id += ids[i];
			}else 
			id += "," + ids[i];
		}
		String where = "from Teacher where teaId IN (" + id.toString() + ")";
		List<Teacher> listtemp = teacherService.getAllRows(where);
		response.setContentType("text/html;charset=utf-8");
		try{
			for(Teacher teacher : listtemp)
			{
				teacher.setTeaJudge(true);
				teacherService.editOneTeacher(teacher);
			}
			response.getWriter().write("true");
		}catch (Exception e) {
			response.getWriter().write("false");
		}
	}
	
	/**
	 * 课题评审教师安排
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-8 上午10:06:14
	 * @return ModelAndView
	 */
	@RequestMapping(value="/judge.do")
	public ModelAndView judge(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department 	= (Department) request.getSession().getAttribute("department");
		String[] ids 			= request.getParameterValues("ids");
		String where 	= "from Teacher where deptId = '" + department.getDeptId() + "'";
		list 	= teacherService.getAllRows(where);
		if(null == list || 1 > list.size()) {
			mav.setViewName("public/ajaxDone");
			RequestSetAttribute.requestSetAttribute(request, 300, "", "教师记录不存在", "", "");
			
			return mav;
		}
		if(null == ids || 1 > ids.length) {
			try {
				for(int i = 0; i < list.size(); i ++) {
					Teacher teacher = list.get(i);
					if(teacher.getTeaJudge() == true) {
						teacher.setTeaJudge(false);
						teacherService.editOneTeacher(teacher);
					}
				}
				RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "安排成功", "topicorder", "/admin/topic/topicorderview.do");
			} catch (Exception e) {
				RequestSetAttribute.requestSetAttribute(request, 300, "", "服务器繁忙，请稍后再试", "", "");
			}
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}else {
			try {
				for(int i = 0; i < list.size(); i ++) {
					Teacher teacher = list.get(i);
					boolean isJuddge = true;
					for(int j = 0; j < ids.length; j ++) {
						if(teacher.getTeaId().equals(ids[j])) {
							teacher.setTeaJudge(true);
							teacherService.editOneTeacher(teacher);
							isJuddge = false;
							break;
						}
					}
					if(isJuddge) {
						teacher.setTeaJudge(false);
						teacherService.editOneTeacher(teacher);
					}
				}
				RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "安排成功", "topicorder", "/admin/topic/topicorderview.do");
			} catch (Exception e) {
				RequestSetAttribute.requestSetAttribute(request, 300, "", "服务器繁忙，请稍后再试", "", "");			
			}
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
	}
}
