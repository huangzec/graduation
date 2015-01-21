<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*, com.mvc.entity.*" %>
<%@page import="org.apache.poi.hssf.record.Record"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String status 	= request.getSession().getAttribute("user_status").toString();
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
					<% Message message = (Message) request.getAttribute("record"); %>
					<div class="widget-header">
						<i class="icon-star"></i> <%=StringUtil.cutString(message.getName(), 65) %>
						<div style="float: right; margin-top:6px; margin-right: 15px;"><a class="btn" href="<%=basePath %>user/message/list.do">返回</a></div>
					</div>
					<div class="widget-content">
						<div style="text-align: center;">
							<span>发送人：<%=message.getFromId() %></span>
							<span>接收时间：<%=message.getCreateTime() %></span>
						</div>
						<hr/>
						<div id="w_list_print" style="margin-top: 10px; text-align: center;">
							 <%=message.getContent() %>
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