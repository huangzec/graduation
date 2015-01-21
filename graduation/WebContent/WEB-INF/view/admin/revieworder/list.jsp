<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Revieworder> list 	= (List<Revieworder>) request.getAttribute("list");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
	Department department 	= (Department) request.getSession().getAttribute("department");
	String message 			= (String) request.getAttribute("message");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/revieworder/list.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="ingrade" value="${ingrade}" />
	<input type="hidden" name="profess" value="${profess}" />
	<input type="hidden" name="inclass" value="${inclass}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="grade-review-list-search" action="<%=basePath %>admin/revieworder/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>毕业评阅教师安排</td>
				<td>
					年级：
					<select name="ingrade" class="ingrade">
						<option value="">请选择</option>
						<%
							if(!Verify.isEmpty(gradeList)) {
								for(int i = 0; i < gradeList.size(); i ++) {
									Tbgrade grade = gradeList.get(i);
									%>
									<option value="<%=grade.getGraId() %>">--<%=grade.getGraNumber() %>--</option>
									<%
								}
							}
						 %>
					</select>
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
			<li><a class="add" href="<%=basePath %>admin/revieworder/grade.do" target="navTab" rel="revieworder"><span>安排毕业评阅教师</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="<%=basePath %>admin/revieworder/editview.do?id={sid_teacher}" target="navTab" rel="editgrade"><span>修改毕业评阅教师</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('revieworder')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="revieworder">
		
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="40">*</th>
					<th width="120">学生</th>
					<th width="80">评阅教师</th>
					<th width="80">年级</th>
					<th width="80">状态</th>
					<th width="150">创建时间</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++) {
						Revieworder reodr = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=reodr.getId() %>&status=<%=reodr.getStatus() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=(reodr.getStudentId() + " 【" + HResponse.formatValue("stuId", reodr.getStudentId(), request) + "】 ") %></td>
					<td><%=(reodr.getTeacherId() + " 【" + HResponse.formatValue("teaId", reodr.getTeacherId(), request) + "】 ") %></td>
					<td><%=HResponse.formatValue("grade", reodr.getTbgradeId(), request) %></td>
					<td><%=HResponse.formatValue("status", reodr.getStatus(), request) %></td>
					<td><%=reodr.getCreateTime() %></td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(list)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="5" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
