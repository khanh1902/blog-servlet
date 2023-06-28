<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>
<html>
<head>
    <title>List News</title>
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
                    <li class="active">News</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert}">
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <h2>New Management</h2>
                        <br/>

                        <a class="btn btn-success" data-toggle="tooltip"
                           title="Add New" href="<c:url value="/admin-new?type=edit"/>"> <i class="fa fa-solid fa-plus"
                                                                                            aria-hidden="true"></i>
                        </a>

                        <a id="btnDelete" class="btn btn-danger" data-toggle="tooltip"
                           title="Delete News" href=''> <i class="fa fa-solid fa-trash" aria-hidden="true"></i>
                        </a>
                        <br/>

                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" id="checkAll"></th>
                                    <th scope="col">Id</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Category</th>
                                    <th scope="col">Created By</th>
                                    <th scope="col">Created Date</th>
                                    <th scope="col">Option</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${model.content}">
                                    <tr>
                                        <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                        <th scope="row">${item.id}</th>
                                        <td>${item.title}</td>
                                        <td>${item.categoryId}</td>
                                        <td>${item.createdBy}</td>
                                        <td>${item.createdDate}</td>
                                        <td>
                                            <a class="btn btn-sm btn-success btn-views" data-toggle="tooltip"
                                               title="Views" href="<c:url value="/new?id=${item.id}"/>"> <i class="fa fa-solid fa-eye"
                                                                         aria-hidden="true"></i>
                                            </a>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Update new"
                                               href="<c:url value="/admin-new?type=edit&id=${item.id}"/>"> <i
                                                    class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <nav aria-label="Page navigation">
                            <ul class="pagination" id="pagination"></ul>
                        </nav>
                        <input type="hidden" value="" id="type" name="type"/>
                        <input type="hidden" value="" id="offset" name="offset"/>
                        <input type="hidden" value="" id="limit" name="limit"/>
                        <input type="hidden" value="" id="sortName" name="sortName"/>
                        <input type="hidden" value="" id="asc" name="asc"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div><!-- /.main-content -->
<script type="text/javascript">
    var currentPage = ${model.currentPage};
    var totalPages = ${model.totalPages};
    var limit = ${model.maxPageItems};
    var sortName = "${model.sortName}";
    var asc = ${model.asc};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: totalPages,
            startPage: currentPage,
            onPageClick: function (event, offset) {
                if (currentPage != offset) {
                    $('#limit').val(limit);
                    $('#offset').val(offset);
                    $('#sortName').val(sortName);
                    $('#asc').val(asc);
                    $('#type').val('list');
                    $('#formSubmit').submit(); // submit luon o su kien cuoi cung
                }
            }
        });
    });


    //delete
    $("#btnDelete").click(function () {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        deleteNew(data);
    });

    function deleteNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${NewURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${NewURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=error_system";
            }
        });
    }
</script>
</body>
</html>
