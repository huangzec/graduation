<%@page language="java" import="java.util.*,java.util.Map.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Department department 		= (Department) request.getSession().getAttribute("department");
	List<Tbgrade> gradeList 	= (List<Tbgrade>) request.getAttribute("gradeList");
 %>
<div class="pageContent">
	<form method="post" action="<%=basePath %>admin/opentopicinfo/totalresult.do" id="score-search" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" id="opt-score-search" layoutH="56">
			<div class="divider"></div>
			<p>
				查看开题答辩概况
			</p>
			<div class="divider"></div>
			<div>
				<label>请选择查询条件：</label>
				<select class="ingrade" name="gradeid">
					<option value="">-- 请选择年级 --</option>
					<%
						if(!Verify.isEmpty(gradeList)) {
							for(int i = 0; i < gradeList.size(); i ++) {
								Tbgrade grade = gradeList.get(i);
								%>
								<option value="<%=grade.getGraId() %>">-- <%=grade.getGraNumber() %> --</option>
								<%
							}
						}
					 %>
				</select>
			</div>
			<div class="divider"></div>
			<div class="">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" class="optresult-search-btn">确定</button></div></div></li>
					
				</ul>
			</div>
		</div>
	</form>
</div>
