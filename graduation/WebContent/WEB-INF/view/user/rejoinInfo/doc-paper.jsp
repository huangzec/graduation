<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*,com.mvc.common.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	List<Topicfinish> list = (List<Topicfinish>) request.getAttribute("list");
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
						<a href="<%=basePath%>user/rejoin/document.do" class="btn btn-type f-right">返回</a>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<div style="text-align: center;">
							<table class="table table-bordered">
								<thead>
									<tr class="tabletr">
										<td>#</td>
										<td>标题</td>
										<td>提交时间</td>
									</tr>
								</thead>
								<tbody>
									<%
										if(list != null && list.size() > 0){
											for(int i = 0; i < list.size(); i++){
												Topicfinish topicfinish = list.get(i);
									 %>
									 	<tr class="tabletr">
									 		<td><%=i+1 %></td>
									 		<td><a href="<%=basePath %>user/rejoin/papeldetail.do?id=<%=topicfinish.getId() %>"><%=topicfinish.getTitle() %></a></td>
									 		<td><%=topicfinish.getCreateTime() %></td>
									 	</tr>
									 <%		}
									 	}else{
									  %>
									  	<tr class="tabletr">
									  		<td colspan="3" class="color-red">暂无相关记录。</td>
									  	</tr>
									  <%	} %>
								</tbody>
							</table>
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