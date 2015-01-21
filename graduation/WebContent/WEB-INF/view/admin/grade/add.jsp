<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="false"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/grade/add.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>系部：</label>
				<input name="id" type="hidden" size="30" value="${department.deptId }" />
				<input name="name" type="text" size="30" value="${department.deptName }" readonly="readonly" />
			</p>
			<p>
				<label>届数：</label>
				<input name="number" class="required" type="text" size="30" value="" alt="如：2011届,且不超过8个字符"/>
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
