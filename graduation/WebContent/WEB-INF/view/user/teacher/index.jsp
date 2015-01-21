<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage 	= (List<Message>) request.getAttribute("total_message");
	List<Tbgrade> gradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
 %>
<jsp:include page="../common/header.jsp" flush="true" />
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
				<h1 class="page-title">
					<i class="icon-home"></i>
					我的主页					
				</h1>
				<jsp:include page="../common/center-stat.jsp" flush="true" />
				<!-- /stat-container -->
				<div class="widget">
					<div class="widget-header">
						<h3>
							指导的学生开题情况 选择年级
							<select id="tbgrade">
								<option value="">-- 请选择 --</option>
								<%
									if(!Verify.isEmpty(gradeList)) {
										for(int i = 0; i < gradeList.size(); i ++) {
											Tbgrade grade = gradeList.get(i);
											%>
											<option value="<%=grade.getGraId() %>">--<%=grade.getGraNumber() %>--</option>
											<%
										}
									}
								 %>
							</select> 
						</h3>
					</div> <!-- /widget-header -->	
					<div class="widget-content">
						<div id="pie-chart" class="chart-holder"></div>
					</div> <!-- /widget-content -->
				</div> <!-- /widget -->
				<div class="widget">
					<div class="widget-header">
						<h3>
							指导的学生毕业答辩情况 选择年级
							<select id="grade">
								<option value="">-- 请选择 --</option>
								<%
									if(!Verify.isEmpty(gradeList)) {
										for(int i = 0; i < gradeList.size(); i ++) {
											Tbgrade grade = gradeList.get(i);
											%>
											<option value="<%=grade.getGraId() %>">--<%=grade.getGraNumber() %>--</option>
											<%
										}
									}
								 %>
							</select> 
						</h3>
					</div> <!-- /widget-header -->	
					<div class="widget-content">
						<div id="finish-pie-chart" class="chart-holder"></div>
					</div> <!-- /widget-content -->
				</div> <!-- /widget -->
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
<script src="<%=basePath %>cscon/js/jquery.flot.js"></script>
<script src="<%=basePath %>cscon/js/jquery.flot.pie.js"></script>
<script src="<%=basePath %>cscon/js/charts/pie.js"></script>
  </body>
</html>