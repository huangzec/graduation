<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/JavaScript">
	function loadimage(){ 
		document.getElementById("code").src = "<%=basePath %>public/image.jsp?"+Math.random(); 
}
</script>
<div class="page">
	<div class="pageContent">
	<form method="post" action="<%=basePath %>user/repwd.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="id" value="<%=request.getSession().getAttribute("user_id") %>">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>原密码</label>
				<input type="password" class="required" name="oldpassword">
			</div>
			<div class="unit">
				<label>新密码</label>
				<input type="password" class="required" name="password"><lable>6~32个字符</lable>
			</div>
			<div class="unit">
				<label>确认密码</label>
				<input type="password" class="required" name="repassword">
			</div>
			<div class="unit">
				<label>验证码</label>
				<input type="text" class="required" name="code"> 
				<a href="javascript:loadimage();">
					<img src="<%=basePath %>public/image.jsp" BORDER="0" alt="vcode" id="code" style="cursor:pointer" align="absmiddle">
				</a>
			</div>
			<div class="unit" style="color:red;">
				<label>${message }</label>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认修改</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>