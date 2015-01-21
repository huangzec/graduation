package com.mvc.controller.admin;

import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Student;
import com.mvc.entity.Tballdoc;
import com.mvc.entity.Tbgrade;
import com.mvc.exception.VerifyException;
import com.mvc.service.StudentService;
import com.mvc.service.TballdocService;
import com.mvc.service.TbgradeService;


@Controller
@RequestMapping(value="/admin/rejoin/")
public class TballdocController {
	
	@Autowired
	private TballdocService alldocService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	
	private int pageNum = 1;//页数
	
	private int numPerPage = 20;//每页显示多少条
	
	private List<Tballdoc> alldoclist;

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

	public List<Tballdoc> getAlldoclist() {
		return alldoclist;
	}

	public void setAlldoclist(List<Tballdoc> alldoclist) {
		this.alldoclist = alldoclist;
	}
	
	/**
	 * 
	 * 本系部学生所有材料 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-28 上午10:36:12
	 * @return ModelAndView
	 */
	@RequestMapping(value="alldoclist.do")
	public ModelAndView alldoclist(HttpServletRequest request, ModelMap modelMap){
		Department dept   = (Department) request.getSession().getAttribute("department");
		ModelAndView mav  = new ModelAndView("admin/graduateinfo/all-doc-list");
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
		String grade 			= request.getParameter("gradeid");
		String profession 		= request.getParameter("professid");
		String tbclass 			= request.getParameter("claid");
		String where 			= "from Tballdoc where deptId = '" + dept.getDeptId() + "' AND docState = '1' ";
		if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession) && !Verify.isEmpty(tbclass)) {
			where += " AND stuId IN (" +
					"select s.stuId from Student s where s.claId = (" +
					"select c.claId from Tbclass c where c.claId = '" + tbclass.trim() + "' AND c.proId = (" +
					"select p.proId from Profession p where p.proId = '" + profession.trim() + "' AND p.deptId = '" + 
					dept.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}else if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession)) {
			where += " AND stuId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.proId = '" + profession.trim() + "' AND p.deptId = '" + 
					dept.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}else if(!Verify.isEmpty(grade)) {
			where += " AND stuId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.deptId = '" + 
					dept.getDeptId() + "' AND p.graId = " + Integer.parseInt(grade.trim()) + ") ) ) ";
		}
		where += " order by stuId asc";
		try {
			List<Tballdoc> alldoclist = alldocService.getAllRecordByPages(where, pagination);
			_assignStuName(request, dept.getDeptId());
			String gwhere = "from Tbgrade where deptId = '" + dept.getDeptId() + "' order by graNumber desc";
			mav.addObject("keyword", grade);
			request.setAttribute("tbgradeList",tbgradeService.getAllRowsByWhere(gwhere));
			_assignTbgradeListMap(tbgradeService.getAllRowsByWhere(gwhere), request);
			if(alldoclist == null || alldoclist.size() < 1){
				RequestSetAttribute.setPageAttribute("", pagination, alldoclist, modelMap);
				return mav;
			}
			if(alldoclist.size() == 0 && pagination.getCurrentPage() != 1){
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				alldoclist = alldocService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, alldoclist, modelMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}

	/**
	 * 
	 * 学生姓名加载 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-28 上午11:29:27
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignStuName(HttpServletRequest request, String deptId) throws VerifyException {
		String where = "from Student where claId in (" +
				"select claId from Tbclass where proId in " +
				"(select proId from Profession where deptId = '" + deptId + "' ))";
		List<Student> stulist = studentService.getAllRows(where);
		if(stulist == null || stulist.size() < 0){
			return;
		}
		request.setAttribute("stu_map", ArrayUtil.turnListToMap("stuId", "stuName", stulist));
	}

	/**
	 * 
	 * 加载年纪Map 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-10-28 下午05:13:25
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignTbgradeListMap(List<Tbgrade> tbgrade, HttpServletRequest request) throws VerifyException {
		if(Verify.isEmpty(tbgrade)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("graId", "graId", tbgrade);
		List<Tbgrade> tempList 	= tbgradeService.getAllRowsByWhere("from Tbgrade where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("grade_map", ArrayUtil.turnListToMap("graId", "graNumber", tempList));
	}
	
	/**
	 * 最终材料详细页
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/detail.do")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/graduateinfo/detail");
		Department department = (Department) request.getSession().getAttribute("department");
		String id 		= request.getParameter("id");
		String userId 	= request.getParameter("userid");
		if(Verify.isEmpty(id) || Verify.isEmpty(userId)) {
			mav.addObject("message", "编号不能为空");
			
			return mav;
		}
		String where = "from Tballdoc where id = " + Integer.parseInt(id) + " AND stuId = '" + userId + "' ";
		try {
			mav.addObject("tballdoc", alldocService.getRecordByWhere(where));
			_assignStuName(request, department.getDeptId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
}
