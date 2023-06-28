<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<div class="row">
    <div class="col-md-2">
        <div class="list-group">
            <a class="list-group-item"><b>Category</b></a>
            <c:forEach var="item" items="${categoryModel.listResult}">
                <a href="<c:url value="/new?cateId=${item.id}&offset=1&limit=8&sortName=id&asc=true"/>"
                   class="list-group-item">${item.name}</a>
            </c:forEach>
        </div>
    </div>


    <div class="col-md-10">
        <div class="row">
            <c:forEach var="item" items="${newsModel.listResult}">
                <div class="col-sm-9 col-lg-9 col-md-9">
                    <div class="thumbnail">
                        <h4><a href="<c:url value="/new?id=${item.id}"/> ">${item.title}</a>
                        </h4>
                        <div class="caption">
                            <div class="col-sm-4 col-lg-4 col-md-4">
                                <img src="${item.thumbnail}" alt="" class="img-responsive">
                            </div>

                            <div class="col-sm-7 col-lg-7 col-md-7">
                                <p>${item.shortDescription}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
