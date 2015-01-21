<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Department> list 		= (List<Department>) request.getAttribute("list");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/dept/deptList.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/dept/deptList.do" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>系部名称：</label>
				<input type="text" name="keyword"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>/admin/dept/intoAddOneDept.do" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/dept/deleteOneDept.do?deptId={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个系部"><span>删除</span></a></li>
			<li><a class="edit" href="<%=basePath %>admin/dept/intoEditOneDept.do?deptId={sid_user}" target="navTab" warn="请选择一个系部" rel="editDept"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=basePath %>admin/dept/excelExport.do" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印</span></a></li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="98%" targetType="navTab" asc="asc"desc="desc" layoutH="116">
				<thead>
					<tr>
						<th>系部编号</th>
						<th>系部名称</th>
						<th>系部专业类型</th>
					</tr>
				</thead>
				<tbody>
					<%
						if(null != list && 0 < list.size()) { 
							for(int i = 0; i < list.size(); i ++) {
								Department department = list.get(i);
					 %>
						<tr target="sid_user" rel="<%=department.getDeptId() %>">
							<td><%=department.getDeptId() %></td>
							<td><%=department.getDeptName() %></td>
							<td><%=HResponse.formatValue("majortype", department.getMajorType(), request) %></td>
						</tr>
					<% } } %>
					<% if(null == list || 1 > list.size()) { %>
					<tr>
						<td colspan="2" align="center">
							<font color="red" size="+4"><%=request.getAttribute("error") %></font>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
