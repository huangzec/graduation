<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbtopic> topicList = (List<Tbtopic>) request.getAttribute("list");
	List<Tbtopic> subList = (List<Tbtopic>) request.getAttribute("subList");
	List<Selectfirst> selsList = (List<Selectfirst>) request.getAttribute("selsList");
	Settime judgeTime 	= (Settime) request.getAttribute("judge-time");
	List begroupList 	= (List) request.getAttribute("begroupList");
	String curYear 		= (String) request.getParameter("year");
 %>
<jsp:include page="../common/header.jsp" flush="true" />
<link href="<%=basePath %>vendor/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" /> 
<link href="<%=basePath %>vendor/bootstrap-switch/css/highlight.css" rel="stylesheet" /> 
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
				<div class="widget">										
					<div class="widget-header">
						<i class="icon-file"></i>  <h3><a href="<%=basePath %>user/taskdoc/senddoclist.do" title="下发毕业(论文)设计任务书">下发毕业(论文)设计任务书</a> </h3>
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>课题选择情况</h3>
						<span class="">年份：
							<select name="curyear" id="cur-year" data-cur="<%=(HResponse.formatEmpty(curYear, request)) %>">
								<option value="">-- 请选择 --</option>
								<%
									 if(!Verify.isEmpty(begroupList)) {
									 	for(int i = 0; i < begroupList.size(); i ++) {
									 		if(!Verify.isEmpty(begroupList.get(i))) {
									 			
					 			%>
					 							<option value="<%=begroupList.get(i) %>">-- <%=begroupList.get(i) %> --</option>
					 			<%
					 							}
					 							
									 		}
									 	}
								 %>
							</select>
						</span>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<div class="tabbable">
							<ul class="nav nav-tabs">
							  <li class="active">
							  	<a href="<%=basePath %>user/topic/selectcase.do?topic=student">学生课题</a>
							  </li>
							  <li><a href="<%=basePath %>user/topic/selectcase.do?topic=teacher">本人课题</a></li>
							</ul>
						</div>
						<form action="<%=basePath %>judge/judgeview.do" method="post" id="judge-form">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<td>#</td>
										<td>课题编号</td>
										<td>课题名称</td>
										<td>课题内容</td>
										<td>课题完成人数</td>
									</tr>
								</thead>
								<tbody>
									<% 
										if(null != topicList && 0 < topicList.size()) {
											for(int i = 0; i < topicList.size(); i ++) {
											Tbtopic tbtopic = topicList.get(i);
									%>
									<tr>
										<td><%=(i + 1) %></td>
										<td><%=tbtopic.getTopId() %></td>
										<td>
											<a href="javascript:;" class="name" tag="name<%=i %>" title="<%=tbtopic.getTopName() %>">
												<%=StringUtil.cutString(tbtopic.getTopName(), 20) %>
											</a>
										</td>
										<td>
											<div class="hide header">
												课题 <%=(Verify.isEmpty(tbtopic.getTopName()) ? "" : tbtopic.getTopName()) %> 内容
											</div>
											<div class="hide body">
												<p><%=(Verify.isEmpty(tbtopic.getTopContent()) ? "" : tbtopic.getTopContent()) %></p>
											</div>
											<a href="javascript:;" class="content" tec="content<%=i %>" title="">
												查看
											</a>
										</td>
										<td><%=tbtopic.getTopNumber() %></td>
									</tr>
									<%
										if(tbtopic.getTopNumber() == 1) {
									 %>
									 <tr class="sontopicname<%=i %>" style="display: none;">
									 	<td colspan="5">
									 		<table style="width: 100%;">
											 	<thead>
											 		<tr>
											 			<td colspan="4" style="text-align: center;">选择课题” <%=tbtopic.getTopName() %> “的学生</td>
											 		</tr>
											 		<tr>
											 			<td>###</td>
											 			<td>学号</td>
											 			<td style="text-align: center;">对课题的认识</td>
											 			<td>确定为任务人</td>
											 		</tr>
											 	</thead>
											 	<tbody>
											 		<% 
											 			if(null != selsList && 0 < selsList.size()) {
											 			int indexsels = 1;
											 			for(int k = 0; k < selsList.size(); k++) {
											 				Selectfirst sels = selsList.get(k);
											 				if(sels.getTbtopic().getTopId().equals(tbtopic.getTopId())) {
											 		%>
											 		<tr>
											 			<td><%=(indexsels++) %></td>
											 			<td>
											 				<%=sels.getStuId() %> 【<%=HResponse.formatValue("stuId", sels.getStuId(), request) %>】
											 			</td>
											 			<td>
											 				<div class="hide header">
																学生 <%=(Verify.isEmpty(sels.getStuId()) ? "" : sels.getStuId()) %> 对课题的理解与认识
															</div>
															<div class="hide body">
																<p><%=(Verify.isEmpty(sels.getSelKnowing()) ? "" : sels.getSelKnowing()) %></p>
															</div>
											 				<a href="javascript:;" class="know" subknow="knowed<%=(i + "" + k) %>" title="<%=(Verify.isEmpty(sels.getSelKnowing()) ? "" : sels.getSelKnowing()) %>">
											 					查看
										 					</a>
											 			</td>
											 			<td>
											 				<%=HResponse.formatValue("status", sels.getSelStatus(), request) %>
															<input cur="0" class="switchbtn" type="hidden" value="1" name="status" />
															<input type="hidden" name="item" value="<%=sels.getSelId() %>" />
															<input type="hidden" name="subtopid" value="<%=tbtopic.getTopId() %>" />
															<input type="hidden" name="topictype" value="1" />
														</td>
											 		</tr>
											 		<% } } } %>
											 	</tbody>
											 </table>
									 	</td>
									 </tr>
									 <%} else { %>
									<%
										if(null != subList && 0 < subList.size()) {
																				
									 %>
									  <tr class="sontopicname<%=i %>" style="display: none;">
									 	<td colspan="5">
										 <table style="width: 100%;">
										 	<thead>
										 		<tr>
										 			<td colspan="4" style="text-align: center;">课题” <%=tbtopic.getTopName() %> “的子课题</td>
										 		</tr>
										 		<tr>
										 			<td>##</td>
										 			<td>子课题编号</td>
										 			<td style="text-align: center;">子课题名称</td>
										 		</tr>
										 	</thead>
										 	<tbody>
										 		<% 
										 			for(int j = 0; j < subList.size(); j++) {
										 				 Tbtopic tbc = subList.get(j);
										 				 if(tbc.getParentId().equals(tbtopic.getTopId())) {
										 		%>
										 		<tr class="subtop<%=(i + "" + j) %>">
										 			<td><%=(j + 1) %></td>
										 			<td><%=tbc.getTopId() %></td>
										 			<td>
										 				<a href="javascript:;" class="subsontopic" subson="subsontopic<%=(i + "" + j) %>" title="<%=(Verify.isEmpty(tbc.getTopName()) ? "" : tbc.getTopName()) %>">
										 					<%=(Verify.isEmpty(tbc.getTopName()) ? "暂无相关信息" : StringUtil.cutString(tbc.getTopName(), 70)) %>
									 					</a>
										 			</td>
										 		</tr>
										 		<%
										 			if(null != selsList && 0 < selsList.size()) {
										 		 %>
										 		 <tr class="subsontopic<%=(i + "" + j) %>" style="display: none;">
												 	<td colspan="5">
													 <table style="width: 100%;">
													 	<thead>
													 		<tr>
													 			<td colspan="4" style="text-align: center;">选择课题” <%=tbc.getTopName() %> “的学生</td>
													 		</tr>
													 		<tr>
													 			<td>###</td>
													 			<td>学号</td>
													 			<td style="text-align: center;">对课题的认识</td>
													 			<td>确定为任务人</td>
													 		</tr>
													 	</thead>
													 	<tbody>
													 		<% 
													 			int indexsels = 1;
													 			for(int k = 0; k < selsList.size(); k++) {
													 				Selectfirst sels = selsList.get(k);
													 				if(sels.getTbtopic().getTopId().equals(tbc.getTopId())) {
													 		%>
													 		<tr>
													 			<td><%=(indexsels++) %></td>
													 			<td>
													 				<%=sels.getStuId() %> 【<%=HResponse.formatValue("stuId", sels.getStuId(), request) %>】
													 			</td>
													 			<td>
														 			<div class="hide header">
																		学生 <%=(Verify.isEmpty(sels.getStuId()) ? "" : sels.getStuId()) %> 对课题的理解与认识
																	</div>
																	<div class="hide body">
																		<p><%=(Verify.isEmpty(sels.getSelKnowing()) ? "" : sels.getSelKnowing()) %></p>
																	</div>
													 				<a href="javascript:;" class="know" subknow="knowed<%=(i + "" + k) %>" title="<%=(Verify.isEmpty(sels.getSelKnowing()) ? "" : sels.getSelKnowing()) %>">
													 					查看
												 					</a>
													 			</td>
													 			<td>
													 				<%=HResponse.formatValue("status", sels.getSelStatus(), request) %>
																	<input cur="0" class="switchbtn" type="hidden" value="1" name="status" />
																	<input type="hidden" name="item" value="<%=sels.getSelId() %>" />
																	<input type="hidden" name="subtopid" value="<%=tbc.getTopId() %>" />
																	<input type="hidden" name="topictype" value="1" />
																</td>
													 		</tr>
													 		<% } } %>
													 	</tbody>
													 </table>
													 </td>
												 </tr>
										 		 <% } %>
										 		<% } } %>
										 	</tbody>
										 </table>
										 </td>
									 <% } } %>
									<% } %>
									<tr>
										<td colspan="5"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
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
									<% } if(Verify.isEmpty(topicList) || topicList.size() == 0) { %>
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
<script src="<%=basePath %>js/site-judge.js"></script>
  </body>
</html>