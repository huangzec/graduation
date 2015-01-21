<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> messageList 	= (List<Message>) request.getAttribute("messageList");
	List<Message> unreadList 	= (List<Message>) request.getAttribute("unreadList");
 %>
<div class="accountInfo">
	<div class="alertInfo">
		<h2>最新消息：</h2>
		<div id="latest-news" class="latest-news">
          <%
          	if(null != unreadList && 0 < unreadList.size()) {
          		Message message = unreadList.get(0);
           %>
          <a target="navTab" rel="unread" href="<%=basePath %>admin/message/detail.do?id=<%=message.getId() %>" title="<%=message.getName() + " 【" + message.getCreateTime() +  "】" %>">
          	<%=StringUtil.cutString(message.getName(), 20) %>
          </a>
          <% } %>
        </div>
	</div>
	<div class="right">
		<p>
			未读消息 <%=((null == unreadList || 1 > unreadList.size()) ? "0" : unreadList.size()) %> 条，
			消息 <%=((null == messageList || 1 > messageList.size()) ? "0" : "<a target=\"navTab\" title=\"消息\" rel=\"read\" href=\"" + basePath + "admin/message/list.do\">" + messageList.size() + "</a>") %> 条
		</p>
		<p>今天是：<%=HResponse.formatDateTime(HResponse.formatDate(new Date())) %> ，<%=HResponse.todayOfWeek() %></p>
	</div>
	<p><span>您好: <%=request.getSession().getAttribute("user_name") %>，系部管理员</span></p>
</div>

<div class="pageCentent">
	<%
		if(null != unreadList && 0 < unreadList.size()) {
	%>
	<ul>
		<li class="text-center">未读消息</li>
		<li><span>日期</span>标题</li>	
	<%	
			for(int i = 0; i < unreadList.size(); i ++) {
				Message message = unreadList.get(i);
	 %>
	 	<li>
	 		<span><%=message.getCreateTime() %></span>
	 		<a target="navTab" title="<%=message.getName() %>" href="<%=basePath %>admin/message/detail.do?id=<%=message.getId() %>">
	 			<%=StringUtil.cutString(message.getName(), 100) %>
	 		</a>
	 	</li>
	 	<% } %>
	 </ul>
	 <% } %>
</div>
