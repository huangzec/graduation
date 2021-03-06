<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>怀化学院毕业论文(设计)综合管理系统</title>
<link href="<%=basePath %>themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
	function loadimage(){ 
		document.getElementById("code").src = "<%=basePath %>public/image.jsp?"+Math.random(); 
}
</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo" style="width: 550px;">
				<img src="<%=basePath %>themes/default/images/_logo.png" />
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="<%=basePath %>">返回网站</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="<%=basePath %>themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="<%=basePath %>admin/enter/adminLogin.do" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					<p>
						<label>身份：</label>
						<select name="status">
							<option value="3">系部管理员</option>
							<option value="4">教务处管理员</option>
						</select>
					</p>
					<p>
						<label>验证码：</label>
						<input type="text" class="required" name="code" size="10"> 
						<a href="javascript:loadimage();">
							<img src="<%=basePath %>public/image.jsp" BORDER="0" alt="vcode" title="看不清？换一张" id="code" style="cursor:pointer;width: 50px;" align="absmiddle">
						</a>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
					<div class="message">${message }</div>
				</form>
			</div>
			<div class="login_banner"><img src="<%=basePath %>themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">下载驱动程序</a></li>
					<li><a href="#">如何安装密钥驱动程序？</a></li>
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p>您可以使用 网易网盘 ，随时存，随地取</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
				</div>
			</div>
		</div>
		<jsp:include page="common/footer.jsp" flush="true" />
	</div>
</body>
</html>