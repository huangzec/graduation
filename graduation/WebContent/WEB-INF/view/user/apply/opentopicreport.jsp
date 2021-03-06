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
	List<Selectfirst> list = (List<Selectfirst>) request.getAttribute("list");
	Tbtopic tbtopic = (Tbtopic) request.getAttribute("topic");
	Teacher teacher = (Teacher) request.getAttribute("teacher");
	Department department = (Department) request.getSession().getAttribute("dept");
	Map<String, String> stuMap = (Map<String, String>) request.getAttribute("student_map");
	Map<String, String> professionMap = (Map<String, String>) request.getAttribute("profession_map");
	Taskdoc taskdoc 	= (Taskdoc) request.getAttribute("taskdoc");
	Selectfirst selectfirst = (Selectfirst) request.getAttribute("selectfirst");
 %>	
 							<div class="widget">										
								<div class="widget-header">
									<i class="icon-edit"></i><h3>填写开题报告书</h3>
								</div> <!-- /widget-content -->					
							</div> <!-- /widget -->
							<div class="widget widget-table">										
								<div class="widget-content" style="text-align: center;">
									<br/>
									<div class="line-height">
										<img alt="" src="<%=basePath %>images/hhxylogo.jpg">
									</div>
									<div class="line-height">
										<h2>本科生毕业<%
														if(!Verify.isEmpty(selectfirst) && !Verify.isEmpty(selectfirst.getTbtopic())) {
															if(selectfirst.getTbtopic().getTopType().equals("1")){
																%>论文<%
															}else {
																%>设计<%
															}
														}
													 %>
											<br/>
											开 题 报 告 书
										</h2>
									</div>
									<div class="left-100">
										<fieldset>
											<div class="control-group height-60">											
												<label class="control-label">题　　目 </label>
												<div class="controls">
													<input type="text" class="input-mlarge" readonly="true" value="<%=HResponse.formatValue("topic", (null == selectfirst ? "" : selectfirst.getTbtopic().getTopId()), request) %>" />
												</div>			
											</div>
											<div class="control-group height-60">											
												<label class="control-label">学生姓名</label>
												<div class="controls">
													<input type="text" class="input-mlarge" readonly="true" value="<%=request.getSession().getAttribute("user_name") %>" />
												</div>			
											</div>
											<div class="control-group height-60">											
												<label class="control-label">学　　号</label>
												<div class="controls">
													<input type="text" class="input-mlarge" readonly="true" value="<%=request.getSession().getAttribute("user_id") %>" />
												</div>			
											</div>
											<div class="control-group height-60">											
												<label class="control-label">系　　别</label>
												<div class="controls">
													<input type="text" class="input-mlarge" readonly="true" value="<%=department.getDeptName() %>" />
												</div>			
											</div>
											<div class="control-group height-60">											
												<label class="control-label">专　　业 </label>
												<div class="controls">
													<input type="text" class="input-mlarge" readonly="true" value="<%=professionMap.get("pro_Name") %>" />
												</div>			
											</div>
											<div class="control-group height-60">											
												<label class="control-label">指导教师</label>
												<div class="controls">
													<input type="text" class="input-mlarge" readonly="true" value="<%=HResponse.formatValue("teacher", (null == selectfirst ? "" : selectfirst.getTeaId()), request) %>" />
												</div>			
											</div>
											<div class="control-group height-60">											
												<label class="controls" style="color: red;">${message }</label>
											</div>
											<div class="">											
												<label class=""><%=HResponse.formatDateTime(HResponse.formatDate(new Date())) %></label>
											</div>
										</fieldset>
									</div>
									<hr/>
									<div class="widget-content">
											<input type="hidden" name="stuid" value="" />
											<input type="hidden" name="teaid" value="" />
											<table class="table  table-bordered">
												<tr>
													<td width="100">
														<%
															if(!Verify.isEmpty(selectfirst) && !Verify.isEmpty(selectfirst.getTbtopic())) {
																if(selectfirst.getTbtopic().getTopType().equals("1")){
																	%>论文<%
																}else {
																	%>设计<%
																}
															}
														 %>题目
													</td>
													<td sytle="">
														<%=HResponse.formatValue("topic", (null == selectfirst ? "" : selectfirst.getTbtopic().getTopId()), request) %>
													</td>
													
												</tr>
												<tr>
													<td colspan="2">
														<div>
															一、选题的目的、意义及相关研究动态和自己的见解：
															<script id="meaning" name="meaning" type="text/plain">
																
															</script>
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div>
															二、课题的主要内容：
															<script id="maincontent" name="content" type="text/plain">
																
															</script>
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div>
															三、研究方法、设计方案或论文撰写提纲：
															<script id="research" name="research" type="text/plain">
																
															</script>
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div>
															四、完成期限和预期进度：
															<script id="deadline" name="deadline" type="text/plain">
																
															</script>
														</div>				
													</td>
												</tr>
												<tr>
													<td colspan="6">
														<div>
															五、主要参考文献（不少于10篇）：
															<script id="references" name="references" type="text/plain">
																
															</script>
														</div>								
													</td>
												</tr>
												<tr>
													<td colspan="6">
														<div>
															六、指导教师意见：
															<!-- <script id="teacher_view" name="teacher_view" type="text/plain">
																
															</script> -->
														</div>
																		
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<table class="table">
															<tr>
																<td colspan="4" style="text-align: center;">七、开题报告会纪要</td>
															</tr>
															<tr>
																<td>时间</td>
																<td></td>
																<td>地点</td>
																<td></td>
															</tr>
															<tr>
																<td>与会人员</td>
																<td colspan="3">
																	<table class="table">
																		<tr>
																			<td>姓名</td>
																			<td>职务（职称）</td>
																			<td>姓名</td>
																			<td>职务（职称）</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td></td>
																			<td></td>
																			<td></td>
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td colspan="4">
																	<div style="text-align: center;">
																		会议记录摘要：
																	</div>
																	<div class="text-left height-100">
																		
																	</div>
																	<div class="text-right">
																		会议主持人： <br/>
																		会议记录人： <br/>
																		年  月  日 
																	</div>
																</td>
															</tr>
															<tr>
																<td colspan="4" style="text-align: center;">
																	<div style="text-align: center;">
																		八、开题答辩小组意见：
																	</div>
																	<div class="text-left height-100">
																		
																	</div>
																	<div class="text-right">
																		负责人签名： <br/>
																		年  月  日 
																	</div>
																</td>
															</tr>
															<tr>
																<td colspan="4" style="text-align: center;">
																	<div style="text-align: center;">
																		九、系(部)意见：
																	</div>
																	<div class="text-left height-100">
																		
																	</div>
																	<div class="text-right">
																		负责人签名： <br/>
																		 单位（盖章）<br/>
																		年  月  日 
																	</div>
																</td>
															</tr>
														</table>																		
													</td>
												</tr>
											</table>
									</div> <!-- /widget-content -->	
								</div> <!-- /widget-header -->
								<div class="form-actions text-right">
									<button type="submit" id="apply-btn" class="btn btn-primary">提交申请</button> 
									<button type="button" class="btn openreportpre">返回上一步</button>
								</div>			
							</div>

