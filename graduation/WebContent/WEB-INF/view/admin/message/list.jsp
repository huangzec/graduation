<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> list 	= (List<Message>) request.getAttribute("list");
	Department department 		= (Department) request.getSession().getAttribute("department");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/message/list.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum	" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<br>
	<div class="searchBar text-center">
		消息列表
	</div>
	<br>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/message/addview.do" target="navTab" rel="send"><span>发送消息</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<div id="revieworder">
		
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="40">*</th>
					<th width="120">标题</th>
					<th width="80">发件人</th>
					<th width="80">时间</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && 0 < list.size()) {
						for(int i = 0; i < list.size(); i ++) {
						Message message = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=message.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td>
						<a target="navTab" rel="detail" title="<%=message.getName() %>" href="<%=basePath %>admin/message/detail.do?id=<%=message.getId() %>">
							<%=StringUtil.cutString(message.getName(), 25) %>
						</a>
					</td>
					<td><%=message.getFromId() %></td>
					<td><%=message.getCreateTime() %></td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(list)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="4" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
