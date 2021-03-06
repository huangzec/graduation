<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="false"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="pagerForm" method="post" action="<%=basePath %>admin/grade/list.do">
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
			<li><a class="add" href="<%=basePath %>admin/grade/addview.do" target="navTab" rel="addgrade"><span>添加</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/grade/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120" align="center">系部</th>
				<th width="120" align="center">年级</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
			<tr target="sid_teacher" rel="${item.graId }"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
				<td align="center">${department.deptName }</td>
				<td align="center">${item.graNumber }</td>
				<td>
					<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/grade/editview.do?id=${item.graId}" class="btnEdit">编辑</a>
					<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/grade/delete.do?id=${item.graId }" class="btnDel">删除</a>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty list }">
				<tr target="" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="3" style="color:red;" align="center">暂无相关记录！</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
