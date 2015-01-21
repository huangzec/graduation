<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage = (List<Message>) request.getAttribute("total_message");
 %>
<jsp:include page="common/header.jsp" flush="true" />
</head>

<body background="<%=basePath %>cscon/body-bg.png">

<div class="navbar navbar-fixed-top">
	
	<jsp:include page="common/top.jsp" flush="true" />
	<!-- /navbar-inner -->
	
</div> <!-- /navbar -->

<div id="content">
	
	<div class="container">
		
		<div class="row">
			<%
				String status 	= (String) (request.getSession().getAttribute("user_status") + "");
				if(!Verify.isEmpty(status) && status.equals("2")) {
			%>
				<jsp:include page="common/navmenu_tea.jsp" flush="true" />
			<%
				} else if(!Verify.isEmpty(status) && status.equals("1")) {
			%>
				<jsp:include page="common/navmenu.jsp" flush="true" />
			<% } %>
			<!-- /span3 -->
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-lock"></i>
					密码修改					
				</h1>
				<!-- /stat-container -->
				<div class="widget">
					<div class="widget-header">
						<h3>修改密码</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content">
						<div class="tabbable">
				<ul class="nav nav-tabs">
				  <li class="active">
				    <a href="#1" data-toggle="tab">设置新密码</a>
				  </li>
				</ul>
				<br />
					<div class="tab-content">
						<div class="tab-pane active" id="1">
						<form id="" class="form-horizontal" method="post" action="<%=basePath %>user/site/repwd.do" />
							<fieldset>
								<div class="control-group">											
									<label class="control-label">原密码</label>
									<div class="controls">
										<input type="password" class="input-medium" name="oldpassword" />
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">新密码</label>
									<div class="controls">
										<input type="password" class="input-medium" id="password1" name="password" />
									</div>			
								</div>
								<div class="control-group">											
									<label class="control-label">确认密码</label>
									<div class="controls">
										<input type="password" class="input-medium" id="password2" name="repassword" />
									</div>			
								</div>
								<div class="control-group">											
									<label class="controls" style="color: red;">${message }</label>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-primary repwdbtn">保存</button> 
									<button type="button" class="btn">取消</button>
								</div>
							</fieldset>
						</form>
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
		<jsp:include page="common/footer.jsp" flush="true" />
	</div> <!-- /container -->
	
</div> <!-- /footer -->


    

<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->

  </body>
</html>