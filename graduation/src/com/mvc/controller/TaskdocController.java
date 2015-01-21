package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Reviewresult;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Taskdoc;
import com.mvc.exception.VerifyException;
import com.mvc.service.MessageService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.StudentService;
import com.mvc.service.TaskdocService;
import com.mvc.service.TbgradeService;
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
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private OpentopicscoreService opentopicscoreService;
	
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
	
	public static LinkedHashMap<String, String> _taskdocMap = new LinkedHashMap<String, String>(){{
		put("0", "<button class=\"btn btn-success\" type=\"button\">下发任务书</button>");
		put("1", "任务书已下发");
	}};
	
	/**
	 * 发送任务书列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-13 上午11:12:37
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/senddoclist.do")
	public ModelAndView senddocList(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/taskdoc-list");
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String grade 			= request.getParameter("grade");
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
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
		String where = "from Selectfirst where selStatus = '1' AND teaId = '" + userid + 
				"' AND deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(grade)) {
			where += " AND stuId IN (select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (select p.proId from Profession p " +
					"where p.graId = " + Integer.parseInt(grade) + " AND deptId = '" + department.getDeptId() + 
					"' ) ) ) ";
		}
		List<Selectfirst> selsList = taskdocService.getAllSelefRecordByPages(where, pagination);
		_assignGradeInfo(request);
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
	 * 加载年级信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午06:00:58
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignGradeInfo(HttpServletRequest request) throws VerifyException 
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Tbgrade where deptId = '" + department.getDeptId() + 
			"' order by graNumber desc";
		
		request.setAttribute(
				"gradeList", 
				tbgradeService.getAllRowsByWhere(where)
				);		
	}
	
	/**
	 * 加载学生Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 下午02:24:00
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap(List<Selectfirst> data, HttpServletRequest request) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/senddocview.do")
	public ModelAndView senddocView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
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
	 * @throws VerifyException 
	 */
	private void _assignTaskdoc(HttpServletRequest request) throws VerifyException {
		String where = "from Taskdoc where 	stuId = '" + request.getParameter("taskerid") + 
				"' and teaId = '" + request.getSession().getAttribute("user_id") + "'";
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/send.do")
	public ModelAndView send(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		String stuid 		= request.getParameter("taskerid");
		String where 	= "from Taskdoc where stuId = '" + stuid + "'";
		Taskdoc taskdoc 	= taskdocService.getRecordByWhere(where);
		try{
			if(Verify.isEmpty(taskdoc)) {
				taskdoc 	= new Taskdoc();
				_setTaskdocInfo(taskdoc, request);
				taskdocService.saveOneTaskdoc(taskdoc);
				request.setAttribute("message", "任务书下发成功");
			} else {
				_setTaskdocInfo(taskdoc, request);
				taskdocService.editOneTaskdoc(taskdoc);
				request.setAttribute("message", "任务书修改成功");
			}
			_assignSendMessage(request);
		}catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
		}
		
		return new ModelAndView("forward:/user/taskdoc/senddoclist.do");
	}
	
	/**
	 * ajax发送任务书
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/asend.do")
	public void asend(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		String stuid 		= request.getParameter("taskerid");
		if(Verify.isEmpty(stuid)) {
			HResponse.errorJSON("学生不能为空", response);
			return;
		}
		try {
			String where 	= "from Taskdoc where stuId = '" + stuid + "' ";
			Taskdoc taskdoc 	= taskdocService.getRecordByWhere(where);
			if(Verify.isEmpty(taskdoc)) {
				taskdoc 	= new Taskdoc();
				_setTaskdocInfo(taskdoc, request);
				taskdocService.saveOneTaskdoc(taskdoc);
				request.setAttribute("message", "任务书下发成功");
				_assignSendMessage(request);
			} else {
				if(isPassOpentopic(request)) {
					HResponse.errorJSON("该学生已通过开题答辩，任务书不可修改", response);
					return;
				}
				_setTaskdocInfo(taskdoc, request);
				taskdocService.editOneTaskdoc(taskdoc);
				request.setAttribute("message", "任务书修改成功");
			}	
			HResponse.okJSON(request.getAttribute("message").toString(), response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
	
	/**
	 * 开题答辩是否已经通过 通过返回true
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private boolean isPassOpentopic(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String stuid 			= request.getParameter("taskerid");
		String where = "from Opentopicscore where departmentId = '" + department.getDeptId() + 
				"' AND studentId = '" + stuid.trim() + "' AND score > 60 ";
		List<Opentopicscore> list = opentopicscoreService.getAllRowsByWhere(where);
		if(Verify.isEmpty(list)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 检测任务书是否可修改
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/checkedit.do")
	public void checkedit(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		if(isPassOpentopic(request)) {
			HResponse.errorJSON("该学生已通过开题答辩，任务书不可修改", response);
			return;
		}
		
		HResponse.okJSON(response);
	}

	/**
	 * 发送消息给学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param request 
	 * @date 2014-8-28 上午10:58:35
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignSendMessage(HttpServletRequest request) throws VerifyException {
		Message message = new Message();
		message.setName("怀化学院本科毕业论文(设计)任务书");
		message.setContent(
				"指导老师 " + request.getSession().getAttribute("user_name") + " 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收"
				);
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
	
	/**
	 * 查看任务书
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException
	 */
	@RequestMapping(value="/review.do")
	public ModelAndView review(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/taskdoc-review");
		String topicid 	= request.getParameter("topicid");
		String taskerid = request.getParameter("taskerid");
		if(Verify.isEmpty(topicid)) {
			throw new VerifyException("课题编号不能为空");
		}
		if(Verify.isEmpty(taskerid)) {
			throw new VerifyException("课题任务人不能为空");
		}
		_assignTaskdoc(request);
		if(Verify.isEmpty(request.getAttribute("taskdoc"))) {
			throw new VerifyException("任务书不存在");
		}
		_assignStudentInfo(request, taskerid);
		TeacherController.assignTeacherposMap(request);
		mav.addObject("topic", tbtopicService.getByTopId(topicid));
		mav.addObject("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
		
		return mav;
	}
	
	/**
	 * 编辑任务书视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-15 下午07:08:36
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/editview.do")
	public ModelAndView editdocView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/taskdoc-edit");
		String topicid 	= request.getParameter("topicid");
		String taskerid = request.getParameter("taskerid");
		if(Verify.isEmpty(topicid)) {
			throw new VerifyException("课题编号不能为空");
		}
		if(Verify.isEmpty(taskerid)) {
			throw new VerifyException("课题任务人不能为空");
		}
		_assignTaskdoc(request);
		if(Verify.isEmpty(request.getAttribute("taskdoc"))) {
			throw new VerifyException("任务书不存在");
		}
		_assignStudentInfo(request, taskerid);
		TeacherController.assignTeacherposMap(request);
		mav.addObject("topic", tbtopicService.getByTopId(topicid));
		mav.addObject("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
		
		return mav;
	}
	
	/**
	 * ajax获取列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/agetlist.do")
	public void ajaxGetJudgedList(HttpServletRequest request, HttpServletResponse response)
	{
		String userid 	= (String) request.getSession().getAttribute("user_id");
		String[] ids 	= request.getParameterValues("ids[]");
		try {
			if(ids.length < 0) {
				HResponse.errorJSON(response);
				return;
			}
			String id = "";
			for(int i = 0; i < ids.length; i ++) {
				id += ids[i] + ",";
			}
			id = id.substring(0, id.lastIndexOf(","));
			String where = "from Taskdoc where teaId = '" + userid + "' AND stuId IN (" + id + ") ";
			List<Taskdoc> taskList = taskdocService.getAllRows(where);
			String turnid = "";
			if(!Verify.isEmpty(taskList)) {
				for(int j = 0; j < taskList.size(); j ++) {
					Taskdoc taskdoc = taskList.get(j);
					turnid += "{\"id\": " + "\"" + taskdoc.getStuId() + "\"},";
				}
				turnid = turnid.substring(0, turnid.lastIndexOf(","));
				turnid = "[" + turnid + "]";
			}
			
			HResponse.okJSON("成功", turnid, response);
		} catch (Exception e) {
			e.printStackTrace();

			HResponse.errorJSON(response);
		}
	}
}
