<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Student student 			= (Student) request.getAttribute("student");
	Department department 		= (Department) request.getSession().getAttribute("dept");
	List<Map<String, String>> list 	= (List<Map<String, String>>) request.getAttribute("pos_list");
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
						<% if(!Verify.isEmpty(student)) { %>
						<form id="" class="form-horizontal" method="post" enctype="multipart/form-data" action="<%=basePath %>user/site/stueditfile.do" />
							<fieldset>
								<div class="control-group">											
									<label class="control-label">学　　号：</label>
									<div class="controls">
										<%=student.getStuId() %>
										<input type="hidden" name="id" value="<%=student.getStuId() %>" />
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">姓　　名：</label>
									<div class="controls">
										<%=student.getStuName() %>
										<input type="hidden" name="name" />
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
										<% if(student.getStuSex().equals("1")) { %>
											<input type="radio" value="1" name="sex" checked />男　　
											<input type="radio" value="2" name="sex" />女
										<% } else { %>
											<input type="radio" value="1" name="sex" />男　　
											<input type="radio" value="2" name="sex" checked />女
										<% } %>
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">联系电话：</label>
									<div class="controls">
										<input type="text" class="input-large" name="phone" value="<%=student.getStuTel() %>" />
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">电子邮箱：</label>
									<div class="controls">
										<input type="text" class="input-large" name="email" id="email" value="<%=student.getStuEmail() %>" />
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">头　　像：</label>
									<div class="controls">
										<input type="file" class="input-large" name="image_path" />
										<a href="<%=basePath + (Verify.isEmpty(student.getImagePath()) ? "" : student.getImagePath()) %>" target="_black">
											<img alt="<%=student.getStuName() %>" src="<%=basePath + (Verify.isEmpty(student.getImagePath()) ? "images/noimg.png" : student.getImagePath()) %>" width="250px;">
										</a>
									</div>			
								</div>
								<div class="control-group">											
									<label class="controls" style="color: red;">${message }</label>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-primary teaperfilebtn">确认修改</button> 
								</div>
							</fieldset>
						</form>
						<% } else { %>
							<div class="text-center" style="color: red;">记录不存在</div>
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
<script src="<%=basePath %>js/perfile.js"></script>

  </body>
</html>