<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-user"/>
<c:url var ="RegisterURL" value="/register"/>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div id="formContent">

    <!-- Login Form -->
    <form id="formSubmit">

        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert}">
                    ${messageResponse}
            </div>
        </c:if>
        <h1>Register</h1>

        <input type="text" id="username" class="fadeIn second" name="username" placeholder="username">

        <input type="text" id="fullname" class="fadeIn second" name="fullname" placeholder="fullname">

        <input type="text" id="password" class="fadeIn third" name="password" placeholder="password">

        <input type="text" id="confirmPassword" class="fadeIn second" name="confirmPassword" placeholder="confirmPassword">


        <input type="button" class="fadeIn fourth" value="Register" id="btnRegister">

    </form>

    <!-- Remind Password -->
    <div id="formFooterPP">
        <a class="underlineHover" href="<c:url value="/login?action=login"/>">Login</a>
    </div>

</div>

<script>
    $('#btnRegister').click(function (e){
        e.preventDefault(); // tranh submit nham url
        var data = {};
        var formData = $('#formSubmit').serializeArray(); // lay data tu cac tag thong qua name = ""
        $.each(formData, function (index, value){
            data[""+value.name+""] = value.value;
        });
        register(data)
    });
    function register(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${RegisterURL}?action=register&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${RegisterURL}?action=register&message=error_system";
            }
        });
    }
</script>
</body>
</html>
