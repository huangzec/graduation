<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Teacher> list 			= (List<Teacher>) request.getAttribute("list");
 %>
<h2 class="contentTitle">课题评审安排</h2>
<form method="post" action="<%=basePath %>admin/topic/judge.do" id="judge-form" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
	<div class="pageFormContent" layoutH="98">
		<label>请选择参与评审的老师</label>
		<div class="divider"></div>
		<% 
			if(null != list && 0 < list.size()) {
				for(int i = 0; i < list.size(); i ++) {
					Teacher teacher = list.get(i);
		 %>
			<label>
				<input type="checkbox" name="ids" value="<%=teacher.getTeaId() %>" <%= ((Verify.isEmpty(teacher.getTeaJudge()) ? false : teacher.getTeaJudge()) == true ? "checked" : "") %> />
				<%=Verify.isEmpty(teacher.getTeaName()) ? "" : teacher.getTeaName() %>
			</label>
		<% 
				}
			}else {
		 %>
			<label class="txt-center" style="color: red;">暂无相关老师</label>
		<% } %>
	</div>
	<div class="formBar">
		<label style="float:left"><input type="checkbox" class="checkboxCtrl" group="ids" />全选</label>
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="checkboxCtrl" group="ids" selectType="invert">反选</button></div></div></li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
		</ul>
	</div>
</form>

