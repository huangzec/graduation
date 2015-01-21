<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*, com.mvc.entity.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String status 	= request.getSession().getAttribute("user_status").toString();
	String title 	= (String) request.getAttribute("title");
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
			<% if(status.equals("1")) { %>
				<jsp:include page="../common/navmenu.jsp" flush="true" />
			<% } else if(status.equals("2")) { %>
				<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
			<% } %>
			<!-- /span3 -->
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-envelope"></i>
					我的消息				
				</h1>
				<!-- /stat-container -->
				
				<div class="widget">
					<div class="widget-content">
						<div id="w_list_print">
							<form action="<%=basePath %>user/message/unread.do" method="post">
								<table class="table_list">
									<thead>
										<tr style="border-bottom: 1px dashed #e2e2e2;">
											<th colspan="4"><%=Verify.isEmpty(title) ? "" : title %></th>
										</tr>
										<tr style="border-bottom: 1px dashed #e2e2e2;">
											<th height="30px">*</th>
											<td height="30px" align="center">标题</td>
											<td height="30px" align="center">发送人</td>
											<td height="30px" align="center">时间</td>
										</tr>
									</thead>
									<tbody>
										<%
											List<Message> list = (List<Message>)request.getAttribute("list");
											if(!Verify.isEmpty(list)) {
												for(int i = 0; null != list && i < list.size(); i ++) {
													Message message = list.get(i);
											%>
												<tr style="border-bottom: 1px dashed #e2e2e2;">
													<td width="5%" height="30px" align="center"><%=(i + 1) %></td>
													<td width="60%" height="30px" align="center">
														<a href="<%=basePath %>user/message/read.do?id=<%=message.getId() %>" title="<%=message.getName() %>">
															<%=StringUtil.cutString(message.getName(), 30) %>
														</a>
													</td>
													
													<td width="10%" height="30px"><%=HResponse.formatValue("from_id", message.getFromId(), request) %></td>
													<td align="center" height="30px"><%=message.getCreateTime() %></td>
												</tr>
											<% } %>
												<tr>
													<td colspan="4"><jsp:include page="../common/pagelist.jsp" flush="true" /> </td>
												</tr>
										<% } %>
										 <% if(null == list || 1 > list.size()) { %>
											<tr>
												<th colspan="4">暂无相关记录</th>
											</tr>
										<% } %>
									</tbody>
								</table>
							</form>
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