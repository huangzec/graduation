<%@page contentType="text/html; charset=utf-8"%>
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
<title>怀化学院毕业论文（设计）综合管理系统</title>
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
<script src="<%=basePath %>js/admin.js" type="text/javascript"></script>

<script type="text/javascript">
	var basePath = '<%=basePath %>';
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}
</script>
<script src="<%=basePath %>js/judge.js" type="text/javascript"></script>
<script src="<%=basePath %>js/score.js" type="text/javascript"></script>
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
				<a class="logo" href="<%=basePath %>admin/index.do">标志</a>
				<ul class="nav">
					<li><a href="<%=basePath %>admin/index.do" target="_back" width="600">去网站</a></li>
					<li><a href="<%=basePath %>user/repassword.do" target="dialog">修改密码</a></li>
					<li><a href="<%=basePath %>admin/enter/logout.do">退出</a></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>您好: <%=request.getSession().getAttribute("user_name") %>  系部管理员</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">				
					<div class="accordionHeader">
						<h2><span>Folder</span>常用组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>答辩管理</a>
								<ul>
									<li><a href="<%=basePath %>admin/opentopicinfo/list.do" target="navTab" rel="graduateinfo">开题答辩安排</a></li>
									<li><a href="<%=basePath %>admin/revieworder/list.do" target="navTab" rel="opentopicresult">毕业评阅教师安排</a></li>
									<li><a href="<%=basePath %>admin/graduateinfo/list.do" target="navTab" rel="graduateinfo">毕业答辩安排</a></li>
								</ul>
							</li>
							<li><a>答辩结果与成绩</a>
								<ul>
									<li><a href="<%=basePath %>admin/opentopicinfo/optscore.do" target="navTab" rel="graduateinfo">开题答辩成绩</a></li>
									<li><a href="<%=basePath %>admin/opentopicinfo/optresult.do" target="navTab" rel="graduateinfo">开题答辩概况</a></li>
									<li><a href="<%=basePath %>admin/opentopicinfo/tableoneresult.do" target="navTab" rel="graduateinfo">表一成绩</a></li>
									<li><a href="<%=basePath %>admin/opentopicinfo/tabletworesult.do" target="navTab" rel="graduateinfo">表二成绩</a></li>
									<li><a href="<%=basePath %>admin/opentopicinfo/tablethreeresult.do" target="navTab" rel="graduateinfo">表三成绩</a></li>
									<li><a href="<%=basePath %>admin/graduateinfo/totalscore.do" target="navTab" rel="graduateinfo">论文(设计)总成绩</a></li>
									<li><a href="<%=basePath %>admin/graduateinfo/gderesult.do" target="navTab" rel="graduateinfo">毕业答辩概况</a></li>
									<li><a href="<%=basePath %>admin/rejoin/alldoclist.do" target="navTab" rel="graduateinfo">系部所有学生材料</a></li>
								</ul>
							</li>
							<li><a>个人中心</a>
								<ul>
									<li><a href="<%=basePath %>admin/index/main.do" target="navTab" rel="main">我的主页</a></li>
									<li><a href="<%=basePath %>admin/manager/perfile.do" target="navTab" rel="perfile">我的信息</a></li>
									<li><a href="<%=basePath %>admin/message/addview.do" target="navTab" rel="sendmessage">发送消息</a></li>
								</ul>
							</li>
							<li><a href="<%=basePath %>admin/settime/setsubmit.do" target="navTab" rel="setsubmit">设置时间</a></li>
							<li><a href="<%=basePath %>admin/settime/timeorder.do" target="navTab" rel="timeorder">浏览时间安排</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>课题信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>课题提交信息</a>
								<ul>
									<li>
										<a href="<%=basePath %>admin/topicorderreview/topictotallist.do" target="navTab" rel="topictotallist">
											提交信息
										</a>
									</li>
									<li>
										<a href="<%=basePath %>admin/topicorderreview/topicsearchlist.do" target="navTab" rel="topicsearchlist">
											高级检索
										</a>
									</li>
								</ul>
							</li>
							<li><a>课题评审</a>
								<ul>
									<li>
										<a href="<%=basePath %>admin/topicorderreview/list.do" target="navTab" rel="topicorderreviewlist">
											课题评审安排
										</a>
									</li>
									<li>
										<a href="<%=basePath %>admin/topicorderreview/topicresult.do" target="navTab" rel="topicresult">
											课题评审结果
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>学生管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>添加学生</a>
								<ul>
									<li><a href="<%=basePath %>admin/student/addoneview.do" target="navTab" rel="addstudent">单个添加学生</a></li>
									<li><a href="<%=basePath %>admin/student/excelimport.do" target="navTab" rel="excelimport">excel导入</a></li>
								</ul>
							</li>
							<li><a href="<%=basePath %>admin/student/stulist.do" target="navTab" rel="studentlist">浏览学生</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>教师管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a>添加教师</a>
								<ul>
									<li><a href="<%=basePath %>admin/teacher/addoneview.do" target="navTab" rel="addteacher">单个添加教师</a></li>
									<li><a href="<%=basePath %>admin/teacher/excelimport.do" target="navTab" rel="excelteacher">excel导入</a></li>
								</ul>
							</li>
							<li><a href="<%=basePath %>admin/teacher/teachlist.do" target="navTab" rel="teacherlist">浏览教师</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>年级管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=basePath %>admin/grade/addview.do" target="navTab" rel="addgrade">添加年级</a></li>
							<li><a href="<%=basePath %>admin/grade/list.do" target="navTab" rel="gradelist">浏览年级</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>专业管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="<%=basePath %>admin/profession/addview.do" target="navTab" rel="addprofession">添加专业</a></li>
							<li><a href="<%=basePath %>admin/profession/list.do" target="navTab" rel="professionlist">浏览专业</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>班级管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="<%=basePath %>admin/tbclass/addview.do" target="navTab" rel="addtbclass">添加班级</a></li>
							<li><a href="<%=basePath %>admin/tbclass/list.do" target="navTab" rel="tbclasslist">浏览班级</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>教室管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="<%=basePath %>admin/room/addview.do" target="navTab" rel="addroom">添加教室</a></li>
							<li><a href="<%=basePath %>admin/room/list.do" target="navTab" rel="roomlist">浏览教室</a></li>
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
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p>您好: <a title="我的信息" href="<%=basePath %>admin/manager/perfile.do" target="navTab"><%=request.getSession().getAttribute("user_name") %></a>  系部管理员</p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							<h2>新闻动态:</h2>
							<div class="unit news-box">正在加载中，请您稍后...</div>
							<div class="divider"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" flush="true" />
	<script type="text/javascript">
		$(function() {
			$(".news-box").load(
				'<%=basePath %>admin/httpclient/getpagecontent.do',
				function(response, status) {
					if(status != 'success') {
						return;
					}
					$(".new-box").html(response);
				}
			);
		});
	</script>
</body>
</html>