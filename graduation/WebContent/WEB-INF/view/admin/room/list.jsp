<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Room> list = (List<Room>) request.getAttribute("list");
 %>

<form id="pagerForm" method="post" action="<%=basePath %>admin/room/list.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
				</td>
			</tr>
		</table>
		<div class="subBar">
		</div>
	</div>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/room/addview.do" target="navTab" rel="addroom"><span>添加教室</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/room/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<div id="teacherlist">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="120" align="center">教室编号</th>
					<th width="150" align="center">教室名称</th>
					<th width="80" align="center">是否可用</th>
					<th width="120" align="center">创建时间</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && 0 < list.size()) {
						for(int i = 0; i < list.size(); i ++) {
							Room room = list.get(i);
				 %>
				<tr target="sid_teacher" rel="<%=room.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td align="center"><%=room.getNumber() %></td>
					<td align="center"><%=room.getName() %></td>
					<td align="center"><%=HResponse.formatValue("usable", room.getUsable(), request) %></td>
					<td align="center"><%=room.getCreateTime() %></td>
					<td>
						<a title="编辑"  target="navTab" href="<%=basePath %>admin/room/editview.do?id=<%=room.getId() %>" class="btnEdit">编辑</a>
						<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/room/delete.do?id=<%=room.getId() %>" class="btnDel">删除</a>
					</td>
				</tr>
				<% } } %>
				<% if(null == list || 1 > list.size()) { %>
					<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
						<td clospans="5" align="center" style="color:red;">暂无相关记录！</td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
