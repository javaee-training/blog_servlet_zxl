<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>编辑</title>
</head>
<body>
<div class="container-fluid">
    <!-- 导航栏 -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">张三的博客</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="./blog/view.jsp" class="fa fa-plus">文章</a></li>
            <li><a href="./tag/add.jsp" class="fa fa-plus">标签</a></li>
            <li><a href="" class="fa fa-user-o">张三</a></li>
            <li><a href="" class="fa fa-sign-out">退出</a></li>
        </ul>
    </nav>
    <div class="row-fluid">
        <div class="center-block">
            <!-- 表单 -->
            <form action="blog_edit" method="post" class="form-signin">
                <div class="panel panel-default">
                    <div class="panel-heading">编辑文章</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="title">标题</label>
                            <input type="text" id="title" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="tag">标签</label>
                            <select class="form-control">
                                <option value = "诗词歌赋">诗词歌赋</option>
                                <option value="技术笔记">技术笔记</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="content">内容</label>
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#">编辑</a></li>
                                <li><a href="#">预览</a></li>
                            </ul>
                            <textarea rows="5" class="form-control"></textarea>
                        </div>
                        <div class="form-group" style="float: right;">
                            <button type="button" class="btn btn-default">保存</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>