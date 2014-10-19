package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.mvc.common.SqlUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Gradeone;
import com.mvc.entity.Gradetwo;
import com.mvc.entity.Graduateinfo;
import com.mvc.entity.Revieworder;
import com.mvc.entity.Student;
import com.mvc.entity.Tbgrade;
import com.mvc.service.GradeoneService;
import com.mvc.service.GradetwoService;
import com.mvc.service.RevieworderService;
import com.mvc.service.StudentService;
import com.mvc.service.TbgradeService;

/**
 * 毕业评阅安排控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/user/revieworder")
public class RevieworderSiteController {

	@Autowired
	private RevieworderService revieworderService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private GradeoneService gradeoneService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GradetwoService gradetwoService;
	
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
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 毕业论文评阅查询视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午05:23:54
	 * @return ModelAndView
	 */
	@RequestMapping(value="/searchview.do")
	public ModelAndView searchView(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/search-view");
		_assignGradeInfo(request);
		
		return mav;
	}

	/**
	 * 加载年级信息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午06:00:58
	 * @return void
	 */
	private void _assignGradeInfo(HttpServletRequest request) 
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Tbgrade where deptId = '" + department.getDeptId() + 
			"' order by graNumber desc";
		
		request.setAttribute(
				"gradeList", 
				tbgradeService.getAllRowsByWhere(where)
				);		
	}

	/**
	 * 数据列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/list");
		Department department = (Department) request.getSession().getAttribute("dept");
		_assignGradeInfo(request);
		String grade 	= request.getParameter("grade");
		if(Verify.isEmpty(grade)) {
			request.setAttribute("message", "请选择毕业届别");
			
			return mav;
		}
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(!(request.getParameter("numPerPage") == null)) {
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
		String where = "from Revieworder where tbgradeId = '" + grade + 
			"' AND departmentId = '" + department.getDeptId() + 
			"' AND teacherId = '" + request.getSession().getAttribute("user_id") + 
			"' AND status = '1'";
		list = revieworderService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Revieworder>) revieworderService.getAllRecordByPages(where, pagination);
		}
		modelMap.put("list", list);
		modelMap.put("pagination", pagination);
		_assignStudentListMap(list, request);
		
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
	 * 指导老师是否已经评该学生成绩 
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-26 下午10:11:11
	 * @return void
	 */
	@RequestMapping(value="/guideteacherjudge.do")
	public void guideTeacherJudge(HttpServletRequest request, HttpServletResponse response)
	{
		String studentId 	= request.getParameter("stuId");
		if(Verify.isEmpty(studentId)) {
			HResponse.errorJSON("学生编号不能为空", response);
			return;
		}
		String where = "from Gradeone where stuId = '" + studentId + 
			"' AND status = '1' order by createTime desc";
		try {
			Gradeone gradeone = gradeoneService.getRecordByWhere(where);
			if(Verify.isEmpty(gradeone)) {
				HResponse.errorJSON("该学生的指导老师尚未评定成绩", response);
				return;
			}
			
			HResponse.okJSON(response);
		} catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
	
	/**
	 * 评阅教师评分
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午11:34:32
	 * @return void
	 */
	@RequestMapping(value="/scorereview.do")
	public void scoreReview(HttpServletRequest request, HttpServletResponse response)
	{
		try{
			Float one 		= Float.parseFloat(request.getParameter("one"));
			Float two 		= Float.parseFloat(request.getParameter("two"));
			Float three 	= Float.parseFloat(request.getParameter("three"));
			Float four 		= Float.parseFloat(request.getParameter("four"));
			Float five 		= Float.parseFloat(request.getParameter("five"));
			Float all 		= one + two + three + four + five;
			String content 	= request.getParameter("content");
			String userId 	= request.getParameter("userId");
			Gradetwo gradetwo = new Gradetwo();
			gradetwo.setStuId(userId);
			gradetwo.setGtOne(one);
			gradetwo.setGtTwo(two);
			gradetwo.setGtThree(three);
			gradetwo.setGtFour(four);
			gradetwo.setGtFive(five);
			gradetwo.setGtAll(all);
			gradetwo.setContent(content);
			gradetwo.setStatus("1");
			gradetwo.setCreateTime(HResponse.formatDateTime(new Date()));
			gradetwoService.addOne(gradetwo);
			
			HResponse.okJSON(response);
		}catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后重试", response);
		}
	}
	
	/**
	 * 标记已评阅
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-27 上午11:43:49
	 * @return void
	 */
	@RequestMapping(value="/deal.do")
	public void deal(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		if(Verify.isEmpty(id)) {
			HResponse.errorJSON("编号不能为空", response);
			return;
		}
		try{
			Revieworder revieworder = revieworderService.getOneRecordById(Integer.parseInt(id));
			if(Verify.isEmpty(revieworder)) {
				HResponse.errorJSON("记录不存在", response);
				return;
			}
			revieworder.setStatus("2");
			revieworderService.editOneRevieworder(revieworder);
			
			HResponse.okJSON("评审成功", response);
		}catch (Exception e) {
			HResponse.errorJSON("服务器繁忙，请稍后再试", response);
		}
	}
	
	/**
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
	/**
	 * 编辑视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/revieworder/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-26 16:22:26
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		
		return mav;
	}
	
}
