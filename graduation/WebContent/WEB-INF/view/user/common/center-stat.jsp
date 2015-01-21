<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage 	= (List<Message>) request.getAttribute("total_message");
	List<Message> list 			= (List<Message>) request.getAttribute("list");
	String status 				= (String) (request.getSession().getAttribute("user_status") + "");
 %>
 <div class="stat-container">		
	<div class="stat-holder">						
		<div class="stat">							
			<span><i class="icon-user"></i></span>
			<%
				if(!Verify.isEmpty(status) && status.trim().toString().equals("2")) {
			%>
				<a href="<%=basePath %>user/site/teaperfile.do">我的个人信息</a>
			<%
				} else if(!Verify.isEmpty(status) && status.equals("1")) {
			%>
				<a href="<%=basePath %>user/site/stuperfile.do">我的个人信息</a>
			<% } %>							
		</div> <!-- /stat -->						
	</div> <!-- /stat-holder -->
	
	<div class="stat-holder">						
		<div class="stat">							
			<span><%=(null == totalMessage ? 0 : totalMessage.size()) %></span>							
			<a href="<%=basePath %>user/message/unread.do">未读消息</a>							
		</div> <!-- /stat -->						
	</div> <!-- /stat-holder -->
	
	<div class="stat-holder">						
		<div class="stat">							
			<span><%=(null == list ? 0 : list.size()) %></span>							
			<a href="<%=basePath %>user/message/list.do">我的消息</a>							
		</div> <!-- /stat -->						
	</div> <!-- /stat-holder -->
	
</div> 