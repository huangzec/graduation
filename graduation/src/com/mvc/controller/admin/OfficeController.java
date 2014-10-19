package com.mvc.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.RequestSetAttribute;
import com.mvc.entity.Tboffice;
import com.mvc.service.TbofficeService;

/**
 * 教务处管理员处理类
 * 
 * @author Happy_Jqc@163.com
 *
 */
@Controller
@RequestMapping(value="/admin/office")
public class OfficeController {
	
	@Autowired
	private TbofficeService officeService;
	
	/**
	 * 
	 * 进入个人信息页面 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 下午7:15:03
	 * @return ModelAndView
	 */
	@RequestMapping(value="/intoPerfile.do")
	public ModelAndView intoPerfile(HttpServletRequest request, ModelMap modelMap){
		String offId = (String) request.getSession().getAttribute("user_id");
		System.out.println(offId);
		Tboffice tbOffice = new Tboffice();
		tbOffice = officeService.getOneTbOffice("from Tboffice where offId='" + offId + "'");
		if(tbOffice == null ){
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "用户不存在！");
			return new ModelAndView("public/ajaxDone");
		}
		if(request.getSession().getAttribute("user_status") != null &&
				(Integer) request.getSession().getAttribute("user_status") == 4){
			tbOffice = officeService.getOneTbOffice("from Tboffice where offId='" + offId + "'");
			modelMap.put("tbOffice", tbOffice);
		}
		return new ModelAndView("admin/office/perfile");
	}
	
	/**
	 * 
	 * 个人信息更新提交 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-14 下午7:22:11
	 * @return String
	 */
	@RequestMapping(value="/perfile.do")
	public String perfile(HttpServletRequest request){
		String offEmail = request.getParameter("email");
		String offId = (String) request.getSession().getAttribute("user_id");
		
		if(offEmail == null || offEmail.equals("")){
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "邮箱不能为空！");
			return "public/ajaxDone";
		}
		Tboffice tboffice = officeService.getOneTbOffice("from Tboffice where offId='" + offId + "'");
		if(tboffice == null){
			request.setAttribute("status", 300);
			request.setAttribute("message", "记录已不存在！");
			return "public/ajaxDone";
		}
		tboffice.setOffSex(request.getParameter("sex"));
		tboffice.setOffTel(request.getParameter("phone"));
		tboffice.setOffEmail(offEmail);
		try{
			officeService.editOffice(tboffice);
			RequestSetAttribute.requestSetAttribute(
					request, 200, "", "修改成功", "perfile", "/admin/office/perfile.do");
		}catch(Exception e){
			RequestSetAttribute.requestSetAttribute(
					request, 300, "", "修改失败", "perfile", "/admin/office/perfile.do");
		}
		return "public/ajaxDone";
	}

}
