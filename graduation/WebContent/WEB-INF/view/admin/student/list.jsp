<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Student> list 		= (List<Student>) request.getAttribute("list");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
	List<Profession> prList = (List<Profession>) request.getAttribute("professionList");
	String keywordGrade		= (String) request.getAttribute("grade");
	String keywordProfess	= (String) request.getAttribute("profession");
 %>
<form id="pagerForm" method="post" action="<%=basePath %>admin/student/stulist.do">
	<input type="hidden" name="keywordid" value="${keywordid}">
	<input type="hidden" name="keywordname" value="${keywordname}" />
	<input type="hidden" name="grade" value="${grade}" />
	<input type="hidden" name="profession" value="${profession}" />
	<input type="hidden" name="tbclass" value="${tbclass}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="student-form" action="<%=basePath %>admin/student/stulist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					学号：<input type="text" name="keywordid" />
				</td>
				<td>
					姓名：<input type="text" name="keywordname" />
				</td>
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
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="stu-search">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=basePath %>admin/student/addoneview.do" target="navTab" rel="addstudent"><span>添加</span></a></li>
			<li><a class="delete" href="<%=basePath %>admin/student/delete.do?id={sid_teacher}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=basePath %>admin/student/editview.do?id={sid_teacher}" target="navTab" rel="editstudent"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=basePath %>admin/student/export.do" target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:$.printBox('studentlist')"><span>打印</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=basePath %>admin/student/changeview.do?id={sid_teacher}" target="navTab" rel="changeview" title="学生转系"><span>学生转系</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="<%=basePath %>admin/index/resetpwd.do?id={sid_teacher}&permissions=1" target="dialog"><span>重置密码</span></a></li>
		</ul>
	</div>
	<div id="studentlist">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="80"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="120">学号</th>
					<th width="120">姓名</th>
					<th>班级</th>
					<th width="80">性别</th>
					<th width="150">联系电话</th>
					<th width="150">电子邮箱</th>
					<th width="100">状态</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(null != list && 0 < list.size()) {
					for(int i = 0; i < list.size(); i ++) {
						Student st = list.get(i);
			 	%>
					<tr target="sid_teacher" rel="<%=st.getStuId() %>"><!-- 当选中一行时,tr上的rel值会自动替换到url变量中.注意url变量名{sid_user}和tr的target="sid_user"保持一致 -->
						<td><input name="ids" value="<%=st.getStuId() %>" type="checkbox"></td>
						<td><%=st.getStuId() %></td>
						<td><%=st.getStuName() %></td>
						<td><%=HResponse.formatValue("tbclass", st.getClaId(), request) %></td>
						<td>
							<%=HResponse.formatValue("sex", st.getStuSex(), request) %>
						</td>
						<td><%=st.getStuTel() %></td>
						<td><%=st.getStuEmail() %></td>
						<td>
							<%=HResponse.formatValue("status", st.getStatus(), request) %>
						</td>
						<td>
							<a title="编辑"  target="navTab" mask="true" href="<%=basePath %>admin/student/editview.do?id=<%=st.getStuId() %>" class="btnEdit">编辑</a>
							<a title="删除" target="ajaxTodo" href="<%=basePath %>admin/student/delete.do?id=<%=st.getStuId() %>" class="btnDel">删除</a>
						</td>
					</tr>
				<% } } %>
				<% if(null == list || 1 > list.size()) { %>
					<tr>
						<td clospans="8" style="text-align:center;color:red;font-size:14px;">暂无相关记录</td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<jsp:include page="../common/page.jsp" flush="true" />
</div>
