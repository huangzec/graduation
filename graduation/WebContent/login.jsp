<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>怀化学院毕业论文(设计)综合管理系统</title>
	<link href="cscon/login.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="cscon/demo.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="csconjs/jquery1.42.min.js"></script>

</head>
<body>
	<div class="header">
		<h1 class="headerLogo">
			<img alt="logo" src="images/_logo.png" />
		</h1>
		<div class="headfont">怀化学院毕业论文(设计)综合管理系统</div>
	</div>

	<div class="banner">
		<div class="login-aside">
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout:fixed;">
				<div class="error-box"></div>
				<form class="registerform" method="post" action="<%=basePath%>user/enter/userlogin.do">
					<div class="fm-item">
						<span class="STYLE1">${message }</span>
						<label for="logonId" class="form-label">系统登陆：</label> <input
							type="text" maxlength="100" id="username" name="username"
							class="i-text">
						<div class="ui-form-explain"></div>
					</div>
					<div class="fm-item">
						<label for="logonId" class="form-label">登陆密码：</label>
						<input type="password" value="" maxlength="100" id="password" name="password" class="i-text">
						<div class="ui-form-explain"></div>
					</div>					
					<div class="fm-item">
						<label for="logonId" class="form-label">
						<input type="radio" name="status" value="1" checked />学　生
						<input type="radio" name="status" value="2" />教　师</label>
					</div>
					<div class="fm-item">
						<label for="logonId" class="form-label" >验证码：
						<input type="text" class="required" name="code" size="10"> 
						<a href="javascript:loadimage();">
							<img src="<%=basePath %>public/image.jsp" BORDER="0" alt="vcode" title="看不清？换一张" id="code" style="cursor:pointer;width: 50px;" align="absmiddle">
						</a>
						</label>
					</div>
					<div class="fm-item">
						<label for="logonId" class="form-label"></label> 
						<input type="submit" value="" tabindex="4" id="send-btn" class="btn-login">
					</div>
				</form>
			</div>
		</div>
		<div class="bd">
			<ul>
				<li style="background:url(themes/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"></li>
				<li style="background:url(themes/theme-pic2.jpg) #BCE0FF center 0 no-repeat;"></li>
			</ul>
		</div>
	</div>
	<br />
	<div class="footer">
		<c:import url="include/footer.jsp" />
	</div>
</body>
</html>
