<%@page language="java" import="java.util.*,java.util.Map.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Tbgrade> list 	= (List<Tbgrade>) request.getAttribute("gradeList");
	Department department 		= (Department) request.getSession().getAttribute("department");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/revieworder/addview.do" id="score-search" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" id="review-where" layoutH="56">
			<div class="divider"></div>
			<p>
				<label>安排毕业评阅教师</label>
			</p>
			<div class="divider"></div>
			<div>
				<label>请选择要安排的单位：</label>
				<select name="grade" class="ingrade required">
					<option value="">-- 请选择 --</option>
					<%
						if(null != list && 0 < list.size()) {
							for(int i = 0; i < list.size(); i ++) {
							Tbgrade grade = list.get(i);
					 %>
					 		<option value="<%=grade.getGraId() %>"><%=grade.getGraNumber() %></option>
					 <%
					 		}
					 	}
					  %>
				</select>
			</div>
			<div class="divider"></div>
			<p>
				可按年级、专业、班级安排
			</p>
			<div class="divider"></div>
			<div class="">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent"><button type="submit" class="review-order-btn">确定</button></div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
