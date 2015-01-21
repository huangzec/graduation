<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>出错啦</title></head>
<body>
<% Exception e = (Exception)request.getAttribute("ex"); %>
<% Exception ex = request.getAttribute("ex"); %>
<H2>业务错误: <%= e.getClass().getSimpleName()%><%=e.getClass().getSimpleName() %></H2>
<hr />
<P>错误描述：</P>
<%= e.getMessage()%><%=e.getMessage() %>
<P>错误信息：</P>
<% e.printStackTrace(new java.io.PrintWriter(out)); %>
<% ex.printStackTrace(new PrintWriter(out)); %>
</body>
</html>