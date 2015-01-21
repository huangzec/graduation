<%@page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	Settime submitTime 	= (Settime) request.getSession().getAttribute("submit-time");
 %>
	<jsp:include page="../common/header.jsp" flush="true" />

	<style type="text/css">
		.input_required{
			height:28px;
		}
	</style>
	</head>
<body>
<div class="navbar navbar-fixed-top">
	<jsp:include page="../common/top.jsp" flush="true" />
	<!-- /navbar-inner -->
</div> <!-- /navbar -->
<div id="content">
		<div class="container">
			<div class="row">
				<jsp:include page="../common/navmenu_tea.jsp" flush="true" />
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-envelope"></i>发送消息
					</h1>
					<div class="row">
						<div class="span9">
							<div class="widget">
								<div class="widget-content">
									<i class="icon-star"></i> 提交时间：
								</div> <!-- /widget-content -->					
							</div>
							<div class="widget-content">
								<div class="tabbable">
									<div style="text-align:center"><span style="color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span></div>
									<br />
									<div class="tab-content">
										<div class="tab-pane active" id="1">
											<form action="<%=basePath %>user/message/send.do" method="post" id="" class="form-horizontal">
												<fieldset>
													<div class="control-group">
														<label class="control-label" for="topicname">标题</label>
														<div class="controls">
															<input name="title" class="input-medium" type="text" style="width:590px;" />
														</div>
														<!-- /controls -->
													</div>
													<div class="control-group">
														<label class="control-label" for="topicname">接收人</label>
														<div class="controls">
															<input name="toid" class="input-medium" type="text" style="width:590px;" />
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													<div class="control-group" style="padding-top:10px;">
														<label class="control-label" for="topTec">内容</label>
														<div class="controls">
															<script id="editor" type="text/plain" style="width:590px;height:200px;" name="content"></script>
														</div>
													</div>							
													<div class="form-actions" style="text-align: center; padding:10px 0 10px 0;">
														<button type="submit" class="btn btn-primary">发送</button>
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
	</div> <!-- /container -->
</div> <!-- /footer -->
<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

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