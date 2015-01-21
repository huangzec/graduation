<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Opentopicinfo> list 	= (List<Opentopicinfo>) request.getAttribute("list");
	List<Tbgrade> gradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
	List<Map<String, String>> begroupList 			= (List<Map<String, String>>) request.getAttribute("begroup_list");
	List<Map<String, String>> statusList 			= (List<Map<String, String>>) request.getAttribute("status_list");
	Department department 		= (Department) request.getSession().getAttribute("department");
	String begp					= (String) request.getAttribute("begroup");
	String status					= (String) request.getAttribute("status");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/opentopicinfo/list.do">
	<input type="hidden" name="date" value="${date}">
	<input type="hidden" name="place" value="${place}" />
	<input type="hidden" name="begroup" value="${begroup}" />
	<input type="hidden" name="status" value="${status}" />
	<input type="hidden" name="ingrade" value="${ingrade}" />
	<input type="hidden" name="profess" value="${profess}" />
	<input type="hidden" name="inclass" value="${inclass}" />
	<input type="hidden" name="pageNum	" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="open-topic-list-search" action="<%=basePath %>admin/opentopicinfo/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					答辩日期：<input type="text" size="10" name="date" value="${date}" class="date" />
				</td>
				<td>
					答辩地点：<input type="text" name="place" value="${place}" />
				</td>
				<td>
					组别：
					<select name="begroup" class="">
						<option value="">-- 请选择 --</option>
						<%
							for(int i = 0; null != begroupList && i < begroupList.size(); i ++) {
								Map<String, String> map = begroupList.get(i);
								if(!Verify.isEmpty(begp) && begp.trim().equals(ArrayUtil.getMapValue("id", map))) {
						 %>
								<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
							<% }else { %>
								<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% } } %>
					</select>
				</td>
				<td>
					状态：
					<select name="status" class="">
						<option value="">-- 请选择 --</option>
						<%
							for(int i = 0; null != statusList && i < statusList.size(); i ++) {
								Map<String, String> map = statusList.get(i);
								if(!Verify.isEmpty(status) && status.trim().equals(ArrayUtil.getMapValue("id", map))) {
						 %>
								<option value="<%=ArrayUtil.getMapValue("id", map) %>" selected> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
							<% }else { %>
								<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
						<% } } %>
					</select>
				</td>
				<td>
					年级：
					<select name="ingrade" class="ingrade">
						<option value="">请选择</option>
						<%
							if(!Verify.isEmpty(gradeList)) {
								for(int i = 0; i < gradeList.size(); i ++) {
									Tbgrade grade = gradeList.get(i);
									%>
									<option value="<%=grade.getGraId() %>">--<%=grade.getGraNumber() %>--</option>
									<%
								}
							}
						 %>
					</select>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/opentopicinfo/addview.do" target="navTab"><span>安排开题答辩</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('opentopicinfo-list')"><span>打印</span></a></li>
		</ul>
	</div>
	<div id="opentopicinfo-list">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="60">答辩序号</th>
					<th width="150">学生</th>
					<th width="80">开题答辩日期</th>
					<th width="80">开题答辩地点</th>
					<th width="300">评审教师</th>
					<th width="100">评审组长</th>
					<th width="50">组别</th>
					<th width="40">状态</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(!Verify.isEmpty(list)) {
						for(int i = 0; i < list.size(); i ++) {
						Opentopicinfo opt = list.get(i);
				%>
				<tr target="sid_teacher" rel="<%=opt.getOpiId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td><%=opt.getOpiNumber() %></td>
					<td><%=(opt.getStuId() + " 【" + HResponse.formatValue("stuId", opt.getStuId(), request) + "】 ") %></td>
					<td><%=opt.getOpiDate() %></td>
					<td><%=opt.getOpiPlace() %></td>
					<td>
						<a href="<%=basePath %>admin/opentopicinfo/detail.do?id=<%=opt.getOpiId() %>" target="navTab" rel="detail" title="查看详细">
							<%=(StringUtil.cutString(HResponse.formatJudgeValue(opt.getJudge(), request), 50)) %>
						</a>
					</td>
					<td>【<%=opt.getHeaderman() %>】<%=HResponse.formatValue("headerman", opt.getHeaderman(), request) %></td>
					<td><%=HResponse.formatValue("begroup", opt.getBegroup(), request) %></td>
					<td><%=HResponse.formatValue("status", opt.getStatus(), request) %></td>
				</tr>
				<% } } %>
				<%
					if(Verify.isEmpty(list)) {
				 %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="7" style="color:red;text-align:center;">暂无相关记录！</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
