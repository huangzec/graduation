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
import org.springframework.ui.Model;
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
import com.mvc.exception.VerifyException;
import com.mvc.service.DeptService;
import com.mvc.service.ProfessionService;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;

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
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private DeptService departmentService;
	
	private Pagination pagination;
	private List<Student> list = new ArrayList<Student>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 20;//每页显示多少条

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
	 * 注册状态Map
	 */
	private static Map<String, String> _statusMap = new LinkedHashMap<String, String>(){{
		put("1", "激活");
		put("2", "未激活");
		put("3", "不能使用");
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
	 * 加载状态Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 */
	public static void assignStatusMap(HttpServletRequest request)
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
	public static void assignStatusListMap(HttpServletRequest request)
	{
		request.setAttribute(
				"status_list", 
				MapUtil.makeLinkedListMap(_statusMap)
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
		mav.setViewName("admin/student/add");
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbclass tc where tc.proId IN (" +
				"select pro.proId from Profession pro where deptId ='" + 
				department.getDeptId() + "') order by tc.claId desc";
		String gradeWhere = "from Tbgrade where deptId = '" + department.getDeptId() + 
				"' order by graNumber desc ";
		try {
			mav.addObject("gradeList", tbgradeService.getAllRowsByWhere(gradeWhere));
			mav.addObject("tbclassList", tbclassService.getAllRowsByWhere(where));
			assignStatusListMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		String claid 		= request.getParameter("claid");
		String sex 			= request.getParameter("sex");
		String phone 		= request.getParameter("phone");
		String email 		= request.getParameter("email");
		String status 		= request.getParameter("status");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Student student = studentService.getOneById(id);
		if(!Verify.isEmpty(student)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "该学号已经存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		student = new Student();
		student.setStuId(id);
		student.setStuName(name);
		student.setClaId(claid);
		student.setStuSex(sex);
		student.setStuTel(phone);
		student.setStuEmail(email);
		student.setStatus(status);
		try{
			studentService.addOneStudent(student);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "studentlist", "/admin/student/stulist.do");
		}catch (Exception e) {
			e.printStackTrace();
			
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
		String claid 		= request.getParameter("claid");
		String email 		= request.getParameter("email");
		String status 		= request.getParameter("status");
		if(Verify.isEmpty(id)) {
			request.setAttribute("message", "学号不能为空");
			
			return false;
		}
		if(Verify.isEmpty(name)) {
			request.setAttribute("message", "姓名不能为空");
			
			return false;
		}
		if(Verify.isEmpty(claid)) {
			request.setAttribute("message", "班级不能为空");
			
			return false;
		}
		if(Verify.isEmpty(email)) {
			request.setAttribute("message", "电子邮箱不能为空");
			
			return false;
		}
		if(Verify.isEmpty(status)) {
			request.setAttribute("message", "状态不能为空");
			
			return false;
		}
		if(!Verify.isNumber(id)) {
			request.setAttribute("message", "学号不是一个数字");
			
			return false;
		}
		if(!Verify.isStrLen(id, 1, 12)) {
			request.setAttribute("message", "学号不超过12个字符");
			
			return false;
		}
		if(!Verify.isStrLen(name, 1, 16)) {
			request.setAttribute("message", "姓名不超过16个字符");
			
			return false;
		}
		if(!Verify.isStrLen(email, 1, 30)) {
			request.setAttribute("message", "邮箱不超过30个字符");
			
			return false;
		}
		if(!Verify.isEmail(email)) {
			request.setAttribute("message", "邮箱格式不正确");
			
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
					student.setClaId(result[i][j]);
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
				student.setStatus("1");
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
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/export.do")
	public void export(HttpServletRequest request, HttpServletResponse response) throws VerifyException
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
		mav.setViewName("admin/student/list");
		Department department = (Department) request.getSession().getAttribute("department");
		String grade 		= request.getParameter("grade");
		String profession 	= request.getParameter("profession");
		String tbclass 		= request.getParameter("tbclass");
		String userId 		= request.getParameter("keywordid");
		String name 		= request.getParameter("keywordname");
		if(!Verify.isEmpty(request.getParameter("pageNum")))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!Verify.isEmpty(request.getParameter("numPerPage"))) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage").toString());
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
		if(!Verify.isEmpty(profession) && Verify.isEmpty(grade)) {
			return mav;
		}
		if(!Verify.isEmpty(tbclass) && Verify.isEmpty(profession)) {
			return mav;
		}
		String where = "from Student where claId IN ( " +
				"select t.claId from Tbclass t where t.proId IN ( " +
				"select p.proId from Profession p where p.deptId = '" + 
				department.getDeptId() + "' ) ) ";
		if(!Verify.isEmpty(tbclass) && !Verify.isEmpty(profession) && !Verify.isEmpty(grade)) {
			/**
			 * 如果班级，专业，年级都不为空
			 */	
			where = "from Student where claId = ( " +
					"select t.claId from Tbclass t where t.claId = '" + tbclass.trim() + "' AND t.proId = ( " +
					"select p.proId from Profession p where p.deptId = '" + department.getDeptId() + 
					"' AND p.proId = '" + profession.trim() + "' AND p.graId = " + grade.trim() + " ) ) ";
			mav.addObject("tbclass", tbclass);
			mav.addObject("profession", profession);
			mav.addObject("grade", grade);
		}else if(!Verify.isEmpty(profession) && !Verify.isEmpty(grade)) {
			/**
			 * 如果专业，年级都不为空
			 */	
			where = "from Student where claId IN ( " +
					"select t.claId from Tbclass t where t.proId = ( " +
					"select p.proId from Profession p where p.deptId = '" + department.getDeptId() + 
					"' AND p.proId = '" + profession.trim() + "' AND p.graId = " + grade.trim() + " ) ) ";
			mav.addObject("profession", profession);
			mav.addObject("grade", grade);
		}else if(!Verify.isEmpty(grade)) {
			/**
			 * 如果年级不为空
			 */	
			where = "from Student where claId IN ( " +
					"select t.claId from Tbclass t where t.proId IN ( " +
					"select p.proId from Profession p where p.deptId = '" + department.getDeptId() + 
					"' AND p.graId = " + grade.trim() + " ) ) ";
			mav.addObject("grade", grade);
		}
		if(!Verify.isEmpty(userId)) {
			where += " AND stuId LIKE '%" + userId.trim() + "%' ";
			mav.addObject("keywordid", userId);
		}
		if(!Verify.isEmpty(name)) {
			where += " AND stuName LIKE '%" + name.trim() + "%' ";
			mav.addObject("keywordname", name);
		}
		where += "order by stuId asc";
		try {
			list = studentService.getAllRecordByPages(where, pagination);
			_assignTbgradeList(request);
			_assignProfession(request);
			if(list == null || list.size() < 1) {
				
				return mav;
			}
			if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				list = (List<Student>) studentService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
			TeacherController.assignSexMapMap(request);
			_assignTbclassListMap(list, request);	
			assignStatusMap(request);
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
	 * @date 2014-10-6 下午05:01:23
	 * @return void
	 * @throws VerifyException 
	 */
	@SuppressWarnings("unchecked")
	private void _assignTbclassListMap(List<?> data, HttpServletRequest request) throws VerifyException {
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
		Department department = (Department) request.getSession().getAttribute("department");
		String id 		= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try {
			Student student = studentService.getOneById(id);
			if(Verify.isEmpty(student)) {
				mav.addObject("statusCode", 300);
				mav.addObject("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			//学生所在年级
			String inGrade 		= "from Tbgrade where graId = (select p.graId from Profession p where p.proId = (" +
					"select c.proId from Tbclass c where c.claId = '" + student.getClaId() + "'))";
			//学生所在专业
			String inProfession = "from Profession where proId = (" +
					"select c.proId from Tbclass c where c.claId = '" + student.getClaId() + "')";
			String gradeWhere 	= "from Tbgrade g where g.deptId = '" + 
					department.getDeptId() + "' order by g.graNumber desc ";
			String professWhere = "from Profession p where p.graId = (" +
					"select pf.graId from Profession pf where pf.proId = (" +
					"select c.proId from Tbclass c where c.claId = '" + 
					student.getClaId() + "' )) order by p.proId asc ";
			String where = "from Tbclass tc where tc.proId = (" +
					"select c.proId from Tbclass c where c.claId ='" + 
					student.getClaId() + "') order by tc.claId desc";
			mav.addObject("ingrade",tbgradeService.getRecordByWhere(inGrade));
			mav.addObject("inprofession", professionService.getRecordByWhere(inProfession));
			mav.addObject("gradeList", tbgradeService.getAllRowsByWhere(gradeWhere));
			mav.addObject("professionList", professionService.getAllRowsByWhere(professWhere));
			mav.addObject("tbclassList", tbclassService.getAllRowsByWhere(where));
			mav.addObject("student", student);
			assignStatusListMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		String claid 		= request.getParameter("claid");
		String sex 			= request.getParameter("sex");
		String phone 		= request.getParameter("phone");
		String email 		= request.getParameter("email");
		String status 		= request.getParameter("status");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Student student = studentService.getOneById(id);
		if(Verify.isEmpty(student)){
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		student.setStuId(id);
		student.setStuName(name);
		student.setClaId(claid);
		student.setStuSex(sex);
		student.setStuTel(phone);
		student.setStuEmail(email);
		student.setStatus(status);
		try{
			studentService.editOneStudent(student);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "studentlist", "/admin/student/stulist.do");
		}catch (Exception e) {
			e.printStackTrace();
			
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
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
		String where 			= "from Tbgrade where deptId = '" + department.getDeptId() + "' order by graNumber desc ";
		
		request.setAttribute(
				"gradeList",
				tbgradeService.getAllRowsByWhere(where)
				);		
	}
	
	/**
	 * 加载专业列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignProfession(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("department");
		String grade 			= request.getParameter("grade");
		if(Verify.isEmpty(grade)) {
			return;
		}
		String where = "from Profession where deptId = '" + department.getDeptId() + 
				"' AND graId = " + Integer.parseInt(grade);
		
		request.setAttribute(
				"professionList",
				professionService.getAllRowsByWhere(where)
				);
	}
	
	/**
	 * 学生转系部视图
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/changeview.do")
	public ModelAndView changView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 		= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try {
			Student student = studentService.getOneById(id);
			if(Verify.isEmpty(student)) {
				mav.addObject("statusCode", 300);
				mav.addObject("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			//学生所在年级
			String inGrade 		= "from Tbgrade where graId = (select p.graId from Profession p where p.proId = (" +
					"select c.proId from Tbclass c where c.claId = '" + student.getClaId() + "'))";
			//学生所在专业
			String inProfession = "from Profession where proId = (" +
					"select c.proId from Tbclass c where c.claId = '" + student.getClaId() + "')";
			String gradeWhere 	= "from Tbgrade g where g.deptId = '" + 
					department.getDeptId() + "' order by g.graNumber desc ";
			String professWhere = "from Profession p where p.graId = (" +
					"select pf.graId from Profession pf where pf.proId = (" +
					"select c.proId from Tbclass c where c.claId = '" + 
					student.getClaId() + "' )) order by p.proId asc ";
			String where = "from Tbclass tc where tc.proId = (" +
					"select c.proId from Tbclass c where c.claId ='" + 
					student.getClaId() + "') order by tc.claId desc";
			String deptWhere = "from Department where deptId != '" + department.getDeptId() + "' order by deptId asc ";
			mav.addObject("ingrade",tbgradeService.getRecordByWhere(inGrade));
			mav.addObject("inprofession", professionService.getRecordByWhere(inProfession));
			mav.addObject("gradeList", tbgradeService.getAllRowsByWhere(gradeWhere));
			mav.addObject("professionList", professionService.getAllRowsByWhere(professWhere));
			mav.addObject("tbclassList", tbclassService.getAllRowsByWhere(where));
			mav.addObject("student", student);
			mav.addObject("departmentList", departmentService.getAll(deptWhere));
			assignStatusListMap(request);
			_assignSexMap(request);
			assignStatusMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("admin/student/change");
		
		return mav;
	}
	
	@RequestMapping(value="/change.do")
	public ModelAndView change(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("public/ajaxDone");
		String id 			= request.getParameter("id");
		String claid 		= request.getParameter("new-claid");
		if(Verify.isEmpty(id)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "id不能为空");
			
			return mav;
		}
		if(Verify.isEmpty(claid)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "转到单位的班级不正确，请确认");
			
			return mav;
		}
		try{
			Student student = studentService.getOneById(id);
			if(Verify.isEmpty(student)){
				mav.addObject("statusCode", 300);
				mav.addObject("message", "记录不存在");
				
				return mav;
			}
			student.setClaId(claid);
			studentService.editOneStudent(student);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "学生转系成功", "studentlist", "/admin/student/stulist.do");
		}catch (Exception e) {
			e.printStackTrace();
			
			RequestSetAttribute.requestSetAttribute(request, 300, "", "学生转系失败", "", "");
		}
		
		return mav;
	}

}
