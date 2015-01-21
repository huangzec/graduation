<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post"
		action="<%=basePath%>admin/manager/addOneDeptMgr.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>编 号：</label> 
				<input name="dmId" class="digits required" type="text" size="30" alt="请输入数字" />
			</p>
			<p>
				<label>所属系部：</label> 
				<input type="hidden" name="orgLookup.id" value="${orgLookup.id}" /> 
				<input type="text" readonly="readonly" class="required" name="orgLookup.orgName" 
					value="" suggestFields="orgNum,orgName" 
					suggestUrl=""
					lookupGroup="orgLookup" /> 
				<a class="btnLook" href="<%=basePath%>admin/manager/lookup.do"
					lookupGroup="orgLookup">查找带回</a>点击查找带回
			</p>
			<div class="divider"></div>
			<p>
				<label>姓 名：</label> 
				<input name="dmName" type="text" size="30" class="required" />
			</p>
			<p>
				<label>性 别：</label> <input type="radio" name="dmSex" value="1" checked />男
				<input type="radio" name="dmSex" value="2" />女
			</p>
			<div class="divider"></div>
			<p>
				<label>联系电话：</label> <input type="text" size="30" class="phone"
					name="dmTel" />
			</p>
			<p>
				<label>电子邮箱：</label> <input type="text" size="30"
					class="required email" name="dmEmail" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
