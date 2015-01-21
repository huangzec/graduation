<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	Tbtopic topic = (Tbtopic) request.getAttribute("topic");
	Settime selectTime = (Settime) request.getAttribute("select-time");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
	.trClass {height: 35px;}
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
						<i class="icon-bookmark"></i> 选择课题
					</h1>
					<div class="row">
						<div class="span9">
							<div class="widget">
								<div class="widget-content">
									<i class="icon-star"></i> 填写相关信息【<a class="accordion-toggle title collapsed">填写对所选课题的理解和认识……</a>】
								</div>
							</div>
							<div class="widget-content">
								<div class="tabbable">
									<div style="text-align:center">
										<span style="color: red;">
											<b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b>
										</span>
									</div>
									<br />
									<div class="tab-content">
										<div class="tab-pane active" id="1">
											<form action="<%=basePath%>user/topic/writeKnowing.do" method="post" id="selection-form" class="form-horizontal" name="writeknow">
												<fieldset>
													<input type="hidden" name="topicId" value="<%=topic.getTopId() %>" />
													<div class="control-group">
														<label class="control-label" for="topicname"> 课题名称</label>
														<div class="controls">
															<input type="text" class="input-medium disabled" style="width:600px;" id="topicname" value="<%=topic.getTopName() %>" name="topicname" disabled />
															<p class="help-block">
																课题名称已确定，不能更改！
															</p>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													
													<div class="control-group">
														<label class="control-label" for="topicsubmit"> 提 交 人</label>
														<div class="controls">
															<input type="text" class="input-medium disabled" style="width:600px;" id="topicname" value="<%=topic.getTopCommitId() %>" name="topicname" disabled />
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													
													<div class="control-group">
														<label class="control-label" for="topictec"> 课题内容</label>
														<div class="controls">
															<script id="toptec" type="text/plain" style="width:600px;height:150px;" name="toptec">
																<%=topic.getTopContent() %>
															</script>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													
													<div class="control-group" style="padding-top: 10px;">
														<label class="control-label" for="knowing"> 对课题的认识</label>
														<div class="controls">
															<script id="knowing" type="text/plain" style="width:600px;height:150px;" name="knowing"></script>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													
													<div class="form-actions" style="text-align: center; padding:10px 0 10px 0;">
														<button type="submit" id="topic-sub-btn" class="btn btn-primary">Save</button>
													</div>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
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
	<script src="<%=basePath%>js/site-judge.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/ueditor.parse.js"> </script>
	<script src="<%=basePath %>js/topic.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>vendor/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    UE.getEditor('knowing');
	    UE.getEditor('toptec');
	</script> 
</body>
</html>