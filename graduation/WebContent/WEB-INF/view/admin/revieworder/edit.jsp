<%@page language="java" import="java.util.*,java.util.Map.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Revieworder revieworder 	= (Revieworder) request.getAttribute("revieworder");
	Teacher teacher 			= (Teacher) request.getAttribute("teacher");
	Student student 			= (Student) request.getAttribute("student");
	Tbgrade grade 				= (Tbgrade) request.getAttribute("grade");
	Selectfirst selectfirst		= (Selectfirst) request.getAttribute("selectfirst");
	Department department 		= (Department) request.getSession().getAttribute("department");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/revieworder/edit.do" id="review-edit" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>修改毕业评阅教师</label>
			</p>
			<input type="hidden" name="id" value="<%=(Verify.isEmpty(revieworder) ? "" : revieworder.getId()) %>" />
			<div class="divider"></div>
			<div>
				<label>学生</label>
				<%=(Verify.isEmpty(student) ? "" : (student.getStuId() + "【" + student.getStuName() + "】")) %>
			</div>
			<div class="divider"></div>
			<div>
				<label>当前毕业评阅教师</label>
				<%=(Verify.isEmpty(teacher) ? "" : (teacher.getTeaId() + "【" + teacher.getTeaName() + "】")) %>
			</div>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>安排新的毕业评阅教师</dt>
				<dd>
					<input name="tea.id" value="" type="hidden">
					<textarea name="tea.name" cols="60" rows="2" readonly="true"></textarea>
					<a class="btnLook" href="<%=basePath %>admin/revieworder/lookuptea.do?id=<%=(Verify.isEmpty(teacher) ? "" : teacher.getTeaId()) %>&orderid=<%=(Verify.isEmpty(selectfirst) ? "" : selectfirst.getTeaId()) %>" lookupGroup="tea">
						查找带回
					</a>
					<span class="info">(查找回带)</span>
				</dd>
			</dl>
			<div class="divider"></div>
			<p>
				<label>学生所属年级</label>
				<label><%=(Verify.isEmpty(grade) ? "" : grade.getGraNumber()) %></label>
			</p>
			<div class="divider"></div>
			<p>
				<label>当前状态</label>
				<label>
					<%=(Verify.isEmpty(revieworder) ? "" : HResponse.formatValue("status", revieworder.getStatus(), request)) %>
				</label>
			</p>
			<div class="divider"></div>
			<div class="">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent"><button type="submit" class="reviewedit-btn">确定修改</button></div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
