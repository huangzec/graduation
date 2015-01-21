<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页面不存在 - 怀化学院毕业论文（设计）综合管理系统</title>
<link href="<%=basePath %>themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
	function loadimage(){ 
		document.getElementById("code").src = "<%=basePath %>public/image.jsp?"+Math.random(); 
}
</script>
<script type="text/javascript" src="http://www.qq.com/404/search_children.js" charset="utf-8"></script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#">帮助</a></li>
					</ul>
				</div>
				
			</div>
		</div>
		<div id="login_content">
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p>Page Not Found </p>
					<p>We looked everywhere but we couldn't find it!</p>
					<p>
						您访问的页面不存在,您可以返回 
						<a href="javascript:history.go(-1);">上一页</a>  
						<a href="<%=basePath %>">返回首页</a>
					</p>
				</div>
			</div>
		</div>
		<c:import url="../view/admin/common/footer.jsp" />
	</div>
</body>
</html>