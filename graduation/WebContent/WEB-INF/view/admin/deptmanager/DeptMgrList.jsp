<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%	
	List<Deptmanager> list 		= (List<Deptmanager>) request.getAttribute("list");
	List<Department> departList	= (List<Department>) request.getAttribute("deptList");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/manager/DeptMgrList.do">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="<%=basePath %>admin/manager/DeptMgrList.do" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>姓名：</label> <input type="text" name="keyword" /></li>
				<li>
					<label>所在部门：</label>
					<select class="combox" name="keywords">
						<option value="">请选择</option>
						<% 
							if(null != departList && 0 < departList.size()) {
								for(int i = 0; i < departList.size(); i ++) {
									Department department = departList.get(i);
						 %>
								<option value="<%=department.getDeptId() %>"><%=department.getDeptName() %></option>
						<% } } %>
					</select>
				</li>
			</ul>
			<div class="subBar">
				<ul><li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">检索</button>
					</div>
				</div></li></ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="<%=basePath%>/admin/manager/intoAddOneDeptMgr.do" target="navTab"><span>添加</span>
			</a></li>
			<li><a class="delete"
				href="<%=basePath%>admin/manager/deleteOneDeptMgr.do?dmId={sid_user}"
				target="ajaxTodo" title="确定要删除吗？" warn="请选择一个系部"><span>删除</span>
			</a></li>
			<li><a class="edit"
				href="<%=basePath%>admin/manager/intoEditOneDeptMgr.do?dmId={sid_user}"
				target="navTab" warn="请选择一个系部" rel="editDeptMgr"><span>修改</span> </a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=basePath%>admin/manager/exportExcel.do"
				target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span>
			</a></li>
			<li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印</span>
			</a></li><li class="line">line</li>
			<li><a class="edit" 
				href="<%=basePath %>admin/index/resetpwd.do?id={sid_user}&permissions=3" target="dialog">
				<span>重置密码</span></a>
			</li>
		</ul>
	</div>

	<div id="w_list_print">
		<table class="list" width="98%" targetType="navTab" asc="asc"
			desc="desc" layoutH="116">
			<thead>
				<tr>
					<th style="text-align: center">系部ID</th>
					<th style="text-align: center">系部名称</th>
					<th style="text-align: center">管理员ID</th>
					<th style="text-align: center">姓名</th>
					<th style="text-align: center">性别</th>
					<th style="text-align: center">联系电话</th>
					<th style="text-align: center">邮箱</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(list != null && list.size() > 0){
						for(int i = 0; i < list.size(); i++){
							Deptmanager manager = list.get(i);
				 %>
				 	<tr target="sid_user" rel="<%=manager.getDmId() %>">
						<td style="text-align: center"><%=manager.getDeptId() %></td>
						<td style="text-align: center"><%=HResponse.formatValue("dept", manager.getDeptId(), request) %></td>
						<td style="text-align: center"><%=manager.getDmId() %></td>
						<td style="text-align: center"><%=manager.getDmName() %></td>
						<td style="text-align: center"><%=HResponse.formatValue("sex", manager.getDmSex(), request) %></td>
						<td style="text-align: center"><%=manager.getDmTel() %></td>
						<td style="text-align: center"><%=manager.getDmEmail() %></td>
					</tr>
				 <%		}
				 	}else {
				  %>
					<tr>
						<td colspan="7" style="text-align: center; color: red;">
							暂无相关记录！
						</td>
					</tr>
				<%	} %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>

