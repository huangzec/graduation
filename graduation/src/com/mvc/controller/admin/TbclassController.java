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
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Profession;
import com.mvc.entity.Tbclass;
import com.mvc.entity.Tbgrade;
import com.mvc.exception.VerifyException;
import com.mvc.service.ProfessionService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;

/**
 * 班级控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/tbclass")
public class TbclassController {
	
	@Autowired
	private TbclassService tbclassService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	private List<Profession> list = new ArrayList<Profession>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 20;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Profession> getList() {
		return list;
	}

	public void setList(List<Profession> list) {
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
	 * 添加班级视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午09:51:46
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Profession where 1 = 1 AND deptId = '" + department.getDeptId() + "'";
		String gradeWhere 	= "from Tbgrade where deptId = '" + department.getDeptId() + "' order by graNumber desc ";
		List professionList = professionService.getAllRowsByWhere(where);
		List gradeList 		= tbgradeService.getAllRowsByWhere(gradeWhere);
		mav.addObject("gradeList", gradeList);
		mav.addObject("professionList", professionList);
		mav.setViewName("admin/tbclass/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午09:53:02
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		String proId  	= request.getParameter("parent_id");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Tbclass tbclass = tbclassService.getRecordByWhere("from Tbclass where claId = '" + id + "' ");
		if(!Verify.isEmpty(tbclass)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "班级编号已存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		tbclass = new Tbclass();
		tbclass.setClaId(id.trim());
		tbclass.setClaName(name);
		tbclass.setProId(proId);
		try{
			tbclassService.addOneTbclass(tbclass);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "tbclasslist", "/admin/tbclass/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "添加失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 班级列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午09:54:13
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/tbclass/list");
		Department department 	= (Department) request.getSession().getAttribute("department");
		String grade 			= request.getParameter("grade");
		String profession 		= request.getParameter("profession");
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
		mav.addObject("grade", grade);
		mav.addObject("profession", profession);
		String where = "from Tbclass tbc where tbc.proId IN (select pro.proId from Profession pro where pro.deptId = '" 
				+ department.getDeptId() + "') order by tbc.claId desc";
		if(!Verify.isEmpty(grade)) {
			//如果年级不为空
			where = "from Tbclass tbc where tbc.proId IN (select pro.proId from Profession pro where pro.deptId = '" 
					+ department.getDeptId() + "' AND pro.graId = " + Integer.parseInt(grade) +
					") order by tbc.claId desc";
		}
		if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession)) {
			//如果年级不为空 ，并且专业不为空
			where = "from Tbclass tbc where tbc.proId = (select pro.proId from Profession pro where pro.deptId = '" 
					+ department.getDeptId() + "' AND pro.graId = " + Integer.parseInt(grade) +
					" AND pro.proId = '" + profession + "') order by tbc.claId desc";
		}
		try {
			_assignTbgradeList(request);
			_assignProfession(request);
			list = professionService.getAllRecordByPages(where, pagination);
			if(list == null || list.size() < 1) {
				
				return mav;
			}
			if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				list = (List<Profession>) professionService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
			mav.addObject("department", department);
			_assignProfessionListMap(list, request);
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
	 * 加载专业列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-6 下午09:37:41
	 * @return void
	 * @throws VerifyException 
	 */
	@SuppressWarnings("unchecked")
	private void _assignProfessionListMap(List<?> data, HttpServletRequest request) throws VerifyException {
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("proId", "proId", data);
		List<Profession> tempList 	= professionService.getAllRowsByWhere("from Profession where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("profession_map", ArrayUtil.turnListToMap("proId", "proName", tempList));
	}

	/**
	 * 删除班级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午09:58:18
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Tbclass tbclass = tbclassService.getRecordByWhere("from Tbclass where claId = '" + id + "' ");
		if(Verify.isEmpty(tbclass)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			tbclassService.removeOneTbclass(tbclass);
			RequestSetAttribute.requestSetAttribute(request, 200, "", "删除成功", "", "");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "删除失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 编辑班级视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午09:55:27
	 * @return ModelAndView
	 */
	@RequestMapping(value="/editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "id不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try {
			Tbclass tbclass = tbclassService.getRecordByWhere("from Tbclass where claId = '" + id + "' ");
			if(Verify.isEmpty(tbclass)) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			String where = "from Profession where 1 = 1 AND deptId = '" + department.getDeptId() + "'";
			_assignTbgradeList(request);
			String gradeWhere 	= "from Tbgrade where deptId = '" + department.getDeptId() + "' AND graId = (" +
					"select p.graId from Profession p where p.proId = (" +
					"select c.proId from Tbclass c where c.claId = '"+ tbclass.getClaId() + "'))";
			Tbgrade tbgrade 	= tbgradeService.getRecordByWhere(gradeWhere);
			List professionList = professionService.getAllRowsByWhere(where);
			mav.addObject("tbgrade", tbgrade);
			mav.addObject("professionList", professionList);
			mav.addObject("department", department);
			mav.addObject("tbclass", tbclass);
			mav.setViewName("admin/tbclass/edit");
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "服务器繁忙，请稍后再试");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 上午09:56:41
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Tbclass tbclass = tbclassService.getRecordByWhere("from Tbclass where claId = '" + id + "' ");
		if(Verify.isEmpty(tbclass)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		tbclass.setClaName(name);
		tbclass.setProId(request.getParameter("parent_id").toString());
		try{
			tbclassService.editOneTbclass(tbclass);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "tbclasslist", "/admin/tbclass/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}

	/**
	 * 验证数据
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-16 下午03:04:05
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String id  	= request.getParameter("id");
		String name = request.getParameter("name");
		String proId  	= request.getParameter("parent_id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("message", "班级编号不能为空");
			
			return false;
		}
		if(Verify.isEmpty(name)) {
			request.setAttribute("message", "班级名称不能为空");
			
			return false;
		}
		if(!Verify.isStrLen(id, 1, 64)) {
			request.setAttribute("message", "班级编号不能超过64个字符");
			
			return false;
		}
		if(!Verify.isStrLen(name, 1, 64)) {
			request.setAttribute("message", "班级名称不能超过64个字符");
			
			return false;
		}
		if(!Verify.isNumber(id)) {
			request.setAttribute("message", "班级编号不是一个数字");
			
			return false;
		}
		if(Verify.isEmpty(proId)) {
			request.setAttribute("message", "请先添加专业");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 异步加载班级数据
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/ajaxloaddata.do")
	public void ajaxLoaddata(HttpServletRequest request, HttpServletResponse response)
	{
		Department department 	= (Department) request.getSession().getAttribute("department");
		String dept 	= request.getParameter("dept");
		String grade 	= request.getParameter("grade");
		String profess 	= request.getParameter("profess");
		if(Verify.isEmpty(grade)) {
			return;
		}
		if(Verify.isEmpty(profess)) {
			return;
		}
		String where = "from Tbclass c where c.proId = (" +
				"select p.proId from Profession p where p.graId = " + Integer.parseInt(grade) + 
				" AND p.deptId = '" + (Verify.isEmpty(dept) ? department.getDeptId() : dept.trim()) + "' AND p.proId = '" + profess + "' )";
		try {
			List<Tbclass> tempList = tbclassService.getAllRows(where);
			
			HResponse.okJSON(null, ArrayUtil.listToJson("claId", "claName", tempList), response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
}
