<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbgrade> gradeList 		= (List<Tbgrade>) request.getAttribute("gradeList");
	Profession profession 			= (Profession) request.getAttribute("profession");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/profession/edit.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>专业编号：</label>
				<input name="id" type="text" readonly="readonly" size="30" value="${profession.proId }" class="required"/>
			</p>
			<p>
				<label>专业名称：</label>
				<input name="name" class="required" type="text" size="30" value="${profession.proName }" alt="不超过50个字符"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>所属系部：</label>
				<input name="parent_id" type="hidden" size="30" value="${department.deptId }" />
				<input name="" type="text" size="30" value="${department.deptName }" readonly="readonly"/>
			</p>
			<p>
				<label>年　级：</label>
				<select name="graid" class="required">
					<% if(null == gradeList || 1 > gradeList.size()) { %>
						<option>-- 请先添加年级 --</option>
					<% 
						} else {
						for(int i = 0; i < gradeList.size(); i ++) {
							Tbgrade grade = gradeList.get(i);
							if(profession.getGraId().toString().trim().equals(grade.getGraId().toString().trim())) 
							{
					 %>
							<option value="<%=grade.getGraId() %>" selected>-- <%=grade.getGraNumber() %> --</option>
							<% } else { %>
							<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
					<% } } } %>
				</select>
			</p>
			<div class="divider"></div>
			<p class="text-center">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					
				</ul>
			</p>
		</div>
	</form>
</div>