package com.mvc.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ExcelIO;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.entity.Department;
import com.mvc.entity.Deptmanager;
import com.mvc.service.DeptService;

/**
 * 操作系部
 * 
 * @author Happy_Jqc@163.com
 *
 */
@Controller
@RequestMapping(value="/admin/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	private String keyword;
	private Pagination pagination;
	private int pageNum=1;
	List<Department> deptList = new ArrayList<Department>();
	
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	/**
	 * 
	 * 进入单个添加系部页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-11 下午8:26:14
	 * @return ModelAndView
	 */
	@RequestMapping(value="/intoAddOneDept.do")
	public ModelAndView intoAddOneDept(){
		return new ModelAndView("admin/dept/addOneDept");
	}
	
	/**
	 * 
	 * 单个添加系部方法 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 上午10:53:57
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addOneDept.do")
	public String addOneDept(HttpServletRequest request){
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		
		if(!this.isExist(deptId, request)){
			return "public/ajaxDone";
		}
		if (!this.isNameOk(request, deptName)) {
			return "public/ajaxDone";
		}
		Department dept = new Department();
		dept.setDeptId(deptId);
		dept.setDeptName(deptName);
		try{
			deptService.save(dept);
			RequestSetAttribute.requestSetAttribute(
				request, 200, "closeCurrent", "系部添加成功", "deptList", "admin/dept/deptList.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
					request, 300, "", "系部添加失败", "intoAddOneDept", "/deptList.do");	
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 判断是否存在 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-19 下午4:33:40
	 * @return boolean
	 */
	protected boolean isExist(String deptId, HttpServletRequest request){
		Department dept = new Department();
		dept = deptService.getOneDept(deptId);
		if (dept != null && !dept.getDeptId().equals("")) {
			request.setAttribute("message", "记录" + dept.getDeptId() + "已存在");
			request.setAttribute("statusCode", 300);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 判断系部名称是否冲突 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-19 下午4:39:40
	 * @return boolean
	 */
	protected boolean isNameOk(HttpServletRequest request,String deptName){
		deptList = deptService.getAll("from Department");
		for (int j = 0; j < deptList.size(); j++) {
			if(deptName.equals(deptList.get(j).getDeptName())){
				request.setAttribute("message", "系部名称--" + deptName + "--冲突");
				request.setAttribute("statusCode", 300);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 进入批量添加系部页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-11 下午9:06:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="/intoAddLotDept.do")
	public ModelAndView intoAddLotDept(){
		return new ModelAndView("admin/dept/addLotDept");
	}
	
	/**
	 * 
	 * TODO 批量添加
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 上午10:54:51
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addLotDept.do")
	public String addLotDept(HttpServletRequest request, ModelMap modelMap){
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
			Department dept = new Department();
			for (int j = 0; j < result[i].length; j++) {
				if(j == 0){
					dept.setDeptId(result[i][j]);
				}
				if(j == 1){
					dept.setDeptName(result[i][j]);
				}
			}
			if (!this.isExist(dept.getDeptId(), request)) {
				return "public/ajaxDone";
			}else if (!this.isNameOk(request, dept.getDeptName())) {
				return "public/ajaxDone";
			}else{
				try{
					deptService.save(dept);
					RequestSetAttribute.requestSetAttribute(
						request, 200, "closeCurrent", "添加成功", "deptList", "admin/dept/deptList.do");
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
	 * TODO EXCEL导出
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-18 下午5:46:19
	 * @return String
	 */
	@RequestMapping(value="/excelExport.do")
	public String excelExport(HttpServletRequest request, HttpServletResponse response){
		deptList = deptService.getAll("from Department");
		OutputStream outStream = null;
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("系部一览表");
			sheet.setDefaultColumnWidth((short) 15);
			sheet.setDefaultRowHeightInPoints(15);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.BORDER_HAIR);
			
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(25);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 1));
			cell.setCellValue(new HSSFRichTextString("怀化学院系部一览表"));
			
			HSSFRow row_2 = sheet.createRow((int) 1);
			row_2.setHeightInPoints(18);
			HSSFCell cell_2 = row_2.createCell((short) 0);
			cell_2.setCellValue("系部ID");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			cell_2 = row_2.createCell((short) 1);
			cell_2.setCellValue("系部名称");
			cell_2.setCellStyle(style);
			cell_2.setCellStyle(ExcelIO.stylecreateTitle(wb, 2));
			
			int i = 0;
			for(Department deptlist : deptList){
				row = sheet.createRow((int) i+2);
				row.setHeightInPoints(20);
				cell = row.createCell((short) 0);
				cell.setCellValue(deptlist.getDeptId());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				sheet.setColumnWidth(1, 5000);
				cell = row.createCell((short) 1);
				cell.setCellValue(deptlist.getDeptName());
				cell.setCellStyle(style);
				cell.setCellStyle(ExcelIO.stylecreateTitle(wb, 3));
				
				/*row.createCell((short) 0).setCellValue(deptlist.getDeptId());
				row.createCell((short) 1).setCellValue(deptlist.getDeptName());*/
				i++;
			}
			Region region = new Region();
			region.setColumnFrom((short) 0);
			region.setColumnTo((short) 1);
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
		return "";
	}
	
	/**
	 * 
	 * 删除一个系部 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午5:44:10
	 * @return String
	 */
	@RequestMapping(value="/deleteOneDept.do")
	public String deleteOneDept(HttpServletRequest request){
		String deptId = request.getParameter("deptId");
		if(deptId.equals("") || deptId == null){
			request.setAttribute("statusCoed", 300);
			request.setAttribute("message", "系部编号为空！");
			return "public/ajaxDone";
		}
		Department dept = deptService.getOneDept(deptId);
		if(dept == null){
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "系部不存在！");
			return "public/ajaxDone";
		}
		try{
			deptService.remove(dept);
			RequestSetAttribute.requestSetAttribute(
				request, 200, "", "删除成功", "deptList", "/deptList.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
				request, 300, "", "删除失败", "deptList", "/deptList.do");
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 进入 更新系部信息页面
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午6:16:14
	 * @return ModelAndView
	 */
	@RequestMapping(value="/intoEditOneDept.do")
	public ModelAndView intoEditOneDept(HttpServletRequest request, ModelMap modelMap){
		String deptId = request.getParameter("deptId");
		Department dept = deptService.getOneDept(deptId);
		request.setAttribute("deptId", dept.getDeptId());
		request.setAttribute("deptName", dept.getDeptName());
		return new ModelAndView("admin/dept/editOneDept");
	}
	
	/**
	 * 
	 * 更新提交方法 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午7:34:02
	 * @return String
	 */
	@RequestMapping(value="/editOneDept.do")
	public String editOneDept(HttpServletRequest request){
		Department dept = new Department();
		String deptName = request.getParameter("deptName");
		deptList = deptService.getAll("from Department");
		for (int i = 0; i < deptList.size(); i++) {
			if(deptName.equals(deptList.get(i).getDeptName())){
				RequestSetAttribute.requestSetAttribute(request, 300, "", "系部名称冲突", "", "");
				return "public/ajaxDone";
			}
		}
		try{
			dept.setDeptId(request.getParameter("deptId"));
			dept.setDeptName(deptName);
			deptService.editOneDept(dept);
			RequestSetAttribute.requestSetAttribute(
					request, 200, "closeCurrent", "系部信息修改成功", "deptList", "/deptList.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
				request, 300, "", "系部信息修改失败", "", "/editOneDept.do");
		}
		return "public/ajaxDone";
	}
	
	/**
	 * 
	 * 进入系部浏览页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-11 下午9:08:50
	 * @return ModelAndView
	 */
	@RequestMapping(value="/deptList.do")
	public ModelAndView deptList(HttpServletRequest request, ModelMap modelMap){
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
		keyword = request.getParameter("keyword");
		String where = "from Department";
		if(keyword == null || keyword.equals("")){
			where += "";
		}
		else{
			where += " where deptName like '%" + keyword + "%'";
		}
		where += " order by deptId asc";
		deptList = deptService.getAllRecordByPages(where, pagination);
		if(deptList == null || deptList.size() < 1) {
			return new ModelAndView("admin/dept/deptList", "error", "没有任何记录!");
		}
		if(this.deptList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			deptList = (List<Department>) deptService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute(keyword, pagination, deptList, modelMap);
		modelMap.put("deptList", deptList);	
		
		return new ModelAndView("admin/dept/deptList");
	}
	
}
