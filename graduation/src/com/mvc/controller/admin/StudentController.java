package com.mvc.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.mvc.common.ArrayUtil;
import com.mvc.common.ExcelIO;
import com.mvc.common.HResponse;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Student;
import com.mvc.entity.Tbclass;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Teacher;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 学生控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/student")
public class StudentController {
	
	@Autowired
	private TbclassService tbclassService;
	
	@Autowired
	private StudentService studentService;
	
	private Pagination pagination;
	private List<Student> list = new ArrayList<Student>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
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
	 * 注册性别Map
	 */
	private static Map<String, String> _sexMap =new LinkedHashMap<String, String>(){{
		put("1", "男");
		put("2", "女");
	}};
	
	/**
	 * 加载性别Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-17 下午02:29:24
	 * @return void
	 */
	public static  void _assignSexMap(HttpServletRequest request)
	{
		request.setAttribute(
				"sex_map", 
				MapUtil.makeLinkedMapMap(_sexMap)
				);
	}
	
	/**
	 * 添加单个学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午09:42:54
	 * @return String
	 */
	@RequestMapping(value="/addoneview.do")
	public ModelAndView addOneView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		System.out.println("start");
		String where = "from Tbclass tc where tc.proId IN (" +
				"select pro.proId from Profession pro where deptId ='" + 
				department.getDeptId() + "') order by tc.claId desc";
		mav.addObject("tbclassList", tbclassService.getAllRowsByWhere(where));
		mav.setViewName("admin/student/add");
		
		return mav;
	}
	
	/**
	 * 添加学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午02:02:02
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		String id 			= request.getParameter("id");
		String name 		= request.getParameter("name");
		String claid 		= request.getParameter("parent_id");
		String sex 			= request.getParameter("sex");
		String phone 		= request.getParameter("phone");
		String email 		= request.getParameter("email");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Student student = studentService.getOneById(id);
		if(student != null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "该学号已经存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		student = new Student();
		student.setStuId(id);
		student.setStuName(name);
		student.setClaId(Integer.parseInt(claid));
		student.setStuSex(sex);
		student.setStuTel(phone);
		student.setStuEmail(email);
		try{
			studentService.addOneStudent(student);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "studentlist", "/admin/student/stulist.do");
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
	 * @date 2014-7-17 下午02:57:30
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String id 			= request.getParameter("id");
		String name 		= request.getParameter("name");
		String claid 		= request.getParameter("parent_id");
		String email 		= request.getParameter("email");
		if(id == null ||id.trim().equals("")) {
			request.setAttribute("message", "学号不能为空");
			
			return false;
		}
		if(name == null || name.trim().equals("")) {
			request.setAttribute("message", "姓名不能为空");
			
			return false;
		}
		if(claid == null || claid.trim().equals("")) {
			request.setAttribute("message", "班级不能为空");
			
			return false;
		}
		if(email == null || email.trim().equals("")) {
			request.setAttribute("message", "电子邮箱不能为空");
			
			return false;
		}
		return true;
	}

	/**
	 * excel导入
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午09:44:43
	 * @return ModelAndView
	 */
	@RequestMapping(value="/excelimport.do")
	public String excelImport(HttpServletRequest request, HttpServletResponse response)
	{
		return "admin/student/excel";
	}
	
	/**
	 * excel批量导入学生信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-29 下午09:07:53
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
			Student student = new Student();
			for (int j = 0; j < result[i].length; j++) {
				if(j == 0){
					student.setStuId(result[i][j]);
				}
				if(j == 1){
					student.setStuName(result[i][j]);
				}
				if(j == 2){
					student.setClaId(Integer.parseInt(result[i][j]));
				}
				if(j == 3){
					student.setStuSex(result[i][j]);
				}
				if(j == 4){
					student.setStuTel(result[i][j]);
				}
				if(j == 5){
					student.setStuEmail(result[i][j]);
				}
			}
			if(i > 1) {
				if(!this.isEmptyAttribute(request, student, (i+1)))
				{
					request.setAttribute("statusCode", 300);
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
				if (!this.isExist(student.getStuId(), request)) {
					request.setAttribute("message", "第" + (i+1) + "条记录学号" + student.getStuId() + "已存在");
					mav.setViewName("public/ajaxDone");
					
					return mav;
				}
			}
			try{
				studentService.addOneStudent(student);
				RequestSetAttribute.requestSetAttribute(
					request, 200, "closeCurrent", "添加成功", "stuentlist", "admin/student/stulist.do");
			}catch(Exception e){
				RequestSetAttribute.requestSetAttribute(
					request, 300, "", "第" + (i+1) + "个添加失败", "", "");
			}
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 验证是否已存在
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-29 下午09:25:47
	 * @return boolean
	 */
	protected boolean isExist(String id, HttpServletRequest request) {
		Student student = studentService.getOneById(id);
		if (student != null && !student.getStuId().equals("")) {
			request.setAttribute("statusCode", 300);
			return false;
		}
		
		return true;
	}

	/**
	 * 判断属性是否为空
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-29 下午09:17:25
	 * @return boolean
	 */
	protected boolean isEmptyAttribute(HttpServletRequest request,
			Student student, int i) {
		if(student.getStuId().equals(""))
		{
			request.setAttribute("message", "第" + i + "条记录的学号不能为空");
			
			return false;
		}
		if(student.getClaId().equals(""))
		{
			request.setAttribute("message", "第" + i + "条记录的班级不能为空");
			
			return false;
		}
		if(student.getStuEmail().equals(""))
		{
			request.setAttribute("message", "第" + i + "条记录的电子邮箱不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * excel导出学生信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-29 下午08:51:53
	 * @return String
	 */
	@RequestMapping(value="/export.do")
	public void export(HttpServletRequest request, HttpServletResponse response)
	{
		Department department = (Department) request.getSession().getAttribute("department");
		TeacherController.assignSexMapMap(request);
		
		String where = "from Student s where s.claId IN ( " +
			"select t.claId from Tbclass t where t.proId IN ( " +
			"select p.proId from Profession p where p.deptId = '" + 
			department.getDeptId() + "' ) ) order by s.stuId asc";
		list = studentService.getAllRows(where);
		OutputStream outStream = null;
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("学生信息一览表");
			sheet.setDefaultColumnWidth((short) 15);
			sheet.setDefaultRowHeightInPoints(15);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.BORDER_HAIR);
			
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(25);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 1));
			cell.setCellValue(new HSSFRichTextString(department.getDeptName() + "学生信息一览表"));
			
			HSSFRow row_2 = sheet.createRow((int) 1);
			row_2.setHeightInPoints(18);
			HSSFCell cell_2 = row_2.createCell((short) 0);
			cell_2.setCellValue("学号");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 1);
			cell_2.setCellValue("姓名");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 2);
			cell_2.setCellValue("班级");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 3);
			cell_2.setCellValue("性别");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 4);
			cell_2.setCellValue("联系电话");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 5);
			cell_2.setCellValue("电子邮箱");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			int i = 0;
			_assignTbclassListMap(list, request);
			for(Student student : list){
				row = sheet.createRow((int) i+2);
				row.setHeightInPoints(20);
				cell = row.createCell((short) 0);
				cell.setCellValue(student.getStuId());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				sheet.setColumnWidth(5, 5000);
				cell = row.createCell((short) 1);
				cell.setCellValue(student.getStuName());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 2);
				cell.setCellValue(HResponse.formatValue("tbclass", student.getClaId().toString(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 3);
				cell.setCellValue(HResponse.formatValue("sex", student.getStuSex(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 4);
				cell.setCellValue(student.getStuTel());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 5);
				cell.setCellValue(student.getStuEmail());
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
		
		return;
	}
	
	/**
	 * 查询学生列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午09:46:33
	 * @return ModelAndView
	 */
	@RequestMapping(value="/stulist.do")
	public ModelAndView studentList(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		};
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
		String where = "from Student s where s.claId IN ( " +
				"select t.claId from Tbclass t where t.proId IN ( " +
				"select p.proId from Profession p where p.deptId = '" + 
				department.getDeptId() + "' ) ) order by s.stuId asc";
		list = studentService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("admin/student/list");
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Student>) studentService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
		mav.setViewName("admin/student/list");
		TeacherController.assignSexMapMap(request);
		_assignTbclassListMap(list, request);
		
		return mav;
	}
	
	/**
	 * 加载班级列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午05:01:23
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void _assignTbclassListMap(List<?> data, HttpServletRequest request) {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("claId", "claId", data);
		List<Tbclass> tempList 	= tbclassService.getAllRows("from Tbclass where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("tbclass_map", ArrayUtil.turnListToMap("claId", "claName", tempList));
	}
	
	/**
	 * 删除一个学生
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午04:21:06
	 * @return ModelAndView
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		if(id == null || id.trim().equals("")) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Student student = studentService.getOneById(id);
		if(student == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			studentService.removeOneStudent(student);
			RequestSetAttribute.requestSetAttribute(request, 200, "", "删除成功", "", "");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "删除失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 编辑学生视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午07:56:40
	 * @return ModelAndView
	 */
	@RequestMapping(value="/editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 		= request.getParameter("id");
		if(id == null || id.trim().equals("")) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Student student = studentService.getOneById(id);
		if(student == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbclass tc where tc.proId IN (" +
				"select pro.proId from Profession pro where deptId ='" + 
				department.getDeptId() + "') order by tc.claId desc";
		mav.addObject("tbclassList", tbclassService.getAllRowsByWhere(where));
		mav.addObject("student", student);
		mav.setViewName("admin/student/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 下午07:58:00
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 			= request.getParameter("id");
		String name 		= request.getParameter("name");
		String claid 		= request.getParameter("parent_id");
		String sex 			= request.getParameter("sex");
		String phone 		= request.getParameter("phone");
		String email 		= request.getParameter("email");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Student student = studentService.getOneById(id);
		if(student == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		student.setStuId(id);
		student.setStuName(name);
		student.setClaId(Integer.parseInt(claid));
		student.setStuSex(sex);
		student.setStuTel(phone);
		student.setStuEmail(email);
		try{
			studentService.editOneStudent(student);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "studentlist", "/admin/student/stulist.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
}
