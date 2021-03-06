<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	Teacher teacherInfo = (Teacher) request.getAttribute("teacher-info");
	Selectfirst selectfirst = (Selectfirst) request.getAttribute("select-info");
	List<Teacher> teacherlist = (List<Teacher>) request.getAttribute("teacher-list");
	Settime selectTime = (Settime) request.getAttribute("select-time");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
.trClass {
	height: 35px;
}
</style>
<script language="JavaScript">
	function isConfirm() {
		if (confirm("确认提交？"))
			return true;
		else
			return false;
	}
</script>

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
						<i class="icon-bookmark"></i> 课题选择
					</h1>
					<div class="row">
						<div class="span9">
							<div class="widget">
								<div class="widget-content">
									<i class="icon-star"></i> 选择时间：
									<%=(Verify.isEmpty(selectTime) ? "<span class=\"label label-default\">非选择时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + selectTime.getStartTime() + " ~~~~ " + selectTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>")%>
								</div>
							</div>
							<%
								if (!Verify.isEmpty(selectTime)) {
									if (!Verify.isEmpty(request.getAttribute("message"))) {
							%>
							<div style="text-align: center">
								<span style="color: red; font-size: 15px;"> <%=request.getAttribute("message") %><br />
									<br /> </span>
							</div>
							<%
								}
							%>
							<div class="widget">
								<div class="widget-header">
									<i class="icon-file"></i>
									<h3>
										课题选择信息
									</h3>
								</div>
								<div class="widget-content">
									<div class="tabbable">
										<div class="tab-content">
											<div class="tab-pane active" id="1">
												<form id="judge-form" class="form-horizontal">
													<fieldset>
														<div class="control-group">
															<label class="control-label" for="topicname">
																课题名称
															</label>
															<div class="controls" style="padding-top: 5px;">
																<%=selectfirst.getTbtopic().getTopName()%>
															</div>
															<!-- /controls -->
														</div>
														<!-- /control-group -->

														<div class="control-group">
															<label class="control-label" for="topTec">
																课题内容
															</label>
															<div class="controls" style="padding-top: 5px;">
																<%=selectfirst.getTbtopic().getTopContent() %>
															</div>
														</div>

														<div class="control-group">
															<label class="control-label" for="knowing">
																课题认识
															</label>
															<div class="controls" style="padding-top: 5px;">
																<%	if (Verify.isEmpty(selectfirst.getSelKnowing())) { %>
																<a href="<%=basePath %>user/topic/intoWriteKnowing.do?id=<%=selectfirst.getTbtopic().getTopId()%>">请填写对课题的认识</a>
																<% } else {out.println(selectfirst.getSelKnowing());} %>
															</div>
														</div>

														<div class="control-group">
															<label class="control-label" for="teacher">
																指导教师
															</label>
															<div class="controls" style="padding-top: 5px;">
																<%
																	if (Verify.isEmpty(teacherInfo)) {
																%>
																<a href="#teacherlist">请选择教师</a>
																<%
																	} else {
																			int i = 1;
																%>
																<div class="hide header">
																	指导老师 <%=teacherInfo.getTeaName() %> 信息
																</div>
																<div class="hide body">
																	<center>
																		<table>
																			<tr style="height: 30px;">
																				<td style="text-align: center;">教师工号：</td>
																				<td style="text-align: center;"><%=teacherInfo.getTeaId()%></td>
																			</tr>
																			<tr style="height: 30px;">
																				<td style="text-align: center;"> 教师姓名：</td>
																				<td style="text-align: center;"><%=teacherInfo.getTeaName()%></td>
																			</tr>
																			<tr style="height: 30px;">
																				<td style="text-align: center;">教师职称：</td>
																				<td style="text-align: center;"><%=HResponse.formatValue("teapos", teacherInfo.getTeaPos(), request) %></td>
																			</tr>
																			<tr style="height: 30px;">
																				<td style="text-align: center;">电子邮箱：</td>
																				<td style="text-align: center;"><%=teacherInfo.getTeaEmail()%></td>
																			</tr>
																		</table>
																	</center>
																</div>
																<a href="javascript:;" class="know" subknow="knowed<%=(i) %>" title="<%=teacherInfo.getTeaName()%> %>">
																	<%=teacherInfo.getTeaName()%>
										 						</a>
																<!-- Modal -->
																<div id="content<%=i%>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
																	<div class="modal-header">
																		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"> × </button>
																		<h3 id="myModalLabel" style="text-align: center">指导老师信息</h3>
																	</div>
																	<div class="modal-body">
																		
																	</div>
																	<div class="modal-footer">
																		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
																	</div>
																</div>
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
																<%
																	}
																%>
															</div>
														</div>
													</fieldset>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
							<%
								if (Verify.isEmpty(teacherInfo)) {
							%>
							<div class="widget widget-table" id="teacherlist">
								<div class="widget-header">
									<i class="icon-th-list"></i>
									<h3>
										教师列表
									</h3>
								</div>
								<!-- /widget-header -->
								<div class="widget-content">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>
												<td style="text-align: center;">#</td>
												<td style="text-align: center;">教师工号</td>
												<td style="text-align: center;">教师名称</td>
												<td style="text-align: center;">教师职称</td>
												<td style="text-align: center;">选择</td>
											</tr>
										</thead>
										<tbody>
											<%
												if (!Verify.isEmpty(teacherlist)) {
													for (int i = 0; i < teacherlist.size(); i++) {
														Teacher teacher = teacherlist.get(i);
											%>
											<tr>
												<td style="text-align: center;"><%=(i + 1)%></td>
												<td style="text-align: center;"><%=teacher.getTeaId()%></td>
												<td style="text-align: center;"><%=teacher.getTeaName()%></td>
												<td style="text-align: center;"><%=HResponse.formatValue("teapos", teacher.getTeaPos(), request)%></td>
												<td class="action-td" style="text-align: center;">
													<button type="submit" class="btn btn-primary"
														onclick="{if(confirm('　　确定选择吗？\n\n　　提交后不能再更改！')){window.location='<%=basePath%>user/topic/selectteacher.do?id=<%=teacher.getTeaId()%>';return true;}return false;}">
														<i class="icon-ok"></i>
													</button>
												</td>
											</tr>
											<%
												}
											%>
											<tr>
												<td colspan="5">
													<div style="float: right; margin-right: 20px;">
														<jsp:include page="../common/pagelist.jsp" flush="true" />
													</div>
												</td>
											</tr>
											<%
												}
														if (Verify.isEmpty(teacherlist) || teacherlist.size() == 0) {
											%>
											<tr>
												<td colspan="5" style="color: red; text-align: center;">
													暂无相关记录
												</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
								<!-- /widget-content -->
							</div>
							<%
								}
								}
							%>
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
	<script src="<%=basePath%>js/site-judge.js"></script>

	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>vendor/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>vendor/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>vendor/ueditor/ueditor.parse.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>vendor/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    UE.getEditor('knowing');
	    UE.getEditor('toptec');
	</script>
</body>
</html>