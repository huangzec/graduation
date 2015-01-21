<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
	Opentopicinfo headerman 	= (Opentopicinfo) request.getSession().getAttribute("headerman");
	Graduateinfo gradHeaderman = (Graduateinfo) request.getSession().getAttribute("grad-headerman");
	String topicjudgeteacher 	= (String) request.getSession().getAttribute("topicjudgeteacher");
	String userId 				= (String) request.getSession().getAttribute("user_id");
 %>
   		<div class="span3">
			
			<div class="account-container">
			
				<div class="account-avatar">
					<a href="<%=basePath %>user/site/teaperfile.do">
						<img src="<%=basePath + ((Verify.isEmpty(teacher) || Verify.isEmpty(teacher.getImagePath())) ? "images/noimg.png" : teacher.getImagePath()) %>" alt="<%=(Verify.isEmpty(teacher) ? "" : teacher.getTeaName()) %>" class="thumbnail" />
					</a>
					
				</div> <!-- /account-avatar -->
			
				<div class="account-details">
				
					<span class="account-name"><%=request.getSession().getAttribute("user_name") %></span>
					
					<span class="account-role">教　师</span>
					
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
					<a href="<%=basePath%>user/topic/tea_lookTopic.do">
						<i class="icon-th-list"></i>
						查看课题		
					</a>
				</li>
				
				<li>
					<a href="<%=basePath %>user/topic/selectcase.do">
						<i class="icon-th-large"></i>
						选择情况
					</a>
				</li>
				<%
					if(!Verify.isEmpty(topicjudgeteacher) && !Verify.isEmpty(userId) && topicjudgeteacher.equals(userId.trim())) {
				%>
					<li>
						<a href="<%=basePath %>judge/judgeview.do">
							<i class="icon-eye-open"></i>
							评审课题	
						</a>
					</li>
				<% } %>
				<%
					if(null != headerman) {
				%>
					<li>
						<a href="<%=basePath %>user/opentopicinfo/enteroptscore.do">
							<i class="icon-pencil"></i>
							录入开题答辩成绩	
						</a>
					</li>
				<% } %>
				<%
					if(null != gradHeaderman) {
				%>
					<li>
						<a href="<%=basePath %>user/graduateinfo/enterscorelist.do">
							<i class="icon-pencil"></i>
							录入毕业答辩成绩	
						</a>
					</li>
				<% } %>
				<li>
					<a href="<%=basePath %>user/revieworder/searchview.do">
						<i class="icon-zoom-in"></i>
						毕业论文评阅	
					</a>
				</li>
				<li>
					<a href="<%=basePath %>user/apply/confirm.do">
						<i class="icon-ok-circle"></i>
						答辩确认	
					</a>
				</li>
				<li>
					<a href="<%=basePath %>user/rejoin/checkview.do">
						<i class="icon-indent-right"></i>
						资料审查	
					</a>
				</li>
				
			</ul>	
			<hr />
			<div class="sidebar-extra">
				<p>怀仁化物，立地仰天，厚德博学，唯实求新</p>
			</div> <!-- .sidebar-extra -->
			<br />
		</div>
		
		