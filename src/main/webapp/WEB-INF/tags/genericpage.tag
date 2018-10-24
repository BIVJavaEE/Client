<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
    <head>
        <!-- Standard Meta -->
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

        <!-- Site Properties -->
        <link rel="stylesheet" type="text/css" href="../../css/semantic.min.css">

        <script crossorigin src="https://unpkg.com/react@16/umd/react.development.js"></script>
        <script src="../../js/jquery-3.3.1.slim.min.js"></script>
        <script src="../../js/semantic.min.js"></script>
        <script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>

        <style type="text/css">
            body {
                background-color: #f9f9f9;
            }
            .ui.menu.fixed {
                z-index: 1001;
            }

            .main.container {
                padding-top: 7em;
                /*margin-bottom: -7em;*/
            }
        </style>

        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <div id="pageheader">
            <jsp:invoke fragment="header"/>
        </div>
        <div id="body">
            <div class="main ui container">
                <jsp:doBody/>
            </div>
        </div>
        <div id="pagefooter">
            <jsp:invoke fragment="footer"/>
        </div>
    </body>
</html>