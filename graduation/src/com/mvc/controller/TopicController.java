package com.mvc.controller;

import java.util.Date;
import java.util.LinkedHashMap;
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
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Department;
import com.mvc.entity.Message;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Settime;
import com.mvc.entity.Student;
import com.mvc.entity.Tbclass;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Tbtopic;
import com.mvc.entity.Teacher;
import com.mvc.service.MessageService;
import com.mvc.service.SettimeService;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TbtopicService;
import com.mvc.service.TeacherService;

/**
 * 课题
 * 
 * @author Happy_Jqc@163.com
 *
 */

@Controller
@RequestMapping(value="/user/topic/")
public class TopicController {
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private SettimeService settimeService;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MessageService messageServie;
	
	@Autowired
	private TbclassService tbclassService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	
	private Integer pageNum = 1;

	private int numPerPage = 10;//每页显示多少条
	
	private List<Tbtopic> topicList;
	
	private List<Teacher> list;
	
	private List<Student> studentList;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

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

	public List<Tbtopic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Tbtopic> topicList) {
		this.topicList = topicList;
	}

	public List<Teacher> getList() {
		return list;
	}

	public void setList(List<Teacher> list) {
		this.list = list;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * 注册任务人Map
	 */
	private static LinkedHashMap<String, String> _selStatusMap = new LinkedHashMap<String, String>(){{
		put(
				"0", 
				"<button class=\"btn btn-info selectcase-btn\" type=\"button\">确定为任务人</button>"
				);
		put("1", "已确认为任务人");
	}};
	
	/**
	 * 加载任务人Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-25 下午04:51:24
	 * @return void
	 */
	public static void assignSelStatus(HttpServletRequest request)
	{
		request.setAttribute(
				"status_map",
				MapUtil.makeLinkedMapMap(_selStatusMap)
				);
	}

	/**
	 * 
	 * 进入课题提交页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-22 下午5:16:02
	 * @return ModelAndView
	 */
	@RequestMapping(value="intoSubmitTopic.do")
	public ModelAndView intoSubmitTopic(HttpServletRequest request, ModelMap modelMap){
		Integer userStatus = (Integer) request.getSession().getAttribute("user_status");
		_assignSubmitTime(request);
		if (userStatus == 1) {
			return new ModelAndView("user/student/submitTopic");
		} else {
			return new ModelAndView("user/teacher/submitTopic");
		}
	}
	
	/**
	 * 
	 * 加载提交课题时间 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-11 下午05:08:42
	 * @return void
	 */
	private void _assignSubmitTime(HttpServletRequest request){
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Settime where deptId = '" + department.getDeptId() + "' AND mark = '1' AND ('" + HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
		Settime submitTime 	= settimeService.getOneByWhere(where);
		request.getSession().setAttribute("submit-time", submitTime);
	}
		
	/**
	 * 
	 * 教师课题提交 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-22 下午5:18:00
	 * @return ModelAndView
	 */
	@RequestMapping(value="tea_submitTopic.do")
	public ModelAndView tea_submitTopic(HttpServletRequest request, ModelMap modelMap){
		if(!this._verifyData(request)){
			return new ModelAndView("forward:/user/topic/intoSubmitTopic.do");
		}

		if(!this.isOKSontopic(request)){
			return new ModelAndView("forward:/user/topic/intoSubmitTopic.do");
		}
		
		String user_id = (String) request.getSession().getAttribute("user_id");
		String topId = request.getParameter("topId");
		String topName = request.getParameter("topName");
		String topTec = request.getParameter("topTec");
		int topNum = Integer.parseInt(request.getParameter("topNum"));
		Department dept = (Department) request.getSession().getAttribute("dept");
		
		this._saveTopic(request, topId, topName, topNum, topTec, user_id, dept.getDeptId());
		
		return new ModelAndView("forward:/user/topic/tea_lookTopic.do");
	}

	/**
	 * 
	 * 信息确认 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:24:05
	 * @return boolean
	 */
	private boolean _verifyData(HttpServletRequest request) {
		String topId = request.getParameter("topId");
		String topName = request.getParameter("topName");
		
		if(topId.equals("") || topId == null){
			request.setAttribute("message", "课题编号不能为空！");
			return false;
		}
		if(!this._isTopIdExist(topId)){
			request.setAttribute("message", "课题编号冲突！");
			return false;
		}
		if(topName.equals("") || topName == null){
			request.setAttribute("message", "课题名称不能为空！");
			return false;
		}
		
		if(!this._isTopNameExist(topName)){
			request.setAttribute("message", "课题名称冲突！");
			return false;
		}
		
		if( (Integer) request.getSession().getAttribute("user_status") == 2){
			String topNum = request.getParameter("topNum");
			if(topNum.equals("") || topNum == null){
				request.setAttribute("message", "请选择课题完成人数！");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * 子课题名称确认 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-15 下午06:56:18
	 * @return boolean
	 */
	private boolean isOKSontopic(HttpServletRequest request) {
		
		int topNum = Integer.parseInt(request.getParameter("topNum"));
		
		if(topNum== 2){
			String sTName1 = request.getParameter("sTName1");
			String sTName2 = request.getParameter("sTName2");
			if(!this._isTopNameExist(sTName1) || !this._isTopNameExist(sTName2) || (sTName1.equals(sTName2))){
				request.setAttribute("message", "子课题名称冲突！");
				return false;
			}
		}
		if(topNum == 3){
			String sTName3 = request.getParameter("sTName3");
			String sTName4 = request.getParameter("sTName4");
			String sTName5 = request.getParameter("sTName5");
			if(!this._isTopNameExist(sTName3) || !this._isTopNameExist(sTName4) || !this._isTopNameExist(sTName5)
				|| sTName3.equals(sTName4) || sTName3.equals(sTName5) || sTName4.equals(sTName5)){
				request.setAttribute("message", "子课题名称冲突！");
				return false;
			}
		}
		if(topNum == 4){
			String sTName6 = request.getParameter("sTName6");
			String sTName7 = request.getParameter("sTName7");
			String sTName8 = request.getParameter("sTName8");
			String sTName9 = request.getParameter("sTName9");
			if(!this._isTopNameExist(sTName6) || !this._isTopNameExist(sTName7) || !this._isTopNameExist(sTName8) 
				|| !this._isTopNameExist(sTName9) || sTName6.equals(sTName7) || sTName6.equals(sTName8) 
				|| sTName6.equals(sTName9) || sTName7.equals(sTName8) || sTName7.equals(sTName9) || sTName8.equals(sTName9)){
				request.setAttribute("message", "子课题名称冲突！");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 判断课题是否已存在 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:44:19
	 * @return boolean
	 */
	private boolean _isTopNameExist(String topName) {
		List<Tbtopic> topicList = tbtopicService.getAll("from Tbtopic");
		for (int i = 0; i < topicList.size(); i++) {
			if (topName.equals(topicList.get(i).getTopName())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 判断课题编号是否冲突 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:44:33
	 * @return boolean
	 */
	private boolean _isTopIdExist(String topId) {
		Tbtopic topic = new Tbtopic();
		topic = tbtopicService.getByTopId(topId);
		if(topic != null){
			return false;
		}
		return true;
	}
	
	
	/**
	 * 
	 * 教师提交添加课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-14 上午09:38:18
	 * @return void
	 */
	private void _saveTopic(HttpServletRequest request, String topId, String topName, int topNum,
			String topTec, String userId, String deptId) {
		Tbtopic topic = new Tbtopic();
		topic.setTopId(topId);
		topic.setTopName(topName);
		topic.setTopNumber(topNum);
		topic.setTopTec(topTec);
		topic.setTopCommitId(userId);
		topic.setDeptId(deptId);
		topic.setTopFlag("2");
		topic.setTopStatus("0");
		topic.setParentId("0");
		try{
			tbtopicService.save(topic);
			if(topNum == 2){
				this._saveSonTopic(topId+"1", request.getParameter("sTName1"), userId, 
					topId, deptId, topTec);
				this._saveSonTopic(topId+"2", request.getParameter("sTName2"), userId, 
					topId, deptId, topTec);
			}
			if(topNum == 3){
				this._saveSonTopic(topId+"1", request.getParameter("sTName3"), userId, 
					topId, deptId, topTec);
				this._saveSonTopic(topId+"2", request.getParameter("sTName4"), userId, 
					topId, deptId, topTec);
				this._saveSonTopic(topId+"3", request.getParameter("sTName5"), userId, 
					topId, deptId, topTec);
			}
			if(topNum == 4){
				this._saveSonTopic(topId+"1", request.getParameter("sTName6"), userId, 
					topId, deptId, topTec);
				this._saveSonTopic(topId+"2", request.getParameter("sTName7"), userId, 
					topId, deptId, topTec);
				this._saveSonTopic(topId+"3", request.getParameter("sTName8"), userId, 
					topId, deptId, topTec);
				this._saveSonTopic(topId+"4", request.getParameter("sTName9"), userId, 
					topId, deptId, topTec);
			}
			request.setAttribute("message", "课题提交成功！");
		}catch(Exception e){
			System.out.println(e.toString());
			request.setAttribute("message", "课题提交失败！");
		}
	}

	/**
	 * 
	 * 添加相应子课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-14 上午10:00:09
	 * @return void
	 */
	private void _saveSonTopic(String topId, String topName, String userId,
		String parentId, String deptId, String topTec) {
		Tbtopic topic = new Tbtopic();	
		topic.setTopId(topId);
		topic.setTopName(topName);
		topic.setTopNumber(1);
		topic.setTopTec(topTec);
		topic.setTopCommitId(userId);
		topic.setDeptId(deptId);
		topic.setTopFlag("2");
		topic.setTopStatus("0");
		topic.setParentId(parentId);
		try{
			tbtopicService.save(topic);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * 
	 * 学生提交课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 下午09:21:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="stu_submitTopic.do")
	public ModelAndView stu_submitTopic(HttpServletRequest request, ModelMap modelMap){
		if(!this._verifyData(request)){
			return new ModelAndView("forward:/user/topic/intoSubmitTopic.do");
		}
		
		String user_id = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		
		Tbtopic topic = new Tbtopic();
		topic.setTopId(request.getParameter("topId"));
		topic.setTopName(request.getParameter("topName"));
		topic.setTopNumber(1);
		topic.setTopTec(request.getParameter("topTec"));
		topic.setTopStatus("0");
		topic.setTopCommitId(user_id);
		topic.setTopFlag("1");
		topic.setDeptId(dept.getDeptId());
		topic.setParentId("0");
		try {
			tbtopicService.save(topic);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new ModelAndView("forward:/user/topic/stu_lookTopic.do");
	}
	
	/**
	 * 
	 * 教师查看个人提交的课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-28 上午9:45:33
	 * @return ModelAndView
	 */
	@RequestMapping(value="tea_lookTopic.do")
	public ModelAndView tea_lookTopic(HttpServletRequest request, ModelMap modelMap){
		String username = (String) request.getSession().getAttribute("user_id");
		
		_assignlookTime(request);
		
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println("pageNum =  " + pageNum);
		int size = 20;
		if(pagination == null){
			pagination = new Pagination(size);
		}
		pagination.setSize(size);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Tbtopic where topCommitId='" + username +"' and parentId='0' order by topId asc";
		topicList = tbtopicService.getAllRecordByPages(where, pagination);
		if(topicList == null || topicList.size() < 1) {
			return new ModelAndView("user/teacher/lookTopic");
		}
		if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		modelMap.put("topicList", topicList);
		
		String hql = "from Tbtopic where topCommitId='" + username + "' and parentId<>'0' order by topId asc";
		List<Tbtopic> sonTopicList = tbtopicService.getAll(hql);
		modelMap.put("sonTopicList", sonTopicList);
		return new ModelAndView("user/teacher/lookTopic");
	}
	
	/**
	 * 
	 * 加载查看课题时间
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-18 上午10:52:12
	 * @return void
	 */
	private void _assignlookTime(HttpServletRequest request) {
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Settime where deptId = '" + department.getDeptId() + "' AND mark = '4' AND ('" + HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
		Settime lookTime 	= settimeService.getOneByWhere(where);
		request.getSession().setAttribute("looktime", lookTime);
	}

	/**
	 * 
	 * 学生查看课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-16 上午10:22:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="stu_lookTopic.do")
	public ModelAndView stu_lookTopic(HttpServletRequest request, ModelMap modelMap){
		String username = (String) request.getSession().getAttribute("user_id");
		_assignlookTime(request);
		
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println("pageNum =  " + pageNum);
		int size = 20;
		if(pagination == null){
			pagination = new Pagination(size);
		}
		pagination.setSize(size);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Tbtopic where topCommitId='" + username +"' order by topId asc";
		topicList = tbtopicService.getAllRecordByPages(where, pagination);
		if(topicList == null || topicList.size() < 1) {
			return new ModelAndView("user/student/lookTopic");
		}
		if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		modelMap.put("topicList", topicList);
		return new ModelAndView("user/student/lookTopic");
	}

	/**
	 * 
	 * 学生进入选择课题页面--加载个人课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-8 上午10:54:14
	 * @return ModelAndView
	 */
	@RequestMapping(value="studenttopic.do")
	public ModelAndView studentTopic(HttpServletRequest request, ModelMap modelMap){
		
		String username = (String) request.getSession().getAttribute("user_id");
		_assignSelectTime(request);
		//System.out.println("-----ok fou?------");
		_assignTeacherList(request, modelMap);
		//System.out.println("is ok -----");
		if(!_assignSelected(request, username, modelMap)){
			return new ModelAndView("user/student/have-selected");
		}else{
			if(pagination == null){
				pagination = new Pagination(numPerPage);
			}
			if(!Verify.isEmpty(request.getParameter("page"))) {
				System.out.println("page " + request.getParameter("page"));
				pageNum = Integer.parseInt(request.getParameter("page"));
			}
			pagination.setSize(numPerPage);
			pagination.setCurrentPage(pageNum);
			if(pagination.getCurrentPage() <= 0) {
				pagination.setCurrentPage(1);
			}
			if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
				pagination.setCurrentPage(pagination.getTotalPage());
			}
			String where = "from Tbtopic where topCommitId='" + username +"' and topStatus='1' order by topId asc";
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
			modelMap.put("pagination", pagination);
			modelMap.put("topicList-student", topicList);
			
			return new ModelAndView("user/student/student-topic");
		}
	}
	
	/**
	 * 
	 * 加载教师课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-19 上午11:33:51
	 * @return ModelAndView
	 */
	@RequestMapping(value="teachertopic.do")
	public ModelAndView teacherTopic(HttpServletRequest request, ModelMap modelMap) {
		String username = (String) request.getSession().getAttribute("user_id");
		_assignSelectTime(request);
		_assignTeacherList(request, modelMap);
		if (!_assignSelected(request, username, modelMap)) {
			return new ModelAndView("user/student/have-selected");
		} else {
			if (pagination == null) {
				pagination = new Pagination(numPerPage);
			}
			if (!Verify.isEmpty(request.getParameter("page"))) {
				System.out.println("page " + request.getParameter("page"));
				pageNum = Integer.parseInt(request.getParameter("page"));
			}
			pagination.setSize(numPerPage);
			pagination.setCurrentPage(pageNum);
			if (pagination.getCurrentPage() <= 0) {
				pagination.setCurrentPage(1);
			}
			if (pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
				pagination.setCurrentPage(pagination.getTotalPage());
			}
			String where = "from Tbtopic where parentId='0' and topStatus='1' and topFlag='2' order by topId asc";
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
			modelMap.put("topicList-teacher", topicList);

			String hql = "from Tbtopic where parentId<>'0' and topStatus='1' and topFlag='2' order by topId asc";
			List<Tbtopic> sonTopicList = tbtopicService.getAll(hql);
			modelMap.put("sonTopicList", sonTopicList);
			//System.out.println(topicList.size()+"-----teacherlistsize");
			return new ModelAndView("user/student/teacher-topic");
		}
	}
	
	/**
	 * 
	 * 确认是否已选择课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @param flag 
	 * @date 2014-8-19 下午08:27:05
	 * @return boolean
	 */
	private boolean _assignSelected(HttpServletRequest request, String username, ModelMap modelMap) {
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId ='" + username + "'");
		if(!Verify.isEmpty(selectfirst)){
			Teacher teacher = null;
			//System.out.println(selectfirst.getTeaId()+"......teacher ID");
			if(selectfirst.getTeaId() == null){
				teacher = null;
			}else{
				teacher = teacherService.getOneTeacherById(selectfirst.getTeaId());
			}
			modelMap.put("teacher-info", teacher);
			modelMap.put("select-info", selectfirst);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 学生选择课题进入填写课题认识页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-11 下午09:28:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="intoWriteKnowing.do")
	public ModelAndView intoWriteKnow(HttpServletRequest request, ModelMap modelMap){
		String topID = request.getParameter("id");
		if(Verify.isEmpty(topID)){
			request.setAttribute("message", "请选择相应课题！");
			return new ModelAndView("forward:/user/topic/intoWriteKnowing.do");
		}
		String where = "from Tbtopic where topId = '" + topID + "'";
		Tbtopic topic = tbtopicService.getRecordByWhere(where);
		modelMap.put("topic", topic);
		return new ModelAndView("user/student/writeKnowing");
	}
	
	/**
	 * 
	 * 填写对课题的认识 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-12 下午05:23:50
	 * @return ModelAndView
	 */
	@RequestMapping(value="writeKnowing.do")
	public ModelAndView writeKnowing(HttpServletRequest request, ModelMap modelMap){
		Department dept = (Department) request.getSession().getAttribute("dept");
		String userId = (String) request.getSession().getAttribute("user_id");
		String topId = request.getParameter("topicId");
		Tbtopic topic = tbtopicService.getByTopId(topId);
		
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId ='" + userId + "'");
		
		if(Verify.isEmpty(selectfirst)){
			selectfirst = new Selectfirst();
			selectfirst.setStuId(userId);
			selectfirst.setTbtopic(topic);
			selectfirst.setSelKnowing(request.getParameter("knowing"));
			selectfirst.setSelStatus("0");
			selectfirst.setDeptId(dept.getDeptId());
			if(Integer.parseInt(topic.getTopFlag()) == 2){
				selectfirst.setTeaId(topic.getTopCommitId());
				_sendMessageToTea(userId, topic.getTopCommitId(), "学生选择教师课题", " 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！");
			}
			try{
				selectfirstDao.save(selectfirst);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}else{
			try{
				selectfirst.setSelKnowing(request.getParameter("knowing"));
				selectfirstDao.update(selectfirst);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
		/**
		 * TODO 修改message
		 */
		request.setAttribute("message", "submit successful!");
		return new ModelAndView("forward:/user/topic/studenttopic.do");
	}
	

	/**
	 * 
	 * 加载指导教师列表 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-23 上午08:42:58
	 * @return ModelAndView
	 */
	public void _assignTeacherList(HttpServletRequest request, ModelMap modelMap){
		Department department = (Department) request.getSession().getAttribute("dept");
		//System.out.println(department.getDeptId()+"----deptId");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		System.out.println("numPerPage = " + numPerPage);
		System.out.println("pageNum =  " + pageNum);
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
		String where = "from Teacher where deptId = '" + department.getDeptId() + "'";
		list = teacherService.getAllRecordByPages(where, pagination);
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Teacher>) teacherService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		modelMap.put("teacher-list", list);
		modelMap.put("depmartment", department);
	}
	
	/**
	 * 
	 * 选择指导教师表单提交处理 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-19 下午03:53:27
	 * @return ModelAndView
	 */
	@RequestMapping(value="selectteacher.do")
	public ModelAndView selectTeacher(HttpServletRequest request, ModelMap modelMap){
		String teaId = request.getParameter("id");
		String userId = (String) request.getSession().getAttribute("user_id");
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId ='" + userId + "'");
		if(!Verify.isEmpty(selectfirst)){
			selectfirst.setTeaId(teaId);
			_sendMessageToTea(userId, teaId, "学生选择指导老师", " 已选择您作为指导老师，请注意查看学生选择列表！");
			try{
				selectfirstDao.update(selectfirst);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
		return new ModelAndView("forward:/user/topic/studenttopic.do");
	}
	
	/**
	 * 
	 * 发送消息 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-13 下午08:46:37
	 * @return void
	 */
	private void _sendMessageToTea(String fromId, String toId, String name, String content) {
		Student student = studentService.getOneById(fromId);
		Message message = new Message();
		message.setName(name);
		message.setContent("学生：" + student.getStuName() + content);
		message.setFromId(fromId);
		message.setToId(toId);
		Short status = 1;
		message.setStatus(status);
		message.setCreateTime(HResponse.formatDateTime(new Date()));
		try{
			messageServie.saveMessage(message);
			System.out.println(message.getContent()+"-----"+message.getCreateTime());
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}

	/**
	 * 
	 * 加载选择课题时间 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-11 下午05:25:22
	 * @return void
	 */
	private void _assignSelectTime(HttpServletRequest request) {
		Department dept = (Department) request.getSession().getAttribute("dept");
		String where  = "from Settime where deptId = '" + dept.getDeptId() + "' AND mark = '3' AND ('" + HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
		Settime selectTime = settimeService.getOneByWhere(where);
		request.setAttribute("select-time", selectTime);
	}

	
	/**
	 * 查看课题选择情况
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-10 上午10:27:25
	 * @return ModelAndView
	 */
	@RequestMapping(value="/selectcase.do")
	public ModelAndView selectCase(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/selectcase-list");
		Department department = (Department) request.getSession().getAttribute("dept");
		String userId = (String) request.getSession().getAttribute("user_id");		
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		int size = 10;
		if(Verify.isEmpty(pagination)){
			pagination = new Pagination(size);
		}
		pagination.setSize(size);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Tbtopic where topCommitId='" + userId +
			"' AND topStatus = '1' AND deptId = '" + department.getDeptId() + 
			"' AND parentId = '0' order by topId asc";
		topicList = tbtopicService.getAllRecordByPages(where, pagination);
		String subWhere = "from Tbtopic where topCommitId='" + userId +
			"' AND topStatus = '1' AND deptId = '" + department.getDeptId() + 
			"' AND parentId != '0' order by topId asc";
		List<Tbtopic> subList 	= tbtopicService.getAll(subWhere);
		String selWhere ="from Selectfirst where teaId ='" + userId + "'";
		List<Selectfirst> selsList = selectfirstDao.getAll(selWhere);
		if(Verify.isEmpty(topicList) || topicList.size() < 1) {
			return mav;
		}
		if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", topicList);
		modelMap.put("subList", subList);
		modelMap.put("selsList", selsList);
		modelMap.put("pagination", pagination);
		assignSelStatus(request);
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
	 * 确认课题任务人
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-25 下午06:26:41
	 * @return void
	 */
	@RequestMapping(value="/confirmworker.do")
	public void confirmWorker(HttpServletRequest request, HttpServletResponse response)
	{
		String id 		= request.getParameter("id");
		String topid 	= request.getParameter("topid");
		if(Verify.isEmpty(id)) {
			HResponse.errorJSON("编号不能为空", response);
			return;
		}
		String where = "from Selectfirst where tbtopic.topId = '" + topid + 
			"' AND selStatus = '1' AND teaId = '" + request.getSession().getAttribute("user_id") + "'";
		List<Selectfirst> seList 	= selectfirstDao.getAll(where);
		if(null != seList && 0 < seList.size()) {
			HResponse.errorJSON("当前课题已确认了任务人", response);
			return;
		}
		Selectfirst selectfirst = selectfirstDao.getById(Integer.parseInt(id));
		if(Verify.isEmpty(selectfirst)) {
			HResponse.errorJSON("记录不存在", response);
			return;
		}
		selectfirst.setSelStatus("1");
		try {
			selectfirstDao.update(selectfirst);
			HResponse.okJSON("成功确认该学生为任务人", response);
		} catch (Exception e) {
			HResponse.errorJSON(response);
		}
	}
	
	/**
	 * 
	 * 课题转让---选择转让对象
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-29 上午09:46:21
	 * @return ModelAndView
	 */
	@RequestMapping(value="transferview.do")
	public ModelAndView transferView(HttpServletRequest request, ModelMap modelMap){
		String userId = (String) request.getSession().getAttribute(("user_id"));
		Department dept = (Department) request.getSession().getAttribute("dept");
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId ='" + userId + "'");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/user/topic/studenttopic.do");
		if(Verify.isEmpty(selectfirst)){
			request.setAttribute("message", "您未选择课题，不能进行课题转让操作！");
			return mav;
		}else if(Verify.isEmpty(selectfirst.getSelKnowing())){
			request.setAttribute("message", "您所选择课题未填写课题认识，不能进行课题转让操作！");
			return mav;
		}else if(Verify.isEmpty(selectfirst.getTeaId())){
			request.setAttribute("message", "您未选择指导老师，请先选择指导老师！");
			return mav;
		}else{
			_assignTransTime(request, dept.getDeptId());
			
			String where = "from Selectfirst where deptId = '" + dept.getDeptId() + "'";
			List<Selectfirst> selectlist = selectfirstDao.getAll(where);
			
			if(!(request.getParameter("pageNum") == null))
			{
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(!(request.getParameter("numPerPage") == null)) {
				numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			}
			//System.out.println("numPerPage = " + numPerPage);
			//System.out.println("pageNum =  " + pageNum);
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
			
			Student student = studentService.getOneById(userId);
			String graWhere = "select * from tbgrade where `gra_ID` = (" +
					"select gra_ID from profession where pro_ID = (" +
					"select pro_ID from tbclass where cla_ID = "+ student.getClaId() + "))";
			List<Tbgrade> gradelist = tbgradeService.getAll(graWhere);
			//System.out.println(gradelist.size()+"----gradelist size-----");
			
			String claWhere = "select * from tbclass where `pro_ID` IN (" +
					"select pro_ID from profession where gra_ID = " + gradelist.get(0).getGraId() + ")";
			List<Tbclass> classlist = tbclassService.getAllByConn(claWhere);
			String whereIn = SqlUtil.whereIn("claId", "claId", classlist);
			//System.out.println(whereIn+"----whereIn");
			
			String whereNotIn = SqlUtil.whereNotIn("stuId", "stuId", selectlist);
			
			String sql = "from Student where " + whereNotIn + " AND " + whereIn;
			studentList = studentService.getAllRecordByPages(sql, pagination);
			
			if(studentList != null && this.studentList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				studentList = (List<Student>) studentService.getAllRecordByPages(sql, pagination);
			}
			if(studentList == null){
				studentList = null;
			}
			modelMap.put("student-list", studentList);
			modelMap.put("pagination", pagination);
			return new ModelAndView("user/student/transfer-student");
		}
	}
	
	/**
	 * 
	 * 加载课题转让时间 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-30 下午04:16:14
	 * @return void
	 */
	private void _assignTransTime(HttpServletRequest request, String deptId) {
		String where  = "from Settime where deptId = '" + deptId + "' AND mark = '7' AND ('" + HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
		Settime transferTime = settimeService.getOneByWhere(where);
		request.setAttribute("transfer-time", transferTime);
	}	
	
	/**
	 * 
	 * 课题转让---选择课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-29 上午09:50:43
	 * @return ModelAndView
	 */
	@RequestMapping(value="transtopic.do")
	public ModelAndView transTopic(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response){
		Department dept = (Department) request.getSession().getAttribute("dept");
		String userId = (String) request.getSession().getAttribute(("user_id"));
		String stuId = request.getParameter("id");
		
		_assignTransTime(request, dept.getDeptId());
		
		Selectfirst selfirst = new Selectfirst();
		selfirst.setStuId(stuId);
		selfirst.setSelStatus("0");
		selfirst.setDeptId(dept.getDeptId());
		
		try{
			selectfirstDao.save(selfirst);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		_assignPagination(request, pagination);
		
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId = '" + userId + "'");
		
		List<Tbtopic> topiclist = tbtopicService.getAll("from Tbtopic where topId = '" + selectfirst.getTbtopic().getTopId() +"'");
		String whereNotIn = SqlUtil.whereNotIn("topId", "topId", topiclist);
		
		System.out.println(whereNotIn+".......whereNotIn");
		
		String where = "from Tbtopic where topCommitId='" + userId +"' and topStatus='1' and " + whereNotIn + " order by topId asc";
		topicList = tbtopicService.getAllRecordByPages(where, pagination);
		
		if(topicList != null && topicList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		modelMap.put("topicList", topicList);
		request.getSession().setAttribute("stuId", stuId);
		return new ModelAndView("user/student/transfer-topic");
	}
	
	/**
	 * 
	 * 选择课题转让跳转结果 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 上午09:41:20
	 * @return ModelAndView
	 */
	@RequestMapping(value="confirmtransfer.do")
	public ModelAndView confirmTransfer(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response){
		String stuId = (String) request.getSession().getAttribute("stuId");
		String userId = (String) request.getSession().getAttribute("user_id");
		String topId = request.getParameter("id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		
		_assignTransTime(request, dept.getDeptId());
		
		Selectfirst selectfirst = selectfirstDao.getOne("from Selectfirst where stuId = '" + stuId + "'");
		Tbtopic topic = tbtopicService.getByTopId(topId);
		
		try{
			selectfirst.setTbtopic(topic);
			selectfirstDao.update(selectfirst);
			_sendMessageToTea(userId, stuId, "课题转让", " 向你转让课题，请注意查看选择课题模块内容！");
		}catch(Exception e){
			System.out.println(e.toString());
		}
		request.setAttribute("message", "课题转让成功，请联系该同学填写相关内容及选择指导教师！");
		return new ModelAndView("user/student/transfer-ok");
	}
	
	/**
	 *  
	 * TODO 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 上午09:16:23
	 * @return void
	 */
	public void _assignPagination(HttpServletRequest request, Pagination pagination){
		if(pagination == null){
			pagination = new Pagination(numPerPage);
		}
		if(!Verify.isEmpty(request.getParameter("page"))) {
			//System.out.println("page " + request.getParameter("page"));
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		pagination.setSize(numPerPage);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
	}
}