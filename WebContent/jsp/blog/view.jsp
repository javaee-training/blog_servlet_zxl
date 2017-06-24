<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>查看</title>
</head>
<body>
	<div class="container-fluid">
		<%
			String path = request.getContextPath() + "/jsp";
		%>
		<!-- 导航栏 -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=path%>/index.jsp">${userInfo == null? "个人": userInfo.loginName}博客</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${userInfo != null}">
							<li><a href="<%=path%>/user/userDetail">${userInfo.loginName}</a></li>
							<li><a href="<%=path%>/user/logout">注销</a></li>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		<div class="row-fluid center-block">
			<article>
				<h2>${blogInfo.title}</h2>
				<label class="fa fa-tags">${blogInfo.tags}</label>
				<div>
					<!-- 时间格式有待更改 -->
					<label>${blogInfo.createUserName} ${blogInfo.createTime}</label> <a href="<%=path%>/blog/blogEdit?id="${blogInfo.id}>编辑</a>
				</div>
				<ul style="list-style: none;">
					<li style="list-style: none;">${blogInfo.content}</li>
				</ul>
			</article>
		</div>
	</div>
</body>
</html>