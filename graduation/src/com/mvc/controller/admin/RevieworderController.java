package com.mvc.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.HResponse;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.dao.SelectfirstDao;
import com.mvc.entity.Department;
import com.mvc.entity.Revieworder;
import com.mvc.entity.Selectfirst;
import com.mvc.entity.Student;
import com.mvc.entity.Tbgrade;
import com.mvc.entity.Teacher;
import com.mvc.service.ProfessionService;
import com.mvc.service.RevieworderService;
import com.mvc.service.StudentService;
import com.mvc.service.TbclassService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TeacherService;

/**
 * 毕业评阅安排控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/revieworder")
public class RevieworderController {

	@Autowired
	private RevieworderService revieworderService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private SelectfirstDao selectfirstDao;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private TbclassService tbclassService;
	
	private Pagination pagination;
	private List<Revieworder> list = new ArrayList<Revieworder>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Revieworder> getList() {
		return list;
	}

	public void setList(List<Revieworder> list) {
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
	 * 注册状态Map
	 */
	private static LinkedHashMap<String, String> _statusMap = new LinkedHashMap<String, String>(){{
		put("1", "未评");
		put("2", "已评");
	}};
	
	public static void assignStatusMap(HttpServletRequest request)
	{
		request.setAttribute(
				"status_map", 
				MapUtil.makeLinkedMapMap(_statusMap)
				);
	}
	
	/**
	 * 选择年级
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-28 下午09:47:45
	 * @return ModelAndView
	 */
	@RequestMapping(value="/grade.do")
	public ModelAndView grade(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/revieworder/order-grade");
		Department department = (Department) request.getSession().getAttribute("department");
		String where = "from Tbgrade where deptId = '" + department.getDeptId() + 
			"' order by graNumber desc";
		request.setAttribute(
				"gradeList", 
				tbgradeService.getAllRowsByWhere(where)
				);
		
		return mav;
	}

	/**
	 * 毕业评阅教师安排
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/revieworder/order-list");
		Department department = (Department) request.getSession().getAttribute("department");
		String grade 	= request.getParameter("grade");
		if(Verify.isEmpty(grade)) {
			request.setAttribute("message", "请选择年级");
			
			return mav;
		}
		String proWhere = "from Profession where graId = '" + grade + 
			"' AND deptId = '" + department.getDeptId() + "'";
		String claWhere = "from Tbclass where " + 
			SqlUtil.whereIn("proId", "proId", professionService.getAllRowsByWhere(proWhere));
		String stuWhere = "from Student where " + 
			SqlUtil.whereIn("claId", "claId", tbclassService.getAllRows(claWhere));
		String teaWhere = "from Teacher where deptId = '" + department.getDeptId() + "'";
		String selWhere = "from Selectfirst where ";
		List<Student> stuList = studentService.getAllRows(stuWhere);
		List<Teacher> teaList = teacherService.getAllRows(teaWhere);
		final List<Selectfirst> selList 	= selectfirstDao.getAll(selWhere + SqlUtil.whereIn("stuId", "stuId", stuList));
		if(null == teaList || 0 == teaList.size()) {
			request.setAttribute("message", "教师记录为空，请先添加教师");
			
			return mav;
		}
		if(null == selList || 0 == selList.size()) {
			request.setAttribute("message", "没找到课题选择记录，暂时无法安排论文评阅");
			
			return mav;
		}
		Map<String, Map<String, String>> _reviewOrderMap = new HashMap<String, Map<String, String>>();
		if(null != stuList && 0 < stuList.size()) {
			for(int i = 0; i < stuList.size(); i ++) {
				Map<String, String> _orderTeacherMap = new HashMap<String, String>();
				//确定学生的指导老师
				boolean isHasOrderTeacher = true;//是否有指导老师
				for(int j = 0; j < selList.size(); j ++) {
					if(stuList.get(i).getStuId().equals(selList.get(j).getStuId())) {
						_orderTeacherMap.put("orderteacher", selList.get(j).getTeaId().toString());
						isHasOrderTeacher = false;
						break;
					}
				}
				if(isHasOrderTeacher) {
					_orderTeacherMap.put("orderteacher", null);//没有指导老师
				}
				_reviewOrderMap.put(
						stuList.get(i).getStuId().toString(), 
						_orderTeacherMap
						);
			}
			//随机分配评阅老师 且不能和指导老师相同
			Iterator<Entry<String, Map<String, String>>> iter 	= _reviewOrderMap.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<String, Map<String, String>> item 	= iter.next();
				String orderTea = item.getValue().get("orderteacher");
				Random random 	= new Random();
				int randNum		= random.nextInt(teaList.size());
				if(null != orderTea) {
					while(orderTea.equals(teaList.get(randNum).getTeaId())) {
						randNum		= random.nextInt(teaList.size());
					}
				}
				
				item.getValue().put("reviewteacher", teaList.get(randNum).getTeaId());
			}
		}
		mav.addObject("orderMap", _reviewOrderMap);
		request.getSession().setAttribute("reviewOrderMap", _reviewOrderMap);
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", stuList));
		request.setAttribute("teaId_map", ArrayUtil.turnListToMap("teaId", "teaName", teaList));
		mav.addObject("targetType", "navTab");
		mav.addObject("grade", grade);
		
		return mav;
	}
	
	/**
	 * 确认毕业评阅教师安排
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String grade 	= request.getParameter("grade");
		Map<String, Map<String, String>> _reviewOrderMap = (Map<String, Map<String, String>>) request.getSession().getAttribute("reviewOrderMap");
		if(null == _reviewOrderMap || _reviewOrderMap.isEmpty()) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "没有相关数据，无法完成安排");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		if(Verify.isEmpty(grade)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "年级不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		grade = grade.trim();
		String where = "from Revieworder where departmentId = '" + 
			department.getDeptId() + "' AND tbgradeId = '" + grade + "'";
		List<Revieworder> reviewList = revieworderService.getAllRowsByWhere(where);
		Iterator<Entry<String, Map<String, String>>> iter 	= _reviewOrderMap.entrySet().iterator();
		try {
			while(iter.hasNext()) {
				Entry<String, Map<String, String>> item 	= iter.next();
				String studentId 	= item.getKey();
				boolean isHasUnReview = true;
				if(null != reviewList && 0 < reviewList.size()) {
					for(int i = 0; i < reviewList.size(); i ++) {
						if(studentId.equals(reviewList.get(i).getStudentId()) && reviewList.get(i).getStatus().equals("1")) {
							isHasUnReview = false;
							break;
						}
					}
				}
				if(isHasUnReview) {
					Revieworder revieworder = new Revieworder();
					revieworder.setStudentId(studentId);
					revieworder.setTeacherId(item.getValue().get("reviewteacher"));
					revieworder.setDepartmentId(department.getDeptId());
					revieworder.setTbgradeId(grade);
					revieworder.setStatus("1");
					revieworder.setCreateTime(HResponse.formatDateTime(new Date()));
					revieworderService.addOne(revieworder);
				}
			}
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "安排成功", "revieworder", "/admin/revieworder/list.do");
		} catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "安排出错", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 数据验证
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String id 		= request.getParameter("id");
		String name 	= request.getParameter("name");
		if(id.trim().equals("")) {
			request.setAttribute("message", "毕业评阅安排编号不能为空");
			
			return false;
		}
		if(name.trim().equals("")) {
			request.setAttribute("message", "毕业评阅安排名称不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
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
		String where = "from Revieworder where departmentId = '" + 
			department.getDeptId() + "' order by createTime desc";
		list = revieworderService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("admin/revieworder/list");
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Revieworder>) revieworderService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
		mav.setViewName("admin/revieworder/list");
		_assignStudentListMap(list, request);
		_assignTeacherListMap(list, request);
		assignStatusMap(request);
		_assignGradeListMap(list, request);
		
		return mav;
	}
	
	/**
	 * 加载学生列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 */
	private void _assignStudentListMap(List<Revieworder> data, HttpServletRequest request) 
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("stuId", "studentId", data);
		List<Student> tempList 	= studentService.getAllRows("from Student where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("stuId_map", ArrayUtil.turnListToMap("stuId", "stuName", tempList));
	}
	
	/**
	 * 加载教师列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 */
	private void _assignTeacherListMap(List<Revieworder> data, HttpServletRequest request) 
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("teaId", "teacherId", data);
		List<Teacher> tempList 	= teacherService.getAllRows("from Teacher where " + whereIn);
		if(Verify.isEmpty(tempList)) {
			return;
		}
		
		request.setAttribute("teaId_map", ArrayUtil.turnListToMap("teaId", "teaName", tempList));
	}
	
	/**
	 * 加载年级列表Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-22 下午03:34:46
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void _assignGradeListMap(List<Revieworder> data, HttpServletRequest request) 
	{
		if(Verify.isEmpty(data)) {
			return;
		}
		String whereIn 	= SqlUtil.whereIn("graId", "tbgradeId", data);
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
	 * @date 2014-09-26 11:30:22
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
		Revieworder revieworder = revieworderService.getOneRecordById(Integer.parseInt(id));
		if(revieworder == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			revieworderService.removeOneRevieworder(revieworder);
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
	 * @date 2014-09-26 11:30:22
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		Revieworder revieworder = revieworderService.getOneRecordById(Integer.parseInt(id));
		if(revieworder == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("revieworder", revieworder);
		mav.setViewName("admin/revieworder/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 11:30:22
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
		Revieworder revieworder = revieworderService.getOneRecordById(Integer.parseInt(id));
		if(revieworder == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			
			return mav;
		}
		try{
			revieworderService.editOneRevieworder(revieworder);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "revieworderlist", "/admin/revieworder/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "revieworderlist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
}
