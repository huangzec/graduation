<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/office/perfile.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>编　号：</label>
				<input name="id" type="text" size="30" value="${tbOffice.offId }" readonly="readonly" />
			</p>
			<p>
				<label>姓　名：</label>
				<input name="name" type="text" size="30" value="${tbOffice.offName }"  readonly="readonly" />
			</p>
			<div class="divider"></div>
			<p>
				<label>性　别：</label>
				<c:choose>
					<c:when test="${tbOffice.offSex == '1'}">
						<input type="radio" value="1" name="sex" checked/>男
						<input type="radio" value="2" name="sex" />女
					</c:when>
					<c:when test="${tbOffice.offSex == '2'}">
						<input type="radio" value="1" name="sex" />男
						<input type="radio" value="2" name="sex" checked />女
					</c:when>
				</c:choose>
			</p>
			<div class="divider"></div>
			<p>
				<label>联系电话：</label>
				<input name="phone" class="phone" type="text" size="30" value="${tbOffice.offTel }" alt="请输入数字"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>电子邮箱：</label>
				<input name="email" type="text" size="30" value="${tbOffice.offEmail }" alt="请输入正确的邮箱地址" class="required email"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
