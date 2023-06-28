<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-user"/>
<c:url var="UserURL" value="/admin-user"/>
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
                    <li class="active">News</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <h2>New Management</h2>
                        <br/>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Category</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="roleId" name="roleId">
                                    <option value="">Choose Role</option>
                                    <c:forEach var="role" items="${roleModel}">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Username</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="username" name="username" value=""/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Fullname</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="fullname" name="fullname" value=""/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Password</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="password" name="password" value=""/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Confirm Password</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="confirmPassword" name="confirmPassword"
                                       value=""/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="button" class="btn btn-success btn-bold" value="Add User"
                                       id="btnAddUser"/>
                                <br/>
                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                   title="Cancel"
                                   href="<c:url value="/admin-user?type=list&offset=1&limit=8&sortName=id&asc=true"/>">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div><!-- /.main-content -->
<script>
    $('#btnAddUser').click(function (e) {
        e.preventDefault(); // tranh submit nham url
        var data = {};
        var formData = $('#formSubmit').serializeArray(); // lay data tu cac tag thong qua name = ""
        $.each(formData, function (index, value) {
            data["" + value.name + ""] = value.value;
        });
        addUser(data)

    });

    function addUser(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${UserURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${UserURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=error_insert";
            }
        });
    }
</script>
</body>
</html>
