<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Map<String, String>> statusList 	= (List<Map<String, String>>) request.getAttribute("status_list");
	List<Tbgrade> gradeList 				= (List<Tbgrade>) request.getAttribute("gradeList");
 %>

<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/student/add.do" id="add-student-grade" class="student-form pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>学号：</label>
				<input name="id" type="text" size="30" value="" alt="数字,且不超过12个字符" class="required"/>
			</p>
			<p>
				<label>姓名：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="不超过16个字符"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>班级：</label>
				<select name="" class="required add-student-grade">
					<%
						if(!Verify.isEmpty(gradeList)) {
					%>
						<option value="">-- 请选择年级 --</option>
					<%
							for(Tbgrade grade : gradeList) {
							%>
								<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
							<%
							}
						}
					 %>
				</select>
			</div>
			<div class="divider"></div>
			<p>
				<label>性别：</label>
				<input type="radio" value="1" name="sex" checked />男
				<input type="radio" value="2" name="sex" />女
			</p>
			<p>
				<label>电话：</label>
				<input name="phone" class="" type="text" size="30" value="" alt=""/>
			</p>
			<p>
				<label>邮箱：</label>
				<input name="email" class="required email" type="text" size="30" value="" alt="不能为空,如：123456@qq.com"/>
			</p>
			<p>
				<label>状态：</label>
				<select name="status">
					<%
						for(int i = 0; null != statusList && i < statusList.size(); i ++) {
							Map<String, String> map = statusList.get(i);
					 %>
							<option value="<%=ArrayUtil.getMapValue("id", map) %>"> -- <%=ArrayUtil.getMapValue("name", map) %> -- </option>
					<% } %>
				</select>
			</p>
			<div class="divider"></div>
			<p class="text-center">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="add-student">保存</button></div></div></li>
					
				</ul>
			</p>
		</div>
	</form>
</div>

