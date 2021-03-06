<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Map<String, String>> list 	= (List<Map<String, String>>) request.getAttribute("mark_list");
	List<Tbgrade> gradeList 		= (List<Tbgrade>) request.getAttribute("gradeList");
	Settime settime 				= (Settime) request.getAttribute("settime"); 
 %>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/settime/edit.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="pageHeader">
				<label>提交审核课题时间设置</label>
			</div>
			<div class="divider"></div>
			<input type="hidden" name="id" value="<%=settime.getId() %>" />
			<p>
				<label>标题：</label>
				<input name="name" type="text" size="30" value="<%=settime.getName() %>" alt="不超过64个字符" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>年级：</label>
				<select name="granumber">
					<% if(null == gradeList || 1 > gradeList.size()) { %>
						<option>-- 请先添加年级 --</option>
					<% 
						} else {
						for(int i = 0; i < gradeList.size(); i ++) {
							Tbgrade grade = gradeList.get(i);
							if(settime.getGraNumber().toString().trim().equals(grade.getGraId().toString().trim())) 
							{
					 %>
							<option value="<%=grade.getGraId() %>" selected>-- <%=grade.getGraNumber() %> --</option>
							<% } else { %>
							<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
					<% } } } %>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>开始时间：</label>
				<input name="start_time" size="27" class="date textInput readonly valid required" datefmt="yyyy-MM-dd HH:mm:ss" type="text" value="<%=settime.getStartTime() %>" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<p>
				<label>结束时间：</label>
				<input name="end_time" size="27" class="date textInput readonly valid required" datefmt="yyyy-MM-dd HH:mm:ss" type="text" value="<%=settime.getEndTime() %>" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<p>
				<label>类　型：</label>
				<select name="mark">
					<%
						for(int i = 0; null != list && i < list.size(); i ++) {
							Map<String, String> map = list.get(i);
							if(settime.getMark().equals(ArrayUtil.getMapValue("id", map))) {
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% }else { %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } } %>
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
