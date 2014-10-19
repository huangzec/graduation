package com.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.common.HResponse;
import com.mvc.common.Path;
import com.mvc.common.WordUtil;
import com.mvc.entity.Department;
import com.mvc.entity.Topicreport;
import com.mvc.service.TopicreportService;

/**
 * word文档控制器
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2014-10-14 下午7:11:18 
 * @version 	1.0
 */
@Controller
@RequestMapping(value="/user/word")
public class WordController {

	@Autowired
	private TopicreportService topicreportService;
	
	/**
	 * 导出word文档
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportword.do")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
	{
		Department department = (Department) request.getSession().getAttribute("dept");
		WordUtil wordUtil = new WordUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//String userId = request.getParameter("id");
		String userId = "1106401023";
		String where = "from Topicreport where stuId = '" + userId + 
				"' AND departmentId = '" + department.getDeptId() + "' ";
		Topicreport topicreport = topicreportService.getRecordByWhere(where);
		try {
			dataMap.put("stuid", topicreport.getStuId());
			dataMap.put("author", request.getSession().getAttribute("user_name"));
			dataMap.put("stuname", HResponse.formatValue("stuId", topicreport.getStuId(), request));
			dataMap.put("deptid", topicreport.getDepartmentId());
			dataMap.put("meaning", topicreport.getMeaning());
			dataMap.put("content", topicreport.getContent());
			dataMap.put("research", topicreport.getResearch());
			dataMap.put("deadline", topicreport.getDeadline());
			dataMap.put("referencesl", topicreport.getReferencesl());
			dataMap.put("teacherview", topicreport.getTeacherView());
			dataMap.put("judgeview", topicreport.getJudgeView());
			dataMap.put("topicname", "课题名称");
			wordUtil.createDoc(Path.getWebRootPath(request) + "\\", dataMap);
			System.out.println("成功");
		} catch (Exception e) {
			System.out.println("error");
		}
		
	}
}
