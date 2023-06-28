<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="NewURL" value="/admin-new"/>
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
                                <select class="form-control" id="categoryId" name="categoryId">
                                    <c:if test="${empty model.categoryId}">
                                        <option value="">Choose Category</option>
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${not empty model.categoryId}">
                                        <option value="">Choose Category</option>
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.id}"
                                                    <c:if test="${item.id == model.categoryId}"> selected</c:if>>
                                                    ${item.name}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Title</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Thumbnail</label>
                            <div class="col-sm-3">
                                <input type="file" class="form-control" id="thumbnail" name="thumbnail" value="Upload"/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Short Description</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="shortDescription" name="shortDescription"
                                       value="${model.shortDescription}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Content</label>
                            <div class="col-sm-9">
                                <textarea rows="" cols="" id="content" name="content"
                                          style="width: 820px;height: 175px">${model.content}</textarea>
                            </div>
                        </div>
                        <br/>
                        <br/>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-warning btn-bold"
                                           value="Update New" id="btnAddOrUpdateNew"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-success btn-bold" value="Add New"
                                           id="btnAddOrUpdateNew"/>
                                </c:if>
                                <br/>
                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                   title="Cancel"
                                   href="<c:url value="/admin-new?type=list&offset=1&limit=8&sortName=id&asc=true"/>">
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
    $('#btnAddOrUpdateNew').click(function (e){
        e.preventDefault(); // tranh submit nham url
        var data = new FormData();
        data.append('title', $('#title').val());
        data.append('thumbnail', $('#thumbnail')[0].files[0]);
        data.append('categoryId', $('#categoryId').val());
        data.append('shortDescription', $('#shortDescription').val());
        data.append('content', $('#content').val());


        var id = $('#id').val();
        console.log(id);

        if (id == ""){
            addNew(data)
        }else {
            data.append('id', $('#id').val());
            updateNew(data)
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            data: data,
            contentType: false,
            processData: false,
            success: function (result) {
                window.location.href = "${NewURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${NewURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=error_insert";
            }
        });
    }

    function updateNew(data) {
        // var offset = $('#offset').val();
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            data: data,
            contentType: false,
            processData: false,
            success: function (result) {
                window.location.href = "${NewURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=update_success";
            },
            error: function (error) {
                window.location.href = "${NewURL}?type=list&offset=1&limit=8&sortName=id&asc=true&message=error_system";
            }
        });
    }
</script>
</body>
</html>
