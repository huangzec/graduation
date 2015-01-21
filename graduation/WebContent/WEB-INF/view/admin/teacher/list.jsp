<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Teacher> list 		= (List<Teacher>) request.getAttribute("list");
	Department department 	= (Department) request.getSession().getAttribute("department");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/teacher/teachlist.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/teacher/teachlist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					教师工号：<input type="text" name="keywordid" />
				</td>
				<td>
					教师姓名：<input type="text" class="keywordname" />
				</td>
			</tr>
		</table>
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
			<li><a class="add" href="<%=basePath %>admin/teacher/addoneview.do" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=basePath %>admin/teacher/editview.do?id={sid_teacher}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=basePath %>admin/teacher/export.do" target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li><a class="icon" href="javascript:$.printBox('teacherlist')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="teacherlist">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="120">教师工号</th>
					<th width="120">教师姓名</th>
					<th>所属系部</th>
					<th width="80">性别</th>
					<th width="150" align="center">职称</th>
					<th width="120" align="center">状态</th>
					<th width="150">联系电话</th>
					<th width="150">电子邮箱</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && 0 < list.size()) {
						for(int i = 0; i < list.size(); i ++) {
							Teacher teacher = list.get(i);
				 %>
				<tr target="sid_teacher" rel="<%=teacher.getTeaId() %>">
					<td><input name="ids" value="<%=teacher.getTeaId() %>" type="checkbox"></td>
					<td><%=teacher.getTeaId() %></td>
					<td><%=teacher.getTeaName() %></td>
					<td><%=department.getDeptName() %></td>
					<td>
						<%=HResponse.formatValue("sex", teacher.getTeaSex(), request) %>
					</td>
					<td>
						<%=HResponse.formatValue("teapos", teacher.getTeaPos(), request) %>
					</td>
					<td>
						<%=HResponse.formatValue("status", teacher.getStatus(), request) %>
					</td>
					<td><%=teacher.getTeaTel() %></td>
					<td><%=teacher.getTeaEmail() %></td>
					<td>
						<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/teacher/editview.do?id=<%=teacher.getTeaId() %>" class="btnEdit">编辑</a>
						<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/teacher/delete.do?id=<%=teacher.getTeaId() %>" class="btnDel">删除</a>
					</td>
				</tr>
				<% }  } else { %>
					<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
						<td clospans="9" style="color:red;text-align:center;">暂无相关记录！</td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
