<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	//List<Opentopicscore> list        = (List<Opentopicscore>) request.getAttribute("list");
	//List<Opentopicinfo> opinfolist   = (List<Opentopicinfo>)  request.getAttribute("opinfolist");
	Opentopicinfo opentopicinfo    = (Opentopicinfo) request.getAttribute("opentopicinfo");
	Opentopicscore opentopicscore  = (Opentopicscore) request.getAttribute("opentopicscore");
%>

<jsp:include page="../common/header.jsp" flush="true" />
<style type="text/css">
.trClass {
	height: 35px;
}
</style>
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
				<jsp:include page="../common/navmenu.jsp" flush="true" />
				<div class="span9">
					<h1 class="page-title">
						<i class="icon-list-alt"></i>答辩信息
					</h1>
					<div class="row">
						<div class="span9">
							<div class="widget">
								<div class="widget-header">
									<i class="icon-ok-sign"></i>
									<h3>开题答辩成绩</h3>
									<a href="<%=basePath %>user/rejoin/grade.do" class="btn btn-type f-right">返回</a>
								</div>
								<div class="widget-content">
									<div class="tabbable">
										<div class="tab-content">
											<div class="tab-pane active" id="1">
												<fieldset>
													<table class="table table-bordered">
														<thead>
															<tr class="tabletr">
																<td>#</td>
																<td>答辩时间</td>
																<td>答辩地点</td>
																<td>答辩评委</td>
																<td>答辩组长</td>
																<td>成　绩</td>
																<td>结　果</td>
															</tr>
														</thead>
														<tbody>
															<%	
																if (!Verify.isEmpty(opentopicinfo) && !Verify.isEmpty(opentopicscore)) { 
															%>
																<tr class="tabletr">
																	<td>1</td>
																	<td><%=Verify.isEmpty(opentopicinfo.getOpiDate()) ? "" : opentopicinfo.getOpiDate() %></td>
																	<td><%=Verify.isEmpty(opentopicinfo.getOpiPlace()) ? "" : opentopicinfo.getOpiPlace() %></td>
																	<td>
																		<%	
																			if(!Verify.isEmpty(opentopicinfo.getJudge())){
																				String[] judgeteacher = StringUtil.splitString(opentopicinfo.getJudge());
																				for(int i = 0; i < judgeteacher.length; i++){
																					out.println(Verify.isEmpty(HResponse.formatValue("tea", judgeteacher[i], request)) ? "" : HResponse.formatValue("tea", judgeteacher[i], request)+"　");
																				}
																			}
																		 %>
																	</td>
																	<td><%=Verify.isEmpty(HResponse.formatValue("tea", opentopicinfo.getHeaderman(), request)) ? "" : HResponse.formatValue("tea", opentopicinfo.getHeaderman(), request) %></td>
																	<td><font color="red"><%=Verify.isEmpty(opentopicscore.getScore()) ? "" : opentopicscore.getScore() %></font></td>
																	<td>
																		<% if(!Verify.isEmpty(opentopicscore.getScore())){
																			if(opentopicscore.getScore() >= 60){
																				out.println("<font color='red'>通过</font>");
																			}else{
																				out.println("<font color='red'>未通过</font>");
																			}
																		  }	
																		%>
																	</td>
																</tr>
															<%	} else{ %>
																<tr class="tabletr">
																	<td colspan="5" class="message">暂无相关记录！</td>
																</tr>
															<%	} %>
														</tbody>
													</table>
												</fieldset>
											</div>
										</div>
									</div>
								</div>
							</div>
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
</body>
</html>