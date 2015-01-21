<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/message/add.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>发送消息</label>
			</p>
			<div class="divider"></div>
			<p>
				<label>标题：</label>
				<input name="name" type="text" size="30" value="" class="required"/>
			</p>
			<div class="divider"></div>
			<div class="">
				<label>详细内容：</label>
				<div class="divider"></div>
				<script id="content" name="content" type="text/plain">
																
				</script>
			</div>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>收件人：</dt>
				<dd style="width: 660px;">
					<input name="org.id" value="" type="hidden">
					<textarea name="org.name" rows="10" cols="50" class="required"></textarea>
					<a class="btnLook" href="<%=basePath %>admin/message/lookupstu.do" lookupGroup="org">查找带回</a>
					<span class="info">(发给学生)</span>
					<a class="btnLook" href="<%=basePath %>admin/message/lookuptea.do" lookupGroup="org">查找带回</a>
					<span class="info">(发给教师)</span>
					<a class="btnLook" href="<%=basePath %>admin/message/lookupman.do" lookupGroup="org">查找带回</a>
					<span class="info">(发给教务处管理员)</span>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认发送</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消发送</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<!-- 配置文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=basePath %>vendor/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var content 	= UE.getEditor('content', {initialFrameWidth: 1000});
</script>
