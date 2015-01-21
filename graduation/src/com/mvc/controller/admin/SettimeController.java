package com.mvc.controller.admin;

import java.util.ArrayList;
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
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Profession;
import com.mvc.entity.Settime;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Teacher;
import com.mvc.exception.VerifyException;
import com.mvc.service.SettimeService;
import com.mvc.service.TbgradeService;

/**
 * 设置时间控制器类
 * @author huangzec@foxmail.com
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/admin/settime")
public class SettimeController {
	
	@Autowired
	private SettimeService settimeService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	private List<Settime> list = new ArrayList<Settime>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Settime> getList() {
		return list;
	}

	public void setList(List<Settime> list) {
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
	 * 注册时间类型Map
	 */
	private static LinkedHashMap<String, String> _markMap = new LinkedHashMap<String, String>(){{
		put("1", "提交课题时间");
		put("2", "审核课题时间");
		put("3", "选择课题时间");
		//put("4", "查看课题时间");
		put("5", "开题答辩申请时间");
		put("6", "毕业答辩申请时间");
		put("7", "转让课题时间");
	}};

	/**
	 * 加载注册时间类型Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午06:14:49
	 * @return void
	 */
	private void _assignMarkMap(HttpServletRequest request)
	{
		request.setAttribute(
				"mark_list", 
				MapUtil.makeLinkedListMap(_markMap)
				);
	}
	
	/**
	 * 加载注册时间类型列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午05:30:39
	 * @return void
	 */
	private void _assignMarkListMap(HttpServletRequest request)
	{
		request.setAttribute("mark_map", MapUtil.makeLinkedMapMap(_markMap));
	}
	
	/**
	 * 设置提交课题时间
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午09:11:11
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/setsubmit.do")
	public ModelAndView setSubmit(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbgrade where 1 = 1 AND deptId = '" + department.getDeptId() + "' order by graNumber desc";
		List<Tbgrade> gradeList = (List<Tbgrade>) tbgradeService.getAllRowsByWhere(where);
		mav.addObject("gradeList", gradeList);
		mav.setViewName("admin/settime/setsubmit");
		_assignMarkMap(request);
		
		return mav;
	}
	
	/**
	 * 提交课题时间
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午09:12:59
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/setsub.do")
	public ModelAndView setSub(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String name 		= request.getParameter("name");
		String granumber 	= request.getParameter("granumber");
		String starttime 	= request.getParameter("start_time");
		String endtime 		= request.getParameter("end_time");
		String mark 		= request.getParameter("mark");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String where = "from Settime where deptId = '" + 
		department.getDeptId() + "' AND graNumber = '" + granumber + "' AND mark = '" + 
		mark + "'";
		Settime settime = settimeService.getOneByWhere(where);
		if(!Verify.isEmpty(settime)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "已经设置过该课题时间了");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		settime = new Settime();
		settime.setName(name);
		settime.setDeptId(department.getDeptId());
		settime.setGraNumber(granumber);
		settime.setStartTime(starttime);
		settime.setEndTime(endtime);
		settime.setMark(mark);
		try{
			settimeService.addOneSettime(settime);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "设置成功", "timeorder", "/admin/settime/timeorder.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "设置失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}

	/**
	 * 验证数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午09:18:24
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String name 		= request.getParameter("name");
		String granumber 	= request.getParameter("granumber");
		String starttime 	= request.getParameter("start_time");
		String endtime 		= request.getParameter("end_time");
		if(Verify.isEmpty(name)) {
			request.setAttribute("message", "标题不能为空");
			
			return false;
		}
		if(Verify.isEmpty(granumber)) {
			request.setAttribute("message", "年级不能为空");
			
			return false;
		}
		if(Verify.isEmpty(starttime)) {
			request.setAttribute("message", "开始时间不能为空");
			
			return false;
		}
		if(Verify.isEmpty(endtime)) {
			request.setAttribute("message", "结束时间不能为空");
			
			return false;
		}
		if(!Verify.isStrLen(name, 1, 64)) {
			request.setAttribute("message", "标题不超过64个字符");
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 删除一条记录
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午06:08:42
	 * @return String
	 */
	@RequestMapping(value="/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response)
	{
		String id 	= request.getParameter("id");
		if(id == null || id.trim().equals("")) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "ID不能为空");
			
			return "public/ajaxDone";
		}
		Settime st 	= settimeService.getRecordById(Integer.parseInt(id));
		if(st == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			
			return "public/ajaxDone";
		}
		try{
			settimeService.removeOne(st);
			RequestSetAttribute.requestSetAttribute(request, 200, "", "删除成功", "", "");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "删除失败", "", "");
		}
		
		return "public/ajaxDone";
	}
	
	/**
	 * 编辑视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午06:12:44
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		String id 			= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "记录不存在", "", "");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Settime settime = settimeService.getRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(settime)) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "记录不存在", "", "");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbgrade where 1 = 1 AND deptId = '" + department.getDeptId() + "' order by graNumber desc";
		List<Tbgrade> gradeList = (List<Tbgrade>) tbgradeService.getAllRowsByWhere(where);
		mav.addObject("gradeList", gradeList);
		mav.addObject("settime", settime);
		mav.setViewName("admin/settime/edit");
		_assignMarkMap(request);
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午06:13:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		String id 			= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "id不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Settime settime = settimeService.getRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(settime)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		settime.setName(request.getParameter("name"));
		settime.setGraNumber(request.getParameter("granumber"));
		settime.setStartTime(request.getParameter("start_time"));
		settime.setEndTime(request.getParameter("end_time"));
		settime.setMark(request.getParameter("mark"));
		try{
			settimeService.editOne(settime);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "timeorder", "/admin/settime/timeorder.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "teacherlist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 时间安排
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-17 上午10:21:50
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/timeorder.do")
	public ModelAndView timeOrder(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
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
		String where = "from Settime where deptId = '" + department.getDeptId() + "' order by id desc, startTime desc";
		list = settimeService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("admin/settime/list");
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Settime>) settimeService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
		mav.setViewName("admin/settime/list");
		mav.addObject("department", department);
		_assignMarkListMap(request);
		_assignGradeListMap(list, request);
		
		return mav;
	}

	/**
	 * 加载年级列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午05:43:18
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignGradeListMap(List<?> data, HttpServletRequest request) throws VerifyException {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("graId", "graNumber", data);
		List<Tbgrade> tempList 	= tbgradeService.getAllRowsByWhere("from Tbgrade where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("grade_map", ArrayUtil.turnListToMap("graId", "graNumber", tempList));
	}
}
