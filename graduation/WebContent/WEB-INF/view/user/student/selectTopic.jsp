<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	List<Tbtopic> topicListStudent = (List<Tbtopic>) request.getAttribute("topicList-student");
	List<Tbtopic> topicListTeacher = (List<Tbtopic>) request.getAttribute("topicList-teacher");
	List<Tbtopic> sonTopicList = (List<Tbtopic>) request.getAttribute("sonTopicList");
	Settime selectTime 	= (Settime) request.getAttribute("select-time");
 %>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
	.trClass{height: 35px;}
	.class_td{text-align: center;}
</style>

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
					<i class="icon-bookmark"></i> 选择课题					
				</h1>
				<div class="widget">										
					<div class="widget-content">
						<i class="icon-star"></i> 选择时间：
						 <%=(Verify.isEmpty(selectTime) ? "<span class=\"label label-default\">非选择课题时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + selectTime.getStartTime() + " ~~~~ " + selectTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>") %>
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>课题列表</h3>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<div class="tabbable">
							<div style="text-align:center">
								<span style="color: red;">
									<b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b>
								</span>
							</div>
							<ul class="nav nav-tabs">
						  		<li class="active">
						  			<a href="<%=basePath %>user/topic/intoSelectTopic.do#1" data-toggle="tab">个人课题</a>
						  		</li>
						 		<li>
						 			<a href="<%=basePath %>user/topic/intoSelectTopic.do#2" data-toggle="tab">教师课题</a>
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
												<td style="text-align: center;">课题编号</td>
												<td style="text-align: center;">课题名称</td>
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
												<td style="text-align: center;"><%=tbtopic_student.getTopId() %></td>
												<td style="text-align: center;">
													<a href="#" class="disabled" title="<%=tbtopic_student.getTopName()%>">
														<%=StringUtil.cutString(tbtopic_student.getTopName(), 20)%>
													</a>
												</td>
												<td class="action-td" style="text-align: center;">
													<a href="<%=basePath %>user/topic/intoWriteKnowing.do?id=<%=tbtopic_student.getTopId() %>" class="btn btn-success" title="确认选择">
														<i class="icon-ok"></i>
													</a>
												</td>
											</tr>
											<tr>
												<td colspan="5">
													<div style="float: right; margin-right: 20px;">
														<jsp:include page="../common/pagelist.jsp" flush="true" />
													</div>
												</td>
											</tr>
											<%	}
											} %>
											<%if(Verify.isEmpty(topicListStudent) || topicListStudent.size() == 0) { %>
												<tr>
													<td colspan="5" style="color: red; text-align: center;">暂无相关记录</td>
												</tr>
											<% } %>
										</tbody>
									</table>
								</div>
								<!-- /student-topic -->
								
								<!-- teacher-topic -->
								<div class="tab-pane" id="2">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>
												<td style="text-align: center;">#</td>
												<td style="text-align: center;">课题编号</td>
												<td style="text-align: center;">课题名称</td>
												<td style="text-align: center;">提交人</td>
												<td style="text-align: center;">完成人数</td>
												<td style="text-align: center;">选择</td>
											</tr>
										</thead>
										<tbody id="judge-form">
										<% 
											if(!Verify.isEmpty(topicListTeacher) && !Verify.isEmpty(selectTime)) {
												for(int i = 0; !Verify.isEmpty(topicListTeacher) && i < topicListTeacher.size(); i ++) {
													Tbtopic tbtopic_teacher = topicListTeacher.get(i);
										%>
											<tr>
												<td style="text-align: center;"><%=(i + 1) %></td>
												<td style="text-align: center;"><%=tbtopic_teacher.getTopId() %></td>
												<td style="text-align: center;">
													<%	if(tbtopic_teacher.getTopNumber() == 1){ %>
														<a href="#" class="disabled" title="<%=tbtopic_teacher.getTopName()%>">
															<%=StringUtil.cutString(tbtopic_teacher.getTopName(), 20)%>
														</a>
													<%} else {%>
														<a href="javascript:;" class="name" tag="name<%=i%>" title="<%=tbtopic_teacher.getTopName()%>"> 
															<%=StringUtil.cutString(tbtopic_teacher.getTopName(), 20)%>
														</a>
													<%} %>
												</td>
												<td style="text-align: center;">
													<a href="javascript:;" class="content" tec="content<%=i %>" title="<%=tbtopic_teacher.getTopCommitId() %>">
														<%=tbtopic_teacher.getTopCommitId() %>
													</a>
													<!-- Modal -->
													<div id="content<%=i %>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											  			<div class="modal-header">
											   				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
											   				<h3 id="myModalLabel">
											  	 				<%=(Verify.isEmpty(tbtopic_teacher.getTopName()) ? "" : tbtopic_teacher.getTopName()) %> 所采用的技术
											  				</h3>
											  			</div>
											 			<div class="modal-body">
											    			<p><%=(Verify.isEmpty(tbtopic_teacher.getTopTec()) ? "" : tbtopic_teacher.getTopTec()) %></p>
											  			</div>
											  			<div class="modal-footer">
											   				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
											  			</div>
													</div>
												</td>
												<td style="text-align: center;"><%=tbtopic_teacher.getTopNumber() %></td>
												<td class="action-td" style="text-align: center;">
													<%	if(tbtopic_teacher.getTopNumber() == 1){ %>
													 	<a href="<%=basePath %>user/topic/intoWriteKnowing.do?id=<%=tbtopic_teacher.getTopId() %>" class="btn btn-success" title="确认选择">
															<i class="icon-ok"></i>
														</a>
													<%} else {%>
														<a href="#" class="btn btn-success disabled" title="确认选择">
															<i class="icon-ok"></i>
														</a>
													<%} %>
												</td>
											</tr>
											<%
												if (!Verify.isEmpty(sonTopicList) && sonTopicList.size() > 0) {
											%>
											<tr class="sontopicname<%=i%>" style="display: none;">
												<td colspan="6">
													<table style="width: 100%;" class="table table-striped table-bordered">
														<thead>
															<tr>
																<td colspan="6" style="text-align: center; color: orange; font-weight: bold">
																	<%=tbtopic_teacher.getTopName()%>　子课题信息
																</td>
															</tr>
															<tr>
																<td colspan="1" style="text-align: center;">#</td>
																<td colspan="2" style="text-align: center;">子课题ID</td>
																<td colspan="2" style="text-align: center;">子课题名称</td>
																<td style="text-align: center;">选择</td>
															</tr>
														</thead>
														<tbody>
														<%
															int index = 0;
															for (int j = 0; !Verify.isEmpty(sonTopicList) && j < sonTopicList.size(); j++) {
																Tbtopic sontopic = sonTopicList.get(j);
																if (sontopic.getParentId().equals(tbtopic_teacher.getTopId())) {
														%>
															<tr>
																<td colspan="1" style="text-align: center;"><%=(index + 1)%></td>
																<td colspan="2" style="text-align: center;"><%=(Verify.isEmpty(sontopic.getTopId()) ? "暂无相关信息" : sontopic.getTopId())%></td>
																<td colspan="2" style="text-align: center;"><%=(Verify.isEmpty(sontopic.getTopName()) ? "暂无相关信息" : sontopic.getTopName())%></td>
																<td class="action-td" style="text-align: center;">
																	<a href="<%=basePath %>user/topic/intoWriteKnowing.do?id=<%=sontopic.getTopId() %>" class="btn btn-success" title="确认选择">
																		<i class="icon-ok"></i>
																	</a>
																</td>
															</tr>
															<%	index++;} %>
														<%	} %>
														</tbody>
													</table>
												</td>
											</tr>
											<%		} 
												}
											} %>
											<tr>
												<td colspan="6">
													<div style="float: right; margin-right: 20px;">
														<jsp:include page="../common/pagelist.jsp" flush="true" />
													</div>
												</td>
											</tr>
											<%if(Verify.isEmpty(topicListTeacher) || topicListTeacher.size() == 0) { %>
												<tr>
													<td colspan="6" style="color: red; text-align: center;">暂无相关记录</td>
												</tr>
											<% } %>
										</tbody>
									</table>
								</div>
								<!-- /teacher-topic -->
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