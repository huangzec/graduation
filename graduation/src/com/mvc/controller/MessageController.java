package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.ArrayUtil;
import com.mvc.common.HResponse;
import com.mvc.common.Pagination;
import com.mvc.common.SqlUtil;
import com.mvc.common.StringUtil;
import com.mvc.common.Verify;
import com.mvc.entity.Message;
import com.mvc.exception.VerifyException;
import com.mvc.service.MessageService;

/**
 * 消息控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	private Pagination pagination;
	private List<Message> list = new ArrayList<Message>();
	
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
	/**
	 * 构造函数
	 */
	public MessageController()
	{
		
	}

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
	 * 未读消息列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-2 下午09:37:15
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/user/message/unread.do")
	public ModelAndView unread(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
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
		String where = "from Message where toId = '" + request.getSession().getAttribute("user_id") + "'  AND status = 1  order by createTime desc ";
		list = messageService.getAllRecordByPages(where, pagination);
		modelMap.put("pagination", pagination);
		if(list == null || list.size() < 1) {
			mav.setViewName("user/message/list");
			modelMap.put("list", list);
			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<Message>) messageService.getAllRecordByPages(where, pagination);
		}
		mav.setViewName("user/message/list");
		modelMap.put("list", list);
		request.setAttribute(
				"total_message",
				messageService.getAllRecords("from Message where toId = '" + request.getSession().getAttribute("user_id") + "' AND status = 1")
		);
		mav.addObject("title", "未读消息");
		
		return mav;
	}
	
	/**
	 * 消息列表
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-2 下午09:37:15
	 * @return ModelAndView
	 */
	@RequestMapping(value="/user/message/list.do")
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap)
	{
		ModelAndView mav = new ModelAndView();
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
		String where = "from Message where toId = '" + request.getSession().getAttribute("user_id") + "'  order by createTime desc ";
		try {
			list = messageService.getAllRecordByPages(where, pagination);
			modelMap.put("pagination", pagination);
			if(list == null || list.size() < 1) {
				mav.setViewName("user/message/list");
				modelMap.put("list", list);
				
				return mav;
			}
			if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
				pagination.setCurrentPage(pagination.getCurrentPage() - 1);
				list = (List<Message>) messageService.getAllRecordByPages(where, pagination);
			}
			mav.setViewName("user/message/list");
			modelMap.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("title", "全部消息");
		
		return mav;
	}

	/**
	 * 注册发送人Map
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param list 
	 * @date 2014-8-3 下午02:14:52
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void _assignSenderList(HttpServletRequest request) 
	{
		request.setAttribute(
				"from_id_map", 
				ArrayUtil.turnListMapToMapMap(
						"id",
						messageService.getAllRecords(SqlUtil.whereIn("id", "from_id", (List<Map<String, String>>) request.getAttribute("list")))
				)
		);
	}
	
	/**
	 * 读取消息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-4 上午10:45:56
	 * @return ModelAndView
	 */
	@RequestMapping(value="/user/message/read.do")
	public ModelAndView read(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/message/detail");
		if(Verify.isEmpty(request.getParameter("id"))) {
			mav.addObject("message", "编号不能为空");
			
			return mav;
		}
		Message message = messageService.getRecordById(Integer.parseInt(request.getParameter("id")));
		if(Verify.isEmpty(message)) {
			mav.addObject("message", "记录不存在");
			
			return mav;
		}
		try{
			Short shor = 2;
			message.setStatus(shor);
			messageService.editOneRecord(message);
		}catch (Exception e) {
			e.toString();
		}
		mav.addObject("record", message);		
		
		return mav;
	}
	
	/**
	 * 添加消息
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-30 下午03:59:15
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/user/message/add.do")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		String content = "指导老师 " + request.getSession().getAttribute("user_name") + 
				" 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收";
		Message message = new Message();
		message.setName("怀化学院本科毕业论文(设计)任务书");
		message.setContent(content);
		message.setToId("22");
		message.setFromId((String) request.getSession().getAttribute("user_id"));
		Short status = 1;
		message.setStatus(status);
		message.setCreateTime(HResponse.formatDateTime(new Date()));
		messageService.saveMessage(message);
		
		return new ModelAndView("forward:/user/message/unread.do");
	}
	
	/**
	 * 添加消息视图
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/message/addview.do")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("/user/message/add");
		
		return mav;
	}
	
	/**
	 * 发送消息
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 * @return
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/user/message/send.do")
	public ModelAndView send(HttpServletRequest request, HttpServletResponse response) throws VerifyException
	{
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName("");
		String userid 	= (String) request.getSession().getAttribute("user_id");
		String title 	= request.getParameter("title");
		String content 	= request.getParameter("content");
		String toid		= request.getParameter("toid");
		if(Verify.isEmpty(toid)) {
			throw new VerifyException("接收人不能为空");
		}
		if(Verify.isEmpty(content)) {
			throw new VerifyException("内容不能为空");
		}
		Message message = new Message();
		message.setName(title);
		message.setContent(StringUtil.encodeHtml(content));
		message.setToId(toid);
		message.setFromId(userid);
		short status = 1;
		message.setStatus(status);
		message.setCreateTime(HResponse.formatDateTime(new Date()));
		messageService.saveMessage(message);
		
		return new ModelAndView("redirect:/user/message/addview.do");
	}
}
