<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*,com.mvc.common.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	Gradeone gradeone      = (Gradeone) request.getAttribute("gradeone");
	Gradetwo gradetwo      = (Gradetwo) request.getAttribute("gradetwo");
	Gradethree gradethree  = (Gradethree) request.getAttribute("gradethree");
	Gradeall gradeall      = (Gradeall) request.getAttribute("gradeall");
	Department dept        = (Department) request.getSession().getAttribute("dept");
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
									<h3>毕业答辩成绩</h3>
									<a href="<%=basePath %>user/rejoin/grade.do" class="btn btn-type f-right">返回</a>
								</div>
								<div class="widget-content">
									<div class="tabbable">
										<div class="tab-content">
											<div class="tab-pane active" id="1">
												<fieldset>
													<button  class="marginright marginleft btn btn-success" id="gradeone">
														<i class="icon-star"></i> 成绩表一
													</button>
													<button class="marginright btn btn-success" id="gradetwo">
														<i class="icon-star"></i> 成绩表二 
													</button>
													<button class="marginright btn btn-success" id="gradethree"> 
														<i class="icon-star"></i> 成绩表三 
													</button>
													<button class="btn btn-success" id="gradeall"> 
														<i class="icon-star"></i> 综合成绩
													</button>
													<div id="one" class="hidden gradetable">
														<table class="table table-bordered">
															<thead>
																<tr class="tabletr">
																	<td colspan="7" style="text-align: center;">成绩表 一 各项成绩</td>
																</tr>
																<tr class="tabletr">
																	<td style="text-align: center;">项一</td>
																	<td style="text-align: center;">项二</td>
																	<td style="text-align: center;">项三</td>
																	<td style="text-align: center;">项四</td>
																	<td style="text-align: center;">项五</td>
																	<%if(dept.getMajorType().equals("3")){ %>
																		<td style="text-align: center;">项六</td>
																	<%} %>
																	<td style="text-align: center;">合计</td>
																</tr>
															</thead>
															<tbody>
																<%	if(!Verify.isEmpty(gradeone)){ %>
																	<tr>
																		<td style="text-align: center;"><%=gradeone.getGoOne() %></td>
																		<td style="text-align: center;"><%=gradeone.getGoTwo() %></td>
																		<td style="text-align: center;"><%=gradeone.getGoThree() %></td>
																		<td style="text-align: center;"><%=gradeone.getGoFour() %></td>
																		<td style="text-align: center;"><%=gradeone.getGoFive() %></td>
																		<%if(dept.getMajorType().equals("3")){ %>
																			<td style="text-align: center;"><%=gradeone.getGoSix() %></td>
																		<%} %>
																		<td style="text-align: center;"><%=gradeone.getGoAll() %></td>
																	</tr>
																<%	}else{ %>
																	<tr><td colspan="7" style="text-align: center; color: red;">暂无相关记录！</td></tr>
																<%	} %>
															</tbody>
														</table>
													</div>
													<div id="two" class="hidden gradetable">
														<table class="table table-bordered">
															<thead>
																<tr class="tabletr">
																	<td colspan="7" style="text-align: center;">成绩表 二 各项成绩</td>
																</tr>
																<tr class="tabletr">
																	<td style="text-align: center;">项一</td>
																	<td style="text-align: center;">项二</td>
																	<td style="text-align: center;">项三</td>
																	<td style="text-align: center;">项四</td>
																	<td style="text-align: center;">项五</td>
																	<%if(dept.getMajorType().equals("3")){ %>
																		<td style="text-align: center;">项六</td>
																	<%} %>
																	<td style="text-align: center;">合计</td>
																</tr>
															</thead>
															<tbody>
																<%	if(!Verify.isEmpty(gradetwo)){ %>
																	<tr>
																		<td style="text-align: center;"><%=gradetwo.getGtOne() %></td>
																		<td style="text-align: center;"><%=gradetwo.getGtTwo() %></td>
																		<td style="text-align: center;"><%=gradetwo.getGtThree() %></td>
																		<td style="text-align: center;"><%=gradetwo.getGtFour() %></td>
																		<td style="text-align: center;"><%=gradetwo.getGtFive() %></td>
																		<%if(dept.getMajorType().equals("3")){ %>
																			<td style="text-align: center;"><%=gradetwo.getGtSix() %></td>
																		<%} %>
																		<td style="text-align: center;"><%=gradetwo.getGtAll() %></td>
																	</tr>
																<%	}else{ %>
																	<tr><td colspan="7" style="text-align: center; color: red;">暂无相关记录！</td></tr>
																<%	} %>
															</tbody>
														</table>
													</div>
													<div id="three" class="hidden gradetable">
														<table class="table table-bordered">
															<thead>
																<tr class="tabletr">
																	<td colspan="7" style="text-align: center;">成绩表 三 各项成绩</td>
																</tr>
																<tr class="tabletr">
																	<td style="text-align: center;">项一</td>
																	<td style="text-align: center;">项二</td>
																	<td style="text-align: center;">项三</td>
																	<td style="text-align: center;">项四</td>
																	<td style="text-align: center;">项五</td>
																	<%if(dept.getMajorType().equals("3")){ %>
																		<td style="text-align: center;">项六</td>
																	<%} %>
																	<td style="text-align: center;">合计</td>
																</tr>
															</thead>
															<tbody>
																<%	if(!Verify.isEmpty(gradethree)){ %>
																	<tr>
																		<td style="text-align: center;"><%=gradethree.getGtrOne() %></td>
																		<td style="text-align: center;"><%=gradethree.getGtrTwo() %></td>
																		<td style="text-align: center;"><%=gradethree.getGtrThree() %></td>
																		<td style="text-align: center;"><%=gradethree.getGtrFour() %></td>
																		<td style="text-align: center;"><%=gradethree.getGtrFive() %></td>
																		<%if(dept.getMajorType().equals("3")){ %>
																			<td style="text-align: center;"><%=gradethree.getGtrSix() %></td>
																		<%} %>
																		<td style="text-align: center;"><%=gradethree.getGtrAll() %></td>
																	</tr>
																<%	}else{ %>
																	<tr><td colspan="7" style="text-align: center; color: red;">暂无相关记录！</td></tr>
																<%	} %>
															</tbody>
														</table>
													</div>
													<div id="all" class="hidden gradetable">
														<table class="table table-striped table-bordered">
															<thead>
																<tr class="tabletr">
																	<td>表一成绩(30%)</td>
																	<td>表二成绩(30%)</td>
																	<td>表三成绩(40%)</td>
																	<td>综合成绩</td>
																</tr>
															</thead>
															<tbody>
																<%	if(!Verify.isEmpty(gradeall)){ %>
																	<tr class="tabletr">
																		<td>
																			<%=gradeone.getGoAll() %>
																		</td>
																		<td>
																			<%=gradetwo.getGtAll() %>
																		</td>
																		<td>
																			<%=gradethree.getGtrAll() %>
																		</td>
																		<td>
																			<%=gradeall.getGaGrade() %>
																		</td>
																	</tr>
																<%	}else{ %>
																	<tr>
																		<td colspan="4" style="text-align: center; color: red;">
																			暂无相关记录！
																		</td>
																	</tr>
																<%	} %>
															</tbody>
														</table>
													</div>
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
	<script src="<%=basePath%>js/rejoin.js"></script>
</body>
</html>