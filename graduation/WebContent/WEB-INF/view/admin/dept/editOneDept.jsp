<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Department department 	= (Department) request.getAttribute("department");
	List<Map<String, String>> list 	= (List<Map<String, String>>) request.getAttribute("majortype_list");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath%>admin/dept/editOneDept.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label>系部编号：</label>
				<input name="deptId" type="text" size="30" readonly="readonly" value="${deptId }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>系部名称：</label>
				<input name="deptName" class="required" type="text" size="30" value="${deptName }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>系部专业类型：</label>
				<select name="major-type">
					<%
						for(int i = 0; null != list && i < list.size(); i ++) {
							Map<String, String> map = list.get(i);
							if(department.getMajorType().equals(ArrayUtil.getMapValue("id", map))) {
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% }else { %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } } %>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
