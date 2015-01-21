<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Teacher> list 	= (List<Teacher>) request.getAttribute("list");
	String message 		= (String) request.getAttribute("message");
 %>
<form id="pagerForm" action="<%=basePath %>admin/revieworder/lookuptea.do">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="id" value="${id}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=basePath %>admin/revieworder/lookuptea.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<input type="hidden" name="place" value="${place }" />
	<input type="hidden" name="id" value="${id}" />
	<input type="hidden" name="orderid" value="${orderid}" />
		<ul class="searchContent">
			<li>
				<label>教师姓名:</label>
				<input class="textInput" name="name" value="" type="text">
			</li>
			<li>
				<label>职　　称:</label>
				<select name="pos">
					<option value="">-- 请选择 --</option>
					<option value="0">-- 助教以上 --</option>
					<option value="1">-- 讲师以上 --</option>
					<option value="2">-- 副教授以上 --</option>
					<option value="3">-- 教授 --</option>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th>工号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>职称</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(!Verify.isEmpty(list)) {
					for(int i = 0; i < list.size(); i ++) {
						Teacher tea = list.get(i);
			 %>
			<tr>
				<td><%=tea.getTeaId() %></td>
				<td><%=tea.getTeaName() %></td>
				<td><%=HResponse.formatValue("sex", tea.getTeaSex(), request) %></td>
				<td><%=HResponse.formatValue("teapos", tea.getTeaPos() + "", request) %></td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'<%=tea.getTeaId() %>', name:'<%=(tea.getTeaId() + "【" + tea.getTeaName() + "】") %>'})" title="查找带回">
						选择
					</a>
				</td>
			</tr>
			<% } } %>
			<%
				if(Verify.isEmpty(list)) {
			 %>
			<tr>
				<td colspan="5" style="color:red;text-align:center;">
					<%=(Verify.isEmpty(message) ? "暂无相关记录" : message) %>
				</td>
			</tr>
			<% } %>
		</tbody>
	</table>
</div>
