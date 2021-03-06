<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="false"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<h2 class="contentTitle">请选择需要上传的文件</h2>
<div class="pageContent">
		<form action="<%=basePath%>admin/teacher/excel.do" method="post" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
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
					<dd>
						<a class="buttonActive" href="<%=basePath %>uploadfiles/teacherinfo.xls">
							<span>下载模板</span>
						</a>
					</dd>
				</dl>
				<div class="divider"></div>
				<p>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">开始批量导入</button>
						</div>
					</div>
				</p>
			</div>
		</form>
	</div>
