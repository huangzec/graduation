package com.mvc.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.ExcelIO;
import com.mvc.common.HResponse;
import com.mvc.common.MD5Util;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Deptmanager;
import com.mvc.entity.User;
import com.mvc.exception.VerifyException;
import com.mvc.service.DeptService;
import com.mvc.service.DeptmanagerService;
import com.mvc.service.UserService;

/**
 * 后台用户模块
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/manager")
public class ManagerController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmanagerService deptmanagerService;
	
	@Autowired
	private DeptService deptService;
	
	private String keyword;
	private String keywords;
	private Pagination pagination;
	private int pageNum=1;
	List<Deptmanager> deptMgrList = new ArrayList<Deptmanager>();
	
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<Deptmanager> getDeptMgrList() {
		return deptMgrList;
	}

	public void setDeptMgrList(List<Deptmanager> deptMgrList) {
		this.deptMgrList = deptMgrList;
	}

	/**
	 * 
	 * 加载系部Map 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-12 下午09:00:48
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignDeptMap(List<Deptmanager> list, HttpServletRequest request) throws VerifyException{
		if(list == null || list.size() < 0){
			return;
		}
		String whereIn = SqlUtil.whereIn("deptId", "deptId", list);
		List<Department> deptlist = deptService.getAll("from Department where " + whereIn);
		System.out.println(deptlist.get(0).getDeptName());
		if(deptlist == null || deptlist.size() < 0){
			return;
		}
		request.setAttribute("dept_map", ArrayUtil.turnListToMap("deptId", "deptName", deptlist));
	}
	
	/**
	 * 个人信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-11 下午09:49:33
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/perfile.do")
	public ModelAndView perfile(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		String userID 	= (String) request.getSession().getAttribute("user_id");;
		User user = userService.getOneUser("from User where username='" + userID + "'");
		if(Verify.isEmpty(user)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		if(request.getSession().getAttribute("user_status") != null &&
				(Integer) request.getSession().getAttribute("user_status") == 3) {
			Deptmanager deptmanager = deptmanagerService.getOneDeptManager("from Deptmanager where dmId = '" + user.getUsername() + "'");
			mav.addObject("manager", deptmanager);
		}
		//mav.addObject("manager", user);
		mav.setViewName("admin/deptmanager/perfile");
		
		return mav;
	}
	
	/**
	 * 修改个人信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午04:06:10
	 * @return String
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/file.do")
	public String file(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		String sex 		= request.getParameter("sex");
		String phone 	= request.getParameter("phone");
		String email 	= request.getParameter("email");
		String manager 	= (String) request.getSession().getAttribute("user_id");
		if(!_verifyFileData(request)) {
			//验证数据
		}
		
		Deptmanager deptmanager = deptmanagerService.getOneDeptManager(
				"from Deptmanager where dmId = '"+ manager + "'");
		try{
			deptmanager.setDmSex(sex);
			deptmanager.setDmTel(phone);
			deptmanager.setDmEmail(email);
			deptmanagerService.editOneDeptmanager(deptmanager);
			RequestSetAttribute.requestSetAttribute(
				request, 200, "", "修改成功", "perfile", "/admin/manager/perfile.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(
				request, 300, "", "修改失败", "perfile", "/admin/manager/perfile.do");
		}
		
		return "public/ajaxDone";
	}
	
	/**
	 * 验证个人数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-12 下午05:36:04
	 * @return boolean
	 */
	protected boolean _verifyFileData(HttpServletRequest request) {
		String email 	= request.getParameter("email");
		if(Verify.isEmpty(email)) {
			request.setAttribute("message", "邮箱不能为空");
			
			return false;
		}
		if(!Verify.isEmail(email)) {
			request.setAttribute("message", "邮箱格式不正确");
			
			return false;
		}
		if(Verify.isStrLen(email, 1, 30)) {
			request.setAttribute("message", "长度不超过30个字符");
			
			return false;
		}
		
		return true;
	}

	
	
	
	
	/**
	 * 
	 * 进入添加单个系部管理员页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-11 下午9:11:37
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/intoAddOneDeptMgr.do")
	public ModelAndView intoAddOneDeptMgr(ModelMap modelMap) throws VerifyException{
		List<Department> deptList = deptService.getAll("from Department");
		modelMap.put("deptList", deptList);	
		return new ModelAndView("admin/deptmanager/addOneDeptMgr");
	}
	
	/**
	 * 
	 * 添加一个系部管理员
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午5:03:16
	 * @return String
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addOneDeptMgr.do")
	public String addOneDeptMgr(HttpServletRequest request, ModelMap modelMap) throws VerifyException{
		List<Department> deptList = deptService.getAll("from Department");
		modelMap.put("deptList", deptList);	
		String dmId = request.getParameter("dmId");
		if(!this.verifyData(request)){
			request.setAttribute("statusCode", 300);
			return "public/ajaxDone";
		}
		if(!this.isExist(dmId, request)){
			return "public/ajaxDone";
		}
		Deptmanager deptMgr = new Deptmanager();
		deptMgr.setDmId(dmId);
		deptMgr.setDeptId(request.getParameter("orgLookup.id"));
		deptMgr.setDmName(request.getParameter("dmName"));
		deptMgr.setDmSex(request.getParameter("dmSex"));
		deptMgr.setDmTel(request.getParameter("dmTel"));
		deptMgr.setDmEmail(request.getParameter("dmEmail"));
		try{
			deptmanagerService.save(deptMgr);
			RequestSetAttribute.requestSetAttribute(
				request, 200, "closeCurrent", "添加成功", "deptMgrList", "admin/manager/DeptMgrList.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
				request, 300, "", "添加失败", "", "");
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 对相关数据验证 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 上午11:36:58
	 * @return boolean
	 */
	protected boolean verifyData(HttpServletRequest request) {
		String dmId = request.getParameter("dmId");
		String dmName = request.getParameter("dmName");
		String dmEmail = request.getParameter("dmEmail");
		if(Verify.isEmpty(dmId)){
			request.setAttribute("message", "管理员编号不能为空！");
			return false;
		}
		if(Verify.isEmpty(request.getParameter("orgLookup.id"))){
			request.setAttribute("message", "必须选择系部！");
			return false;
		}
		if(Verify.isEmpty(dmName)){
			request.setAttribute("message", "姓名不能为空");
			return false;
		}
		if(Verify.isEmpty(dmEmail)){
			request.setAttribute("message", "邮箱不能为空！");
			return false;			
		}
		if(!Verify.isStrLen(dmId, 3, 10)){
			request.setAttribute("message", "编号长度在3~10.");
			return false;
		}
		if(!Verify.isStrLen(dmName, 2, 14)){
			request.setAttribute("message", "姓名长度在2~14.");
			return false;
		}
		if(!Verify.isEmail(dmEmail)){
			request.setAttribute("message", "邮箱格式如：12345@163.com");
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 判断是否存在 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-19 下午3:35:46
	 * @return boolean
	 * @throws VerifyException 
	 */
	protected boolean isExist(String dmId,HttpServletRequest request) throws VerifyException{
		Deptmanager deptMgr = new Deptmanager();
		deptMgr = deptmanagerService.getOne(dmId);
		if (deptMgr != null && !dmId.equals("")) {
			request.setAttribute("statusCode", "300");
			request.setAttribute("message", ("记录" + dmId + "已存在！"));
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * lookup查找带回 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 下午3:37:07
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/lookup.do")
	public ModelAndView lookup(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav 			= new ModelAndView();
		List<Department> deptList 	= new ArrayList<Department>();
		int numPerPage = 10;
		mav.setViewName("admin/office/lookup");
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
		keyword = request.getParameter("orgName");
		String where = "from Department ";
		if(keyword == null || keyword.equals("")){
			deptList = deptService.getAllRecordByPages(where, pagination);
		}
		else{
			where = "from Department where deptName='" + keyword + "'";
		}
		deptList = deptService.getAllRecordByPages(where, pagination);
		if(deptList == null || deptList.size() < 1) {
			return new ModelAndView("admin/office/lookup", "error", "没有任何系部！请先添加系部！");
		}
		if(deptList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			deptList = (List<Department>) deptService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute(keyword, pagination, deptList, modelMap);
		
		return mav;
	}

	/**
	 * 
	 * 进入批量添加系部管理员页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-11 下午9:12:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/intoAddLotDeptMgr.do")
	public ModelAndView intoAddLotDeptMgr(){
		return new ModelAndView("admin/deptmanager/addLotDeptMgr");
	}
	
	/**
	 * 
	 * TODO 批量添加
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 下午4:35:18
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addLotDeptMgr.do")
	public String addLotDeptMgr(HttpServletRequest request) throws VerifyException{
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile excelFile = mulRequest.getFile("file");
		/**
		 * 判断文件格式
		 */
		if(!excelFile.getContentType().toString().equals("application/vnd.ms-excel")){
			RequestSetAttribute.requestSetAttribute(
					request, 300, "", "请选择指定的文件格式！", "", "");
			return "public/ajaxDone";
		} 
		String[][] result = ExcelIO.ExcelOperate(request, excelFile);
		int rowLength = result.length;
		for (int i = 0; i < rowLength; i++) {
			Deptmanager deptMgr = new Deptmanager();
			for (int j = 0; j < result[i].length; j++) {
				if (j == 0) {deptMgr.setDeptId(result[i][j]);}
				if (j == 1) {deptMgr.setDmId(result[i][j]);}
				if (j == 2) {deptMgr.setDmName(result[i][j]);}
				if (j == 3) {deptMgr.setDmSex(result[i][j]);}
				if (j == 4) {deptMgr.setDmTel(result[i][j]);}
				if (j == 5) {deptMgr.setDmEmail(result[i][j]);}
			}
			if (!this.isExist(deptMgr.getDmId(), request)) {
				return "public/ajaxDone";
			}
			else{
				try{
					deptmanagerService.save(deptMgr);
					RequestSetAttribute.requestSetAttribute(
							request, 200, "closeCurrent", "批量添加成功", "deptMgrList", "admin/manager/DeptMgrList.do");
				}catch(Exception e){
					RequestSetAttribute.requestSetAttribute(
							request, 300, "", "批量添加失败", "", "");
				}	
			}
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 进入系部管理员浏览页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-11 下午9:14:45
	 * @return ModelAndView
	 */
	@RequestMapping(value="/DeptMgrList.do")
	public ModelAndView deptMgrList(HttpServletRequest request, ModelMap modelMap){
		try{
			List<Department> deptList = deptService.getAll("from Department");
			modelMap.put("deptList", deptList);
			
			if(!Verify.isEmpty(request.getParameter("pageNum")))
			{
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			int size = 10;
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
			String where = "from Deptmanager";
			//keyword --- 系部管理员姓名--dmName
			//keywords --- 系部编号--deptId
			keyword = request.getParameter("keyword");
			keywords = request.getParameter("keywords");
			if((keywords == null || keywords.equals("")) && (keyword == null || keyword.equals(""))){
				where += "";
			}
			else if((keywords == null || keywords.equals("")) && keyword != null){
				where += " where dmName like '%" + keyword + "%'";
			}
			else if((keyword == null || keyword.equals("")) && keywords != null){
				where += " where deptId like '%" + keywords + "%'";
			}
			else
				where += " where deptId like '%" + keywords + "%' and dmName like '%"+ keyword + "%'";
			
			where += " order by deptId desc";
			deptMgrList = deptmanagerService.getAllRecordByPages(where, pagination);
			if(deptMgrList == null || deptMgrList.size() < 1) {
				return new ModelAndView("/admin/deptmanager/DeptMgrList", "error", "没有任何记录!");
			}
			if(this.deptMgrList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				deptMgrList = deptmanagerService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttributeO(
					keyword, keywords, pagination, deptMgrList, modelMap);
			TeacherController.assignSexMapMap(request);
			_assignDeptMap(deptMgrList, request);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return new ModelAndView("/admin/deptmanager/DeptMgrList");
	}
	
	/**
	 * 
	 * 删除一条系部管理员记录 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 上午11:51:04
	 * @return String
	 * @throws VerifyException 
	 */
	@RequestMapping(value="deleteOneDeptMgr.do")
	public String deleteOneDeptMgr(HttpServletRequest request) throws VerifyException{
		String dmId = request.getParameter("dmId");
		if(dmId.equals("") || dmId == null){
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "编号为空！");
			return "public/ajaxDone";
		}
		Deptmanager deptMgr = new Deptmanager();
		deptMgr = deptmanagerService.getOne(dmId);
		if(deptMgr == null){
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在！");
			return "public/ajaxDone";
		}
		try{
			deptmanagerService.remove(deptMgr);
			RequestSetAttribute.requestSetAttribute(
					request, 200, "", "删除成功", "deptMgrList", "admin/manager/DeptMgrList.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
					request, 300, "", "删除失败", "deptMgrList", "admin/manager/DeptMgrList.do");
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 进入修改系部管理员页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 上午11:57:02
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/intoEditOneDeptMgr.do")
	public ModelAndView intoEditOneDeptMgr(HttpServletRequest request) throws VerifyException{

		List<Department> deptList = deptService.getAll("from Department");
		request.setAttribute("deptList", deptList);
		
		Deptmanager deptMgr = deptmanagerService.getOne(request.getParameter("dmId"));
		request.setAttribute("deptMgr", deptMgr);
		_assignDeptMap(deptMgrList, request);
		
		return new ModelAndView("admin/deptmanager/editOneDeptMgr");
	}
	
	/**
	 * 
	 * 修改系部管理员信息提交方法 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-13 下午3:17:19
	 * @return String
	 */
	@RequestMapping(value="/editOneDeptMgr.do")
	public String editOneDeptMgr(HttpServletRequest request){
		if(!this.verifyData(request)){
			request.setAttribute("statusCode", 300);
			return "public/ajaxDone";
		}
		Deptmanager deptMgr = new Deptmanager();
		deptMgr.setDmId(request.getParameter("dmId"));
		deptMgr.setDeptId(request.getParameter("orgLookup.id"));
		deptMgr.setDmName(request.getParameter("dmName"));
		deptMgr.setDmSex(request.getParameter("sex"));
		deptMgr.setDmTel(request.getParameter("dmTel"));
		deptMgr.setDmEmail(request.getParameter("dmEmail"));
		try{
			deptmanagerService.editOneDeptmanager(deptMgr);
			RequestSetAttribute.requestSetAttribute(
				request, 200, "closeCurrent", "更新成功", "deptMgrList", "admin/manager/DeptMgrList.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
				request, 300, "", "更新失败", "", "");
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 导出EXCEL 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-18 上午9:27:16
	 * @return String
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/exportExcel.do")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response , ModelMap modelMap) throws VerifyException{
		deptMgrList = deptmanagerService.getAll("from Deptmanager order by deptId asc");
		OutputStream outStream = null;
		TeacherController.assignSexMapMap(request);
		_assignDeptMap(deptMgrList, request);
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("各系部管理员一览表");
			sheet.setDefaultColumnWidth((short) 18);
			sheet.setDefaultRowHeightInPoints(15);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.BORDER_HAIR);
			
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(25);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 1));
			cell.setCellValue(new HSSFRichTextString("怀化学院各系部管理员一览表"));
			
			HSSFRow row_2 = sheet.createRow((int) 1);
			row_2.setHeightInPoints(18);
			
			HSSFCell cell_2 = row_2.createCell((short) 0);
			cell_2.setCellValue("系部");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 1);
			cell_2.setCellValue("管理员ID");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 2);
			cell_2.setCellValue("姓名");
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
			cell_2.setCellValue("邮箱");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			int i = 0;
			for(Deptmanager mgrList : deptMgrList){
				row = sheet.createRow((int) i+2);
				row.setHeightInPoints(20);
				
				cell = row.createCell((short) 0);
				cell.setCellValue(mgrList.getDeptId() + "【" + HResponse.formatValue("dept", mgrList.getDeptId(), request) + "】");
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 1);
				cell.setCellValue(mgrList.getDmId());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 2);
				cell.setCellValue(mgrList.getDmName());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 3);
				cell.setCellValue(HResponse.formatValue("sex", mgrList.getDmSex(), request));
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 4);
				cell.setCellValue(mgrList.getDmTel());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				cell = row.createCell((short) 5);
				sheet.setColumnWidth(5, 6000);
				cell.setCellValue(mgrList.getDmEmail());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				
				/*row.createCell((short) 0).setCellValue(mgrList.getDeptId());
				row.createCell((short) 1).setCellValue(mgrList.getDmId());
				row.createCell((short) 2).setCellValue(mgrList.getDmName());
				row.createCell((short) 3).setCellValue(mgrList.getDmSex());
				row.createCell((short) 4).setCellValue(mgrList.getDmTel());
				row.createCell((short) 5).setCellValue(mgrList.getDmEmail());*/
				i++;
			}
			Region region = new Region();
			region.setColumnFrom((short) 0);
			region.setColumnTo((short) 5);
			region.setRowFrom(0);
			region.setRowTo(0);
			sheet.addMergedRegion(region);
			
			response.reset();
			response.setContentType("application/x-download;charset-GBK");
			response.setHeader("Content-Disposition", 
					"attachment;filename=USER_SITE_"+System.currentTimeMillis()+".xls");
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
}
