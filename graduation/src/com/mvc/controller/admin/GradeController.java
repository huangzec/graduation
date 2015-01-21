package com.mvc.controller.admin;

import java.util.ArrayList;
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
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Profession;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Teacher;
import com.mvc.exception.VerifyException;
import com.mvc.service.TbgradeService;

/**
 * 年级控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/grade")
public class GradeController {
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	private List<Tbgrade> list = new ArrayList<Tbgrade>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
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

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<Tbgrade> getList() {
		return list;
	}

	public void setList(List<Tbgrade> list) {
		this.list = list;
	}

	/**
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 上午12:30:15
	 * @return String
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/grade/add");
		mav.addObject("department", (Department) request.getSession().getAttribute("department"));
		
		return mav;
	}
	
	/**
	 * 添加实现方法
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 上午12:32:25
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse reHttpServletResponse) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String number 	= request.getParameter("number");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String where = "from Tbgrade where 1 = 1 AND deptId ='" +
		department.getDeptId() + "' AND graNumber = '" + number.trim() + "'";
		Tbgrade tbgrade = tbgradeService.getRecordByWhere(where);
		if(!Verify.isEmpty(tbgrade)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录已存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		tbgrade = new Tbgrade();
		tbgrade.setDeptId(department.getDeptId());
		tbgrade.setGraNumber(number.trim());
		try{
			tbgradeService.addOneTbgrade(tbgrade);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "gradelist", "/admin/grade/list.do");
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
	 * @date 2014-7-15 下午03:28:10
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String number 	= request.getParameter("number");
		if(Verify.isEmpty(number)) {
			request.setAttribute("message", "届数不能为空");
			
			return false;
		}
		if(!Verify.isStrLen(number, 1, 8)) {
			request.setAttribute("message", "届数不超过8个字符");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 年级列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午12:54:31
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap) throws VerifyException
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
		String where = "from Tbgrade where 1 = 1 AND deptId = '" + department.getDeptId() + "' order by graNumber desc";
		list = tbgradeService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("admin/grade/list");
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Tbgrade>) tbgradeService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
		mav.setViewName("admin/grade/list");
		mav.addObject("department", department);
		
		return mav;
	}
	
	/**
	 * 删除一个年级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午01:21:28
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
		Tbgrade tbgrade = tbgradeService.getOneGradeById(Integer.parseInt(id));
		if(tbgrade == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			tbgradeService.removeOneGrade(tbgrade);
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
	 * @date 2014-7-15 下午01:43:46
	 * @return ModelAndView
	 */
	@RequestMapping(value="/editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		Tbgrade tbgrade = tbgradeService.getOneGradeById(Integer.parseInt(id));
		if(tbgrade == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("grade", tbgrade);
		mav.addObject("department", department);
		mav.setViewName("admin/grade/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午01:50:16
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 		= request.getParameter("id");
		String number 	= request.getParameter("number");
		if(Verify.isEmpty(id)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "id不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Tbgrade tbgrade = tbgradeService.getOneGradeById(Integer.parseInt(id));
		if(Verify.isEmpty(tbgrade)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String where = "from Tbgrade where 1 = 1 AND deptId ='" +
		department.getDeptId() + "' AND graNumber = '" + number.trim() + "'";
		Tbgrade temptbgrade = tbgradeService.getRecordByWhere(where);
		if(!Verify.isEmpty(temptbgrade)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录已存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		tbgrade.setGraNumber(number);
		try{
			tbgradeService.editOneGrade(tbgrade);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "gradelist", "/admin/grade/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}

	/**
	 * 异步加载年级数据
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/ajaxloaddata.do")
	public void ajaxLoaddata(HttpServletRequest request, HttpServletResponse response)
	{
		String dept 	= request.getParameter("dept");
		if(Verify.isEmpty(dept)) {
			HResponse.errorJSON(response);
			
			return;
		}
		String where = "from Tbgrade where deptId = '" + dept.trim() + "' order by graNumber desc ";
		try {
			List<Tbgrade> tempList = tbgradeService.getAllRowsByWhere(where);
			
			HResponse.okJSON(null, ArrayUtil.listToJson("graId", "graNumber", tempList), response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
}
