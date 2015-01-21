<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>批量添加</title>

<form id="pagerForm" method="post" action="<%=basePath %>admin/dept/deptList.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>
<div class="pageHeader">
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="<%=basePath %>admin/dept/deleteOneDept.do?deptId={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个系部"><span>删除</span></a></li>
			<li><a class="edit" href="<%=basePath %>admin/dept/intoEditOneDept.do?deptId={sid_user}" target="navTab" warn="请选择一个系部" rel="editDept"><span>修改</span></a></li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="98%" targetType="navTab" asc="asc"desc="desc" layoutH="116">
				<thead>
					<tr>
						<th>系部编号</th>
						<th>系部名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${deptList}" var="item">
						<tr target="sid_user" rel="${item.deptId}">
							<td>${item.deptId}</td>
							<td>${item.deptName}</td>
						</tr>
					</c:forEach>
					<tr><td colspan="2" align="center">
						<c:if test="${!empty error}">
							<font color="red" size="+4"><c:out value="${error}" /></font>	
						</c:if>
					</td></tr>
				</tbody>
			</table>
	</div>
	<c:import url="../common/page.jsp" />
</div>
