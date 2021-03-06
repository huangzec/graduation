<%@page language="java" import="java.util.*,java.util.Map.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Revieworder> list 	= (List<Revieworder>) request.getAttribute("list");
	Department department 		= (Department) request.getSession().getAttribute("department");
	Map<String, Map<String, String>> reviewOrderMap = (Map<String, Map<String, String>>) request.getAttribute("orderMap");
	String message 		= (String) request.getAttribute("message");
 %>
 
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/revieworder/list.do" method="post">
	<div class="text-center">
		<label>毕业评阅评审教师安排预览</label>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/revieworder/grade.do" target="navTab"><span>安排毕业评阅教师</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('order-list')"><span>打印</span></a></li>
		</ul>
	</div>
	<form method="post" action="<%=basePath %>admin/revieworder/addview.do" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		
		<div id="order-list">
			<input type="hidden" name="grade" value="${grade }" /> 
			<input type="hidden" name="professid" value="${professid }" /> 
			<input type="hidden" name="claid" value="${claid }" /> 
			<table class="table" width="100%" layoutH="138">
				<thead>
					<tr>
						<th width="40">*</th>
						<th width="120">学生</th>
						<th width="80">评阅教师</th>
						<th width="80">指导教师</th>
					</tr>
				</thead>
				<tbody>
					<%
						if(null != reviewOrderMap && !reviewOrderMap.isEmpty()) {
							int i = 1;
							Iterator<Entry<String, Map<String, String>>> iter 	= reviewOrderMap.entrySet().iterator();
							while(iter.hasNext()) {
								Entry<String, Map<String, String>> item 	= iter.next();
					%>
					<tr target="sid_teacher" rel="<%//=reodr.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
						<td><%=(i ++) %></td>
						<td>
							<%=(HResponse.formatEmpty(item.getKey(), request) + " 【" + HResponse.formatValue("stuId", item.getKey(), request) + "】 ") %>
						</td>
						<td>
							<%=(HResponse.formatEmpty(item.getValue().get("reviewteacher"), request) + " 【" + HResponse.formatValue("teaId", item.getValue().get("reviewteacher"), request) + "】 ") %>
						</td>
						<td>
							<%=(HResponse.formatEmpty(item.getValue().get("orderteacher"), request) + " 【" + HResponse.formatValue("teaId", item.getValue().get("orderteacher"), request) + "】 ") %>
						</td>
					</tr>
						<% } } %>
					<%
						if(null == reviewOrderMap || reviewOrderMap.isEmpty()) {
					 %>
					<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
						<td clospans="4" style="color:red;text-align:center;">
							<%=(Verify.isEmpty(message) ? "暂无相关记录！" : message) %>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">重新安排</button></div></div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<a class="" href="<%=basePath %>admin/revieworder/add.do?grade=${grade }&professid=${profess}&claid=${claid } " target="navTab"><span>确认安排</span></a>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
