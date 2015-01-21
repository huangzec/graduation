<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Room> list 	= (List<Room>) request.getAttribute("list");
	Message message 	= (Message) request.getAttribute("message");
 %>

<div class="pageHeader">
	<div class="text-center">
		<%=message.getName() %>
	</div>
	<div class="divider"></div>
	<div class="text-center">时间：<%=message.getCreateTime() %></div>
</div>
<div class="divider"></div>
<div class="pageContent">
	<%=StringUtil.decodeHtml(message.getContent()) %>
</div>
