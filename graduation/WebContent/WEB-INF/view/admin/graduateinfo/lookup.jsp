<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Apply> list 	= (List<Apply>) request.getAttribute("list");
 %>
<form id="pagerForm" action="<%=basePath %>admin/graduateinfo/lookup.do">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=basePath %>admin/graduateinfo/lookup.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				
			</li>
		</ul>
		<div class="subBar">
			<ul>
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
				<th orderfield="id">学生</th>
				<th orderfield="name">申请时间</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(null != list && 0 < list.size()) {
					for(int i = 0; i < list.size(); i ++) {
						Apply apply = list.get(i);
			 %>
			<tr>
				<td>
					<input type="checkbox" name="orgId" value="{id:'<%=apply.getUserId() %>', name:'<%=(apply.getUserId() + "【" + HResponse.formatValue("stuId", apply.getUserId(), request) + "】") %>', applyid:'<%=apply.getId() %>'}"/>
				</td>
				<td><%=apply.getUserId() + " 【" + HResponse.formatValue("stuId", apply.getUserId(), request) + "】" %></td>
				<td><%=apply.getCreateTime() %></td>
			</tr>
			<% } } %>
			<%
				if(null == list || 1 > list.size()) {
			 %>
			<tr>
				<td colspan="5" style="color:red;text-align:center;">暂无相关记录</td>
			</tr>
			<% } %>
		</tbody>
	</table>
</div>
