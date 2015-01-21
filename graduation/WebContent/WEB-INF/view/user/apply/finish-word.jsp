<%@page language="java" import="java.util.*, java.net.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage = (List<Message>) request.getAttribute("total_message");
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	List<Topicfinish> list = (List<Topicfinish>) request.getAttribute("list");
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
						<h3>学生 <%=HResponse.formatValue("stuId", request.getParameter("userid"), request) %> 的毕业答辩相关文档</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content maincontent">
						<div class="title">
							<span>标题</span>日期
						</div>
						<ul>
							<%
								if(!Verify.isEmpty(list)) {
									for(int i = 0; !Verify.isEmpty(list) && i < list.size(); i ++) {
										Topicfinish topicfinish = list.get(i);
							%>
								<li>
							  		<span><%=topicfinish.getCreateTime() %></span>
							  		<a href="<%=basePath %>user/apply/finishworddetail.do?id=<%=topicfinish.getId() %>&itemid=<%=URLEncoder.encode(request.getServletPath().toString()) %>" title="<%=topicfinish.getTitle() %>">
							  			<%=StringUtil.cutString(topicfinish.getTitle(), 100) %>
							  		</a>
							  </li>
							<%
									}
							%>
								<div class="rfloat">
									<jsp:include page="../common/pagelist.jsp" flush="true" />
								</div>
							<%
								}else if(Verify.isEmpty(list) || list.size() == 0){
							%>
								<div class="text-center">
									暂无相关记录
								</div>
							<%
								}
							 %>
						</ul>
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

  </body>
</html>