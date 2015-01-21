<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	List<Tbtopic> topicList = (List<Tbtopic>) request.getAttribute("topicList");
	//Settime lookTime = (Settime) request.getSession().getAttribute("looktime");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
	.trClass { height: 35px; }
	.color:visited{color:#FF2400; text-decoration:none;}
	.color:hover{color:#FF2400; text-decoration:none;}
</style>
</head>
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
						<i class="icon-th-list"></i> 查看课题
					</h1>
					<!--<div class="widget">
						<div class="widget-content">
							<i class="icon-star"></i> 查看时间：
							//(Verify.isEmpty(lookTime) ? "<span class=\"label label-default\">非查看时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + lookTime.getStartTime() + " ~~~~ " + lookTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>")%>
						</div>
						 /widget-content 
					</div>
					--><!-- /widget -->
					<div class="widget widget-table">
						<div class="widget-header">
							<i class="icon-th-list"></i>
							<h3>课题列表</h3>
						</div>
						<%if(!Verify.isEmpty(request.getAttribute("message"))){ %>
							<div style="text-align: center; margin-top: 10px; margin-bottom: 10px;">
								<span style="text-align: center; color: #FF6347;"><b><%=request.getAttribute("message")%></b></span>
							</div>
						<%} %>
						<!-- /widget-header -->
						<div class="widget-content">
							<table class="table table-striped table-bordered">
								<thead>
									<tr class="tabletr">
										<td style="text-align: center;">#</td>
										<td style="text-align: center;">名称</td>
										<td style="text-align: center;">内容</td>
										<td style="text-align: center;">来源</td>
										<td style="text-align: center;">关键字</td>
										<td style="text-align: center;">类型</td>
										<td style="text-align: center;">状 态</td>
										<td style="text-align: center;">操作</td>
									</tr>
								</thead>
								<tbody id="judge-form">
									<%
										//if (!Verify.isEmpty(topicList) && !Verify.isEmpty(lookTime)) {
										if (!Verify.isEmpty(topicList)){
											for (int i = 0; !Verify.isEmpty(topicList) && i < topicList.size(); i++) {
												Tbtopic tbtopic = topicList.get(i);
									%>
									<tr>
										<td style="text-align: center;"><%=(i + 1)%></td>
										<td style="text-align: center;"><%=StringUtil.cutString(tbtopic.getTopName(), 15)%></td>
										<td style="text-align: center;">
											<div class="hide header">
												课题  <%=(Verify.isEmpty(tbtopic.getTopName()) ? "" : tbtopic.getTopName())%> 内容
											</div>
											<div class="hide body">
												<p><%=(Verify.isEmpty(tbtopic.getTopContent()) ? "" : tbtopic.getTopContent())%></p>
											</div>
											<a href="javascript:;" class="know" subknow="knowed<%=(i) %>" title="查看">
												查看
										 	</a>
										</td>
										<td style="text-align: center;"><%=HResponse.formatValue("topsource", tbtopic.getTopSource(), request) %></td>
										<td style="text-align: center;"><%=tbtopic.getTopKeywords() %></td>
										<td style="text-align: center;"><%=HResponse.formatValue("toptype", tbtopic.getTopType(), request) %></td>
										<td style="text-align: center;">
											<%	String topStatus = tbtopic.getTopStatus();
												if (topStatus.equals("0")) {
													out.println("未审核");
												} if (topStatus.equals("1")) {
													out.println("审核通过");
												} if (topStatus.equals("2")) {
													out.println("审核未通过");
												} %>
										</td>
										<td style="text-align: center;">
											<a href="<%=basePath %>user/topic/stueditview.do?id=<%=tbtopic.getTopId() %>" title="编辑" class="btn btn-success color">
												<i class="icon-edit"></i>
											</a>
										</td>
									</tr>
									<%	} %>
									<tr>
										<td colspan="8"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
									</tr>
									<!-- Modal -->
										<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										  <div class="modal-header">
										    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										    <h3 class="myModalLabel">
										    	
										    </h3>
										  </div>
										  <div class="modal-body">
										    
										  </div>
										  <div class="modal-footer">
										    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
										  </div>
										</div>
									<%	} %>
									<c:if test="${empty topicList}">
										<tr class="tabletr">
											<td colspan="8" align="center">
												您未提交任何课题！
											</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div><!-- /widget-content -->
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