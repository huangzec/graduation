package com.mvc.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.jws.WebParam.Mode;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ExcelIO;
import com.mvc.common.HResponse;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.entity.Department;
import com.mvc.entity.Teacher;
import com.mvc.service.DeptService;
import com.mvc.service.TeacherService;
import com.sun.java.swing.plaf.motif.resources.motif;

/**
 * 教师控制器类
 * @author huangzec@foxmail.com
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/admin/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private DeptService deptService;

	private String keyword;
	private Pagination pagination;
	private List<Teacher> list = new ArrayList<Teacher>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	
	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Teacher> getList() {
		return list;
	}

	public void setList(List<Teacher> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * 注册教师职称Map
	 */
	@SuppressWarnings("serial")
	private static LinkedHashMap<String, String> _teacherposMap = new LinkedHashMap<String, String>(){{
		put("0", "助教");
		put("1", "讲师");
		put("2", "副教授");
		put("3", "教授");
	}};
	
	/**
	 * 注册教师性别Map
	 */
	private static LinkedHashMap<String, String> _sexMap = new LinkedHashMap<String, String>(){{
		put("1", "男");
		put("2", "女");
	}};
	
	/**
	 * 注册教师职称Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-16 上午10:33:36
	 * @return void
	 */
	public static void assignTeacherposMap(HttpServletRequest request)
	{
		request.setAttribute(
				"teapos_map",
				MapUtil.makeLinkedMapMap(_teacherposMap)
				);
	}
	
	/**
	 * 注册教师职称列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午08:21:09
	 * @return void
	 */
	public static void assignTeacherposListMap(HttpServletRequest request)
	{
		request.setAttribute(
				"pos_list",
				MapUtil.makeLinkedListMap(_teacherposMap)
				);
	}
	
	/**
	 * 加载性别Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午08:49:26
	 * @return void
	 */
	public static void assignSexMapMap(HttpServletRequest request)
	{
		request.setAttribute(
				"sex_map",
				MapUtil.makeLinkedMapMap(_sexMap)
				);
	}
	
	/**
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 上午11:18:17
	 * @return String
	 */
	@RequestMapping(value="/addoneview.do")
	public ModelAndView addOneView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		mav.addObject("department", department);
		mav.setViewName("admin/teacher/add");
		assignTeacherposListMap(request);
		
		return mav;
	}
	
	/**
	 * 添加单个教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 上午11:22:39
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addoneteach.do")
	public ModelAndView addOneTeacher(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		String id 			= request.getParameter("id");
		String email 		= request.getParameter("email");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Teacher teacher 	= null;
		teacher = teacherService.getOneTeacherById(id);
		if(teacher != null && (!teacher.getTeaId().equals(""))) {
			System.out.println("记录已存在");
			RequestSetAttribute.requestSetAttribute(request, 300, "", "记录已存在", "", "");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		teacher = new Teacher();
		teacher.setTeaId(id);
		teacher.setTeaName(request.getParameter("name"));
		teacher.setDeptId(request.getParameter("parent_id"));
		teacher.setTeaSex(request.getParameter("sex"));
		teacher.setTeaPos(request.getParameter("pos"));
		teacher.setTeaTel(request.getParameter("phone"));
		teacher.setTeaEmail(email);
		try{
			teacherService.addOneTeacher(teacher);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "teacherlist", "/admin/teacher/teachlist.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "添加失败", "teacherlist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav; 
	}
	
	/**
	 * 验证数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 上午11:39:42
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		String email 	= request.getParameter("email");
		if(id.equals("")) {
			request.setAttribute("message", "教师工号不能为空");
			
			return false;
		}
		if(name.equals("")) {
			request.setAttribute("message", "教师姓名不能为空");
			
			return false;
		}
		if(email.equals("")) {
			request.setAttribute("message", "邮箱不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * excel批量导入
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 上午11:25:14
	 * @return ModelAndView
	 */
	@RequestMapping(value="/excelimport.do")
	public ModelAndView excelImport(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/teacher/excel");
		
		return mav;
	}
	
	/**
	 * excel批量导入教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-28 下午05:25:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/excel.do")
	public ModelAndView excel(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile excelFile = mulRequest.getFile("file");
		/**
		 * 判断文件格式
		 */
		if(!excelFile.getContentType().toString().equals("application/vnd.ms-excel")){
			RequestSetAttribute.requestSetAttribute(
					request, 300, "", "请选择指定的文件格式！", "", "");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		} 
		String[][] result = ExcelIO.ExcelOperate(request, excelFile);
		int rowLength = result.length;
		for (int i = 0; i < rowLength; i++) {
			Teacher teacher = new Teacher();
			for (int j = 0; j < result[i].length; j++) {
				if(j == 0){
					teacher.setTeaId(result[i][j]);
				}
				if(j == 1){
					teacher.setTeaName(result[i][j]);
				}
				if(j == 2){
					teacher.setDeptId(result[i][j]);
				}
				if(j == 3){
					teacher.setTeaSex(result[i][j]);
				}
				if(j == 4){
					teacher.setTeaPos(result[i][j]);
				}
				if(j == 5){
					teacher.setTeaTel(result[i][j]);
				}
				if(j == 6){
					teacher.setTeaEmail(result[i][j]);
				}
			}
			if(i > 1) {
				if(!this.isEmptyAttribute(request, teacher, (i+1)))
				{
					request.setAttribute("statusCode", 300);
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
				if (!this.isExist(teacher.getTeaId(), request)) {
					request.setAttribute("message", "第" + (i+1) + "条记录教师工号" + teacher.getTeaId() + "已存在");
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
				/**
				 * 是否是本系部老师
				 */
				if (!this.isSelfDepartment(request, department.getDeptId())) {
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
			}
			try{
				teacherService.addOneTeacher(teacher);
				RequestSetAttribute.requestSetAttribute(
					request, 200, "closeCurrent", "添加成功", "teacherlist", "admin/teacher/teachlist.do");
			}catch(Exception e){
				RequestSetAttribute.requestSetAttribute(
					request, 300, "", "第" + (i+1) + "个添加失败", "", "");
			}
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 判断teacher属性是否为空
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param request 
	 * @param i 
	 * @date 2014-7-28 下午08:38:51
	 * @return boolean
	 */
	protected boolean isEmptyAttribute(HttpServletRequest request, Teacher teacher, int i) {
		if(teacher.getTeaId().equals(""))
		{
			request.setAttribute("message", "第" + i + "条记录的教师工号不能为空");
			
			return false;
		}
		if(teacher.getDeptId().equals(""))
		{
			request.setAttribute("message", "第" + i + "条记录的所属系部不能为空");
			
			return false;
		}
		if(teacher.getTeaEmail().equals(""))
		{
			request.setAttribute("message", "第" + i + "条记录的电子邮箱不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 是否是本系部老师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-28 下午08:29:23
	 * @return boolean
	 */
	protected boolean isSelfDepartment(HttpServletRequest request, String deptId) {
		Department department = (Department) request.getSession().getAttribute("department");
		if(!department.getDeptId().equals(deptId))
		{
			request.setAttribute("message", "所属系部不正确");
			request.setAttribute("statusCode", 300);
			
			return false;
		}
		
		return true;
	}

	/**
	 * 判断记录是否已存在
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-28 下午08:23:14
	 * @return boolean
	 */
	protected boolean isExist(String id, HttpServletRequest request) {
		Teacher teacher = teacherService.getOneTeacherById(id);
		System.out.println("teacher " + id);
		if (teacher != null && !teacher.getTeaId().equals("")) {
			request.setAttribute("statusCode", 300);
			return false;
		}
		
		return true;
	}
	
	/**
	 * excel 导出教师信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-29 下午05:25:24
	 * @return ModelAndView
	 */
	@RequestMapping(value="/export.do")
	public void export(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		assignTeacherposMap(request);
		assignSexMapMap(request);
		list = teacherService.getAllRows("from Teacher where deptId = '" + department.getDeptId() + "'");
		OutputStream outStream = null;
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("教师信息一览表");
			sheet.setDefaultColumnWidth((short) 15);
			sheet.setDefaultRowHeightInPoints(15);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.BORDER_HAIR);
			
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(25);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 1));
			cell.setCellValue(new HSSFRichTextString(department.getDeptName() + "教师信息一览表"));
			
			HSSFRow row_2 = sheet.createRow((int) 1);
			row_2.setHeightInPoints(18);
			HSSFCell cell_2 = row_2.createCell((short) 0);
			cell_2.setCellValue("教师工号");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 1);
			cell_2.setCellValue("教师姓名");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 2);
			cell_2.setCellValue("所属系部");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 3);
			cell_2.setCellValue("性别");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 4);
			cell_2.setCellValue("职称");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 5);
			cell_2.setCellValue("联系电话");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 6);
			cell_2.setCellValue("电子邮箱");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			int i = 0;
			for(Teacher teacher : list){
				row = sheet.createRow((int) i+2);
				row.setHeightInPoints(20);
				cell = row.createCell((short) 0);
				cell.setCellValue(teacher.getTeaId());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				sheet.setColumnWidth(6, 5000);
				cell = row.createCell((short) 1);
				cell.setCellValue(teacher.getTeaName());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 2);
				cell.setCellValue(department.getDeptName());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 3);
				cell.setCellValue(HResponse.formatValue("sex", teacher.getTeaSex(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 4);
				cell.setCellValue(HResponse.formatValue("teapos", teacher.getTeaPos(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 5);
				cell.setCellValue(teacher.getTeaTel());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 6);
				cell.setCellValue(teacher.getTeaEmail());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				i++;
			}
			Region region = new Region();
			region.setColumnFrom((short) 0);
			region.setColumnTo((short) 6);
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
		
		return;
	}

	/**
	 * 教师列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-28 下午05:22:53
	 * @return ModelAndView
	 */
	@RequestMapping(value="/teachlist.do")
	public ModelAndView teacherList(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
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
		keyword = request.getParameter("keyword");
		String keywordid 	= request.getParameter("keywordid");
		String keywordname 	= request.getParameter("keywordname");
		String where = "from Teacher where 1 = 1 AND deptId = '" + department.getDeptId() + "'";
		if(keywordid != null && (!keywordid.trim().equals(""))) {
			where += " AND teaId LIKE '%" + keywordid + "%' ";
		}
		if(keywordname != null && (!keywordname.trim().equals(""))) {
			where += " AND teaName LIKE '%" + keywordname + "%' ";
		}
		if(keyword != null && (!keyword.trim().equals("")))
		{
			where += " ";
			list = teacherService.getAllRecordByPages(where, pagination);
		}
		list = teacherService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("admin/teacher/list");
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Teacher>) teacherService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute(keyword, pagination, list, modelMap);
		mav.setViewName("admin/teacher/list");
		mav.addObject("department", department);
		assignTeacherposMap(request);
		assignSexMapMap(request);
		
		return mav;
	}
	
	/**
	 * 删除教师
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午08:24:53
	 * @return String
	 */
	@RequestMapping(value="/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response)
	{
		String id 	= request.getParameter("id");
		if(id == null || id.equals("")) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "ID不能为空");
			
			return "public/ajaxDone";
		}
		System.out.println("id " + id);
		Teacher teacher = teacherService.getOneTeacherById(id);
		if(teacher == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			
			return "public/ajaxDone";
		}
		try{
			teacherService.removeOneTeacher(teacher);
			RequestSetAttribute.requestSetAttribute(request, 200, "", "删除成功", "", "");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "删除失败", "", "");
		}
		
		return "public/ajaxDone";
	}
	
	/**
	 * lookup查找
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午09:23:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="/lookup.do")
	public ModelAndView lookup(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav 			= new ModelAndView();
		List<Department> deptList 	= new ArrayList<Department>();
		mav.setViewName("admin/teacher/lookup");
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
		keyword = (String) request.getParameter("keyword");
		String orgName 	= (String) request.getParameter("orgName");
		String orgNum 	= (String) request.getParameter("orgNum");
		String where = "from Department where 1 = 1 ";
		if(orgName != null && !(orgName.trim().equals(""))) {
			where += " AND deptName LIKE '%" + orgName + "%' ";
		}
		if(orgNum != null && !(orgNum.trim().equals(""))) {
			where += " AND deptId LIKE '%" + orgNum + "%' ";
		}
		if(keyword != null && !keyword.equals("")) {
			where += " AND keyword LIKE '%" + keyword + "%' ";
		}
		deptList = deptService.getAllRecordByPages(where, pagination);
		if(deptList == null || deptList.size() < 1) {
			return mav;
		}
		if(deptList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			deptList = (List<Department>) deptService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute(keyword, pagination, deptList, modelMap);
		
		return mav;
	}
	
	/**
	 * 编辑视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-14 下午09:36:10
	 * @return ModelAndView
	 */
	@RequestMapping(value="/editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav	= new ModelAndView();
		String id 			= request.getParameter("id");
		if(id == null || id.equals("")) {
			RequestSetAttribute.requestSetAttribute(request, 300, "closeCurrent", "记录不存在", "teacherlist", "/admin/teacher/teachlist.do");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Teacher teacher = teacherService.getOneTeacherById(id);
		if(teacher == null) {
			RequestSetAttribute.requestSetAttribute(request, 300, "closeCurrent", "记录不存在", "teacherlist", "/admin/teacher/teachlist.do");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("teacher", teacher);
		mav.addObject("department", (Department) request.getSession().getAttribute("department"));
		mav.setViewName("admin/teacher/edit");
		assignTeacherposListMap(request);
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午08:55:53
	 * @return String
	 */
	@RequestMapping(value="/edit.do")
	public String edit(HttpServletRequest request, HttpServletResponse response)
	{
		String id 			= request.getParameter("id");
		String email 		= request.getParameter("email");
		if(!this._verifyData(request)) {
			request.setAttribute("statusCode", 300);
			
			return "public/ajaxDone";
		}
		Teacher teacher = teacherService.getOneTeacherById(id);
		if(teacher == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			
			return "public/ajaxDone";
		}
		teacher.setTeaId(id);
		teacher.setTeaName(request.getParameter("name"));
		teacher.setDeptId(request.getParameter("parent_id"));
		teacher.setTeaSex(request.getParameter("sex"));
		teacher.setTeaPos(request.getParameter("pos"));
		teacher.setTeaTel(request.getParameter("phone"));
		teacher.setTeaEmail(email);
		try{
			teacherService.editOneTeacher(teacher);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "teacherlist", "/admin/teacher/teachlist.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "teacherlist", "");
		}
		
		return "public/ajaxDone";
	}
}
