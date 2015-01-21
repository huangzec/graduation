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
	<form method="post" action="<%=basePath %>admin/graduateinfo/totalrows.do" id="graduation-score-search" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="56">
			<div class="divider"></div>
			<p>
				查看毕业论文（设计）总成绩
			</p>
			<div class="divider"></div>
			<div>
				<label>请选择查询的条件：</label>
				<select name="gradeid" class="ingrade required">
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
			<div class="">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="graduate-search-btn">确定</button></div></div></li>
					
				</ul>
			</div>
		</div>
	</form>
</div>
