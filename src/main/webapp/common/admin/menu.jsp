<div id="sidebar" class="sidebar responsive ace-save-state">
    <script type="text/javascript">
        try {
            ace.settings.loadState('sidebar')
        } catch (e) {
        }
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <!-- /.sidebar-shortcuts -->

    <ul class="nav nav-list">
        <li>
            <a href="<c:url value="/admin-home"/>">
                <span class="menu-text"> Dashboard </span>
            </a>

            <b class="arrow"></b>
        </li>
        <li>
            <a href="<c:url value="/admin-category?type=list"/>">
                <span class="menu-text"> Category </span>
            </a>
            <b class="arrow"></b>
        </li>

        <li>
            <a href="<c:url value="/admin-new?type=list&offset=1&limit=8&sortName=id&asc=true"/>">
                <span class="menu-text"> News </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="<c:url value="/admin-comment?offset=1&limit=8&sortName=id&asc=true"/>">
                <span class="menu-text"> Comments </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="<c:url value="/admin-user?type=list&offset=1&limit=8&sortName=id&asc=true"/>">
                <span class="menu-text"> Users </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="<c:url value="/home"/>">
                <span class="menu-text"> Back to Home </span>
            </a>
            <b class="arrow"></b>
        </li>
    </ul>
    <!-- /.nav-list -->

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
           data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>