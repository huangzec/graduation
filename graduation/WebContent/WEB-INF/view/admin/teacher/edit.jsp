<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Teacher teacher 	= (Teacher) request.getAttribute("teacher");
	List<Map<String, String>> list 	= (List<Map<String, String>>) request.getAttribute("pos_list");
	List<Map<String, String>> statusList 	= (List<Map<String, String>>) request.getAttribute("status_list");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/teacher/edit.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>教师工号：</label>
				<input name="id" type="text" size="30" value="${teacher.teaId }" class="required" readonly="readonly"/>
			</p>
			<p>
				<label>教师姓名：</label>
				<input name="name" class="required" type="text" size="30" value="${teacher.teaName }" alt="不超过16个字符"/>
			</p> 
			<div class="divider"></div>
			<p>
				<label>所属系部：</label>
				<input type="hidden" name="parent_id" value="${department.deptId}"/>
				<input type="text" readonly="readonly" name="" value="${department.deptName }" />	
			</p> 
			<div class="divider"></div>
			<p>
				<label>性别：</label>
				<% if(teacher.getTeaSex().equals("1")) { %>
					<input type="radio" value="1" name="sex" checked />男
					<input type="radio" value="2" name="sex" />女
				<% } else { %>
					<input type="radio" value="1" name="sex" />男
					<input type="radio" value="2" name="sex" checked />女
				<% } %>
			</p>
			<p>
				<label>职称：</label>
				<select name="pos">
					<%
						for(int i = 0; null != list && i < list.size(); i ++) {
							Map<String, String> map = list.get(i);
							if(teacher.getTeaPos().equals(ArrayUtil.getMapValue("id", map))) {
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% }else { %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } } %>
				</select>
			</p>
			<p>
				<label>状态：</label>
				<select name="status">
					<%
						for(int i = 0; null != statusList && i < statusList.size(); i ++) {
							Map<String, String> map = statusList.get(i);
							if(teacher.getStatus().equals(ArrayUtil.getMapValue("id", map))) {
						%>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<%
							}else {
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } } %>
				</select>
			</p>
			<p>
				<label>联系电话：</label>
				<input type="text" size="30" name="phone"  class="phone" value="${teacher.teaTel }"/>
			</p>
			<p>
				<label>电子邮箱：</label>
				<input type="text" size="30" name="email" value="${teacher.teaEmail }" alt="不能为空,如：123456@qq.com" class="required email" />
			</p>
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>