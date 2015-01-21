<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Opentopicinfo op 	= (Opentopicinfo) request.getAttribute("opentopicinfo");
 %>

<div class="pageHeader">
	<div class="text-center">
		学生<%=op.getStuId() %> 
		【<%=HResponse.formatValue("stuId", op.getStuId(), request) %>】的开题答辩详细安排
	</div>
	<div class="divider"></div>
	<div class="text-center">安排时间：<%=op.getCreateTime() %></div>
</div>
<div class="pageContent">
	<form method="post" action="#" id="add-student-grade" class="student-form pageForm required-validate">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>学生：</label>
				<input type="text" size="30" value="<%=(op.getStuId() + "【" + HResponse.formatValue("stuId", op.getStuId(), request) + "】") %>" readonly="true"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>开题日期：</label>
				<input type="text" size="30" value="<%=op.getOpiDate() %>"  readonly="true"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>开题地点：</label>
				<input type="text" size="30" value="<%=op.getOpiPlace() %>"  readonly="true"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>答辩序号：</label>
				<input type="text" size="30" value="<%=op.getOpiNumber() %>"  readonly="true"/>
			</p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>评审教师：</dt>
				<dd>
					<textarea name="" cols="100" rows="6" readonly="true"><%=HResponse.formatJudgeValue(op.getJudge(), request) %></textarea>
				</dd>
			</dl>
			<div class="divider"></div>
			<p>
				<label>评审组长：</label>
				<input type="text" readonly="true" size="30" value="<%=(op.getHeaderman() + "【" + HResponse.formatValue("headerman", op.getHeaderman(), request) + "】") %>" />
			</p>
			<div class="divider"></div>
			<p>
				<label>组别：</label>
				<input type="text" readonly="true" value="<%=HResponse.formatValue("begroup", op.getBegroup(), request) %>" />
			</p>
			<div class="divider"></div>
		</div>
	</form>
</div>
