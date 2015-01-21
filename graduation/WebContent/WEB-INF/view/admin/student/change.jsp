<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Student student 		= (Student) request.getAttribute("student");
	Tbgrade ingrade 		= (Tbgrade) request.getAttribute("ingrade");
	Profession inprofession = (Profession) request.getAttribute("inprofession");
	List<Tbgrade> tbgradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
	List<Profession> professionList 	= (List<Profession>) request.getAttribute("professionList");
	List<Tbclass> tbcList 	= (List<Tbclass>) request.getAttribute("tbclassList");
	List<Map<String, String>> statusList 	= (List<Map<String, String>>) request.getAttribute("status_list");
	Department department 	= (Department) request.getSession().getAttribute("department");
	List<Department> departmentList 	= (List<Department>) request.getAttribute("departmentList");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/student/change.do" id="change-student-form" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div>
				<h1>学生基本信息</h1>
			</div>
			<div class="divider"></div>
			<p>
				<label>学号：</label>
				<input name="id" type="text" size="30" value="<%=(Verify.isEmpty(student) ? "" : student.getStuId()) %>" readonly="true" />
			</p>
			<p>
				<label>姓名：</label>
				<input name="name" type="text" size="30" alt="不超过16个字符" value="<%=(Verify.isEmpty(student) ? "" : student.getStuName()) %>"  readonly="true" />
			</p>
			<div class="divider"></div>
			<div>
				<label>原所属单位：</label>
				<select>
					<option><%=(Verify.isEmpty(department) ? "" : department.getDeptName()) %></option>
				</select>
				<select class="ingrade">
					<%
						if(!Verify.isEmpty(ingrade)) {
							%>
							<option value="<%=ingrade.getGraId() %>">-- <%=ingrade.getGraNumber() %> --</option>
							<%
						}
					 %>
				</select>
				<select class="inprofession">
					<%
						
						if(!Verify.isEmpty(inprofession)) {
							%>
							<option value="<%=inprofession.getProId() %>">-- <%=inprofession.getProName() %> --</option>
							<%
						}
					 %>
				</select>
				<select name="claid" class="inclass">
					<% if(!Verify.isEmpty(tbcList) && !Verify.isEmpty(student)) {
						for(int i = 0; i < tbcList.size(); i ++) {
							Tbclass tbc = tbcList.get(i);
							if(student.getClaId().toString().trim().equals(tbc.getClaId().toString().trim())) 
							{
					 %>
							<option value="<%=tbc.getClaId() %>" selected>-- <%=tbc.getClaName() %> --</option>
							<% } %>
					<% } } %>
				</select>
			</div>
			<div class="divider"></div>
			<div>
				<label>转到单位：</label>
				<select class="change-department">
					<option value="">-- 请选择 --</option>
					<%
						if(!Verify.isEmpty(departmentList)) {
							for(int i = 0; i < departmentList.size(); i ++) {
								Department d = departmentList.get(i);
								%>
								<option value="<%=d.getDeptId() %>">-- <%=d.getDeptName() %> --</option>
								<%
							}
						}
					 %>
				</select>
			</div>
			<div class="divider"></div>
			<p>
				<label>性别：</label>
				<input type="text" readonly="true" size="30" value="<%=HResponse.formatValue("sex", (Verify.isEmpty(student) ? "" : student.getStuSex()), request) %>" />
			</p>
			<p>
				<label>电话：</label>
				<input name="phone" readonly="true" type="text" size="30" value="<%=(Verify.isEmpty(student) ? "" : student.getStuTel()) %>" />
			</p>
			<p>
				<label>邮箱：</label>
				<input name="email" readonly="true" type="text" size="30" value="<%=(Verify.isEmpty(student) ? "" : student.getStuEmail()) %>" alt="不能为空,如：123456@qq.com" />
			</p>
			<p>
				<label>状态：</label>
				<input type="text" readonly="true" size="30" value="<%=HResponse.formatValue("status", (Verify.isEmpty(student) ? "" : student.getStatus()), request) %>" />
			</p>
			<div class="divider"></div>
			<p class="text-center">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="change-btn">确定转系</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</p>
		</div>
	</form>
</div>
