<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title><decorator:title default="Admin Home" /></title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <link rel = "stylesheet" href = " <c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />" />
    <link rel = "stylesheet" href = "<c:url value = '/template/admin/css/bootstrap.min.css'/>"/>
    <link rel = "stylesheet" href = "<c:url value = '/template/admin/css/fonts.googleapis.com.css'/>"/>
    <link rel = "stylesheet" href = "<c:url value = '/template/admin/css/ace.min.css'/>"/>
    <link rel = "stylesheet" href = "<c:url value = '/template/admin/css/ace-skins.min.css'/>"/>
    <link rel = "stylesheet" href = "<c:url value = '/template/admin/css/ace-rtl.min.css'/>"/>
    <link rel = "stylesheet" href = "<c:url value = '/template/admin/css/styles.css'/>"/>
    <script src="<c:url value='/template/admin/js/bootstrap.min.js' /> " ></script>
    <script src="<c:url value='/template/admin/js/ace-extra.min.js' /> "></script>
    <script src="<c:url value='/template/admin/js/jquery-2.1.4.min.js' /> "></script>
    <script src="<c:url value='/template/admin/js/jquery.twbsPagination.js' />" type = "text/javascript" ></script>


</head>
    <body class="no-skin">
        <!-- header -->
        <%@ include file  = "/common/admin/header.jsp" %>

        <!-- container -->
        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.loadState('main-container')
                } catch (e) {}
            </script>
            <!-- menu -->
            <%@ include file="/common/admin/menu.jsp" %>
            <!-- /.menu -->
            <div class="main-content">
                <decorator:body/>
            </div>
            <!-- footer -->
            <%@ include file="/common/admin/footer.jsp" %>
            <!-- /.footer -->

            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
            </a>
        </div>
        <script src="<c:url value='/template/admin/js/bootstrap.min.js' />" type = "text/javascript"></script>
        <script src="<c:url value='/template/admin/js/jquery-ui.custom.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.ui.touch-punch.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.easypiechart.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.sparkline.index.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.flot.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.flot.pie.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.flot.resize.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/ace-elements.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/ace.min.js' />"></script>
    </body>
</html>
