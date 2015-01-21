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

import com.mvc.common.HResponse;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Deptmanager;
import com.mvc.entity.Message;
import com.mvc.entity.Student;
import com.mvc.entity.Tboffice;
import com.mvc.entity.Teacher;
import com.mvc.exception.VerifyException;
import com.mvc.service.DeptmanagerService;
import com.mvc.service.MessageService;
import com.mvc.service.StudentService;
import com.mvc.service.TbgradeService;
import com.mvc.service.TbofficeService;
import com.mvc.service.TeacherService;

/**
 * 消息模块控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/admin/message")
public class AdminMessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private DeptmanagerService deptmanagerService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private TbofficeService tbofficeService;
	
	@Autowired
	private TbgradeService tbgradeService;
	
	private Pagination pagination;
	private List<Message> list = new ArrayList<Message>();
	private List<Teacher> teaList = new ArrayList<Teacher>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 20;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Message> getList() {
		return list;
	}

	public void setList(List<Message> list) {
		this.list = list;
	}

	public List<Teacher> getTeaList() {
		return teaList;
	}

	public void setTeaList(List<Teacher> teaList) {
		this.teaList = teaList;
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
	 * @date 2014-10-20 15:37:10
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String status 	= request.getSession().getAttribute("user_status").toString();
		if(status.equals("3")) {
			mav.setViewName("admin/message/add-dept");
		}else {
			mav.setViewName("admin/message/add");
		}
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-20 15:37:10
	 * @return ModelAndView
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String name 	= request.getParameter("name");
		String content 	= request.getParameter("content");
		String toIds 	= request.getParameter("org.id");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		String[] toId 	= toIds.split(","); 
		Short status = 1;
		try{
			for(int i = 0; i < toId.length; i ++) {
				Message message = new Message();
				message.setName(name);
				message.setContent(StringUtil.encodeHtml(content));
				message.setToId(toId[i]);
				message.setFromId(request.getSession().getAttribute("user_id").toString());
				message.setStatus(status);
				message.setCreateTime(HResponse.formatDateTime(new Date()));
				
				messageService.saveMessage(message);
			}
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "消息发送成功", "messagelist", "/admin/message/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "消息发送失败", "", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 数据验证
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-20 15:37:10
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String id 		= request.getParameter("org.id");
		String name 	= request.getParameter("name");
		if(Verify.isEmpty(id)) {
			request.setAttribute("message", "收件人不能为空");
			
			return false;
		}
		if(Verify.isEmpty(name)) {
			request.setAttribute("message", "标题不能为空");
			
			return false;
		}
		
		return true;
	}

	/**
	 * 列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-20 15:37:10
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap ) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String userId 	= (String) request.getSession().getAttribute("user_id");
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
		String where = "from Message where toId = '" + userId + "' order by createTime desc ";
		list = messageService.getAllRecordByPages(where, pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("admin/message/list");
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Message>) messageService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
		mav.setViewName("admin/message/list");
		
		return mav;
	}
	
	/**
	 * 删除操作
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-20 15:37:10
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
		Message message = messageService.getRecordById(Integer.parseInt(id));
		if(message == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			messageService.removeOneRecord(message);
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
	 * @date 2014-10-20 15:37:10
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		Message message = messageService.getRecordById(Integer.parseInt(id));
		if(message == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("message", message);
		mav.setViewName("admin/message/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-10-20 15:37:10
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
		Message message = messageService.getRecordById(Integer.parseInt(id));
		if(message == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			
			return mav;
		}
		try{
			messageService.editOneRecord(message);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "messagelist", "/admin/message/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "messagelist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
	/**
	 * 查看详细消息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="detail.do")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "编号不能为空");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Message message = messageService.getRecordById(Integer.parseInt(id));
		if(Verify.isEmpty(message)) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("message", message);
		mav.setViewName("admin/message/detail");
		_assignMessageStatus(request);
		
		return mav;
	}

	/**
	 * 修改消息状态
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request 
	 */
	private void _assignMessageStatus(HttpServletRequest request) 
	{
		String id 	= request.getParameter("id");
		if(Verify.isEmpty(id)) {
			return;
		}
		try {
			Message message = messageService.getRecordById(Integer.parseInt(id));
			if(Verify.isEmpty(message)) {
				return;
			}
			Short status = 2;
			message.setStatus(status);
			messageService.editOneRecord(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * lookup查找系部管理员
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/lookup.do")
	public ModelAndView lookup(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/message/lookup");
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
		String where = "from Deptmanager order by dmId asc ";
		List<Deptmanager> deptList 	= deptmanagerService.getAllRecordByPages(where, pagination);
		if(deptList == null || deptList.size() < 1) {
			return new ModelAndView("admin/message/lookup", "message", "没有相关数据，请先添加！");
		}
		if(deptList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			deptList = (List<Deptmanager>) deptmanagerService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, deptList, modelMap);
		
		return mav;
	}
	
	/**
	 * lookup查找学生
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/lookupstu.do")
	public ModelAndView lookupStudent(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/message/lookup-student");
		Department department 	= (Department) request.getSession().getAttribute("department");
		String graId 			= request.getParameter("graid");
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
		pagination.setSize(20);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Student where claId IN (" +
				"select c.claId from Tbclass c where c.proId IN (" +
				"select p.proId from Profession p where p.deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(graId)) {
			where += " AND p.graId = '" + graId + "' ";
		}
		where += " )) order by stuId asc ";
		try {
			_assignGradeList(request);
			List<Student> stuList 	= studentService.getAllRecordByPages(where, pagination);
			if(stuList == null || stuList.size() < 1) {
				return new ModelAndView("admin/message/lookup-student", "message", "没有相关数据，请先添加！");
			}
			if(stuList.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				stuList = (List<Student>) studentService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, stuList, modelMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("gradid", graId);
		
		return mav;
	}
	
	/**
	 * 加载年级列表
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @throws VerifyException 
	 */
	private void _assignGradeList(HttpServletRequest request) throws VerifyException
	{
		Department department 	= (Department) request.getSession().getAttribute("department");
		String where = "from Tbgrade where deptId = '" + department.getDeptId() + "' order by graNumber desc ";
		request.setAttribute("gradeList", tbgradeService.getAllRowsByWhere(where));
	}

	/**
	 * lookup查找教师
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/lookuptea.do")
	public ModelAndView lookupTeacher(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/message/lookup-teacher");
		Department department 	= (Department) request.getSession().getAttribute("department");
		String id	 			= request.getParameter("id");
		String name 			= request.getParameter("name");
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
		String where = "from Teacher where deptId = '" + department.getDeptId() + "' ";
		if(!Verify.isEmpty(id)) {
			where += " AND teaId LIKE '%" + id + "%' ";
		}
		if(!Verify.isEmpty(name)) {
			where += " AND teaName LIKE '%" + name + "%' ";
		}
		where += " order by teaId asc ";
		teaList 	= teacherService.getAllRecordByPages(where, pagination);
		if(teaList == null || teaList.size() < 1) {
			return new ModelAndView("admin/message/lookup-teacher", "message", "没有相关数据，请先添加！");
		}
		if(teaList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			teaList = (List<Teacher>) teacherService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, teaList, modelMap);
		mav.addObject("id", id);
		mav.addObject("name", name);
		
		return mav;
	}
	
	/**
	 * lookup查找教务处管理员
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/lookupman.do")
	public ModelAndView lookupManager(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/message/lookup-manager");
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
		String where = "from Tboffice order by offId asc ";
		List<Tboffice> officeList 	= tbofficeService.getAllRecordByPages(where, pagination);
		if(officeList == null || officeList.size() < 1) {
			return new ModelAndView("admin/message/lookup-manager", "message", "没有相关数据，请先添加！");
		}
		if(officeList.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			officeList = (List<Tboffice>) tbofficeService.getAllRecordByPages(where, pagination);
		}
		RequestSetAttribute.setPageAttribute("", pagination, officeList, modelMap);
		
		return mav;
	}
	
}
