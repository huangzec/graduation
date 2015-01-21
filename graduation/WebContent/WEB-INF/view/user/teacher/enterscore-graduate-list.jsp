<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Graduateinfo> list = (List<Graduateinfo>) request.getAttribute("list");
	Department department 	= (Department) request.getSession().getAttribute("dept");
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
					<i class="icon-pencil"></i>
					录入答辩成绩					
				</h1>				
				<!-- /stat-container -->				
				
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>录入毕业答辩成绩</h3>
						<span style="float: right;margin-right: 10px;color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<td>答辩序号</td>
									<td>学生</td>
									<td>答辩时间</td>
									<td>答辩地点</td>
									<td width="20%">录入成绩</td>
								</tr>
							</thead>
							<tbody>
								<% 
									if(null != list && 0 < list.size()) {
										for(int i = 0; i < list.size(); i ++) {
										Graduateinfo grad = list.get(i);
								%>
								<tr>
									<td><%=grad.getGdiNumber() %></td>
									<td>
										<%=grad.getStuId() + " 【" + HResponse.formatValue("stuId", grad.getStuId(), request) + "】" %>
									</td>
									<td>
										<%=grad.getGdiDate() %>
									</td>
									<td><%=grad.getGdiPlace() %></td>
									<td class="action-td">
										<button class="btn btn-info enter-graduate-score-btn" type="button">录入成绩</button>
										<button class="btn btn-warning graduate-unpass-btn" type="button">不通过</button>
									</td>
									<input type="hidden" name="student" value="<%=grad.getStuId() + " 【" + HResponse.formatValue("stuId", grad.getStuId(), request) + "】" %>" />
									<input type="hidden" name="studentid" value="<%=grad.getStuId() %>" />
									<input type="hidden" name="starttime" value="<%=grad.getGdiDate() %>" />
									<input type="hidden" name="place" value="<%=grad.getGdiPlace() %>" />
									<input type="hidden" name="person" value="<%=grad.getJudge() %>" />
									<input type="hidden" name="id" value="<%=grad.getGdiId() %>" />
								</tr>
								<% 
										}
									} 
									if(null == list || 1 > list.size()) { 
								%>
								<tr>
									<td colspan="5" style="color: red; text-align: center;">暂无相关记录</td>
								</tr>
								<% } %>
							</tbody>
						</table>
						<!-- Modal -->
						<div id="" style="width: 55%;"  class="modal hide fade enter-graduate-score" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-header">
						    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						    <h3 id="myModalLabel">
						    	录入学生<tag></tag>毕业答辩成绩
						    </h3>
						  </div>
						  <div class="modal-body">
							  <div>
							  	<label>答辩中提出的主要问题及回答的简要情况记录：</label>
							  	<textarea rows="10" name="content" style="width: 100%;"></textarea>
							  </div>
							  <div>
							  	会议主持人：<input type="text" name="hoster" class="" placeholder="会议主持人">
							  </div>
							  <div>
							  	会议记录人：<input type="text" name="recorder" class="" placeholder="会议记录人">
							  </div>
							  <hr />
							  <div id="modal-table">
							  	
							  </div>
							  <hr />
							  <div>
							  	<label>答辩小组意见：</label>
							  	<textarea rows="5" name="judge-view" style="width: 100%;"></textarea>
							  </div>
						  </div>
						  <div class="modal-footer">
						    <button class="btn btn-primary enter-graduate-score-modal-btn">确认提交</button>
						  </div>
						</div>
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
<script src="<%=basePath %>js/confirm.js"></script>
  </body>
</html>