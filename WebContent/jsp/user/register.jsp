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
<title>注册</title>
</head>
<body>
	<div class="container-fluid">
		<!-- 导航栏 -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="../index.jsp">张三的博客</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="./register.jsp">注册</a></li>
					<li><a href="./login.jsp">登录</a></li>
				</ul>
			</div>
		</nav>
		<div class="row-fluid center-block" style="max-width: 400px;">
			<!-- 警告框 -->
			<div class="alert alert-danger" role="alert">
				<i class="fa fa-exclamation-triangle" aria-hidden="true">用户名不能为空</i>
			</div>
			<div class="center-block">
				<!-- 表单 -->
				<form action="register" method="post" class="form-signin">
					<div class="panel panel-default">
						<div class="panel-heading">注册账号</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="usernameInput">用户名</label> <input type="text"
									name="username" id="username" class="form-control">
							</div>
							<div class="form-group">
								<label for="passwordInput">密码</label> <input type="password"
									name="password" id="password" class="form-control">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">注册</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>