<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbgrade> gradeList 		= (List<Tbgrade>) request.getAttribute("gradeList");
	String curGrade 				= (String) request.getParameter("grade");
	List<Student> stuList 			= (List<Student>) request.getAttribute("stuList");
	List<Selectfirst> sefList 		= (List<Selectfirst>) request.getAttribute("sefList");
	List<Opentopicinfo> infoList 	= (List<Opentopicinfo>) request.getAttribute("optinfoList");
	List<Opentopicscore> scoreList 	= (List<Opentopicscore>) request.getAttribute("optscoreList");
	int canjiadabian 	= 0;
	int tongguodabian 	= 0;
	int weidabian 		= 0;
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
					<i class="icon-retweet"></i>
					学生开题答辩概况					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-content">
						<i class="icon-hand-right"></i>请选择年级
						<select id="cur-grade" data-cur="<%=HResponse.formatEmpty(curGrade, request) %>">
							<option value="">-- 请选择 --</option>
							<%
								if(!Verify.isEmpty(gradeList)) {
									for(int i = 0; i < gradeList.size(); i ++) {
										Tbgrade grade = gradeList.get(i);
										%>
										<option value="<%=grade.getGraId() %>">--<%=grade.getGraNumber() %>--</option>
										<%
									}
								}
							 %>
						</select> 
					</div> <!-- /widget-content -->					
				</div> <!-- /widget -->
				<div class="widget widget-table">										
					<div class="widget-header">
						<i class="icon-th-list"></i>
						<h3>指导的学生开题概况表</h3>
						<span style="float: right;margin-right: 10px;color: red;"><b></b></span>
					</div> <!-- /widget-header -->					
					<div class="widget-content">
						<form action="<%=basePath %>" method="post" id="">
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<td>#</td>
										<td>学生</td>
										<td>答辩状况</td>
									</tr>
								</thead>
								<tbody>
									<%
										if(!Verify.isEmpty(sefList)) {
											for(int i = 0; i < sefList.size(); i ++) {
												Selectfirst se = sefList.get(i);
												boolean isHaveOpentopic = false;
												Double opentopicScore 	= (double) 0.0;
												if((!Verify.isEmpty(infoList))) {
													for(int j = 0; j < infoList.size(); j ++) {
														Opentopicinfo opt = infoList.get(j);
														if(opt.getStuId().equals(se.getStuId())) {
															//参加答辩
															isHaveOpentopic = true;
															if(!Verify.isEmpty(scoreList)) {
																for(int k = 0; k < scoreList.size(); k ++) {
																	Opentopicscore opts = scoreList.get(k);
																	if(opts.getStudentId().equals(se.getStuId())) {
																		opentopicScore = opts.getScore();
																		break;
																	}
																}
																
															}
															canjiadabian ++;
															break;
														}
													}
												}
												
											%>
											<tr>
												<td><%=(i + 1) %></td>
												<td width="30%">
													<%=(se.getStuId() + "【" + HResponse.formatValue("stuId", se.getStuId(), request) + "】") %>
												</td>
												<td>
													<%
														if(isHaveOpentopic) {
															out.print("<span style=\"color: red;\">已参加答辩  成绩： " + opentopicScore + "</span>");
															if(opentopicScore > 60) {
																tongguodabian ++;
																out.print(" <span style=\"color: red;\">答辩通过</span>");
															} else {
																out.print(" 答辩不通过");
															}
														}else {
															out.print("未参加答辩 ");
														}
													 %>
												</td>
											</tr>
											<%
											}
											%>
											<tr>
												<td colspan="3" style="color: red; text-align: center;">
													总人数：<%=sefList.size() %> 人，
													其中：参加答辩 <%=canjiadabian %> 人 ：
													答辩通过 <%=tongguodabian %> 人 ，
													答辩未通过 <%=(canjiadabian - tongguodabian) %> 人；
													未参加答辩 <%=(Verify.isEmpty(sefList) ? 0 : (sefList.size() - canjiadabian)) %> 人；
												</td>
											</tr>
											<%
										}
									 %>
									 <%
									 	if(Verify.isEmpty(sefList)) {
							 		 %>
										<tr>
											<td colspan="3" style="color: red; text-align: center;">暂无指导学生</td>
										</tr>									 		
							 		 <%
									 	}
									  %>
								</tbody>
							</table>
						</form>
					</div> <!-- /widget-content -->					
				</div>
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
<script src="<%=basePath %>js/site-judge.js"></script>
  </body>
</html>