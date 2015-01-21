<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*,com.mvc.common.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	Topicfinish topicfinish = (Topicfinish) request.getAttribute("topicfinish");
	Department department = (Department) request.getSession().getAttribute("dept");
%>
<jsp:include page="../common/header.jsp" flush="true" />
</head>
<body background="<%=basePath%>cscon/body-bg.png">
<div class="navbar navbar-fixed-top">	
	<jsp:include page="../common/top.jsp" flush="true" />
	<!-- /navbar-inner -->	
</div> <!-- /navbar -->
<div id="content">	
	<div class="container">		
		<div class="row">
			<jsp:include page="../common/navmenu.jsp" flush="true" />
			<!-- /span3 -->			
			<div class="span9">				
				<h1 class="page-title">
					<i class="icon-home"></i>
					答辩信息					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-header">
						<i class="icon-folder-open"></i>
						<h3 style="margin-right:2px;">答辩材料</h3>
						<i class="icon-arrow-right"></i>
						<h3>毕业答辩相关材料</h3>
						<a href="<%=basePath%>user/rejoin/paper.do" class="btn btn-type f-right">返回</a>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<div style="text-align: center;">
							<%if(!Verify.isEmpty(topicfinish)){ %>
								<div style="font-size: 18px; font-weight: bold;"><%=topicfinish.getTitle() %></div>
								<br/>	
								<div style="font-size: 11px; float: right; margin-right: 180px;">上传时间：<%=topicfinish.getCreateTime() %></div>
								<br/>
								<div style="font-size: 18px;"><%=StringUtil.decodeHtml(topicfinish.getContent()) %></div>
							<%}else{
								out.println("无内容！");
							} %>
						</div>
					</div>
				</div>
			</div> <!-- /widget-content -->	
		</div> <!-- /span9 -->						
	</div> <!-- /row -->	
</div> <!-- /content -->	
<div id="footer">	
	<div class="container">				
		<hr />
		<jsp:include page="../common/footer.jsp" flush="true" />
	</div> <!-- /container -->	
</div> <!-- /footer -->
<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath%>js/confirm.js"></script>
  </body>
</html>