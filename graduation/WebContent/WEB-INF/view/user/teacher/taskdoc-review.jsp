<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../common/header.jsp" flush="true" />
<link href="<%=basePath %>vendor/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" /> 
<link href="<%=basePath %>css/style.css" rel="stylesheet" /> 
<link href="<%=basePath %>vendor/bootstrap-switch/css/highlight.css" rel="stylesheet" />
<link href="<%=basePath %>vendor/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" /> 
<%
	List<Selectfirst> list = (List<Selectfirst>) request.getAttribute("list");
	Tbtopic tbtopic = (Tbtopic) request.getAttribute("topic");
	Teacher teacher = (Teacher) request.getAttribute("teacher");
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Taskdoc taskdoc 	= (Taskdoc) request.getAttribute("taskdoc");
 %>
</head>
<body background="<%=basePath %>cscon/body-bg.png">
<div class="navbar navbar-fixed-top">	
	<jsp:include page="../common/top.jsp" flush="true" />
	<!-- /navbar-inner -->	
</div> <!-- /navbar -->
<div id="content">	
	<div class="container">		
		<div class="row">
			<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
			<!-- /span3 -->			
			<div class="span9">				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-header">
						<i class="icon-file"></i>  <h3><a href="<%=basePath %>user/taskdoc/senddoclist.do" title="下发毕业(论文)设计任务书">下发毕业(论文)设计任务书</a> </h3>
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				<div class="widget widget-table">										
					<div class="widget-header" style="text-align: center;font-size: 20px;font-weight: bold;">
						<span>
							怀化学院本科毕业<%
											if(!Verify.isEmpty(tbtopic)) {
												if(tbtopic.getTopType().equals("1")) {
													out.print("论文");
												}else {
													out.print("设计");
												}
											}
										 %>任务书
						</span>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="" method="post" id="taskdoc-form">
							<input type="hidden" name="stuid" value="" />
							<input type="hidden" name="teaid" value="" />
							<table class="table table-striped table-bordered">
								<tr>
									<td colspan="2">
										<%
											if(!Verify.isEmpty(tbtopic)) {
												if(tbtopic.getTopType().equals("1")) {
													out.print("论文");
												}else {
													out.print("设计");
												}
											}
										 %>题目
									</td>
									<td colspan="6">
										<%=(Verify.isEmpty(tbtopic) ? "" : tbtopic.getTopName()) %>
										<input type="hidden" name="title" value="<%=(Verify.isEmpty(tbtopic) ? "" : tbtopic.getTopName()) %>" />
										<input type="hidden" name="topicid" value="<%=request.getParameter("topicid") %>" />
									</td>
								</tr>
								<tr>
									<td>学生姓名</td>
									<td>
										<%=HResponse.formatEmpty(stuMap.get("stu_Name"), request) %>
										<input type="hidden" name="taskerid" value="<%=request.getParameter("taskerid") %>" />
									</td>
									<td>系别</td>
									<td><%=department.getDeptName() %></td>
									<td>专业</td>
									<td><%=HResponse.formatEmpty(stuMap.get("pro_Name"), request) %></td>
								</tr>
								<tr>
									<td colspan="2">指导老师姓名</td>
									<td>
										<%=teacher.getTeaName() %>
										<input type="hidden" name="teacherid" value="<%=teacher.getTeaId() %>" />
									</td>
									<td>职称</td>
									<td colspan="2">
										<input type="hidden" name="teacherpos" value="<%=teacher.getTeaPos() %>" />
										<%=HResponse.formatValue("teapos", teacher.getTeaPos(), request) %>
									</td>
								</tr>
								<tr>
									<td colspan="2">题目来源</td>
									<td colspan="4">
										<div id="taskdoc" data-cur="<%=(Verify.isEmpty(taskdoc) ? "" : taskdoc.getSource()) %>">
											<label class="radio inline">
											  <input type="radio" name="source" value="1" checked> 1.科学技术
											</label>
											<label class="radio inline">
											  <input type="radio" name="source" value="2"> 2.生产实践 
											</label>
											<label class="radio inline">
											  <input type="radio" name="source" value="3"> 3.社会经济
											</label>
											<label class="radio inline">
											  <input type="radio" name="source" value="4"> 4.自拟
											</label>
											<label class="radio inline">
											  <input type="radio" name="source" value="5"> 5.其他  
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											毕业<%
												if(!Verify.isEmpty(tbtopic)) {
													if(tbtopic.getTopType().equals("1")) {
														out.print("论文");
													}else {
														out.print("设计");
													}
												}
											 %>内容要求：
												<%=StringUtil.decodeHtml(Verify.isEmpty(taskdoc) ? "" : taskdoc.getTdConRequest()) %>											
										</div>				
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											主要参考资料:
												<%=StringUtil.decodeHtml(Verify.isEmpty(taskdoc) ? "" : taskdoc.getTdRefMaterial()) %>
										</div>								
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											毕业<%
												if(!Verify.isEmpty(tbtopic)) {
													if(tbtopic.getTopType().equals("1")) {
														out.print("论文");
													}else {
														out.print("设计");
													}
												}
											 %>工作计划：
												<%=StringUtil.decodeHtml(Verify.isEmpty(taskdoc) ? "" : taskdoc.getTdWorkPlane()) %>											
										</div>
														
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<ul>
											<li>
												接收任务日期 
												<input type="text" readonly="true" value="<%=HResponse.formatEmpty(Verify.isEmpty(taskdoc) ? "" : taskdoc.getReceiptTime(), request) %>" />
											</li>
											<li>
												要求完成任务日期
												<input type="text" readonly="true" value="<%=HResponse.formatEmpty(Verify.isEmpty(taskdoc) ? "" : taskdoc.getFinishTime(), request) %>" />
											</li>
											<li>学 　 　 生   _________（签名）     </li>
											<li>_______年_______ 月_______日</li>
											<li>指 导 教 师  _________（签名） </li>
											<li>_______年_______ 月_______日</li>
											<li>系　主　任_________（签名） </li>
											<li>_______年_______ 月_______日</li>
										</ul>
									</td>
								</tr>
								
							</table>
						</form>
					</div> <!-- /widget-content -->	
					<div style="margin-left: 20px;margin-top: 5px;">
						说明：本表为学生毕业论文（设计）指导性文件，由指导教师填写，一式两份，一份交系（部）存档备查，一份发给学生。
					</div>
					<div style="float: right;">
						<button class="btn btn-primary" id="edit-btn" type="button">修改</button>
						<input type="hidden" name="topicid" value="<%=request.getParameter("topicid") %>" />
						<input type="hidden" name="taskerid" value="<%=request.getParameter("taskerid") %>" />
					</div>				
				</div>
			</div> <!-- /span9 -->						
		</div> <!-- /row -->		
	</div> <!-- /container -->	
</div> <!-- /content -->	
<div id="footer">	
	<div class="container">				
		<hr />
		<jsp:include page="../common/footer.jsp" flush="true" />
	</div> <!-- /container -->	
</div> <!-- /footer -->
<script src="<%=basePath %>js/taskdoc.js"></script>
  </body>
</html>