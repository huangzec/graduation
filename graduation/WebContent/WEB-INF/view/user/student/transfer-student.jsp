<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	List<Student> studentList = (List<Student>) request.getAttribute("student-list");
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
								<h3>学生列表</h3>
								<span style="float: right; margin-right: 10px; color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message"))%></b>
								</span>
							</div>
							<!-- /widget-header -->
							<div class="widget-content">
								<table class="table table-striped table-bordered">
									<thead>
										<tr class="tabletr">
											<td style="text-align: center;">#</td>
											<td style="text-align: center;">学　号</td>
											<td style="text-align: center;">姓　名</td>
											<td style="text-align: center;">邮　箱</td>
											<td style="text-align: center;">选　择</td>
										</tr>
									</thead>
									<tbody id="judge-form">
										<%
											if (!Verify.isEmpty(studentList) && !Verify.isEmpty(transfertime)) {
												for (int i = 0; !Verify.isEmpty(studentList) && i < studentList.size(); i++) {
													Student student = studentList.get(i);
										%>
											<tr>
												<td style="text-align: center;"><%=(i + 1)%></td>
												<td style="text-align: center;"><%=student.getStuId()%></td>
												<td style="text-align: center;"><%=student.getStuName()%></td>
												<td style="text-align: center;"><%=student.getStuEmail() %></td>
												<td class="action-td" style="text-align: center;">
													<a href="<%=basePath %>user/topic/transtopic.do?id=<%=student.getStuId() %>" class="btn btn-success" title="确认选择">
														<i class="icon-ok"></i>
													</a>
												</td>
											</tr>
										<%	} %>
											<tr>
												<td colspan="5"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
											</tr>
										<%	}else{ %>
											<tr class="tabletr">
												<td colspan="5" style="text-align: center; color: red;">
													当前无学生可供操作！
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