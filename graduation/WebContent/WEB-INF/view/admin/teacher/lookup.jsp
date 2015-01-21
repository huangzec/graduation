<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="false"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<form id="pagerForm" action="<%=basePath %>admin/teacher/lookup.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="orgName" value="${orgName }" />
	<input type="hidden" name="orgNum" value="${orgNum }" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="<%=basePath %>admin/teacher/lookup.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>系部名称:</label>
				<input class="textInput" name="orgName" value="" type="text">
			</li>	  
			<li>
				<label>系部编号:</label>
				<input class="textInput" name="orgNum" value="" type="text">
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
				<th orderfield="orgName" align="center">系部编号</th>
				<th orderfield="orgNum" align="center">系部名称</th>
				<th width="80">选中返回</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${list != null }">
			<c:forEach items="${list}" var="item">
				<tr>
					<td align="center">${item.deptId }</td>
					<td align="center">${item.deptName }</td>
					<td>
						<a class="btnSelect" href="javascript:$.bringBack({id:'${item.deptId }', orgName:'${item.deptName }', orgNum:'${item.deptId }'})" title="选中返回">选择</a>
					</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${list == null }">
				<tr>
					<td align="center" colspan="3" style="color: red;">没有相关记录！</td>
				</tr>
			</c:if>
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
