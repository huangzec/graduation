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
import com.mvc.entity.Tbgrade;
import com.mvc.exception.VerifyException;
import com.mvc.service.ProfessionService;
import com.mvc.service.TbgradeService;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 专业控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/profession")
public class ProfessionController {

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
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午02:29:08
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbgrade where 1 = 1 AND deptId = '" + department.getDeptId() + "' order by graNumber desc";
		List gradeList = (List) tbgradeService.getAllRowsByWhere(where);
		mav.addObject("gradeList", gradeList);
		mav.addObject("department", department);
		mav.setViewName("admin/profession/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午02:30:13
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
		Profession profession = professionService.getOneById(id);
		if(profession != null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "专业编号已存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		profession = new Profession();
		profession.setProId(id);
		profession.setProName(name);
		profession.setGraId(Integer.parseInt(graid));
		profession.setDeptId(request.getParameter("parent_id"));
		try{
			professionService.addOneProfession(profession);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "professionlist", "/admin/profession/list.do");
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
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		if(Verify.isEmpty(id)) {
			request.setAttribute("message", "专业编号不能为空");
			
			return false;
		}
		if(Verify.isEmpty(name)) {
			request.setAttribute("message", "专业名称不能为空");
			
			return false;
		}
		if(!Verify.isStrLen(id, 1, 10)) {
			request.setAttribute("message", "专业编号不超过10个字符");
			
			return false;
		}
		if(!Verify.isStrLen(name, 1, 50)) {
			request.setAttribute("message", "专业名称不超过50个字符");
			
			return false;
		}
		if(!Verify.isNumber(id)) {
			request.setAttribute("message", "专业编号不是一个数字");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午02:31:21
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/profession/list");
		Department department 	= (Department) request.getSession().getAttribute("department");
		String grade 			= request.getParameter("grade");
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
		String where = "from Profession where deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(grade)) {
			where += " AND graId = " + Integer.parseInt(grade.trim());
			mav.addObject("keyword", grade);
		}
		where += " order by proId desc";
		try {
			_assignTbgradeList(request);
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
			_assignTbgradeListMap(list, request);
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
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午02:32:44
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
		Profession profession = professionService.getOneById(id);
		if(profession == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			professionService.removeOneProfession(profession);
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
	 * @date 2014-7-15 下午02:33:52
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		Profession profession = professionService.getOneById(id);
		if(profession == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String where = "from Tbgrade where 1 = 1 AND deptId = '" + department.getDeptId() + "' order by graNumber desc";
		List gradeList = (List) tbgradeService.getAllRowsByWhere(where);
		mav.addObject("gradeList", gradeList);
		mav.addObject("profession", profession);
		mav.addObject("department", department);
		mav.setViewName("admin/profession/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-15 下午02:34:58
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
		Profession profession = professionService.getOneById(id);
		if(profession == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			
			return mav;
		}
		profession.setProId(id);
		profession.setProName(name);
		profession.setDeptId(request.getParameter("parent_id"));
		profession.setGraId(Integer.parseInt(request.getParameter("graid")));
		try{
			professionService.editOneProfession(profession);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "professionlist", "/admin/profession/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "teacherlist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * ajax加载全部专业
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ajaxloaddata.do")
	public void ajaxLoaddata(HttpServletRequest request, HttpServletResponse response)
	{
		Department department 	= (Department) request.getSession().getAttribute("department");
		String dept 			= request.getParameter("dept");
		String grade 			= request.getParameter("grade");
		if(Verify.isEmpty(grade)) {
			return;
		}
		String where = "from Profession where deptId = '" + (Verify.isEmpty(dept) ? department.getDeptId() : dept.trim()) + 
				"' AND graId = " + Integer.parseInt(grade);
		try {
			List<Profession> tempList = professionService.getAllRowsByWhere(where);
			
			HResponse.okJSON(null, ArrayUtil.listToJson("proId", "proName", tempList), response);
		} catch (Exception e) {
			e.printStackTrace();
			
			HResponse.errorJSON(response);
		}
	}
	
}
