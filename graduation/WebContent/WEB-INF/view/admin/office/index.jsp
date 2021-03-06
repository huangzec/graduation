<%@page contentType="text/html; charset=utf-8"%>
<%@page session="false"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>怀化学院毕业论文(设计)综合管理系统</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>favicon.ico" />
<link href="<%=basePath %>themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath %>uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- vendor -->
<link href="<%=basePath %>vendor/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>vendor/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>vendor/ueditor/themes/iframe.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="<%=basePath %>themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<script src="<%=basePath %>js/speedup.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=basePath %>xheditor/xheditor-1.1.12-zh-cn.min.js" type="text/javascript"></script>
<script src="<%=basePath %>uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="<%=basePath %>uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<script src="<%=basePath %>bin/dwz.min.js" type="text/javascript"></script>

<script src="<%=basePath %>js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.js" type="text/javascript"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.utils.js" type="text/javascript"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.dialog.js" type="text/javascript"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.validate.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.marquee.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/admin.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("<%=basePath %>dwz.frag.xml", {
		loginUrl:"<%=basePath %>user/ajaxlogin.do", loginTitle:"登录",	// 弹出登录对话框
		//loginUrl:"index.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://www.hhtc.edu.cn/" target="_blank">标志</a>
				<ul class="nav">
					<li><a href="<%=basePath %>admin/index.do" target="_back" width="600">去网站</a></li>
					<li><a href="<%=basePath %>user/repassword.do" target="dialog">修改密码</a></li>
					<li><a href="<%=basePath %>admin/enter/logout.do">退出</a></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a class="office" href="<%=basePath %>admin/index/office.do" target="navTab" rel="main">我的主页</a></li>
							<li><a class="" href="<%=basePath %>admin/message/addview.do" target="navTab" rel="sendmessage">发送消息</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>系部管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">							
							<li><span>添加系部</span>
								<ul>
									<li><a href="<%=basePath %>/admin/dept/intoAddOneDept.do" target="navTab" rel="intoAddOneDept">单个添加系部</a></li>
									<li><a href="<%=basePath %>/admin/dept/intoAddLotDept.do" target="navTab" rel="intoAddLotDept">批量添加系部</a></li>
								</ul>
							</li>
							<li><a href="<%=basePath %>admin/dept/deptList.do" target="navTab" rel="deptList">浏览系部</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>系部管理员管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">							
							<li><span>添加系部管理员</span>
								<ul>
									<li><a href="<%=basePath %>/admin/manager/intoAddOneDeptMgr.do" target="navTab" rel="intoAddOneDeptMgr">单个添加系部管理员</a></li>
									<li><a href="<%=basePath %>/admin/manager/intoAddLotDeptMgr.do" target="navTab" rel="intoAddLotDeptMgr">批量添加系部管理员</a></li>
								</ul>
							</li>
							<li><a href="<%=basePath %>/admin/manager/DeptMgrList.do" target="navTab" rel="deptMgrList">浏览系部管理员</a></li>
						</ul>
					</div>
				</div>
			</div>  
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main">
								<a href="javascript:;">
									<span class="home_icon">我的主页</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p>您好: <%=request.getSession().getAttribute("user_name") %>，教务处管理员</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" flush="true" />
	
</body>
</html>