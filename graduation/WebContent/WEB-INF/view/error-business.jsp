<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Map<String, Object> map = (Map<String, Object>) request.getAttribute("exp");
 %>
<html lang=en>
<head><title>出错啦</title></head>
<body>
<% Exception e = (Exception) map.get("ex"); %>
<H2>业务错误: <%= e.getClass().getSimpleName()%><%=e.getClass().getSimpleName() %></H2>
<hr />
<P>错误描述：</P>
<%= e.getMessage()%><%=e.getMessage() %>
<P>错误信息：</P>
<% e.printStackTrace(new java.io.PrintWriter(out)); %>
<% //ex.printStackTrace(new PrintWriter(out)); %>
</body>
</html>