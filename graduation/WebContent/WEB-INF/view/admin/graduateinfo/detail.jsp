<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Tballdoc tballdoc 	= (Tballdoc) request.getAttribute("tballdoc");
 %>

<div class="pageHeader">
	<div class="text-center">
		毕业生 
		<%=(Verify.isEmpty(tballdoc) ? "" : tballdoc.getStuId()) + "【" + HResponse.formatValue("stu", Verify.isEmpty(tballdoc) ? "" : tballdoc.getStuId(), request) + "】" %> 
		最终上交的材料
	</div>
	<div class="divider"></div>
	<div class="text-center">提交时间： <%=Verify.isEmpty(tballdoc) ? "" : tballdoc.getCreateTime() %></div>
</div>
<div class="pageContent">
	<form method="post" action="<%=basePath %>" class="pageForm required-validate">
		<div class="pageFormContent" layoutH="56">
			<%
				out.println(
					Verify.isEmpty(tballdoc) ? "" : ("<a href=\"" + basePath + tballdoc.getFilePath() + 
					"\" target=\"_bank\">" + tballdoc.getDocContent() + "</a>")
				); 
			%>			
		</div>
	</form>
</div>
