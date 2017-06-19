<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL 核心标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL 函数标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- JSTL 国际化即格式化文本标签 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link
	href="http://cdn.bootcss.com/github-markdown-css/2.5.0/github-markdown.min.css"
	rel="stylesheet">
<link href="style.css" rel="stylesheet">
<script type="text/javascript"
	src="http://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"
	src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>首页</title>
</head>
<body>
	<%
		String path = request.getContextPath() + "/jsp";
	%>
	<div class="container-fluid">
		<!-- 导航栏 -->
		<div class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="<%=path%>/index.jsp">${userInfo == null? "个人": userInfo.loginName}博客</a>
				</div>
				<!-- 布局在导航栏右侧 -->
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${userInfo != null}">
							<li><a href="<%=path%>/user/user_detail.jsp">${userInfo.loginName}</a></li>
							<li><a href="">注销</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<%=path%>/user/register.jsp"><c:choose>
										<c:when test="${isRegister != null}"></c:when>
										<c:otherwise>注册</c:otherwise>
									</c:choose></a></li>
							<li><a href="<%=path%>/user/login.jsp">登录</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<!-- 下面是首页正文区域 -->
		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">文章</div>
					<ul class="list-group">
						<c:choose>
							<c:when test="${fn:length(blogs) == 0}">
								<li class="list-group-item text-center">没有文章</li>
							</c:when>
							<c:otherwise>
								<c:forEach items="${blogs}" var="blogInfo">
									<li class="list-group-item">
										<h2>
											<a href="<%=path%>/blog/view.jsp">${blogInfo.title}</a>
										</h2>
										<div>
											<c:forEach items="blogInfo.tags" var="tagInfo">
												<span class="label label-default"><i
													class="fa fa-tags" aria-hidden="true"></i>${tagInfo.name}</span>
											</c:forEach>
										</div>
										<div class="markdown-body"></div>
										<div class="text-small text-gray">
											<span>${blogInfo.createUserName}</span>
											<time class="ml-2">
												<fmt:formatDate value="${blogInfo.createTime}"
													pattern="yyyy-MM-dd HH:mm" type="date"></fmt:formatDate>
											</time>
											<a class="ml-2" href="<%=path%>/blog/view.jsp">查看更多</a> <a
												class="ml-2" href="<%=path%>/blog/edit.jsp">编辑</a>
										</div>
									</li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- 分页栏 -->
				<c:if test="${pageInfo.rows > pageInfo.rowsPerPage }">
					<nav aria-label="">
						<ul class="pager">
							<c:choose>
								<c:when test="${pageInfo.pageNum <= 1}">
									<li class="disabled"><a href="#">上一页</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/blogs?page=${pageInfo.pageNum - 1}">上一页</a>
									</li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pageInfo.pageNum >= pageInfo.pageCount}">
									<li class="disabled"><a href="#">下一页</a></li>
								</c:when>
								<c:otherwise>
									<li>
										<!-- 使用pageContext.request.contextPath 需要导入javax.servlet.jsp-api.jar-->
										<a
										href="${pageContext.request.contextPath}/blogs?page=${pageInfo.pageNum + 1}">下一页</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</c:if>
			</div>
			<!-- 标签区 -->
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">标签</div>
					<ul class="list-group">
						<c:choose>
							<c:when test="${fn.length(tags).length == 0 }">
								<li class="list-group-item text-center">没有定义标签</li>
							</c:when>
							<c:otherwise>
								<c:forEach items="${tags}" var="tagInfo">
									<li class="list-group-item"><a
										href="${pageContext.request.contextPath}/blogs?tagId=${tagInfo.id}">
											<i class="fa fa-tag" aria-hidden="true"></i> ${tagInfo.name}
									</a></li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>