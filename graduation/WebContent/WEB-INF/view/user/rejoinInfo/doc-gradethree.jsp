<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*, com.mvc.common.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Gradethree gradethree = (Gradethree) request.getAttribute("grade-three");
	Graduateinfo graduateinfo = (Graduateinfo) request.getAttribute("graduate-info");
	Selectfirst selectfirst = (Selectfirst) request.getAttribute("selectfirst");
	Teacher teacher = (Teacher) request.getAttribute("teacher");
	Department department 	= (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
 %>
<jsp:include page="../common/header.jsp" flush="true" />
</head>
<body background="<%=basePath %>cscon/body-bg.png">

	<div style="padding-top: 3%;">
		<div class="container">
			<!-- /span3 -->
			<div style="text-align: center; margin-bottom: 15px;">
				<a class="btn btn-primary" onclick=preview(1)>打印</a> &nbsp;&nbsp;
				<a class="btn btn-info" href="<%=basePath%>user/rejoin/document.do">返回</a>
			</div>
			<div class="widget-table" style="width: 70%; margin-left: 169px; margin-bottom: 20px;">
				<%
					if(!Verify.isEmpty(gradethree) && !Verify.isEmpty(graduateinfo)){
				%>
				<!-- startprint1 -->
				<div class="widget-content" id="printarea">
					<div style="text-align: center;">
						<div class="grade-title">
							<% if(selectfirst.getTbtopic().getTopType().equals("1")){ %>
                    				怀化学院本科毕业论文成绩评定表(三)
                    			<% }else{ %>
                    				怀化学院本科毕业设计成绩评定表(三)
                    			<% } %>
							<br />
						</div>
						<% 	if(department.getMajorType().equals("1")) { %>
						<div class="type-profes">
							（理工医农学类专业适用）
							<br />
						</div>
						<%	}else if(department.getMajorType().equals("2")){ %>
						<span> （人文社科类专业适用） <br /> </span>
						<%	}else{out.println("<span><br/></span>");} %>
					</div>
					<table class="table table-bordered" style="font-size: 14px;">
						<tr>
							<td colspan="9" class="center-td font-type" style="border-top: 1px solid #D5D5D5">答辩小组老师评分用表</td>
						</tr>
						<tr>
							<td colspan="9">一、毕业论文（设计）答辩记录</td>
						</tr>
						<tr>
							<td colspan="9">毕业论文题目：<%=selectfirst.getTbtopic().getTopName() %></td>
						</tr>
						<tr class="tabletr">
							<td>作者姓名</td>
							<td colspan="2" width="90px"><%=HResponse.formatEmpty(stuMap.get("stu_Name"), request) %></td>
							<td>所属系（部）、专业、年级</td>
							<td colspan="4" width="390px">
								<%=department.getDeptName() %>系（部）
								<%=HResponse.formatEmpty(stuMap.get("pro_Name"), request) %>专业
								<%=HResponse.formatEmpty(stuMap.get("stu_ID"), request) %>年级
							</td>
						</tr>
						<tr class="tabletr">
							<td>指导教师<br />姓名、职称</td>
							<td colspan="8">
								<%=teacher.getTeaName() %>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<%=HResponse.formatValue("teapos", teacher.getTeaPos(), request) %>
							</td>
						</tr>
						<tr>
							<td colspan="9" class="center-td font-type">答 辩 会 纪 要</td>
						</tr>
						<tr class="tabletr">
							<td colspan="2">时间</td>
							<td colspan="2"><%=graduateinfo.getGdiDate() %></td>
							<td colspan="3">地点</td>
							<td colspan="2"><%=graduateinfo.getGdiPlace() %></td>
						</tr>
						<tr class="tabletr">
							<td class="center-td" rowspan="5">答辩小组成员</td>
							<td colspan="2">姓 名</td>
							<td colspan="2">职务（职称）</td>
							<td colspan="2">姓 名</td>
							<td colspan="2">职务（职称）</td>
						</tr>
						<%	int i = 0;
							for(int k = 0; k < 4; k++){
						%>
							<tr class="tabletr">
							<%	for(int j = 0; j < 2; j++){
									String[] judgeteacher = StringUtil.splitString(graduateinfo.getJudge());
									if(i < judgeteacher.length){
							%>
							<td colspan="2"><%=Verify.isEmpty(HResponse.formatValue("tea", judgeteacher[i], request)) ? "" : HResponse.formatValue("tea", judgeteacher[i], request) %></td>
							<td colspan="2"><%=HResponse.formatValue("teapos", HResponse.formatValue("pos", judgeteacher[i], request), request) %></td>
							<%		i++;
								}else{ 
							%>
							<td colspan="2">&nbsp;</td>
							<td colspan="2">&nbsp;</td>
							<%		}
								} 
							%>
						</tr>
						<%	} %>
						<tr>
							<td colspan="9">
								答辩中提出的主要问题及回答的简要情况记录：
								<br /><br /><br /><br /><br /><br />
								<br /><br /><br /><br /><br /><br />
								<div class="f-right" style="margin-right: 50px; margin-bottom: 10px;">
									会议主持人：<%=HResponse.formatValue("tea",graduateinfo.getHeaderman(), request) %>
									<br />
									记 录 人： HResponse
									<br /><%=HResponse.formatDateTime(graduateinfo.getGdiDate()) %>
								</div>
							</td>
						</tr>
					</table>
					<table class="table table-bordered" style="font-size: 14px;">
						<tr>
							<td colspan="5">二、毕业论文答辩小组成绩评定</td>
						</tr>
						<tr class="tabletr">
							<td width="110px">评价项目</td>
							<td colspan="2">评 价 指 标</td>
							<td width="65px">分值</td>
							<td width="65px">评分</td>
						</tr>
						<tr>
							<td class="center-td">选题质量</td>
							<td class="left-td" colspan="2">
								①选题符合专业培养目标要求； ②与科学研究、工程或生产实际紧密结合； ③有一定创新性和应用价值； ④难度适中。
							</td>
							<td class="center-td">10</td>
							<td class="center-td"><%=gradethree.getGtrOne() %></td>
						</tr>
						<%	if(!department.getMajorType().equals("3")){ %>
							<tr>
								<td class="center-td">研究水平与<br />实际能力</td>
								<td colspan="2">
									①能独立开展研究工作； ②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；
									③实验设计合理，实验数据准确可靠，理论分析与计算正确； ④有较强的实际动手能力、经济分析能力和现代技术应用能力。
								</td>
								<td class="center-td">30</td>
								<td class="center-td"><%=gradethree.getGtrThree() %></td>
							</tr>
							<tr>
								<td class="center-td">论文撰写<br />质 量</td>
								<td colspan="2">
									①论文结构严谨，层次清晰，结论正确，技术用语准确； ②行文流畅，语句通畅； ③论文格式符合规范要求；
									④图表完备、整洁，编号齐全。
								</td>
								<td class="center-td">30</td>
								<td class="center-td"><%=gradethree.getGtrFour() %></td>
							</tr>
							<tr>
								<td class="center-td">学术水平<br />与创新</td>
								<td colspan="2">
									①具有一定的学术水平或应用价值； ②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。
								</td>
								<td class="center-td">10</td>
								<td class="center-td"><%=gradethree.getGtrFive() %></td>
							</tr>
							<tr>
								<td class="center-td">答辩</td>
								<td colspan="2">
									①能简明扼要阐述设计思想和内容，思路清晰，语言表达准确、顺畅，分析归纳科学、合理，结论严谨；
									②回答问题有理论根据，基本概念清楚，逻辑性强，能抓住要点，对主要问题回答准确、有深度； ③仪态端庄，自然得体。
								</td>
								<td class="center-td">20</td>
								<td class="center-td"><%=gradethree.getGtrFive() %></td>
							</tr>
						<%	}else if(department.getMajorType().equals("3")){ %>
							<tr>
								<td class="center-td">设计水平与<br/>实际能力</td>
								<td colspan="2">
									①能独立开展设计工作；
									②能熟练掌握和运用所学专业基本理论、基本知识和基本技能分析解决相关理论和实际问题；
									③设计方案合理可行，数据准确可靠，论证充分，理论分析与计算正确；
									④有较强的实际动手能力、经济分析能力和现代技术应用能力。
								</td>
								<td class="center-td">20</td>
								<td class="center-td"><%=gradethree.getGtrTwo() %></td>
							</tr>
							<tr>
								<td class="center-td">设计说明书<br />撰写质 量</td>
								<td colspan="2">
									①结构严谨，层次清晰，结论正确，技术用语准确；
									②行文流畅，语句通畅；
									③格式符合规范要求；
									④图表完备、整洁，符号统一，编号齐全。
								</td>
								<td class="center-td">20</td>
								<td class="center-td"><%=gradethree.getGtrThree() %></td>
							</tr>
							<tr>
								<td class="center-td">图纸质量</td>
								<td colspan="2">
									①结构合理，工艺可行；
									②图样绘制与技术要求符合国家标准；
									③图面质量及工作量符合要求。
								</td>
								<td class="center-td">20</td>
								<td class="center-td"><%=gradethree.getGtrFour() %></td>
							</tr>
							<tr>
								<td class="center-td">学术水平<br />与创新</td>
								<td colspan="2">
									①具有一定的学术水平或应用价值；②对与课题相关的理论或实际问题有较深刻的认识，有新的见解，有一定的创新。
								</td>
								<td class="center-td">10</td>
								<td class="center-td"><%=gradethree.getGtrFive() %></td>
							</tr>
							<tr>
								<td class="center-td">答辩</td>
								<td colspan="2">
									①能简明扼要阐述设计思想和内容，思路清晰，语言表达准确、顺畅，分析归纳科学、合理，结论严谨；
									②回答问题有理论根据，基本概念清楚，逻辑性强，能抓住要点，对主要问题回答准确、有深度； ③仪态端庄，自然得体。
								</td>
								<td class="center-td">20</td>
								<td class="center-td"><%=gradethree.getGtrSix() %></td>
							</tr>
						<%	} %>
						<tr class="tabletr">
							<td colspan="5">
								<div class="rfloat score">
									总分：&nbsp;&nbsp;&nbsp;<%=gradethree.getGtrAll() %>
								</div>
							</td>
						</tr>
						<tr>
							<td class="center-td">答辩小组意见</td>
							<td colspan="4">
								评语：<br /><br /><br /><br /><br /><br /><br />
								<div class="f-right"style="margin-right: 50px; margin-bottom: 10px;">
									答辩负责人：<%=HResponse.formatValue("tea",graduateinfo.getHeaderman(), request) %>
									<br /><br />年 月 日
								</div>
							</td>
						</tr>
						<tr>
							<td class="center-td">系（部）学位委员会意见</td>
							<td colspan="4">
								评语：<br /><br /><br /><br /><br /><br /><br />
								最终评定成绩：<br /><br />
								最终评定等级：<br /><br />
								负责人（签名）： 系（部）（公章） 年 月 日
							</td>
						</tr>
					</table>
				</div>
				<div style="margin-left: 20px; margin-top: 5px;">
						注：毕业论文（设计）最终评定成绩由系（部）学位委员会根据指导教师评定成绩（30％），评阅教师评定成绩（30%）和答辩成绩（40％）综合确定。
				</div>
				<!-- endprint1 -->
				<%}else{ %>
				<div class="widget-content">
					<div style="text-align: center; color: red; font-size: 16px;">暂无相关记录！</div>
				</div>
				<%} %>
			</div>
		</div>
	</div>
	<!-- Le javascript
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath %>js/printview.js"></script>
</body>
</html>