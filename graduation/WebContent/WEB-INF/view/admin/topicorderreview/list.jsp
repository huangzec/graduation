<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Topicorderreview> list = (List<Topicorderreview>) request.getAttribute("list");
	List begroupList 			= (List) request.getAttribute("begroupList");
	String year					= (String) request.getAttribute("year");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/topicorderreview/list.do">
	<input type="hidden" name="year" value="${year}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/topicorderreview/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					评审年份：
					<select name="year" class="">
						<option value="">-- 请选择 --</option>
						<%
							 if(!Verify.isEmpty(begroupList)) {
							 	for(int i = 0; i < begroupList.size(); i ++) {
							 		if(!Verify.isEmpty(begroupList.get(i))) {
							 			if(!Verify.isEmpty(year) && year.trim().equals(begroupList.get(i).toString().trim())) {
						 				%>
						 				<option value="<%=begroupList.get(i) %>" selected >-- <%=begroupList.get(i) %> --</option>
						 				<%
							 			} else {
			 			%>
			 							<option value="<%=begroupList.get(i) %>">-- <%=begroupList.get(i) %> --</option>
			 			<%
			 							}
							 		}
							 	}
							 }
						 %>
					</select>
				</td>
				<td>
					<div class="buttonContent"><button type="submit">检索</button></div>
				</td>
			</tr>
		</table>
		<div class="subBar">
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/topicorderreview/addview.do" target="navTab" rel="addjudgetea"><span>安排课题评审教师</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/topicorderreview/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120" align="center">*</th>
				<th width="120" align="center">标题</th>
				<th width="80" align="center">评审教师</th>
				<th width="80" align="center">评审年份</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(!Verify.isEmpty(list)) {
					for(int i = 0; i < list.size(); i ++) {
						Topicorderreview review = list.get(i);
			 %>
			<tr target="sid_teacher" rel="<%=review.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
				<td align="center"><%=(i + 1) %></td>
				<td align="center"><%=review.getName() %></td>
				<td align="center">
					<a href="<%=basePath %>admin/topicorderreview/detail.do?id=<%=review.getId() %>" target="navTab" rel="detail" title="查看详细">
						<%=(StringUtil.cutString(HResponse.formatJudgeValue(review.getJudgeTea(), request), 100)) %>
					</a>
				</td>
				<td align="center"><%=review.getJudgeYear() %></td>
				<td>
					<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/topicorderreview/editview.do?id=<%=review.getId() %>" class="btnEdit">编辑</a>
					<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/topicorderreview/delete.do?id=<%=review.getId() %>" class="btnDel">删除</a>
				</td>
			</tr>
			<% } } %>
			<% if(Verify.isEmpty(list)) { %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="5" align="center" style="color:red;">暂无相关记录！</td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
