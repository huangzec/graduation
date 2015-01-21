<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="../common/header.jsp" flush="true" />
<link href="<%=basePath%>vendor/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" />
<link href="<%=basePath%>css/style.css" rel="stylesheet" />
<link href="<%=basePath%>vendor/bootstrap-switch/css/highlight.css" rel="stylesheet" />
<link href="<%=basePath%>vendor/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" />
<%
	Teacher teacher            = (Teacher) request.getAttribute("teacher");
	Department department      = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Taskdoc taskdoc            = (Taskdoc) request.getAttribute("task-doc");
	Selectfirst selectfirst    = (Selectfirst) request.getAttribute("selectfirst");
%>
</head>
<body background="<%=basePath %>cscon/body-bg.png">
	<div style="margin-top: 3%;">
		<div class="container">
			<!-- /span3 -->
			<div style="text-align: center; margin-bottom: 15px;">
				<a class="btn btn-primary" onclick=preview(1)>打印</a> &nbsp;&nbsp;
				<a class="btn btn-info" href="<%=basePath%>user/rejoin/document.do">返回</a>
			</div>
			<div class="widget-table" style="width: 70%; margin-left: 169px;">
				<div class="widget-content" id="printarea"
					style="text-align: center; font-size: 20px; font-weight: bold;">
					<% if(!Verify.isEmpty(taskdoc)){ %>
						<!-- startprint1 -->
						<table class="table table-striped table-bordered">
								<tr>
									<td colspan="8">
										<div style="text-align: center; font-size: 20px; font-weight: bold; margin-top: 10px; margin-bottom: 10px;">
											<span>
												<% if(selectfirst.getTbtopic().getTopType().equals("1")){ %>
													怀化学院本科毕业论文任务书
												<% }else{ %>
													怀化学院本科毕业设计任务书
												<% } %>
											</span>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;">设计题目</td>
									<td colspan="6" style="text-align: center;">
										<%=taskdoc.getTitle() %>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;">学生姓名</td>
									<td style="text-align: center;">
										<%=HResponse.formatEmpty(stuMap.get("stu_Name"), request) %>
									</td>
									<td style="text-align: center;">系别</td>
									<td style="text-align: center;"><%=department.getDeptName() %></td>
									<td style="text-align: center;">专业</td>
									<td style="text-align: center;"><%=HResponse.formatEmpty(stuMap.get("pro_Name"), request) %></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;">指导老师</td>
									<td style="text-align: center;"><%=teacher.getTeaName() %></td>
									<td style="text-align: center;">职称</td>
									<td colspan="2" style="text-align: center;">
										<%=HResponse.formatValue("teapos", teacher.getTeaPos(), request) %>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;">题目来源</td>
									<td colspan="4" style="text-align: center;"><%=HResponse.formatValue("topsource", taskdoc.getSource(), request) %></td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											<p>毕业设计内容要求：</p>
											<%=StringUtil.decodeHtml(taskdoc.getTdConRequest()) %>
										</div>				
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											<p>主要参考资料：</p>
											<%=StringUtil.decodeHtml(taskdoc.getTdRefMaterial()) %>
										</div>								
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											<p>毕业设计工作计划：</p>
											<%=StringUtil.decodeHtml(taskdoc.getTdWorkPlane()) %>
										</div>
														
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<ul>
											<div style="margin-top: 10px;">
												<div style="float: left; margin-left: 30px;">
													接收任务日期： <%=HResponse.formatDateTime(taskdoc.getReceiptTime()) %>
												</div>
												<div style="float: right; margin-right: 60px;">
													要求完成任务日期： <%=HResponse.formatDateTime(taskdoc.getFinishTime()) %>
												</div>
											</div>
											<div style="padding-top: 30px;">
												<div style="float: left; margin-left: 30px;">学 　 　 生   _________（签名）</div>
											    <div style="float: right; margin-right: 60px;">_______年_______ 月_______日</div>
											</div>
											<div style="padding-top: 30px;">
												<div style="float: left; margin-left: 30px;">指 导 教 师  _________（签名）</div>
												<div style="float: right; margin-right: 60px;">_______年_______ 月_______日</div>
											</div>
											<div style="padding-top: 30px; padding-bottom: 20px;">
												<div style="float: left; margin-left: 30px;">系　主　任_________（签名） </div>
												<div style="float: right; margin-right: 60px;">_______年_______ 月_______日</div>
											</div>
										</ul>
									</td>
								</tr>
								
							</table>
					</div> <!-- /widget-content -->	
					<div style="margin-left: 20px;margin-top: 5px; margin-bottom: 10px;">
						说明：本表为学生毕业论文（设计）指导性文件，由指导教师填写，一式两份，一份交系（部）存档备查，一份发给学生。
					</div>
					<div style="margin-top: 20px;"></div>
					<!-- endprint1 -->
					<%}else{ %>
						<div style="text-align: center; font-size: 16px; color: red; margin-top: 15px;">
							暂无相关记录。
						</div>
					<%} %>	
				</div>	
			</div>
		</div>
	</div>
	<!-- Le javascript
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>cscon/js/jquery-1.7.2.min.js"></script>
	<script src="<%=basePath%>cscon/js/bootstrap.js"></script>
	<script src="<%=basePath%>vendor/hhjslib/hhjslib.js"></script>
	<script src="<%=basePath%>vendor/hhjslib/hhjslib.dialog.js"></script>
	<script src="<%=basePath%>vendor/hhjslib/hhjslib.utils.js"></script>
	<script
		src="<%=basePath%>vendor/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script
		src="<%=basePath%>vendor/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="<%=basePath%>js/printview.js"></script>
</body>
</html>