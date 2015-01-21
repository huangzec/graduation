package com.mvc.controller.admin;

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
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.Verify;
import com.mvc.controller.TopicController;
import com.mvc.entity.Department;
import com.mvc.entity.Reviewresult;
import com.mvc.entity.Tbtopic;
import com.mvc.entity.Teacher;
import com.mvc.entity.Topicorderreview;
import com.mvc.exception.VerifyException;
import com.mvc.service.ProfessionService;
import com.mvc.service.ReviewresultService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TbtopicService;
import com.mvc.service.TeacherService;
import com.mvc.service.TopicorderreviewService;

/**
 * 课题评审教师安排控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/topicorderreview")
public class TopicorderreviewController {

	@Autowired
	private TopicorderreviewService topicorderreviewService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private ReviewresultService reviewresultService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	@Autowired
	private ProfessionService professionService;
	
	private Pagination pagination;
	private List<Topicorderreview> list = new ArrayList<Topicorderreview>();
	private List<Teacher> teacherList 	= new ArrayList<Teacher>();
	private List<Tbtopic> topicList 	= new ArrayList<Tbtopic>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 20;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Topicorderreview> getList() {
		return list;
	}

	public void setList(List<Topicorderreview> list) {
		this.list = list;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<Tbtopic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Tbtopic> topicList) {
		this.topicList = topicList;
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
	 * @date 2014-11-14 21:10:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String judgeYear 	= request.getParameter("judge-year");
		String judgeTea 	= request.getParameter("tea.id");
		String name 		= request.getParameter("name");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String where = "from Topicorderreview where departmentId = '" + department.getDeptId() + 
				"' AND judgeYear = '" + judgeYear.toString().trim() + "' ";
		try{
			Topicorderreview topicorderreview = topicorderreviewService.getRecordByWhere(where);
			if(!Verify.isEmpty(topicorderreview)) {
				mav.addObject("statusCode", 300);
				mav.addObject("message", "课题评审年份" + judgeYear + "已存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			topicorderreview = new Topicorderreview();
			topicorderreview.setName(name);
			topicorderreview.setDepartmentId(department.getDeptId());
			topicorderreview.setJudgeTea(judgeTea);
			topicorderreview.setJudgeYear(judgeYear);
			topicorderreview.setCreateTime(HResponse.formatDateTime(new Date()));
			
			topicorderreviewService.addOne(topicorderreview);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "安排成功", "topicorderreviewlist", "/admin/topicorderreview/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "安排失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 数据验证
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String judgeYear 	= request.getParameter("judge-year");
		String judgeTea 	= request.getParameter("tea.id");
		if(Verify.isEmpty(judgeYear)) {
			request.setAttribute("message", "课题评审年份不能为空");
			
			return false;
		}
		if(!Verify.isNumber(judgeYear)) {
			request.setAttribute("message", "课题评审年份不是一个数字");
			
			return false;
		}
		if(Verify.isEmpty(judgeTea)) {
			request.setAttribute("message", "课题评审教师不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/list");
		Department department = (Department) request.getSession().getAttribute("department");
		String year 		= request.getParameter("year");
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
		String where = "from Topicorderreview where departmentId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(year)) {
			where += " AND judgeYear = '" + year + "' ";
			mav.addObject("year", year);
		}
		where += " order by createTime desc ";
		try {
			String begroupWhere = "select judgeYear from Topicorderreview where departmentId = '" + 
					department.getDeptId() + "' group by judgeYear ";
			List<Topicorderreview> begroupList = topicorderreviewService.getAllRowsByWhere(begroupWhere);
			mav.addObject("begroupList", begroupList);
			list = topicorderreviewService.getAllRecordByPages(where, pagination);
			if(list == null || list.size() < 1) {
				
				return mav;
			}
			if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				list = (List<Topicorderreview>) topicorderreviewService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);	
			_assignJudgeListMap(list, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "ID不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Topicorderreview topicorderreview = topicorderreviewService.getOneRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(topicorderreview)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			topicorderreviewService.removeOneTopicorderreview(topicorderreview);
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
	 * @date 2014-11-14 21:10:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		try {
			Topicorderreview topicorderreview = topicorderreviewService.getOneRecordById(Integer.parseInt(id));
			if(Verify.isEmpty(topicorderreview)) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			mav.addObject("topicorderreview", topicorderreview);
			mav.setViewName("admin/topicorderreview/edit");
			_assignJudgeMapMap(topicorderreview, request);
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("admin/topicorderreview/edit");
		}
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-11-14 21:10:41
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		String name = request.getParameter("name");
		String judgeYear 	= request.getParameter("judge-year");
		String judgeTea 	= request.getParameter("tea.id");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		if(Verify.isEmpty(id)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			mav.addObject("message", "编号不能为空");
			
			return mav;
		}
		try{
			Topicorderreview topicorderreview = topicorderreviewService.getOneRecordById(Integer.parseInt(id));
			if(Verify.isEmpty(topicorderreview)) {
				mav.addObject("statusCode", 300);
				mav.addObject("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				
				return mav;
			}
			topicorderreview.setName(name);
			topicorderreview.setJudgeTea(judgeTea);
			topicorderreviewService.editOneTopicorderreview(topicorderreview);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "topicorderreviewlist", "/admin/topicorderreview/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "topicorderreviewlist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 *  lookup查找教师列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lookup.do")
	public ModelAndView lookup(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/lookuptea");
		Department department = (Department) request.getSession().getAttribute("department");
		String pos 	= request.getParameter("pos");
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
		pagination.setSize(100);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Teacher where deptId = '" + department.getDeptId() + "' AND status = '1'";
		if(!Verify.isEmpty(pos)) {
			if(pos.toString().trim().equals("0")) {
				where += " AND teaPos IN ('0', '1', '2', '3')";
			}else if(pos.toString().trim().equals("1")) {
				where += " AND teaPos IN ('1', '2', '3')";
			}else if(pos.toString().trim().equals("2")) {
				where += " AND teaPos IN ('2', '3')";
			}else if(pos.toString().trim().equals("3")) {
				where += " AND teaPos IN ('3')";
			}else if(pos.toString().trim().equals("4")) {
				where += " AND teaPos NOT IN ('0', '1', '2', '3')";
			}
		}
		where += " order by teaId asc ";
		try {
			teacherList = teacherService.getAllRecordByPages(where, pagination);
			if(teacherList == null || teacherList.size() < 1) {
				
				return mav;
			}
			if(this.teacherList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				teacherList = (List<Teacher>) teacherService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, teacherList, modelMap);
			TeacherController.assignSexMapMap(request);
			TeacherController.assignStatusMap(request);
			TeacherController.assignTeacherposMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 查看详细记录
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
		mav.setViewName("admin/topicorderreview/detail");
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			return mav;
		}
		try {
			Topicorderreview topicorderreview = topicorderreviewService.getOneRecordById(Integer.parseInt(id.trim()));
			mav.addObject("topicorderreview", topicorderreview);
			_assignJudgeMapMap(topicorderreview, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 加载评审教师Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignJudgeMapMap(Topicorderreview topicorderreview, HttpServletRequest request) throws VerifyException 
	{
		if(Verify.isEmpty(topicorderreview) || Verify.isEmpty(topicorderreview.getJudgeTea())) {
			return;
		}
		String[] ids = null;
		ids 	= topicorderreview.getJudgeTea().split(",");
		StringBuffer id 	= new StringBuffer("(");
		for(int i = 0; i < ids.length; i ++) {
			id.append("'" + ids[i] + "',");
		}
		id.deleteCharAt(id.length() - 1);
		id.append(")").toString();
		List<Teacher> tempList 	= teacherService.getAllRows("from Teacher where teaId IN " + id);
		if(null == tempList || 1 > tempList.size()) {
			return;
		}
		
		request.setAttribute("judge_map", ArrayUtil.turnListToMap("teaId", "teaName", tempList));
	}

	/**
	 * 加载评审教师列表Map
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param data
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignJudgeListMap(List<Topicorderreview> data, HttpServletRequest request) throws VerifyException
	{
		if(null == data || 1 > data.size()) {
			return;
		}
		String[] ids = null;
		String tempString = "";
		for(int i = 0; i < data.size(); i ++) {
			tempString += data.get(i).getJudgeTea() + ",";
		}
		ids 	= tempString.split(",");
		StringBuffer id 	= new StringBuffer("(");
		for(int i = 0; i < ids.length; i ++) {
			id.append("'" + ids[i] + "',");
		}
		id.deleteCharAt(id.length() - 1);
		id.append(")").toString();
		List<Teacher> tempList 	= teacherService.getAllRows("from Teacher where teaId IN " + id);
		if(null == tempList || 1 > tempList.size()) {
			return;
		}
		
		request.setAttribute("judge_map", ArrayUtil.turnListToMap("teaId", "teaName", tempList));
	}
	
	/**
	 * 课题评审结果
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/topicresult.do")
	public ModelAndView topicResult(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/topic-judge-result");
		Department department = (Department) request.getSession().getAttribute("department");
		String year 		= request.getParameter("year");
		String submitType 	= request.getParameter("submittype");
		String submiter 	= request.getParameter("submiter");
		String topicStatus 	= request.getParameter("topicstatus");
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
		String where 		= "from Tbtopic where deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(year)) {
			where += " AND topYear = '" + year.trim() + "' ";
			mav.addObject("year", year);
		}
		if(!Verify.isEmpty(submitType)) {
			where += " AND topFlag = '" + submitType + "' ";
			mav.addObject("submittype", submitType);
		}
		if(!Verify.isEmpty(topicStatus)) {
			where += " AND topStatus = '" + topicStatus + "' ";
			mav.addObject("topicstatus", topicStatus);
		}
		if(!Verify.isEmpty(submiter)) {
			where += " AND topCommitId LIKE '%" + submiter + "%' ";
			mav.addObject("submiter", submiter);
		}
		where += "order by topYear desc ";
		try {
			AdmintopicController.assignStatusListMap(request);
			AdmintopicController.assignTypeListMap(request);
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
			String begroupWhere = "select topYear from Tbtopic where deptId = '" + 
					department.getDeptId() + "' group by topYear ";
			List<Tbtopic> begroupList = tbtopicService.getAll(begroupWhere);
			mav.addObject("begroupList", begroupList);
			if(topicList == null || topicList.size() < 1) {
				
				return mav;
			}
			if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, topicList, modelMap);
			AdmintopicController.assignStatusMap(request);
			TopicController.assignSelSource(request);
			TopicController.assignSelType(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 课题提交信息列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/topictotallist.do")
	public ModelAndView topicTotal(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/topic-submit-list");
		Department department = (Department) request.getSession().getAttribute("department");
		String year 		= request.getParameter("year");
		String submitType 	= request.getParameter("submittype");
		String submiter 	= request.getParameter("submiter");
		String topicStatus 	= request.getParameter("topicstatus");
		String tag 			= request.getParameter("tag");
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
		String where 		= "from Tbtopic where deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(year)) {
			where += " AND topYear = '" + year.trim() + "' ";
			mav.addObject("year", year);
		}
		if(!Verify.isEmpty(submitType)) {
			where += " AND topFlag = '" + submitType + "' ";
			mav.addObject("submittype", submitType);
		}
		if(!Verify.isEmpty(topicStatus)) {
			where += " AND topStatus = '" + topicStatus + "' ";
			mav.addObject("topicstatus", topicStatus);
		}
		if(!Verify.isEmpty(submiter)) {
			where += " AND topCommitId LIKE '%" + submiter + "%' ";
			mav.addObject("submiter", submiter);
		}
		if(!Verify.isEmpty(tag)) {
			where += " AND topKeywords LIKE '%" + tag + "%' ";
			mav.addObject("tag", tag);
		}
		where += "order by topYear desc ";
		try {
			AdmintopicController.assignStatusListMap(request);
			AdmintopicController.assignTypeListMap(request);
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
			String begroupWhere = "select topYear from Tbtopic where deptId = '" + 
					department.getDeptId() + "' group by topYear ";
			List<Tbtopic> begroupList = tbtopicService.getAll(begroupWhere);
			mav.addObject("begroupList", begroupList);
			if(topicList == null || topicList.size() < 1) {
				
				return mav;
			}
			if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, topicList, modelMap);
			TopicController.assignSelSource(request);
			TopicController.assignSelType(request);
			AdmintopicController.assignStatusMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 高级检索 课题提交信息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/topicsearchlist.do")
	public ModelAndView topicSearchList(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/topic-search-list");
		Department department = (Department) request.getSession().getAttribute("department");
		String year 		= request.getParameter("year");
		String topicStatus 	= request.getParameter("topicstatus");
		String submitType 	= request.getParameter("submittype");
		
		String grade 		= request.getParameter("grade");
		String profession 	= request.getParameter("profession");
		String tbclass 		= request.getParameter("tbclass");
		String submiter 	= request.getParameter("submiter");
		String tag 			= request.getParameter("tag");
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
		if(!Verify.isEmpty(profession) && Verify.isEmpty(grade)) {
			return mav;
		}
		if(!Verify.isEmpty(tbclass) && Verify.isEmpty(profession)) {
			return mav;
		}
		String where 		= "from Tbtopic where deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(year)) {
			where += " AND topYear = '" + year.trim() + "' ";
			mav.addObject("year", year);
		}
		if(!Verify.isEmpty(submitType)) {
			where += " AND topFlag = '" + submitType + "' ";
			mav.addObject("submittype", submitType);
		}
		if(!Verify.isEmpty(topicStatus)) {
			where += " AND topStatus = '" + topicStatus + "' ";
			mav.addObject("topicstatus", topicStatus);
		}
		if(!Verify.isEmpty(submiter)) {
			where += " AND topCommitId LIKE '%" + submiter + "%' ";
			mav.addObject("submiter", submiter);
		}
		if(!Verify.isEmpty(tag)) {
			where += " AND topKeywords LIKE '%" + tag + "%' ";
			mav.addObject("tag", tag);
		}
		if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession) && !Verify.isEmpty(tbclass)) {
			where += " AND topCommitId IN (" +
					"select s.stuId from Student s where s.claId = (" +
					"select c.claId from Tbclass c where c.claId ='" + tbclass + "' AND c.proId = (" +
					"select p.proId from Profession p where p.graId = " + Integer.parseInt(grade) + 
					" AND p.deptId = '" + department.getDeptId() + "' AND p.proId = '" + profession + "' ))) ";
			mav.addObject("grade", grade);
			mav.addObject("profession", profession);
			mav.addObject("tbclass", tbclass);
		}else if(!Verify.isEmpty(grade) && !Verify.isEmpty(profession)) {
			where += " AND topCommitId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId = (" +
					"select p.proId from Profession p where p.graId = " + Integer.parseInt(grade) + 
					" AND p.deptId = '" + department.getDeptId() + "' AND p.proId = '" + profession + "' ))) ";
			mav.addObject("grade", grade);
			mav.addObject("profession", profession);
		}else if(!Verify.isEmpty(grade)) {
			/**
			 * 查询整个年级学生课题
			 */
			where += " AND topCommitId IN (" +
					"select s.stuId from Student s where s.claId IN (" +
					"select c.claId from Tbclass c where c.proId IN (" +
					"select p.proId from Profession p where p.graId = " + Integer.parseInt(grade) + 
					" AND p.deptId = '" + department.getDeptId() + "' ))) ";
			mav.addObject("grade", grade);
		}
		where += "order by topYear desc ";
		try {
			AdmintopicController.assignStatusListMap(request);
			AdmintopicController.assignTypeListMap(request);
			topicList = tbtopicService.getAllRecordByPages(where, pagination);
			String begroupWhere = "select topYear from Tbtopic where deptId = '" + 
					department.getDeptId() + "' group by topYear ";
			List<Tbtopic> begroupList = tbtopicService.getAll(begroupWhere);
			mav.addObject("begroupList", begroupList);
			_assignTbgradeList(request);
			_assignProfession(request);
			if(topicList == null || topicList.size() < 1) {
				
				return mav;
			}
			if(this.topicList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				topicList = (List<Tbtopic>) tbtopicService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, topicList, modelMap);
			TopicController.assignSelSource(request);
			TopicController.assignSelType(request);
			AdmintopicController.assignStatusMap(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 系部管理员修改课题名称
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edittopicnameview.do")
	public ModelAndView editTopicNameView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/topicorderreview/edit-topic-name");
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try {
			if(_assignTopicIsJudge(request)) {
				request.setAttribute("statusCode", 300);
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			Tbtopic topic = tbtopicService.getByTopId(id);
			if(Verify.isEmpty(topic)) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			mav.addObject("topic", topic);
			AdmintopicController.assignTypeMap(request);
			TopicController.assignSelSource(request);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "服务器繁忙，请稍后再试");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		
		return mav;
	}
	
	/**
	 * 加载课题是否已经评阅
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @return 
	 */
	private boolean _assignTopicIsJudge(HttpServletRequest request) 
	{
		String id 	= request.getParameter("id");
		try {
			String where = "from Reviewresult where topId = '" + id.trim() + "' ";
			Reviewresult reviewresult = reviewresultService.getRecordByWhere(where);
			if(!Verify.isEmpty(reviewresult)) {
				request.setAttribute("message", "课题已经评阅，不可以修改");
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "服务器繁忙，请稍后再试");
			
			return true;
		}
		
		return false;		
	}

	/**
	 * 系部管理员修改课题名称
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editname.do")
	public ModelAndView editName(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		String name = request.getParameter("name");
		if(Verify.isEmpty(id)) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "id不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			if(_assignTopicIsJudge(request)) {
				request.setAttribute("statusCode", 300);
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			Tbtopic topic = tbtopicService.getByTopId(id);
			if(Verify.isEmpty(topic)) {
				mav.addObject("statusCode", 300);
				mav.addObject("message", "记录不存在");
				mav.setViewName("public/ajaxDone");
				
				return mav;
			}
			topic.setTopName(name);
			tbtopicService.editOneRecord(topic);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "topictotallist", "/admin/topicorderreview/topictotallist.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "topictotallist", "");
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

}
