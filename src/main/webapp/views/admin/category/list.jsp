<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-category"/>
<c:url var="CategoryURL" value="/admin-category"/>
<html>
<head>
    <title>List Category</title>
</head>
<body>
<div class="main-content">
    <form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Category</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-9">
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert}">
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <h2>Category Management</h2>
                        <br/>

                        <a class="btn btn-success" data-toggle="tooltip"
                           title="Add Category" href="<c:url value="/admin-category?type=edit"/>"> <i class="fa fa-solid fa-plus"
                                                                                            aria-hidden="true"></i>
                        </a>

                        <a id="btnDelete" class="btn btn-danger" data-toggle="tooltip"
                           title="Delete Category" href=''> <i class="fa fa-solid fa-trash" aria-hidden="true"></i>
                        </a>
                        <br/>

                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" id="checkAll"></th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">code</th>
                                    <th scope="col">Option</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${model.listResult}">
                                    <tr>
                                        <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                        <th scope="row">${item.id}</th>
                                        <td>${item.name}</td>
                                        <th scope="row">${item.code}</th>
                                        <td>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Update Category"
                                               href="<c:url value="/admin-category?type=edit&id=${item.id}"/>"> <i
                                                    class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div><!-- /.main-content -->
<script type="text/javascript">
    //delete
    $("#btnDelete").click(function () {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        deleteCategory(data);
    });

    function deleteCategory(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${CategoryURL}?type=list&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${CategoryURL}?type=list&message=error_system";
            }
        });
    }
</script>
</body>
</html>
