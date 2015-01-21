package com.mvc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.mvc.controller.admin.TeacherController;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Department;
import com.mvc.entity.Message;
import com.mvc.entity.Reviewresult;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Settime;
import com.mvc.entity.Student;
import com.mvc.entity.Tbclass;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Tbtopic;
import com.mvc.entity.Teacher;
import com.mvc.exception.VerifyException;
import com.mvc.service.MessageService;
import com.mvc.service.ReviewresultService;
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
	
	@Autowired
	private ReviewresultService reviewresultService;
	
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
	 * 注册课题审核状态map
	 */
	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> _topStatusMap = new LinkedHashMap<String, String>(){{
		put("0", "未审核");
		put("1", "通过");
		put("2", "未通过");
	}};

	/**
	 * 注册任务人Map
	 */
	@SuppressWarnings("serial")
	private static LinkedHashMap<String, String> _selStatusMap = new LinkedHashMap<String, String>(){{
		put(
				"0", 
				"<button class=\"btn btn-info selectcase-btn\" type=\"button\">确定为任务人</button>" +
				" <button class=\"btn btn-warning selectcasecannot-btn\" type=\"button\">非任务人</button>"
				);
		put("1", "<span style=\"color: red;\">已确认为任务人</span>");
		put("2", "已确定为非任务人");
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
	 * 注册课题类型map
	 */
	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> _selTypeMap = new LinkedHashMap<String, String>(){{
		put("1", "论文");
		put("2", "设计");
		put("3", "其他");
	}};
	
	/**
	 * 
	 * 加载课题类型map 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-17 下午05:46:42
	 * @return void
	 */
	public static void assignSelType(HttpServletRequest request){
		request.setAttribute("toptype_map", MapUtil.makeLinkedMapMap(_selTypeMap));
	}
	
	/**
	 * 注册课题来源map
	 */
	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> _selSourceMap = new LinkedHashMap<String, String>(){{
		put("1", "科学技术");
		put("2", "生成实践");
		put("3", "社会经济");
		put("4", "自拟");
		put("5", "其他");
	}};
	
	/**
	 * 
	 * 加载课题来源map 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-17 下午05:50:54
	 * @return void
	 */
	public static void assignSelSource(HttpServletRequest request){
		request.setAttribute("topsource_map", MapUtil.makeLinkedMapMap(_selSourceMap));
	}
	
	/**
	 * 加载课题审核状态列表Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void assignTopStatusListMap(HttpServletRequest request)
	{
		request.setAttribute("topstatus_list", MapUtil.makeLinkedListMap(_topStatusMap));
	}
	
	/**
	 * 加载课题审核状态Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void assignTopStatusMapMap(HttpServletRequest request)
	{
		request.setAttribute("topstatus_map", MapUtil.makeLinkedMapMap(_topStatusMap));
	}
	
	/**
	 * 
	 * 进入课题提交页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-22 下午5:16:02
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="intoSubmitTopic.do")
	public ModelAndView intoSubmitTopic(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
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
	 * @throws VerifyException 
	 */
	private void _assignSubmitTime(HttpServletRequest request) throws VerifyException{
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="tea_submitTopic.do")
	public ModelAndView tea_submitTopic(HttpServletRequest request, HttpServletResponse response,
		ModelMap modelMap) throws IOException, VerifyException{
		if(!this._verifyData(request)){
			return new ModelAndView("forward:/user/topic/intoSubmitTopic.do");
		}

		if(!this.isOKSontopic(request)){
			return new ModelAndView("forward:/user/topic/intoSubmitTopic.do");
		}
		
		String user_id = (String) request.getSession().getAttribute("user_id");
		String topName = request.getParameter("topName");
		String topContent = request.getParameter("topContent");
		String topType = request.getParameter("topType");
		String source = request.getParameter("source");
		String keywords = request.getParameter("topkeywords");
		int topNum = Integer.parseInt(request.getParameter("topNum"));
		Department dept = (Department) request.getSession().getAttribute("dept");
		Calendar rightNow = Calendar.getInstance();
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
	    String topId = fmt.format(rightNow.getTime()); 
	    
		this._saveTopic(request, topId, topName, topNum, topContent, 
				user_id, dept.getDeptId(), topType, source, keywords);
		return new ModelAndView("redirect:/user/topic/tea_lookTopic.do");
	}

	/**
	 * 
	 * 信息确认 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-24 上午11:24:05
	 * @return boolean
	 * @throws VerifyException 
	 */
	private boolean _verifyData(HttpServletRequest request) throws VerifyException {
		//String topId = request.getParameter("topId");
		String topName = request.getParameter("topName");
		String topContent = request.getParameter("topContent");
		String topKeywords = request.getParameter("topkeywords");
		
		/*if(topId.equals("") || topId == null){
			request.setAttribute("message", "课题编号不能为空！");
			return false;
		}
		if(!this._isTopIdExist(topId)){
			request.setAttribute("message", "课题编号冲突！");
			return false;
		}*/
		if(Verify.isEmpty(topName)){
			request.setAttribute("message", "课题名称不能为空！");
			return false;
		}
		
		if(!this._isTopNameExist(topName, request)){
			request.setAttribute("message", "课题名称冲突！");
			return false;
		}
		
		if(Verify.isEmpty(topContent)){
			request.setAttribute("message", "课题内容不能为空!");
			return false;
		}
		
		if(Verify.isEmpty(topKeywords)){
			request.setAttribute("message", "关键字不能为空！");
			return false;
		}
		
		if( (Integer) request.getSession().getAttribute("user_status") == 2){
			String topNum = request.getParameter("topNum");
			if(Verify.isEmpty(topNum)){
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
	 * @throws VerifyException 
	 */
	private boolean isOKSontopic(HttpServletRequest request) throws VerifyException {
		
		int topNum = Integer.parseInt(request.getParameter("topNum"));
		
		if(topNum== 2){
			String sTName1 = request.getParameter("sTName1");
			String sTName2 = request.getParameter("sTName2");
			if(!this._isTopNameExist(sTName1, request) || !this._isTopNameExist(sTName2, request) || (sTName1.equals(sTName2))){
				request.setAttribute("message", "子课题名称冲突！");
				return false;
			}
		}
		if(topNum == 3){
			String sTName3 = request.getParameter("sTName3");
			String sTName4 = request.getParameter("sTName4");
			String sTName5 = request.getParameter("sTName5");
			if(!this._isTopNameExist(sTName3, request) || !this._isTopNameExist(sTName4, request) || !this._isTopNameExist(sTName5, request)
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
			if(!this._isTopNameExist(sTName6, request) || !this._isTopNameExist(sTName7, request) || !this._isTopNameExist(sTName8, request) 
				|| !this._isTopNameExist(sTName9, request) || sTName6.equals(sTName7) || sTName6.equals(sTName8) 
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
	 * @param request 
	 * @date 2014-7-24 上午11:44:19
	 * @return boolean
	 * @throws VerifyException 
	 */
	private boolean _isTopNameExist(String topName, HttpServletRequest request) throws VerifyException {
		String id 		= request.getParameter("id");
		String topId    = request.getParameter("topid");
		String where 	= "from Tbtopic where topName = '" + topName + "' ";
		if(!Verify.isEmpty(id)) {
			where += " AND topId != '" + id + "' ";
		}else if(!Verify.isEmpty(topId)){
			where += " AND topId <> '" + topId + "'";
		}
		List<Tbtopic> topicList = tbtopicService.getAll(where);
		if(!Verify.isEmpty(topicList)) {
			return false;
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
	 * @throws VerifyException 
	 */
	private boolean _isTopIdExist(String topId) throws VerifyException {
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
	 * @param keywords 
	 * @param source 
	 * @date 2014-8-14 上午09:38:18
	 * @return void
	 */
	private void _saveTopic(HttpServletRequest request, String topId, String topName, int topNum,
			String topContent, String userId, String deptId, String topType, String source, String keywords) {
		Tbtopic topic = new Tbtopic();
		topic.setTopId(topId);
		topic.setTopName(topName);
		topic.setTopNumber(topNum);
		topic.setTopContent(topContent);
		topic.setTopCommitId(userId);
		topic.setDeptId(deptId);
		topic.setTopFlag("2");
		topic.setTopStatus("0");
		topic.setParentId("0");
		topic.setTopType(topType);
		topic.setTopSource(source);
		topic.setTopKeywords(keywords);
		topic.setTopYear(HResponse.getCurrentYear());
		try{
			tbtopicService.save(topic);
			if(topNum == 2){
				this._saveSonTopic(topId+"1", request.getParameter("sTName1"), userId, 
					topId, deptId, topContent, topType, source, keywords);
				this._saveSonTopic(topId+"2", request.getParameter("sTName2"), userId, 
					topId, deptId, topContent, topType, source, keywords);
			}
			if(topNum == 3){
				this._saveSonTopic(topId+"1", request.getParameter("sTName3"), userId, 
					topId, deptId, topContent, topType, source, keywords);
				this._saveSonTopic(topId+"2", request.getParameter("sTName4"), userId, 
					topId, deptId, topContent, topType, source, keywords);
				this._saveSonTopic(topId+"3", request.getParameter("sTName5"), userId, 
					topId, deptId, topContent, topType, source, keywords);
			}
			if(topNum == 4){
				this._saveSonTopic(topId+"1", request.getParameter("sTName6"), userId, 
					topId, deptId, topContent, topType, source, keywords);
				this._saveSonTopic(topId+"2", request.getParameter("sTName7"), userId, 
					topId, deptId, topContent, topType, source, keywords);
				this._saveSonTopic(topId+"3", request.getParameter("sTName8"), userId, 
					topId, deptId, topContent, topType, source, keywords);
				this._saveSonTopic(topId+"4", request.getParameter("sTName9"), userId, 
					topId, deptId, topContent, topType, source, keywords);
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
	 * @param keywords 
	 * @param source 
	 * @param topType 
	 * @date 2014-8-14 上午10:00:09
	 * @return void
	 */
	private void _saveSonTopic(String topId, String topName, String userId,
		String parentId, String deptId, String topContent, String topType, String source, String keywords) {
		Tbtopic topic = new Tbtopic();	
		topic.setTopId(topId);
		topic.setTopName(topName);
		topic.setTopNumber(1);
		topic.setTopContent(topContent);
		topic.setTopCommitId(userId);
		topic.setDeptId(deptId);
		topic.setTopFlag("2");
		topic.setTopStatus("0");
		topic.setParentId(parentId);
		topic.setTopType(topType);
		topic.setTopSource(source);
		topic.setTopKeywords(keywords);
		topic.setTopYear(topId.substring(0, 4));
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="stu_submitTopic.do")
	public ModelAndView stu_submitTopic(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		if(!this._verifyData(request)){
			return new ModelAndView("forward:/user/topic/intoSubmitTopic.do");
		}
		
		String user_id = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		Calendar rightNow = Calendar.getInstance();
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
	    String topId = fmt.format(rightNow.getTime()); 
		
		Tbtopic topic = new Tbtopic();
		topic.setTopId(topId);
		topic.setTopName(request.getParameter("topName"));
		topic.setTopNumber(1);
		topic.setTopContent(request.getParameter("topContent"));
		topic.setTopStatus("0");
		topic.setTopCommitId(user_id);
		topic.setTopFlag("1");
		topic.setTopKeywords(request.getParameter("topkeywords"));
		topic.setTopSource(request.getParameter("source"));
		topic.setTopType(request.getParameter("topType"));
		topic.setTopYear(HResponse.getCurrentYear());
		topic.setDeptId(dept.getDeptId());
		topic.setParentId("0");
		try {
			tbtopicService.save(topic);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new ModelAndView("redirect:/user/topic/stu_lookTopic.do");
	}
	
	/**
	 * 
	 * 教师查看个人提交的课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-28 上午9:45:33
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="tea_lookTopic.do")
	public ModelAndView tea_lookTopic(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String username = (String) request.getSession().getAttribute("user_id");
		String year 	= request.getParameter("year");
		//_assignlookTime(request);
		
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println("pageNum =  " + pageNum);
		int size = 20;
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
		String where = "from Tbtopic where topCommitId='" + username +"' and parentId = '0' ";
		if(!Verify.isEmpty(year)) {
			where += " AND topYear LIKE '%" + year + "%' ";
		}
		where += " order by topId asc";
		topicList = tbtopicService.getAllRecordByPages(where, pagination);
		_assignTopicYear(request);
		if(topicList == null || topicList.size() < 1) {
			return new ModelAndView("user/teacher/lookTopic");
		}
		if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		modelMap.put("topicList", topicList);
		
		String hql = "from Tbtopic where topCommitId='" + username + "' and parentId <> '0' ";
		if(!Verify.isEmpty(year)) {
			hql += " AND topYear LIKE '%" + year + "%' ";
		}
		hql += " order by topId asc";
		List<Tbtopic> sonTopicList = tbtopicService.getAll(hql);
		modelMap.put("sonTopicList", sonTopicList);
		assignSelSource(request);
		assignSelType(request);
		assignTopStatusMapMap(request);
		
		return new ModelAndView("user/teacher/lookTopic");
	}
	
	/**
	 * 
	 * TODO 加载查看课题时间
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-18 上午10:52:12
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignlookTime(HttpServletRequest request) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="stu_lookTopic.do")
	public ModelAndView stu_lookTopic(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String username = (String) request.getSession().getAttribute("user_id");
		//_assignlookTime(request);
		
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
		assignSelSource(request);
		assignSelType(request);
		return new ModelAndView("user/student/lookTopic");
	}

	/**
	 * 
	 * 学生进入选择课题页面--加载个人课题 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-8 上午10:54:14
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="studenttopic.do")
	public ModelAndView studentTopic(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{		
		String username = (String) request.getSession().getAttribute("user_id");
		_assignSelectTime(request);
		_assignTeacherList(request, modelMap);
		if(!_assignSelected(request, modelMap)){
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
			assignSelSource(request);
			assignSelType(request);
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
	 * @throws VerifyException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="teachertopic.do")
	public ModelAndView teacherTopic(HttpServletRequest request, ModelMap modelMap) throws VerifyException {
		String username = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		_assignSelectTime(request);
		_assignTeacherList(request, modelMap);
		if (!_assignSelected(request, modelMap)) {
			return new ModelAndView("user/student/have-selected");
		} else {
			if (pagination == null) {
				pagination = new Pagination(numPerPage);
			}
			if (!Verify.isEmpty(request.getParameter("pageNum"))) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			pagination.setSize(numPerPage);
			pagination.setCurrentPage(pageNum);
			if (pagination.getCurrentPage() <= 0) {
				pagination.setCurrentPage(1);
			}
			if (pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
				pagination.setCurrentPage(pagination.getTotalPage());
			}
			String where = "from Tbtopic where parentId='0' and topStatus='1' and topFlag='2' and deptId = '" +
					dept.getDeptId() + "' AND completerId is null order by topId asc";
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
			System.out.println(topicList.size());
			modelMap.put("topicList-teacher", topicList);

			String hql = "from Tbtopic where parentId<>'0' and topStatus='1' and topFlag='2' and deptId = '" +
					dept.getDeptId() + "' AND completerId is null order by topId asc";
			List<Tbtopic> sonTopicList = tbtopicService.getAll(hql);
			modelMap.put("sonTopicList", sonTopicList);
			List<Teacher> tealist = teacherService.getAllRows("from Teacher where deptId = '" + dept.getDeptId() + "'");
			modelMap.put("tealist", tealist);
			assignSelSource(request);
			assignSelType(request);
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
	 * @throws VerifyException 
	 */
	private boolean _assignSelected(HttpServletRequest request, ModelMap modelMap) throws VerifyException {
		String username = (String) request.getSession().getAttribute("user_id");
		String where 	= "from Selectfirst where stuId ='" + username + "' order by selId desc ";
		Selectfirst selectfirst = selectfirstDao.getOne(where);
		if(!Verify.isEmpty(selectfirst)){
			modelMap.put("select-info", selectfirst);
			Teacher teacher = null;
			if(selectfirst.getSelStatus().equals("2")){
				request.setAttribute("message", "你所选择教师工号为 " + selectfirst.getTeaId() + " 的课题未被同意！请重新选择！");
				return true;
			}else{
				if(selectfirst.getTeaId() == null){
					teacher = null;
				}else{
					teacher = teacherService.getOneTeacherById(selectfirst.getTeaId());
				}
				modelMap.put("teacher-info", teacher);
				return false;
			}
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="intoWriteKnowing.do")
	public ModelAndView intoWriteKnow(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String topID = request.getParameter("id");
		if(Verify.isEmpty(topID)){
			throw new VerifyException("请选择相应课题！");
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="writeKnowing.do")
	public ModelAndView writeKnowing(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		Department dept = (Department) request.getSession().getAttribute("dept");
		String userId = (String) request.getSession().getAttribute("user_id");
		String topId = request.getParameter("topicId");
		Tbtopic topic = tbtopicService.getByTopId(topId);
		String where = "from Selectfirst where stuId ='" + userId + "' AND selStatus IN ('0', '1') ";
		Selectfirst selectfirst = selectfirstDao.getOne(where);
		if(Verify.isEmpty(selectfirst)){
			selectfirst = new Selectfirst();
			selectfirst.setStuId(userId);
			selectfirst.setTbtopic(topic);
			selectfirst.setSelKnowing(request.getParameter("knowing"));
			selectfirst.setSelStatus("0");
			selectfirst.setDeptId(dept.getDeptId());
			if(Integer.parseInt(topic.getTopFlag()) == 2){
				selectfirst.setTeaId(topic.getTopCommitId());
				_sendMessageToTea(
						userId,
						topic.getTopCommitId(),
						"学生选择教师课题",
						" 已选择您的课题并选择您为指导老师，请注意查看学生选择列表！"
						);
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
		request.setAttribute("message", "课题选择成功！");
		
		return new ModelAndView("redirect:/user/topic/studenttopic.do");
	}
	

	/**
	 * 
	 * 加载指导教师列表 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-23 上午08:42:58
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	public void _assignTeacherList(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		Department department = (Department) request.getSession().getAttribute("dept");
		//System.out.println(department.getDeptId()+"----deptId");
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
		String where = "from Teacher where deptId = '" + department.getDeptId() + "' order by teaId asc ";
		list = teacherService.getAllRecordByPages(where, pagination);
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Teacher>) teacherService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		modelMap.put("teacher-list", list);
		modelMap.put("depmartment", department);
		TeacherController.assignTeacherposMap(request);
	}
	
	/**
	 * 
	 * 选择指导教师表单提交处理 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-19 下午03:53:27
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="selectteacher.do")
	public ModelAndView selectTeacher(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String teaId = request.getParameter("id");
		String userId = (String) request.getSession().getAttribute("user_id");
		Selectfirst selectfirst = selectfirstDao.getOne(
				"from Selectfirst where stuId ='" + userId + "' AND selStatus = '0' order by selId desc "
				);
		if(!Verify.isEmpty(selectfirst)){
			selectfirst.setTeaId(teaId);
			_sendMessageToTea(userId, teaId, "学生选择指导老师", " 已选择您作为指导老师，请注意查看学生选择列表！");
			try{
				selectfirstDao.update(selectfirst);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
		return new ModelAndView("redirect:/user/topic/studenttopic.do");
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
	 * @throws VerifyException 
	 */
	private void _assignSelectTime(HttpServletRequest request) throws VerifyException {
		Department dept = (Department) request.getSession().getAttribute("dept");
		String where  = "from Settime where deptId = '" + dept.getDeptId() + "' AND mark = '3' AND ('" + 
				HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
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
		Department department = (Department) request.getSession().getAttribute("dept");
		String userId	= (String) request.getSession().getAttribute("user_id");	
		String topic 	= request.getParameter("topic");
		String year 	= request.getParameter("year");
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(Verify.isEmpty(pagination)){
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
		String where = "";
		String subWhere = "";
		if((!Verify.isEmpty(topic) && topic.trim().equals("student"))) {
			/**
			 * 学生提交的课题
			 */
			mav.setViewName("user/teacher/selectcase-student-list");
			String scomm = "from Tbtopic t where t.topFlag ='1' AND t.topStatus = '1' " +
					"AND t.deptId = '" + department.getDeptId() + "' ";
			String topcomm = "AND t.topId IN (select sf.tbtopic.topId from Selectfirst sf where " +
					"sf.teaId = '"+ userId + "') order by t.topId asc";
			where =  scomm + "AND t.parentId = '0' ";
			subWhere = scomm + "AND t.parentId != '0' ";
			if(!Verify.isEmpty(year)) {
				where 		+= " AND topYear LIKE '%" + year.trim() + "%' ";
				subWhere 	+= " AND topYear LIKE '%" + year.trim() + "%' ";
			}
			where 		+= topcomm;
			subWhere 	+= topcomm;
		} else {
			/**
			 * 教师自己的课题
			 */
			mav.setViewName("user/teacher/selectcase-list");
			String commom = "from Tbtopic where topCommitId='" + userId +
					"' AND topStatus = '1' AND deptId = '" + department.getDeptId() + "' " ;
			where 		= commom + "AND parentId = '0' ";
			subWhere 	= commom + "AND parentId != '0' ";
			if(!Verify.isEmpty(year)) {
				where 		+= " AND topYear LIKE '%" + year.trim() + "%' ";
				subWhere 	+= " AND topYear LIKE '%" + year.trim() + "%' ";
			}
			where 		+= "order by topId asc";
			subWhere 	+= "order by topId asc";
		}
		try {
			_assignTopicYear(request);
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
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
	 * 确认课题任务人
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-25 下午06:26:41
	 * @return void
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/confirmworker.do")
	public void confirmWorker(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		String id 		= request.getParameter("id");
		String topid 	= request.getParameter("topid");
		String worker 	= request.getParameter("worker");
		String topicpe 	= request.getParameter("topicpe");
		String userid 	= (String) request.getSession().getAttribute("user_id");
		if(Verify.isEmpty(id) || Verify.isEmpty(topid)) {
			HResponse.errorJSON("编号不能为空", response);
			
			return;
		}
		if(Verify.isEmpty(worker)) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
			
			return;
		}
		if(worker.trim().equals("ok")) {
			String where = "from Selectfirst where tbtopic.topId = '" + topid + 
				"' AND selStatus = '1' AND teaId = '" + request.getSession().getAttribute("user_id") + "'";
			List<Selectfirst> seList 	= selectfirstDao.getAll(where);
			if(!Verify.isEmpty(seList)) {
				HResponse.errorJSON("当前课题已确认了任务人", response);
				
				return;
			}
		}
		
		Selectfirst selectfirst = selectfirstDao.getById(Integer.parseInt(id));
		if(Verify.isEmpty(selectfirst)) {
			HResponse.errorJSON("记录不存在", response);
			return;
		}
		List<Selectfirst> selist = null;
		String content = "你选的课题已经被老师" + request.getSession().getAttribute("user_name").toString() + "确认为 ";
		String item = content;
		if(worker.trim().equals("ok")) {
			request.setAttribute("title", "你被指导老师确认为任务人了");
			selectfirst.setSelStatus("1");
			content += "任务人 ";
			/**
			 * 选择该课题的其他同学为非任务人
			 */
			String where = "from Selectfirst where teaId = '" + userid + 
					"' AND tbtopic.topId = '" + topid + "' AND selId != " + id ; 
			selist = selectfirstDao.getAll(where);
		} else if(worker.trim().equals("no")){
			request.setAttribute("title", "你被指导老师拒绝为任务人了");
			/**
			 * 判断是学生的课题还是教师的课题
			 */
			if(!Verify.isEmpty(topicpe) && topicpe.trim().equals("1")) {
				selectfirst.setTeaId("");
			} else {
				selectfirst.setSelStatus("2");
			}
			
			content += "非任务人 ";
		}
		content += "，请及时查看";
		try {
			selectfirstDao.update(selectfirst);
			_assignSendMessage(selectfirst.getStuId(), userid, content, request);
			if(worker.trim().equals("ok")) {
				//修改课题表任务人
				Tbtopic tbtopic = tbtopicService.getByTopId(topid);
				tbtopic.setCompleterId(selectfirst.getStuId());
				tbtopicService.editOneRecord(tbtopic);
			}
			if(!Verify.isEmpty(selist)) {
				//选择该课题的其他同学为非任务人
				for(int i = 0; i < selist.size(); i ++) {
					Selectfirst se = selist.get(i);
					se.setSelStatus("2");
					selectfirstDao.update(se);
					_assignSendMessage(
							se.getStuId(), 
							userid, 
							item += "非任务人，请及时查看 ", 
							request
							);
				}
			}
			
			HResponse.okJSON(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
	
	/**
	 * 发送消息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param toId
	 * @param fromId
	 * @param content
	 * @param request
	 */
	private void _assignSendMessage(String toId, String fromId, String content, HttpServletRequest request)
	{
		Message message = new Message();
		try{
			message.setName(request.getAttribute("title").toString());
			message.setContent(content);
			message.setToId(toId);
			message.setFromId(fromId);
			short status = 1;
			message.setStatus(status);
			message.setCreateTime(HResponse.formatDateTime(new Date()));
			messageServie.saveMessage(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载课题年份
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignTopicYear(HttpServletRequest request) throws VerifyException 
	{
		Department department 	= (Department) request.getSession().getAttribute("dept");
		String begroupWhere = "select topYear from Tbtopic where deptId = '" + 
				department.getDeptId() + "' group by topYear order by topYear desc ";
		List<Tbtopic> begroupList = tbtopicService.getAll(begroupWhere);
		request.setAttribute("begroupList", begroupList);
	}
	
	/**
	 * 
	 * 课题转让---选择转让对象
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-29 上午09:46:21
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="transferview.do")
	public ModelAndView transferView(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		String userId = (String) request.getSession().getAttribute(("user_id"));
		Department dept = (Department) request.getSession().getAttribute("dept");
		Selectfirst selectfirst = selectfirstDao.getOne(
				"from Selectfirst where stuId ='" + userId + "' AND selStatus ='1' "
				);
		List<Tbtopic> topiclist = tbtopicService.getAll(_assignHaveTopic(userId));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/user/topic/studenttopic.do");
		if(Verify.isEmpty(selectfirst)){
			throw new VerifyException("您未选择课题或未被确认为任务人！");
		}else if(Verify.isEmpty(selectfirst.getSelKnowing())){
			throw new VerifyException("您所选择课题未填写课题认识，不能进行课题转让操作！");
		}else if(Verify.isEmpty(selectfirst.getTeaId())){
			throw new VerifyException("您未选择指导老师，请先选择指导老师！");
		}else if(Verify.isEmpty(topiclist)){
			throw new VerifyException("当前无课题可供转让！");
		}else{
			_assignTransTime(request, dept.getDeptId());
			String where = "from Selectfirst where deptId = '" + dept.getDeptId() + "'";
			List<Selectfirst> selectlist = selectfirstDao.getAll(where);
			if(!Verify.isEmpty(request.getParameter("pageNum")))
			{
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
				numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			}
			if(Verify.isEmpty(pagination)){
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
			String claWhere = "select * from tbclass where `pro_ID` IN (" +
					"select pro_ID from profession where gra_ID = " + gradelist.get(0).getGraId() + ")";
			List<Tbclass> classlist = tbclassService.getAllByConn(claWhere);
			String whereIn = SqlUtil.whereIn("claId", "claId", classlist);
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
		}
		
		return new ModelAndView("user/student/transfer-student");
	}
	
	/**
	 * 
	 * 加载课题转让时间 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-9-30 下午04:16:14
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignTransTime(HttpServletRequest request, String deptId) throws VerifyException {
		String where  = "from Settime where deptId = '" + deptId + "' AND mark = '7' AND ('" + 
				HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="transtopic.do")
	public ModelAndView transTopic(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) throws VerifyException{
		Department dept = (Department) request.getSession().getAttribute("dept");
		String userId = (String) request.getSession().getAttribute(("user_id"));
		String stuId = request.getParameter("id");
		_assignTransTime(request, dept.getDeptId());
		Selectfirst selfirst = new Selectfirst();
		selfirst.setStuId(stuId);
		selfirst.setSelStatus("0");
		selfirst.setDeptId(dept.getDeptId());
		try{
			//selectfirstDao.save(selfirst);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		_assignPagination(request, pagination);
		String where = _assignHaveTopic(userId);
		topicList = tbtopicService.getAllRecordByPages(where, pagination);
		
		if(topicList != null && topicList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("pagination", pagination);
		//modelMap.put("topicList", topicList);
		request.setAttribute("topicList", topicList);
		request.getSession().setAttribute("stuId", stuId);
		assignSelSource(request);
		assignSelType(request);
		
		return new ModelAndView("user/student/transfer-topic");
	}
	
	/**
	 * 
	 * 获得可以转让课题查询语句 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-11-11 下午07:48:40
	 * @return String
	 * @throws VerifyException 
	 */
	private String _assignHaveTopic(String userId) throws VerifyException {
		String where = "from Tbtopic where topCommitId='" + userId +"' and topStatus='1' ";		
		Selectfirst selectfirst = selectfirstDao.getOne(
				"from Selectfirst where stuId = '" + userId + "' AND selStatus = '1' "
				);
		if(Verify.isEmpty(selectfirst)) {
			/**
			 * 自己得先有课题及有指导老师
			 */
			where += " AND 1 = 2 ";
			
			return where;
		}
		List<Tbtopic> topiclist = tbtopicService.getAll(
				"from Tbtopic where topId = '" + selectfirst.getTbtopic().getTopId() + "'"
				);
		String whereNotIn = SqlUtil.whereNotIn("topId", "topId", topiclist);
		where += "and " + whereNotIn + " order by topId asc";
		
		return where;
	}

	/**
	 * 
	 * 选择课题转让跳转结果 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-1 上午09:41:20
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="confirmtransfer.do")
	public ModelAndView confirmTransfer(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) throws VerifyException{
		String stuId = (String) request.getSession().getAttribute("stuId");
		String userId = (String) request.getSession().getAttribute("user_id");
		String topId = request.getParameter("id");
		String toId 	= request.getParameter("userid");
		Department dept = (Department) request.getSession().getAttribute("dept");
		if(Verify.isEmpty(topId)) {
			throw new VerifyException("课题编号不能为空");
		}
		if(Verify.isEmpty(toId)) {
			throw new VerifyException("接收课题的学生不能为空");
		}
		_assignTransTime(request, dept.getDeptId());
		String where = "from Selectfirst where deptId = '" + dept.getDeptId() + 
				"' AND stuId = '" + toId + "' AND selStatus IN ('0', '1') ";
		String topicwhere = "from Selectfirst where deptId = '" + dept.getDeptId() + 
				"' AND tbtopic.topId = '" + topId + "' AND selStatus IN ('0', '1') ";
		Selectfirst selectfirst = selectfirstDao.getOne(where);
		if(!Verify.isEmpty(selectfirst)) {
			throw new VerifyException("该同学已选课题或正在等待指导老师确认");
		}
		List<Selectfirst> sList = selectfirstDao.getAll(topicwhere);
		if(!Verify.isEmpty(sList)) {
			throw new VerifyException("该课题正在等待指导老师确认");
		}
		Tbtopic topic = tbtopicService.getByTopId(topId);
		selectfirst = new Selectfirst();
		selectfirst.setTbtopic(topic);
		selectfirst.setStuId(toId);
		selectfirst.setSelKnowing(null);
		selectfirst.setSelStatus("0");
		selectfirst.setTeaId(null);
		selectfirst.setDeptId(dept.getDeptId());
		
		selectfirstDao.save(selectfirst);
		_sendMessageToTea(userId, stuId, "课题转让", " 向你转让课题，请注意查看选择课题模块内容！");
		
		request.setAttribute("message", "课题转让成功，请联系该同学填写相关内容及选择指导教师！");
		return new ModelAndView("user/student/transfer-ok");
	}
	
	/**
	 *  
	 * 加载Pagination 
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

	/**
	 * 
	 * enter student topic edit view 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-12-29 下午04:58:45
	 * @return ModelAndView
	 */
	@RequestMapping(value="stueditview.do")
	public ModelAndView stuEditView(HttpServletRequest request, ModelMap modelMap){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/user/topic/stu_lookTopic.do");
		String topId = request.getParameter("id");
		try {
			Reviewresult reviewresult = reviewresultService.getRecordByWhere("from Reviewresult where topId = '" + topId + "'");
			Tbtopic topic = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + topId + "'");
			if(Verify.isEmpty(reviewresult)){
				if(!Verify.isEmpty(topic)){
					modelMap.put("topic", topic);
					mav.setViewName("user/student/topic-edit");
				}else{
					request.setAttribute("message", "课题不存在！");
				}
			}else{
				request.setAttribute("message", "对不起，当前不可进行课题修改操作！");
			}			
		} catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
			
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 
	 * the form about student edit topic 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-12-29 下午05:00:41
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="stuedit.do")
	public ModelAndView stuEdit(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/topic/stu_lookTopic.do");
		String topId = request.getParameter("topId");
		String topName = request.getParameter("topName");
		Tbtopic topic = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + topId + "'");
		if(Verify.isEmpty(topic)) {
			throw new VerifyException("课题已不存在！");
		}
		if(!_verifyData(request)) {
			throw new VerifyException("请将课题信息完善！" + request.getAttribute("message"));
		}
		topic.setTopName(topName);
		topic.setTopContent(request.getParameter("topContent"));
		topic.setTopKeywords(request.getParameter("topkeywords"));
		topic.setTopType(request.getParameter("topType"));
		topic.setTopSource(request.getParameter("source"));
		try {
			tbtopicService.editOneRecord(topic);
			request.setAttribute("message", "课题修改成功！");
		} catch (Exception e) {
			throw new VerifyException("课题修改失败！");
		}
		
		return mav;
	}
	
	/**
	 * 
	 * 判断课题是否能进行编辑 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-10 下午03:45:55
	 * @return boolean
	 */
	private boolean _assignEditOrNot(HttpServletRequest request) throws VerifyException{
		String topId = request.getParameter("id");
		String where = "from Reviewresult where topId = '" + topId + "'";
		List<Reviewresult> list = reviewresultService.getAllRowsByWhere(where);
		if(Verify.isEmpty(list)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * teacher edit topic judge 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-12-29 下午05:04:14
	 * @return ModelAndView
	 * @throws VerifyException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "teaeditview.do")
	public ModelAndView teaEditView(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/user/topic/tea_lookTopic.do");
		String topId = request.getParameter("id");
		try {
			if(_assignEditOrNot(request)){
				Tbtopic topic = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + topId + "'");
				if(!Verify.isEmpty(topic)){
					List<Tbtopic> sontopiclist = tbtopicService.getAll("from Tbtopic where topId like '%" + topId + "%' AND topId <> '" + topId + "'");
					modelMap.put("topic", topic);
					modelMap.put("sontopiclist", sontopiclist);
					mav.setViewName("user/teacher/topic-edit");
				}else{
					request.setAttribute("message", "课题不存在！");
				}
			}else{
				request.setAttribute("message", "当前课题不可进行编辑操作！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new VerifyException("服务器繁忙，请稍后再试");
		}
		
		return mav;
	}
	/*public void teaEditView(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String topId = request.getParameter("topid");
		try {
			if(!_assignEditOrNot(request)){
				HResponse.errorJSON("当前课题不能进行编辑操作！", response);
				return;
			}
			Tbtopic topic = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + topId + "'");
			if(Verify.isEmpty(topic)){
				HResponse.errorJSON("课题已不存在！", response);
				return;
			}
			
			HResponse.okJSON(response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON("服务器繁忙，请稍后再试！", response);
		}
		
	}*/
	
	/**
	 * 
	 * the form about teacher edit topic 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-12-29 下午05:07:01
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="teaedit.do")
	public ModelAndView teaEdit(HttpServletRequest request, HttpServletResponse response) throws VerifyException{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:/user/topic/tea_lookTopic.do");
		
		String topId    = request.getParameter("topid");
		String userId   = (String) request.getSession().getAttribute("user_id");
		Department dept = (Department) request.getSession().getAttribute("dept");
		
		Tbtopic topic   = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + topId + "'");
		if(Verify.isEmpty(topic)){
			throw new VerifyException("课题已不存在!");
		}
		if(!_verifyData(request)){
			throw new VerifyException((String)request.getAttribute("message"));
		}
		
		int oldnum = topic.getTopNumber();
		int curnum = Integer.parseInt(request.getParameter("topNum"));

		topic.setTopName(request.getParameter("topName"));
		topic.setTopContent(request.getParameter("topContent"));
		topic.setTopKeywords(request.getParameter("topKeywords"));
		topic.setTopType(request.getParameter("topType"));
		topic.setTopSource(request.getParameter("topSource"));
		topic.setTopNumber(curnum);
		
		_dealSontopic(oldnum, curnum, userId, topId, dept.getDeptId(), request);
		
		try {
			tbtopicService.editOneRecord(topic);
			request.setAttribute("message", "课题修改成功！");
		} catch (Exception e) {
			throw new VerifyException("课题修改失败！");
		}
		return mav;
	}

	/**
	 * 
	 * edit son topic 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @param deptId 
	 * @param topId2 
	 * @param request 
	 * @date 2015-1-12 下午04:58:59
	 * @return void
	 * @throws VerifyException 
	 */
	private void _dealSontopic(int oldnum, int curnum, String userId, String topId, String deptId, HttpServletRequest request) throws VerifyException {
		String content   = request.getParameter("topContent");
		String type      = request.getParameter("topType");
		String source    = request.getParameter("topSource");
		String keywords  = request.getParameter("topKeywords");
		if(oldnum < curnum){
			if(oldnum == 1){
				for(int i = oldnum; i <= curnum; i++){
					_saveSonTopic((topId + i), request.getParameter(("sTName" + i)), userId, topId, deptId, content, type, source, keywords);
				}
			}else{
				for(int i = 1; i <= oldnum; i++){
					_editSonTopic(request, i, content, type, source, keywords );
				}
				for(int i = (oldnum + 1); i <= curnum; i++){
					_saveSonTopic((topId + i), request.getParameter("sTName" + i), userId, topId, deptId, content, type, source, keywords);
				}
			}
		}
		if(oldnum > curnum){
			if(curnum == 1){
				for (int i = 1; i <= oldnum; i++) {
					_deleteSontopic(request, i);
				}
			}else{
				for(int i = 1; i <= curnum; i++){
					_editSonTopic(request, i, content, type, source, keywords );
				}
				for(int i = (curnum + 1); i <= oldnum; i++){
					_deleteSontopic(request, i);
				}
			}
		}
		if(oldnum == curnum){
			for(int i = 1; i <= oldnum; i++){
				_editSonTopic(request, i, content, type, source, keywords );
			}
		}
	}

	/**
	 * database operation for delete son topic 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-12 下午05:55:01
	 * @return void
	 * @throws VerifyException 
	 */
	private void _deleteSontopic(HttpServletRequest request, int i) throws VerifyException {
		String sonId = request.getParameter(("sonId" + i));
		Tbtopic topic = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + sonId + "'");
		tbtopicService.remove(topic);
	}

	/**
	 * 
	 * database operation for editing son topic 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2015-1-12 下午05:30:57
	 * @return void
	 * @throws VerifyException 
	 */
	private void _editSonTopic(HttpServletRequest request, int i, String content, String type, String source, String keywords) throws VerifyException {
		String sonId = request.getParameter(("sonId" + i));
		Tbtopic topic = tbtopicService.getRecordByWhere("from Tbtopic where topId = '" + sonId + "'");
		topic.setTopName(request.getParameter(("sTName" + i)));
		topic.setTopContent(content);
		topic.setTopType(type);
		topic.setTopSource(source);
		topic.setTopKeywords(keywords);
		try {
			tbtopicService.editOneRecord(topic);
		} catch (Exception e) {
			throw new VerifyException("子课题修改失败！");
		}
	}

}
