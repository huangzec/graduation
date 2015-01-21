<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Room> list 	= (List<Room>) request.getAttribute("list");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/opentopicinfo/addview.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/opentopicinfo/addview.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					安排毕业答辩
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/graduateinfo/addview.do" target="navTab"><span>安排毕业答辩</span></a></li>
			<li class="line">line</li>
			<li>教室列表</li>
		</ul>
	</div>
	<div id="opentopicinfo">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="20">*</th>
					<th width="120">教室编号</th>
					<th width="120">教室名称</th>
					<th width="150">是否可用</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++ ) {
							Room room = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=room.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=room.getNumber() %></td>
					<td><%=room.getName() %></td>
					<td><%=HResponse.formatValue("usable", room.getUsable(), request) %></td>
					<td>
						<a class="button" target="navTab" href="<%=basePath %>admin/graduateinfo/orderroom.do?id=<%=room.getId() %>" rel="orderroom">
							<span>安排该教室</span>
						</a>
					</td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(list)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td colspan="5" style="color:red;text-align:center;">暂无可用的教室，请先添加教室！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
