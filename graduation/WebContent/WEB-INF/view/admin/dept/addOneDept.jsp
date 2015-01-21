<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Map<String, String>> list = (List<Map<String, String>>) request.getAttribute("majortype_list");
 %>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/dept/addOneDept.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>系部编号：</label>
				<input name="deptId" type="text" size="30" class="required" alt="请输入系部编号" />
			</p>
			<div class="divider"></div>
			<p>
				<label>系部名称：</label>
				<input name="deptName" class="required" type="text" size="30" alt="请输入系部名称" />
			</p>
			<div class="divider"></div>
			<p>
				<label>系部专业类型：</label>
				<select name="major-type">
					<%
						for(int i = 0; null != list && i < list.size(); i ++) {
							Map<String, String> map = list.get(i);
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } %>
				</select>
			</p>
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