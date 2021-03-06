<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
			<jsp:include page="../common/navmenu.jsp" flush="true" />
			<!-- /span3 -->			
			<div class="span9">				
				<h1 class="page-title">
					<i class="icon-list-alt"></i>答辩信息				
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-content">
						 <h3>
						 	<span style="margin-right: 180px; margin-left:60px;">
						 		<i class="icon-arrow-right"></i>
						 		<a href="<%=basePath %>user/rejoin/timeplace.do">答辩时间、地点</a>
						 	</span>
						 	<span style="margin-right: 180px;">
						 		<i class="icon-arrow-right"></i> 
						 		<a href="<%=basePath %>user/rejoin/grade.do">答辩成绩</a>
						 	</span>
						 	<span>
						 		<i class="icon-arrow-right"></i> 
						 		<a href="<%=basePath %>user/rejoin/document.do">答辩材料</a>
						 	</span>
						 </h3>
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
<script src="<%=basePath %>js/site-judge.js"></script>
  </body>
</html>