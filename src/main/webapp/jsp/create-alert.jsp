<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Create an alert</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/create-alert.css">
        <link rel="stylesheet" type="text/css" href="bundles/predicates-list.css">
    </jsp:attribute>

    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>

        <h1 id="title"> Create an alert </h1>
        <form class="ui form" method="get">

            <h2>General information</h2>
            <div class="field">
                <label>Name</label>
                <input type="text" name="name">
            </div>
            <div class="grouped fields">
                <label>Priority</label>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="priority" value="high" checked="checked">
                        <label>High</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="priority" value="medium">
                        <label>Medium</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="priority" value="low">
                        <label>Low</label>
                    </div>
                </div>
            </div>

            <h2>Predicates</h2>
            <div id="predicates-list"></div>

            <h2>Sensors</h2>

            <button class="ui primary button">
                Save
            </button>

        </form>
        <script type="text/javascript" src="bundles/predicates-list.js"></script>
    </jsp:body>

</t:genericpage>