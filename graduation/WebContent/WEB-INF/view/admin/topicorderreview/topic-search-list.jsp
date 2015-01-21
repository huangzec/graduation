<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbtopic> list 		= (List<Tbtopic>) request.getAttribute("list");
	Department department 	= (Department) request.getSession().getAttribute("department");
	String submiter 		= (String) request.getAttribute("submiter");
	String topicStatus 		= (String) request.getAttribute("topicstatus");
	String tag		 		= (String) request.getAttribute("tag");
	List<Map<String, String>> statusList 	= (List<Map<String, String>>) request.getAttribute("status_list");
	
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
	List<Profession> prList = (List<Profession>) request.getAttribute("professionList");
	String keyword 			= (String) request.getAttribute("keyword");
	String keywordGrade		= (String) request.getAttribute("grade");
	String keywordProfess	= (String) request.getAttribute("profession");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/topicorderreview/topicsearchlist.do">
	<input type="hidden" name="submittype" value="${submittype}">
	<input type="hidden" name="submiter" value="${submiter}" />
	<input type="hidden" name="topicstatus" value="${topicstatus}" />
	<input type="hidden" name="grade" value="${grade}" />
	<input type="hidden" name="profession" value="${profession}" />
	<input type="hidden" name="tbclass" value="${tbclass}" />
	<input type="hidden" name="tag" value="${tag}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="topic-form" action="<%=basePath %>admin/topicorderreview/topicsearchlist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					年　级：
					<select name="grade" class="required topicgrade">
						<option value="">-- 请选择 --</option>
						<%
							if(!Verify.isEmpty(gradeList)) {
								for(int i = 0; i < gradeList.size(); i ++) {
								Tbgrade grade = gradeList.get(i);
								if(!Verify.isEmpty(keywordGrade) && Integer.parseInt(keywordGrade) == grade.getGraId()) {
						 %>
						 		<option value="<%=grade.getGraId() %>" selected ><%=grade.getGraNumber() %></option>
						 <%
						 			}else {
						 			%>
				 				<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
						 			<%
						 			}
						 		}
						 	}
						  %>
					</select>
				</td>
				<td>
					专　业：
					<select name="profession" class="topicprofession">
						
					</select>
				</td>
				<td>
					班　级：
					<select name="tbclass" class="topicclass">
						
					</select>
				</td>
			</tr>
			<tr>
				<td>
					提交人：
					<input type="text" size="30" value="<%=(Verify.isEmpty(submiter) ? "" : submiter) %>" name="submiter" alt="请输入你要查询的提交人ID" />
				</td>
				<td>
					课题关键字：
					<input type="text" name="tag" value="<%=(Verify.isEmpty(tag) ? "" : tag) %>" alt="请输入课题关键字"  />
				</td>
				<td>
					<div class="buttonActive">
						<div class="buttonContent"><button type="submit" class="topic-search">检索</button></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li><a class="edit" href="<%=basePath %>admin/topicorderreview/edittopicnameview.do?id={sid_topic}" target="navTab" rel="edittopicname"><span>修改课题</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('topic-search-list')"><span>打印</span></a></li>
			<li class="line">line</li>			
		</ul>
	</div>
	<div id="topic-search-list">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="40">*</th>
					<th width="120">提交人</th>
					<th width="200">课题名称</th>
					<th width="40">完成人数</th>
					<th width="80">课题类型</th>
					<th width="80">课题来源</th>
					<th width="80">课题关键字</th>
					<th width="80">年份</th>
					<th width="80">状态</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++) {
						Tbtopic topic = list.get(i);
				%>
				<tr target="sid_topic" rel="<%=topic.getTopId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=(i + 1) %></td>
					<td><%=topic.getTopCommitId() %></td>
					<td><%=topic.getTopName() %></td>
					<td><%=topic.getTopNumber() %></td>
					<td><%=HResponse.formatValue("toptype", topic.getTopType(), request)  %></td>
					<td><%=HResponse.formatValue("topsource", topic.getTopSource(), request)  %></td>
					<td><%=HResponse.formatEmpty(topic.getTopKeywords(), request) %></td>
					<td><%=topic.getTopYear() %></td>
					<td><%=HResponse.formatValue("status", topic.getTopStatus(), request)  %></td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(list)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="10" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
