<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--输出,条件,迭代标签库-->
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
<title>登录</title>
</head>
<body>
	<script type="text/javascript">
		function check() {
			alert("!");
		}
	</script>
	<c:set var="returnusername" value="${returnusername}"></c:set>
	<div class="container-fluid">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="../index.jsp">张三的博客</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="./register.jsp">注册</a></li>
					<li><a href="./login.jsp"><c:choose>
								<c:when test="${returnusername != null}">张希乐</c:when>
								<c:when test="${returnusername == null}">登录</c:when>
							</c:choose></a></li>
				</ul>
			</div>
		</nav>
		<div class="row-fluid center-block" style="max-width: 400px;">
			<div id="isEmpty" class="alert alert-danger" role="alert"
				style="visibility: hidden;">
				<i class="fa fa-exclamation-triangle" aria-hidden="true">用户名不能为空</i>
			</div>
			<div class="center-block">
				<form action="login" method="post" class="form-signin">
					<div class="panel panel-default">
						<div class="panel-heading">登录</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="usernameInput">用户名</label> <input type="text"
									name="username" id="username" class="form-control">
							</div>
							<div class="form-group">
								<label for="passwordInput">密码</label> <input type="password"
									name="password" id="inputpassword" class="form-control">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block"
									onclick="check()">登录</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>