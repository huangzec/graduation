<%@page import="com.mvc.common.Verify"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String id 			= (String) request.getAttribute("id");
	String name 		= (String) request.getAttribute("name");
	String permissions 	= (String) request.getAttribute("permissions");
%>
<script type="text/JavaScript">
	function loadimage(){ 
		document.getElementById("code").src = "<%=basePath %>public/image.jsp?"+Math.random(); 
}
</script>
<div class="page">
	<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/index/reset.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="id" value="<%=(Verify.isEmpty(id) ? "" : id) %>">
		<input type="hidden" name="permissions" value="<%=(Verify.isEmpty(permissions) ? "" : permissions) %>">
		<div class="pageFormContent" layoutH="58">
			<div class="unit text-center">
				重置 <%=(Verify.isEmpty(name) ? "" : name) %> 密码
			</div>
			<div class="unit">
				<label>新密码</label>
				<input type="text" class="required" name="password" value="<%=(Verify.isEmpty(id) ? "" : id) %>" />
				<lable>4~32个字符</lable>
			</div>
			<div class="unit" style="color:red;">
				<label>${message }</label>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认重置</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>