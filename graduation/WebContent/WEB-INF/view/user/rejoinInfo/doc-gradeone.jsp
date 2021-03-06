<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Gradeone gradeone = (Gradeone) request.getAttribute("grade-one");
	Selectfirst selectfirst = (Selectfirst) request.getAttribute("selectfirst");
	Department department 	= (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
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
					答辩信息					
				</h1>				
				<!-- /stat-container -->				
				<div class="widget">										
					<div class="widget-header">
						<i class="icon-folder-open"></i>
						<h3 style="margin-right:2px;">答辩材料</h3>
						<i class="icon-arrow-right"></i>
						<h3>成绩表一</h3>
						<a href="<%=basePath %>user/rejoin/document.do" class="btn btn-type f-right">返回</a>
					</div> <!-- /widget-header -->					
					<%
						if(!Verify.isEmpty(gradeone)){
					 %>
					<!-- startprint1 -->
					<div class="widget-content">
						<div style="text-align: center;">
							<div class="grade-title">
								<% if(selectfirst.getTbtopic().getTopType().equals("1")){ %>
                    				怀化学院本科毕业论文成绩评定表(一)
                    			<% }else{ %>
                    				怀化学院本科毕业设计成绩评定表(一)
                    			<% } %>
                   				<br/>
							</div>
							<% 	if(department.getMajorType().equals("1")) { %>
								<div class="type-profes">
                    				（理工医农学类专业适用）
                    				<br/>
                				</div>
                			<%	}else if(department.getMajorType().equals("2")){ %>
                				<span>
                    				（人文社科类专业适用）
                    				<br/>
                				</span>
                			<%	} else{%>
                				<span><br/></span>
                			<%	} %>
                		</div>
						<table class="table table-bordered">
							<tr>
								<td colspan="5" class="center-td font-type">
									指导教师评分用表
								</td>
							</tr>
                			<tr>
                				<td colspan="5">
                					　毕业论文题目：<%=selectfirst.getTbtopic().getTopName() %>
                				</td>
                			</tr>
                			<tr class="tabletr">
                				<td>姓　名</td>
                				<td width="270px"><%=HResponse.formatEmpty(stuMap.get("stu_Name"), request) %></td>
                				<td>学　号</td>
                				<td colspan="2"><%=HResponse.formatEmpty(stuMap.get("stu_ID"), request) %></td>
                			</tr>
                			<tr class="tabletr">
                				<td>系　别</td>
                				<td><%=department.getDeptName() %></td>
                				<td>专　业</td>
                				<td colspan="2"><%=HResponse.formatEmpty(stuMap.get("pro_Name"), request) %></td>
                			</tr>
							<tr class="tabletr">
								<td width="110px">评价项目</td>
								<td colspan="2">评   价   指   标</td>
								<td width="65px">分值</td>
								<td width="65px">评分</td>
							</tr>
							<tr>
								<td class="center-td">
								  	学习态度<br/>
								  	与工作量
								</td>
								<td class="left-td" colspan="2">
								  	①学习态度认真，自觉遵守纪律；
									②工作作风严谨务实，具有良好的团队精神;
									③工作量饱满，按期圆满完成规定的任务。
								</td>
								<td class="center-td">10</td>
								<td class="center-td">
									<%=gradeone.getGoOne() %>
								</td>
							</tr>
							<tr>
								<td class="center-td">
								  	文献综述与<br/>
								  	  外文翻译
								</td>
								<td colspan="2">
								  	①能独立查阅文献和从事其他形式调研；
								  	②能较好理解课题任务并提出实施方案；
								  	③具有收集、整理各种信息及获取新知识的能力，查阅文献有一定广泛性；
								  	④文献综述撰写规范，外文翻译符合规定要求，译文准确，质量好；
								  	⑤文献综述、外文翻译与研究课题密切相关，文献数量符合相关要求。
								</td>
								<td class="center-td">20</td>
								<td class="center-td">
								  	<%=gradeone.getGoTwo() %>
								</td>
							</tr>
							<% 	if(!department.getMajorType().equals("3")){
									if(department.getMajorType().equals("1")) { %>
								<tr>
								  	<td class="center-td">
								  		研究水平与<br/>
								  		 实际能力
								  	</td>
								  	<td colspan="2">
								  		①能独立开展研究工作；
								  		②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；
								  		③实验设计合理，实验数据准确可靠，理论分析与计算正确；
								  		④有较强的实际动手能力、经济分析能力和现代技术应用能力。
									</td>
								  	<td class="center-td">30</td>
								  	<td class="center-td">
								  		<%=gradeone.getGoThree() %>
								  	</td>
								</tr>
								<tr>
								  	<td class="center-td">
								  		论文撰写<br/>
								  		  质    量
								  	</td>
								  	<td colspan="2">
								  		①论文结构严谨，层次清晰，结论正确，技术用语准确；
								  		②行文流畅，语句通畅；
								  		③论文格式符合规范要求；
								  		④图表完备、整洁，符号统一，编号齐全。
								  	</td>
								  	<td class="center-td">30</td>
								  	<td class="center-td">
								  		<%=gradeone.getGoFour() %>
								  	</td>
								</tr>
								<%	}else if(department.getMajorType().equals("2")){ %>
								  	<tr>
								  		<td class="center-td">
								  			研究水平与<br/>
								  			 实际能力
								  		</td>
								  		<td colspan="2">
								  			①能独立开展研究工作；
								  			②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；
								  			③论点正确、鲜明，阐述清楚，对研究的问题有较强的分析和概括能力，有一定深度；
								  			④论据充分，材料翔实可靠，说服力强。
								  		</td>
								  		<td class="center-td">30</td>
								  		<td class="center-td">
								  			<%=gradeone.getGoThree() %>
								  		</td>
								  	</tr>
								  	<tr>
								  		<td class="center-td">
								  			论文撰写<br/>
								  			  质    量
								  		</td>
										<td class="left-td" colspan="2">
								  			①论文结构严谨，逻辑性强，论述层次清晰；
								  			②语句通畅，语言准确、生动；
								  			③论文格式符合规范要求；
								  			④图表完备、整洁，编号齐全。
								  		</td>
								  		<td class="center-td">30</td>
								  		<td class="center-td">
								  			<%=gradeone.getGoFour() %>
								  		</td>
								  	</tr>
								<%	}%>
								<tr>
								  	<td class="center-td">
								  		学术水平<br/>
								  		  与创新
								  	</td>
								  	<td colspan="2">
								  		①具有一定的学术水平或应用价值；
								  		②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。
								  	</td>
								  	<td class="center-td">10</td>
								  	<td class="center-td">
								  		<%=gradeone.getGoFive() %>
								  	</td>
								</tr>
								<% }else if(department.getMajorType().equals("3")){ %>
									<tr>
								  		<td class="center-td">
								  			设计水平与<br/>
								  			 实际能力
								  		</td>
								  		<td colspan="2">
								  			①能独立开展设计工作；
								  			②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；
								  			③设计方案合理可行，数据准确可靠，论证充分，理论分析与计算正确；
								  			④有较强的实际动手能力、经济分析能力和现代技术应用能力。
								  		</td>
								  		<td class="center-td">30</td>
								  		<td class="center-td">
								  			<%=gradeone.getGoThree() %>
								  		</td>
								  	</tr>
								  	<tr>
								  		<td class="center-td">
								  			设计说明书<br/>
								  			  撰写质量
								  		</td>
										<td class="left-td" colspan="2">
								  			①结构严谨，层次清晰，结论正确，技术用语准确；
								  			②行文流畅，语句通畅；
								  			③格式符合规范要求；
								  			④图表完备、整洁，符号统一，编号齐全。
								  		</td>
								  		<td class="center-td">30</td>
								  		<td class="center-td">
								  			<%=gradeone.getGoFour() %>
								  		</td>
								  	</tr>
								  	<tr>
								  		<td class="center-td">
								  			图纸质量
								  		</td>
										<td class="left-td" colspan="2">
								  			①结构合理，工艺可行；
								  			②图样绘制与技术要求符合国家标准；
								  			③图面质量及工作量符合要求。
								  		</td>
								  		<td class="center-td">30</td>
								  		<td class="center-td">
								  			<%=gradeone.getGoFive() %>
								  		</td>
								  	</tr>
								  	<tr>
								  		<td class="center-td">
								  			学术水平<br/>
								  			  与创新
								  		</td>
								  		<td colspan="2">
								  			①具有一定的学术水平或应用价值；
								  			②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。
								  		</td>
								  		<td class="center-td">10</td>
								  		<td class="center-td">
								  			<%=gradeone.getGoSix() %>
								  		</td>
									</tr>
								<%	} %>
								<tr class="tabletr">
								  	<td colspan="5">
										<div class="rfloat score">
									  		总分：&nbsp;&nbsp;&nbsp;<%=gradeone.getGoAll() %>
									  	</div>
								  	</td>
								</tr>
								<tr>
									<td colspan="5">
										指导教师评定意见：<br/><br/>
										　　<%=gradeone.getContent() %>
									</td>
							</tr>	
						</table>
					</div>
					<!-- endprint1 -->
					<div style="float: right; margin-top: 10px;">
							<a class="btn btn-primary" onclick=preview(1)>打印</a>
							&nbsp;&nbsp;
							<a class="btn btn-info" href="<%=basePath%>user/rejoin/document.do">返回</a>
						</div>
					<%}else{ %>
						<div class="widget-content">
							<div style="text-align: center; color: red; font-size: 16px;">
								暂无相关记录！
							</div>
						</div>
					<%} %>
				</div>
			</div> <!-- /widget-content -->	
		</div> <!-- /span9 -->						
	</div> <!-- /row -->	
</div> <!-- /content -->	
<div id="footer">	
	<div class="container">				
		<hr />
		<jsp:include page="../common/footer.jsp" flush="true" />
	</div> <!-- /container -->	
</div> <!-- /footer -->
<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath%>js/printview.js"></script>
  </body>
</html>