<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="../common/header.jsp" flush="true" />
<link href="<%=basePath%>vendor/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" />
<link href="<%=basePath%>css/style.css" rel="stylesheet" />
<link href="<%=basePath%>vendor/bootstrap-switch/css/highlight.css" rel="stylesheet" />
<link href="<%=basePath%>vendor/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" />
<%
	Selectfirst selectfirst = (Selectfirst) request.getAttribute("selectfirst");
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Map<String, String> professionMap = (Map<String, String>) request.getAttribute("profession_map");
	Topicreport topicreport = (Topicreport) request.getAttribute("topic-report");
	Opentopicinfo opentopicinfo = (Opentopicinfo) request.getAttribute("open-topic-info");
%>
</head>
<body background="<%=basePath %>cscon/body-bg.png">
	<div style="margin-top: 3%;">
		<div class="container">
			<!-- /span3 -->
			<div style="text-align: center; margin-bottom: 15px;">
				<a class="btn btn-primary" onclick=preview(1)>打印</a> &nbsp;&nbsp;
				<a class="btn btn-info" href="<%=basePath%>user/rejoin/document.do">返回</a>
			</div>
			<div class="widget-table" style="width: 70%; margin-left: 169px; margin-bottom: 20px;">
				<%
					if (!Verify.isEmpty(topicreport) && !Verify.isEmpty(opentopicinfo)) {
				%>
				<!-- startprint1 -->
				<div class="widget-content" style="text-align: center; font-size: 20px; font-weight: bold;">
					<br />
					<div class="line-height text-center">
						<img alt="" src="<%=basePath%>images/hhxylogo.jpg">
					</div>
					<div class="line-height">
						<h2>
							<% if(selectfirst.getTbtopic().getTopType().equals("1")){ %>
								本科生毕业论文
							<% }else{ %>
								本科生毕业设计
							<% } %>
							<br />
							开 题 报 告 书
						</h2>
					</div>
					<div class="left-100">
						<fieldset>
							<div class="control-group height-60">
								<label class="control-label">
									题 目
								</label>
								<div class="controls">
									<%=selectfirst.getTbtopic().getTopName()%>
								</div>
							</div>
							<div class="control-group height-60">
								<label class="control-label">
									学生姓名
								</label>
								<div class="controls">
									<%=stuMap.get("stu_Name")%>
								</div>
							</div>
							<div class="control-group height-60">
								<label class="control-label">
									学 号
								</label>
								<div class="controls">
									<%=(String) request.getSession().getAttribute("user_id")%>
								</div>
							</div>
							<div class="control-group height-60">
								<label class="control-label">
									系 别
								</label>
								<div class="controls">
									<%=department.getDeptName()%>
								</div>
							</div>
							<div class="control-group height-60">
								<label class="control-label">专 业</label>
								<div class="controls">
									<%=professionMap.get("pro_Name")%>
								</div>
							</div>
							<div class="control-group height-60">
								<label class="control-label">指导教师</label>
								<div class="controls">
									<%=HResponse.formatValue("tea", selectfirst.getTeaId(), request)%>
								</div>
							</div>
							<div class="control-group height-60">
								<label><%=HResponse.formatDateTime(HResponse.formatDate(new Date()))%></label>
							</div>
						</fieldset>
					</div>
					<hr />
					<div class="widget-content">
						<input type="hidden" name="stuid" value="" />
						<input type="hidden" name="teaid" value="" />
						<table class="table  table-bordered">
							<tr>
								<td width="100">设计题目</td>
								<td sytle="">
									<%=selectfirst.getTbtopic().getTopName()%>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										一、选题的目的、意义及相关研究动态和自己的见解：
										<br />
										<%=(StringUtil.decodeHtml(topicreport.getMeaning())) %>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										二、课题的主要内容：
										<br />
										<%=(StringUtil.decodeHtml(topicreport.getContent())) %>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										三、研究方法、设计方案或论文撰写提纲：
										<br />
										<%=(StringUtil.decodeHtml(topicreport.getResearch())) %>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										四、完成期限和预期进度：
										<br />
										<%=(StringUtil.decodeHtml(topicreport.getDeadline())) %>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div>
										五、主要参考文献（不少于10篇）：
										<br />
										<%=(StringUtil.decodeHtml(topicreport.getReferencesl())) %>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div>
										六、指导教师意见：
										<br />
										<%=(StringUtil.decodeHtml(topicreport.getTeacherView())) %>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table class="table">
										<tr>
											<td colspan="4" style="text-align: center;">
												七、开题报告会纪要
											</td>
										</tr>
										<tr class="tabletr">
											<td>时间</td>
											<td><%=opentopicinfo.getOpiDate()%></td>
											<td>地点</td>
											<td><%=opentopicinfo.getOpiPlace()%></td>
										</tr>
										<tr class="tabletr">
											<td>与会人员</td>
											<td colspan="3">
												<table class="table">
													<tr>
														<td>姓名</td>
														<td>职务（职称）</td>
														<td>姓名</td>
														<td>职务（职称）</td>
													</tr>
													<%
														int i = 0;
														for (int k = 0; k < 3; k++) {
													%>
													<tr>
														<%
															for (int j = 0; j < 2; j++) {
																String[] judgeteacher = StringUtil.splitString(opentopicinfo.getJudge());
																if (i < judgeteacher.length) {
														%>
														<td><%=Verify.isEmpty(HResponse.formatValue("tea", judgeteacher[i], request)) ? "" : HResponse.formatValue("tea",judgeteacher[i], request)%></td>
														<td><%=HResponse.formatValue("teapos", HResponse.formatValue("pos", judgeteacher[i], request), request)%></td>
														<%
																	i++;
																} else {
														%>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<%
																}
															}
														%>
													</tr>
													<%	}%>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="4">
												<div style="text-align: center;">
													会议记录摘要：
												</div>
												<div class="text-left height-100">
													<%=HResponse.formatEmpty(topicreport.getMeeting(), request) %>
												</div>
												<div class="text-right">
													会议主持人：
													<%=HResponse.formatValue("tea", opentopicinfo.getHeaderman(), request)%><br />
													会议记录人：
													<br />
													<%=HResponse.formatDateTime(opentopicinfo.getOpiDate())%>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="4" style="text-align: center;">
												<div style="text-align: center;">
													八、开题答辩小组意见：
												</div>
												<div class="text-left height-100">
													<%=HResponse.formatEmpty(topicreport.getJudgeView(), request) %>
												</div>
												<div class="text-right">
													负责人签名：
													<br />
													年 月 日
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="4" style="text-align: center;">
												<div style="text-align: center;">
													九、系(部)意见：
												</div>
												<div class="text-left height-100">
													<%=HResponse.formatEmpty(topicreport.getDepartmentView(), request) %>
												</div>
												<div class="text-right">
													负责人签名：
													<br />
													单位（盖章）
													<br />
													年 月 日
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- /widget-content -->
				</div>
				<!-- endprint1 -->
				<!-- /widget-header -->
				<%
					} else {
				%>
				<div class="widget-content message"
					style="height: 50px; line-height: 50px; font-size: 18px;">
					暂无相关记录
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
	<!-- Le javascript
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>cscon/js/jquery-1.7.2.min.js"></script>
	<script src="<%=basePath%>cscon/js/bootstrap.js"></script>
	<script src="<%=basePath%>vendor/hhjslib/hhjslib.js"></script>
	<script src="<%=basePath%>vendor/hhjslib/hhjslib.dialog.js"></script>
	<script src="<%=basePath%>vendor/hhjslib/hhjslib.utils.js"></script>
	<script
		src="<%=basePath%>vendor/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script
		src="<%=basePath%>vendor/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="<%=basePath%>js/printview.js"></script>
</body>
</html>