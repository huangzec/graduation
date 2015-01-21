<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Teacher> lookupList 	= (List<Teacher>) request.getAttribute("list");
	String id 					= (String) request.getAttribute("id");
	String name 				= (String) request.getAttribute("name");
 %>
<form id="pagerForm" action="<%=basePath %>admin/message/lookuptea.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="name" value="<%=Verify.isEmpty(name) ? "" : name %>" />
	<input type="hidden" name="id" value="<%=Verify.isEmpty(id) ? "" : id %>" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=basePath %>admin/message/lookuptea.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>教师工号:</label>
				<input class="textInput" name="id" value="" type="text">
			</li>	
			<li>
				<label>教师姓名:</label>
				<input class="textInput" name="name" value="" type="text">
			</li>  
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="orgId" warn="请选择部门">选择带回</button></div></div></li>
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
				<th align="center">教师工号</th>
				<th align="center">教师姓名</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(null != lookupList && 0 < lookupList.size()) {
					for(int i = 0; i < lookupList.size(); i ++) {
					Teacher teacher = lookupList.get(i);
			 %>
				<tr>
					<td><input type="checkbox" name="orgId" value="{id:'<%=teacher.getTeaId() %>', name:'<%=(teacher.getTeaId() + "【" + teacher.getTeaName() + "】") %>'}"/></td>
					<td align="center"><%=teacher.getTeaId() %></td>
					<td align="center"><%=teacher.getTeaName() %></td>
				</tr>
			<% } } %>
			<% if(null == lookupList || 1 > lookupList.size()) { %>
			<tr>
				<td colspan="3" align="center">
					<font color="red" size="+4"><%=request.getAttribute("message") %></font>
				</td>
			</tr>
			<% } %>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>

			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:this.value})">
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>当前页: 第 ${currentPage } 页  / </span><span>共 ${totalPage } 页/ ${totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${totalCount }" numPerPage="${numPerPage }" pageNumShown="${pageNumShown }" currentPage="${currentPage }"></div>
	</div>
</div>
