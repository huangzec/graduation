package com.mvc.controller.admin;

import java.util.ArrayList;
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
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Teacher;
import com.mvc.entity.Topicorderreview;
import com.mvc.exception.VerifyException;
import com.mvc.service.ApplyService;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradethreeService;
import com.mvc.service.GradetwoService;
import com.mvc.service.LinkeddataApplyTopicinfoService;
import com.mvc.service.OpentopicinfoService;
import com.mvc.service.OpentopicscoreService;
import com.mvc.service.RoomService;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;
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
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private GradeoneService gradeoneService;
	
	@Autowired
	private GradetwoService gradetwoService;
	
	@Autowired
	private GradethreeService gradethreeService;
	
	private Pagination pagination;
	private List<Opentopicinfo> list = new ArrayList<Opentopicinfo>();
	private List<Room> roomList 	= new ArrayList<Room>();
	private List<Student> stuList 	= new ArrayList<Student>();
	private List<Teacher> teaList 	= new ArrayList<Teacher>();
	private List<Apply> applyList	= new ArrayList<Apply>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 20;//每页显示多少条
	
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
	 * 注册组别Map
	 */
	private static LinkedHashMap<String, String> _begroupMap 	= new LinkedHashMap<String, String>(){{
		put("1", "第一组");
		put("2", "第二组");
		put("3", "第三组");
		put("4", "第四组");
		put("5", "第五组");
		put("6", "第六组");
		put("7", "第七组");
		put("8", "第八组");
		put("9", "第九组");
		put("10", "第十组");
	}};
	
	/**
	 * 注册状态Map
	 */
	private static LinkedHashMap<String, String> _statusMap 	= new LinkedHashMap<String, String>(){{
		put("1", "未评");
		put("2", "已评");
	}};
	
	/**
	 * 加载组别Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void _assignBeGroupMap(HttpServletRequest request)
	{
		request.setAttribute(
				"begroup_map", 
				MapUtil.makeLinkedMapMap(_begroupMap)
				);
	}
	
	/**
	 * 注册组别列表Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void _assignBeGroupListMap(HttpServletRequest request)
	{
		request.setAttribute(
				"begroup_list", 
				MapUtil.makeLinkedListMap(_begroupMap)
				);
	}
	
	/**
	 * 加载状态Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void _assignStatusMap(HttpServletRequest request)
	{
		request.setAttribute(
				"status_map", 
				MapUtil.makeLinkedMapMap(_statusMap)
				);
	}
	
	/**
	 * 加载状态列表Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void _assignStatusListMap(HttpServletRequest request)
	{
		request.setAttribute(
				"status_list", 
				MapUtil.makeLinkedListMap(_statusMap)
				);
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
		String status 	= request.getParameter("status");
		String begroup 	= request.getParameter("begroup");
		String ingrade 	= request.getParameter("ingrade");
		String profess 	= request.getParameter("profess");
		String inclass 	= request.getParameter("inclass");
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
			department.getDeptId() + "'  ";//and status = '1'
		if(!Verify.isEmpty(status)) {
			where += " AND status = '" + status.trim() + "' ";
			request.setAttribute("status", status);
		}
		if(!Verify.isEmpty(date)) {
			where += " AND opiDate LIKE '%" + date + "%' ";
			request.setAttribute("date", date);
		}
		if(!Verify.isEmpty(place)) {
			where += " AND opiPlace LIKE '%" + place + "%' ";
			request.setAttribute("place", place);
		}
		if(!Verify.isEmpty(begroup)) {
			where += " AND begroup = '" + begroup.trim() + "' ";
			request.setAttribute("begroup", begroup);
		}
		if(!Verify.isEmpty(ingrade) && !Verify.isEmpty(profess) && !Verify.isEmpty(inclass)) {
			where += " AND stuId IN (select s.stuId from Student s where s.claId = '" + inclass.trim() + "' )";
			request.setAttribute("ingrade", ingrade);
			request.setAttribute("profess", profess);
			request.setAttribute("inclass", inclass);
		}else if(!Verify.isEmpty(ingrade) && !Verify.isEmpty(profess)) {
			where += " AND stuId IN (select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId = '" + profess.trim() + "' ))";
			request.setAttribute("ingrade", ingrade);
			request.setAttribute("profess", profess);
		}else if(!Verify.isEmpty(ingrade)) {
			where += " AND stuId IN (select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.deptId = '" + department.getDeptId() + 
					"' AND p.graId = " + Integer.parseInt(ingrade) + " ) ))";
			request.setAttribute("ingrade", ingrade);
		}
		where += " order by opiPlace asc, opiNumber asc, status asc, createTime desc ";
		try {
			_assignBeGroupListMap(request);
			_assignStatusMap(request);
			_assignStatusListMap(request);
			_assignTbgradeList(request);
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
			_assignBeGroupMap(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	private void _assignStudentListMap(List<Opentopicinfo> data, HttpServletRequest request) throws VerifyException {
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
	 * 加载学生Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStudentMapMap(Opentopicinfo data, HttpServletRequest request) throws VerifyException {
		if(Verify.isEmpty(data)) {
			return;
		}
		String where = "from Student where stuId = '" + data.getStuId() + "' ";
		List<Student> tempList 	= studentService.getAllRows(where);
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
	 * @throws VerifyException 
	 */
	private void _assignHeadermanListMap(List<Opentopicinfo> data, HttpServletRequest request) throws VerifyException
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
	 * 加载组长MapMap
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:55:13
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignHeadermanMapMap(Opentopicinfo opentopicinfo, HttpServletRequest request) throws VerifyException
	{
		if(null == opentopicinfo) {
			return;
		}
		String where = "from Teacher where teaId = '" + opentopicinfo.getHeaderman() + "' ";
		
		List<Teacher> tempList 	= teacherService.getAllRows(where);
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
	 * @throws VerifyException 
	 */
	private void _assignJudgeListMap(List<Opentopicinfo> data, HttpServletRequest request) throws VerifyException
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
	 * 加载评审教师Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignJudgeMapMap(Opentopicinfo opentopicinfo, HttpServletRequest request) throws VerifyException 
	{
		if(Verify.isEmpty(opentopicinfo) || Verify.isEmpty(opentopicinfo.getJudge())) {
			return;
		}
		String[] ids = null;
		ids 	= opentopicinfo.getJudge().split(",");
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, ModelMap modelMap) throws VerifyException
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
		_assignBeGroupListMap(request);
		
		return mav;
	}
	
	/**
	 * 答辩详细安排实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午04:49:47
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/order.do")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String date 	= request.getParameter("date");
		String place 	= request.getParameter("place");
		String students = request.getParameter("stu.id");
		String judge 	= request.getParameter("tea.id");
		String applyids	= request.getParameter("stu.applyid");
		String header 	= request.getParameter("headername");
		String begroup 	= request.getParameter("begroup");
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
				opentopicinfo.setBegroup(begroup);
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
	 * @throws VerifyException 
	 */
	private void _assignUpdateApplyStatus(String applyId, String status) throws VerifyException
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
		try {
			String where = "from Apply where departmentId = '" + department.getDeptId() + 
					"' and type = '1' and pass = '2' and status = '1' order by createTime asc ";
			applyList = applyService.getAllRows(where);			
			RequestSetAttribute.setPageAttribute("", pagination, applyList, modelMap);
			_assignStudentListMap(applyList, "stuId", "userId", request);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap(List<?> data, String filed, String exFiled, HttpServletRequest request) throws VerifyException
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn(filed, exFiled, data);
		List<Student> tempList 	= studentService.getAllRows("from Student where status = '1' AND " + whereIn);
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
		String place 	= request.getParameter("place");
		String pos 		= request.getParameter("pos");
		String name 	= request.getParameter("name");
		if(Verify.isEmpty(place)) {
			return mav;
		}
		try {
			String hql = "from Opentopicinfo where departmentId = '" + 
					department.getDeptId() + "' and status = '1' AND opiPlace != '" + place.trim() + "' ";
			Opentopicinfo op = null;
			StringBuffer whereIn = new StringBuffer("(");
			List<Opentopicinfo> tempList = opentopicinfoService.getAllRowsByWhere(hql);
			String where = null;
			if(tempList == null || tempList.size() < 1) {
				where = "from Teacher where deptId = '" + department.getDeptId() + "' AND status = '1' ";
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
				 where = "from Teacher where deptId = '" + department.getDeptId() + 
					 "' AND status = '1' AND  teaId NOT IN " + whereIn;
			}
			if(!Verify.isEmpty(pos)) {
				/**
				 * 按职称查询
				 */
				if(pos.trim().equals("0")) {
					where += " AND teaPos IN ('0', '1', '2', '3') ";
				}else if(pos.trim().equals("1")) {
					where += " AND teaPos IN ('1', '2', '3') ";
				}else if(pos.trim().equals("2")) {
					where += " AND teaPos IN ('2', '3') ";
				}else if(pos.trim().equals("3")) {
					where += " AND teaPos = '3' ";
				}
				mav.addObject("pos", pos);
			}
			if(!Verify.isEmpty(name)) {
				where += " AND teaName LIKE '%" + name + "%' ";
				mav.addObject("name", name);
			}
			where += " order by teaId asc ";
			teaList = teacherService.getAllRows(where);
			RequestSetAttribute.setPageAttribute("", pagination, teaList, modelMap);
			StudentController._assignSexMap(request);
			TeacherController.assignTeacherposMap(request);
			mav.addObject("place", place);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 加载班级列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午02:38:53
	 * @return void
	 * @throws VerifyException 
	 */
	@SuppressWarnings("unused")
	private void _assignTbclassListMap(List<Student> data, HttpServletRequest request) throws VerifyException
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
	 * @throws VerifyException 
	 */
	private void _assignRoom(HttpServletRequest request, ModelMap modelMap) throws VerifyException
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
		try {
			_assignTbgradeList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		Department department 	= (Department) request.getSession().getAttribute("department");
		String where 	= "from Tbgrade where deptId = '" + department.getDeptId() + 
				"' order by graNumber desc ";
		
		request.setAttribute("gradeList", tbgradeService.getAllRowsByWhere(where));
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
		String grade 			= request.getParameter("gradeid");
		String profession 		= request.getParameter("professid");
		String tbclass 			= request.getParameter("claid");
		String where 			= "from Opentopicscore where departmentId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession) && !Verify.isEmpty(tbclass)) {
			where += " AND studentId IN (" +
					"select s.stuId from Student s where s.claId = (" +
					"select c.claId from Tbclass c where c.claId = '" + tbclass.trim() + "' AND c.proId = (" +
					"select p.proId from Profession p where p.proId = '" + profession.trim() + "' AND p.deptId = '" + 
					department.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}else if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession)) {
			where += " AND studentId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.proId = '" + profession.trim() + "' AND p.deptId = '" + 
					department.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}else if(!Verify.isEmpty(grade)) {
			where += " AND studentId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.deptId = '" + 
					department.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}
		if(!Verify.isEmpty(start) && !Verify.isEmpty(end)) {
			where += " AND createTime between '" + start + 
				" 00:00:00' AND '" + end + " 23:59:59'";
		}
		where += " order by createTime desc";
		try {
			mav.addObject("scoreList", opentopicscoreService.getAllRowsByWhere(where));
			_assignStudentListMap2(opentopicscoreService.getAllRowsByWhere(where), request);
			_assignTbgradeList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 加载学生列表Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param data
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap2(List<Opentopicscore> data, HttpServletRequest request) throws VerifyException {
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
	 * @throws VerifyException 
	 */
	public  void roomList(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		System.out.println("is  comein");
		_assignRoom(request, modelMap);
	}
	
	/**
	 * 开题答辩概况
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/optresult.do")
	public ModelAndView optResult(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/opt-result-search");
		try {
			_assignTbgradeList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 开题答辩概况
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/totalresult.do")
	public ModelAndView totalResult(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/opt-result-search-list");
		Department department 	= (Department) request.getSession().getAttribute("department");
		String gradeid 			= request.getParameter("gradeid");
		String professid 		= request.getParameter("professid");
		String claid 			= request.getParameter("claid");
		if(Verify.isEmpty(claid)) {
			///如果班级编号为空
			return mav;
		}
		//查询该班级的学生
		String stuWhere = "from Student where claId = '" + claid + "' order by stuId asc ";
		//该班级的开题答辩信息
		String optinfo 	= "from Opentopicinfo where departmentId = '" + department.getDeptId() + "' ";
		try {
			//查找学生
			List<Student> stuList 	= studentService.getAllRows(stuWhere);
			if(!Verify.isEmpty(stuList)) {
				optinfo += " AND " + SqlUtil.whereIn("stuId", "stuId", stuList) + " order by createTime desc ";
			}
			//查找开题信息
			List<Opentopicinfo> optinfoList = opentopicinfoService.getAllRowsByWhere(optinfo);
			if(!Verify.isEmpty(optinfoList)) {
				//查找开题答辩分数
				String scoreWhere = "from Opentopicscore where " + SqlUtil.whereIn("studentId", "stuId", optinfoList);
				request.setAttribute("optscoreList", opentopicscoreService.getAllRowsByWhere(scoreWhere));
			}
			request.setAttribute("stuList", stuList);
			request.setAttribute("optinfoList", optinfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 毕业答辩表一成绩查询
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/tableoneresult.do")
	public ModelAndView tableOneResult(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/table-result-search");
		request.setAttribute("action", "tableonescore.do");
		request.setAttribute("message", "查看表一成绩");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 毕业答辩表二成绩查询
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/tabletworesult.do")
	public ModelAndView tableTwoResult(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/table-result-search");
		request.setAttribute("action", "tabletwoscore.do");
		request.setAttribute("message", "查看表二成绩");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}

	/**
	 * 毕业答辩表三成绩查询
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/tablethreeresult.do")
	public ModelAndView tableThreeResult(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/table-result-search");
		request.setAttribute("action", "tablethreescore.do");
		request.setAttribute("message", "查看表三成绩");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 查看表一分数
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/tableonescore.do")
	public ModelAndView tableOneScore(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/table-result-score-one");
		String claid 			= request.getParameter("claid");
		if(Verify.isEmpty(claid)) {
			///如果班级编号为空
			return mav;
		}
		String stuWhere = "from Student where claId = '" + claid + "' order by stuId asc ";
		String scoreWhere = "from Gradeone ";
		try {
			//查找学生
			List<Student> stuList 	= studentService.getAllRows(stuWhere);
			if(!Verify.isEmpty(stuList)) {
				scoreWhere += " where " + SqlUtil.whereIn("stuId", "stuId", stuList) + " order by createTime desc "; 
				request.setAttribute("scoreList", gradeoneService.getAllRowsByWhere(scoreWhere));
			}
			request.setAttribute("stuList", stuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 查看表三分数
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/tabletwoscore.do")
	public ModelAndView tableTwoScore(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/table-result-score-two");
		String claid 			= request.getParameter("claid");
		if(Verify.isEmpty(claid)) {
			///如果班级编号为空
			return mav;
		}
		String stuWhere = "from Student where claId = '" + claid + "' order by stuId asc ";
		String scoreWhere = "from Gradetwo ";
		try {
			//查找学生
			List<Student> stuList 	= studentService.getAllRows(stuWhere);
			if(!Verify.isEmpty(stuList)) {
				scoreWhere += " where " + SqlUtil.whereIn("stuId", "stuId", stuList) + " order by createTime desc "; 
				request.setAttribute("scoreList", gradetwoService.getAllRowsByWhere(scoreWhere));
			}
			request.setAttribute("stuList", stuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 查看表三分数
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/tablethreescore.do")
	public ModelAndView tableThreeScore(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/opentopicinfo/table-result-score-three");
		String claid 			= request.getParameter("claid");
		if(Verify.isEmpty(claid)) {
			///如果班级编号为空
			return mav;
		}
		String stuWhere = "from Student where claId = '" + claid + "' order by stuId asc ";
		String scoreWhere = "from Gradethree ";
		try {
			//查找学生
			List<Student> stuList 	= studentService.getAllRows(stuWhere);
			if(!Verify.isEmpty(stuList)) {
				scoreWhere += " where " + SqlUtil.whereIn("stuId", "stuId", stuList) + " order by createTime desc "; 
				request.setAttribute("scoreList", gradethreeService.getAllRowsByWhere(scoreWhere));
			}
			request.setAttribute("stuList", stuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 判断该组是否已经被安排了
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/abegroup.do")
	public void abegroup(HttpServletRequest request, HttpServletResponse response)
	{
		Department department 	= (Department) request.getSession().getAttribute("department");
		String place 	= request.getParameter("place");
		String begroup 	= request.getParameter("begroup");
		if(Verify.isEmpty(place)) {
			HResponse.errorJSON("答辩教室不能为空", response);
			
			return;
		}
		if(Verify.isEmpty(begroup)) {
			HResponse.errorJSON("组别不能为空", response);
			
			return;
		}
		try {
			String where = "from Opentopicinfo where departmentId = '" + department.getDeptId() + 
					"' AND opiPlace = '" + place.trim() + "' AND begroup = '" + 
					begroup + "' AND status = '1' ";
			Opentopicinfo opentopicinfo = opentopicinfoService.getRecordByWhere(where);
			if(!Verify.isEmpty(opentopicinfo)) {
				HResponse.errorJSON("该组已被安排，请安排其他组", response);
				
				return;
			}
			HResponse.okJSON(response);
		} catch (Exception e) {
			e.printStackTrace();
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
	
	/**
	 * 详细信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="detail.do")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("/admin/opentopicinfo/detail");
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			return mav;
		}
		try {
			Opentopicinfo opentopicinfo = opentopicinfoService.getOneRecordById(Integer.parseInt(id.trim()));
			mav.addObject("opentopicinfo", opentopicinfo);
			_assignJudgeMapMap(opentopicinfo, request);
			_assignHeadermanMapMap(opentopicinfo, request);
			_assignBeGroupMap(request);
			_assignStudentMapMap(opentopicinfo, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
}
