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
						<i class="icon-map-marker"></i>
						<h3>答辩时间、地点</h3>
						<a href="<%=basePath %>user/rejoin/infoview.do" class="btn btn-type f-right">返回</a>
					</div> <!-- /widget-header -->
					<div class="widget-content">
						<form id="apply-form" class="form-horizontal" method="post" action="<%=basePath%>user/rejoin/gettpinfo.do" />
							<div class="tabbable applytype">
								<ul class="nav nav-tabs">
								  <li class="active">
								    <a href="#1" data-toggle="tab">选择答辩类型</a>
								  </li>
								</ul>
								<br />
								<div class="tab-content">
									<div class="tab-pane active" id="1">
									<fieldset>
											<div class="control-group">											
												<label class="radio inline">
												    <input type="radio" name="type" value="1" checked>开题答辩
												</label>
												<label class="radio inline">
													<input type="radio" name="type" value="2" />毕业答辩
												</label>			
											</div>
											<div class="control-group">											
												<label class="controls" style="color: red;">${message }</label>
											</div>
											
										</fieldset>
									
									</div>
								</div>
								<div class="text-right">
									<button type="submit" class="btn btn-info">确定</button>
								</div>
							</div>
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

<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>vendor/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath %>vendor/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

  </body>
</html>