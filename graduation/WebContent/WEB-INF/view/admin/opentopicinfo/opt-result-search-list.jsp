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
	List<Student> stuList 		= (List<Student>) request.getAttribute("stuList");
	List<Opentopicinfo> optinfoList 	= (List<Opentopicinfo>) request.getAttribute("optinfoList");
	List<Opentopicscore> optscoreList 	= (List<Opentopicscore>) request.getAttribute("optscoreList");
	int canjiadabian 	= 0;
	int tongguodabian 	= 0;
	int weidabian 		= 0;
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/opentopicinfo/totalresult.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="pageNum	" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form method="post" action="<%=basePath %>admin/opentopicinfo/totalresult.do" id="score-search" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="optresult-search-btn">检索</button></div></div></li>
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
					<th width="40">*</th>
					<th width="120">学生</th>
					<th width="80">答辩情况</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(stuList)) {
						for(int i = 0; i < stuList.size(); i ++) {
						Student stu = stuList.get(i);
						boolean isHaveOpentopic = false;
						Double opentopicScore 	= (double) 0.0;
						if((!Verify.isEmpty(optinfoList))) {
							for(int j = 0; j < optinfoList.size(); j ++) {
								Opentopicinfo opt = optinfoList.get(j);
								if(opt.getStuId().equals(stu.getStuId())) {
									//参加答辩
									isHaveOpentopic = true;
									if(!Verify.isEmpty(optscoreList)) {
										for(int k = 0; k < optscoreList.size(); k ++) {
											Opentopicscore opts = optscoreList.get(k);
											if(opts.getStudentId().equals(stu.getStuId())) {
												opentopicScore = opts.getScore();
												break;
											}
										}
										
									}
									canjiadabian ++;
									break;
								}
							}
						}
						
				%>
				<tr target="sid_teacher" rel="<%=stu.getStuId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=(stu.getStuId() + "【" + stu.getStuName() + "】") %></td>
					<td>
						<%
							if(isHaveOpentopic) {
								out.print("<span style=\"color: red;\">已参加答辩 </span>");
								if(opentopicScore > 60) {
									tongguodabian ++;
									out.print(" <span style=\"color: red;\">答辩通过</span>");
								} else {
									out.print(" 答辩不通过");
								}
							}else {
								out.print("未参加答辩 ");
							}
						 %>
					</td>
				</tr>
				<% } %>
				<%
					} 
				%>
				<%
					if(null == stuList || 1 > stuList.size()) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="4" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>参加答辩人数：<%=canjiadabian %> </span><span>&nbsp;&nbsp;</span>
				<span>通过答辩人数：<%=tongguodabian %> </span><span>&nbsp;&nbsp;</span>
				<span>未通过答辩人数：<%=(canjiadabian - tongguodabian) %> </span><span>&nbsp;&nbsp;</span>
				<span>未参加答辩人数：  <%=(Verify.isEmpty(stuList) ? 0 : (stuList.size() - canjiadabian)) %></span>  
			</div>
		</div>
	</div>
</div>
