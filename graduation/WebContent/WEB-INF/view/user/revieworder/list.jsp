<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Revieworder> list 	= (List<Revieworder>) request.getAttribute("list");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
	Department department 	= (Department) request.getSession().getAttribute("dept");
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
					<i class="icon-zoom-in"></i>
					毕业论文评阅					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-content">
						 <form class="form-search" method="post" action="<%=basePath %>user/revieworder/list.do">
						 	<label>请选择毕业届别：</label>
						 	<select name="grade" class="grade" id="revieworder-btn" data-cur="<%=HResponse.formatEmpty(curGrade, request) %>">
								 <option value="">-- 请选择 --</option>
								 <%
								 	if(null != gradeList && 0 < gradeList.size()) {
								 		for(int i = 0; i < gradeList.size(); i ++) {
								 		Tbgrade grade = gradeList.get(i);
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
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>评阅列表</h3>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="<%=basePath %>judge/judgeview.do" method="post" id="judge-form">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<td>#</td>
										<td>学生</td>
										<td>查看文档</td>
										<td>评分</td>
									</tr>
								</thead>
								<tbody>
									<% 
										if(!Verify.isEmpty(list)) {
											for(int i = 0; !Verify.isEmpty(list) && i < list.size(); i ++) {
											Revieworder reder = list.get(i);
									%>
									<tr>
										<td>
											<%=(i + 1) %>											
											<input type="hidden" name="id" value="<%=reder.getId() %>" />
											<input type="hidden" name="studentid" value="<%=reder.getStudentId() %>" />
											<input type="hidden" name="" value="" />
										</td>
										<td>
											<%=(reder.getStudentId() + " 【" + HResponse.formatValue("stuId", reder.getStudentId(), request) + "】") %>
										</td>
										<td>
											<button class="btn btn-info review-look-btn" type="button">查看文档</button>
										</td>
										<td>
											<%
												if(reder.getStatus().equals("2")) {
													%>
													<i class="icon-ok"></i><span style="color: red;">已评</span>
													<%
												} else {
											 %>
											<button class="btn btn-success review-judge-btn" type="button">评分</button>
											<% } %>
										</td>
									</tr>
									<% } %>
									<tr>
										<td colspan="4"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
									</tr>
									<% } if(Verify.isEmpty(list) || list.size() == 0) { %>
									<tr>
										<td colspan="4" style="color: red; text-align: center;">暂无相关记录</td>
									</tr>
									<% } %>
								</tbody>
							</table>
						</form>
						<!-- Modal -->
						<form action="" id="review-judge-form" method="post">
							<div id="myModal" style="width: 55%;" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-header">
							    <h3 id="myModalLabel">评阅教师评分表</h3>
							  </div>
							  <input type="hidden" name="modal-student" />
							  <input type="hidden" name="modal-id" />
							  <div class="modal-body">
							  	
							  </div>
							  <div class="modal-footer">
							    <button type="button" class="btn btn-primary review-judge-teacher-btn">确定评分</button>
							  </div>
							</div>
						</form>
					</div> <!-- /widget-content -->					
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
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>js/site-judge.js"></script>
  </body>
</html>