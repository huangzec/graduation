<%@page import="com.mvc.entity.Department"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="false"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Department department = (Department) request.getSession().getAttribute("department");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/profession/add.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>专业编号：</label>
				<input name="id" type="text" size="30" value="" alt="系部编号(<%=department.getDeptId() %>)+编号,不超过10位" class="required"/>
			</p>
			<p>
				<label>专业名称：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="不超过50个字符"/>
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
					<c:if test="${empty gradeList }">
						<option value="">-- 请先添加年级 --</option>
					</c:if>
					<c:if test="${gradeList != null }">
						<c:forEach items="${gradeList}" var="item">
						<option value="${item.graId }">-- ${item.graNumber } --</option>
						</c:forEach>
					</c:if>
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
