<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Topicorderreview review 	= (Topicorderreview) request.getAttribute("topicorderreview");
 %>

<div class="pageHeader">
	<div class="text-center">
		课题评审安排【<%=review.getName() %>】
	</div>
	<div class="divider"></div>
	<div class="text-center">评审年份：<%=review.getJudgeYear() %> 年</div>
</div>
<div class="pageContent">
	<%=HResponse.formatJudgeValue(review.getJudgeTea(), request) %>
</div>
