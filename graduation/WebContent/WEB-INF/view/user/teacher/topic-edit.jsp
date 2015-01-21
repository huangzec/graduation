<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	Tbtopic topic = (Tbtopic) request.getAttribute("topic");
	List<Tbtopic> list = (List<Tbtopic>) request.getAttribute("sontopiclist");
	//Settime submitTime 	= (Settime) request.getSession().getAttribute("submit-time");
 %>
	<jsp:include page="../common/header.jsp" flush="true" />
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
							<!--<div class="widget">
								<div class="widget-content">
									<i class="icon-star"></i> 提交时间：
									%=(Verify.isEmpty(submitTime) ? "<span class=\"label label-default\">非提交时间 ！</span>" : "【 <a href=\"javascript:;\" class=\"accordion-toggle title collapsed\" title=\"正在进行......\">" + submitTime.getStartTime() + " ~~~~ " + submitTime.getEndTime() + "</a> 】  <span class=\"label label-success\">正在进行中......</span>") %>
								</div>  /widget-content 					
							</div>
							%if(!Verify.isEmpty(submitTime)){ %>-->
							<div class="widget-content">
								<div class="tabbable">
									<div style="text-align:center"><span style="color: red;"><b><%=(Verify.isEmpty(request.getAttribute("message")) ? "" : request.getAttribute("message")) %></b></span></div>
									<br />
									<div class="tab-content">
										<div class="tab-pane active" id="1">
											<form action="<%=basePath %>user/topic/teaedit.do" method="post" id="selection-form" class="form-horizontal">
												<input type="hidden" name="topid" value="<%=topic.getTopId() %>" />
												<fieldset>
													<div class="control-group">
														<label class="control-label" style="margin-top:6px;"> 课题类型</label>
														<div class="controls" id="topic-type" data-cur="<%=topic.getTopType() %>">
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
														<div class="controls" id="topic-source" data-cur="<%=topic.getTopSource() %>">
															<label class="radio inline">
										  						<input type="radio" name="topSource" value="1" checked> 1.科学技术
															</label>
															<label class="radio inline">
										  						<input type="radio" name="topSource" value="2"> 2.生产实践 
															</label>
															<label class="radio inline">
										  						<input type="radio" name="topSource" value="3"> 3.社会经济
															</label>
															<label class="radio inline">
										  						<input type="radio" name="topSource" value="4"> 4.自拟
															</label>
															<label class="radio inline">
										  						<input type="radio" name="topSource" value="5"> 5.其他  
															</label>
														</div>
														<!-- /controls -->
													</div>
													
													<div class="control-group">
														<label class="control-label" for="topName"> 课题名称</label>
														<div class="controls">
															<input name="topName" id="topName" value="<%=topic.getTopName() %>" class="input-medium" type="text" style="width:590px;" />
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												
													<div class="control-group" id="numdiv">
														<label class="control-label" for="topNum"> 完成人数</label>
														<div class="controls">
															<input type="text" class="input-medium" id="topNum" name="topNum" readonly value="<%=topic.getTopNumber() %>" style="width:590px;" />
															<button type="button" class="btn btn-block edit-numadd-btn">增加</button>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
													<div id="sontopicdiv">
														<% if(!Verify.isEmpty(list)){ 
															for(int i = 0; i < list.size(); i++){
																Tbtopic sontopic = list.get(i);
														%>
															<input type="hidden" name="sonId<%=(i+1) %>" value="<%=sontopic.getTopId() %>" />
															<input type="hidden" value="<%=sontopic.getTopName() %>" id="sonName<%=(i+1) %>" />
															<div class="control-group" id="sondiv<%=(i+1) %>">											
																<div class="controls">
																	子课题名称<%=StringUtil.numToCH(i+1) %>：<input name="sTName<%=(i+1) %>" value="<%=sontopic.getTopName() %>" class="input-medium" type="text" style="width:497px;" />
																	<% if(i == 0){ %>
																		<button type="button" class="btn edit-numsub-btn">删除</button>
																	<% } %>
																</div>
															</div> <!-- /control-group -->
														<% }
														} %>
													</div>
													<div class="control-group" style="padding-top:10px;">
														<label class="control-label" for="editor"> 课题内容</label>
														<div class="controls">
															<script id="editor" type="text/plain" style="width:590px;height:200px;" name="topContent"><%=topic.getTopContent() %></script>
														</div>
													</div>							
													<div class="control-group">
														<label class="control-label" for="topKeywords"> 关键字</label>
														<div class="controls">
															<input name="topKeywords" id="topKeywords" class="input-medium" value="<%=topic.getTopKeywords() %>" type="text" style="width:590px;" />
														</div>
														<!-- /controls -->
													</div>	
													<div class="form-actions" style="text-align: center; padding:10px 0 10px 0;">
														<button type="submit" class="btn btn-primary">Save</button>　
														<button type="button" class="btn">Cancel</button>
													</div>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
							</div>	<!-- /widget-content --><!--
							%} %>
						--></div>
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
	<script src="<%=basePath %>js/site-judge.js"></script>
	<script src="<%=basePath %>js/topic.js"></script>
	
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