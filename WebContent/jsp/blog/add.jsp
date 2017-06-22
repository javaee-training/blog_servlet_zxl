<%@page import="com.doufuding.java.util.PostgresDriver"%>
<%@page import="java.util.List"%>
<%@page import="com.doufuding.java.model.TagInfo"%>
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
<title>添加博客</title>
</head>
<body>
	<%
		String path = request.getContextPath() + "/jsp";
	    PostgresDriver postgresDriver = new PostgresDriver();
	    List<TagInfo> tagInfos = postgresDriver.geTagInfos();
	    session.setAttribute("tagInfos", tagInfos);
	%>
	<div class="container-fluid">
		<!-- 导航栏 -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=path%>/index.jsp">${userInfo == null? "个人": userInfo.loginName}博客</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=path%>/blog/view.jsp" class="fa fa-plus">文章</a></li>
					<li><a href="<%=path%>/tag/add.jsp" class="fa fa-plus">标签</a></li>
					<li><a href="<%=path%>/user/user_detail.jsp"
						class="fa fa-user-o">${userInfo.loginName}</a></li>
					<li><a href="<%=path%>/user/logout" class="fa fa-sign-out">退出</a></li>
				</ul>
			</div>
		</nav>
		<div class="row-fluid">
			<div class="center-block">
				<!-- 警告提示框 -->
				<c:choose>
					<c:when test="${userCheckResultBlogAdd == null}"></c:when>
					<c:otherwise>
						<div id="isEmpty" class="alert alert-danger" role="alert">
							<i class="fa fa-exclamation-triangle" aria-hidden="true">${userCheckResultBlogAdd}</i>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 表单 -->
				<form action="blogAdd" method="post" class="form-signin">
					<div class="panel panel-default">
						<div class="panel-heading">录入文章</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="title">标题</label> <input type="text" id="title"
									name="title" class="form-control">
							</div>
							<div class="form-group">
								<label for="tag">标签</label> <select name="tagSelect"
									class="form-control">
									<option value="0" selected="selected">请选择标签</option>
									<c:forEach items="${tagInfos}" var="tagInfo">
										<option value="${tagInfo.id}">${tagInfo.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="content">内容</label>
								<ul class="nav nav-tabs">
									<li class="active"><a href="#">编辑</a></li>
									<li><a href="#">预览</a></li>
								</ul>
								<textarea rows="5" name="content" class="form-control"></textarea>
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