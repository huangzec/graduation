<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbtopic> topicList = (List<Tbtopic>) request.getAttribute("list");
	List<Tbtopic> subList 	= (List<Tbtopic>) request.getAttribute("subList");
	Settime judgeTime 	= (Settime) request.getAttribute("judge-time");
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
					<i class="icon-eye-open"></i>
					评审课题					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-content">
						<i class="icon-eye-open"></i>  课题评审 <i class="icon-star"></i> 
						 评审时间：
						 <%=(Verify.isEmpty(judgeTime) ? "<span class=\"label label-default\">非评审时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + judgeTime.getStartTime() + " ~~~~ " + judgeTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>") %>
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>课题表</h3>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="<%=basePath %>judge/judgeview.do" method="post" id="judge-form">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<td>#</td>
										<td>课题编号</td>
										<td>课题名称</td>
										<td>课题内容</td>
										<td>课题完成人数</td>
										<td>评审</td>
									</tr>
								</thead>
								<tbody id="judge-box">
									<% 
										if(!Verify.isEmpty(topicList) && !Verify.isEmpty(judgeTime)) {
											for(int i = 0; !Verify.isEmpty(topicList) && i < topicList.size(); i ++) {
											Tbtopic tbtopic = topicList.get(i);
									%>
									<tr class="record" data-cur="1">
										<td><%=(i + 1) %></td>
										<td><%=tbtopic.getTopId() %></td>
										<td>
											<%
												if(tbtopic.getTopNumber() == 1) {
											 %>
												<%=StringUtil.cutString(tbtopic.getTopName(), 20) %>
											<% } else { %>
												<a href="javascript:;" class="name" tag="name<%=i %>" title="<%=tbtopic.getTopName() %>">
													<%=StringUtil.cutString(tbtopic.getTopName(), 20) %>
												</a>
											<% } %>
										</td>
										<td>
								 			<div class="hide header">
												课题 <%=(Verify.isEmpty(tbtopic.getTopName()) ? "" : tbtopic.getTopName()) %> 内容
											</div>
											<div class="hide body">
												<p><%=(Verify.isEmpty(tbtopic.getTopContent()) ? "" : tbtopic.getTopContent()) %></p>
											</div>
											<a href="javascript:;" class="content" tec="content<%=i %>" title="">
												查看详细
											</a>
										</td>
										<td><%=tbtopic.getTopNumber() %></td>
										<td class="action-td is-judged" data-cur="<%=tbtopic.getTopId() %>">
											<a href="<%=basePath %>judge/pass.do?id=<%=tbtopic.getTopId() %>" class="btn btn-small btn-warning pass" title="评审通过">
												<i class="icon-ok"></i>								
											</a>					
											<a href="<%=basePath %>judge/unpass.do?id=<%=tbtopic.getTopId() %>" class="btn btn-small repass" title="评审不通过">
												<i class="icon-remove"></i>						
											</a>
										</td>
									</tr>
									<%
										if(null != subList && 0 < subList.size()) {
									 %>
									<tr class="sontopicname<%=i %>" style="display: none;">
									 	<td colspan="7">
										 <table style="width: 100%;">
										 	<thead>
										 		<tr>
										 			<td colspan="7" style="text-align: center;"><%=tbtopic.getTopName() %>  子课题信息</td>
										 		</tr>
										 		<tr>
										 			<td colspan="2">#</td>
										 			<td colspan="5" style="text-align: center;">子课题名称</td>
										 		</tr>
										 	</thead>
										 	<tbody>
										 		<% 
										 			for(int j = 0; j < subList.size(); j++ ) {
										 				Tbtopic subTopic = subList.get(j);
										 				if(subTopic.getParentId().equals(tbtopic.getTopId())) {
										 		%>
										 		<tr>
										 			<td colspan="2"><%=(j + 1) %></td>
										 			<td colspan="5" style="text-align: center;"><%=(Verify.isEmpty(subTopic.getTopName()) ? "暂无相关信息" : subTopic.getTopName()) %></td>
										 		</tr>
										 		<% } } %>
										 	</tbody>
										 </table>
										 </td>
									 </tr>
									 <% } %>
									<% } %>
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
									<% } if(Verify.isEmpty(topicList) || topicList.size() == 0) { %>
									<tr  class="record" data-cur="0">
										<td colspan="7" style="color: red; text-align: center;">暂无相关记录</td>
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
<script src="<%=basePath %>js/site-judge.js"></script>
  </body>
</html>