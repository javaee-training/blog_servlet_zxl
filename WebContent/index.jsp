<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--输出,条件,迭代标签库-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 添加外部链接 -->
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
<title>首页</title>
</head>
<body>
	<c:set var="username" value="${returnusername}"></c:set>
	<div class="container-fluid">
		<!-- 导航栏 -->
		<div class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.jsp">张三的博客</a>
				</div>
				<!-- 布局在导航栏右侧 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="./user/register.jsp">注册</a></li>
					<li><a href="./user/login.jsp"><c:choose>
								<c:when test="${username != null}">${username}</c:when>
								<c:otherwise>登录
					 </c:otherwise>
							</c:choose></a></li>
				</ul>
			</div>
		</div>
		<!-- 下面是首页正文区域 -->
	</div>
</body>
</html>