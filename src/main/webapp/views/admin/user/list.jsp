<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-user"/>
<c:url var="UserURL" value="/admin-user"/>
<html>
<head>
    <title>User</title>
</head>
<body>
<div class="main-content">
    <form action="<c:url value='/admin-user'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Users</li>
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
                        <h2>User Management</h2>
                        <br/>

                        <a class="btn btn-success" data-toggle="tooltip"
                           title="Add New" href="<c:url value="/admin-user?type=edit"/>"> <i class="fa fa-solid fa-plus"
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
                                    <th scope="col">Username</th>
                                    <th scope="col">Fullname</th>
                                    <th scope="col">Role</th>
                                    <th scope="col">Created Date</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${model.content}">
                                    <tr>
                                        <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                        <th scope="row">${item.id}</th>
                                        <td>${item.username}</td>
                                        <td>${item.fullname}</td>
                                        <c:forEach var="role" items="${roleModel}">
                                            <c:if test="${item.roleId == role.id}">
                                                <td>${role.name}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${item.createdDate}</td>
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
                window.location.href = "${UserURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${UserURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=error_system";
            }
        });
    }
</script>
</body>
</html>
