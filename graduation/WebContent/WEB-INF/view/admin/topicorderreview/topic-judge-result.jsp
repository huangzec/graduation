<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbtopic> list 		= (List<Tbtopic>) request.getAttribute("list");
	List begroupList 		= (List) request.getAttribute("begroupList");
	Department department 	= (Department) request.getSession().getAttribute("department");
	String year				= (String) request.getAttribute("year");
	String submitType 		= (String) request.getAttribute("submittype");
	String submiter 		= (String) request.getAttribute("submiter");
	String topicStatus 		= (String) request.getAttribute("topicstatus");
	List<Map<String, String>> statusList 	= (List<Map<String, String>>) request.getAttribute("status_list");
	List<Map<String, String>> typeList 	= (List<Map<String, String>>) request.getAttribute("type_list");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/topicorderreview/topicresult.do">
	<input type="hidden" name="submittype" value="${submittype}">
	<input type="hidden" name="submiter" value="${submiter}" />
	<input type="hidden" name="topicstatus" value="${topicstatus}" />
	<input type="hidden" name="year" value="${year}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basePath %>admin/topicorderreview/topicresult.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					年　　份：
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
					提交人类型：
					<select name="submittype">
						<option value="">-- 请选择 --</option>
						<%
						for(int i = 0; null != typeList && i < typeList.size(); i ++) {
							Map<String, String> map = typeList.get(i);
							if(!Verify.isEmpty(submitType) && submitType.equals(ArrayUtil.getMapValue("id", map))) {
						 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected > -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% } else {
							%>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
							<%
						} } %>
					</select>
				</td>
				<td>
					审核状态：
					<select name="topicstatus">
						<option value="">--- 请选择 -</option>
						<%
						for(int i = 0; null != statusList && i < statusList.size(); i ++) {
							Map<String, String> map = statusList.get(i);
							if(!Verify.isEmpty(topicStatus) && topicStatus.equals(ArrayUtil.getMapValue("id", map))) {
						 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected > -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% } else {
						%>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% 
						} } %>
					</select>
				</td>
				<td>
					提交人：
					<input type="text" size="30" value="<%=(Verify.isEmpty(submiter) ? "" : submiter) %>" name="submiter" alt="请输入你要查询的提交人ID" />
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
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('opentopicinfo-list')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="opentopicinfo-list">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="40">*</th>
					<th width="120">提交人</th>
					<th width="200">课题名称</th>
					<th width="40">完成人数</th>
					<th width="50">审核状态</th>
					<th width="80">课题类型</th>
					<th width="80">课题来源</th>
					<th width="80">年份</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++) {
						Tbtopic topic = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=topic.getTopId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=topic.getTopCommitId() %></td>
					<td><%=topic.getTopName() %></td>
					<td><%=topic.getTopNumber() %></td>
					<td><%=HResponse.formatValue("status", topic.getTopStatus(), request) %></td>
					<td><%=HResponse.formatValue("toptype", topic.getTopType(), request)  %></td>
					<td><%=HResponse.formatValue("topsource", topic.getTopSource(), request)  %></td>
					<td><%=topic.getTopYear() %></td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(list)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="10" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
