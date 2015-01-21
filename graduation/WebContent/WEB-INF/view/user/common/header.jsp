<%@page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>怀化学院毕业论文(设计)综合管理系统</title>
    
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />    
    
    <link href="<%=basePath %>cscon/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=basePath %>cscon/bootstrap-responsive.min.css" rel="stylesheet" />
    
    <link href="<%=basePath %>cscon/font-awesome.css" rel="stylesheet" />
    
    <link href="<%=basePath %>cscon/adminia.css" rel="stylesheet" /> 
    <link href="<%=basePath %>cscon/adminia-responsive.css" rel="stylesheet" /> 
    
    <link href="<%=basePath %>cscon/pages/dashboard.css" rel="stylesheet" /> 
    <link href="<%=basePath %>cscon/style.css" rel="stylesheet" /> 
    <link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>favicon.ico" />
	<script type="text/javascript">
		var siteUrl 	= '<%=basePath %>';
		var siteUri 	= '<%=basePath %>';
	</script>
  			
	<script src="<%=basePath %>cscon/js/jquery-1.7.2.min.js"></script>
	<script src="<%=basePath %>cscon/js/bootstrap.js"></script>