<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Room room 	= (Room) request.getAttribute("room");
	List<Map<String, String>> begroupList 	= (List<Map<String, String>>) request.getAttribute("begroup_list");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/graduateinfo/order.do" id="grade-order-room" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="pageHeader text-center">
				教室 <%=(room.getNumber() + "【" + room.getName() + "】") %> 的答辩安排
				<input type="hidden" name="place" class="place" value="<%=room.getNumber() %>" />
			</div>
			<div class="divider"></div>
			<p>
				<label>答辩时间：</label>
				<input type="text" name="date" class="date required" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>组　　别：</label>
				<select name="begroup" class="grade-begroup required" >
					<option value="">-- 请选择 --</option>
					<%
						for(int i = 0; null != begroupList && i < begroupList.size(); i ++) {
							Map<String, String> map = begroupList.get(i);
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } %>
				</select>
			</p>
			<div class="divider"></div>
			
			<dl class="nowrap">
				<dt>答辩学生：</dt>
				<dd>
					<input name="stu.id" value="" type="hidden">
					<input name="stu.applyid" value="" type="hidden" />
					<textarea name="stu.name" cols="60" rows="6" readonly="true"></textarea>
					<a class="btnLook" href="<%=basePath %>admin/graduateinfo/lookup.do" lookupGroup="stu">查找带回</a>
					<span class="info">(查找回带)</span>
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>评审教师：</dt>
				<dd>
					<input name="tea.id" class="teaid" value="" type="hidden">
					<textarea name="tea.name" cols="60" rows="6" readonly="true"></textarea>
					<a class="btnLook" href="<%=basePath %>admin/graduateinfo/lookuptea.do?place=<%=room.getNumber() %>" lookupGroup="tea">查找带回</a>
					<span class="info">(查找回带)</span>
				</dd>
			</dl>
			<div class="divider"></div>
			<p>
				<label>组长：</label>
				<select class="selec hidden" name="headername">
					
				</select>
				<button type="button" class="header" data-loaded="1">选择</button>
			</p>
			<div class="divider"></div>
			<div class="">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认安排</button></div></div></li>
					
				</ul>
			</div>
		</div>
	</form>
</div>
