<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Tbclass tbclass 			= (Tbclass) request.getAttribute("tbclass");
	Tbgrade tbgrade 			= (Tbgrade) request.getAttribute("tbgrade");
	List<Tbgrade> gradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
	List<Profession> proList 	= (List<Profession>) request.getAttribute("professionList");
	Department department 		= (Department) request.getSession().getAttribute("department");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/tbclass/edit.do" id="add-class-grade" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>班级编号：</label>
				<input name="id" type="text" size="30" value="${tbclass.claId }" readonly="readonly"/>
			</p>
			<p>
				<label>班级名称：</label>
				<input name="name" class="required" type="text" size="30" value="${tbclass.claName }" alt="不超过64个字符"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>专　业：</label>
				<select name="" class="required add-class-grade">
					<%
						if(!Verify.isEmpty(gradeList)) {
					%>
						<option value="">--请选择年级--</option>
					<%
							for(int i = 0; i < gradeList.size(); i ++) {
								Tbgrade grade = gradeList.get(i);
								if(grade.getGraId().toString().trim().equals(tbgrade.getGraId().toString().trim())) {
					%>
								<option value="<%=grade.getGraId() %>" selected>-- <%=grade.getGraNumber() %> --</option>
					<%
							}else { 
								%>
								<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
								<%
							} 	}
						}else {
					%>
							<option value="">-- 请先添加年级  --</option>
					<%
						}
					 %>
				</select>
				<select name="parent_id" class="profession loaded">
					<% if(null == proList || 1 > proList.size()) { %>
						<option>-- 请先添加专业 --</option>
					<% 
						} else {
						for(int i = 0; i < proList.size(); i ++) {
							Profession pro = proList.get(i);
							if(tbclass.getProId().toString().trim().equals(pro.getProId().toString().trim())) 
							{
					 %>
							<option value="<%=pro.getProId() %>" selected>-- <%=pro.getProName() %> --</option>
							<% } else if(pro.getGraId().toString().equals(tbgrade.getGraId().toString().trim())){ %>
							<option value="<%=pro.getProId() %>">-- <%=pro.getProName() %> --</option>
					<% } } } %>
				</select>
			</div>
			<div class="divider"></div>
			<p class="text-center">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="add-class-btn">保存</button></div></div></li>
					
				</ul>
			</p>
		</div>
	</form>
</div>