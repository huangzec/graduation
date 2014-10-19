package com.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.Pagination;
import com.mvc.entity.User;
import com.mvc.service.UserService;

@Controller
public class UserOldController {

	static Logger logger = Logger.getLogger(UserOldController.class);
	private static final String LOGIN = "/user/login";
	private static final String LIST = "/user/list.do";
	
	private String file;
	private File image_path;
	private String image_pathFileName;
	
	private String content;
	
	@Autowired
	private UserService userService;
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public File getImage_path() {
		return image_path;
	}

	public void setImage_path(File imagePath) {
		image_path = imagePath;
	}

	public String getImage_pathFileName() {
		return image_pathFileName;
	}

	public void setImage_pathFileName(String imagePathFileName) {
		image_pathFileName = imagePathFileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@RequestMapping(value="/user/ll")
	public ModelAndView login(HttpServletRequest request,ModelMap modelMap){

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		User user = userService.getRecordByNameAndPwd(userName, passWord);
		if(user == null)
		{
			logger.error("没有该用户信息");
			return new ModelAndView("index");
		}
		this.setUserInfoByLogin(request, user);
		logger.error("登陆成功");
		return new ModelAndView("main");
	}
	
	public void setUserInfoByLogin(HttpServletRequest request, User user)
	{
//		request.getSession().setAttribute("user_id", user.getId());
//		request.getSession().setAttribute("user_adminInd", user.getAdminInd());
	}
	private String keyword;
	private Pagination pagination;
	private List<User> list = new ArrayList<User>();
	
	private int pageNum=1;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	@RequestMapping(value=LIST)
	public String list(HttpServletRequest request,ModelMap modelMap){

		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println("pageNum =  " + pageNum);
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
			list = userService.getAllRecordByPages(pagination);
		} else{
			list = userService.getAllRecordByPages(keyword, pagination);
		}
		//System.out.println("list " + list.size());
		if(list == null || list.size() < 1) {
			return "userListView";
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<User>) userService.getAllRecordByPages(pagination);
		}
	    modelMap.put("targetType", "navTab");
	    modelMap.put("totalCount", pagination.getTotalRecord());
	    modelMap.put("totalPage", pagination.getTotalPage());
	    modelMap.put("numPerPage", pagination.getSize());
	    modelMap.put("pageNumShown", pagination.getTotalPage());
	    modelMap.put("currentPage", pagination.getCurrentPage());
	    modelMap.put("keyword", keyword);
		modelMap.put("userList", list);
		
		return "userListView";
	}
	
	@RequestMapping(value="/user/search.do")
	public String search(HttpServletRequest request, ModelMap modelMap)
	{
		if(!(request.getParameter("pageNum") == null))
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println("pageNum =  " + pageNum);
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
		keyword 	= request.getParameter("keyword");
		System.out.println("keyword = " + keyword);
		if(keyword == null)
		{
			list = (List<User>) userService.getAllRecordByPages(pagination);
		} else{
			list = (List<User>) userService.getAllRecordByPages(keyword, pagination);
		}
		if(list.size() < 1 || list == null) {
			return "userListView";
		}
		if(this.list.size() == 0 && pagination.getCurrentPage() != 1) {
			pagination.setCurrentPage(pagination.getCurrentPage() - 1);
			list = (List<User>) userService.getAllRecordByPages(pagination);
		}
	    modelMap.put("targetType", "navTab");
	    modelMap.put("totalCount", pagination.getTotalRecord());
	    modelMap.put("totalPage", pagination.getTotalPage());
	    modelMap.put("numPerPage", pagination.getSize());
	    modelMap.put("pageNumShown", pagination.getTotalPage());
	    modelMap.put("currentPage", pagination.getCurrentPage());
		modelMap.put("userList", list);
		
		return "userListView";
	}
	
	@RequestMapping(value="/user/editview.do")
	public ModelAndView editview(HttpServletRequest request, ModelMap modelMap)
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getRecordById(id);
		if(user == null)
		{
			return new ModelAndView("redirect:/user/list.do");
		}
		return new ModelAndView("edituser", "record", user);
	}
	
	@RequestMapping(value="/user/edit.do")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap)
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getRecordById(id);
//		user.setAdminInd(request.getParameter("adminInd"));
//		user.setPassWord(request.getParameter("passWord"));
		this.saveImage_path(request, user);
//		user.setContent(request.getParameter("content"));
		try{
			userService.editOneUser(user);
			request.setAttribute("statusCode", 200);
			request.setAttribute("callbackType", "closeCurrent");
			request.setAttribute("message", "编辑成功");
			request.setAttribute("navTabId", "userListview");
			request.setAttribute("forwardUrl", "/user/list.do");
		}catch (Exception e) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("callbackType", "");
			request.setAttribute("message", "编辑失败");
			request.setAttribute("navTabId", "");
			request.setAttribute("forwardUrl", "");
		}
		return new ModelAndView("public/ajaxDone");
		//return new ModelAndView("redirect:/user/list.do");
	}
	
	@RequestMapping(value="/user/addview.do")
	public String addview()
	{
		return "user/adduser";
	}
	
	@RequestMapping(value="/user/add.do")
	public ModelAndView add(HttpServletRequest request, ModelMap modelMap)
	{
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		User user = new User();
//		user.setAdminInd(userName);
//		user.setPassWord(passWord);
//		user.setLoginName("admin11");
//		user.setStaffId(23);
//		this.saveImage_path(request, user);
//		user.setContent(request.getParameter("content"));		
		try{
			userService.addOneUser(user);
			request.setAttribute("statusCode", 200);
			request.setAttribute("callbackType", "closeCurrent");
			request.setAttribute("message", "添加成功");
			request.setAttribute("navTabId", "userListview");
			request.setAttribute("forwardUrl", "/user/list.do");
		}catch (Exception e) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("callbackType", "");
			request.setAttribute("message", "添加失败");
			request.setAttribute("navTabId", "");
			request.setAttribute("forwardUrl", "");
		}
		return new ModelAndView("public/ajaxDone");
	}
	
	protected void saveImage_path(HttpServletRequest request, User user) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 /** 页面控件的文件流* */
        MultipartFile multipartFile = multipartRequest.getFile("image_path");
        System.out.println("上传的文件大小为" + multipartFile.getSize());
        if(multipartFile.getSize() <= 0)
        {
        	return;
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        /** 构建文件保存的目录* */
        String logoPathDir = "/uploadfiles/"
                + dateformat.format(new Date());
        String tempPathDir = logoPathDir;
        /** 得到文件保存目录的真实路径* */
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath(logoPathDir);
        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        /** 获取文件的后缀* */
        String suffix = multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf("."));
        /** 使用UUID生成文件名称* */
        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        /** 拼成完整的文件保存路径加文件* */
        String fileName = logoRealPathDir + File.separator + logImageName;
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
            //user.setImage_path(tempPathDir + "/" + logImageName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@RequestMapping(value="/user/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response)
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getRecordById(id);
		if(user == null)
		{
			request.setAttribute("statusCode", 300);
			request.setAttribute("callbackType", "");
			request.setAttribute("message", "用户不存在");
			request.setAttribute("navTabId", "");
			request.setAttribute("forwardUrl", "/user/list.do");
			return "public/ajaxDone";
		}
		try{
			userService.removeOneUser(user);
			request.setAttribute("statusCode", 200);
			request.setAttribute("callbackType", "");
			request.setAttribute("message", "用户删除成功");
			request.setAttribute("navTabId", "userListview");
			request.setAttribute("forwardUrl", "/user/list.do");
		}catch (Exception e) {
			request.setAttribute("statusCode", 300);
			request.setAttribute("callbackType", "");
			request.setAttribute("message", "用户删除失败");
			request.setAttribute("navTabId", "");
			request.setAttribute("forwardUrl", "/user/list.do");
		}
		return "public/ajaxDone";
	}
	
	@RequestMapping(value="/user/quick.do")
	public String quick(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println(request.getParameter("ids"));
		return "public/ajaxDone";
	}
	
	@RequestMapping(value="/user/site.do")
	public ModelAndView site(HttpServletRequest request, ModelMap modelMap)
	{

		Integer id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getRecordById(id);
		if(user == null)
		{
			return new ModelAndView("redirect:/user/list.do");
		}
		
		return new ModelAndView("site/news/news", "record", user);
	}
}
