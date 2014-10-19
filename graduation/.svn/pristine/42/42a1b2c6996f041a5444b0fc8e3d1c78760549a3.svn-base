package com.mvc.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.mvc.entity.Deptmanager;

public class RequestSetAttribute {

	/**
	 * 
	 * request设置attribute 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-12 下午12:15:27
	 * @return String
	 */
	public static void requestSetAttribute(HttpServletRequest request, int statusCode, 
			String callbackType, String message, String navTabId,
			String forwardUrl) {
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("callbackType", callbackType);
		request.setAttribute("message", message);
		request.setAttribute("navTabId", navTabId);
		request.setAttribute("forwardUrl", forwardUrl);
	}
	
	/**
	 * 设置分页属性
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-7-13 下午03:41:22
	 * @return void
	 */
	public static void setPageAttribute(String keyword, Pagination pagination, List<?> list, ModelMap modelMap) {
		modelMap.put("targetType", "navTab");
	    modelMap.put("totalCount", pagination.getTotalRecord());
	    modelMap.put("totalPage", pagination.getTotalPage());
	    modelMap.put("numPerPage", pagination.getSize());
	    modelMap.put("pageNumShown", pagination.getTotalPage());
	    modelMap.put("currentPage", pagination.getCurrentPage());
	    modelMap.put("keyword", keyword);
		modelMap.put("list", list);
	}

	/**
	 * 
	 * 模糊查询两个参数时设置分页属性 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-16 上午11:27:54
	 * @return void
	 */
	public static void setPageAttributeO(String keyword, String keywords,
			Pagination pagination, List<?> list, ModelMap modelMap) {
		modelMap.put("targetType", "navTab");
	    modelMap.put("totalCount", pagination.getTotalRecord());
	    modelMap.put("totalPage", pagination.getTotalPage());
	    modelMap.put("numPerPage", pagination.getSize());
	    modelMap.put("pageNumShown", pagination.getTotalPage());
	    modelMap.put("currentPage", pagination.getCurrentPage());
	    modelMap.put("keyword", keyword);
	    modelMap.put("keywords", keywords);
		modelMap.put("list", list);
	}
}
