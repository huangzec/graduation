<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../common/header.jsp" flush="true" />
<link href="<%=basePath %>vendor/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" /> 
<link href="<%=basePath %>vendor/bootstrap-switch/css/highlight.css" rel="stylesheet" /> 
<%
	List<Selectfirst> list = (List<Selectfirst>) request.getAttribute("list");
	List<Tbgrade> gradelist = (List<Tbgrade>) request.getAttribute("gradeList");
	String curGrade 		= (String) request.getParameter("grade");
 %>
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
				<!-- /stat-container -->
				<h1 class="page-title">
					<i class="icon-file"></i> 
					下发毕业(论文)设计任务书					
				</h1>
				<!-- /stat-container -->
				<div class="widget">										
					<div class="widget-content">
						 <form class="form-search" method="post" action="<%=basePath %>">
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
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>课题最终选择情况</h3>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="<%=basePath %>judge/judgeview.do" method="post" id="task-form">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<td>#</td>
										<td>课题编号</td>
										<td>课题名称</td>
										<td>任务人</td>
										<td>下发任务书</td>
									</tr>
								</thead>
								<tbody>
									<% 
										if(!Verify.isEmpty(list)) {
											for(int i = 0; i < list.size(); i ++) {
											Selectfirst selectfirst = list.get(i);
									%>
									<tr class="record" cur="1" data-cur="<%=selectfirst.getStuId() %>">
										<td><%=(i + 1) %></td>
										<td><%=selectfirst.getTbtopic().getTopId() %></td>
										<td>
											<a href="javascript:;" class="name" tag="name<%=i %>" title="<%=selectfirst.getTbtopic().getTopName() %>">
												<%=StringUtil.cutString(selectfirst.getTbtopic().getTopName(), 20) %>
											</a>
										</td>
										<td>
											<a href="javascript:;" class="content" tec="content<%=i %>" title="<%=(selectfirst.getStuId() + '【' + HResponse.formatValue("stuId", selectfirst.getStuId(), request) + '】') %>">
												<%=StringUtil.cutString(HResponse.formatValue("stuId", selectfirst.getStuId(), request), 20) %>
											</a>
										</td>
										<td style="text-align: center;" class="is-send" data-cur="<%=selectfirst.getStuId() %>" topic-cur="<%=selectfirst.getTbtopic().getTopId() %>">
											<button task="btn<%=i %>" class="btn btn-info senddocbtn" type="button">下发任务书</button>
										</td>
										<input type="hidden" class="topicidbtn<%=i %>" value="<%=selectfirst.getTbtopic().getTopId() %>" />
										<input type="hidden" class="taskeridbtn<%=i %>" value="<%=selectfirst.getStuId() %>" />
									</tr>
									<% } %>
									<tr>
										<td colspan="5"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
									</tr>
									<% } if(Verify.isEmpty(list) || list.size() == 0) { %>
									<tr>
										<td colspan="5" style="color: red; text-align: center;">暂无相关记录</td>
									</tr>
									<% } %>
								</tbody>
							</table>
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
<script src="<%=basePath %>js/taskdoc.js"></script>
  </body>
</html>