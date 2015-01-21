<%@page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	List<Tballdoc> list     = (List<Tballdoc>) request.getAttribute("list");
	Student student         = (Student) request.getAttribute("student");
	Department department   = (Department) request.getSession().getAttribute("dept");
	Tbdocnum docnum         = (Tbdocnum) request.getAttribute("docnum");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
	.input_required {height: 28px;}
</style>
<script type="text/javascript">
	function display(){
		$("#addfile").show();
	}
</script>

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
				<jsp:include page="../common/navmenu.jsp" flush="true" />
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-home"></i>答辩信息
					</h1>
					<!-- /stat-container -->
					<div class="widget">
						<div class="widget-header">
							<i class="icon-folder-open"></i>
							<h3 style="margin-right: 2px;">答辩材料</h3>
							<i class="icon-arrow-right"></i>
							<h3>所有材料</h3>
							<a href="<%=basePath%>user/rejoin/document.do" class="btn btn-type f-right">返回</a>
							<% if(!docnum.getNumstate().equals("1")){ %>
								<a href="#" class="btn btn-primary f-right" onclick="display()" style="margin-top: 6px; margin-right: 10px;">继续上传</a>
							<% } %>
						</div>
						<!-- /widget-header -->
						<div class="widget-content">
							<%if(!Verify.isEmpty(request.getAttribute("OK"))){ %>
							<div class="tabbable">
								<div style="text-align: center">
									<span style="color: red; size: 13px;"><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message"))%></span>
								</div>
								<br />
								<div class="tab-content">
									<%	String msg = (String) request.getAttribute("msg");
										if((!Verify.isEmpty(msg) && msg.equals("selectfile")) || Verify.isEmpty(list)){ if(!Verify.isEmpty(msg))System.out.println("++++++++++++"+msg);%>
										<span style="color: red; size: 13px;">文件名过长或未选择文件</span>
										<form action='<%=basePath%>user/rejoin/alldoc.do' method='post' enctype='multipart/form-data'>
      										<div>
      											<input type='file' name='filepath' style='margin-top: 8px; height: 30px; width: 300px;'>
												<button class='btn btn-primary'>submit</button>
											</div>
										</form>
									<%	} %>
									<div class="tab-pane active" id="addfile" style="display: none;">
										<form action='<%=basePath%>user/rejoin/alldoc.do' method='post' enctype='multipart/form-data'>
      										<div>
      											<input type='file' name='filepath' style='margin-top: 8px; height: 30px; width: 300px;'>
												<button class='btn btn-primary'>submit</button>
											</div>
										</form>
										
										<!--<pl></pl>
										<button class="btn btn-success" style="margin-left: 20px;">添加更多</button>-->
									</div>
									<%	if(!Verify.isEmpty(list)){ %>
										<table class="table table-bordered">
											<thead>
												<tr class="tabletr">
													<td>#</td>
													<td>标题</td>
													<td>内容</td>
													<td>状态</td>
													<td>上传时间</td>
													<% if(!Verify.isEmpty(docnum.getNumstate()) && docnum.getNumstate().equals("2")){ %>
									 					<td>操作</td>
									 				<% } %>
												</tr>
											</thead>
											<tbody>
											<%for(int i = 0; i < list.size(); i++){
												Tballdoc tballdoc = list.get(i);
											 %>
									 			<tr class="tabletr">
									 				<td><%=i+1 %></td>
									 				<td><%=Verify.isEmpty(tballdoc.getDocTitle()) ? "" : tballdoc.getDocTitle() %></td>
									 				<td><a href="<%=basePath %><%=tballdoc.getFilePath() %>"><%=tballdoc.getDocContent() %></a></td>
									 				<td style="color: red;"><%=Verify.isEmpty(HResponse.formatValue("docstatus", docnum.getNumstate(), request)) ? "" : HResponse.formatValue("docstatus", docnum.getNumstate(), request) %></td>
									 				<td><%=Verify.isEmpty(tballdoc.getCreateTime()) ? "" : tballdoc.getCreateTime() %></td>
									 				
									 				<% if(!Verify.isEmpty(docnum.getNumstate()) && docnum.getNumstate().equals("2")){ %>
									 					<td><a href="#myModal" role="button" class="btn btn-info" data-toggle="modal"><i class="icon-edit"></i></a></td>
									 					
									 					<!-- Modal -->
									 					<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										  					<div class="modal-header">
										    					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										    					<h3 class="myModalLabel">资料编辑——<%=tballdoc.getDocTitle() %></h3>
										 					</div>
										  					<div class="modal-body">
										  						<form action="<%=basePath %>user/rejoin/editdoc.do" method="post" enctype="multipart/form-data">
										  							<input type="hidden" name="docId" value="<%=tballdoc.getId() %>" />
										  							<input type="hidden" name="numId" value="<%=docnum.getId() %>" />
										  							<input type="text" value="<%=tballdoc.getDocContent() %>" />
										  							<input type="file" name="newfile" onchange="this.previousSibling.value=this.value;" style="margin-top: 8px; height: 30px; width: 300px;">
																	<button type="submit" class="btn btn-primary">submit</button>
										  						</form>
										  					</div>
										  					<div class="modal-footer">
										    					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
										  					</div>
														</div>
									 				<% } %>
									 			</tr>
									 		<%} %>
											</tbody>
										</table>
									<%	} %>
								</div>
							</div>
							<%	}else{
									out.println("<div style='text-align: center; color: red;'>当前不可进行该操作！</div>");
								}
							%>
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
</body>
</html>