<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  
<%
	Department department 	= (Department) request.getSession().getAttribute("department");
	Deptmanager deptmanager = (Deptmanager) request.getAttribute("manager");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/manager/file.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>编　号：</label>
				<input name="id" type="text" size="30" value="${manager.dmId }" readonly="readonly" />
			</p>
			<p>
				<label>姓　名：</label>
				<input name="name" type="text" size="30" value="${manager.dmName }"  readonly="readonly" />
			</p>
			<div class="divider"></div>
			<p>
				<label>所属系部：</label>
				<input type="text" name="deptid" size="30" value="<%=(department.getDeptId() + "【" + department.getDeptName()) + "】" %>"  readonly="readonly" />	
			</p>
			<p>
				<label>性　别：</label>
				<% if(deptmanager.getDmSex().equals("1")) { %>
					<input type="radio" value="1" name="sex" checked />男
					<input type="radio" value="2" name="sex" />女
				<% } else { %>
					<input type="radio" value="1" name="sex" />男
					<input type="radio" value="2" name="sex" checked />女
				<% } %>
			</p>
			<div class="divider"></div>
			<p>
				<label>联系电话：</label>
				<input name="phone" class="phone" type="text" size="30" value="${manager.dmTel }" />
			</p>
			<p>
				<label>电子邮箱：</label>
				<input name="email" class="required email" type="text" size="30" value="${manager.dmEmail }" />
			</p>
			<div class="divider"></div>
			<div class="">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改保存</button></div></div></li>
					
				</ul>
			</div>
		</div>
	</form>
</div>
