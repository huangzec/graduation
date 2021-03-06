<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	List<Tbtopic> topicList = (List<Tbtopic>) request.getAttribute("topicList");
	Settime transfertime = (Settime) request.getAttribute("transfer-time");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
.trClass { height: 35px; }
</style>

<body>
	<div class="navbar navbar-fixed-top">
		<jsp:include page="../common/top.jsp" flush="true" />
		<!-- /navbar-inner -->
	</div>
	<!-- /navbar -->
	<div id="content">
		<div class="container">
			<div class="row">
				<jsp:include page="../common/navmenu.jsp" flush="true" />
				<!-- /span3 -->
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-share-alt"></i> 课题转让
					</h1>
					<div class="widget">
						<div class="widget-content">
							<i class="icon-star"></i> 转让时间：
							<%=(Verify.isEmpty(transfertime) ? "<span class=\"label label-default\">非转让时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + transfertime.getStartTime() + " ~~~~ " + transfertime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>")%>
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
					<div class="widget widget-table">
						<div style="color: red; text-align: center; font-size: 15px;">
							<%=request.getAttribute("message") %>
						</div>
					</div>
				</div><!-- /span9 -->
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /content -->
	<div id="footer">
		<div class="container">
			<hr />
			<jsp:include page="../common/footer.jsp" flush="true" />
		</div>
		<!-- /container -->
	</div>
	<!-- /footer -->
	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>js/site-judge.js"></script>
</body>
</html>