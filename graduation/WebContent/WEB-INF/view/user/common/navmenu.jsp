<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Student student 	= (Student) request.getSession().getAttribute("student");
 %>
   		<div class="span3">
			
			<div class="account-container">
			
				<div class="account-avatar">
					<a href="<%=basePath %>user/site/stuperfile.do">
						<img src="<%=basePath + ((Verify.isEmpty(student) || Verify.isEmpty(student.getImagePath())) ? "images/noimg.png" : student.getImagePath()) %>" alt="<%=(Verify.isEmpty(student) ? "" : student.getStuName()) %>" class="thumbnail" />
					</a>
				</div> <!-- /account-avatar -->
			
				<div class="account-details">
				
					<span class="account-name"><%=request.getSession().getAttribute("user_name") %></span>
					
					<span class="account-role">学　生</span>
					
					<span class="account-actions">
						<a href="<%=basePath %>user/site/repassword.do">修改密码</a>
					</span>
				
				</div> <!-- /account-details -->
			
			</div> <!-- /account-container -->
			
			<hr />
			
			<ul id="main-nav" class="nav nav-tabs nav-stacked">
				
				<li class="">
					<a href="<%=basePath %>user/index.do">
						<i class="icon-home"></i>
						我的主页 		
					</a>
				</li>
				
				<li>
					<a href="<%=basePath%>user/topic/intoSubmitTopic.do">
						<i class="icon-share"></i>
						提交课题	
					</a>
				</li>
				
				<li>
					<a href="<%=basePath%>user/topic/stu_lookTopic.do">
						<i class="icon-eye-open"></i>
						查看课题		
					</a>
				</li>
				
				<li>
					<a href="<%=basePath%>user/topic/studenttopic.do">
						<i class="icon-check"></i> 选择课题
					</a>
				</li>
				
				<li>
					<a href="<%=basePath%>user/topic/transferview.do">
						<i class="icon-share-alt"></i>
						课题转让	
					</a>
				</li>
				
				<li>
					<a href="<%=basePath %>user/apply/list.do">
						<i class="icon-edit"></i>
						答辩申请	
					</a>
				</li>
				
				<li>
					<a href="<%=basePath %>user/rejoin/infoview.do">
						<i class="icon-list-alt"></i>
						答辩信息	
					</a>
				</li>
			</ul>	
			<hr />
			
			<div class="sidebar-extra">
				<p>怀仁化物，立地仰天，厚德博学，唯实求新</p>
			</div> <!-- .sidebar-extra -->
			<br />
		</div>
		
		