<%@page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/room/add.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>教室编号：</label>
				<input name="number" type="text" size="30" value="" alt="不超过64个字符" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>教室名称：</label>
				<input name="name" type="text" size="30" value="" alt="不超过64个字符" />
			</p>
			<div class="divider"></div>
			<p>
				<label>是否可用：</label>
				<input type="radio" name="usable" value="1" checked />可用
				<input type="radio" name="usable" value="2" />不可用
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
