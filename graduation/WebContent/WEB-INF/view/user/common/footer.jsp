<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<div id="footer">
			Copyright &copy; 2014-2015 
			<a href="mailto:Happy_Jqc@163.com" title="Happy_Jqc@163.com">姜其灿  </a>
			<a href="mailto:huangzec@foxmail.com" title="huangzec@foxmail.com"> 黄泽西</a>    
			指导老师:姚敦红、张文
			All Rights Reserved.
		</div>
		

<script src="<%=basePath %>vendor/hhjslib/hhjslib.js"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.dialog.js"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.validate.js"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.utils.js"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.automask.js"></script>
<script src="<%=basePath %>js/common.js"></script>
