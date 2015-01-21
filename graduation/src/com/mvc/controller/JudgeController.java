package com.mvc.controller;

import java.io.IOException;
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
import com.mvc.common.Verify;
import com.mvc.entity.Department;
import com.mvc.entity.Profession;
import com.mvc.entity.Reviewresult;
import com.mvc.entity.Settime;
import com.mvc.entity.Tbtopic;
import com.mvc.exception.VerifyException;
import com.mvc.service.JudgeService;
import com.mvc.service.SettimeService;
import com.mvc.service.TbtopicService;

/**
 * 评审课题控制器类
 * @author huangzec@foxmail.com
 *
 */
@Controller
@RequestMapping(value="/judge")
public class JudgeController {
	
	@Autowired
	private TbtopicService tbtopicService;
	
	@Autowired
	private SettimeService settimeService;
	
	@Autowired
	private JudgeService judgeService;
	
	private Pagination pagination;	
	private List<Tbtopic> list = new ArrayList<Tbtopic>();
	private int pageNum = 1;//页数
	private int numPerPage = 10;//每页显示多少条
	
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

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Tbtopic> getList() {
		return list;
	}

	public void setList(List<Tbtopic> list) {
		this.list = list;
	}

	/**
	 * 评审课题视图
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-4 下午08:58:22
	 * @return ModelAndView
	 * @throws VerifyException 
	 */
	@RequestMapping(value="/judgeview.do")
	public ModelAndView judgeView(HttpServletRequest request, ModelMap modelMap) throws VerifyException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/teacher/judge-list");
		Department department = (Department) request.getSession().getAttribute("dept");
		_assignJudgeSetTime(request);
		if(pagination == null){
			pagination = new Pagination(numPerPage);
		}
		if(!Verify.isEmpty(request.getParameter("pageNum"))) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		pagination.setSize(numPerPage);
		pagination.setCurrentPage(pageNum);
		if(pagination.getCurrentPage() <= 0) {
			pagination.setCurrentPage(1);
		}
		if(pagination.getTotalPage() != 0 && pagination.getCurrentPage() > pagination.getTotalPage()) {
			pagination.setCurrentPage(pagination.getTotalPage());
		}
		String where = "from Tbtopic where deptId = '" + department.getDeptId() + 
			"' AND topStatus = '0' AND parentId = '0' order by topId asc";
		list 	= tbtopicService.getAllRecordByPages(where, pagination);
		String subWhere 	= "from Tbtopic where deptId = '" + department.getDeptId() + 
			"' AND topStatus = '0' AND parentId != '0' order by topId asc";
		List<Tbtopic> subList 	= tbtopicService.getAll(subWhere);
		if(list == null || list.size() < 1) {			
			return mav;
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = tbtopicService.getAllRecordByPages(where, pagination);
		}
		mav.addObject("list", list);
		mav.addObject("subList", subList);
		mav.addObject("pagination", pagination);
		
		return mav;
	}

	/**
	 * 加载评审时间
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-5 下午05:54:32
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignJudgeSetTime(HttpServletRequest request) throws VerifyException {
		Department department = (Department) request.getSession().getAttribute("dept");
		String where = "from Settime where deptId = '" + department.getDeptId() + 
			"' AND mark = '2' AND ('" + HResponse.formatDateTime(new Date()) + "' between startTime and endTime )";
		Settime judgeTime 	= settimeService.getOneByWhere(where);
		request.setAttribute("judge-time", judgeTime);
	}
	
	/**
	 * 课题评审通过
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-7 下午09:49:25
	 * @return ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping(value="/pass.do")
	public ModelAndView pass(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String topicId 	= request.getParameter("id");
		if(Verify.isEmpty(topicId)) {
			request.setAttribute("message", "编号不能为空");
			return new ModelAndView("forward:/judge/judgeview.do");
		}
		String judgeId 	= (String) request.getSession().getAttribute("user_id");
		try{
			_assignJudgeResult(request, topicId, judgeId, "1");
		}catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
		}
		
		return new ModelAndView("forward:/judge/judgeview.do");
	}
	
	/**
	 * 评审不通过
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @date 2014-8-8 下午03:03:48
	 * @return ModelAndView
	 */
	@RequestMapping(value="/unpass.do")
	public ModelAndView unpass(HttpServletRequest request, HttpServletResponse response)
	{
		String topicId 	= request.getParameter("id");
		if(Verify.isEmpty(topicId)) {
			request.setAttribute("message", "编号不能为空");
			return new ModelAndView("forward:/judge/judgeview.do");
		}
		String judgeId 	= (String) request.getSession().getAttribute("user_id");
		try{
			_assignJudgeResult(request, topicId, judgeId, "2");
		}catch (Exception e) {
			request.setAttribute("message", "服务器繁忙，请稍后再试");
		}
		
		return new ModelAndView("forward:/judge/judgeview.do");
	}

	/**
	 * 评审是否通过
	 *  
	 * @Description  
	 * @author huangzec@foxmail.com
	 * @param request 
	 * @date 2014-8-8 上午11:11:16
	 * @return void
	 * @throws VerifyException 
	 */
	private void _assignJudgeResult(HttpServletRequest request, String topicId, String judgeId, String status) throws VerifyException {
		Reviewresult reviewResult = null;
		String where = "from Reviewresult where topId = '" + topicId + "' AND judgeId = '" + judgeId + "'";
		reviewResult = judgeService.getRecordByWhere(where);
		if(!Verify.isEmpty(reviewResult)) {
			request.setAttribute("message", "你已经评审过" + topicId + "该课题了");
			return;
		}
		String hql = "from Reviewresult where topId = '" + topicId + "' ";
		List<Reviewresult> tempList = judgeService.getSomeRows(hql);
		reviewResult = new Reviewresult();
		reviewResult.setTopId(topicId);
		reviewResult.setJudgeId(judgeId);
		reviewResult.setStatus(status);
		if(Verify.isEmpty(tempList) || tempList.size() < 6) {
			try{
				judgeService.addOneReviewResult(reviewResult);
				request.setAttribute("message", "评审成功");
			}catch (Exception e) {
				e.toString();
			}
		}else if(!Verify.isEmpty(tempList) && tempList.size() == 6) {
			String csql = hql + "AND status = '1'";
			List<Reviewresult> temp = judgeService.getSomeRows(csql);
			if(!Verify.isEmpty(temp) && ((temp.size() == 3 && status.equals("1")) || temp.size() > 3)) {
				//最终评审通过
				Tbtopic tbtopic = tbtopicService.getByTopId(topicId);
				if(Verify.isEmpty(tbtopic)) {
					request.setAttribute("message", "服务器繁忙，请您稍后再试");
					return;
				}
				tbtopic.setTopStatus("1");
				tbtopicService.editOneRecord(tbtopic);
				List<Tbtopic> tbtcList 	= tbtopicService.getAll("from Tbtopic where parentId = '" + tbtopic.getTopId() + "'");
				if(null != tbtcList && 0 < tbtcList.size()){
					for (Tbtopic tbc : tbtcList) {
						tbc.setTopStatus("1");
						tbtopicService.editOneRecord(tbc);
					}
				}
			}else {
				//最终评审不通过
				Tbtopic tbtopic = tbtopicService.getByTopId(topicId);
				if(Verify.isEmpty(tbtopic)) {
					request.setAttribute("message", "服务器繁忙，请您稍后再试");
					return;
				}
				tbtopic.setTopStatus("2");
				tbtopicService.editOneRecord(tbtopic);
				List<Tbtopic> tbtcList 	= tbtopicService.getAll("from Tbtopic where parentId = '" + tbtopic.getTopId() + "'");
				if(null != tbtcList && 0 < tbtcList.size()){
					for (Tbtopic tbc : tbtcList) {
						tbc.setTopStatus("2");
						tbtopicService.editOneRecord(tbc);
					}
				}
			}
			try{
				judgeService.addOneReviewResult(reviewResult);
				request.setAttribute("message", "评审成功");
			}catch (Exception e) {
				e.toString();
			}
		}else if(!Verify.isEmpty(tempList) && tempList.size() > 6) {
			request.setAttribute("message", "该课题已经达到评审人数限制");
			return;
		}
	}
	
	/**
	 * ajax获取评审过的记录
	 *  
	 * @author huangzec <huangzec@foxmail.com>
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/agetlist.do")
	public void ajaxGetJudgedList(HttpServletRequest request, HttpServletResponse response)
	{
		String userid 	= (String) request.getSession().getAttribute("user_id");
		String[] ids 	= request.getParameterValues("ids[]");
		try {
			if(ids.length < 0) {
				HResponse.errorJSON(response);
				return;
			}
			String id = "";
			for(int i = 0; i < ids.length; i ++) {
				id += ids[i] + ",";
			}
			id = id.substring(0, id.lastIndexOf(","));
			String where = "from Reviewresult where judgeId = '" + userid + "' AND topId IN (" + id + ") ";
			List<Reviewresult> relist = judgeService.getSomeRows(where);
			String turnid = "";
			if(!Verify.isEmpty(relist)) {
				for(int j = 0; j < relist.size(); j ++) {
					Reviewresult reviewresult = relist.get(j);
					turnid += "{\"id\": " + "\"" + reviewresult.getTopId() + "\"},";
				}
				turnid = turnid.substring(0, turnid.lastIndexOf(","));
				turnid = "[" + turnid + "]";
			}
			
			HResponse.okJSON("成功", turnid, response);
		} catch (Exception e) {
			e.printStackTrace();

			HResponse.errorJSON(response);
		}
	}

}
