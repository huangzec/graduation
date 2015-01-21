<%@page language="java" import="java.util.*, java.math.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Opentopicscore> list 	= (List<Opentopicscore>) request.getAttribute("scoreList");
	Department department 		= (Department) request.getSession().getAttribute("department");
	List<Tbgrade> gradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/graduateinfo/list.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum	" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form method="post" action="<%=basePath %>admin/opentopicinfo/totalrows.do" id="score-search-list" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
	<div class="searchBar">
		<div>
			请选择查询条件：
			<select class="ingrade" name="gradeid">
				<option value="">-- 请选择年级 --</option>
				<%
					if(!Verify.isEmpty(gradeList)) {
						for(int i = 0; i < gradeList.size(); i ++) {
							Tbgrade grade = gradeList.get(i);
							%>
							<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
							<%
						}
					}
				 %>
			</select>
		</div>
		<div class="divider"></div>
		<table class="searchContent">
			<tr>
				<td>
					起始日期：
					<input type="text" name="start" class="date" readonly="true"/>
				</td>
				<td>
					<label>截止日期：</label>
					<input type="text" name="end" class="date" readonly="true"/>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="score-search-btn">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('score-list')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="score-list">
		
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="80">*</th>
					<th width="120">学生</th>
					<th width="120">分数</th>
					<th width="100">是否通过</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++) {
						Opentopicscore ops = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=ops.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=(ops.getStudentId() + " 【" + HResponse.formatValue("stuId", ops.getStudentId(), request) + "】") %></td>
					<td><%=ops.getScore() %></td>
					<td>
						<%
							if(ops.getScore() >= 60) {
						 %>
						 	通过
						 <% } else { %>
						 	<span style="color:red;text-align:center;">不通过</span>
						 <% } %>
					</td>
				</tr>
				<% } } %>
				<%
					if(null == list || 1 > list.size()) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="4" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</div>
