<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Map<String, String>> list = (List<Map<String, String>>) request.getAttribute("mark_list");
 %>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/settime/setsub.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="pageHeader">
				<label>提交审核课题时间设置</label>
			</div>
			<div class="divider"></div>
			<p>
				<label>标题：</label>
				<input name="name" type="text" size="30" value="" alt="不超过64个字符" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>年级：</label>
				<select name="granumber">
					<c:if test="${empty gradeList }">
						<option value="">-- 请先添加年级 --</option>
					</c:if>
					<c:if test="${gradeList != null }">
						<c:forEach items="${gradeList}" var="item">
						<option value="${item.graId }">-- ${item.graNumber } --</option>
						</c:forEach>
					</c:if>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>开始时间：</label>
				<input name="start_time" size="27" class="date textInput readonly valid required" datefmt="yyyy-MM-dd HH:mm:ss" type="text" value="" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<p>
				<label>结束时间：</label>
				<input name="end_time" size="27" class="date textInput readonly valid required" datefmt="yyyy-MM-dd HH:mm:ss" type="text" value="" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<p>
				<label>类　型：</label>
				<select name="mark">
					<%
						for(int i = 0; null != list && i < list.size(); i ++) {
							Map<String, String> map = list.get(i);
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } %>
				</select>
			</p>
			<div class="divider"></div>
			<div class="">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					
				</ul>
			</div>
		</div>
	</form>
</div>
