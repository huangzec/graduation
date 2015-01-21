<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Department department 	= (Department) request.getSession().getAttribute("department");
	List<Tbgrade> gradeList = (List<Tbgrade>) request.getAttribute("gradeList");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/tbclass/add.do" id="add-class-grade" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>班级编号：</label>
				<input name="id" type="text" size="30" value="" alt="系部编号(<%=department.getDeptId() %>)+年级编号+编号" class="required"/>
			</p>
			<p>
				<label>班级名称：</label>
				<input name="name" class="required" type="text" size="30" value="" alt="不超过64个字符"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>专　业：</label>
				<select name="" class="required add-class-grade">
					<%
						if(!Verify.isEmpty(gradeList)) {
					%>
						<option value="">--请选择年级--</option>
					<%
							for(int i = 0; i < gradeList.size(); i ++) {
								Tbgrade grade = gradeList.get(i);
					%>
								<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
					<%
							}
						}else {
					%>
							<option value="">-- 请先添加年级  --</option>
					<%
						}
					 %>
				</select>
			</div>
			<div class="divider"></div>
			<p class="text-center">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="add-class-btn">保存</button></div></div></li>
					
				</ul>
			</p>
		</div>
	</form>
</div>
