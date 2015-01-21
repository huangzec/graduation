<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.mvc.common.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Tbtopic topic = (Tbtopic) request.getAttribute("topic");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/topicorderreview/editname.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="<%=(Verify.isEmpty(topic) ? "" : topic.getTopId()) %>" />
			<p>
				<label>课题名称：</label>
				<input name="name" type="text" size="35" value="<%=(Verify.isEmpty(topic) ? "" : topic.getTopName()) %>" alt="如：2014年课题评审教师安排" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>课题类型：</label>
				<input type="text" value="<%=HResponse.formatValue("type", (Verify.isEmpty(topic) ? "" : topic.getTopType()), request) %> 课题" readonly="true" />
			</p>
			<p>
				<label>课题所属年份：</label>
				<input type="text" readonly="true" value="<%=HResponse.formatEmpty((Verify.isEmpty(topic) ? "" : topic.getTopYear()), request) %>" />
			</p>
			<div class="divider"></div>
			<p>
				<label>课题关键字：</label>
				<input type="text" readonly="true" value="<%=HResponse.formatEmpty((Verify.isEmpty(topic) ? "" : topic.getTopKeywords()), request) %>" />
			</p>
			<p>
				<label>课题来源：</label>
				<input type="text" readonly="true" value="<%=HResponse.formatValue("topsource", (Verify.isEmpty(topic) ? "" : topic.getTopSource()), request) %>" />
			</p>
			<div class="divider"></div>
			<p>
				<label>课题内容：</label>
				<%=StringUtil.decodeHtml((Verify.isEmpty(topic) ? "" : topic.getTopContent())) %>
				
			</p>
			<div class="divider"></div>
			<div class=""><div class="buttonContent"><button type="submit">保存</button><button type="button" class="close">取消</button></div>
				
			</div>
		</div>
	</form>
</div>
