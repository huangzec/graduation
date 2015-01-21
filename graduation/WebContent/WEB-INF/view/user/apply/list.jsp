<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Apply> list = (List<Apply>) request.getAttribute("list");
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
					<i class="icon-edit"></i>
					答辩申请					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-content">
						 <h3><i class="icon-plus"></i> <a href="<%=basePath %>user/apply/addview.do">申请答辩</a> </h3>
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>我的答辩申请列表</h3>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="<%=basePath %>judge/judgeview.do" method="post" id="judge-form">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<td>#</td>
										<td>申请人</td>
										<td>类型</td>
										<td>是否同意参加答辩</td>
										<td>状态</td>
										<td>提交申请时间</td>
									</tr>
								</thead>
								<tbody>
									<% 
										if(!Verify.isEmpty(list)) {
											for(int i = 0; !Verify.isEmpty(list) && i < list.size(); i ++) {
											Apply apply = list.get(i);
									%>
									<tr>
										<td><%=(i + 1) %></td>
										<td><%=request.getSession().getAttribute("user_name") %></td>
										<td><%=HResponse.formatValue("type", apply.getType(), request) %></td>
										<td><%=HResponse.formatValue("pass", apply.getPass(), request) %></td>
										<td><%=HResponse.formatValue("status", apply.getStatus(), request) %></td>
										<td><%=apply.getCreateTime() %></td>
									</tr>
									<% } %>
									<tr>
										<td colspan="7"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
									</tr>
									<% } if(Verify.isEmpty(list) || list.size() == 0) { %>
									<tr>
										<td colspan="7" style="color: red; text-align: center;">暂无相关记录</td>
									</tr>
									<% } %>
								</tbody>
							</table>
						</form>
					</div> <!-- /widget-content -->					
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
<script src="<%=basePath %>js/site-judge.js"></script>
  </body>
</html>