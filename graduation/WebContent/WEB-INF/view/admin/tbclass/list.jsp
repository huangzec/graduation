<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbclass> list 		= (List<Tbclass>) request.getAttribute("list");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
	List<Profession> prList = (List<Profession>) request.getAttribute("professionList");
	String keyword 			= (String) request.getAttribute("keyword");
	String keywordGrade		= (String) request.getAttribute("grade");
	String keywordProfess	= (String) request.getAttribute("profession");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/tbclass/list.do">
	<input type="hidden" name="grade" value="<%=(Verify.isEmpty(keywordGrade) ? "" : keywordGrade) %>">
	<input type="hidden" name="profession" value="<%=(Verify.isEmpty(keywordProfess) ? "" : keywordProfess) %>" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="class-form" action="<%=basePath %>admin/tbclass/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					年　级：
					<select name="grade" class="required grade">
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
					<select name="profession" class="profession">
						<%
						if(!Verify.isEmpty(prList)) {
							for(int i = 0; i < prList.size(); i ++) {
							Profession profession = prList.get(i);
							if(!Verify.isEmpty(keywordProfess) && keywordProfess.equals(profession.getProId())) {
					 %>
					 		<option value="<%=profession.getProId() %>" selected ><%=profession.getProName() %></option>
					 <%
					 			}else {
					 			%>
			 				<option value="<%=profession.getProId() %>"><%=profession.getProName() %></option>
					 			<%
					 			}
					 		}
					 	}
					  %>
					</select>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit" class="grade-btn">检索</button></div></div>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<!-- <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/tbclass/addview.do" target="navTab" rel="addtbclass"><span>添加</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/tbclass/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120" align="center">班级编号</th>
				<th width="120" align="center">班级名称</th>
				<th width="120" align="center">专　业</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<%
				if(null != list && 0 < list.size()) {
					for(int i = 0; i < list.size(); i ++) {
						Tbclass tbclass = list.get(i);
			 %>
			<tr target="sid_teacher" rel="<%=tbclass.getClaId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
				<td align="center"><%=tbclass.getClaId() %></td>
				<td align="center"><%=tbclass.getClaName() %></td>
				<td align="center"><%=HResponse.formatValue("profession", tbclass.getProId(), request) %></td>
				<td>
					<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/tbclass/editview.do?id=<%=tbclass.getClaId() %>" class="btnEdit">编辑</a>
					<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/tbclass/delete.do?id=<%=tbclass.getClaId() %>" class="btnDel">删除</a>
				</td>
			</tr>
			<% } } else { %>
				<tr target="sid_teacher" rel=""><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
					<td clospans="4" align="center" style="color:red;">暂无相关记录！</td>
				</tr>
			<% } %>
		</tbody>
	</table>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
