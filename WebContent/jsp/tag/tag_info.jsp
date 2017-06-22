<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL 核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL 函数标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- JSTL 国际化即格式化文本标签 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"
	src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>标签信息</title>
</head>
<body>
	<%
		String path = request.getContextPath() + "/jsp";
	%>
	<div class="container-fluid">
		<!-- 导航栏 -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=path%>/index.jsp">${userInfo == null? "个人": userInfo.loginName}博客</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=path%>/user/user_detail.jsp"
						class="fa fa-user-o">${userInfo.loginName}</a></li>
					<li><a href="<%=path%>/user/logout" class="fa fa-sign-out">注销</a></li>
				</ul>
			</div>
		</nav>
		<div class="row-fluid center-block">
			<article>
				<h2>${tagInfo.id}</h2>
				<ul style="list-style: none;">
					<li style="list-style: none;">${tagInfo.name}</li>
				</ul>
			</article>
		</div>
	</div>
</body>
</html>