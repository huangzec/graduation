<%@page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	List<Tballdoc> doclist = (List<Tballdoc>) request.getAttribute("doclist");
	List<Tbdocnum> numlist = (List<Tbdocnum>) request.getAttribute("numlist");
	List<Tbgrade> list     = (List<Tbgrade>) request.getAttribute("gradeList");
	String      year       = (String) request.getAttribute("grade");
	Department department  = (Department) request.getSession().getAttribute("dept");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
	.input_required {height: 28px;}
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
				<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-indent-right"></i>材料审查
					</h1>
					<!-- /stat-container -->
					<div class="widget">										
						<div class="widget-content">
							<form class="form-search" method="post">
						 		<label>请选择毕业届别：</label>
						 		<select name="grade" class="grade" id="cur-grade" data-cur="<%=HResponse.formatEmpty(year, request) %>">
									<option value="">-- 请选择 --</option>
								 	<%
								 		if(null != list && 0 < list.size()) {
								 			for(int i = 0; i < list.size(); i ++) {
								 			Tbgrade grade = list.get(i);
								 	%>
								 			<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
								 	<% 
								 			} 
								 		} 
								 	%>
								</select>
							</form>
						</div> <!-- /widget-content -->					
					</div> <!-- /widget -->
					<%if(!Verify.isEmpty(request.getAttribute("message"))){ %>
						<div class="alert alert-error alert-block">
							<button class="close" data-dismiss="alert">×</button>
							<strong><div style="text-align: center;"><%=request.getAttribute("message") %></div></strong>
						</div>
					<%} %>
					<div class="widget">
						<div class="widget-header">
							<i class="icon-th-list"></i>
							<h3 style="margin-right: 2px;">材料列表</h3>
						</div>
						<!-- /widget-header -->
						<div class="widget-content">
							<div class="tabbable">
								<div class="tab-content">
									<%	if(!Verify.isEmpty(numlist)){ %>
										<table class="table table-bordered">
											<thead>
												<tr class="tabletr">
													<td>#</td>
													<td>学生</td>
													<td>查看材料</td>
													<td>状态</td>
												</tr>
											</thead>
											<tbody id="judge-form">
											<%for(int i = 0; i < numlist.size(); i++){
												Tbdocnum docnum = numlist.get(i);
											 %>
									 			<tr class="tabletr">
									 				<td><%=i+1 %></td>
									 				<td>
									 					<%=(docnum.getStuId() + "【" + HResponse.formatValue("stuname", docnum.getStuId(), request) + "】") %>
								 					</td>
									 				<td>
									 					<a href="javascript:;" class="name" tag="name<%=i%>" title="<%=HResponse.formatValue("stuname", docnum.getStuId(), request) %>的材料">
									 						<%=HResponse.formatValue("stuname", docnum.getStuId(), request) %>的材料
									 					</a>
									 				</td>
									 				<td>
									 					<% if(!docnum.getNumstate().equals("1") && !docnum.getNumstate().equals("2")){ %>
									 						<a type="button" class="btn btn-success check-pass">合格</a>
									 						<a href="#myModal" role="button" data-toggle="modal" class="btn btn-danger">返回修改</a>
									 						<!-- modal -->
									 						<div id="myModal" class="modal hide fade modal-info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										  						<div class="modal-header">
										    						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										    						<h3 class="myModalLabel">学生<%=HResponse.formatValue("stuname", docnum.getStuId(), request) %>材料审核修改意见</h3>
										 						</div>
										  						<div class="modal-body">
										  							<input type="hidden" value="<%=docnum.getStuId() %>" id="stuId" />
										  							<textarea style="width: 400px; height: 200px;" id="editinfo"></textarea>
										  							<div>
										  								<button type="button" class="btn btn-danger rejoin-info-btn" style="float: right; margin-right: 60px;">确定并提交</button>
										  							</div>
										  						</div>
										  						<div class="modal-footer">
										    						<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
										  						</div>
															</div>
															
									 					<%	}else if(docnum.getNumstate().equals("1")) out.println("合格");
									 						else if(docnum.getNumstate().equals("2")) out.println("返回修改");
									 					 %>
									 				</td>
									 			</tr>
									 			<%
													if (!Verify.isEmpty(doclist) && doclist.size() > 0) {
												%>
												<tr class="sontopicname<%=i%>" style="display: none;">
													<td colspan="4" style="padding-top: 10px;">
														<table style="width: 100%;" class="table table-striped table-bordered">
															<thead>
																<tr>
																	<td colspan="4" style="text-align: center; color: orange; font-weight: bold">
																		学生 <%=HResponse.formatValue("stuname", docnum.getStuId(), request) %> 的材料
																	</td>
																</tr>
																<tr class="tabletr">
																	<td>#</td>
																	<td>标题</td>
																	<td>内容</td>
																	<td>提交时间</td>
																</tr>
															</thead>
															<tbody>
															<%
																int index = 0;
																for (int j = 0; j < doclist.size(); j++) {
																	Tballdoc alldoc = doclist.get(j);
																	if(alldoc.getStuId().equals(docnum.getStuId())) { 
															%>
																<tr class="tabletr">
																	<td><%=index+1 %></td>
									 								<td><%=alldoc.getDocTitle() %></td>
									 								<td><a href="<%=basePath %><%=alldoc.getFilePath() %>"><%=alldoc.getDocContent() %></a></td>
									 								<td><%=alldoc.getCreateTime() %></td>
																</tr>
																<%	index++;} %>
																<%	} %>
															</tbody>
														</table>
													</td>
												</tr>
													<% } %>
									 			<%} %>
									 			<tr>
													<td colspan="9"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
												</tr>
											</tbody>
										</table>
									<%	} %>
								</div>
							</div>
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
				</div>
				<!-- /span9 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /span9 -->
	</div>
	<!-- /row -->
	</div>
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
	<script src="<%=basePath %>js/rejoin.js"></script>
	<script src="<%=basePath %>js/site-judge.js"></script>
</body>
</html>