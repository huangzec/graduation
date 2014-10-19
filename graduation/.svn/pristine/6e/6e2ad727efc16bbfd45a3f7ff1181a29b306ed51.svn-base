<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<Message> totalMessage = (List<Message>) request.getAttribute("total_message");
	List<Map<String, String>> tables = (List<Map<String, String>>) request.getAttribute("tables");
 %>
<jsp:include page="../user/common/header.jsp" flush="true" />
</head>

<body background="<%=basePath %>cscon/body-bg.png">

<div class="navbar navbar-fixed-top">
	
	<jsp:include page="../user/common/top.jsp" flush="true" />
	<!-- /navbar-inner -->
	
</div> <!-- /navbar -->

<div id="content">
	
	<div class="container">
		
		<div class="row">
			<%
				String status 	= (String) (request.getSession().getAttribute("user_status") + "");
				if(!Verify.isEmpty(status) && status.equals("2")) {
			%>
				<jsp:include page="../user/common/navmenu_tea.jsp" flush="true" />
			<%
				} else if(!Verify.isEmpty(status) && status.equals("1")) {
			%>
				<jsp:include page="../user/common/navmenu.jsp" flush="true" />
			<% } %>
			<!-- /span3 -->
			
			<div class="span9">
				
				<h1 class="page-title">
					<i class="icon-home"></i>
					我的主页					
				</h1>
				
				<jsp:include page="../user/common/center-stat.jsp" flush="true" />
				<!-- /stat-container -->
				
				<div class="widget">
					<div class="widget-header">
						<i class="icon-cog"></i><h3>构建工具模块</h3>
					</div>					
					<div class="widget-content">
						<form action="<%=basePath %>user/wizard/add.do" method="post" id="wizard-form" class="bs-docs-example form-horizontal">
							<div class="tabbable">
							  <ul class="nav nav-tabs">
							    <li class="active"><a href="#tab1" data-toggle="tab">基本信息</a></li>
							    <li><a href="#tab2" data-toggle="tab">生成文件</a></li>
							  </ul>
							  <div class="tab-content">
							    <div class="tab-pane active" id="tab1">
							    	<div class="control-group">
									    <label class="control-label" for="inputEmail">选择数据表</label>
									    <div class="controls">
									      <select id="entityname" name="entityname">
									      	<option value="">请选择数据表</option>
									      <%
									      	if(!Verify.isEmpty(tables)) {
									      		for(int i = 0; i < tables.size(); i ++ ) {
									      			Map<String, String> map = tables.get(i);
									      %>
									      			<option value="<%=map.get("name") %>"><%=map.get("name") %></option>
									      <%
									      		}
									      	}
									       %>
											</select>
									    </div>
									  </div>
									  <div class="control-group">
									    <label class="control-label">中文名称</label>
									    <div class="controls">
									      <input type="text" id="" name="entityzhname" placeholder="中文名称">
									    </div>
									  </div>
									  <div class="control-group">
									    <label class="control-label">标识</label>
									    <div class="controls">
									      <input type="text" id="" name="identifier" readonly="true" placeholder="标识">
									    </div>
									  </div>
									  <div class="control-group">
									    <label class="control-label">工程目录</label>
									    <div class="controls">
									      <input type="text" id="" name="project_dir" placeholder="工程目录">
									    </div>
									  </div>
							    </div>
							    <div class="tab-pane" id="tab2">
							      <div id="gen-files-box" class="tab-pane active">
                                        <h3 class="header smaller lighter blue">DAO数据层文件列表</h3>
                                        <div class="row-fluid">
                                            <div class="span3">
                                                <label class="checkbox inline">
                                                    <input name="dao" type="checkbox" checked value="dao"><span class="lbl">Dao文件</span>
                                                </label>
                                            </div>
                                        </div>
                                        <h3 class="header smaller lighter blue">Service服务层文件列表 </h3>
                                        <div class="row-fluid">
                                            <div class="span4">
                                                <label class="checkbox inline">
                                                    <input name="service" type="checkbox" value="service"><span class="lbl">Service服务层文件</span>
                                                </label>
                                            </div>                        
                                        </div>
                                        <h3 class="header smaller lighter blue">Controller控制层文件列表</h3>
                                        <div class="row-fluid">
                                            <div class="span4">
                                                <label class="checkbox inline">
                                                    <input name="controller" type="checkbox" value="site"><span class="lbl"> Controller前台文件</span>
                                                </label>
                                                <label class="checkbox inline">
                                                    <input name="controller" type="checkbox" value="admin"><span class="lbl"> Controller后台文件</span>
                                                </label>
                                            </div>
                                        </div>
                                      </div>
							    </div>
							  </div>
							</div>
							<div class="control-group">
								<lable class="controls" style="color: red;">${message }</lable>
							</div>
							<div class="form-actions">
							  <button type="submit" class="btn btn-primary wizardbtn"><i class="icon-ok"></i>提交</button>
							  <button type="reset" class="btn btn-info"><i class="icon-repeat"></i>重置</button>
							</div>
						</form>
					</div> <!-- /widget-content -->
					
				</div> <!-- /widget -->
			</div> <!-- /span9 -->
			
			
		</div> <!-- /row -->
		
	</div> <!-- /container -->
	
</div> <!-- /content -->
					
	
<div id="footer">
	
	<div class="container">				
		<hr />
		<jsp:include page="../user/common/footer.jsp" flush="true" />
	</div> <!-- /container -->
	
</div> <!-- /footer -->


    

<!-- Le javascript
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>cscon/js/jquery-1.7.2.min.js"></script>
<script src="<%=basePath %>cscon/js/bootstrap.js"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.js"></script>
<script src="<%=basePath %>vendor/hhjslib/hhjslib.dialog.js"></script>
<script src="<%=basePath %>js/wizard.js"></script>
  </body>
</html>