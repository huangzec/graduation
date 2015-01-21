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
import com.mvc.entity.Gradeall;
import com.mvc.entity.Gradeone;
import com.mvc.entity.LinkeddataApplyTopicapply;
import com.mvc.entity.LinkeddataApplyTopicfinish;
import com.mvc.entity.LinkeddataApplyTopicreport;
import com.mvc.entity.Message;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Profession;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Settime;
import com.mvc.entity.Student;
import com.mvc.entity.Tbtopic;
import com.mvc.entity.Topicapply;
import com.mvc.entity.Topicfinish;
import com.mvc.entity.Topicreport;
import com.mvc.exception.VerifyException;
import com.mvc.service.ApplyService;
import com.mvc.service.GradeallService;
import com.mvc.service.GradeoneService;
import com.mvc.service.LinkeddataApplyTopicapplyService;
import com.mvc.service.LinkeddataApplyTopicfinishService;
import com.mvc.service.LinkeddataApplyTopicreportService;
import com.mvc.service.MessageService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.ProfessionService;
import com.mvc.service.SettimeService;
import com.mvc.service.StudentService;
import com.mvc.service.TbgradeService;
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
	private SettimeService settimeService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private LinkeddataApplyTopicapplyService linkeddataApplyTopicapplyService;
	
	@Autowired
	private LinkeddataApplyTopicreportService linkeddataApplyTopicreportService;
	
	@Autowired
	private LinkeddataApplyTopicfinishService linkeddataApplyTopicfinishService;
	
	@Autowired
	private OpentopicscoreService opentopicscoreService;
	
	@Autowired
	private GradeallService gradeallService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
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
	@SuppressWarnings("serial")
	private static LinkedHashMap<String, String> _typeMap 	= new LinkedHashMap<String, String>(){{
		put("1", "开题答辩");
		put("2", "毕业答辩");
	}};
	
	/**
	 * 注册状态Map
	 */
	@SuppressWarnings("serial")
	private static LinkedHashMap<String, String> _statusMap 	= new LinkedHashMap<String, String>(){{
		put("1", "未受理");
		put("2", "已受理");
	}};
	
	/**
	 * 注册同意答辩Map
	 */
	@SuppressWarnings("serial")
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
	 * 注册学生端同意答辩Map
	 */
	@SuppressWarnings("serial")
	private static LinkedHashMap<String, String> _studentPassMap 	= new LinkedHashMap<String, String>(){{
		put("0","未处理");
		put("1", "不同意");
		put("2", "同意");
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
	 * 加载是否同意答辩Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void _assignStudentPassMap(HttpServletRequest request)
	{
		request.setAttribute(
				"pass_map", 
				MapUtil.makeLinkedMapMap(_studentPassMap)
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/add");
		Student student = studentService.getOneStu("from Student where stuId = '" + (String) request.getSession().getAttribute("user_id") + "'");
		String prowhere = "select * from profession where `pro_ID` = (" +
				"select pro_ID from tbclass where cla_ID = (" +
				"select cla_ID from student where stu_ID = " + request.getSession().getAttribute("user_id") + "))";
		Map<String, String> professionMap = professionService.getLinkedRecordByWhere(prowhere);
		String selWhere = "from Selectfirst where stuId = '" + request.getSession().getAttribute("user_id") + "' AND selStatus = '1'";
		Selectfirst selectfirst = selectfirstDao.getOne(selWhere);
		if(Verify.isEmpty(selectfirst)) {
			throw new VerifyException("你还没被指导老师确认为任务人");
		}
		request.setAttribute("profession_map", professionMap);
		request.setAttribute("selectfirst", selectfirst);
		request.setAttribute("student", student);
		_assignTeacherInfo(request, selectfirst);
		_assignTopicInfo(request, selectfirst);
		TopicController.assignSelType(request);
		_assignFinishReport(request);
		
		return mav;
	}

	/**
	 * 加载课题信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 上午10:56:25
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignTopicInfo(HttpServletRequest request, Selectfirst selectfirst) throws VerifyException
	{
		if(Verify.isEmpty(selectfirst)) {
			return;
		}
		String where = "from Tbtopic where topId = '" + selectfirst.getTbtopic().getTopId() + "'";
		
		request.setAttribute("topic", tbtopicService.getRecordByWhere(where));
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView openTopicAdd(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		String userId 	= (String) request.getSession().getAttribute("user_id");
		String topicId 	= request.getParameter("topic_id");
		String type 	= request.getParameter("type");
		Department department 	= (Department) request.getSession().getAttribute("dept");
		if(Verify.isEmpty(topicId)) {
			throw new VerifyException("课题编号不能为空");
		}
		String where 	= "from Apply where userId = '" + userId 
			+ "' AND type = '" + type + "' AND status = '1'";
		Apply apply 	= applyService.getRecordByWhere(where);
		if(!Verify.isEmpty(apply)) {
			throw new VerifyException("你还有该类型未被处理的答辩申请");
		}
		String name 	= "学生 " + userId + "【" + request.getSession().getAttribute("user_name").toString() + "】";
		String content 	= "";
		String toId 	= "";
		apply 	= new Apply();
		apply.setUserId(userId);
		apply.setDepartmentId(department.getDeptId());
		apply.setType(type);
		apply.setPass("0");
		apply.setStatus("1");
		apply.setCreateTime(HResponse.formatDateTime(new Date()));
		int applyId = 0;
		String swhere 	= "from Selectfirst where stuId = '" + userId + "' AND selStatus ='1' AND deptId = '" 
				+ department.getDeptId() + "' ";
		
		Selectfirst selectfirst = selectfirstDao.getOne(swhere);
		if(type.equals("1")) {
			//如果是开题答辩  是否已经通过开题答辩
			if(_assignIsPassOpen(request)) {
				throw new VerifyException("你已经通过开题答辩了，不需要再申请开题答辩");
			}		
			applyId = applyService.addOneReturn(apply);
			if(applyId == 0) {
				
				throw new VerifyException("答辩申请失败");
			}
			request.setAttribute("applyId", applyId + "");
			int topicApplyId 	= _assignOpenTopicApply(request);
			int topicReportId 	= _assignOpenTopicReport(request);
			if(topicApplyId == 0 || topicReportId == 0) {
				
				throw new VerifyException("答辩申请失败");
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
				throw new VerifyException("答辩申请失败");
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
				
				throw new VerifyException("答辩申请失败");
			}
			name += "提交了开题答辩申请";
			content += name + "，请你及时查看，并确认是否同意其参加答辩";
			if(!Verify.isEmpty(selectfirst)) {
				toId = selectfirst.getTeaId();
			}
			_assignSendMessage(name, content, toId, request);
		} else if(type.equals("2")) {
			//如果是毕业答辩
			if(_assignIsPassFinish(request)) {
				//如果已经通过毕业答辩
				throw new VerifyException("你已经通过毕业答辩了，不需要再申请毕业答辩");
			}		
			applyId = applyService.addOneReturn(apply);
			if(applyId == 0) {
				
				throw new VerifyException("答辩申请失败");
			}
			request.setAttribute("applyId", applyId + "");
			int topicFinishId  = _assignOpenTopicFinish(request);
			if(topicFinishId == 0) {
				
				throw new VerifyException("答辩申请失败");
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
				throw new VerifyException("答辩申请失败");
			}
			name += "提交了毕业答辩申请";
			content += name + "，请你及时查看，并确认是否同意其参加答辩";
			if(!Verify.isEmpty(selectfirst)) {
				toId = selectfirst.getTeaId();
			}
			_assignSendMessage(name, content, toId, request);
		}
		
		return new ModelAndView("forward:/user/apply/list.do", "message", "答辩申请成功");	
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
	 * 加载毕业答辩材料
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignFinishReport(HttpServletRequest request) throws VerifyException 
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String where = "from Topicfinish where departmentId = '" + department.getDeptId() + 
				"' AND stuId = '" + userid + "' order by createTime desc ";
		
		request.setAttribute(
				"topicfinish",
				topicfinishService.getRecordByWhere(where)
				);
	}
	
	/**
	 * 加载发送消息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param name
	 * @param content
	 * @param request
	 */
	private void _assignSendMessage(String name, String content, String toId, HttpServletRequest request)
	{
		String userId 	= (String) request.getSession().getAttribute("user_id");
		try {
			Message message = new Message();
			message.setName(name);
			message.setContent(content);
			message.setToId(toId);
			message.setFromId(userId);
			short status = 1;
			message.setStatus(status);
			message.setCreateTime(HResponse.formatDateTime(new Date()));
			messageService.saveMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 数据列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 13:27:54
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap ) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/list");
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
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
		_assignStudentPassMap(request);
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/confirm.do")
	public ModelAndView confirm(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String grade 			= request.getParameter("grade");
		mav.setViewName("user/apply/confirm");
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
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
				userid + "'";
		if(!Verify.isEmpty(grade)) {
			where += " AND stuId IN (select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (select p.proId from Profession p " +
					"where p.graId = " + Integer.parseInt(grade) + " AND deptId = '" + department.getDeptId() + 
					"' ) ) ) ";
		}		
		where += " ) order by pass asc ";
		list = applyService.getAllRecordByPages(where, pagination);
		_assignGradeInfo(request);
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/finishconfirm.do")
	public ModelAndView finishConfirm(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/finish-confirm");
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
		String where = "from Apply where type = '2' AND departmentId = '" + department.getDeptId() + 
			"' AND userId IN (select stuId from Selectfirst where selStatus = '1' AND teaId = '" + 
			userid + "' ";
		if(!Verify.isEmpty(grade)) {
			where += " AND stuId IN (select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (select p.proId from Profession p " +
					"where p.graId = " + Integer.parseInt(grade) + " AND deptId = '" + department.getDeptId() + 
					"' ) ) ) ";
		}		
		where += " ) order by pass asc ";
		list = applyService.getAllRecordByPages(where, pagination);
		_assignGradeInfo(request);
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
	 * 注册学生Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-12 下午02:24:00
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap(List<Apply> data, HttpServletRequest request) throws VerifyException {
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
	private void _assignStudentRecordMap(String stuId, HttpServletRequest request)
	{
		Student student = studentService.getOneById(stuId);
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
		String userId 	= (String) request.getSession().getAttribute("user_id");
		String id 		= request.getParameter("id");
		String pass 	= request.getParameter("pass");
		String toId 	= request.getParameter("userid");
		Apply apply = applyService.getOneRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(apply)) {
			HResponse.errorJSON("记录不存在", response);
		}
		try {
			apply.setPass(pass);
			if(pass.equals("1")) {
				apply.setStatus("2");
			}
			applyService.editOneApply(apply);
			String name 	= "你的指导老师 " + userId + " 【" + request.getSession().getAttribute("user_name").toString() + "】";
			String content 	= "";
			if(pass.equals("1")) {
				name += " 不同意你参加答辩";
				content += name + "，请你完善相关的文档后，再提交答辩申请";
			}else {
				name += " 同意你参加答辩";
				content += name + "， 请你做好答辩的准备，及时查看答辩相关信息";
			}
			_assignSendMessage(name, content, toId, request);
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
		String id 			= request.getParameter("id");
		String stuid 		= request.getParameter("userid");
		_assignStudentRecordMap(request);
		if(Verify.isEmpty(stuid) || Verify.isEmpty(id)) {
			return mav;
		}
		try {
			String aplyWhere 	= "from Topicapply where id = (" +
				"select relId from LinkeddataApplyTopicapply where itemId ='" + id + "' )"; 
			String reportWhere 	= "from Topicreport where id = (" +
				"select relId from LinkeddataApplyTopicreport where itemId ='" + id + "' )"; 
			String selWhere 	= "from Selectfirst where stuId = '" + stuid + "' AND selStatus = '1' AND teaId = '" + 
			request.getSession().getAttribute("user_id") + "'";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/finishtopicword.do")
	public ModelAndView finishTopicWord(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/apply/finish-word");
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/finishreviewtopicword.do")
	public ModelAndView finishReviewTopicWord(HttpServletRequest request, ModelMap modelMap) throws VerifyException
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
		_assignStudentRecordMap(userId, request);
		if(Verify.isEmpty(topicfinish)) {
			request.setAttribute("message", "记录不存在");
			
			return mav;
		}
		request.setAttribute("record", topicfinish);
		
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
		_assignStudentRecordMap(topicfinish.getStuId(), request);
		if(Verify.isEmpty(topicfinish)) {
			request.setAttribute("message", "记录不存在");
			
			return mav;
		}
		request.setAttribute("record", topicfinish);
		
		return mav;
	}
	
	/**
	 * 指导老师是否已经评分
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/havescoreteacher.do")
	public void haveScoreTeacher(HttpServletRequest request, HttpServletResponse response)
	{
		String userId 	= request.getParameter("userid");
		if(Verify.isEmpty(userId)) {
			HResponse.errorJSON(response);
			
			return;
		}
		try {
			String where = "from Gradeone where stuId = '" + userId.trim() + "' ";
			Gradeone gradeone = gradeoneService.getRecordByWhere(where);
			if(Verify.isEmpty(gradeone)) {
				HResponse.errorJSON(response);
				
				return;
			}
			String data = "[{\"one\": \"" + gradeone.getGoOne() + "\", \"two\": \"" + gradeone.getGoTwo() + 
					"\" , \"three\": \"" + gradeone.getGoThree() + "\" , \"four\": \"" + gradeone.getGoFour() + 
					"\" , \"five\": \"" + gradeone.getGoFive()+ "\" , \"six\": \"" + gradeone.getGoSix() + 
					"\" , \"all\": \"" + gradeone.getGoAll() + "\" , \"content\": \"" + 
					gradeone.getContent() + "\" }]";
			
			HResponse.okJSON(null, data, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
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
		String userId 		= request.getParameter("userId");
		String oneScore 	= request.getParameter("one");
		String twoScore 	= request.getParameter("two");
		String threeScore 	= request.getParameter("three");
		String fourScore 	= request.getParameter("four");
		String fiveScore 	= request.getParameter("five");
		String sixScore 	= request.getParameter("six");
		String content 		= request.getParameter("content");
		if(Verify.isEmpty(userId)) {
			HResponse.errorJSON("服务器繁忙，请稍后重试", response);
			
			return;
		}
		String[] score 		= {oneScore, twoScore, threeScore, fourScore, fiveScore};
		boolean bool = false;
		boolean isMax = false;
		for(int i = 0; i < score.length; i ++) {
			if(!Verify.isScore(score[i])) {
				HResponse.errorJSON("第 " + (i + 1) + " 项的分数不正确", response);
				bool = true;
				break;
			}
			if(Float.parseFloat(score[i]) > 30) {
				HResponse.errorJSON("第 " + (i + 1) + " 项的分数不超过30分", response);
				isMax = true;
				break;
			}
		}
		if(bool || isMax) {
			return;
		}
		if(!Verify.isEmpty(sixScore)) {
			if(!Verify.isScore(sixScore)) {
				HResponse.errorJSON("第 6 项的分数不正确", response);
				
				return;
			}
			if(Float.parseFloat(sixScore) > 30) {
				HResponse.errorJSON("第 6 项的分数不超过30分", response);
				
				return;
			}
		}
		if(Verify.isEmpty(content)) {
			HResponse.errorJSON("评定意见不能为空", response);
			
			return;
		}
		try{
			Float one 		= Float.parseFloat(oneScore);
			Float two 		= Float.parseFloat(twoScore);
			Float three 	= Float.parseFloat(threeScore);
			Float four 		= Float.parseFloat(fourScore);
			Float five 		= Float.parseFloat(fiveScore);
			Float six 		= (float) 0;
			Float all 		= one + two + three + four + five;
			if(!Verify.isEmpty(sixScore)) {
				six = Float.parseFloat(sixScore);
				all = all + six;
			}
			if(all > 100) {
				HResponse.errorJSON("总分不超过100分，请检查各项分值", response);
				
				return;
			}
			String where 	= "from Gradeone where stuId = '" + userId + "' ";
			Gradeone gradeone = gradeoneService.getRecordByWhere(where);
			if(!Verify.isEmpty(gradeone)) {
				gradeone.setGoOne(one);
				gradeone.setGoTwo(two);
				gradeone.setGoThree(three);
				gradeone.setGoFour(four);
				gradeone.setGoFive(five);
				gradeone.setGoSix(six);
				gradeone.setGoAll(all);
				gradeone.setContent(content);
				gradeone.setCreateTime(HResponse.formatDateTime(new Date()));
				gradeoneService.editOneGradeone(gradeone);
				HResponse.okJSON(response);
				
				return;
			}
			gradeone = new Gradeone();
			gradeone.setStuId(userId);
			gradeone.setGoOne(one);
			gradeone.setGoTwo(two);
			gradeone.setGoThree(three);
			gradeone.setGoFour(four);
			gradeone.setGoFive(five);
			gradeone.setGoSix(six);
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
	
	/**
	 * 检查时间设置 和是否已经通过答辩
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/checktime.do")
	public void checkTime(HttpServletRequest request, HttpServletResponse response)
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String userid 	= (String) request.getSession().getAttribute("user_id");
		String mark 	= request.getParameter("mark");
		if(Verify.isEmpty(mark)) {
			HResponse.errorJSON("服务器繁忙，请稍后重试", response);
			return;
		}
		String graid = "from Profession where deptId = '" + department.getDeptId() + 
				"' AND proId = (select c.proId from Tbclass c where c.claId = (" +
				"select s.claId from Student s where s.stuId = '" + userid + "')) ";
		String where = "from Settime where deptId = '" + department.getDeptId() + 
				"' AND mark = '" + mark.trim() + "' AND '" + HResponse.formatDateTime(new Date()) + 
				"' BETWEEN startTime AND endTime ";
		try {
			Profession profession = professionService.getRecordByWhere(graid);
			if(Verify.isEmpty(profession) ||(!Verify.isEmpty(profession) && Verify.isEmpty(profession.getGraId()))) {
				HResponse.errorJSON("服务器繁忙，请稍后重试", response);
				return;
			}
			where += " AND graNumber = '" + (profession.getGraId() + "") + "' ";
			Settime settime 	= settimeService.getOneByWhere(where);
			if(Verify.isEmpty(settime)) {
				HResponse.errorJSON("时间暂未开放", response);
				return;
			}
			if(mark.trim().equals("5")) {
				if(_assignIsPassOpen(request)) {
					//如果已经通过开题答辩
					HResponse.errorJSON("你已经通过开题答辩了，不需要再申请开题答辩", response);
					return;
				}				
			}
			if(mark.trim().equals("6")) {
				if(_assignIsPassFinish(request)) {
					//如果已经通过毕业答辩
					HResponse.errorJSON("你已经通过毕业答辩了，不需要再申请毕业答辩", response);
					return;
				}				
			}
			
			HResponse.okJSON(response);
		} catch (Exception e) {
			e.printStackTrace();
			HResponse.errorJSON("服务器繁忙，请稍后重试", response);
		}
	}
	
	/**
	 * 是否通过开题答辩
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private boolean _assignIsPassOpen(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String where = "from Opentopicscore where departmentId = '" + department.getDeptId() + 
				"' AND studentId = '" + userid + "' order by createTime desc ";
		Opentopicscore opentopicscore = opentopicscoreService.getRecordByWhere(where);
		if(!Verify.isEmpty(opentopicscore) && opentopicscore.getScore() > 60) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 是否已经通过毕业答辩 
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @return
	 * @throws VerifyException
	 */
	private boolean _assignIsPassFinish(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String where = "from Gradeall where departmentId = '" + department.getDeptId() + 
				"' AND stuId = '" + userid + "' AND status = '1' order by createTime desc ";
		Gradeall gradeall = gradeallService.getRecordByWhere(where);
		if(!Verify.isEmpty(gradeall) && gradeall.getGaGrade() > 60) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 检测学生所选课题的类型：1 论文，2 设计，3 其他
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/topictype.do")
	public void checkTopicType(HttpServletRequest request, HttpServletResponse response)
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String stuId 	= request.getParameter("stuid");
		if(Verify.isEmpty(stuId)) {
			HResponse.errorJSON("学生ID不能为空", response);
			
			return; 	
		}
		String where = "from Tbtopic where topId = (" +
				"select s.tbtopic.topId from Selectfirst s where s.stuId = '" + stuId.trim() + 
				"' AND s.deptId = '" + department.getDeptId() + "')";
		try {
			Tbtopic tbtopic = tbtopicService.getRecordByWhere(where);
			if(Verify.isEmpty(tbtopic) || Verify.isEmpty(tbtopic.getTopType())) {
				HResponse.errorJSON("该学生所选课题出现异常，请与管理员联系", response);
				
				return;
			}
			String html = "";
			if(tbtopic.getTopType().trim().equals("1")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td>" +
						"<td>分值</td><td>评分</td></tr><tr><td>学习态度与工作量</td><td>①学习态度认真，自觉遵守纪律；" +
						"②工作作风严谨务实，具有良好的团队精神；③工作量饱满，按期圆满完成规定的任务。</td><td>10</td><td>" +
						"<input type='text' name='one' class='input-mini'/></td></tr><tr><td>文献综述与外文翻译" +
						"</td><td>①能独立查阅文献和从事其他形式调研；②能较好理解课题任务并提出实施方案；" +
						"③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；④文献综述撰写规范，外文翻译符合规定要求，译文准确，质量好；" +
						"⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。</td><td>20</td><td><input type='text' " +
						"name='two' class='input-mini'/></td></tr><tr><td>研究水平与实际能力</td><td>①能独立开展研究工作；" +
						"②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；③实验设计合理，实验数据准确可靠，" +
						"理论分析与计算正确；④有较强的实际动手能力、经济分析能力和现代技术应用能力。</td><td>30</td><td>" +
						"<input type='text' name='three' class='input-mini'/></td></tr><tr><td>论文撰写质量</td>" +
						"<td>①论文结构严谨，层次清晰，结论正确，技术用语准确；②行文流畅，语句通畅；③论文格式符合规范要求；④图表完备、整洁，" +
						"符号统一，编号齐全。</td><td>30</td><td><input type='text' name='four' class='input-mini'/>" +
						"</td></tr><tr><td>学术水平与创新</td><td>①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有较深刻的" +
						"认识，有新的见解，有一定的创新。</td><td>10</td><td><input type='text' name='five' " +
						"class='input-mini'/></td></tr><tr><td colspan='4'><div class='rfloat'>总分：" +
						"<input type='text' name='all' class='input-mini'/></div></td></tr><tr><td colspan='4'>" +
						"<div class='lfloat' style='width: 100%;'><label>指导教师评定意见：</label><textarea rows='10' " +
						"name='content' style='width: 100%;'></textarea></div></td></tr></table>";
			}else if(tbtopic.getTopType().trim().equals("2")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>学习态度与工作量</td><td>①学习态度认真，自觉遵守纪律；②工作作风严谨务实，具有良好的团队精神；" +
						"③工作量饱满，按期圆满完成规定的任务。</td><td>10</td><td><input type='text' name='one' " +
						"class='input-mini'/></td></tr><tr><td>文献综述与外文翻译</td><td>①能独立查阅文献和从事其他形式调研；" +
						"②能较好理解课题任务并提出实施方案；③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；④文献综述撰写规范，" +
						"外文翻译符合规定要求，译文准确，质量好；⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。</td><td>20</td>" +
						"<td><input type='text' name='two' class='input-mini'/></td></tr><tr><td>研究水平与实际能力" +
						"</td><td>①能独立开展研究工作；②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题" +
						"；③论点正确、鲜明，阐述清楚，对研究的问题有较强的分析和概括能力，有一定深度；④论据充分，材料翔实可靠，说服力强。</td>" +
						"<td>30</td><td><input type='text' name='three' class='input-mini'/></td></tr><tr>" +
						"<td>论文撰写质量</td><td>①论文结构严谨，逻辑性强，论述层次清晰；②语句通畅，语言准确、生动；③论文格式符合规范要求；" +
						"④图表完备、整洁，编号齐全。</td><td>30</td><td><input type='text' name='four' " +
						"class='input-mini'/></td></tr><tr><td>学术水平与创新</td><td>①具有一定的学术水平或应用价值；" +
						"②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td><td>10</td><td>" +
						"<input type='text' name='five' class='input-mini'/></td></tr><tr><td colspan='4'>" +
						"<div class='rfloat'>总分：<input type='text' name='all' class='input-mini'/></div>" +
						"</td></tr><tr><td colspan='4'><div class='lfloat' style='width: 100%;'><label>" +
						"指导教师评定意见：</label><textarea rows='10' name='content' style='width: 100%;'>" +
						"</textarea></div></td></tr></table>";
			}else if(tbtopic.getTopType().trim().equals("3")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>学习态度与工作量</td><td>①学习态度认真，自觉遵守纪律；②工作作风严谨务实，具有良好的团队精神；" +
						"③工作量饱满，按期圆满完成规定的任务。</td><td>10</td><td><input type='text' name='one' " +
						"class='input-mini'/></td></tr><tr><td>文献综述与外文翻译</td><td>①能独立查阅文献和从事其他形式调研；" +
						"②能较好理解课题任务并提出实施方案；③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；④文献综述撰写规范，" +
						"外文翻译符合规定要求，译文准确，质量好；⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。</td><td>20</td>" +
						"<td><input type='text' name='two' class='input-mini'/></td></tr><tr><td>设计水平与实际能力" +
						"</td><td>①能独立开展设计工作；②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；" +
						"③设计方案合理可行，数据准确可靠，论证充分，理论分析与计算正确；④有较强的实际动手能力、经济分析能力和现代技术应用能力。" +
						"</td><td>20</td><td><input type='text' name='three' class='input-mini'/></td></tr>" +
						"<tr><td>设计说明书撰写质量</td><td>①结构严谨，层次清晰，结论正确，技术用语准确；②行文流畅，语句通畅；" +
						"③格式符合规范要求；④图表完备、整洁，符号统一，编号齐全。</td><td>20</td><td>" +
						"<input type='text' name='four' class='input-mini'/></td></tr><tr><td>图纸质量</td>" +
						"<td>①结构合理，工艺可行；②图样绘制与技术要求符合国家标准；③图面质量及工作量符合要求。</td><td>20</td><td>" +
						"<input type='text' name='five' class='input-mini'/></td></tr><tr><td>学术水平与创新</td>" +
						"<td>①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td>" +
						"<td>10</td><td><input type='text' name='six' class='input-mini'/></td></tr><tr>" +
						"<td colspan='4'><div class='rfloat'>总分：<input type='text' name='all' " +
						"class='input-mini'/></div></td></tr><tr><td colspan='4'><div class='lfloat' " +
						"style='width: 100%;'><label>指导教师评定意见：</label><textarea rows='10' name='content' " +
						"style='width: 100%;'></textarea></div></td></tr></table>";
			}
			HResponse.write("{\"rs\": true, \"html\": \"" + html + "\"}", response);
		} catch (Exception e) {
			e.printStackTrace();
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
}
