<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage 	= (List<Message>) request.getAttribute("total_message");
	Student student 			= (Student) request.getAttribute("student");
	Topicfinish tph 			= (Topicfinish) request.getAttribute("topicfinish");
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
			<jsp:include page="../common/navmenu.jsp" flush="true" />
			<!-- /span3 -->
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-home"></i>
					我的主页					
				</h1>
				<!-- /stat-container -->
				<div class="widget">
					<div class="widget-header">
						<h3>申请答辩</h3>
					</div> <!-- /widget-header -->
					<div class="widget-content">
						<form id="apply-form" class="form-horizontal" method="post" action="" />
							<div class="tabbable applytype">
								<ul class="nav nav-tabs">
								  <li class="active">
								    <a href="#1" data-toggle="tab">选择答辩类型</a>
								  </li>
								</ul>
								<br />
								<div class="tab-content">
									<div class="tab-pane active" id="1">
									<fieldset>
											<div class="control-group">											
												<label class="radio inline">
												    <input type="radio" name="type" value="1" checked>开题答辩
												</label>
												<label class="radio inline">
													<input type="radio" name="type" value="2" />毕业答辩
												</label>			
											</div>
											<div class="control-group">											
												<label class="controls" style="color: red;" id="message">${message }</label>
											</div>
											
										</fieldset>
									
									</div>
								</div>
								<div class="text-right">
									<button type="button" class="btn btn-info typenext" loaded-open="1" loaded-finish="1">下一步</button>
								</div>
							</div>
							<div class="finish hidden">
								<label>
									标　题：
									<input type="text" name="title" value="<%=((Verify.isEmpty(tph) ? (student.getStuName() + "的毕业答辩申请相关材料") : tph.getTitle())) %>" class="input-mlarge" />
								</label>
								<hr/>
								<label>请在附件里上传毕业答辩相关的文档：</label>
								<script id="finish" name="finish" type="text/plain">
									<%=(Verify.isEmpty(tph) ? "" : StringUtil.decodeHtml(tph.getContent())) %>						
								</script>
								<div class="form-actions text-right">
									<button type="button" id="finish-btn" class="btn btn-primary">提交申请</button> 
									<button type="button" class="btn finishpre">返回上一步</button>
								</div>
							</div>
							<div class="openapply openly">
								<jsp:include page="opentopicapply.jsp"/>
							</div>
							<hr>
							<div class="openreport openrt">
								<jsp:include page="opentopicreport.jsp" />
							</div>
							
						</form>
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
<script src="<%=basePath %>js/apply.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var knowing 	= UE.getEditor('knowing');
    //var teacherIdea = UE.getEditor('teacher_idea');
    //var paperIdea 	= UE.getEditor('paper_idea');
    //var departmentIdea 	= UE.getEditor('department_idea');
    var meaning 	= UE.getEditor('meaning');
    var content 	= UE.getEditor('maincontent');
    var research 	= UE.getEditor('research');
    var deadline	= UE.getEditor('deadline');
    var references 	= UE.getEditor('references');
    //var teacherView = UE.getEditor('teacher_view');
    var finish	 	= UE.getEditor('finish');
    $('#openTime').datetimepicker({
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