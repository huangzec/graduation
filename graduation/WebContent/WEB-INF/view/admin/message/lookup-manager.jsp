<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tboffice> lookupList 	= (List<Tboffice>) request.getAttribute("list");
 %>
<form id="pagerForm" action="<%=basePath %>admin/message/lookupman.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="orgName" value="${orgName }" />
	<input type="hidden" name="orgNum" value="${orgNum }" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=basePath %>admin/message/lookupman.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
			</li>	  
		</ul>
		<div class="subBar">
			<ul>
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
				<th align="center">教务处管理员编号</th>
				<th align="center">教务处管理员姓名</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(null != lookupList && 0 < lookupList.size()) {
					for(int i = 0; i < lookupList.size(); i ++) {
					Tboffice office = lookupList.get(i);
			 %>
				<tr>
					<td><input type="checkbox" name="orgId" value="{id:'<%=office.getOffId() %>', name:'<%=(office.getOffId() + "【" + office.getOffName() + "】") %>'}"/></td>
					<td align="center"><%=office.getOffId() %></td>
					<td align="center"><%=office.getOffName() %></td>
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
