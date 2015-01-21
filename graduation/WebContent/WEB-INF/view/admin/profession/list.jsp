<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Profession> list 	= (List<Profession>) request.getAttribute("list");
	Department department 	= (Department) request.getSession().getAttribute("department");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
	String keyword 			= (String) request.getAttribute("keyword");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/profession/list.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="grade" value="<%=(Verify.isEmpty(keyword) ? "" : keyword) %>" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/profession/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					年　级：
					<select name="grade" class="required">
						<option value="">-- 请选择 --</option>
						<%
							if(!Verify.isEmpty(gradeList)) {
								for(int i = 0; i < gradeList.size(); i ++) {
								Tbgrade grade = gradeList.get(i);
								if(!Verify.isEmpty(keyword) && Integer.parseInt(keyword) == grade.getGraId()) {
						 %>
						 		<option value="<%=grade.getGraId() %>" selected ><%=grade.getGraNumber() %></option>
						 <%
						 			}else {
						 			%>
				 				<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
						 			<%
						 			}
						 		}
						 	}
						  %>
					</select>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<!-- <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/profession/addview.do" target="navTab" rel="addgrade"><span>添加</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/profession/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120" align="center">专业编号</th>
				<th width="120" align="center">专业名称</th>
				<th width="120" align="center">所属系部</th>
				<th width="120" align="center">年级</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(null != list && 0 < list.size()) {
					for(int i = 0; i < list.size(); i ++) {
						Profession profession = list.get(i);
			 %>
			<tr target="sid_teacher" rel="<%=profession.getProId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
				<td align="center"><%=profession.getProId() %></td>
				<td align="center"><%=profession.getProName() %></td>
				<td align="center"><%=department.getDeptName() %></td>
				<td align="center"><%=HResponse.formatValue("grade", profession.getGraId().toString(), request) %></td>
				<td>
					<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/profession/editview.do?id=<%=profession.getProId() %>" class="btnEdit">编辑</a>
					<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/profession/delete.do?id=<%=profession.getProId() %>" class="btnDel">删除</a>
				</td>
			</tr>
			<% } } else { %>
				<tr target="" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="5" style="color:red;" align="center">暂无相关记录！</td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
