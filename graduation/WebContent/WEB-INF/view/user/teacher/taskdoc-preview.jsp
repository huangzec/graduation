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
	List<Map<String, String>> prev = (List<Map<String, String>>)request.getAttribute("prev");
	Map<String, String> prevMap = prev.get(0);
	Teacher teacher = (Teacher) request.getAttribute("teacher");
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
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
				<div class="widget widget-table">										
					<div class="widget-header" style="text-align: center;font-size: 20px;font-weight: bold;">
						<span>怀化学院本科毕业设计任务书</span>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="" method="post" id="taskdoc-form">
							<table class="table table-striped table-bordered">
								<tr>
									<td colspan="2">设计题目</td>
									<td colspan="6">
										<%=prevMap.get("title") %>
									</td>
								</tr>
								<tr>
									<td>学生姓名</td>
									<td>
										<%=HResponse.formatEmpty(stuMap.get("stu_Name"), request) %>
									</td>
									<td>系别</td>
									<td><%=department.getDeptName() %></td>
									<td>专业</td>
									<td><%=HResponse.formatEmpty(stuMap.get("pro_Name"), request) %></td>
								</tr>
								<tr>
									<td colspan="2">指导老师姓名</td>
									<td><%=teacher.getTeaName() %></td>
									<td>职称</td>
									<td colspan="2">
										<%=HResponse.formatValue("teapos", teacher.getTeaPos(), request) %>
									</td>
								</tr>
								<tr>
									<td colspan="2">题目来源</td>
									<td colspan="4">
										<div id="taskdoc" data-cur="<%=prevMap.get("source") %>">
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
											<p>毕业设计内容要求：</p>
											<%=HResponse.formatEmpty(prevMap.get("conRequest"), request) %>
										</div>				
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											<p>主要参考资料：</p>
											<%=HResponse.formatEmpty(prevMap.get("refMaterial"), request) %>
										</div>								
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div>
											<p>毕业设计工作计划：</p>
											<%=HResponse.formatEmpty(prevMap.get("workPlane"), request) %>
										</div>
														
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<ul>
											<li>
												接收任务日期 
												<%=HResponse.formatDateTime(prevMap.get("receipttime")) %>
											</li>
											<li>
												要求完成任务日期  
												<%=HResponse.formatDateTime(prevMap.get("finishtime")) %>
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
<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>vendor/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath %>vendor/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/taskdoc.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
  </body>
</html>