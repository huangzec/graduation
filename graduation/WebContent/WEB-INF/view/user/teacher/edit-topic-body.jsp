<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mvc.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="form-horizontal">
	<div class="control-group">
		<label class="control-label" style="margin-top: 6px;">课题类型</label>
		<div class="controls" id="tptype">
			<label class="radio inline">
				<input type="radio" name="topType" value="1" />1.论文
			</label>
			<label class="radio inline">
				<input type="radio" name="topType" value="2" />2.设计
			</label>
			<label class="radio inline">
				<input type="radio" name="topType" value="3" />3.其他
			</label>
		</div>
		<!-- /controls -->
	</div>
	<div class="control-group">
		<label class="control-label" style="margin-top: 6px;">课题来源</label>
		<div class="controls" id="tpsource">
			<label class="radio inline">
				<input type="radio" name="source" value="1" />1.科学技术
			</label>
			<label class="radio inline">
				<input type="radio" name="source" value="2" />2.生产实践
			</label>
			<label class="radio inline">
				<input type="radio" name="source" value="3" />3.社会经济
			</label>
			<label class="radio inline">
				<input type="radio" name="source" value="4" />4.自拟
			</label>
			<label class="radio inline">
				<input type="radio" name="source" value="5" />5.其他
			</label>
		</div>
		<!-- /controls -->
	</div>

	<div class="control-group">
		<label class="control-label" for="topicname">课题名称</label>
		<div class="controls">
			<input name="topName" id="topicname" class="input-medium"type="text" style="width: 590px;" />
		</div>
		<!-- /controls -->
	</div>
	<!-- /control-group -->

	<div class="control-group">
		<label class="control-label" for="topnum">完成人数</label>
		<div class="controls">
			<select id="topnum" name="topnum" style="width: 600px;">
				<option value="1" />1
				<option value="2" />2
				<option value="3" />3
				<option value="4" />4
			</select>
		</div>
		<!-- /controls -->
	</div>
	<!-- /control-group -->
	
	<div class="control-group" style="padding-top: 10px;">
		<label class="control-label" for="topContent">课题内容</label>
		<div class="controls">
			<script id="editor" type="text/plain" name="topContent"></script>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="topkeywords">关键字</label>
		<div class="controls">
			<input name="topkeywords" class="input-medium" id="topkeywords" type="text" style="width: 590px;" />
		</div>
		<!-- /controls -->
	</div>
	<div class="form-actions">
		<button class="btn btn-success edit-ok-btn">确定</button>
	</div>
</div>