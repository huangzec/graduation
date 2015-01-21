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
import com.mvc.common.Path;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeall;
import com.mvc.entity.Gradeone;
import com.mvc.entity.Gradethree;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.LinkeddataApplyGradeall;
import com.mvc.entity.LinkeddataApplyGradethree;
import com.mvc.entity.LinkeddataApplyGraduateinfo;
import com.mvc.entity.LinkeddataMeetingGraduateinfo;
import com.mvc.entity.Meeting;
import com.mvc.entity.Opentopicinfo;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Tbtopic;
import com.mvc.exception.VerifyException;
import com.mvc.service.GradeallService;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradethreeService;
import com.mvc.service.GradetwoService;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.LinkeddataApplyGradeallService;
import com.mvc.service.LinkeddataApplyGradethreeService;
import com.mvc.service.LinkeddataApplyGraduateinfoService;
import com.mvc.service.LinkeddataMeetingGraduateinfoService;
import com.mvc.service.MeetingService;
import com.mvc.service.StudentService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TbtopicService;

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
	
	@Autowired
	private LinkeddataMeetingGraduateinfoService linkeddataMeetingGraduateinfoService;
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	@Autowired
	private TbgradeService tbgradeService;
	
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap ) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/graduateinfo/list");
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/enterscorelist.do")
	public ModelAndView enterScoreList(HttpServletRequest request, ModelMap modelMap) throws VerifyException
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
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap(List<Graduateinfo> data, HttpServletRequest request) throws VerifyException {
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
		String userId 	= request.getParameter("sid");
		if(Verify.isEmpty(id) || Verify.isEmpty(userId)) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
			
			return;
		}
		if(this._verifyScoreDate(request)) {//数据格式有误
			HResponse.errorJSON(request.getAttribute("message").toString(), response);
			
			return ;
		}
		String where 	= "from LinkeddataMeetingGraduateinfo where relId = '" + id + "' ";
		String gtWhere 	= "from Gradethree where stuId = '" + userId + "' order by createTime desc ";
		try {
			Gradethree gradethree = gradethreeService.getRecordByWhere(gtWhere);
			if(!Verify.isEmpty(gradethree)) {
				String metWhere = "from Meeting where id = (" +
						"select itemId from LinkeddataMeetingGraduateinfo where relId = '" + id + "') " ;
				Meeting meeting = meetingService.getRecordByWhere(metWhere);
				if(Verify.isEmpty(meeting)) {//如果会议为空，则新增
					int meetingId = _assignMeetingInfo(request);
					request.setAttribute("meetingId", meetingId + "");
					_assignLinkeddataMeetingGraduateinfo(request);
				}else {//否则，则修改会议信息
					meeting.setName("学生 " + request.getParameter("stu") + " 的毕业答辩会议记录");
					meeting.setParentId(request.getParameter("id").toString());
					meeting.setStartTime(request.getParameter("sttime").toString());
					meeting.setPlace(request.getParameter("place").toString());
					meeting.setPerson(request.getParameter("person").toString());
					meeting.setContent(request.getParameter("content").toString());
					meeting.setHoster(request.getParameter("hoster").toString());
					meeting.setRecorder(request.getParameter("recorder").toString());
					
					meetingService.editOneMeeting(meeting);
				}
			}else {
				int meetingId = _assignMeetingInfo(request);
				request.setAttribute("meetingId", meetingId + "");
				_assignLinkeddataMeetingGraduateinfo(request);
			}
			_assignGradethree(gradethree, request);
			_assignGradeTotal(request);
			_assignGraduateinfo(request);
									
			HResponse.okJSON("成功录入学生" + request.getParameter("stu") + "的成绩", response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}

	/**
	 * 加载会议记录与毕业答辩信息表的关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignLinkeddataMeetingGraduateinfo(HttpServletRequest request) throws VerifyException 
	{
		LinkeddataMeetingGraduateinfo linkeddataMeetingGraduateinfo = new LinkeddataMeetingGraduateinfo();
		linkeddataMeetingGraduateinfo.setItemId(request.getAttribute("meetingId").toString());
		linkeddataMeetingGraduateinfo.setRelId(request.getParameter("id").toString());
		linkeddataMeetingGraduateinfo.setExtend("0");
		linkeddataMeetingGraduateinfo.setCreateTime(HResponse.formatDateTime(new Date()));
		
		linkeddataMeetingGraduateinfoService.addOne(linkeddataMeetingGraduateinfo);
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
		meeting.setParentId(request.getParameter("id").toString());
		meeting.setStartTime(request.getParameter("sttime").toString());
		meeting.setPlace(request.getParameter("place").toString());
		meeting.setPerson(request.getParameter("person").toString());
		meeting.setContent(request.getParameter("content").toString());
		meeting.setHoster(request.getParameter("hoster").toString());
		meeting.setRecorder(request.getParameter("recorder").toString());
		
		return meetingService.addRecord(meeting);
	}

	/**
	 * 修改毕业答辩会议信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param meetingRecord 
	 * @date 2014-10-3 下午08:17:24
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignGraduateinfo(HttpServletRequest request) throws VerifyException {
		String content 		= request.getParameter("judgeview");
		Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(request.getParameter("id")));
		graduateinfo.setContent(content);
		graduateinfo.setStatus("2");
		
		graduateinfoService.editOneGraduateinfo(graduateinfo);
	}

	/**
	 * 录入毕业答辩成绩（表三）
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-3 下午08:31:13
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignGradethree(Gradethree gradethree, HttpServletRequest request) throws VerifyException {
		String oneScore 	= request.getParameter("one");
		String twoScore 	= request.getParameter("two");
		String threeScore	= request.getParameter("three");
		String fourScore 	= request.getParameter("four");
		String fiveScore 	= request.getParameter("five");
		String sixScore 	= request.getParameter("six");
		boolean  bool = false;
		if(Verify.isEmpty(gradethree)) {//已有成绩，就修改，否则，就新增
			gradethree = new Gradethree();
			bool = true;
		}
		Float one 	= Float.parseFloat(oneScore);
		Float two 	= Float.parseFloat(twoScore);
		Float three	= Float.parseFloat(threeScore);
		Float four 	= Float.parseFloat(fourScore);
		Float five 	= Float.parseFloat(fiveScore);
		Float all 	= one + two + three + four + five;
		Float six 		= (float) 0;
		if(!Verify.isEmpty(sixScore)) {
			six = Float.parseFloat(sixScore);
			all = all + six;
		}
		gradethree.setStuId(request.getParameter("sid"));
		gradethree.setGtrOne(one);
		gradethree.setGtrTwo(two);
		gradethree.setGtrThree(three);
		gradethree.setGtrFour(four);
		gradethree.setGtrFive(five);
		gradethree.setGtrSix(six);
		gradethree.setGtrAll(all);
		gradethree.setContent(request.getParameter("judgeview"));
		gradethree.setStatus("1");
		gradethree.setCreateTime(HResponse.formatDateTime(new Date()));
		if(bool) {
			gradethreeService.addOne(gradethree);
		}else {
			gradethreeService.editOneGradethree(gradethree);
		}
	}
	
	/**
	 * 验证分数
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @return
	 */
	private boolean _verifyScoreDate(HttpServletRequest request)
	{
		try {
			String oneScore 	= request.getParameter("one");
			String twoScore 	= request.getParameter("two");
			String threeScore	= request.getParameter("three");
			String fourScore 	= request.getParameter("four");
			String fiveScore 	= request.getParameter("five");
			String sixScore 	= request.getParameter("six");
			String content 		= request.getParameter("judgeview");
			String[] score 		= {oneScore, twoScore, threeScore, fourScore, fiveScore};
			boolean bool = false;
			for(int i = 0; i < score.length; i ++) { 
				if(!Verify.isScore(score[i])) {
					request.setAttribute("message", "第 " + (i + 1) + " 项的分数不正确");
					bool = true;
					break;
				}
				if(Float.parseFloat(score[i]) > 30) {
					request.setAttribute("message", "第 " + (i + 1) + " 项的分数不能超过最高分30分！");
					bool = true;
					break;
				}
			}
			if(bool) {
				return true;
			}
			if(!Verify.isEmpty(sixScore)) {
				if(!Verify.isScore(sixScore)) {
					request.setAttribute("message", "第 6 项的分数不正确");
					
					return true;
				}
				if(Float.parseFloat(sixScore) > 30) {
					request.setAttribute("message", "第 6 项的分数不能超过最高分30分！");
					return true;
				}
			}
			if(Verify.isEmpty(content)) {
				request.setAttribute("message", "答辩小组意见不能为空");
				
				return true;
			}
			
			return false;			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "分值格式错误，请检查各项分值");
			
			return true;
		}
	}
	
	/**
	 * 加载答辩申请与成绩表三的关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignLinkeddataApplyGradethree(HttpServletRequest request) throws VerifyException
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
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignGradeTotal(HttpServletRequest request) throws VerifyException {
		Department department = (Department) request.getSession().getAttribute("dept");
		String userId 	= request.getParameter("sid");
		String teaWhere = "from Gradeone where stuId = '" + userId + 
			"' AND status = '1' order by createTime desc";
		String judWhere = "from Gradetwo where stuId = '" + userId + 
			"' AND status = '1' order by createTime desc";
		String endWhere = "from Gradethree where stuId = '" + userId + 
				"' AND status = '1' order by createTime desc";
		boolean bool = false;
		Gradeone gradeone 		= gradeoneService.getRecordByWhere(teaWhere);
		Gradetwo gradetwo 		= gradetwoService.getRecordByWhere(judWhere);
		Gradethree gradethree 	= gradethreeService.getRecordByWhere(endWhere);
		/**
		 * 毕业论文（设计）最终评定成绩由系（部）学位委员会根据指导教师评定成绩（30％），
		 * 评阅教师评定成绩（30%）和答辩成绩（40％）综合确定。
		 */
		Float one 	= gradeone.getGoAll();
		Float two 	= gradetwo.getGtAll();
		Float three = gradethree.getGtrAll();
		Float total = one * 0.3f + two * 0.3f + three * 0.4f;
		String allWhere = "from Gradeall where departmentId = '" + 
				department.getDeptId() + "' AND stuId = '" + userId + "' order by createTime desc ";
		Gradeall gradeall = gradeallService.getRecordByWhere(allWhere);
		if(Verify.isEmpty(gradeall)) {
			gradeall = new Gradeall();
			bool = true;
		}
		gradeall.setStuId(request.getParameter("sid"));
		gradeall.setDepartmentId(department.getDeptId());
		gradeall.setGaGrade(total);
		gradeall.setStatus("1");
		gradeall.setCreateTime(HResponse.formatDateTime(new Date()));
		if(bool) {
			gradeallService.addOne(gradeall);
		}else {
			gradeallService.editOneGradeall(gradeall);
		}
	}

	/**
	 * 加载答辩申请与答辩总成绩表关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignLinkeddataApplyGradeall(HttpServletRequest request) throws VerifyException {
		LinkeddataApplyGradeall linkeddataApplyGradeall = new LinkeddataApplyGradeall();
		linkeddataApplyGradeall.setItemId(request.getAttribute("applyid").toString());
		linkeddataApplyGradeall.setRelId(request.getAttribute("gradeallid").toString());
		linkeddataApplyGradeall.setExtend("0");
		linkeddataApplyGradeall.setCreateTime(HResponse.formatDateTime(new Date()));
		linkeddataApplyGradeallService.addOne(linkeddataApplyGradeall);
	}
	
	/**
	 * 毕业答辩不通过
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/graduateunpass.do")
	public void graduateUnpass(HttpServletRequest request, HttpServletResponse response)
	{
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			HResponse.errorJSON(response);
			
			return;
		}
		try {
			Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(id));
			if(Verify.isEmpty(graduateinfo)) {
				HResponse.errorJSON(response);
				
				return;
			}
			graduateinfo.setStatus("2");
			graduateinfoService.editOneGraduateinfo(graduateinfo);
			
			HResponse.okJSON(response);
		} catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
			e.printStackTrace();
		}
	}

	/**
	 * 是否已经给了毕业答辩成绩
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/havegraduatescore.do")
	public void haveGraduateScore(HttpServletRequest request, HttpServletResponse response)
	{
		String id 		= request.getParameter("id");
		String userId 	= request.getParameter("userid");
		if(Verify.isEmpty(userId) || Verify.isEmpty(id)) {
			HResponse.errorJSON(response);
			
			return;
		}
		try {
			String where = "from Gradethree where stuId = '" + userId.trim() + "' order by createTime desc ";
			Gradethree gradethree = gradethreeService.getRecordByWhere(where);
			if(Verify.isEmpty(gradethree)) {
				HResponse.errorJSON(response);
				
				return;
			}
			String data = "[{\"one\": \"" + gradethree.getGtrOne() + "\", \"two\": \"" + gradethree.getGtrTwo() + 
					"\" , \"three\": \"" + gradethree.getGtrThree() + "\" , \"four\": \"" + gradethree.getGtrFour() + 
					"\" , \"five\": \"" + gradethree.getGtrFive()+ "\" , \"six\": \"" + gradethree.getGtrSix() + 
					"\" , \"all\": \"" + gradethree.getGtrAll() + "\", \"judgeview\": \"" + gradethree.getContent() + "\" " ;
			String mWhere = "from Meeting where id = (" +
					"select itemId from LinkeddataMeetingGraduateinfo where relId = '" + id + "' ) ";
			Meeting meeting = meetingService.getRecordByWhere(mWhere);
			if(!Verify.isEmpty(meeting)) {
				data += ", \"content\": \"" + meeting.getContent() + "\" , \"hoster\": \"" + 
				meeting.getHoster() + "\", \"recorder\": \"" + meeting.getRecorder() + "\" ";
			}
			data += " }]";
			
			HResponse.okJSON(null, data, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
	
	/**
	 * 指导老师查看学生毕业答辩情况
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/situation.do")
	public void situation(HttpServletRequest request, HttpServletResponse response)
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String grade 			= request.getParameter("grade");
		if(Verify.isEmpty(grade)) {
			HResponse.errorJSON(response);
			return;
		}
		String data 	= "[" +
				"{\"lable\": \"<a href='" + Path.getBasePath(request) + 
				"user/graduateinfo/general.do?grade=" + grade + "'>参加答辩人数： %s  人</a>\", \"data\": %s}, " +
				"{\"lable\": \"<a href='" + Path.getBasePath(request) + 
				"user/graduateinfo/general.do?grade=" + grade + "'>通过答辩人数： %s 人</a>\", \"data\": %s}, " +
				"{\"lable\": \"<a href='" + Path.getBasePath(request) + 
				"user/graduateinfo/general.do?grade=" + grade + "'>未通过答辩人数： %s 人</a>\", \"data\": %s}," +
				"{\"lable\": \"<a href='" + Path.getBasePath(request) + 
				"user/graduateinfo/general.do?grade=" + grade + "'>未参加答辩人数： %s人</a>\", \"data\": %s}]";
		int total 	= 0;
		int canjia 	= 0;
		int tongguo = 0;
		try {
			//学生所在年级
			String where 	= "from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.graId = " + 
					grade + " AND p.deptId = '" + department.getDeptId() + "')) ";
			List<Student> stuList = studentService.getAllRows(where);
			String selWhere = "from Selectfirst where deptId = '" + department.getDeptId() + 
					"' AND teaId = '" + userid + "' AND selStatus = '1' AND  " + 
					SqlUtil.whereIn("stuId", "stuId", stuList);
			String optinfo 	= "from Graduateinfo where departmentId = '" + department.getDeptId() + "' ";
			List<Selectfirst> sefList 	= selectfirstDao.getAll(selWhere);
			if(Verify.isEmpty(sefList)) {
				//如果没有指导学生
				HResponse.errorJSON(response);
				
				return;
			}
			optinfo += " AND " + SqlUtil.whereIn("stuId", "stuId", sefList) + " ";			
			//查找毕业答辩信息
			List<Graduateinfo> optinfoList = graduateinfoService.getAllRowsByWhere(optinfo);
			total 	= sefList.size();
			if(Verify.isEmpty(optinfoList)) {
				data = String.format(data, "0", "0", "0", "0", "0", "0", total, total);
				HResponse.okJSON(null, data.toString(), response);
				
				return;
			}
			//查找毕业答辩分数
			String scoreWhere = "from Gradeall where " + SqlUtil.whereIn("stuId", "stuId", sefList);
			List<Gradeall> optscoreList 	= gradeallService.getAllRowsByWhere(scoreWhere);
			if(!Verify.isEmpty(optscoreList)) {
				canjia = optscoreList.size();
				for(int i = 0; i < canjia; i ++) {
					if(optscoreList.get(i).getGaGrade() > 60) {
						tongguo ++;
					}
				}
				data = String.format(
						data, canjia, canjia, tongguo, tongguo, (canjia - tongguo),
						(canjia - tongguo), (total - canjia), (total - canjia)
						);
				HResponse.okJSON(null, data.toString(), response);
				
				return;
			}
			canjia = optinfoList.size();
			data = String.format(
					data, canjia, canjia, 0, 0, canjia, canjia, (total - canjia), (total - canjia)
					);
			
			HResponse.okJSON(null, data, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
	
	/**
	 * 毕业答辩详细概况
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/general.do")
	public ModelAndView general(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String userid 			= (String) request.getSession().getAttribute("user_id");
		String grade 			= request.getParameter("grade");
		ModelAndView mav 		= new ModelAndView();
		mav.setViewName("/user/teacher/general-finish");
		_assignTbgradeList(request);
		if(Verify.isEmpty(grade)) {
			throw new VerifyException("请选择年级");
		}
		//学生所在年级
		String where 	= "from Student s where s.claId IN (" +
				"select c.claId from Tbclass c where c.proId IN (" +
				"select p.proId from Profession p where p.graId = " + 
				grade + " AND p.deptId = '" + department.getDeptId() + "')) ";
		List<Student> stuList = studentService.getAllRows(where);
		String selWhere = "from Selectfirst where deptId = '" + department.getDeptId() + 
				"' AND teaId = '" + userid + "' AND selStatus = '1' AND  " + 
				SqlUtil.whereIn("stuId", "stuId", stuList);
		String optinfo 	= "from Graduateinfo where departmentId = '" + department.getDeptId() + "' ";
		List<Selectfirst> sefList 	= selectfirstDao.getAll(selWhere);
		if(Verify.isEmpty(sefList)) {
			/**
			 * 如果没有指导学生
			 */
			throw new VerifyException("当前还没有指导学生");
		}
		//有指导学生
		optinfo += " AND " + SqlUtil.whereIn("stuId", "stuId", sefList) + " ";			
		//查找毕业答辩信息
		List<Graduateinfo> optinfoList = graduateinfoService.getAllRowsByWhere(optinfo);
		//查找毕业答辩分数
		String scoreWhere = "from Gradeall where " + SqlUtil.whereIn("stuId", "stuId", sefList);
		List<Gradeall> optscoreList 	= gradeallService.getAllRowsByWhere(scoreWhere);
		request.setAttribute("stuList", stuList);
		request.setAttribute("sefList", sefList);
		request.setAttribute("infoList", optinfoList);
		request.setAttribute("scoreList", optscoreList);
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", stuList));
				
		return mav;
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
	 * 学生所选课题的类型
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/topictype.do")
	public void topicType(HttpServletRequest request, HttpServletResponse response)
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
				HResponse.errorJSON("该学生未选课题或所选课题出现异常，请与管理员联系", response);
				
				return;
			}
			String html = "";
			if(tbtopic.getTopType().trim().equals("1")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>选题质量</td><td>①选题符合专业培养目标要求；②与科学研究、工程或生产实际紧密结合；" +
						"③有一定创新性和应用价值；④难度适中。</td><td>10</td><td><input class='input-mini' type='text' " +
						"name='one'></td></tr><tr><td>研究水平与实际能力</td><td>①能独立开展研究工作；②能熟练掌握和运用所学专业基" +
						"本理论、基本知识和基本技能分析解决相关理论和实际问题；③实验设计合理，实验数据准确可靠，理论分析与计算正确；" +
						"④有较强的实际动手能力、经济分析能力和现代技术应用能力。</td><td>30</td><td><input class='input-mini' " +
						"type='text' name='two'></td></tr><tr><td>论文撰写质量</td><td>①论文结构严谨，层次清晰，结论正确，" +
						"技术用语准确；②行文流畅，语句通畅；③论文格式符合规范要求；④图表完备、整洁，符号统一，编号齐全。</td><td>30</td>" +
						"<td><input class='input-mini' type='text' name='three'></td></tr><tr><td>学术水平与创新</td>" +
						"<td>①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td>" +
						"<td>10</td><td><input class='input-mini' type='text' name='four'></td></tr><tr><td>答辩</td>" +
						"<td>①能简明扼要阐述论文主要内容，思路清晰，语言表达准确、顺畅，分析归纳科学、合理，结论严谨；②回答问题有理论根据" +
						"，基本概念清楚，逻辑性强，能抓住要点，对主要问题回答准确、有深度；③仪态端庄，自然得体。</td><td>20</td><td>" +
						"<input class='input-mini' type='text' name='five'></td></tr><tr><td colspan='4'>" +
						"<div class='rfloat'>总分：<input type='text' name='all' class='input-mini'/></div></td>" +
						"</tr></table>";
			}else if(tbtopic.getTopType().trim().equals("2")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>选题质量</td><td>①选题符合专业培养目标要求；②注重反映解决社会、经济、文化中的实际问题" +
						"；③有一定创新性和应用价值；④难度适中。</td><td>10</td><td><input class='input-mini' type='text' " +
						"name='one'></td></tr><tr><td>研究水平与实际能力</td><td>①能独立开展研究工作；②能熟练掌握和运用所学专业基" +
						"本理论、基本知识和基本技能分析解决相关理论和实际问题；③论点正确、鲜明，阐述清楚，对研究的问题有较强的分析和概括能力，" +
						"有一定深度；④论据充分，材料翔实可靠，说服力强。</td><td>30</td><td><input class='input-mini' " +
						"type='text' name='two'></td></tr><tr><td>论文撰写质量</td><td>①论文结构严谨，逻辑性强，论述层次清晰；" +
						"②语句通畅，语言准确、生动；③论文格式符合规范要求；④图表完备、整洁，编号齐全。</td><td>30</td><td>" +
						"<input class='input-mini' type='text' name='three'></td></tr><tr><td>学术水平与创新</td>" +
						"<td>①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td>" +
						"<td>10</td><td><input class='input-mini' type='text' name='four'></td></tr><tr><td>答辩</td>" +
						"<td>①能简明扼要阐述论文主要内容，思路清晰，语言表达准确、顺畅，分析归纳科学、合理，结论严谨；②回答问题有理论根据，" +
						"基本概念清楚，逻辑性强，能抓住要点，对主要问题回答准确、有深度；③仪态端庄，自然得体。</td><td>20</td><td>" +
						"<input class='input-mini' type='text' name='five'></td></tr><tr><td colspan='4'>" +
						"<div class='rfloat'>总分：<input type='text' name='all' class='input-mini'/></div></td>" +
						"</tr></table>";
			}else if(tbtopic.getTopType().trim().equals("3")) {
				html = "<table class='table table-bordered'><tr><td>评价项目</td><td>评价指标</td><td>分值</td><td>评分" +
						"</td></tr><tr><td>选题质量</td><td>①选题符合专业培养目标要求；②与科学研究、工程或生产实际紧密结合；" +
						"③有一定创新性和应用价值；④难度适中。</td><td>10</td><td><input class='input-mini' type='text' " +
						"name='one'></td></tr><tr><td>设计水平与实际能力</td><td>①能独立开展设计工作；②能熟练掌握和运用所学专业基" +
						"本理论、基本知识和基本技能分析解决相关理论和实际问题；③设计方案合理可行，数据准确可靠，论证充分，理论分析与计算正确；" +
						"④有较强的实际动手能力、经济分析能力和现代技术应用能力。</td><td>20</td><td><input class='input-mini' " +
						"type='text' name='two'></td></tr><tr><td>设计说明书撰写质量</td><td>①结构严谨，层次清晰，结论正确，" +
						"技术用语准确；②行文流畅，语句通畅；③格式符合规范要求；④图表完备、整洁，符号统一，编号齐全。</td><td>20</td><td>" +
						"<input class='input-mini' type='text' name='three'></td></tr><tr><td>图纸质量</td>" +
						"<td>①结构合理，工艺可行；②图样绘制与技术要求符合国家标准；③图面质量及工作量符合要求。</td><td>20</td><td>" +
						"<input class='input-mini' type='text' name='four'></td></tr><tr><td>学术水平与创新</td>" +
						"<td>①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。</td>" +
						"<td>10</td><td><input class='input-mini' type='text' name='five'></td></tr><tr><td>答辩</td>" +
						"<td>①能简明扼要阐述设计思想和内容，思路清晰，语言表达准确、顺畅，分析归纳科学、合理，结论严谨；②回答问题有理论根据，" +
						"基本概念清楚，逻辑性强，能抓住要点，对主要问题回答准确、有深度；③仪态端庄，自然得体。</td><td>20</td><td>" +
						"<input class='input-mini' type='text' name='six'></td></tr><tr><td colspan='4'>" +
						"<div class='rfloat'>总分：<input type='text' name='all' class='input-mini'/></div></td>" +
						"</tr></table>";
			}
			HResponse.write("{\"rs\": true, \"html\": \"" + html + "\"}", response);
		} catch (Exception e) {
			e.printStackTrace();
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
}
