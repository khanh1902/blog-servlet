<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-comment"/>
<c:url var="NewURL" value="/new"/>
<html>
<head>
    <title>News</title>
</head>
<body>
<div class="row">

    <div class="col-md-12">
        <div class="thumbnail">
            <div class="caption-full">
                <h1>${newModel.title}</h1>
                <hr>
                <!-- <br/> -->
                <p>${newModel.shortDescription}</p>
                <br/>
                <img class="img-responsive" src="${newModel.thumbnail}" alt="">
                <br/>

                <p>${newModel.content}</p>
            </div>
        </div>
        <div class="well">
            <div class="ratings">
                <p class="pull-left">Comment</p>
            </div>
            <hr>
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert}">
                        ${messageResponse}
                </div>
            </c:if>
            <%-- Add commnent --%>
            <form id="formSubmit">
                <div class="row">
                    <div class="col-md-12">
                        <b>Comment</b>
                        <div class="col-sm-pull">
                            <input type="text" class="form-control" id="content" name="content" value=""/>
                        </div>
                        <br>
                        <input type="button" class="btn btn-white btn-warning btn-bold"
                               value="Comment" id="btnAddComment"/>
                    </div>
                </div>
                <hr>
            </form>
            <c:forEach var="comment" items="${commentModel}">
                <div class="row">
                    <div class="col-md-12">
                        <c:forEach var="user" items="${userModel}">
                            <c:if test="${comment.userId == user.id}">
                                <b>${user.fullname}</b>
                            </c:if>
                        </c:forEach>
                        <span class="pull-right">${comment.createdDate}</span>
                        <p>${comment.content}</p>
                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    $('#btnAddComment').click(function (e) {
        e.preventDefault(); // tranh submit nham url
        var data = {};
        data["content"] = $('#content').val();
        data["newId"] = ${newModel.id};
        // console.log(data);
        addComment(data)
    });

    function addComment(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NewURL}?id=${newModel.id}&message=comment_success";
            },
            error: function (error) {
                window.location.href = "${NewURL}?id=${newModel.id}&message=error_system";
            }
        });
    }
</script>
</body>
</html>
