<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Map<String, Object> map = (Map<String, Object>) request.getAttribute("exp");
	Exception e 			= (Exception) map.get("ex");
 %>

<!DOCTYPE html>
<html lang="en">
    <head>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
      <title>系统助手提示您</title>
    	<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>favicon.ico" />
        <link href="<%=basePath %>vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="<%=basePath %>css/ace.min.css" />
        <script language="javascript" src="<%=basePath %>vendor/jquery/jquery.js"></script>
        <script language="javascript" src="<%=basePath %>vendor/bootstrap/js/bootstrap.min.js"></script>
        <script language="javascript" src="<%=basePath %>vendor/hhjslib/hhjslib.js"></script>
        <script language="javascript" src="<%=basePath %>vendor/hhjslib/hhjslib.file.js"></script>
        <script language="javascript" src="<%=basePath %>vendor/hhjslib/hhjslib.dialog.js"></script>
        <script language="javascript" src="<%=basePath %>js/hjz-extend.js"></script>
        <script type="text/javascript">
            $(function() {
                var typeMap     = {
                    status200: 'success',
                    status201: 'info',
                    status400: 'warning',
                    status500: 'error'
                };
                HHJsLib.dialog(
                    "<strong><%=e.getMessage() %></strong>"
                    + "<i style='font-weight: 400;'>（请您稍等，<b id='close-time'>3</b>秒后页面将跳转...）</i>",
                    typeMap['status500']
                );
                setTimeout(function() {
                   history.go(-1);
                }, 3000);
                $(function() {
                    setInterval(function() {
                        var time    = parseInt($('#close-time').html()) - 1;
                        time        = time < 1 ? 0 : time;
                        $('#close-time').html(time);
                    }, 800);
                });
            }); 
        </script>
    </head>
    <body></body>
</html>

