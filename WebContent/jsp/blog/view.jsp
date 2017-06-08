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
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>查看</title>
</head>
<body>
<div class="container-fluid">
    <!-- 导航栏 -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.jsp">张三的博客</a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="./register.jsp">注册</a></li>
                <li><a href="./login.jsp">登录</a></li>
            </ul>
        </div>
    </nav>
    <div class="row-fluid">
        <article>
            <h2>鹿柴</h2>
            <label class="fa fa-tags">诗词歌赋</label>
            <div>
                <!-- 时间格式有待更改 -->
                <label>王维 2017年1月1日 10:00</label>
                <a href="./edit.html">编辑</a>
            </div>
            <ul style="{list-style: none;}">
                <li style="list-style: none;">空山不见人，但闻人语声。</li>
                <li style="list-style: none;">返影入深林，复照青苔上。</li>
            </ul>
        </article>
    </div>
</div>
</body>
</html>