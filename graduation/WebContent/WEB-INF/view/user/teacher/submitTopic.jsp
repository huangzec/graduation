<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	Settime submitTime 	= (Settime) request.getSession().getAttribute("submit-time");
 %>
	<jsp:include page="../common/header.jsp" flush="true" />
	<script language="JavaScript">
		function tick() {
		var date = new Date();
		var y = date.getYear();
		var month = date.getMonth() + 1;
		var d = date.getDate();
		var hr = date.getHours();
		var m = date.getMinutes();
		var s = date.getSeconds();
		var day = date.getDay();
		y = y > 200 ? y : 1900 + y;
		if (month < 10) month = "0" + month;
		if (d < 10) d = "0" + d;
		if (hr < 10) hr = "0" + hr;
		if (m < 10) m = "0" + m;
		if (s < 10) s = "0" + s;
		timeString = y + "" + month + "" + d + "" + hr + "" + m + "" + s;				
		document.getElementById("topId").value = timeString;
	}
	</script>
	<script language="JavaScript">
		function Display(sid)
		{
			var rad2 = document.getElementById("radio2");				
			var rad3 = document.getElementById("radio3");
			var rad4 = document.getElementById("radio4");
			if(sid == 1){
				rad2.style.display = 'none';
				rad3.style.display = 'none';
				rad4.style.display = 'none';
			}else if(sid == 2){
				rad2.style.display = 'block';
				rad3.style.display = 'none';
				rad4.style.display = 'none';
			}else if(sid == 3){
				rad2.style.display = 'none';
				rad3.style.display = 'block';
				rad4.style.display = 'none';
			}else{
				rad2.style.display = 'none';
				rad3.style.display = 'none';
				rad4.style.display = 'block';
			}
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
				<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-share"></i>提交课题
					</h1>
					<div class="row">
						<div class="span9">
							<div class="widget">
								<div class="widget-content">
									<i class="icon-star"></i> 提交时间：
									<%=(Verify.isEmpty(submitTime) ? "<span class=\"label label-default\">非提交时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + submitTime.getStartTime() + " ~~~~ " + submitTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>") %>
								</div> <!-- /widget-content -->					
							</div>
							<%if(!Verify.isEmpty(submitTime)){ %>
							<div class="widget-content">
								<div class="tabbable">
									<div style="text-align:center"><span style="color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span></div>
									<br />
									<div class="tab-content">
										<div class="tab-pane active" id="1">
											<form action="<%=basePath %>user/topic/tea_submitTopic.do" method="post" id="selection-form" class="form-horizontal">
												<fieldset>
													<div class="control-group">
														<label class="control-label" style="margin-top:6px;"> 课题类型</label>
														<div class="controls">
															<label class="radio inline">
																<input type="radio" name="topType" value="1" checked />1.论文
															</label>
															<label class="radio inline">
																<input type="radio" name="topType" value="2" />2.设计
															</label>
															<label class="radio inline">
																<input type="radio" name="topType" value="3" />3.其他
															</label>
														</div>
														<!-- /controls -->
													</div>
													<div class="control-group">
														<label class="control-label" style="margin-top:6px;"> 课题来源</label>
														<div class="controls">
															<label class="radio inline">
										  						<input type="radio" name="source" value="1" checked> 1.科学技术
															</label>
															<label class="radio inline">
										  						<input type="radio" name="source" value="2"> 2.生产实践 
															</label>
															<label class="radio inline">
										  						<input type="radio" name="source" value="3"> 3.社会经济
															</label>
															<label class="radio inline">
										  						<input type="radio" name="source" value="4"> 4.自拟
															</label>
															<label class="radio inline">
										  						<input type="radio" name="source" value="5"> 5.其他  
															</label>
														</div>
														<!-- /controls -->
													</div>
													
													<div class="control-group">
														<label class="control-label" for="topicname"> 课题名称</label>
														<div class="controls">
															<input name="topName" class="input-medium" type="text" style="width:590px;" />
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												
													<div class="control-group">
														<label class="control-label" for="topictec"> 完成人数</label>
														<div class="controls">
															<input type="radio" id="tp1" name="topNum" value="1" onclick="Display(1)" checked> 1　
															<input type="radio" id="tp2" name="topNum" value="2" onclick="Display(2)"> 2　
															<input type="radio" id="tp3" name="topNum" value="3" onclick="Display(3)"> 3　
															<input type="radio" id="tp4" name="topNum" value="4" onclick="Display(4)"> 4
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												
													<div style="display:none" id="radio2">
														<div class="control-group">											
															<div class="controls">
																子课题名称一：<input name="sTName1" class="input-medium" id="firstname1" type="text" style="width:497px;" />　
															</div>
														</div> <!-- /control-group -->
								
														<div class="control-group">											
															<div class="controls" style="margin-top:13px;">
																子课题名称二：<input name="sTName2" class="input-medium" id="firstname2" type="text" style="width:497px;" />
															</div>		
														</div> <!-- /control-group -->
													</div>
							
													<div style="display:none" id="radio3">
														<div class="control-group">											
															<div class="controls">
																子课题名称一：<input name="sTName3" class="input-medium" id="firstname1" type="text" style="width:497px;" />
															</div>
														</div> <!-- /control-group -->
														<div class="control-group">											
															<div class="controls" style="margin-top:13px;">
																子课题名称二：<input name="sTName4" class="input-medium" id="firstname2" type="text" style="width:497px;" />
															</div>		
														</div> <!-- /control-group -->
	
														<div class="control-group">											
															<div class="controls" style="margin-top:13px;">
																子课题名称三：<input name="sTName5" class="input-medium" id="firstname3" type="text" style="width:497px;" />
															</div>
														</div> <!-- /control-group -->
													</div>
								
													<div style="display:none" id="radio4">
														<div class="control-group">											
															<div class="controls">
																子课题名称一：<input name="sTName6" class="input-medium" id="firstname1" type="text" style="width:497px;" />
															</div>
														</div> <!-- /control-group -->

														<div class="control-group">											
															<div class="controls" style="margin-top:13px;">
																子课题名称二：<input name="sTName7" class="input-medium" id="firstname2" type="text" style="width:497px;" />
															</div>		
														</div> <!-- /control-group -->
	
														<div class="control-group">											
															<div class="controls" style="margin-top:13px;">
																子课题名称三：<input name="sTName8" class="input-medium" id="firstname3" type="text" style="width:497px;" />
															</div>
														</div> <!-- /control-group -->
								
														<div class="control-group">											
															<div class="controls" style="margin-top:13px;">
																子课题名称四：<input name="sTName9" class="input-medium" id="firstname4" type="text" style="width:497px;" />
															</div>
														</div> <!-- /control-group -->
													</div>
													<div class="control-group" style="padding-top:10px;">
														<label class="control-label" for="topTec"> 课题内容</label>
														<div class="controls">
															<script id="editor" type="text/plain" style="width:590px;height:200px;" name="topContent"></script>
														</div>
													</div>							
													<div class="control-group">
														<label class="control-label" for="topicname"> 关键字</label>
														<div class="controls">
															<input name="topkeywords" class="input-medium" placeholder="如：移动应用、信息系统" type="text" style="width:590px;" />
														</div>
														<!-- /controls -->
													</div>	
													<div class="form-actions" style="text-align: center; padding:10px 0 10px 0;">
														<button type="submit" class="btn btn-primary">Save</button>　
														<button class="btn">Cancel</button>
													</div>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
							</div>	<!-- /widget-content -->
							<%} %>
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
	<!-- /container -->
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
	
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.parse.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    UE.getEditor('editor');
	</script> 
</body>
</html>