<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/topicorderreview/add.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标　　题：</label>
				<input name="name" type="text" size="30" value="" alt="如：2014年课题评审教师安排" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>评审年份：</label>
				<input type="text" name="judge-year" class="date" dateFmt="yyyy" minDate="2000-01-15" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>评审教师：</dt>
				<dd>
					<input name="tea.id" value="" type="hidden">
					<textarea name="tea.name" cols="60" rows="15" readonly="true"></textarea>
					<a class="btnLook" href="<%=basePath %>admin/topicorderreview/lookup.do" lookupGroup="tea">查找带回</a>
					<span class="info">(查找回带)</span>
				</dd>
			</dl>
			<div class="divider"></div>
			<div class=""><div class="buttonContent"><button type="submit">保存</button><button type="button" class="close">取消</button></div>
				
			</div>
		</div>
	</form>
</div>
