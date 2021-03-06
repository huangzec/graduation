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
							<i class="icon-folder-open"></i>
							<h3>答辩材料</h3>
							<a href="<%=basePath %>user/rejoin/infoview.do" class="btn btn-type f-right">返回</a>
						</div> <!-- /widget-header -->
						<div class="widget-content">
							<div class="tabbable applytype">
								<div class="tab-content">
									<div class="tab-pane active" id="1">
										<fieldset>
											<div class="control-group">
												<label class="radio inline">
													<a class="btn btn-success" onclick="window.location='<%=basePath%>user/rejoin/gradeone.do'">成绩表一</a>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<a class="btn btn-success" onclick="window.location='<%=basePath%>user/rejoin/gradetwo.do'">成绩表二</a>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<a class="btn btn-success" href="<%=basePath%>user/rejoin/gradethree.do" target="_blank">成绩表三</a>
												</label>
												<hr/>
												<label class="radio inline">
													<a class="btn btn-success" onclick="window.location='<%=basePath%>user/rejoin/paper.do'">相关材料</a>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<a class="btn btn-success" href="<%=basePath%>user/rejoin/openreport.do" target="_blank">开题报告书</a>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<a class="btn btn-success" href="<%=basePath%>user/rejoin/taskdoc.do" target="_blank">任务书</a>
												</label>
												<hr/>
												<label class="radio inline">
													<div style="float: left;"><a class="btn btn-warning" onclick="window.location='<%=basePath %>user/rejoin/alldocview.do'" title="此为毕业答辩结束通过以后上传所有材料">上传所有材料</a></div>
													<div style="float: left; margin-top: 5px; margin-left:3px;">(此为毕业答辩结束通过以后上传所有材料,包括源代码、论文正本、可执行程序等)</div>
												</label>
											</div>
										</fieldset>
									</div>
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