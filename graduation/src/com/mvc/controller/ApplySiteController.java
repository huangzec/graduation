package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.HResponse;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.SqlUtil;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Apply;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeone;
import com.mvc.entity.LinkeddataApplyTopicapply;
import com.mvc.entity.LinkeddataApplyTopicfinish;
import com.mvc.entity.LinkeddataApplyTopicreport;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Topicapply;
import com.mvc.entity.Topicfinish;
import com.mvc.entity.Topicreport;
import com.mvc.service.ApplyService;
import com.mvc.service.GradeoneService;
import com.mvc.service.LinkeddataApplyTopicapplyService;
import com.mvc.service.LinkeddataApplyTopicfinishService;
import com.mvc.service.LinkeddataApplyTopicreportService;
import com.mvc.service.ProfessionService;
import com.mvc.service.StudentService;
import com.mvc.service.TbtopicService;
import com.mvc.service.TeacherService;
import com.mvc.service.TopicapplyService;
import com.mvc.service.TopicfinishService;
import com.mvc.service.TopicreportService;

/**
 * 开题答辩、毕业答辩申请控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/apply")
public class ApplySiteController {

	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TopicapplyService topicapplyService;
	
	@Autowired
	private TopicreportService topicreportService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private TopicfinishService topicfinishService;
	
	@Autowired
	private GradeoneService gradeoneService;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private LinkeddataApplyTopicapplyService linkeddataApplyTopicapplyService;
	
	@Autowired
	private LinkeddataApplyTopicreportService linkeddataApplyTopicreportService;
	
	@Autowired
	private LinkeddataApplyTopicfinishService linkeddataApplyTopicfinishService;
	
	private Pagination pagination;
	private List<Apply> list = new ArrayList<Apply>();
	private List<Topicfinish> topicFinishList = new ArrayList<Topicfinish>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Apply> getList() {
		return list;
	}

	public void setList(List<Apply> list) {
		this.list = list;
	}

	public List<Topicfinish> getTopicFinishList() {
		return topicFinishList;
	}

	public void setTopicFinishList(List<Topicfinish> topicFinishList) {
		this.topicFinishList = topicFinishList;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	/**
	 * 注册答辩类型Map
	 */
	private static LinkedHashMap<String, String> _typeMap 	= new LinkedHashMap<String, String>(){{
		put("1", "开题答辩");
		put("2", "毕业答辩");
	}};
	
	/**
	 * 注册状态Map
	 */
	private static LinkedHashMap<String, String> _statusMap 	= new LinkedHashMap<String, String>(){{
		put("1", "未受理");
		put("2", "已受理");
	}};
	
	/**
	 * 注册同意答辩Map
	 */
	private static LinkedHashMap<String, String> _passMap 	= new LinkedHashMap<String, String>(){{
		put(
				"0",
				"<button class=\"btn btn-success\" pass=\"2\" type=\"button\">同意参加答辩</button>&nbsp;&nbsp;" +
				"<button class=\"btn btn-success\" pass=\"1\"  type=\"button\">不同意参加答辩</button>"
			);
		put("1", "不同意");
		put("2", "<lable style=\"color: red;\">已同意</lable>");
	}};
	
	/**
	 * 加载类型Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-5 下午03:34:46
	 * @return void
	 */
	public static void _assignTypeMap(HttpServletRequest request)
	{
		request.setAttribute(
				"type_map", 
				MapUtil.makeLinkedMapMap(_typeMap)
				);
	}
	
	/**
	 * 加载状态Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-10 下午09:24:58
	 * @return void
	 */
	public static void _assignStatusMap(HttpServletRequest request)
	{
		request.setAttribute(
				"status_map", 
				MapUtil.makeLinkedMapMap(_statusMap)
				);
	}
	
	/**
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/add");
		String prowhere = "select * from profession where `pro_ID` = (" +
				"select pro_ID from tbclass where cla_ID = (" +
				"select cla_ID from student where stu_ID = " + request.getSession().getAttribute("user_id") + "))";
		Map<String, String> professionMap = professionService.getLinkedRecordByWhere(prowhere);
		String selWhere = "from Selectfirst where stuId = '" + request.getSession().getAttribute("user_id") + "' AND selStatus = '1'";
		Selectfirst selectfirst = selectfirstDao.getOne(selWhere);
		if(Verify.isEmpty(selectfirst)) {
			request.setAttribute("message", "你还没被指导老师确认为任务人");
		}
		request.setAttribute("profession_map", professionMap);
		request.setAttribute("selectfirst", selectfirst);
		_assignTeacherInfo(request, selectfirst);
		_assignTopicInfo(request, selectfirst);
		
		return mav;
	}
	
	/**
	 * 加载课题信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 上午10:56:25
	 * @return void
	 */
	private void _assignTopicInfo(HttpServletRequest request, Selectfirst selectfirst)
	{
		if(Verify.isEmpty(selectfirst)) {
			return;
		}
		String where = "from Tbtopic where topId = '" + selectfirst.getTbtopic().getTopId() + "'";
		
		request.setAttribute(
				"topic_map",
				ArrayUtil.turnRecordToMap("topId", "topName", selectfirstDao.getOne(where))
				);
	}

	/**
	 * 加载指导教师信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 上午10:56:43
	 * @return void
	 */
	private void _assignTeacherInfo(HttpServletRequest request, Selectfirst selectfirst) 
	{
		if(Verify.isEmpty(selectfirst)) {
			return;
		}
		
		request.setAttribute(
				"teacher_map",
				ArrayUtil.turnRecordToMap("teaId", "teaName", teacherService.getOneTeacherById(selectfirst.getTeaId()))
				);
		
	}

	/**
	 * 添加开题答辩、毕业答辩申请
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView openTopicAdd(HttpServletRequest request, HttpServletResponse response)
	{
		String userId 	= (String) request.getSession().getAttribute("user_id");
		String topicId 	= request.getParameter("topic_id");
		String type 	= request.getParameter("type");
		Department department 	= (Department) request.getSession().getAttribute("dept");
		if(Verify.isEmpty(topicId)) {
			return new ModelAndView("forward:/user/apply/addview.do", "message", "课题编号不能为空");
		}
		String where 	= "from Apply where userId = '" + userId 
			+ "' AND type = '" + type + "' AND pass = '0' AND status = '1'";
		Apply apply 	= applyService.getRecordByWhere(where);
		if(!Verify.isEmpty(apply)) {
			return new ModelAndView("forward:/user/apply/addview.do", "message", "你还有该类型未被处理的答辩申请");
		}
		try {
			apply 	= new Apply();
			apply.setUserId(userId);
			apply.setDepartmentId(department.getDeptId());
			apply.setType(type);
			apply.setPass("0");
			apply.setStatus("1");
			apply.setCreateTime(HResponse.formatDateTime(new Date()));
			int applyId = applyService.addOneReturn(apply);
			if(applyId == 0) {
				return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
			}
			request.setAttribute("applyId", applyId + "");
			if(type.equals("1")) {
				int topicApplyId 	= _assignOpenTopicApply(request);
				int topicReportId 	= _assignOpenTopicReport(request);
				if(topicApplyId == 0 || topicReportId == 0) {
					return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
				}
				/**
				 * 添加答辩申请与答辩申请表关联关系
				 */
				LinkeddataApplyTopicapply linkeddataApplyTopicapply = new LinkeddataApplyTopicapply();
				linkeddataApplyTopicapply.setItemId(applyId + "");
				linkeddataApplyTopicapply.setRelId(topicApplyId + "");
				linkeddataApplyTopicapply.setExtend("0");
				linkeddataApplyTopicapply.setCreateTime(HResponse.formatDateTime(new Date()));
				int linkeddataApplyId = linkeddataApplyTopicapplyService.addOneReturn(linkeddataApplyTopicapply);
				if(linkeddataApplyId == 0) {
					return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
				}
				/**
				 * 添加答辩申请与答辩报告书关联关系
				 */
				LinkeddataApplyTopicreport linkeddataApplyTopicreport = new LinkeddataApplyTopicreport();
				linkeddataApplyTopicreport.setItemId(applyId + "");
				linkeddataApplyTopicreport.setRelId(topicReportId + "");
				linkeddataApplyTopicreport.setExtend("0");
				linkeddataApplyTopicreport.setCreateTime(HResponse.formatDateTime(new Date()));
				int linkeddataReportId = linkeddataApplyTopicreportService.addOneReturn(linkeddataApplyTopicreport);
				if(linkeddataReportId == 0) {
					return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
				}
			} else if(type.equals("2")) {
				int topicFinishId  = _assignOpenTopicFinish(request);
				if(topicFinishId == 0) {
					return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
				}
				/**
				 * 添加答辩申请与毕业答辩相关材料关联关系
				 */
				LinkeddataApplyTopicfinish linkeddataApplyTopicfinish = new LinkeddataApplyTopicfinish();
				linkeddataApplyTopicfinish.setItemId(applyId + "");
				linkeddataApplyTopicfinish.setRelId(topicFinishId + "");
				linkeddataApplyTopicfinish.setExtend("0");
				linkeddataApplyTopicfinish.setCreateTime(HResponse.formatDateTime(new Date()));
				int linkeddataFinishId = linkeddataApplyTopicfinishService.addOneReturn(linkeddataApplyTopicfinish);
				if(linkeddataFinishId == 0) {
					return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
				}
			}
			
			return new ModelAndView("forward:/user/apply/list.do", "message", "答辩申请成功");
		} catch (Exception e) {
			return new ModelAndView("forward:/user/apply/addview.do", "message", "答辩申请失败");
		}
	}

	/**
	 * 提交开题答辩申请表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-14 上午11:03:52
	 * @return int
	 */
	private int _assignOpenTopicApply(HttpServletRequest request) 
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userId 			= (String) request.getSession().getAttribute("user_id");
		String topicId 			= request.getParameter("topic_id");
		String openTime 		= request.getParameter("open_time");
		String knowing 			= request.getParameter("knowing");
		String teacherIdea 		= request.getParameter("teacher_idea");
		String paperIdea 		= request.getParameter("paper_idea");
		String departmentIdea 	= request.getParameter("department_idea");
		
		Topicapply topicapply 	= new Topicapply();
		topicapply.setUserId(userId);
		topicapply.setTopicId(topicId);
		topicapply.setParentId(department.getDeptId());
		topicapply.setOpenTime(openTime);
		topicapply.setKnowing(StringUtil.encodeHtml(knowing));
		topicapply.setTeacherIdea(StringUtil.encodeHtml(teacherIdea));
		topicapply.setPaperIdea(StringUtil.encodeHtml(paperIdea));
		topicapply.setDepartmentIdea(StringUtil.encodeHtml(departmentIdea));
		topicapply.setCreateTime(HResponse.formatDate(new Date()));
		int topicapplyId = topicapplyService.addOneReturn(topicapply);
		
		return topicapplyId;
	}

	/**
	 * 提交开题答辩报告书
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-14 上午11:07:10
	 * @return int
	 */
	private int _assignOpenTopicReport(HttpServletRequest request)
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userId 			= (String) request.getSession().getAttribute("user_id");
		String meaning 			= request.getParameter("meaning");
		String content 			= request.getParameter("content");
		String research 		= request.getParameter("research");
		String deadline 		= request.getParameter("deadline");
		String references 		= request.getParameter("references");
		String teacherView 		= request.getParameter("teacher_view");
		String meeting			= request.getParameter("meeting");
		String judgeView 		= request.getParameter("judge_view");
		String departmentView 	= request.getParameter("department_view");
		Topicreport topicreport = new Topicreport();
		topicreport.setStuId(userId);
		topicreport.setDepartmentId(department.getDeptId());
		topicreport.setMeaning(StringUtil.encodeHtml(meaning));
		topicreport.setContent(StringUtil.encodeHtml(content));
		topicreport.setResearch(StringUtil.encodeHtml(research));
		topicreport.setDeadline(StringUtil.encodeHtml(deadline));
		topicreport.setReferencesl(StringUtil.encodeHtml(references));
		topicreport.setTeacherView(StringUtil.encodeHtml(teacherView));
		topicreport.setMeeting(meeting);
		topicreport.setJudgeView(judgeView);
		topicreport.setDepartmentView(departmentView);
		topicreport.setCreateTime(HResponse.formatDateTime(new Date()));
		
		return topicreportService.addOneReturn(topicreport);
	}
	
	/**
	 * 提交毕业答辩相关材料
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-14 下午08:02:49
	 * @return int
	 */
	private int _assignOpenTopicFinish(HttpServletRequest request)
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userId 			= (String) request.getSession().getAttribute("user_id");
		String title 			= request.getParameter("title");
		String content 			= request.getParameter("finish");
		
		Topicfinish topicfinish = new Topicfinish();
		topicfinish.setStuId(userId);
		topicfinish.setDepartmentId(department.getDeptId());
		topicfinish.setTitle(title);
		topicfinish.setContent(StringUtil.encodeHtml(content));
		topicfinish.setCreateTime(HResponse.formatDateTime(new Date()));
		int topicfinishId = topicfinishService.addOneReturn(topicfinish);
		
		return topicfinishId;
	}
	
	/**
	 * 数据列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/list");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		};
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
		String where = "from Apply where userId = '" + request.getSession().getAttribute("user_id") + 
			"' order by createTime desc";
		list = applyService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Apply>) applyService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", list);
		modelMap.put("pagination", pagination);
		_assignTypeMap(request);
		_assignStatusMap(request);
		
		return mav;
	}
	
	/**
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 编辑视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 开题答辩确认
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-7 上午11:23:17
	 * @return ModelAndView
	 */
	@RequestMapping(value="/confirm.do")
	public ModelAndView confirm(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("dept");
		mav.setViewName("user/apply/confirm");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		};
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
		String where = "from Apply where type = '1' AND departmentId = '" + department.getDeptId() + 
			"' AND userId IN (select stuId from Selectfirst where selStatus = '1' AND teaId = '" + 
			request.getSession().getAttribute("user_id") + "') order by pass asc ";
		list = applyService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Apply>) applyService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", list);
		modelMap.put("pagination", pagination);
		_assignPassMap(request);
		_assignStudentListMap(list, request);
		
		return mav;
	}
	
	/**
	 * 毕业答辩确认
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-14 下午10:10:25
	 * @return ModelAndView
	 */
	@RequestMapping(value="/finishconfirm.do")
	public ModelAndView finishConfirm(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/finish-confirm");
		Department department = (Department) request.getSession().getAttribute("dept");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		};
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
		String where = "from Apply where type = '2' AND departmentId = '" + department.getDeptId() + 
			"' AND userId IN (select stuId from Selectfirst where selStatus = '1' AND teaId = '" + 
			request.getSession().getAttribute("user_id") + "') order by pass asc ";
		list = applyService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Apply>) applyService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", list);
		modelMap.put("pagination", pagination);
		_assignPassMap(request);
		_assignStudentListMap(list, request);
		
		return mav;
	} 

	/**
	 * 注册学生Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 下午02:24:00
	 * @return void
	 */
	private void _assignStudentListMap(List<Apply> data, HttpServletRequest request) {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("stuId", "userId", data);
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("userId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
	}
	
	/**
	 * 注册单个学生Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-15 下午01:58:53
	 * @return void
	 */
	private void _assignStudentRecordMap(Topicfinish topicfinish, HttpServletRequest request)
	{
		Student student = studentService.getOneById(topicfinish.getStuId());
		if(Verify.isEmpty(student)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnRecordToMap("stuId", "stuName", student));
	}

	/**
	 * 加载是否同意Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-7 下午05:38:41
	 * @return void
	 */
	private void _assignPassMap(HttpServletRequest request) {
		request.setAttribute(
				"pass_map", 
				MapUtil.makeLinkedMapMap(_passMap)
				);
	}
	
	/**
	 * 是否同意答辩
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-11 下午12:06:16
	 * @return void
	 */
	@RequestMapping(value="/agree.do")
	public void agree(HttpServletRequest request, HttpServletResponse response)
	{
		String id 	= request.getParameter("id");
		String pass = request.getParameter("pass");
		Apply apply = applyService.getOneRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(apply)) {
			HResponse.errorJSON("记录不存在", response);
		}
		try {
			apply.setPass(pass);
			applyService.editOneApply(apply);
			HResponse.okJSON(response);
		} catch (Exception e) {
			HResponse.errorJSON(response);
		}
	}
	
	/**
	 * 查看开题答辩相关文档
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-13 下午02:25:05
	 * @return ModelAndView
	 */
	@RequestMapping(value="/opentopicword.do")
	public ModelAndView openTopicWord(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/opentopicword");
		Department department = (Department) request.getSession().getAttribute("dept");
		String stuid 		= request.getParameter("userid");
		if(Verify.isEmpty(stuid)) {
			return mav;
		}
		String aplyWhere 	= "from Topicapply where parentId = '" + department.getDeptId() + 
			"' AND userId = '" + stuid + "' order by createTime desc ";
		String reportWhere 	= "from Topicreport where departmentId = '" + department.getDeptId() + 
			"' AND stuId = '" + stuid + "' order by createTime desc ";
		String selWhere 	= "from Selectfirst where stuId = '" + stuid + "' AND selStatus = '1' AND teaId = '" + 
			request.getSession().getAttribute("user_id") + "'";
		System.out.println("selwhere " + selWhere);
		Selectfirst selectfirst = selectfirstDao.getOne(selWhere);
		String topWhere 	= "from Tbtopic where " ;
		topWhere += Verify.isEmpty(selectfirst) ? " 1 = 2" : " topId = '" + selectfirst.getTbtopic().getTopId() + "'";
		Topicreport topicreport = topicreportService.getRecordByWhere(reportWhere);		
		Topicapply topicapply = topicapplyService.getRecordByWhere(aplyWhere);
		mav.addObject("topic", tbtopicService.getRecordByWhere(topWhere));
		mav.addObject("topicapply", topicapply);
		mav.addObject("topicreport", topicreport);
		_assignStudentInfo(request, stuid);
		mav.addObject("teacher", teacherService.getOneTeacherById((String) request.getSession().getAttribute("user_id")));
		_assignStudentRecordMap(request);
		
		return mav;
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
	 * 加载学生记录Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	private void _assignStudentRecordMap(HttpServletRequest request)
	{
		if(Verify.isEmpty(request.getParameter("userid"))) {
			return;
		}
		Student student = studentService.getOneById(request.getParameter("userid"));
		if(Verify.isEmpty(student)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnRecordToMap("stuId", "stuName", student));
	}
	
	/**
	 * 提交指导老师意见
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return	
	 */
	@RequestMapping(value="/enterteacherview.do")
	public ModelAndView enterTeacherView(HttpServletRequest request, HttpServletResponse response)
	{
		String teacherView 	= request.getParameter("teacher-view");
		String opinion 		= request.getParameter("opinion");
		String applyId 		= request.getParameter("applyid");
		String reportId 	= request.getParameter("reportid");
		if(Verify.isEmpty(applyId) || Verify.isEmpty(reportId)) {
			request.setAttribute("message", "文档不存在");
			
			return new ModelAndView("forward:/user/apply/confirm.do");
		}
		try {
			Topicapply topicapply 	= topicapplyService.getOneRecordById(Integer.parseInt(applyId));
			Topicreport topicreport = topicreportService.getOneRecordById(Integer.parseInt(reportId));
			if(Verify.isEmpty(topicapply) || Verify.isEmpty(topicreport)) {
				request.setAttribute("message", "记录不存在");
				
				return new ModelAndView("forward:/user/apply/confirm.do");
			}
			topicapply.setTeacherIdea(teacherView);
			topicapplyService.editOneTopicapply(topicapply);
			topicreport.setTeacherView(opinion);
			topicreportService.editOneTopicreport(topicreport);
			
			request.setAttribute("message", "提交意见成功");
		} catch (Exception e) {
			request.setAttribute("message", "提交意见失败");
		}
		
		return new ModelAndView("forward:/user/apply/confirm.do");
	}
	
	/**
	 * 查看毕业答辩相关文档列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-15 上午09:42:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="/finishtopicword.do")
	public ModelAndView finishTopicWord(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/finish-word");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		};
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
		String id 		= request.getParameter("id");
		String stuId 	= request.getParameter("userid");
		String linkWhere = "from LinkeddataApplyTopicfinish where ";
		if(Verify.isEmpty(id)) {
			linkWhere += " 1 = 2";
		}else {
			linkWhere += " itemId = '" + id + "' ";
		}
		LinkeddataApplyTopicfinish linkeddataApplyTopicfinish = linkeddataApplyTopicfinishService.getRecordByWhere(linkWhere);
		String where = "from Topicfinish where ";
		if(Verify.isEmpty(linkeddataApplyTopicfinish) || Verify.isEmpty(linkeddataApplyTopicfinish.getRelId())) {
			where += " 1 = 2 ";
		}else {
			where += " id = " + Integer.parseInt(linkeddataApplyTopicfinish.getRelId());
		}
		if(!Verify.isEmpty(stuId)) {
			where += " AND stuId = '" + stuId + "' ";
		}
		where += " order by createTime desc";
		topicFinishList = topicfinishService.getAllRecordByPages(where, pagination);
		if(topicFinishList == null || topicFinishList.size() < 1) {
			request.setAttribute(
					"stuId_map", 
					ArrayUtil.turnRecordToMap(
							"stuId", 
							"stuName",
							studentService.getOneById(request.getParameter("userid"))
							)
				);
			
			return mav;
		}
		if(this.topicFinishList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicFinishList = (List<Topicfinish>) topicfinishService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", topicFinishList);
		modelMap.put("pagination", pagination);
		request.setAttribute(
				"stuId_map", 
				ArrayUtil.turnRecordToMap(
						"stuId", 
						"stuName",
						studentService.getOneById(request.getParameter("userid"))
						)
			);
		
		return mav;
	}
	
	/**
	 * 毕业评阅教师查看毕业答辩相关材料
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/finishreviewtopicword.do")
	public ModelAndView finishReviewTopicWord(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/finish-word-detail");
		String userId 	= request.getParameter("userid");
		if(Verify.isEmpty(userId)) {
			request.setAttribute("message", "学生编号不能为空");
			
			return mav;
		}
		String where 	= "from Topicfinish where stuId = '" + userId + "' order by createTime desc ";
		Topicfinish topicfinish = topicfinishService.getRecordByWhere(where);
		if(Verify.isEmpty(topicfinish)) {
			request.setAttribute("message", "记录不存在");
			
			return mav;
		}
		request.setAttribute("record", topicfinish);
		_assignStudentRecordMap(topicfinish, request);
		
		return mav;
	}
	
	/**
	 * 查看毕业答辩相关文档详情
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-15 下午01:07:28
	 * @return ModelAndView
	 */
	@RequestMapping(value="/finishworddetail.do")
	public ModelAndView finishWordDetail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/finish-word-detail");
		String id 	= request.getParameter("id");
		Topicfinish topicfinish = topicfinishService.getOneRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(topicfinish)) {
			request.setAttribute("message", "记录不存在");
			
			return mav;
		}
		request.setAttribute("record", topicfinish);
		_assignStudentRecordMap(topicfinish, request);
		
		return mav;
	}
	
	/**
	 * 毕业答辩指导教师评分
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-24 上午11:00:14
	 * @return void
	 */
	@RequestMapping(value="/scoreteacher.do")
	public void scoreTeacher(HttpServletRequest request, HttpServletResponse response)
	{
		try{
			Float one 		= Float.parseFloat(request.getParameter("one"));
			Float two 		= Float.parseFloat(request.getParameter("two"));
			Float three 	= Float.parseFloat(request.getParameter("three"));
			Float four 		= Float.parseFloat(request.getParameter("four"));
			Float five 		= Float.parseFloat(request.getParameter("five"));
			Float all 		= one + two + three + four + five;
			String content 	= request.getParameter("content");
			String userId 	= request.getParameter("userId");
			Gradeone gradeone = new Gradeone();
			gradeone.setStuId(userId);
			gradeone.setGoOne(one);
			gradeone.setGoTwo(two);
			gradeone.setGoThree(three);
			gradeone.setGoFour(four);
			gradeone.setGoFive(five);
			gradeone.setGoAll(all);
			gradeone.setContent(content);
			gradeone.setStatus("1");
			gradeone.setCreateTime(HResponse.formatDateTime(new Date()));
			gradeoneService.addOne(gradeone);
			HResponse.okJSON(response);
		}catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON("服务器繁忙，请稍后重试", response);
		}
	}
}
