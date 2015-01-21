<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbgrade> list = (List<Tbgrade>) request.getAttribute("gradeList");
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
					<i class="icon-zoom-in"></i>
					毕业论文评阅					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-content">
						 <form class="form-search" method="post" action="<%=basePath %>user/revieworder/list.do">
						 	<label>请选择毕业届别：</label>
						 	<select name="grade" class="grade" id="revieworder-btn">
								 <option value="">-- 请选择 --</option>
								 <%
								 	if(null != list && 0 < list.size()) {
								 		for(int i = 0; i < list.size(); i ++) {
								 		Tbgrade grade = list.get(i);
								  %>
								 		<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
								 <% 
								 		} 
								 	} 
								 %>
							</select>
						</form>
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