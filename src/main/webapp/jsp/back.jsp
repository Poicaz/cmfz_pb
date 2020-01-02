<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--bootstrap的核心css--%>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <%--jqGrip--%>
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--jquery.js--%>
    <script src="../boot/js/jquery-3.4.1.min.js"></script>
    <%--国际化--%>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%--jqgrid核心js--%>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <%--bootstrap.js--%>
    <script src="../jsp/assets/bootstrap/js/bootstrap.min.js"></script>
    <%--ajaxFiledUpload--%>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <title>Document</title>
    <script>

    </script>
    <style>
        #footText {
            padding-top: 15px;
        }
    </style>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="col-sm-2">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapsed">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand"><strong>持明法洲管理系统</strong></a>
            </div>
        </div>
        <ul class="nav navbar-nav pull-right">
            <li><a href="#">欢迎：<b>${sessionScope.username}</b></a></li>
            <li><a href="#">退出登录&nbsp;<span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
    </div>
</nav>

<%--手风琴--%>
<div class="container-fluid">
    <div class="col-sm-2">
        <div class="panel-group" id="accordion">
            <%--用户--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center">
                        <a href="#collapseOne" data-toggle="collapse" data-parent="#accordion">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body">
                        <%--
                        javascript:void(0):阻止页面提交
                        --%>
                        <a href="#collapseOne" class="btn btn-danger col-lg-12">
                            用户列表
                        </a>
                    </div>
                </div>
            </div>
            <%--上师管理--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center">
                        <a href="#collapseTwo" data-toggle="collapse" data-parent="#accordion">
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse">
                    <div class="panel-body">
                        <button class="btn btn-danger col-lg-12">上师列表</button>
                    </div>
                </div>
            </div>
            <%--文章管理--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center">
                        <a href="#collapseThree" data-toggle="collapse" data-parent="#accordion">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse">
                    <div class="panel-body">
                        <a class="btn btn-danger col-lg-12"  href="javascript:$('#myContent').load('articles.jsp')">
                            文章列表
                        </a>
                    </div>
                </div>
            </div>
            <%--专辑管理--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center">
                        <a href="#collapseFour" data-toggle="collapse" data-parent="#accordion">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse">
                    <div class="panel-body">
                        <a href="javascript:$('#myContent').load('album.jsp')" class="btn btn-danger col-lg-12">
                                专辑列表
                        </a>
                    </div>
                </div>
            </div>
            <%--轮播图管理--%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center">
                        <a href="#collapseFive" data-toggle="collapse" data-parent="#accordion">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse">
                    <div class="panel-body">
                        <a class="btn btn-danger col-lg-12" href="javascript:$('#myContent').load('banner.jsp')">
                            轮播图列表
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--巨幕--%>
    <div class="col-sm-10" id="myContent">
        <div class="jumbotron">
            <h3 style="color: #8B8388">欢迎来到持明法洲后台系统</h3>
        </div>
        <%--大图轮播--%>
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <%--1--%>
                <div class="item active">
                    <img src="../img/shouye.jpg" alt="...">
                </div>
                <%--2--%>
                <div class="item">
                    <img src="../img/shouye.jpg" alt="...">
                </div>
                <%--3--%>
                <div class="item">
                    <img src="../img/shouye.jpg" alt="...">
                </div>
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container text-center" id="footText">
        @百知教育baizhi@zparkhr.com.cn
    </div>
</nav>
</body>
</html>