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
import com.mvc.entity.Department;
import com.mvc.entity.Gradeall;
import com.mvc.entity.Gradeone;
import com.mvc.entity.Gradethree;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.LinkeddataApplyGradeall;
import com.mvc.entity.LinkeddataApplyGradethree;
import com.mvc.entity.LinkeddataApplyGraduateinfo;
import com.mvc.entity.Meeting;
import com.mvc.entity.Student;
import com.mvc.service.GradeallService;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradethreeService;
import com.mvc.service.GradetwoService;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.LinkeddataApplyGradeallService;
import com.mvc.service.LinkeddataApplyGradethreeService;
import com.mvc.service.LinkeddataApplyGraduateinfoService;
import com.mvc.service.MeetingService;
import com.mvc.service.StudentService;

/**
 * 毕业答辩控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/graduateinfo")
public class GraduateinfoSiteController {

	@Autowired
	private GraduateinfoService graduateinfoService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private GradeoneService gradeoneService;
	
	@Autowired
	private GradetwoService gradetwoService;
	
	@Autowired
	private GradethreeService gradethreeService;
	
	@Autowired
	private GradeallService gradeallService;
	
	@Autowired
	private LinkeddataApplyGraduateinfoService linkeddataApplyGraduateinfoService;
	
	@Autowired
	private LinkeddataApplyGradethreeService linkeddataApplyGradethreeService;
	
	@Autowired
	private LinkeddataApplyGradeallService linkeddataApplyGradeallService;
	
	private Pagination pagination;
	private List<Graduateinfo> list = new ArrayList<Graduateinfo>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Graduateinfo> getList() {
		return list;
	}

	public void setList(List<Graduateinfo> list) {
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
	 * @date 2014-10-02 16:41:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/graduateinfo/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 16:41:13
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
	 * @date 2014-10-02 16:41:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/graduateinfo/list");
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
		String where = "from Graduateinfo where 1 = 1 ";
		list = graduateinfoService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Graduateinfo>) graduateinfoService.getAllRecordByPages(where, pagination);
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
	 * @date 2014-10-02 16:41:13
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
	 * @date 2014-10-02 16:41:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/graduateinfo/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-02 16:41:13
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
	 * 录入毕业答辩成绩列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-2 下午04:47:36
	 * @return ModelAndView
	 */
	@RequestMapping(value="/enterscorelist.do")
	public ModelAndView enterScoreList(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/enterscore-graduate-list");
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Graduateinfo where departmentId = '" + 
			department.getDeptId() + "' AND headerman = '" + 
			request.getSession().getAttribute("user_id") + "' AND status = '1'";
		request.setAttribute(
				"list", 
				graduateinfoService.getAllRowsByWhere(where)
				);
		_assignStudentListMap(graduateinfoService.getAllRowsByWhere(where), request);
		
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
	private void _assignStudentListMap(List<Graduateinfo> data, HttpServletRequest request) {
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
	 * 录入毕业答辩成绩及相关内容
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午05:18:43
	 * @return void
	 */
	@RequestMapping(value="/enterscorecontent.do")
	public void enterScoreContent(HttpServletRequest request, HttpServletResponse response)
	{
		String id 		= request.getParameter("id");
		String where 	= "from LinkeddataApplyGraduateinfo where relId = '" + id + "' ";
		try {
			LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo = linkeddataApplyGraduateinfoService.getRecordByWhere(where);
			int applyId 		= Integer.parseInt(linkeddataApplyGraduateinfo.getItemId());			
			int meetingRecord 	= _assignMeetingInfo(request);
			int gradeThreeId 	= _assignGradethree(request);
			request.setAttribute("applyid", applyId);
			request.setAttribute("gradethreeid", gradeThreeId);
			_assignLinkeddataApplyGradethree(request);
			request.setAttribute("meetingid", meetingRecord);
			_assignGradeTotal(request);
			_assignGraduateinfo(request);			
									
			HResponse.okJSON("成功录入学生" + request.getParameter("stu") + "的成绩", response);
		} catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}

	/**
	 * 完善毕业答辩会议信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午07:32:29
	 * @return int
	 */
	private int _assignMeetingInfo(HttpServletRequest request) {
		Meeting meeting = new Meeting();
		meeting.setName("学生 " + request.getParameter("stu") + " 的毕业答辩会议记录");
		meeting.setParentId("0");
		meeting.setStartTime(request.getParameter("sttime"));
		meeting.setPlace(request.getParameter("place"));
		meeting.setPerson(request.getParameter("person"));
		meeting.setContent(request.getParameter("content"));
		meeting.setHoster(request.getParameter("hoster"));
		meeting.setRecorder(request.getParameter("recorder"));
		
		return meetingService.addRecord(meeting);
	}

	/**
	 * 修改毕业答辩会议信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param meetingRecord 
	 * @date 2014-10-3 下午08:17:24
	 * @return boolean
	 */
	private void _assignGraduateinfo(HttpServletRequest request) {
		Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(request.getParameter("id")));
		graduateinfo.setContent(request.getAttribute("meetingid").toString());
		graduateinfo.setStatus("2");
		graduateinfoService.editOneGraduateinfo(graduateinfo);
	}

	/**
	 * 录入毕业答辩成绩（表三）
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午08:31:13
	 * @return boolean
	 */
	private int _assignGradethree(HttpServletRequest request) {
		Gradethree gradethree = new Gradethree();
		Float one 	= Float.parseFloat(request.getParameter("one"));
		Float two 	= Float.parseFloat(request.getParameter("two"));
		Float three	= Float.parseFloat(request.getParameter("three"));
		Float four 	= Float.parseFloat(request.getParameter("four"));
		Float five 	= Float.parseFloat(request.getParameter("five"));
		Float all 	= one + two + three + four + five;
		gradethree.setStuId(request.getParameter("sid"));
		gradethree.setGtrOne(one);
		gradethree.setGtrTwo(two);
		gradethree.setGtrThree(three);
		gradethree.setGtrFour(four);
		gradethree.setGtrFive(five);
		gradethree.setGtrAll(all);
		gradethree.setContent(request.getParameter("judge-view"));
		gradethree.setStatus("1");
		gradethree.setCreateTime(HResponse.formatDateTime(new Date()));
		
		return gradethreeService.addOneReturn(gradethree);
	}
	
	/**
	 * 加载答辩申请与成绩表三的关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	private void _assignLinkeddataApplyGradethree(HttpServletRequest request)
	{
		LinkeddataApplyGradethree linkeddataApplyGradethree = new LinkeddataApplyGradethree();
		linkeddataApplyGradethree.setItemId(request.getAttribute("applyid").toString());
		linkeddataApplyGradethree.setRelId(request.getAttribute("gradethreeid").toString());
		linkeddataApplyGradethree.setExtend("0");
		linkeddataApplyGradethree.setCreateTime(HResponse.formatDateTime(new Date()));
		linkeddataApplyGradethreeService.addOne(linkeddataApplyGradethree);
	}

	/**
	 * 加载毕业答辩总成绩
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午10:01:41
	 * @return boolean
	 */
	private void _assignGradeTotal(HttpServletRequest request) {
		Department department = (Department) request.getSession().getAttribute("dept");
		String teaWhere = "from Gradeone where stuId = '" + request.getParameter("sid") + 
			"' AND status = '1' order by createTime desc";
		String judWhere = "from Gradetwo where stuId = '" + request.getParameter("sid") + 
			"' AND status = '1' order by createTime desc";
		String gradeThreeId		= request.getAttribute("gradethreeid").toString();
		Gradeone gradeone 		= gradeoneService.getRecordByWhere(teaWhere);
		Gradetwo gradetwo 		= gradetwoService.getRecordByWhere(judWhere);
		Gradethree gradethree 	= gradethreeService.getOneRecordById(Integer.parseInt(gradeThreeId));
		/**
		 * 毕业论文（设计）最终评定成绩由系（部）学位委员会根据指导教师评定成绩（30％），
		 * 评阅教师评定成绩（30%）和答辩成绩（40％）综合确定。
		 */
		Float one 	= gradeone.getGoAll();
		Float two 	= gradetwo.getGtAll();
		Float three = gradethree.getGtrAll();
		Float total = one * 0.3f + two * 0.3f + three * 0.4f;
		Gradeall gradeall = new Gradeall();
		gradeall.setStuId(request.getParameter("sid"));
		gradeall.setDepartmentId(department.getDeptId());
		gradeall.setGaGrade(total);
		gradeall.setStatus("1");
		gradeall.setCreateTime(HResponse.formatDateTime(new Date()));
		request.setAttribute("gradeallid", gradeallService.addOneReturn(gradeall) + "");
		_assignLinkeddataApplyGradeall(request);
	}

	/**
	 * 加载答辩申请与答辩总成绩表关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	private void _assignLinkeddataApplyGradeall(HttpServletRequest request) {
		LinkeddataApplyGradeall linkeddataApplyGradeall = new LinkeddataApplyGradeall();
		linkeddataApplyGradeall.setItemId(request.getAttribute("applyid").toString());
		linkeddataApplyGradeall.setRelId(request.getAttribute("gradeallid").toString());
		linkeddataApplyGradeall.setExtend("0");
		linkeddataApplyGradeall.setCreateTime(HResponse.formatDateTime(new Date()));
		linkeddataApplyGradeallService.addOne(linkeddataApplyGradeall);
	}

}