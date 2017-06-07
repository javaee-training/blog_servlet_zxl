<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>关于</title>
</head>
<body>
	<div class="container-fluid">
		<!-- 导航栏 -->
		<c:set var="username" value="${returnusername}"></c:set>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="../index.jsp">张三的博客</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${username != null}">
							<li><a href="./user/user_detail.jsp">${username}</a></li>
							<li><a href="">注销</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="./user/register.jsp">注册</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		<div class="row-fluid">
			<h2>功能描述</h2>
			<p>本系统仅支持一人注册，第一个人注册的用户升级为博主，支持以markdown语法编写博客，匿名与登录用户可浏览博客。</p>
		</div>
	</div>
</body>
</html>