<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><decorator:title default="Home"/></title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/template/user/css/bootstrap.min.css'/>" rel="stylesheet"/>

    <!-- Add custom CSS here -->
    <link href="<c:url value='/template/user/css/styles.css'/>" rel="stylesheet"/>
    <script src="<c:url value='/template/user/js/jquery-1.10.2.js'/>"></script>
    <script src="<c:url value='/template/user/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/template/user/js/jquery.twbsPagination.js' />" type = "text/javascript" ></script>



</head>
<body>
    <%-- header --%>
    <%@include file="/common/user/header.jsp"%>

    <%-- container --%>
    <div class="container">
        <decorator:body/>
    </div>

    <%-- footer --%>
    <%@include file="/common/user/footer.jsp"%>
    <!-- JavaScript -->
    <script src="<c:url value='/template/user/js/bootstrap.js'/>"></script>
</body>
</html>
