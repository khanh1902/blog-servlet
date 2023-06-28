<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/home"/>">Daily Blog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty USERMODEL}">
                    <c:if test="${USERMODEL.roleId == roleAdminModel.id}">
                        <li>
                            <a href="<c:url value="/admin-home"/>">Admin page </a>
                        </li>
                    </c:if>

                    <li>
                        <a href="">Welcome, ${USERMODEL.fullname} </a>
                    </li>
                    <li>
                        <a href="<c:url value="/logout?action=logout"/>">Logout</a>
                    </li>
                </c:if>
                <c:if test="${ empty USERMODEL}">
                    <li>
                        <a href="<c:url value="/login?action=login"/>">Login</a>
                    </li>
                    <li>
                        <a href="<c:url value="/register?action=register"/>">Register</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
