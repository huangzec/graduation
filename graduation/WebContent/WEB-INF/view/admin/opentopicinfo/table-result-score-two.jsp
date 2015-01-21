<%@page language="java" import="java.util.*, java.math.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Gradetwo> scoreList 	= (List<Gradetwo>) request.getAttribute("scoreList");
	Department department 		= (Department) request.getSession().getAttribute("department");
	List<Tbgrade> gradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
	List<Student> stuList 		= (List<Student>) request.getAttribute("stuList");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/opentopicinfo/tabletwoscore.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum	" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form method="post" action="<%=basePath %>admin/opentopicinfo/tabletwoscore.do" id="score-search-list" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
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
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="score-search-btn optresult-search-btn">检索</button></div></div></li>
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
					<th width="50">*</th>
					<th width="150">学生</th>
					<th width="60">项一分数</th>
					<th width="60">项二分数</th>
					<th width="60">项三分数</th>
					<th width="60">项四分数</th>
					<th width="60">项五分数</th>
					<th width="60">项六分数</th>
					<th width="90">表二总分数</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(stuList)) {
						for(int i = 0; i < stuList.size(); i ++) {
							Student student = stuList.get(i);
							Float one 	= (float) 0.0;
							Float two 	= (float) 0.0;
							Float three	= (float) 0.0;
							Float four 	= (float) 0.0;
							Float five 	= (float) 0.0;
							Float six 	= (float) 0.0;
							Float all 	= (float) 0.0;
							if(!Verify.isEmpty(scoreList)) {
								for(int j = 0; j < scoreList.size(); j ++) {
									Gradetwo score = scoreList.get(j);
									if(score.getStuId().equals(student.getStuId())) {
										one 	= score.getGtOne();
										two 	= score.getGtTwo();
										three 	= score.getGtThree();
										four 	= score.getGtFour();
										five 	= score.getGtFive();
										six 	= score.getGtSix();
										all 	= score.getGtAll();
										break;
									}
								}
							}
				%>
				<tr target="sid_teacher" rel="<%=student.getStuId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=(student.getStuId() + "【" + student.getStuName() + "】") %></td>
					<td><%=one %></td>
					<td><%=two %></td>
					<td><%=three %></td>
					<td><%=four %></td>
					<td><%=five %></td>
					<td><%=six %></td>
					<td><%=all %></td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(stuList)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="9" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</div>
