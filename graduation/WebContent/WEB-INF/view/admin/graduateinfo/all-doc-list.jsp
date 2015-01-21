<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tballdoc> list 	 = (List<Tballdoc>) request.getAttribute("list");
	List<Tbgrade> gradeList  = (List<Tbgrade>) request.getAttribute("tbgradeList");
	Department department    = (Department) request.getSession().getAttribute("department");
	String keyword 			 = (String) request.getAttribute("keyword");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/rejoin/alldoclist.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="grade" value="<%=Verify.isEmpty(keyword) ? "" : keyword %>" />
	<input type="hidden" name="pageNum" value="1<%=keyword %>" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form method="post" action="<%=basePath %>admin/rejoin/alldoclist.do" id="alldoc-search" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
	<div class="searchBar">
		<div>
			请选择要查询的年级：
			<select name="gradeid" class="ingrade required">
				<option value="">-- 请选择 --</option>
				<%
					if(null != gradeList && 0 < gradeList.size()) {
						for(int i = 0; i < gradeList.size(); i ++) {
						Tbgrade grade = gradeList.get(i);
				 %>
				 		<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
				 <%
				 		}
				 	}
				  %>
			</select>
		</div>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="alldoc-search-btn">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('all-doc-list')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="all-doc-list">
		
		<table class="table" width="100%" layoutH="138">
			
			<tbody>
				<tr>
					<td colspan="4" style="text-align:center;">
						毕业生最终上交材料一览表
					</td>
				</tr>
				<tr>
					<td style="text-align: center">学号</td>
					<td style="text-align: center">姓名</td>
					<td style="text-align: center">查看详细</td>
					<td style="text-align: center">上传时间</td>
				</tr>
				<%
					if(list != null && list.size() > 0) {
						for(int i = 0; i < list.size(); i ++) {
							Tballdoc alldoc = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=alldoc.getId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td style="text-align: center"><%=alldoc.getStuId() %></td>
					<td style="text-align: center"><%=HResponse.formatValue("stu", alldoc.getStuId(), request) %></td>
					<td style="text-align: center;">
						<a style="color: red;" href="<%=basePath %>admin/rejoin/detail.do?id=<%=alldoc.getId() %>&userid=<%=alldoc.getStuId() %>" target="dialog" mask="true" rel="redetail" title="查看详细">
							详细
						</a>
					</td>
					<td style="text-align: center"><%=alldoc.getCreateTime() %></td>
				</tr>
				<% } }else{%>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td colspan="4" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
