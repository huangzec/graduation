<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Gradeall> list 	= (List<Gradeall>) request.getAttribute("gradeall-list");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("tbgradeList");
	Department department 		= (Department) request.getSession().getAttribute("department");
	String gradeName 			= (String) request.getAttribute("grade");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/graduateinfo/list.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum	" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form method="post" action="<%=basePath %>admin/graduateinfo/totalrows.do" id="graduation-score-search-list" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
	<div class="searchBar">
		<div>
			请选择查询的条件：
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="graduate-search-btn">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('grade-list')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="grade-list">
		
		<table class="table" width="100%" layoutH="138">
			
			<tbody>
				<tr>
					<td colspan="3" style="text-align:center;">
						<%=(department.getDeptName() + " " + HResponse.formatValue("grade", gradeName, request)) %> 
						毕业论文（设计）总成绩表
					</td>
				</tr>
				<tr>
					<td width="40">*</td>
					<td width="120">学生</td>
					<td width="80">成绩</td>
				</tr>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++) {
						Gradeall grad = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=grad.getGaId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=(grad.getStuId() + " 【" + HResponse.formatValue("stuId", grad.getStuId(), request) + "】 ") %></td>
					<td><%=grad.getGaGrade() %></td>
				</tr>
				<% } } %>
				<%
					if(null == list || 1 > list.size()) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td colspan="3" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</div>
