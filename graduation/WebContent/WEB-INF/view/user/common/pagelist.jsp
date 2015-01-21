<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mvc.entity.*, com.mvc.common.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
总记录数：${pagination.totalRecord } 条
	每页：${pagination.size } 条
	第  ${pagination.currentPage } 页/
	共 ${pagination.totalPage } 页
	<c:if test="${pagination.currentPage == 1 }">
		<span class="current">首页</span>
		<span class="current">上一页</span>
	</c:if>
	<c:if test="${pagination.currentPage != 1 }">
		<a href="javascript:void(0);" data-cur="1"  class="pagelist">首页</a>
		<a href="javascript:void(0);" data-cur="${pagination.currentPage - 1 }"  class="pagelist">上一页</a>
	</c:if>
	<c:if test="${pagination.currentPage == pagination.totalPage || pagination.totalPage == 0 }">
		<span class="current">下一页</span>
		<span class="current">末页</span>
	</c:if>
	<c:if test="${pagination.currentPage != pagination.totalPage && pagination.totalPage != 0 }">
		<a href="javascript:void(0);" data-cur="${pagination.currentPage + 1 }" class="pagelist">下一页</a>
		<a href="javascript:void(0);" data-cur="${pagination.totalPage }"  class="pagelist">末页</a>
	</c:if>
<script>
	$(function() {
		$(".pagelist").click(function() {
			var $that 	= $(this);
			var curHref = window.location.href;
			var url 	= "";
			var regex = /\?/;
			if(!regex.test(curHref)) {
				//如果不带参数
				url = curHref + "?pageNum=";
				window.location.href = url + $that.attr("data-cur");
			}else {
				//带参数
				var page = /pageNum/;
				if(!page.test(curHref)) {
					//如果没有pageNum
					url = curHref + "&pageNum=";
					window.location.href = url + $that.attr("data-cur");
				}else {
					//如果有pageNum
					var pre = /(pageNum=[0-9]+)/g;
					var curl = curHref.replace(pre, "pageNum=" + $that.attr("data-cur"));
					window.location.href = curl;
				}
			}
		});
	});
</script>