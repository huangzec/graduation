<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	List<Tbtopic> topicListStudent = (List<Tbtopic>) request.getAttribute("topicList-student");
	Settime selectTime 	= (Settime) request.getAttribute("select-time");
 %>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
	.trClass{height: 35px;}
	.class_td{text-align: center;}
</style>
</head>
<body>
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
					<i class="icon-check"></i> 选择课题					
				</h1>
				<div class="widget">										
					<div class="widget-content">
						<i class="icon-star"></i> 选择时间：
						 <%=(Verify.isEmpty(selectTime) ? "<span class=\"label label-default\">非选择课题时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + selectTime.getStartTime() + " ~~~~ " + selectTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>") %>
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				
				<%	if(!Verify.isEmpty(request.getAttribute("message"))){ %>
					<div style="text-align:center">
						<span style="color: red; font-size: 15px;">
							<%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %><br/><br/>
						</span>
					</div>
				<%	} %>
				
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>课题列表</h3>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<div class="tabbable">
							<ul class="nav nav-tabs">
						  		<li class="active">
						  			<a href="#1" data-toggle="tab">个人课题</a>
						  		</li>
						 		<li>
						 			<a href="<%=basePath %>user/topic/teachertopic.do">教师课题</a>
						 		</li>
							</ul>
							<br />
							<div class="tab-content">
								<!-- student-topic -->
								<div class="tab-pane active" id="1">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>
												<td style="text-align: center;">#</td>
												<td style="text-align: center;">名称</td>
												<td style="text-align: center;">来源</td>
												<td style="text-align: center;">关键字</td>
												<td style="text-align: center;">类型</td>
												<td style="text-align: center;">选择</td>
											</tr>
										</thead>
										<tbody>
										<% 
											if(!Verify.isEmpty(topicListStudent) && !Verify.isEmpty(selectTime)) {
												for(int i = 0; !Verify.isEmpty(topicListStudent) && i < topicListStudent.size(); i ++) {
													Tbtopic tbtopic_student = topicListStudent.get(i);
										%>
											<tr>
												<td style="text-align: center;"><%=(i + 1) %></td>
												<td style="text-align: center;">
													<a href="#" class="disabled" title="<%=tbtopic_student.getTopName()%>">
														<%=StringUtil.cutString(tbtopic_student.getTopName(), 20)%>
													</a>
												</td>
												<td style="text-align: center;"><%=HResponse.formatValue("topsource", tbtopic_student.getTopSource(), request) %></td>
												<td style="text-align: center;"><%=tbtopic_student.getTopKeywords() %></td>
												<td style="text-align: center;"><%=HResponse.formatValue("toptype", tbtopic_student.getTopType(), request) %></td>
												<td class="action-td" style="text-align: center;">
													<a href="<%=basePath %>user/topic/intoWriteKnowing.do?id=<%=tbtopic_student.getTopId() %>" class="btn btn-success" title="确认选择">
														<i class="icon-ok"></i>
													</a>
												</td>
											</tr>
											<%	}%>
											<tr>
												<td colspan="6">
													<div style="float: right; margin-right: 20px;">
														<jsp:include page="../common/pagelist.jsp" flush="true" />
													</div>
												</td>
											</tr>
											<% } %>
											<%if(Verify.isEmpty(topicListStudent) || topicListStudent.size() == 0) { %>
												<tr>
													<td colspan="6" style="color: red; text-align: center;">暂无相关记录</td>
												</tr>
											<% } %>
										</tbody>
									</table>
								</div>
								<!-- /student-topic -->
							</div>
							<!-- tab-content -->
						</div>
						<!-- /widget-content -->	
					</div>				
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
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>js/site-judge.js"></script>
</body>
</html>