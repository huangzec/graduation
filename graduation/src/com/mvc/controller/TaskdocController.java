package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.HResponse;
import com.mvc.common.Pagination;
import com.mvc.common.SqlUtil;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.controller.admin.TeacherController;
import com.mvc.entity.Department;
import com.mvc.entity.Message;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Taskdoc;
import com.mvc.service.MessageService;
import com.mvc.service.StudentService;
import com.mvc.service.TaskdocService;
import com.mvc.service.TbtopicService;
import com.mvc.service.TeacherService;

/**
 * 毕业设计任务书控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/taskdoc")
public class TaskdocController {

	@Autowired
	private TaskdocService taskdocService;
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MessageService messageService;
	
	private Integer pageNum = 1;

	private int numPerPage = 10;//每页显示多少条
	private Pagination pagination;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	private List<Taskdoc> list;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<Taskdoc> getList() {
		return list;
	}

	public void setList(List<Taskdoc> list) {
		this.list = list;
	}
	
	/**
	 * 发送任务书列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-13 上午11:12:37
	 * @return ModelAndView
	 */
	@RequestMapping(value="/senddoclist.do")
	public ModelAndView senddocList(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/taskdoc-list");
		Department department = (Department) request.getSession().getAttribute("dept");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		if(pagination == null){
			pagination = new Pagination(numPerPage);
		}
		pagination.setSize(numPerPage);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Selectfirst where selStatus = '1' AND teaId = '" + request.getSession().getAttribute("user_id") + "'";
		
		List<Selectfirst> selsList = taskdocService.getAllSelefRecordByPages(where, pagination);
		if(selsList == null || selsList.size() < 1) {
			return mav;
		}
		if(selsList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			selsList = taskdocService.getAllSelefRecordByPages(where, pagination);
		}
		modelMap.put("list", selsList);
		modelMap.put("pagination", pagination);
		mav.addObject("department", department);
		_assignStudentListMap(selsList, request);
		
		return mav;
	}
	
	/**
	 * 加载学生Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 下午02:24:00
	 * @return void
	 */
	private void _assignStudentListMap(List<Selectfirst> data, HttpServletRequest request) {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("stuId", "stuId", data);
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
	}
	
	/**
	 * 发送任务书视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-15 下午07:08:36
	 * @return ModelAndView
	 */
	@RequestMapping(value="/senddocview.do")
	public ModelAndView senddocView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/taskdoc");
		String topicid 	= request.getParameter("topicid");
		String taskerid = request.getParameter("taskerid");
		_assignTaskdoc(request);
		_assignStudentInfo(request, taskerid);
		TeacherController.assignTeacherposMap(request);
		mav.addObject("topic", tbtopicService.getByTopId(topicid));
		mav.addObject("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
		
		return mav;
	}
	
	/**
	 * 加载任务书
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-27 下午07:50:13
	 * @return void
	 */
	private void _assignTaskdoc(HttpServletRequest request) {
		String where = "from Taskdoc where 	stuId = '" + request.getParameter("taskerid") + "' and teaId = '" + request.getSession().getAttribute("user_id") + "'";
		request.setAttribute("taskdoc", taskdocService.getRecordByWhere(where));		
	}

	/**
	 * 加载学生信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-16 上午11:06:06
	 * @return void
	 */
	private void _assignStudentInfo(HttpServletRequest request, String id) {
		String where = "select * from student s ,  tbclass c, profession p where s.stu_ID = '" + 
		id + "' and s.cla_ID = c.cla_ID and c.pro_ID = p.pro_ID";
		Map<String, String> map = studentService.getStudentInfo(where);
		request.setAttribute("student_map", map);
	}

	/**
	 * 预览任务书
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-15 下午07:06:28
	 * @return ModelAndView
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value="/preview.do")
	public ModelAndView preview(final HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/taskdoc-preview");
		List<Map<String, String>> prev = new ArrayList<Map<String, String>>();
		Map<String, String> taskpreviewMap = new HashMap<String, String>(){{
			put("title", request.getParameter("title"));
			put("stuid", request.getParameter("stuid"));
			put("teaid", request.getParameter("teaid"));
			put("source", request.getParameter("source"));
			put("conRequest", request.getParameter("conRequest"));
			put("refMaterial", request.getParameter("refMaterial"));
			put("workPlane", request.getParameter("workPlane"));
			put("receipttime", request.getParameter("receipttime"));
			put("finishtime", request.getParameter("finishtime"));
		}};
		prev.add(taskpreviewMap);
		_assignStudentInfo(request, request.getParameter("taskerid"));
		TeacherController.assignTeacherposMap(request);
		mav.addObject("prev", prev);
		mav.addObject("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
				
		return mav;
	}
	
	/**
	 * 发送任务书
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-16 下午09:16:47
	 * @return ModelAndView
	 */
	@RequestMapping(value="/send.do")
	public ModelAndView send(HttpServletRequest request, HttpServletResponse response)
	{
		String stuid 		= request.getParameter("taskerid");
		String teaid 		= (String) request.getSession().getAttribute("user_id");
		String where 	= "from Taskdoc where stuId = '" + stuid + "'";
		Taskdoc taskdoc 	= taskdocService.getRecordByWhere(where);
		try{
			if(Verify.isEmpty(taskdoc)) {
				taskdoc 	= new Taskdoc();
				_setTaskdocInfo(taskdoc, request);
				taskdocService.saveOneTaskdoc(taskdoc);
			} else {
				_setTaskdocInfo(taskdoc, request);
				taskdocService.editOneTaskdoc(taskdoc);
			}
			_assignSendMessage(request);
			request.setAttribute("message", "任务书下发成功");
		}catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
		}
		
		return new ModelAndView("forward:/user/taskdoc/senddoclist.do");
	}

	/**
	 * 发送消息给学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param request 
	 * @date 2014-8-28 上午10:58:35
	 * @return void
	 */
	private void _assignSendMessage(HttpServletRequest request) {
		Message message = new Message();
		message.setName("怀化学院本科毕业论文(设计)任务书");
		message.setContent("指导老师 " + request.getSession().getAttribute("user_name") + " 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收");
		message.setToId(request.getParameter("taskerid").toString());
		message.setFromId((String) request.getSession().getAttribute("user_id"));
		Short status = 1;
		message.setStatus(status);
		message.setCreateTime(HResponse.formatDateTime(new Date()));
		messageService.saveMessage(message);
	}

	/**
	 * 设置任务书信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param request 
	 * @date 2014-8-27 下午07:12:46
	 * @return void
	 */
	private void _setTaskdocInfo(Taskdoc taskdoc, HttpServletRequest request) {
		String title 		= request.getParameter("title");
		String stuid 		= request.getParameter("taskerid");
		String teaid 		= (String) request.getSession().getAttribute("user_id");
		String source 		= request.getParameter("source");
		String conRequest 	= request.getParameter("conRequest");
		String refMaterial 	= request.getParameter("refMaterial");
		String workPlane 	= request.getParameter("workPlane");
		String receipttime 	= request.getParameter("receipttime");
		String finishtime 	= request.getParameter("finishtime");
		taskdoc.setTitle(title);
		taskdoc.setStuId(stuid);
		taskdoc.setTeaId(teaid);
		taskdoc.setSource(source);
		taskdoc.setTdConRequest(StringUtil.encodeHtml(conRequest));
		taskdoc.setTdRefMaterial(StringUtil.encodeHtml(refMaterial));
		taskdoc.setTdWorkPlane(StringUtil.encodeHtml(workPlane));
		taskdoc.setReceiptTime(receipttime);
		taskdoc.setFinishTime(finishtime);
		taskdoc.setCreateTime(HResponse.formatDateTime(new Date()));
	}
}