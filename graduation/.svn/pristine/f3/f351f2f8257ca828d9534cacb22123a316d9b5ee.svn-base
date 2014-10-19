package com.mvc.controller.admin;

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
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Apply;
import com.mvc.entity.Department;
import com.mvc.entity.LinkeddataApplyTopicinfo;
import com.mvc.entity.Opentopicinfo;
import com.mvc.entity.Opentopicscore;
import com.mvc.entity.Room;
import com.mvc.entity.Student;
import com.mvc.entity.Tbclass;
import com.mvc.entity.Teacher;
import com.mvc.service.ApplyService;
import com.mvc.service.LinkeddataApplyTopicinfoService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.RoomService;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TeacherService;

/**
 * 开题答辩控制器
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/opentopicinfo")
public class OpentopicinfoController {

	@Autowired
	private OpentopicinfoService opentopicinfoService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TbclassService tbclassService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private OpentopicscoreService opentopicscoreService;
	
	@Autowired
	private LinkeddataApplyTopicinfoService linkeddataApplyTopicinfoService;
	
	private Pagination pagination;
	private List<Opentopicinfo> list = new ArrayList<Opentopicinfo>();
	private List<Room> roomList 	= new ArrayList<Room>();
	private List<Student> stuList 	= new ArrayList<Student>();
	private List<Teacher> teaList 	= new ArrayList<Teacher>();
	private List<Apply> applyList	= new ArrayList<Apply>();
	
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

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

	public List<Student> getStuList() {
		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	public List<Teacher> getTeaList() {
		return teaList;
	}

	public void setTeaList(List<Teacher> teaList) {
		this.teaList = teaList;
	}

	public List<Apply> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<Apply> applyList) {
		this.applyList = applyList;
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
	 * 开题答辩首页
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-5 下午05:35:05
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView index(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/opentopicinfo/list");
		Department department = (Department) request.getSession().getAttribute("department");
		String date 	= request.getParameter("date");
		String place 	= request.getParameter("place");
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
		String where = "from Opentopicinfo where departmentId = '" + 
			department.getDeptId() + "' and status = '1' ";
		if(!Verify.isEmpty(date)) {
			where += " AND opiDate LIKE '%" + date + "%' ";
			request.setAttribute("date", date);
		}
		if(!Verify.isEmpty(place)) {
			where += " AND opiPlace LIKE '%" + place + "%' ";
			request.setAttribute("place", place);
		}
		where += " order by opiPlace asc";
		list = opentopicinfoService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Opentopicinfo>) opentopicinfoService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
		mav.addObject("department", department);
		_assignStudentListMap(list, request);
		_assignHeadermanListMap(list, request);
		_assignJudgeListMap(list, request);
		
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
	 * 加载组长列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:55:13
	 * @return void
	 */
	private void _assignHeadermanListMap(List<Opentopicinfo> data, HttpServletRequest request)
	{
		if(null == data || 1 > data.size()) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("teaId", "headerman", data);
		List<Teacher> tempList 	= teacherService.getAllRows("from Teacher where " + whereIn);
		if(null == tempList || 1 > tempList.size()) {
			return;
		}
		
		request.setAttribute("headerman_map", ArrayUtil.turnListToMap("teaId", "teaName", tempList));
	}
	
	/**
	 * 加载评审教师列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午04:45:05
	 * @return void
	 */
	private void _assignJudgeListMap(List<Opentopicinfo> data, HttpServletRequest request)
	{
		if(null == data || 1 > data.size()) {
			return;
		}
		String[] ids = null;
		String tempString = "";
		for(int i = 0; i < data.size(); i ++) {
			tempString += data.get(i).getJudge() + ",";
		}
		ids 	= tempString.split(",");
		StringBuffer id 	= new StringBuffer("(");
		for(int i = 0; i < ids.length; i ++) {
			id.append("'" + ids[i] + "',");
		}
		id.deleteCharAt(id.length() - 1);
		id.append(")").toString();
		List<Teacher> tempList 	= teacherService.getAllRows("from Teacher where teaId IN " + id);
		if(null == tempList || 1 > tempList.size()) {
			return;
		}
		
		request.setAttribute("judge_map", ArrayUtil.turnListToMap("teaId", "teaName", tempList));
	}
	
	/**
	 * 安排开题答辩
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-15 下午10:00:39
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/opentopicinfo/add");
		_assignRoom(request, modelMap);
		
		
		return mav;
	}
	
	/**
	 * 答辩详细安排视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 上午10:09:44
	 * @return ModelAndView
	 */
	@RequestMapping(value="/orderroom.do")
	public ModelAndView orderRoom(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		Room room 	= roomService.getOneRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(room)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.setViewName("admin/opentopicinfo/orderroom");
		mav.addObject("room", room);
		
		return mav;
	}
	
	/**
	 * 答辩详细安排实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午04:49:47
	 * @return ModelAndView
	 */
	@RequestMapping(value="/order.do")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String date 	= request.getParameter("date");
		String place 	= request.getParameter("place");
		String students = request.getParameter("stu.id");
		String judge 	= request.getParameter("tea.id");
		String applyids	= request.getParameter("stu.applyid");
		String header 	= request.getParameter("headername");
		if(!_verifyOrderData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String[] stud	 = students.split(",");
		String[] applyid = applyids.split(",");
		String hql = "from Opentopicinfo where departmentId = '" + department.getDeptId() + 
			"' and opiPlace = '" + place + "' and status = '1'";
		List<Opentopicinfo> tempList = opentopicinfoService.getAllRowsByWhere(hql);
		int number 	= 0;
		if(!Verify.isEmpty(tempList)) {
			number 	= tempList.size();
		}
		try {
			for(int i = 0; i < stud.length; i ++ ) {
				Opentopicinfo opentopicinfo = new Opentopicinfo();
				opentopicinfo.setName("");
				opentopicinfo.setStuId(stud[i].toString());
				opentopicinfo.setDepartmentId(department.getDeptId());
				opentopicinfo.setOpiDate(date);	
				opentopicinfo.setOpiPlace(place);
				opentopicinfo.setOpiNumber((++number ) + "");
				opentopicinfo.setJudge(judge);
				opentopicinfo.setHeaderman(header);
				opentopicinfo.setContent("");
				opentopicinfo.setStatus("1");
				opentopicinfo.setCreateTime(HResponse.formatDateTime(new Date()));
				int topicinfoId = opentopicinfoService.addOneReturn(opentopicinfo);
				if(topicinfoId == 0) {
					mav.addObject("message", "服务器繁忙，请稍后再试");
					mav.addObject("statusCode", 300);
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
				request.setAttribute("applyid", applyid[i]);
				request.setAttribute("topicinfoid", topicinfoId);
				int linkeddataId = _assignLinkeddataApplyInfo(request);
				if(linkeddataId == 0) {
					mav.addObject("message", "服务器繁忙，请稍后再试");
					mav.addObject("statusCode", 300);
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
				_assignUpdateApplyStatus(applyid[i], "2");
			}
			
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "安排完成", "opentopicinfo", "/admin/opentopicinfo/list.do");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		} catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "服务器繁忙，请稍后再试", "", "");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		
	}
	
	/**
	 * 加载答辩申请与开题答辩信息关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @return
	 */
	private int _assignLinkeddataApplyInfo(HttpServletRequest request) 
	{
		LinkeddataApplyTopicinfo linkeddataApplyTopicinfo = new LinkeddataApplyTopicinfo();
		linkeddataApplyTopicinfo.setItemId(request.getAttribute("applyid").toString());
		linkeddataApplyTopicinfo.setRelId(request.getAttribute("topicinfoid").toString());
		linkeddataApplyTopicinfo.setExtend("0");
		linkeddataApplyTopicinfo.setCreateTime(HResponse.formatDateTime(new Date()));
		
		return linkeddataApplyTopicinfoService.addOneReturn(linkeddataApplyTopicinfo);
	}

	/**
	 * 验证开题答辩安排提交的数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午07:14:54
	 * @return boolean
	 */
	private boolean _verifyOrderData(HttpServletRequest request) {
		String date 	= request.getParameter("date");
		String place 	= request.getParameter("place");
		String students = request.getParameter("stu.id");
		if(Verify.isEmpty(date)) {
			request.setAttribute("message", "答辩时间不能为空");
			
			return false;
		}
		if(Verify.isEmpty(place)) {
			request.setAttribute("message", "答辩教室不能为空");
			
			return false;
		}
		if(Verify.isEmpty(students)) {
			request.setAttribute("message", "答辩学生不能为空");
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 修改申请表状态
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午09:11:51
	 * @return void
	 */
	private void _assignUpdateApplyStatus(String applyId, String status)
	{
		Apply apply = applyService.getOneRecordById(Integer.parseInt(applyId));
		if(Verify.isEmpty(apply)) {
			return;
		}
		apply.setStatus(status);
		applyService.editOneApply(apply);
	}

	/**
	 * lookup查找功能
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 上午11:30:03
	 * @return ModelAndView
	 */
	@RequestMapping(value="/lookup.do")
	public ModelAndView lookUp(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/opentopicinfo/lookup");
		Department department = (Department) request.getSession().getAttribute("department");
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
		String where = "from Apply where departmentId = '" + department.getDeptId() + 
				"' and type = '1' and pass = '2' and status = '1' order by createTime desc ";
		applyList = applyService.getAllRecordByPages(where, pagination);
		if(applyList == null || applyList.size() < 1) {
			RequestSetAttribute.setPageAttribute("", pagination, applyList, modelMap);
			
			return mav;
		}
		if(this.applyList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			applyList = (List<Apply>) applyService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, applyList, modelMap);
		_assignStudentListMap(applyList, "stuId", "userId", request);
		
		return mav;
	}
	
	/**
	 * 加载学生列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param data
	 * @param filed
	 * @param exFiled
	 * @param request
	 */
	private void _assignStudentListMap(List<?> data, String filed, String exFiled, HttpServletRequest request)
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn(filed, exFiled, data);
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
	}
	
	/**
	 * lookup查找教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-18 上午11:49:50
	 * @return ModelAndView
	 */
	@RequestMapping(value="/lookuptea.do")
	public ModelAndView lookUpTea(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/opentopicinfo/lookuptea");
		Department department = (Department) request.getSession().getAttribute("department");
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
		String hql = "from Opentopicinfo where departmentId = '" + 
			department.getDeptId() + "' and status = '1')";
		Opentopicinfo op = null;
		StringBuffer whereIn = new StringBuffer("(");
		List<Opentopicinfo> tempList = opentopicinfoService.getAllRowsByWhere(hql);
		String where = null;
		if(tempList == null || tempList.size() < 1) {
			where = "from Teacher where 1 = 1";
		}else {
			for(int i = 0; i < tempList.size(); i ++) {
				op = tempList.get(i);
				if(!Verify.isEmpty(op) && !Verify.isEmpty(op.getJudge())) {
					String[] judges = op.getJudge().split(",");
					for(int j = 0; j < judges.length; j ++) {
						whereIn.append("'" + judges[j] + "',");
					}
				}
			}
			whereIn.deleteCharAt(whereIn.length() - 1);
			whereIn.append(")").toString();
			 where = "from Teacher where teaId NOT IN " + whereIn;
		}
		teaList = teacherService.getAllRecordByPages(where, pagination);
		if(teaList == null || teaList.size() < 1) {
			RequestSetAttribute.setPageAttribute("", pagination, teaList, modelMap);
			
			return mav;
		}
		if(this.teaList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			teaList = (List<Teacher>) teacherService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, teaList, modelMap);
		StudentController._assignSexMap(request);
		TeacherController.assignTeacherposMap(request);
		
		return mav;
	}
	
	/**
	 * 加载班级列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午02:38:53
	 * @return void
	 */
	@SuppressWarnings("unused")
	private void _assignTbclassListMap(List<Student> data, HttpServletRequest request)
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("claId", "claId", data);
		List<Tbclass> tempList = tbclassService.getAllRows("from Tbclass where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("cla_Id_map", ArrayUtil.turnListToMap("claId", "claName", tempList));
	}
	
	/**
	 * 加载教室列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-16 下午09:32:17
	 * @return void
	 */
	private void _assignRoom(HttpServletRequest request, ModelMap modelMap)
	{
		Department department = (Department) request.getSession().getAttribute("department");
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
		String where = "from Room where parentId = '" + department.getDeptId() + "' and usable = '1'";
		roomList = roomService.getAllRecordByPages(where, pagination);
		if(roomList == null || roomList.size() < 1) {
			RequestSetAttribute.setPageAttribute("", pagination, roomList, modelMap);
			
			return ;
		}
		if(this.roomList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			roomList = (List<Room>) roomService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, roomList, modelMap);
		RoomController._assignUsableMap(request);
	}
	
	/**
	 * 开题答辩成绩
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-29 下午06:54:05
	 * @return ModelAndView
	 */
	@RequestMapping(value="/optscore.do")
	public ModelAndView optScore(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/opentopicinfo/score-search");
		
		return mav;
	}
	
	/**
	 * 开题答辩成绩列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-5 下午10:09:32
	 * @return ModelAndView
	 */
	@RequestMapping(value="/totalrows.do")
	public ModelAndView totalRows(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/opentopicinfo/score-list");
		Department department 	= (Department) request.getSession().getAttribute("department");
		String start 			= request.getParameter("start");
		String end 				= request.getParameter("end");
		String where 			= "from Opentopicscore where departmentId = '" + department.getDeptId() + "'";
		if(!Verify.isEmpty(start) && !Verify.isEmpty(end)) {
			where += "AND createTime between '" + start + 
				" 00:00:00' AND '" + end + " 23:59:59'";
		}
		where += " order by createTime desc";
		mav.addObject("scoreList", opentopicscoreService.getAllRowsByWhere(where));
		_assignStudentListMap2(opentopicscoreService.getAllRowsByWhere(where), request);
		return mav;
	}
	
	private void _assignStudentListMap2(List<Opentopicscore> data, HttpServletRequest request) {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("stuId", "studentId", data);
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
	}

	/**
	 * 公用的加载教室列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午10:01:52
	 * @return void
	 */
	public  void roomList(HttpServletRequest request, ModelMap modelMap)
	{
		System.out.println("is  comein");
		_assignRoom(request, modelMap);
	}
}
