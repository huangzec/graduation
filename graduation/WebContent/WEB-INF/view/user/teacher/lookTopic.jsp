<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	List<Tbtopic> topicList = (List<Tbtopic>) request.getAttribute("topicList");
	List<Tbtopic> sonTopicList = (List<Tbtopic>) request.getAttribute("sonTopicList");
	//Settime lookTime = (Settime) request.getSession().getAttribute("looktime");
	List begroupList 	= (List) request.getAttribute("begroupList");
	String curYear 		= (String) request.getParameter("year");
%>
	
	<jsp:include page="../common/header.jsp" flush="true" />
	<link rel="stylesheet" href="<%=basePath %>cscon/style.css" />
	<link rel="stylesheet" href="<%=basePath%>cscon/uniform.css" />
	<link rel="stylesheet" href="<%=basePath %>cscon/select2.css" />
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
				<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
				<!-- /span3 -->
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-th-list"></i> 查看课题
					</h1>
					<%if(!Verify.isEmpty(request.getAttribute("message"))){ %>
						<div class="alert alert-info alert-block">
							<button class="close" data-dismiss="alert">×</button>
							<strong><div style="text-align: center;"><%=request.getAttribute("message") %></div></strong>
						</div>
					<%} %>
					<!--<div class="widget">
						<div class="widget-content">
							<i class="icon-star"></i> 查看时间：
							//(Verify.isEmpty(lookTime) ? "<span class=\"label label-default\">非查看时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + lookTime.getStartTime() + " ~~~~ " + lookTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>") %>
						</div>
						 /widget-content 
					</div>
					--><!-- /widget -->
				<div class="widget">										
					<div class="widget-content">
						 <form class="form-search" method="post" action="<%=basePath %>user/revieworder/list.do">
						 	<label>请选择年份：</label>
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
						</form>
					</div> <!-- /widget-content -->
					<div class="widget widget-table">
						<div class="widget-header">
							<i class="icon-th-list"></i>
							<h3>课题列表</h3>
						</div>
						<!-- /widget-header -->
						<div class="widget-content">
							<table class="table table-striped table-bordered">
								<thead>
									<tr class="tabletr">
										<td style="text-align: center;">#</td>
										<td style="text-align: center;">名称</td>
										<td style="text-align: center;">内容</td>
										<td style="text-align: center;">类型</td>
										<td style="text-align: center;">来源</td>
										<td style="text-align: center;">关键字</td>
										<td style="text-align: center;">完成人数</td>
										<td style="text-align: center;">状 态</td>
										<td style="text-align: center;">操作</td>
									</tr>
								</thead>
								<tbody id="judge-form">
									<%
										//if (!Verify.isEmpty(topicList) && !Verify.isEmpty(lookTime)) {
										if (!Verify.isEmpty(topicList)){
											for (int i = 0; !Verify.isEmpty(topicList) && i < topicList.size(); i++) {
												Tbtopic tbtopic = topicList.get(i);
									%>
									<tr>
										<td style="text-align: center;"><%=(i+1) %></td>
										<td style="text-align: center;">
											<%	if(tbtopic.getTopNumber() == 1){ %>
												<a href="#" class="disabled" title="<%=tbtopic.getTopName()%>">
													<%=StringUtil.cutString(tbtopic.getTopName(), 20)%>
												</a>
											<%} else {%>
												<a href="javascript:;" class="name" tag="name<%=i%>" title="<%=tbtopic.getTopName()%>"> 
													<%=StringUtil.cutString(tbtopic.getTopName(), 20)%>
												</a>
											<%} %>
										</td>
										<td style="text-align: center;">
											<div class="hide header">
												课题  <%=(Verify.isEmpty(tbtopic.getTopName()) ? "" : tbtopic.getTopName())%> 内容
											</div>
											<div class="hide body">
												<p><%=(Verify.isEmpty(tbtopic.getTopContent()) ? "" : tbtopic.getTopContent())%></p>
											</div>
											<a href="javascript:;" class="know" subknow="knowed<%=(i) %>" title="<%=Verify.isEmpty(StringUtil.cutString(tbtopic.getTopContent(), 20)) ? "" : StringUtil.cutString(tbtopic.getTopContent(), 20) %>">
												查看
										 	</a>
										</td>
										<td style="text-align: center;"><%=HResponse.formatValue("toptype", tbtopic.getTopType(), request) %></td>
										<td style="text-align: center;"><%=HResponse.formatValue("topsource", tbtopic.getTopSource(), request) %></td>
										<td style="text-align: center;"><%=tbtopic.getTopKeywords() %></td>
										<td style="text-align: center;"><%=tbtopic.getTopNumber() %></td>
										<td style="text-align: center;">
											<%	String topStatus = tbtopic.getTopStatus();
												if (topStatus.equals("0")) {
													out.println("未审核");
												} if (topStatus.equals("1")) {
													out.println("审核通过");
												} if (topStatus.equals("2")) {
													out.println("未通过");
												} %>
										</td>
										<td style="text-align: center;">
											<a href="<%=basePath %>user/topic/teaeditview.do?id=<%=tbtopic.getTopId() %>" class="btn btn-info" title="编辑">
												<i class="icon-edit"></i>
											</a>
										</td>
									</tr>
									<%
										if (!Verify.isEmpty(sonTopicList) && sonTopicList.size() > 0) {
									%>
									<tr class="sontopicname<%=i%>" style="display: none;">
										<td colspan="9" style="padding-top: 10px;">
											<table style="width: 100%;" class="table table-striped table-bordered">
												<thead>
													<tr>
														<td colspan="9" style="text-align: center; color: orange; font-weight: bold">
															<%=tbtopic.getTopName()%>　子课题信息
														</td>
													</tr>
													<tr>
														<td style="text-align: center;">#</td>
														<td colspan="8" style="text-align: center;">子课题名称</td>
													</tr>
												</thead>
												<tbody>
													<%
														int index = 0;
														for (int j = 0; !Verify.isEmpty(sonTopicList) && j < sonTopicList.size(); j++) {
															Tbtopic sontopic = sonTopicList.get(j);
															if (sontopic.getParentId().equals(tbtopic.getTopId())) {
													%>
													<tr>
														<td style="text-align: center;"><%=(index + 1)%></td>
														<td colspan="8" style="text-align: center;"><%=(Verify.isEmpty(sontopic.getTopName()) ? "暂无相关信息" : StringUtil.cutString(sontopic.getTopName(), 30))%></td>
													</tr>
													<% index++;
													  //if(index == 1){ %>
													<%		} %>
													<%	} %>
												</tbody>
											</table>
										</td>
										
									</tr>
									<%	} %>
									<% } %>
									<tr>
										<td colspan="9"><div style="float: right; margin-right: 20px;"><jsp:include page="../common/pagelist.jsp" flush="true" /></div></td>
									</tr>
									<!-- Modal -->
										<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-header">
										    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										    	<h3 class="myModalLabel"></h3>
										  	</div>
										  	<div class="modal-body"></div>
										  	<div class="modal-footer">
										    	<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
										  	</div>
										</div>
									<% } if(Verify.isEmpty(topicList)){ %>
										<tr class="tabletr">
											<td colspan="9" align="center">
												您未提交任何课题！
											</td>
										</tr>
									<% } %>
								</tbody>
							</table>
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
				</div>
				<!-- /span9 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /content -->
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
	<script type="text/javascript" src="<%=basePath %>js/site-judge.js"></script>
    <!--<script type="text/javascript" src="=basePath %>js/topic-edit.js"></script>
           	
	--><script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.parse.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    
	</script>
	<script type="text/javascript" src="<%=basePath %>cscon/js/jquery.ui.custom.js"></script>
	<script type="text/javascript" src="<%=basePath %>cscon/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>cscon/js/unicorn.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.uniform.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/select2.min.js"></script>
    
</body>
</html>