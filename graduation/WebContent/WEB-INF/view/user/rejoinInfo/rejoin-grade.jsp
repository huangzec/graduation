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
					<div class="widget-header">
						<i class="icon-ok-sign"></i>
						<h3>答辩成绩</h3>
						<a href="<%=basePath %>user/rejoin/infoview.do" class="btn btn-type f-right">返回</a>
					</div> <!-- /widget-header -->
					<div class="widget-content">
						<div class="tabbable applytype">
							<div class="tab-content">
								<div class="tab-pane active" id="1">
									<fieldset>
										<div class="control-group">
											<label class="radio inline">
												<button type="submit" class="btn btn-primary" onclick="window.location='<%=basePath%>user/rejoin/opigrade.do'">开题答辩成绩</button>
											</label>
											<hr/>
											<label class="radio inline">
												<button type="submit" class="btn btn-primary" onclick="window.location='<%=basePath%>user/rejoin/gdigrade.do'">毕业答辩成绩</button>
											</label>
										</div>
									</fieldset>
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

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath %>vendor/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script src="<%=basePath %>vendor/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

  </body>
</html>