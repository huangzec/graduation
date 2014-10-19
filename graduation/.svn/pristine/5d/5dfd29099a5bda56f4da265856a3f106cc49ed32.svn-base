package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.mvc.common.Verify;
import com.mvc.entity.Apply;
import com.mvc.entity.Department;
import com.mvc.entity.LinkeddataApplyTopicinfo;
import com.mvc.entity.LinkeddataApplyTopicreport;
import com.mvc.entity.LinkeddataApplyTopicscore;
import com.mvc.entity.Meeting;
import com.mvc.entity.Opentopicinfo;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Student;
import com.mvc.entity.Topicreport;
import com.mvc.service.ApplyService;
import com.mvc.service.LinkeddataApplyTopicinfoService;
import com.mvc.service.LinkeddataApplyTopicreportService;
import com.mvc.service.LinkeddataApplyTopicscoreService;
import com.mvc.service.MeetingService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.StudentService;
import com.mvc.service.TopicreportService;

/**
 * 开题答辩控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/opentopicinfo")
public class OpentopicinfoSiteController {

	@Autowired
	private OpentopicinfoService opentopicinfoService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private OpentopicscoreService opentopicscoreService;
	
	@Autowired
	private TopicreportService topicreportService;
	
	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private LinkeddataApplyTopicinfoService linkeddataApplyTopicinfoService;
	
	@Autowired
	private LinkeddataApplyTopicscoreService linkeddataApplyTopicscoreService;
	
	@Autowired
	private LinkeddataApplyTopicreportService linkeddataApplyTopicreportService;
	
	@Autowired
	private ApplyService applyService;
	
	private Pagination pagination;
	private List<Opentopicinfo> list = new ArrayList<Opentopicinfo>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Opentopicinfo> getList() {
		return list;
	}

	public void setList(List<Opentopicinfo> list) {
		this.list = list;
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
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/opentopicinfo/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}

	/**
	 * 数据列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/Opentopicinfo/list");
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
		String where = "from Opentopicinfo where 1 = 1 ";
		list = opentopicinfoService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Opentopicinfo>) opentopicinfoService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", list);
		modelMap.put("pagination", pagination);
		
		return mav;
	}
	
	/**
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
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
	 * @date 2014-09-05 17:27:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/opentopicinfo/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-05 17:27:13
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
	 * 录入开题答辩成绩学生列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-29 下午08:35:36
	 * @return ModelAndView
	 */
	@RequestMapping(value="/enteroptscore.do")
	public ModelAndView enterOptScore(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/enterscore-list");
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Opentopicinfo where departmentId = '" + 
			department.getDeptId() + "' AND headerman = '" + 
			request.getSession().getAttribute("user_id") + "' AND status = '1'";
		request.setAttribute(
				"list", 
				opentopicinfoService.getAllRowsByWhere(where)
				);
		_assignStudentListMap(opentopicinfoService.getAllRowsByWhere(where), request);
		
		return mav;
	}
	
	/**
	 * 加载学生列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 */
	private void _assignStudentListMap(List<Opentopicinfo> data, HttpServletRequest request) {
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
	 * 录入成绩和相关内容
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-30 下午05:40:28
	 * @return void
	 */
	@RequestMapping(value="/enterscorecontent.do")
	public void enterScoreContent(HttpServletRequest request, HttpServletResponse response)
	{
		String opid 	= request.getParameter("opid");
		String where 	= "from LinkeddataApplyTopicinfo where relId = '" + opid + "' ";
		LinkeddataApplyTopicinfo linkeddataApplyTopicinfo = linkeddataApplyTopicinfoService.getRecordByWhere(where);
		if(Verify.isEmpty(linkeddataApplyTopicinfo)) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
			
			return;
		}
		Apply apply 	= applyService.getOneRecordById(Integer.parseInt(linkeddataApplyTopicinfo.getItemId()));
		if(Verify.isEmpty(apply)) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
						
			return;
		}
		request.setAttribute("applyid", apply.getId() + "");
		try {
			_assignEnterScore(request);
			_assignLinkeddataApplyTopicscore(request);
			if(!_assignMeetingOpentopicReport(request)) {
				HResponse.errorJSON(request.getAttribute("message").toString(), response);
			}

			HResponse.okJSON("成功完成录入成绩", response);
		} catch (Exception e) {
			e.getMessage();
			
			HResponse.errorJSON(e.getMessage(), response);
		}
	}
	
	/**
	 * 录入成绩
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-30 下午05:58:48
	 * @return void
	 */
	private void _assignEnterScore(HttpServletRequest request) throws Exception
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String studentId 		= request.getParameter("sid");
		String score  			= request.getParameter("score");
		Opentopicscore ops 		= new Opentopicscore();
		ops.setStudentId(studentId);
		ops.setDepartmentId(department.getDeptId());
		ops.setScore(Double.parseDouble(score));
		ops.setCreateTime(HResponse.formatDateTime(new Date()));
		request.setAttribute("topicscoreid", opentopicscoreService.addOneReturn(ops) + "");
	}
	
	/**
	 * 加载答辩申请与答辩申请成绩的关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	private void _assignLinkeddataApplyTopicscore(HttpServletRequest request)
	{
		LinkeddataApplyTopicscore linkeddataApplyTopicscore = new LinkeddataApplyTopicscore();
		linkeddataApplyTopicscore.setItemId(request.getAttribute("applyid").toString());
		linkeddataApplyTopicscore.setRelId(request.getAttribute("topicscoreid").toString());
		linkeddataApplyTopicscore.setExtend("0");
		linkeddataApplyTopicscore.setCreateTime(HResponse.formatDateTime(new Date()));
		linkeddataApplyTopicscoreService.addOne(linkeddataApplyTopicscore);
	}
	
	/**
	 * 完善会议信息、开题报告书内容
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-30 下午06:33:42
	 * @return void
	 */
	private boolean _assignMeetingOpentopicReport(HttpServletRequest request)
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String studentId 		= request.getParameter("sid");
		String where 		= "from LinkeddataApplyTopicreport where itemId = '" + request.getAttribute("applyid") + "' ";
		LinkeddataApplyTopicreport linkeddataApplyTopicreport = linkeddataApplyTopicreportService.getRecordByWhere(where);
		if(Verify.isEmpty(linkeddataApplyTopicreport) || Verify.isEmpty(linkeddataApplyTopicreport.getRelId())) {
			request.setAttribute("message", "开题报告书不存在");
			
			return false;
		}
		Topicreport tpreport = topicreportService.getOneRecordById(Integer.parseInt(linkeddataApplyTopicreport.getRelId()));
		if(Verify.isEmpty(tpreport)) {
			request.setAttribute("message", "开题报告书不存在");
			
			return false;
		}
		tpreport.setJudgeView(request.getParameter("judgeview"));
		Meeting meeting = new Meeting();
		meeting.setName("学生 " + tpreport.getStuId() + " 的开题答辩会议记录");
		meeting.setParentId(tpreport.getId().toString());
		meeting.setStartTime(request.getParameter("opdate"));
		meeting.setPlace(request.getParameter("opplace"));
		meeting.setPerson(request.getParameter("opjudge"));
		meeting.setContent(request.getParameter("mcont"));
		meeting.setHoster(request.getParameter("hoster"));
		meeting.setRecorder(request.getParameter("recorder"));
		try {
			tpreport.setMeeting(meetingService.addOneReturn(meeting) + "");
			topicreportService.editOneTopicreport(tpreport);
			Opentopicinfo opi = opentopicinfoService.getOneRecordById(Integer.parseInt(request.getParameter("opid")));
			if(Verify.isEmpty(opi)) {
				request.setAttribute("message", "服务器繁忙，请稍后再试");
				
				return false;
			}
			opi.setStatus("2");
			opentopicinfoService.editOneOpentopicinfo(opi);
			
			return true;
		} catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
			
			return false;
		}
	}
	
}
