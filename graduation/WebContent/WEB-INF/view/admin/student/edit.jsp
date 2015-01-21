<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Student student 		= (Student) request.getAttribute("student");
	Tbgrade ingrade 		= (Tbgrade) request.getAttribute("ingrade");
	Profession inprofession = (Profession) request.getAttribute("inprofession");
	List<Tbgrade> tbgradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
	List<Profession> professionList 	= (List<Profession>) request.getAttribute("professionList");
	List<Tbclass> tbcList 	= (List<Tbclass>) request.getAttribute("tbclassList");
	List<Map<String, String>> statusList 	= (List<Map<String, String>>) request.getAttribute("status_list");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/student/edit.do" id="edit-student-form" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>学号：</label>
				<input name="id" type="text" size="30" value="${student.stuId }" readonly="true" />
			</p>
			<p>
				<label>姓名：</label>
				<input name="name" class="required" type="text" size="30" alt="不超过16个字符" value="${student.stuName }" />
			</p>
			<div class="divider"></div>
			<div>
				<label>班级：</label>
				<select class="ingrade">
					<%
						if(!Verify.isEmpty(tbgradeList)) {
							for(int i = 0; i < tbgradeList.size(); i ++) {
								Tbgrade tbgrade = tbgradeList.get(i);
								if(!Verify.isEmpty(ingrade) && ingrade.getGraId().toString().equals(tbgrade.getGraId().toString())) {
									%>
									<option value="<%=tbgrade.getGraId() %>" selected >-- <%=tbgrade.getGraNumber() %> --</option>
									<%
								}else {
								%>
								<option value="<%=tbgrade.getGraId() %>">-- <%=tbgrade.getGraNumber() %> --</option>
								<%
							} }
						}else {
							%>
							<option value="">-- 请先添加年级 --</option>
							<%
						}
					 %>
				</select>
				<select class="inprofession">
					<%
						if(!Verify.isEmpty(professionList)) {
						%>
							<option value="">-- 请选择 --</option>
						<%
							for(int i = 0; i < professionList.size(); i ++) {
								Profession profession = professionList.get(i);
								if(!Verify.isEmpty(inprofession) && inprofession.getProId().equals(profession.getProId().trim())) {
									%>
									<option value="<%=profession.getProId() %>" selected >-- <%=profession.getProName() %> --</option>
									<%
								}else {
								%>
								<option value="<%=profession.getProId() %>">-- <%=profession.getProName() %> --</option>
								<%
							} }
						}else {
							%>
							<option value="">-- 请先添加专业 --</option>
							<%
						}
					 %>
				</select>
				<select name="claid" class="inclass">
					<% if(Verify.isEmpty(tbcList)) { %>
						<option>-- 请先添加班级 --</option>
					<% 
						} else {
						for(int i = 0; i < tbcList.size(); i ++) {
							Tbclass tbc = tbcList.get(i);
							if(student.getClaId().toString().trim().equals(tbc.getClaId().toString().trim())) 
							{
					 %>
							<option value="<%=tbc.getClaId() %>" selected>-- <%=tbc.getClaName() %> --</option>
							<% } else { %>
							<option value="<%=tbc.getClaId() %>">-- <%=tbc.getClaName() %> --</option>
					<% } } } %>
				</select>
			</div>
			<div class="divider"></div>
			<p>
				<label>性别：</label>
				<% if(!Verify.isEmpty(student) && student.getStuSex().equals("1")) { %>
					<input type="radio" value="1" name="sex" checked />男
					<input type="radio" value="2" name="sex" />女
				<% } else { %>
					<input type="radio" value="1" name="sex" />男
					<input type="radio" value="2" name="sex" checked />女
				<% } %>
			</p>
			<p>
				<label>电话：</label>
				<input name="phone" class="" type="text" size="30" value="${student.stuTel }" />
			</p>
			<p>
				<label>邮箱：</label>
				<input name="email" class="required email" type="text" size="30" value="${student.stuEmail }" alt="不能为空,如：123456@qq.com" />
			</p>
			<p>
				<label>状态：</label>
				<select name="status">
					<%
						for(int i = 0; null != statusList && i < statusList.size(); i ++) {
							Map<String, String> map = statusList.get(i);
							if(!Verify.isEmpty(student) && !Verify.isEmpty(student.getStatus()) && student.getStatus().equals(ArrayUtil.getMapValue("id", map))) {
							%>
								<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
							<%
							} else {
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } } %>
				</select>
			</p>
			<div class="divider"></div>
			<p class="text-center">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</p>
		</div>
	</form>
</div>
