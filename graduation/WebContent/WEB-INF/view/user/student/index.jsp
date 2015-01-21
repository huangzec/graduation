<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Apply opentopicApply 			= (Apply) request.getAttribute("opentopic_apply");
	Apply graduateApply 			= (Apply) request.getAttribute("graduate_apply");
	Opentopicscore opentopicscore 	= (Opentopicscore) request.getAttribute("opentopicscore");
	Gradetwo revieworderscore 		= (Gradetwo) request.getAttribute("revieworderscore");
	Gradethree graduatescore 		= (Gradethree) request.getAttribute("graduatescore");
	List<Tbtopic> topicList 		= (List<Tbtopic>) request.getAttribute("topicList");
	List<Selectfirst> selList 		= (List<Selectfirst>) request.getAttribute("selList");
	Selectfirst selectfirst 		= (Selectfirst) request.getAttribute("selectfirst");
	boolean isShow 			= false;
	boolean isShowOpentopic = false;
	boolean isShowGraduate 	= false;
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
				<jsp:include page="../common/center-stat.jsp" flush="true" />
				<!-- /stat-container -->
				<div class="widget">	
					<div class="widget-header">
						<i class="icon-volume-up"></i>
						<h3>进度</h3>
					</div> <!-- /widget-header -->	
					<%
						if(!Verify.isEmpty(topicList)) {
							isShow = true;
							boolean isPass = false;
							for(int i = 0; i < topicList.size(); i ++) {
								if(topicList.get(i).getTopStatus().equals("1")) {
									isPass = true;
									break;
								}
							}
							%>
							<div class="widget-content progress-new">
								<ul class="progress">
									<li class="dot-active progress-1"></li><li class="line-active progress-1"></li>
									<%
										if(isPass) {
											%>
											<li class="progress-2 dot-active"></li><li class="progress-2 line-active"></li>
											<li class="progress-3 dot-active"></li><li class="progress-3 line-active"></li>
											<%
												if(!Verify.isEmpty(selectfirst)) {
													isShowOpentopic = true;
													%>
													<li class="dot-active progress-4"></li>
													<%
												}else {
													%>
													<li class="dot-inactive progress-4"></li>
													<%
												}
											 %>
											<%
										}else {
											%>
											<li class="progress-2 dot-inactive"></li><li class="progress-2 line-active"></li>
											<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
											<li class="dot-inactive progress-4"></li>
											<%
										}
									 %>
								</ul>
								<ul class="progress-desc">
									<li style="width: 185px">提交课题<div><div></div></div></li>
									<li style="width: 185px">审核<div><div></div></div></li>
									<li style="width: 165px">选题<div><div></div></div></li>
									<li>指导老师确认<div><div></div></div></li>
								</ul>
							</div>
							<%
						}else {
							if(!Verify.isEmpty(selList)) {
								isShow = true;
							%>
							<div class="widget-content progress-new">
								<ul class="progress">
									<li class="progress-1 dot-active"></li><li class="progress-1 line-active"></li>
									<%
										boolean isSelected = false;
										for(int j = 0; j < selList.size(); j++) {
											if(selList.get(j).getSelStatus().equals("1")) {
												isSelected = true;
												break;
											}
										}
										if(isSelected) {
											isShowOpentopic = true;
											%>
											<li class="dot-active progress-2"></li>
											<%
										}else {
											%>
											<li class="dot-inactive progress-2"></li>
											<%
										}	
									 %>
								</ul>
								<ul class="progress-desc">
									<li style="width: 165px">选题<div><div></div></div></li>
									<li>指导老师确认<div><div></div></div></li>
								</ul>
							</div>
							<%
							}
						}
					 %>
					<%
						if(isShowOpentopic) {
							isShow = true;
						if(!Verify.isEmpty(opentopicApply)) {
							
							%>
							<div class="widget-content progress-new">
								<ul class="progress">
									<li class="dot-active progress-1"></li><li class="line-active progress-1"></li>
									<%
										if(opentopicApply.getPass().equals("2")) {
											%>
											<li class="progress-2 dot-active"></li><li class="progress-2 line-active"></li>
											<%
												if(opentopicApply.getStatus().equals("2")) {
													%>
													<li class="progress-3 dot-active"></li><li class="progress-3 line-active"></li>
													<%
														if(!Verify.isEmpty(opentopicscore)) {
															isShowGraduate = true;
															%>
															<li class="dot-active progress-4"></li>
															<%
														}else {
															%>
															<li class="dot-inactive progress-4"></li>
															<%
														}
													 %>
													
													<%
												}else {
													%>
													<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
													<li class="dot-inactive progress-4"></li>
													<%
												}
											 %>
											
											<%
										}else {
											%>
											<li class="progress-2 dot-inactive"></li><li class="progress-2 line-active"></li>
											<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
											<li class="dot-inactive progress-4"></li>
											<%
										}
									 %>
								</ul>
								<ul class="progress-desc">
									<li style="width: 165px">提交开题答辩申请<div><div></div></div></li>
									<li style="width: 190px">指导老师意见<div><div></div></div></li>
									<li style="width: 165px">答辩安排 <div><div></div></div></li>
									<li style="width: 185px">答辩成绩<div><div></div></div></li>
								</ul>
							</div>
							<%
						}else {
							%>
							<div class="widget-content progress-new">
								<ul class="progress">
									<li class="progress-1 dot-inactive"></li><li class="progress-1 line-active"></li>
									<li class="progress-2 dot-inactive"></li><li class="progress-2 line-active"></li>
									<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
									<li class="dot-inactive progress-4"></li>
								</ul>
								<ul class="progress-desc">
									<li style="width: 165px">提交开题答辩申请<div><div></div></div></li>
									<li style="width: 190px">指导老师意见<div><div></div></div></li>
									<li style="width: 165px">答辩安排 <div><div></div></div></li>
									<li style="width: 185px">答辩成绩<div><div></div></div></li>
								</ul>
							<%
						}
						}
					 %>
					 <%
					 	if(isShowGraduate) {
							isShow = true;
					 	if(!Verify.isEmpty(graduateApply)) {
					 		%>
					 		<div class="widget-content progress-new">
								<ul class="progress">
									<li class="dot-active progress-1"></li><li class="line-active progress-1"></li>
									<%
										if(graduateApply.getPass().equals("2")) {
											%>
											<li class="progress-2 dot-active"></li><li class="progress-2 line-active"></li>
											<%
												if(!Verify.isEmpty(revieworderscore)) {
													%>
													<li class="progress-3 dot-active"></li><li class="progress-3 line-active"></li>
													<%
														if(graduateApply.getStatus().equals("2")) {
															%>
															<li class="progress-4 dot-active"></li><li class="progress-4 line-active"></li>
															<%
																if(!Verify.isEmpty(graduatescore)) {
																	%>
																	<li class="dot-active progress-5"></li>
																	<%
																}else {
																	%>
																	<li class="dot-inactive progress-5"></li>
																	<%
																}
															 %>
															<%
														}else {
															%>
															<li class="progress-4 dot-inactive"></li><li class="progress-4 line-active"></li>
															<li class="dot-inactive progress-5"></li>
															<%
														}
													 %>
													<%
												}else {
													%>
													<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
													<li class="progress-4 dot-inactive"></li><li class="progress-4 line-active"></li>
													<li class="dot-inactive progress-5"></li>
													<%
												}
											 %>
											<%
										}else {
											%>
											<li class="progress-2 dot-inactive"></li><li class="progress-2 line-active"></li>
											<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
											<li class="progress-4 dot-inactive"></li><li class="progress-4 line-active"></li>
											<li class="dot-inactive progress-5"></li>
											<%
										}
									 %>
								</ul>
								<ul class="progress-desc">
									<li style="width: 165px">提交毕业答辩申请<div><div></div></div></li>
									<li style="width: 165px">指导老师意见<div><div></div></div></li>
									<li style="width: 210px">评阅教师评分<div><div></div></div></li>
									<li style="width: 165px">毕业答辩安排<div><div></div></div></li>
									<li>毕业答辩成绩<div><div></div></div></li>
								</ul>
							</div>
					 		<%
					 	}else {
					 		%>
					 		<div class="widget-content progress-new">
								<ul class="progress">
									<li class="progress-1 dot-inactive"></li><li class="progress-1 line-active"></li>
									<li class="progress-2 dot-inactive"></li><li class="progress-2 line-active"></li>
									<li class="progress-3 dot-inactive"></li><li class="progress-3 line-active"></li>
									<li class="progress-4 dot-inactive"></li><li class="progress-4 line-active"></li>
									<li class="dot-inactive progress-5"></li>
								</ul>
								<ul class="progress-desc">
									<li style="width: 165px">提交毕业答辩申请<div><div></div></div></li>
									<li style="width: 165px">指导老师意见<div><div></div></div></li>
									<li style="width: 210px">评阅教师评分<div><div></div></div></li>
									<li style="width: 165px">毕业答辩安排<div><div></div></div></li>
									<li>毕业答辩成绩<div><div></div></div></li>
								</ul>
							</div>
					 		<%
					 	}
					 	}
					  %>
					  <%
					  	if(isShow) {
					  		%>
					  		<div>注：以上进度仅代表过程，不代表结果</div>
					  		<%
					  	}
					   %>
					 <!-- /widget-content -->					
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
<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>