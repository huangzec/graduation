<%@page language="java" import="java.util.*,java.util.Map.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Settime> list = (List<Settime>) request.getAttribute("list");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/settime/timeorder.do">
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
			<li><a ><span>时间安排表</span></a></li>
			<li class="line">line</li><li><a class="edit" href="<%=basePath %>admin/settime/editview.do?id={sid_teacher}" target="navTab" rel="edittime"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="<%=basePath %>admin/settime/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120" align="center">标题</th>
				<th width="120" align="center">年级</th>
				<th width="120" align="center">开始时间</th>
				<th width="120" align="center">结束时间</th>
				<th width="120" align="center">标识</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(null != list && 0 < list.size()) {
					for(int i = 0; i < list.size(); i ++) {
						Settime st = list.get(i);
			 %>
			<tr target="sid_teacher" rel="<%=st.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
				<td align="center"><%=st.getName() %></td>
				<td align="center"><%=HResponse.formatValue("grade", st.getGraNumber(), request) %></td>
				<td align="center"><%=st.getStartTime() %></td>
				<td align="center"><%=st.getEndTime() %></td>
				<td align="center">
					<%=HResponse.formatValue("mark", st.getMark(), request) %>
				</td>
				<td>
					<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/settime/editview.do?id=<%=st.getId() %>" class="btnEdit">编辑</a>
					<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/settime/delete.do?id=<%=st.getId() %>" class="btnDel">删除</a>
				</td>
			</tr>
			<% } } %>
			<% if(null == list || 1 > list.size()) { %>
				<tr target="" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="6" style="color:red;" align="center">暂无相关记录！</td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
