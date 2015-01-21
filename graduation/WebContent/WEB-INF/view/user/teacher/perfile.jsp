<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage 	= (List<Message>) request.getAttribute("total_message");
	Teacher teacher 			= (Teacher) request.getAttribute("teacher");
	Department department 		= (Department) request.getSession().getAttribute("dept");
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
			<%
				String status 	= (String) (request.getSession().getAttribute("user_status") + "");
				if(!Verify.isEmpty(status) && status.equals("2")) {
			%>
				<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
			<%
				} else if(!Verify.isEmpty(status) && status.equals("1")) {
			%>
				<jsp:include page="../common/navmenu.jsp" flush="true" />
			<% } %>
			<!-- /span3 -->
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-user"></i>
					我的个人信息				
				</h1>
				<!-- /stat-container -->
				<div class="widget">
					<div class="widget-content">
						<div class="tabbable">
				<ul class="nav nav-tabs">
				  <li class="active">
				    <a href="#1" data-toggle="tab">个人信息</a>
				  </li>
				</ul>
				<br />
					<div class="tab-content">
						<div class="tab-pane active" id="1">
						<% if(!Verify.isEmpty(teacher)) { %>
						<form id="" class="form-horizontal" method="post" action="" />
							<fieldset>
								<div class="control-group">											
									<label class="control-label">工　　号：</label>
									<div class="controls">
										<%=teacher.getTeaId() %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">姓　　名：</label>
									<div class="controls">
										<%=teacher.getTeaName() %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">所属系部：</label>
									<div class="controls">
										<%=department.getDeptName() %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">性　　别：</label>
									<div class="controls">
										<%=HResponse.formatValue("sex", teacher.getTeaSex(), request) %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">职　　称：</label>
									<div class="controls">
										<%=HResponse.formatValue("teapos", teacher.getTeaPos(), request) %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">联系电话：</label>
									<div class="controls">
										<%=teacher.getTeaTel() %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">电子邮箱：</label>
									<div class="controls">
										<%=teacher.getTeaEmail() %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">头　　像：</label>
									<div class="controls">
										<a href="<%=basePath + (Verify.isEmpty(teacher.getImagePath()) ? "" : teacher.getImagePath()) %>" target="_black">
											<img alt="<%=teacher.getTeaName() %>" src="<%=basePath + (Verify.isEmpty(teacher.getImagePath()) ? "images/noimg.png" : teacher.getImagePath()) %>" width="250px;">
										</a>
									</div>			
								</div>
								<div class="control-group">											
									<label class="controls" style="color: red;">${message }</label>
								</div>
								<div class="form-actions">
									<a class="btn btn-primary btn-link" href="<%=basePath %>user/site/teaeditfileview.do">
										修改
									</a>
								</div>
							</fieldset>
						</form>
						<% } %>
						</div>
					</div>
				</div>
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

<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->

  </body>
</html>