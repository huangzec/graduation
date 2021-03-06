<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Teacher> list 	= (List<Teacher>) request.getAttribute("list");
 %>
<form id="pagerForm" action="<%=basePath %>admin/topicorderreview/lookup.do">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=basePath %>admin/topicorderreview/lookup.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>职　　称:</label>
				<select class="combox" name="pos">
					<option value="all">-- 所有 --</option>
					<option value="0">-- 助教以上 --</option>
					<option value="1">-- 教师以上 --</option>
					<option value="2">-- 副教授以上 --</option>
					<option value="3">-- 教授 --</option>
					<option value="4">-- 其他 --</option>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="orgId" warn="请选择学生">选择带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="orgId" /></th>
				<th orderfield="id">工号</th>
				<th orderfield="name">姓名</th>
				<th orderfield="sex">性别</th>
				<th>职称</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(!Verify.isEmpty(list)) {
					for(int i = 0; i < list.size(); i ++) {
						Teacher tea = list.get(i);
			 %>
			<tr>
				<td>
					<input type="checkbox" name="orgId" value="{id:'<%=tea.getTeaId() %>', name:'<%=(tea.getTeaId() + "【" + tea.getTeaName() + "】") %>', sex:'<%=tea.getTeaSex() %>', parent_id:'<%=tea.getDeptId() %>'}"/>
				</td>
				<td><%=tea.getTeaId() %></td>
				<td><%=tea.getTeaName() %></td>
				<td><%=HResponse.formatValue("sex", tea.getTeaSex(), request) %></td>
				<td><%=HResponse.formatValue("teapos", tea.getTeaPos() + "", request) %></td>
			</tr>
			<% } } %>
			<%
				if(Verify.isEmpty(list)) {
			 %>
			<tr>
				<td colspan="5" style="color:red;text-align:center;">暂无相关记录</td>
			</tr>
			<% } %>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<!-- <span>每页</span>

			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:this.value})">
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select> -->
			<span>当前页: 第 ${currentPage } 页  / </span><span>共 ${totalPage } 页/ ${totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${totalCount }" numPerPage="${numPerPage }" pageNumShown="${pageNumShown }" currentPage="${currentPage }"></div>
	</div>
</div>
