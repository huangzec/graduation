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
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Topicfinish topicfinish = (Topicfinish) request.getAttribute("record");
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
					<% if(Verify.isEmpty(topicfinish)) { %>
						<div class="widget-header">
						<h3>学生 <%=HResponse.formatValue("stuId",request.getParameter("userid").toString(), request) %> 的毕业答辩相关文档</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content maincontent">
						<div class="text-center">
							该学生暂未提交毕业答辩相关文档
						</div>
					</div> <!-- /widget-content -->
					<% } else { %>
					<div class="widget-header">
						<h3>学生 <%=HResponse.formatValue("stuId",topicfinish.getStuId(), request) %> 的毕业答辩相关文档</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content maincontent">
						<div class="text-center">
							<%=topicfinish.getTitle() %>
						</div>
						<hr/>
						<div class="title text-center">
							提交时间：<%=topicfinish.getCreateTime() %>
						</div>
						<div class="title-main">
							<%=StringUtil.decodeHtml(topicfinish.getContent()) %>
						</div>
					</div> <!-- /widget-content -->
					<% } %>
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
    
    $('#receiptTime').datetimepicker({
    	minView: "month", //选择日期后，不会再跳转去选择时分秒 
	　　  format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
	　　  language: 'zh-CN', //汉化 
	　　  autoclose:true //选择日期后自动关闭 
    });
    $('#finishTime').datetimepicker({
    	minView: "month", //选择日期后，不会再跳转去选择时分秒 
	　　  format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
	　　  language: 'zh-CN', //汉化 
	　　  autoclose:true //选择日期后自动关闭 
    });
</script>

  </body>
</html>