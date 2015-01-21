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
					<%if(!Verify.isEmpty(transfertime)){ %>
						<div class="widget widget-table">
							<div class="widget-header">
								<i class="icon-th-list"></i>
								<h3>课题列表</h3>
								<span style="float: right; margin-right: 10px; color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message"))%></b>
								</span>
							</div>
							<!-- /widget-header -->
							<div class="widget-content">
								<table class="table table-striped table-bordered">
									<thead>
										<tr class="tabletr">
											<td style="text-align: center;">#</td>
											<td style="text-align: center;">名称</td>
											<td style="text-align: center;">内容</td>
											<td style="text-align: center;">来源</td>
											<td style="text-align: center;">类型</td>
											<td style="text-align: center;">关键字</td>
											<td style="text-align: center;">选　择</td>
										</tr>
									</thead>
									<tbody id="judge-form">
										<%
											if (!Verify.isEmpty(topicList) && !Verify.isEmpty(transfertime)) {
												for (int i = 0; i < topicList.size(); i++) {
													Tbtopic topic = topicList.get(i);
										%>
											<tr>
												<td style="text-align: center;"><%=(i + 1)%></td>
												<td style="text-align: center;"><%=topic.getTopName()%></td>
												<td style="text-align: center;">
													<div class="hide header">
														<%=(Verify.isEmpty(topic.getTopName()) ? "" : topic.getTopName())%>　内容
													</div>
													<div class="hide body">
														<p><%=(Verify.isEmpty(topic.getTopContent()) ? "" : topic.getTopContent())%></p>
													</div>
													<a href="javascript:;" class="know" subknow="knowed<%=(i) %>" title="<%=Verify.isEmpty(StringUtil.cutString(topic.getTopContent(), 50)) ? "" : StringUtil.cutString(topic.getTopContent(), 50) %>">
														查看
										 			</a>
										 		</td>
										 		<td style="text-align: center;"><%=HResponse.formatValue("topsource", topic.getTopSource(), request) %></td>
										 		<td style="text-align: center;"><%=HResponse.formatValue("toptype", topic.getTopType(), request) %></td>
										 		<td style="text-align: center;"><%=topic.getTopKeywords() %></td>
												<td class="action-td" style="text-align: center;">
													<a href="<%=basePath %>user/topic/confirmtransfer.do?id=<%=topic.getTopId() %>&userid=<%=request.getParameter("id") %>" class="btn btn-success" title="确认选择吗？">
														<i class="icon-ok"></i>
													</a>
												</td>
											</tr>
										<%	} %>
											<tr>
												<td colspan="7"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
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
										<%	}else{ %>
											<tr class="tabletr">
												<td colspan="7" style="text-align: center; color: red;">
													当前无课题可供操作！
												</td>
											</tr>
										<%	} %>
									</tbody>
								</table>
							</div><!-- /widget-content -->
						</div>
					<%	} %>
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