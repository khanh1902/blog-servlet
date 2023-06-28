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
            <c:forEach var="item" items="${categoryModel}">
                <a href="<c:url value="/new?cateId=${item.id}&offset=1&limit=8&sortName=id&asc=true"/>" class="list-group-item">${item.name}</a>
            </c:forEach>
        </div>
    </div>


    <div class="col-md-10">
        <%--        <form action="<c:url value='/home'/>" id="formSubmit" method="get">--%>
        <form action="<c:url value='/new'/>" id="formSubmit" method="get">
            <div class="row">
                <c:forEach var="item" items="${model.content}">
                    <div class="col-sm-6 col-lg-6 col-md-6">
                        <div class="thumbnail">
                            <h4><a href="<c:url value="/new?id=${item.id}"/>">${item.title}</a>
                            </h4>
                            <div class="caption">
                                <div class="col-sm-5 col-lg-5 col-md-5">
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
            <nav aria-label="Page navigation">
                <ul class="pagination" id="pagination"></ul>
            </nav>
            <input type="hidden" value="" id="cateId" name="cateId"/>
            <input type="hidden" value="" id="offset" name="offset"/>
            <input type="hidden" value="" id="limit" name="limit"/>
            <input type="hidden" value="" id="sortName" name="sortName"/>
            <input type="hidden" value="" id="asc" name="asc"/>
        </form>
    </div>

</div>
<script type="text/javascript">
    var currentPage = ${model.currentPage};
    var totalPages = ${model.totalPages};
    var limit = ${model.maxPageItems};
    var sortName = "${model.sortName}";
    var asc = ${model.asc};
    var cateId = ${model.cateId}
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: totalPages,
            startPage: currentPage,
            onPageClick: function (event, offset) {
                if (currentPage != offset) {
                    $('#cateId').val(cateId);
                    $('#limit').val(limit);
                    $('#offset').val(offset);
                    $('#sortName').val(sortName);
                    $('#asc').val(asc);
                    $('#formSubmit').submit(); // submit luon o su kien cuoi cung
                }
            }
        });
    });
</script>
</body>
</html>
