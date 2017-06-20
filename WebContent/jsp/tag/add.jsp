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
<title>添加标签</title>
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
					<li><a href="./blog/view.jsp" class="fa fa-plus">文章</a></li>
					<li><a href="./tag/add.jsp" class="fa fa-plus">标签</a></li>
					<c:choose>
						<c:when test="${userInfo != null}">
							<li><a href="<%=path%>/user/user_detail.jsp"
								class="fa fa-user-o">${userInfo.loginName}</a></li>
							<li><a href="<%=path%>/user/logout" class="fa fa-sign-out">注销</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<%=path%>/user/login.jsp">登录</a></li>
						</c:otherwise>
					</c:choose>
					<!-- 
                <li><a href="" class="fa fa-user-o">张三</a></li>
                <li><a href="" class="fa fa-sign-out">退出</a></li>
                 -->
				</ul>
			</div>
		</nav>
		<div class="row-fluid">
			<div class="center-block">
				<!-- 信息提示框，警告框 -->
				<c:choose>
					<c:when test="${userCheckResultTag == null}"></c:when>
					<c:otherwise>
						<div id="isEmpty" class="alert alert-danger" role="alert">
							<i class="fa fa-exclamation-triangle" aria-hidden="true">${userCheckResultTag}</i>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 表单 -->
				<form action="tagadd" method="post" class="form-signin">
					<div class="panel panel-default">
						<div class="panel-heading">录入标签</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="tags">标签</label> <input type="text" id="tags"
									name="tags" class="form-control">
							</div>
							<div class="form-group" style="float: right;">
								<button type="submit" class="btn btn-default">保存</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>