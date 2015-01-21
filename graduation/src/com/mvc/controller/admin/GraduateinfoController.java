package com.mvc.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.ExcelIO;
import com.mvc.common.HResponse;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Apply;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeall;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.LinkeddataApplyGraduateinfo;
import com.mvc.entity.Opentopicinfo;
import com.mvc.entity.Room;
import com.mvc.entity.Student;
import com.mvc.entity.Tbclass;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Teacher;
import com.mvc.exception.VerifyException;
import com.mvc.service.ApplyService;
import com.mvc.service.GradeallService;
import com.mvc.service.GraduateinfoService;
import com.mvc.service.LinkeddataApplyGraduateinfoService;
import com.mvc.service.ProfessionService;
import com.mvc.service.RoomService;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TeacherService;

/**
 * 毕业答辩控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/graduateinfo")
public class GraduateinfoController {

	@Autowired
	private GraduateinfoService graduateinfoService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private TbclassService tbclassService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private GradeallService gradeallService;
	
	@Autowired
	private LinkeddataApplyGraduateinfoService linkeddataApplyGraduateinfoService;
	
	private Pagination pagination;
	private List<Graduateinfo> list = new ArrayList<Graduateinfo>();
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

	public List<Graduateinfo> getList() {
		return list;
	}

	public void setList(List<Graduateinfo> list) {
		this.list = list;
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
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/graduateinfo/add");
		_assignRoom(request, modelMap);
		
		return mav;
	}
	
	/**
	 * 加载教室
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-23 下午03:36:32
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
		List<Room> roomList = roomService.getAllRecordByPages(where, pagination);
		if(roomList == null || roomList.size() < 1) {
			RequestSetAttribute.setPageAttribute("", pagination, roomList, modelMap);
			
			return ;
		}
		if(roomList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			roomList = (List<Room>) roomService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, roomList, modelMap);
		RoomController._assignUsableMap(request);
	}
	
	/**
	 * 安排教室
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-23 下午03:46:51
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
		mav.setViewName("admin/graduateinfo/orderroom");
		mav.addObject("room", room);
		OpentopicinfoController._assignBeGroupListMap(request);
		
		return mav;
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
		mav.setViewName("admin/graduateinfo/lookup");
		Department department = (Department) request.getSession().getAttribute("department");
		try {
			String where = "from Apply where departmentId = '" + department.getDeptId() + 
					"' and type = '2' and pass = '2' and status = '1' ";
			where += " AND userId IN (select stuId from Gradetwo)";
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
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
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
		mav.setViewName("admin/graduateinfo/lookuptea");
		Department department = (Department) request.getSession().getAttribute("department");
		String place 	= request.getParameter("place");
		String pos 		= request.getParameter("pos");
		String name 	= request.getParameter("name");
		if(Verify.isEmpty(place)) {
			return mav;
		}
		try {
			String hql = "from Graduateinfo where departmentId = '" + 
					department.getDeptId() + "' and status = '1' AND gdiPlace != '" + place.trim() + "' ";
			Graduateinfo grad = null;
			StringBuffer whereIn = new StringBuffer("(");
			List<Graduateinfo> tempList = graduateinfoService.getAllRowsByWhere(hql);
			String where = null;
			if(tempList == null || tempList.size() < 1) {
				where = "from Teacher where deptId = '" + department.getDeptId() + "' AND status = '1' ";
			}else {
				for(int i = 0; i < tempList.size(); i ++) {
					grad = tempList.get(i);
					if(!Verify.isEmpty(grad) && !Verify.isEmpty(grad.getJudge())) {
						String[] judges = grad.getJudge().split(",");
						for(int j = 0; j < judges.length; j ++) {
							whereIn.append("'" + judges[j] + "',");
						}
					}
				}
				whereIn.deleteCharAt(whereIn.length() - 1);
				whereIn.append(")").toString();
				 where = "from Teacher where deptId = '" + department.getDeptId() + "' AND teaId NOT IN " + whereIn;
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
		String applyids	= request.getParameter("stu.applyid");
		String judge 	= request.getParameter("tea.id");
		String header 	= request.getParameter("headername");
		String begroup 	= request.getParameter("begroup");
		if(!_verifyOrderData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String[] stud		= students.split(",");
		String[] applyid 	= applyids.split(",");
		String hql = "from Graduateinfo where departmentId = '" + department.getDeptId() + 
			"' and gdiPlace = '" + place + "' and status = '1'";
		List<Graduateinfo> tempList = graduateinfoService.getAllRowsByWhere(hql);
		int number 	= 0;
		if(!Verify.isEmpty(tempList)) {
			number 	= tempList.size();
		}
		try {
			for(int i = 0; i < stud.length; i ++ ) {
				Graduateinfo graduateinfo = new Graduateinfo();
				graduateinfo.setName("");
				graduateinfo.setStuId(stud[i].toString());
				graduateinfo.setDepartmentId(department.getDeptId());
				graduateinfo.setGdiDate(date);	
				graduateinfo.setGdiPlace(place);
				graduateinfo.setGdiNumber(++number);
				graduateinfo.setJudge(judge);
				graduateinfo.setHeaderman(header);
				graduateinfo.setBegroup(begroup);
				graduateinfo.setContent("");
				graduateinfo.setStatus("1");
				graduateinfo.setCreateTime(HResponse.formatDateTime(new Date()));
				int graduateinfoId = graduateinfoService.addOneReturn(graduateinfo);
				request.setAttribute("applyid", applyid[i]);
				request.setAttribute("graduateid", graduateinfoId + "");
				_assignLinkeddataApplyInfo(request);
				_assignUpdateApplyStatus(applyid[i], "2");
			}
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "安排完成", "graduateinfo", "/admin/graduateinfo/list.do");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		} catch (Exception e) {
			mav.addObject("message", "服务器繁忙，请稍后再试");
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
	}
	
	/**
	 * 加载答辩申请与毕业答辩信息关联关系
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignLinkeddataApplyInfo(HttpServletRequest request) throws VerifyException
	{
		LinkeddataApplyGraduateinfo linkeddataApplyGraduateinfo = new LinkeddataApplyGraduateinfo();
		linkeddataApplyGraduateinfo.setItemId(request.getAttribute("applyid").toString());
		linkeddataApplyGraduateinfo.setRelId(request.getAttribute("graduateid").toString());
		linkeddataApplyGraduateinfo.setExtend("0");
		linkeddataApplyGraduateinfo.setCreateTime(HResponse.formatDateTime(new Date()));
		linkeddataApplyGraduateinfoService.addOne(linkeddataApplyGraduateinfo);		
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
	 * 验证毕业答辩安排提交的数据
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
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		String graid 	= request.getParameter("graid");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(id));
		if(graduateinfo != null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "毕业答辩编号已存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		graduateinfo = new Graduateinfo();
		try{
			graduateinfoService.addOne(graduateinfo);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "graduateinfolist", "/admin/graduateinfo/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "添加失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 数据验证
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		if(id.trim().equals("")) {
			request.setAttribute("message", "毕业答辩编号不能为空");
			
			return false;
		}
		if(name.trim().equals("")) {
			request.setAttribute("message", "毕业答辩名称不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/graduateinfo/list");
		Department department = (Department) request.getSession().getAttribute("department");
		String date 	= request.getParameter("date");
		String place 	= request.getParameter("place");
		String begroup 	= request.getParameter("begroup");
		String status 	= request.getParameter("status");
		String ingrade 	= request.getParameter("ingrade");
		String profess 	= request.getParameter("profess");
		String inclass 	= request.getParameter("inclass");
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
		String where = "from Graduateinfo where departmentId = '" + 
			department.getDeptId() + "' ";
		if(!Verify.isEmpty(status)) {
			where += " AND status = '" + status.trim() + "' ";
			request.setAttribute("status", status);
		}
		if(!Verify.isEmpty(date)) {
			where += " AND gdiDate LIKE '%" + date + "%' ";
			request.setAttribute("date", date);
		}
		if(!Verify.isEmpty(place)) {
			where += " AND gdiPlace LIKE '%" + place + "%' ";
			request.setAttribute("place", place);
		}
		if(!Verify.isEmpty(begroup)) {
			where += " AND begroup = '" + begroup.trim() + "' ";
			mav.addObject("begroup", begroup);
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
		where += " order by gdiPlace asc, gdiNumber asc, status asc, createTime desc ";
		try {
			OpentopicinfoController._assignBeGroupListMap(request);
			OpentopicinfoController._assignBeGroupMap(request);
			OpentopicinfoController._assignStatusListMap(request);
			OpentopicinfoController._assignStatusMap(request);
			_assignTbgradeList(request);
			list = graduateinfoService.getAllRecordByPages(where, pagination);
			String begroupWhere = "select begroup from Graduateinfo where departmentId = '" + 
					department.getDeptId() + "' group by begroup ";
			List<Graduateinfo> begroupList = graduateinfoService.getAllRowsByWhere(begroupWhere);
			mav.addObject("begroupList", begroupList);
			if(list == null || list.size() < 1) {
				
				return mav;
			}
			if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				list = (List<Graduateinfo>) graduateinfoService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
			_assignStudentListMap(list, request);
			_assignHeadermanListMap(list, request);
			_assignJudgeListMap(list, request);			
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
	 * 加载学生列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStudentListMap(List<?> data, HttpServletRequest request) throws VerifyException {
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
	 * 加载学生MapMap
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStudentMapMap(Graduateinfo graduateinfo, HttpServletRequest request) throws VerifyException {
		if(Verify.isEmpty(graduateinfo)) {
			return;
		}
		String where 	= "from Student where stuId = '" + graduateinfo.getStuId() + "' ";
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
	private void _assignHeadermanListMap(List<Graduateinfo> data, HttpServletRequest request) throws VerifyException
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
	private void _assignHeadermanMapMap(Graduateinfo graduateinfo, HttpServletRequest request) throws VerifyException
	{
		if(null == graduateinfo) {
			return;
		}
		String where 	= "from Teacher where teaId = '" + graduateinfo.getHeaderman() + "' ";
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
	private void _assignJudgeListMap(List<Graduateinfo> data, HttpServletRequest request) throws VerifyException
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
	 * 加载评审教师MapMap
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午04:45:05
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignJudgeMapMap(Graduateinfo graduateinfo, HttpServletRequest request) throws VerifyException
	{
		if(null == graduateinfo) {
			return;
		}
		String where = "from Teacher where teaId = '" + graduateinfo.getJudge() + "' ";
		List<Teacher> tempList 	= teacherService.getAllRows(where);
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
	private void _assignJudgeMap(Graduateinfo graduateinfo, HttpServletRequest request) throws VerifyException 
	{
		if(Verify.isEmpty(graduateinfo) || Verify.isEmpty(graduateinfo.getJudge())) {
			return;
		}
		String[] ids = null;
		ids 	= graduateinfo.getJudge().split(",");
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
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return ModelAndView
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		if(id == null || id.trim().equals("")) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(id));
		if(graduateinfo == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			graduateinfoService.removeOneGraduateinfo(graduateinfo);
			RequestSetAttribute.requestSetAttribute(request, 200, "", "删除成功", "", "");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "删除失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 编辑视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(id));
		if(graduateinfo == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("graduateinfo", graduateinfo);
		mav.setViewName("admin/graduateinfo/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-22 20:29:42
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		String name = request.getParameter("name");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(id));
		if(graduateinfo == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			
			return mav;
		}
		try{
			graduateinfoService.editOneGraduateinfo(graduateinfo);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "graduateinfolist", "/admin/graduateinfo/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "graduateinfolist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 毕业设计（论文）总成绩
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-4 下午03:41:02
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/totalscore.do")
	public ModelAndView totalScore(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/graduateinfo/grade-total");
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbgrade where deptId = '" + department.getDeptId() + 
			"' order by graNumber desc";
		request.setAttribute(
				"gradeList", 
				tbgradeService.getAllRowsByWhere(where)
				);
		
		return mav;
	}
	
	/**
	 * 年级毕业设计（论文）总成绩列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-4 下午08:49:58
	 * @return ModelAndView
	 */
	@RequestMapping(value="/totalrows.do")
	public ModelAndView totalRows(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/graduateinfo/grade-total-list");
		Department department = (Department) request.getSession().getAttribute("department");
		String grade 			= request.getParameter("gradeid");
		String profession 		= request.getParameter("professid");
		String tbclass 			= request.getParameter("claid");
		String where 			= "from Gradeall where departmentId = '" + department.getDeptId() + 
				"' AND status = '1' ";
		if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession) && !Verify.isEmpty(tbclass)) {
			where += " AND stuId IN (" +
					"select s.stuId from Student s where s.claId = (" +
					"select c.claId from Tbclass c where c.claId = '" + tbclass.trim() + "' AND c.proId = (" +
					"select p.proId from Profession p where p.proId = '" + profession.trim() + "' AND p.deptId = '" + 
					department.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}else if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession)) {
			where += " AND stuId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.proId = '" + profession.trim() + "' AND p.deptId = '" + 
					department.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}else if(!Verify.isEmpty(grade)) {
			where += " AND stuId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.deptId = '" + 
					department.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}
		where += " order by createTime desc";
		try {
			/*String proWhere 	= "from Profession where graId = '" + grade + "'";
			String tbcWhere 	= "from Tbclass where " + SqlUtil.whereIn("proId", "proId", professionService.getAllRowsByWhere(proWhere));
			String stuWhere 	= "from Student where " + SqlUtil.whereIn("claId", "claId", tbclassService.getAllRows(tbcWhere));
			String whereIn 		= SqlUtil.whereIn("stuId", "stuId", studentService.getAllRows(stuWhere));
			*/
			List<Gradeall> gradeallList 	= gradeallService.getAllRowsByWhere(where);
			mav.addObject("gradeall-list", gradeallList);
			_assignStudentListMap(gradeallList, request);
			String gwhere = "from Tbgrade where deptId = '" + department.getDeptId() + "' order by graNumber desc";
			request.setAttribute("tbgradeList",tbgradeService.getAllRowsByWhere(gwhere));
			request.setAttribute("grade", grade);
			_assignTbgradeListMap(tbgradeService.getAllRowsByWhere(gwhere), request);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}

	/**
	 * 加载年级列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午05:01:23
	 * @return void
	 * @throws VerifyException 
	 */
	@SuppressWarnings("unchecked")
	private void _assignTbgradeListMap(List<?> data, HttpServletRequest request) throws VerifyException {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("graId", "graId", data);
		List<Tbgrade> tempList 	= tbgradeService.getAllRowsByWhere("from Tbgrade where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("grade_map", ArrayUtil.turnListToMap("graId", "graNumber", tempList));
	}
	
	/**
	 * 导出毕业答辩安排信息表
	 * 不分页
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-7 下午03:31:48
	 * @return void
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/export.do")
	public void export(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Graduateinfo where departmentId = '" + 
			department.getDeptId() + "' and status = '1' order by gdiPlace asc";
		list = graduateinfoService.getAllRowsByWhere(where);
		_assignStudentListMap(list, request);
		_assignHeadermanListMap(list, request);
		_assignJudgeListMap(list, request);
		
		OutputStream outStream = null;
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(department.getDeptName() + " 毕业答辩安排信息一览表");
			sheet.setDefaultColumnWidth((short) 15);
			sheet.setDefaultRowHeightInPoints(15);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.BORDER_HAIR);
			
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(25);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 1));
			cell.setCellValue(new HSSFRichTextString(department.getDeptName() + " 毕业答辩安排信息一览表"));
			
			HSSFRow row_2 = sheet.createRow((int) 1);
			row_2.setHeightInPoints(18);
			HSSFCell cell_2 = row_2.createCell((short) 0);
			cell_2.setCellValue("答辩序号");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 1);
			cell_2.setCellValue("学生");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 2);
			cell_2.setCellValue("毕业答辩日期");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 3);
			cell_2.setCellValue("毕业答辩地点");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 4);
			cell_2.setCellValue("评审教师");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 5);
			cell_2.setCellValue("评审组长");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			int i = 0;
			for(Graduateinfo grade : list){
				row = sheet.createRow((int) i+2);
				row.setHeightInPoints(20);
				cell = row.createCell((short) 0);
				cell.setCellValue(grade.getGdiNumber());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				sheet.setColumnWidth(5, 5000);
				cell = row.createCell((short) 1);
				cell.setCellValue(grade.getStuId() + " 【" + HResponse.formatValue("stuId", grade.getStuId(), request) + "】");
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 2);
				cell.setCellValue(grade.getGdiDate());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 3);
				cell.setCellValue(grade.getGdiPlace());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 4);
				cell.setCellValue(HResponse.formatJudgeValue(grade.getJudge(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 5);
				cell.setCellValue(HResponse.formatValue("headerman", grade.getHeaderman(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				/*row.createCell((short) 0).setCellValue(deptlist.getDeptId());
				row.createCell((short) 1).setCellValue(deptlist.getDeptName());*/
				i++;
			}
			Region region = new Region();
			region.setColumnFrom((short) 0);
			region.setColumnTo((short) 5);
			region.setRowFrom(0);
			region.setRowTo(0);
			sheet.addMergedRegion(region);
			
			response.reset();
			response.setContentType("application/x-download;charset-UTF-8");
			response.setHeader("Content-Disposition", 
					"attachment;filename=" + System.currentTimeMillis() +".xls");
			outStream = response.getOutputStream();
			wb.write(outStream);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			try {
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
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
			String where = "from Graduateinfo where departmentId = '" + department.getDeptId() + 
					"' AND gdiPlace = '" + place.trim() + "' AND begroup = '" + 
					begroup + "' AND status = '1' ";
			Graduateinfo graduateinfo = graduateinfoService.getRecordByWhere(where);
			if(!Verify.isEmpty(graduateinfo)) {
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
	 * 毕业答辩概况
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/gderesult.do")
	public ModelAndView gdeResult(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("admin/graduateinfo/gde-result-search");
		try {
			_assignTbgradeList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 毕业答辩概况
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
		mav.setViewName("admin/graduateinfo/gde-result-search-list");
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
		//该班级的答辩信息
		String gdeinfo 	= "from Graduateinfo where departmentId = '" + department.getDeptId() + "' ";
		try {
			//查找学生
			List<Student> stuList 	= studentService.getAllRows(stuWhere);
			if(!Verify.isEmpty(stuList)) {
				gdeinfo += " AND " + SqlUtil.whereIn("stuId", "stuId", stuList) + " order by createTime desc ";
			}
			//查找信息
			List<Graduateinfo> gdeinfoList = graduateinfoService.getAllRowsByWhere(gdeinfo);
			if(!Verify.isEmpty(gdeinfoList)) {
				//查找答辩分数
				String scoreWhere = "from Gradeall where " + SqlUtil.whereIn("stuId", "stuId", gdeinfoList);
				request.setAttribute("gdescoreList", gradeallService.getAllRowsByWhere(scoreWhere));
			}
			request.setAttribute("stuList", stuList);
			request.setAttribute("gdeinfoList", gdeinfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		_assignTbgradeList(request);
		
		return mav;
	}
	
	/**
	 * 详细安排信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="orderdetail.do")
	public ModelAndView orderDetail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("/admin/graduateinfo/order-detail");
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			return mav;
		}
		try {
			Graduateinfo graduateinfo = graduateinfoService.getOneRecordById(Integer.parseInt(id.trim()));
			mav.addObject("graduateinfo", graduateinfo);
			_assignJudgeMap(graduateinfo, request);
			_assignJudgeMapMap(graduateinfo, request);
			_assignHeadermanMapMap(graduateinfo, request);
			OpentopicinfoController._assignBeGroupMap(request);
			_assignStudentMapMap(graduateinfo, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
}
