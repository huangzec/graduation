<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			
			<a class="brand" href="<%=basePath %>">怀化学院毕业论文(设计)综合管理系统</a>
			
			<div class="nav-collapse">
			
				<ul class="nav pull-right">
				
					<li class="divider-vertical"></li>
					
					<li class="dropdown">
						
						<a data-toggle="dropdown" class="dropdown-toggle " href="#">
							Welcome　<%=request.getSession().getAttribute("user_name") %> <b class="caret"></b>							
						</a>
						
						<ul class="dropdown-menu">
							<li>
								<a href="<%=basePath %>user/site/repassword.do"><i class="icon-lock"></i> 修改密码</a>
							</li>
							
							<li class="divider"></li>
							
							<li>
								<a href="<%=basePath %>user/enter/loginOut.do"><i class="icon-off"></i> 退　出</a>
							</li>
						</ul>
					</li>
				</ul>
				
			</div> <!-- /nav-collapse -->
			
		</div> <!-- /container -->
		
	</div> 