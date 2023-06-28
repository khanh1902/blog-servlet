<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
    <div id="formContent">

        <!-- Login Form -->
        <form action="<c:url value="/login"/>" method="POST">

            <c:if test="${not empty message}">
                <div class="alert alert-${alert}" role="alert">
                        ${message}
                </div>
            </c:if>
            <h1>Login</h1>

            <input type="text" id="username" class="fadeIn second" name="username" placeholder="username">
            <input type="text" id="password" class="fadeIn third" name="password" placeholder="password">

            <input type="hidden" value="login" name="action" />
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Password -->
        <div id="formFooterPP">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

    </div>
</body>
</html>
