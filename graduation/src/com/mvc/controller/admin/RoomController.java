package com.mvc.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.HResponse;
import com.mvc.common.MapUtil;
import com.mvc.common.Pagination;
import com.mvc.common.RequestSetAttribute;
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Room;
import com.mvc.exception.VerifyException;
import com.mvc.service.RoomService;

/**
 * 教室控制器类
 * 
 * @author huangzec@foxmail.com
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/admin/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	private Pagination pagination;
	private List<Room> list = new ArrayList<Room>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Room> getList() {
		return list;
	}

	public void setList(List<Room> list) {
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
	 * 注册是否可用Map
	 */
	private static Map<String, String> _usableMap = new LinkedHashMap<String, String>(){{
		put("1", "可用");
		put("2", "不可用");
	}};
	
	/**
	 * 加载是否可用Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-9-16 下午06:07:56
	 * @return void
	 */
	public static void _assignUsableMap(HttpServletRequest request)
	{
		request.setAttribute(
				"usable_map", 
				MapUtil.makeLinkedMapMap(_usableMap)
			);
	}

	/**
	 * 添加视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/room/add");
		
		return mav;
	}
	
	/**
	 * 添加实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String number	= request.getParameter("number");
		String name 	= request.getParameter("name");
		String usable 	= request.getParameter("usable");
		if(!this._verifyData(request)) {
			mav.addObject("statusCode", 300);
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		Room room = roomService.getOneRecordByWhere("from Room where number = '" + number + "'");
		if(room != null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "教室编号已存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		room = new Room();
		room.setNumber(number);
		room.setName(name);
		room.setParentId(department.getDeptId());
		room.setUsable(usable);
		room.setCreateTime(HResponse.formatDateTime(new Date()));
		try{
			roomService.addOne(room);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "添加成功", "roomlist", "/admin/room/list.do");
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
	 * @date 2014-09-16 15:56:58
	 * @return boolean
	 */
	protected boolean _verifyData(HttpServletRequest request) {
		String number	= request.getParameter("number");
		String name 	= request.getParameter("name");
		if(Verify.isEmpty(number)) {
			request.setAttribute("message", "教室编号不能为空");
			
			return false;
		}
		if(!Verify.isStrLen(number, 1, 64)) {
			request.setAttribute("message", "教室编号不超过64个字符");
			
			return false;
		}
		if(!Verify.isEmpty(name)) {
			if(!Verify.isStrLen(number, 1, 64)) {
				request.setAttribute("message", "教室名称不超过64个字符");
				
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return ModelAndView
	 */
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap )
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/room/list");
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
		String where = "from Room where parentId = '" + department.getDeptId() + "'";
		try {
			list = roomService.getAllRecordByPages(where, pagination);
			if(list == null || list.size() < 1) {
				
				return mav;
			}
			if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				list = (List<Room>) roomService.getAllRecordByPages(where, pagination);
			}
			RequestSetAttribute.setPageAttribute("", pagination, list, modelMap);
			_assignUsableMap(request);			
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
	 * @date 2014-09-16 15:56:58
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
		Room room = roomService.getOneRecordById(Integer.parseInt(id));
		if(room == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			roomService.removeOneRoom(room);
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
	 * @date 2014-09-16 15:56:58
	 * @return ModelAndView
	 */
	@RequestMapping(value="editview.do")
	public ModelAndView editView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		Department department = (Department) request.getSession().getAttribute("department");
		String id 	= request.getParameter("id");
		Room room = roomService.getOneRecordById(Integer.parseInt(id));
		if(room == null) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		mav.addObject("room", room);
		mav.setViewName("admin/room/edit");
		
		return mav;
	}
	
	/**
	 * 编辑实现
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-09-16 15:56:58
	 * @return ModelAndView
	 */
	@RequestMapping(value="/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String id 		= request.getParameter("id");
		String number 	= request.getParameter("number");
		String name 	= request.getParameter("name");
		String usable 	= request.getParameter("usable");
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
		Room room 	= roomService.getOneRecordById(Integer.parseInt(id));
		if(room == null) {
			mav.addObject("statusCode", 300);
			mav.addObject("message", "记录不存在");
			mav.setViewName("public/ajaxDone");
			
			return mav;
		}
		try{
			room.setNumber(number);
			room.setName(name);
			room.setUsable(usable);
			roomService.editOneRoom(room);
			RequestSetAttribute.requestSetAttribute(request, 200, "closeCurrent", "修改成功", "roomlist", "/admin/room/list.do");
		}catch (Exception e) {
			RequestSetAttribute.requestSetAttribute(request, 300, "", "修改失败", "roomlist", "");
		}
		mav.setViewName("public/ajaxDone");
		
		return mav;
	}
	
}
