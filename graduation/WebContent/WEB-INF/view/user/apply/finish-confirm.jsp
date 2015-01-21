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
	List<Apply> list = (List<Apply>) request.getAttribute("list");
	Department department = (Department) request.getSession().getAttribute("dept");
	List<Tbgrade> gradelist = (List<Tbgrade>) request.getAttribute("gradeList");
	String curGrade 		= (String) request.getParameter("grade");
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
			<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
			<!-- /span3 -->
			<div class="span9">
				<h1 class="page-title">
					<i class="icon-ok-circle"></i>
					答辩确认					
				</h1>
				<!-- /stat-container -->
				<div class="widget">										
					<div class="widget-content">
						 <form class="form-search" method="post" action="<%=basePath %>user/revieworder/list.do">
						 	<label>请选择毕业届别：</label>
						 	<select name="grade" class="grade" id="cur-grade" data-cur="<%=HResponse.formatEmpty(curGrade, request) %>">
								 <option value="">-- 请选择 --</option>
								 <%
								 	if(!Verify.isEmpty(gradelist)) {
								 		for(int i = 0; i < gradelist.size(); i ++) {
								 		Tbgrade grade = gradelist.get(i);
								  %>
								 		<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
								 <% 
								 		} 
								 	} 
								 %>
							</select>
						</form>
					</div> <!-- /widget-content -->
					<div class="widget-header">
						<h3>答辩确认</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content">
						<div class="tabbable">
							<ul class="nav nav-tabs">
							  <li class="active">
							    <a href="#1" data-toggle="tab">毕业答辩确认</a>
							  </li>
							  <li><a href="<%=basePath %>user/apply/confirm.do">开题答辩确认</a></li>
							</ul>
							<br />
							<div class="tab-content">
								<div class="tab-pane active" id="1">
									<form id="edit-profile" class="form-horizontal" method="post" action="" />
										<fieldset>
											<table class="table table-striped table-bordered">
												<thead>
													<tr>
														<td>#</td>
														<td>申请人</td>
														<td>提交申请时间</td>
														<td>相关文档</td>
														<td>同意答辩</td>
													</tr>
												</thead>
												<tbody>
													<% 
														if(!Verify.isEmpty(list)) {
															for(int i = 0; !Verify.isEmpty(list) && i < list.size(); i ++) {
															Apply apply = list.get(i);
													%>
													<tr>
														<td><%=(i + 1) %></td>
														<td><%=HResponse.formatValue("userId", apply.getUserId(), request) %></td>
														<td><%=apply.getCreateTime() %></td>
														<td><button class="btn btn-info btn-finish" type="button">查看文档</button></td>
														<td><%=HResponse.formatValue("pass", apply.getPass(), request) %></td>
														<input type="hidden" name="id" value="<%=apply.getId() %>" />
														<input type="hidden" name="user_id" value="<%=apply.getUserId() %>" />
														<input type="hidden" name="type" value="2" />
													</tr>
													<% } %>
													<tr>
														<td colspan="7"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
													</tr>
													<% } if(Verify.isEmpty(list) || list.size() == 0) { %>
													<tr>
														<td colspan="7" style="color: red; text-align: center;">暂无相关记录</td>
													</tr>
													<% } %>
												</tbody>
											</table>
										</fieldset>
									</form>
									</div>
								</div>
								<!-- Modal -->
								<form action="" id="teacher-judge-form" method="post">
									<div id="myModal" style="width: 55%;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									  <div class="modal-header">
									    <h3 id="myModalLabel">指导教师评分表</h3>
									  </div>
									  <div class="modal-body">
									  	
									  </div>
									  <div class="modal-footer">
									    <button type="button" class="btn btn-primary modal-btn">确定评分</button>
									  </div>
									</div>
								</form>
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
<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>js/confirm.js"></script>
  </body>
</html>