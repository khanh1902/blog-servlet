<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Dashboard</title>

</head>

<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-md-3 col-xl-4">
                    <div class="card bg-c-blue order-card">
                        <div class="card-block">
                            <h6 class="m-b-20">Total Categories News</h6>
                            <h2 class="text-right"><i class="fa fa-bars f-left"></i><span>${categories}</span></h2>
                        </div>
                    </div>
                </div>


                <div class="col-md-3 col-xl-4">
                    <div class="card bg-c-green order-card">
                        <div class="card-block">
                            <h6 class="m-b-20">Total Newspaper</h6>
                            <h2 class="text-right"><i class="fa fa-newspaper-o f-left"></i><span>${news}</span></h2>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-xl-4">
                    <div class="card bg-c-yellow order-card">
                        <div class="card-block">
                            <h6 class="m-b-20">Total Comments</h6>
                            <h2 class="text-right"><i class="fa fa-comment f-left"></i><span>${comments}</span></h2>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-xl-4">
                    <div class="card bg-c-pink order-card">
                        <div class="card-block">
                            <h6 class="m-b-20">Total Users</h6>
                            <h2 class="text-right"><i class="fa fa-user f-left"></i><span>${users}</span></h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->
</body>
</html>
