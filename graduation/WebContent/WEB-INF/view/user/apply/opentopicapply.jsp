<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath %>vendor/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" /> 
<link href="<%=basePath %>css/style.css" rel="stylesheet" /> 
<link href="<%=basePath %>vendor/bootstrap-switch/css/highlight.css" rel="stylesheet" />
<link href="<%=basePath %>vendor/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" /> 
<%
	List<Selectfirst> list 	= (List<Selectfirst>) request.getAttribute("list");
	Tbtopic tbtopic 		= (Tbtopic) request.getAttribute("topic");
	Teacher teacher 		= (Teacher) request.getAttribute("teacher");
	Department department 	= (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Map<String, String> professionMap = (Map<String, String>) request.getAttribute("profession_map");
	Taskdoc taskdoc 		= (Taskdoc) request.getAttribute("taskdoc");
	Selectfirst selectfirst = (Selectfirst) request.getAttribute("selectfirst");
 %>	
 							<div class="widget">										
								<div class="widget-header">
									<i class="icon-edit"></i><h3>填写开题答辩申请表</h3>
								</div> <!-- /widget-content -->					
							</div> <!-- /widget -->
							<div class="widget widget-table">										
								<div class="widget-content" style="text-align: center;font-size: 20px;font-weight: bold;">
									<div class="line-height">怀化学院<%=department.getDeptName() %></div>
									<div class="line-height">
										<b>
											毕业<%
												if(!Verify.isEmpty(selectfirst) && !Verify.isEmpty(selectfirst.getTbtopic())) {
													if(selectfirst.getTbtopic().getTopType().equals("1")){
														%>论文<%
													}else {
														%>设计<%
													}
												}
											 %>开题答辩申请表
										</b>
									</div>
									<div class="text-right text-font">申请时间：<%=HResponse.formatDateTime(HResponse.formatDate(new Date())) %></div>
								</div> <!-- /widget-header -->					
								<div class="widget-content">
										<input type="hidden" name="stuid" value="" />
										<input type="hidden" name="teaid" value="" />
										<table class="table  table-bordered">
											<tr>
												<td>系部</td>
												<td colspan="2"><%=department.getDeptName() %></td>
												<td>专业</td>
												<td colspan="2"><%=professionMap.get("pro_Name") %></td>
											</tr>
											<tr>
												<td>学生姓名</td>
												<td>
													<%=request.getSession().getAttribute("user_name") %>
												</td>
												<td>学号</td>
												<td><%=request.getSession().getAttribute("user_id") %></td>
												<td>指导老师</td>
												<td><%=HResponse.formatValue("teacher", (null == selectfirst ? "" : selectfirst.getTeaId()), request) %></td>
											</tr>
											<tr>
												<td>课题名称</td>
												<td colspan="5">
													<%=HResponse.formatValue("topic", (null == selectfirst ? "" : selectfirst.getTbtopic().getTopId()), request) %>
													<input type="hidden" name="topic_id" value="<%=(null == selectfirst ? "" : selectfirst.getTbtopic().getTopId()) %>" />
												</td>
											</tr>
											<tr>
												<td colspan="2">申请开题答辩时间</td>
												<td colspan="4">
													<input type="text" id="openTime" name="open_time" value="" />
												</td>
											</tr>
											<tr>
												<td colspan="6">
													<div>
														对课题的理解：
														<script id="knowing" name="knowing" type="text/plain">
															
														</script>
													</div>				
												</td>
											</tr>
											<tr>
												<td colspan="6">
													<div>
														指导老师意见:
														<!-- <script id="teacher_idea" name="teacher_idea" type="text/plain">
												<%=StringUtil.decodeHtml(Verify.isEmpty(taskdoc) ? "" : taskdoc.getTdRefMaterial()) %>
											</script> -->
													</div>								
												</td>
											</tr>
											<tr>
												<td colspan="6">
													<div>
														论文管理委员会意见：
														<!-- <script id="paper_idea" name="paper_idea" type="text/plain">
															
														</script> -->
													</div>
																	
												</td>
											</tr>
											<tr>
												<td colspan="6">
													<div>
														系部意见（主管教学主任）：
														<!-- <script id="department_idea" name="department_idea" type="text/plain">
															
														</script> -->
													</div>
																	
												</td>
											</tr>
											
											
										</table>
								</div> <!-- /widget-content -->	
								<div class="text-right" style="margin-top: 5px; margin-right: 150px;">
									<%=department.getDeptName() %><br/>
									<%=HResponse.formatDateTime(HResponse.formatDate(new Date())) %>
								</div>
								<div style="float: right;">
									<button class="btn btn-primary applypre" id="printbtn" type="button">上一步</button>
			  						<button class="btn btn-info applynext" id="sendbtn" type="button">下一步</button>
								</div>				
							</div>

