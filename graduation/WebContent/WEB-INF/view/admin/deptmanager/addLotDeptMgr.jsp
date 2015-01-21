<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<h2 class="contentTitle">请选择需要上传的文件</h2>

<div class="pageContent">
	<form action="<%=basePath%>admin/manager/addLotDeptMgr.do" method="post" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="97">
			<dl>
				<dt>文件：</dt>
				<dd>
					<input type="file" name="file" class="required" size="30" />
					<font color="red">上传文件格式：.xls</font>
				</dd>
			</dl>
			<dl>
				<dt>excel模板下载：</dt>
				<dd><a class="buttonActive" href="<%=basePath %>uploadfiles/departmanagerinfo.xls"><span>下载模板</span></a></dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">上传</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button class="close" type="button">关闭</button>
						</div>
				</div></li>
			</ul>
		</div>
	</form>
</div>
