<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-category"/>
<c:url var="CategoryURL" value="/admin-category"/>
<html>
<head>
    <title>Edit New</title>
</head>
<body>
<div class="main-content">
    <form id="formSubmit">
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
                    <div class="col-xs-12">
                        <h2>Category Management</h2>
                        <br/>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">name</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="name" name="name" value="${model.name}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">code</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="code" name="code" value="${model.code}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Update Category" id="btnAddOrUpdateCategory"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-success btn-bold" value="Add Category"
                                           id="btnAddOrUpdateCategory"/>
                                </c:if>
                                <br/>
                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                   title="Cancel"
                                   href="<c:url value="/admin-category?type=list"/>">
                                    Cancel
                                </a>
                            </div>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div><!-- /.main-content -->
<script>
    $('#btnAddOrUpdateCategory').click(function (e) {
        e.preventDefault(); // tranh submit nham url
        var data = {};
        var formData = $('#formSubmit').serializeArray(); // lay data tu cac tag thong qua name = ""
        $.each(formData, function (index, value) {
            // console.log(value);
            data["" + value.name + ""] = value.value;
        });
        var id = $('#id').val();
        if (id == "") {
            addCategory(data)
        } else {
            updateCategory(data)
        }
    });

    function addCategory(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${CategoryURL}?type=list&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${CategoryURL}?type=list&message=error_system";
            }
        });
    }

    function updateCategory(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${CategoryURL}?type=list&message=update_success";
            },
            error: function (error) {
                window.location.href = "${CategoryURL}?type=list&message=error_system";
            }
        });
    }
</script>
</body>
</html>
