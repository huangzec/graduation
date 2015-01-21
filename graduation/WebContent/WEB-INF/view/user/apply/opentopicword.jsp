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
	List<Selectfirst> list = (List<Selectfirst>) request.getAttribute("list");
	Tbtopic tbtopic = (Tbtopic) request.getAttribute("topic");
	Teacher teacher = (Teacher) request.getAttribute("teacher");
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Taskdoc taskdoc 	= (Taskdoc) request.getAttribute("taskdoc");
	Topicapply apply 	= (Topicapply) request.getAttribute("topicapply");
	Topicreport report 	= (Topicreport) request.getAttribute("topicreport");
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
					<i class="icon-folder-open"></i>
					相关文档					
				</h1>
				<!-- /stat-container -->
				<div class="widget">
					<div class="widget-header">
						<h3>学生 <%=request.getParameter("userid") + " 【" + HResponse.formatValue("stuId", request.getParameter("userid"), request) + "】" %>开题答辩相关文档</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content">
						<form id="" class="form-horizontal" method="post" action="<%=basePath %>user/apply/enterteacherview.do" />
							<div class="widget">										
								<div class="widget-header">
									<i class="icon-info-sign"></i><h3>开题答辩申请表</h3>
								</div> <!-- /widget-content -->					
							</div> <!-- /widget -->
							<input type="hidden" name="applyid" value="<%=Verify.isEmpty(apply) ? "" : apply.getId() %>" />
							<input type="hidden" name="reportid" value="<%=Verify.isEmpty(report) ? "" : report.getId() %>" />
							<div class="widget widget-table">										
								<div class="widget-content" style="text-align: center;font-size: 20px;font-weight: bold;">
									<div class="line-height">怀化学院<%=department.getDeptName() %></div>
									<div class="line-height">
										<b>
											毕业<%
												if(!Verify.isEmpty(tbtopic)) {
													if(tbtopic.getTopType().equals("1")) {
														out.print("论文");
													}else{
														out.print("设计");
													}
												}else {
													out.print("论文");
												}
											 %>开题答辩申请表
										</b>
									</div>
									<div class="text-right text-font">
										申请时间：
										<%=HResponse.formatDateTime(Verify.isEmpty(apply) ? "" : apply.getCreateTime()) %>
									</div>
								</div> <!-- /widget-header -->					
								<div class="widget-content">
									<table class="table  table-bordered">
										<tr>
											<td>系部</td>
											<td colspan="2"><%=department.getDeptName() %></td>
											<td>专业</td>
											<td colspan="2">
												<%=HResponse.formatEmpty(Verify.isEmpty(stuMap) ? "" : stuMap.get("pro_Name"), request) %>
											</td>
										</tr>
										<tr>
											<td>学生姓名</td>
											<td>
												<%=HResponse.formatValue("stuId", request.getParameter("userid"), request) %>
											</td>
											<td>学号</td>
											<td><%=request.getParameter("userid") %></td>
											<td>指导老师</td>
											<td><%=Verify.isEmpty(teacher) ? "" : teacher.getTeaName() %></td>
										</tr>
										<tr>
											<td>课题名称</td>
											<td colspan="5">
												<%=(Verify.isEmpty(tbtopic) ? "" : tbtopic.getTopName()) %>
											</td>
										</tr>
										<tr>
											<td colspan="2">申请开题答辩时间</td>
											<td colspan="4">
												<%=HResponse.formatDateTime(Verify.isEmpty(apply) ? "" : apply.getOpenTime()) %>
											</td>
										</tr>
										<tr>
											<td colspan="6">
												<div>
													对课题的理解：
													<%=StringUtil.decodeHtml(Verify.isEmpty(apply) ? "" : apply.getKnowing()) %>
												</div>				
											</td>
										</tr>
										<tr>
											<td colspan="6">
												<div>
													指导老师意见:
													<script id="teacher-view" name="teacher-view" type="text/plain">
														<%=StringUtil.decodeHtml(Verify.isEmpty(apply) ? "" : apply.getTeacherIdea()) %>
													</script>
												</div>								
											</td>
										</tr>
										<tr>
											<td colspan="6">
												<div>
													论文管理委员会意见：
													<%=StringUtil.decodeHtml(Verify.isEmpty(apply) ? "" : apply.getPaperIdea()) %>
												</div>		
											</td>
										</tr>
										<tr>
											<td colspan="6">
												<div>
													系部意见（主管教学主任）：
													<%=StringUtil.decodeHtml(Verify.isEmpty(apply) ? "" : apply.getDepartmentIdea()) %>
												</div>		
											</td>
										</tr>
									</table>
								</div> <!-- /widget-content -->	
								<div class="text-right" style="margin-top: 5px; margin-right: 150px;">
									<%=department.getDeptName() %><br/>
									<%=HResponse.formatDateTime(Verify.isEmpty(apply) ? "" : apply.getCreateTime()) %>
								</div>
							</div>
							<hr/>
							<div>
								<div class="widget">										
									<div class="widget-header">
										<i class="icon-info-sign"></i><h3>开题报告书</h3>
									</div> <!-- /widget-content -->					
								</div> <!-- /widget -->
								<div class="widget widget-table">										
									<div class="widget-content" style="text-align: center;">
										<br/>
										<div class="line-height">
											<img alt="" src="<%=basePath %>images/hhxylogo.jpg">
										</div>
										<div class="line-height">
											<h2>
												本科生毕业<%
															if(!Verify.isEmpty(tbtopic)) {
																if(tbtopic.getTopType().equals("1")) {
																	out.print("论文");
																}else{
																	out.print("设计");
																}
															}else {
																out.print("论文");
															}
														 %>
												<br/>
												开 题 报 告 书
											</h2>
										</div>
										<div class="left-100">
											<fieldset>
												<div class="control-group height-60">											
													<label class="control-label">题　　目</label>
													<div class="controls">
														<label class="control-label" style="width: 270px;">
															<%=(Verify.isEmpty(tbtopic) ? "" : tbtopic.getTopName()) %>
														</label>
													</div>			
												</div>
												<div class="control-group height-60">											
													<label class="control-label">学生姓名</label>
													<div class="controls">
														<label class="control-label">
															<%=HResponse.formatValue("stuId", request.getParameter("userid"), request) %>
														</label>
													</div>			
												</div>
												<div class="control-group height-60">											
													<label class="control-label">学　　号</label>
													<div class="controls">
														<label class="control-label"><%=request.getParameter("userid") %></label>														
													</div>			
												</div>
												<div class="control-group height-60">											
													<label class="control-label">系　　别</label>
													<div class="controls">
														<label class="control-label">
															<%=department.getDeptName() %>
														</label>
													</div>			
												</div>
												<div class="control-group height-60">											
													<label class="control-label">专　　业 </label>
													<div class="controls">
														<label class="control-label">
															<%=HResponse.formatEmpty(Verify.isEmpty(stuMap) ? "" : stuMap.get("pro_Name"), request) %>
														</label>
													</div>				
												</div>
												<div class="control-group height-60">											
													<label class="control-label">指导教师</label>
													<div class="controls">
														<label class="control-label">
															<%=Verify.isEmpty(teacher) ? "" : teacher.getTeaName() %>
														</label>
													</div>			
												</div>
												<div class="control-group height-60">											
													<label class="controls" style="color: red;">${message }</label>
												</div>
												<div class="">											
													<label class=""><%=HResponse.formatDateTime(Verify.isEmpty(report) ? "" : report.getCreateTime()) %></label>
												</div>
											</fieldset>
										</div>
										<hr/>
										<div class="widget-content">
											<form action="" method="post" id="taskdoc-form">
												<input type="hidden" name="stuid" value="" />
												<input type="hidden" name="teaid" value="" />
												<table class="table  table-bordered">
													<tr>
														<td width="15%">
															<%
																if(!Verify.isEmpty(tbtopic)) {
																	if(tbtopic.getTopType().equals("1")) {
																		out.print("论文");
																	}else{
																		out.print("设计");
																	}
																}else {
																	out.print("论文");
																}
															 %>题目
														</td>
														<td sytle="" width="85%"><%=(Verify.isEmpty(tbtopic) ? "" : tbtopic.getTopName()) %></td>
														
													</tr>
													<tr>
														<td colspan="2">
															<div>
																一、选题的目的、意义及相关研究动态和自己的见解：
																<%=StringUtil.decodeHtml(Verify.isEmpty(report) ? "" : report.getMeaning()) %>
															</div>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<div>
																二、课题的主要内容：
																<%=StringUtil.decodeHtml(Verify.isEmpty(report) ? "" : report.getContent()) %>
															</div>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<div>
																三、研究方法、设计方案或论文撰写提纲：
																<%=StringUtil.decodeHtml(Verify.isEmpty(report) ? "" : report.getResearch()) %>
															</div>
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<div>
																四、完成期限和预期进度：
																<%=StringUtil.decodeHtml(Verify.isEmpty(report) ? "" : report.getDeadline()) %>
															</div>				
														</td>
													</tr>
													<tr>
														<td colspan="6">
															<div>
																五、主要参考文献（不少于10篇）：
																<%=StringUtil.decodeHtml(Verify.isEmpty(report) ? "" : report.getReferencesl()) %>
															</div>								
														</td>
													</tr>
													<tr>
														<td colspan="6">
															<div>
																六、指导教师意见：
																<script id="opinion" name="opinion" type="text/plain">
																	<%=StringUtil.decodeHtml(Verify.isEmpty(report) ? "" : report.getTeacherView()) %>	
																</script>
															</div>
																			
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<table class="table">
																<tr>
																	<td colspan="4" style="text-align: center;">七、开题报告会纪要</td>
																</tr>
																<tr>
																	<td>时间</td>
																	<td></td>
																	<td>地点</td>
																	<td></td>
																</tr>
																<tr>
																	<td>与会人员</td>
																	<td colspan="3">
																		<table class="table">
																			<tr>
																				<td>姓名</td>
																				<td>职务（职称）</td>
																				<td>姓名</td>
																				<td>职务（职称）</td>
																			</tr>
																			<tr>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																			</tr>
																			<tr>
																				<td></td>
																				<td></td>
																				<td></td>
																				<td></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="4">
																		<div style="text-align: center;">
																			会议记录摘要：
																		</div>
																		<div class="text-left height-100">
																			
																		</div>
																		<div class="text-right">
																			会议主持人： <br/>
																			会议记录人： <br/>
																			年  月  日 
																		</div>
																	</td>
																</tr>
																<tr>
																	<td colspan="4" style="text-align: center;">
																		<div style="text-align: center;">
																			八、开题答辩小组意见：
																		</div>
																		<div class="text-left height-100">
																			
																		</div>
																		<div class="text-right">
																			负责人签名： <br/>
																			年  月  日 
																		</div>
																	</td>
																</tr>
																<tr>
																	<td colspan="4" style="text-align: center;">
																		<div style="text-align: center;">
																			九、系(部)意见：
																		</div>
																		<div class="text-left height-100">
																			
																		</div>
																		<div class="text-right">
																			负责人签名： <br/>
																			 单位（盖章）<br/>
																			年  月  日 
																		</div>
																	</td>
																</tr>
															</table>																		
														</td>
													</tr>
												</table>
											</form>
										</div> <!-- /widget-content -->	
									</div> <!-- /widget-header -->
									<div class="form-actions text-right">
										<button type="submit" class="btn btn-primary">提交意见</button> 
										<button type="button" class="btn">返回</button>
									</div>			
								</div>
							</div>
							
						</form>
						<div class="text-left">
							<a href="<%=basePath %>user/word/exportword.do">导出文档</a>
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

<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>vendor/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath %>vendor/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/taskdoc.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var teacherView	= UE.getEditor('teacher-view');
    var opinion 	= UE.getEditor('opinion');
</script>

  </body>
</html>