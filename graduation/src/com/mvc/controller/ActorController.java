package com.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.Pagination;
import com.mvc.entity.User;
import com.mvc.service.ActorService;
//import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	/*private Pagination pagination;
	private List<Actor> list = new ArrayList<Actor>();
	private Integer pageNum = 1;
	private String keyword;
	
	public List<Actor> getList() {
		return list;
	}


	public void setList(List<Actor> list) {
		this.list = list;
	}


	public Integer getPageNum() {
		return pageNum;
	}


	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	@RequestMapping(value="/actor/lookup")
	public ModelAndView lookup(HttpServletRequest request, ModelMap modelMap)
	{
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		int size = 2;
		if(pagination == null){
			pagination = new Pagination(size);
		}
		pagination.setSize(size);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		keyword = request.getParameter("keyword");
		if(keyword == null || keyword.equals(""))
		{
			list = actorService.getAllRecordByPages("1 = 1", pagination);
		} else{
			list = actorService.getAllRecordByPages("1 = 1 and name = '" + keyword + "'", pagination);
		}
		if(list == null || list.size() < 1)
		{
		    modelMap.put("totalCount", 0);
			return new ModelAndView("user/lookup");
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = actorService.getAllRecordByPages("1 = 1", pagination);
		}
		System.out.println("actor list : " + list.size());
	    modelMap.put("targetType", "navTab");
	    modelMap.put("totalCount", pagination.getTotalRecord());
	    modelMap.put("totalPage", pagination.getTotalPage());
	    modelMap.put("numPerPage", pagination.getSize());
	    modelMap.put("pageNumShown", pagination.getTotalPage());
	    modelMap.put("currentPage", pagination.getCurrentPage());
		modelMap.put("actorList", list);
		modelMap.put("keyword", keyword);
		
		return new ModelAndView("user/lookup");
	}*/
}
