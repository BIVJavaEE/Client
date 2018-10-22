<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Create an alert</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <link rel="stylesheet" type="text/css" href="../css/create-alert.css">
        <script type="text/babel" src="../js/predicate.jsx"></script>
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%--<p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>--%>
    </jsp:attribute>
    <jsp:body>
        <div class="ui main container">

            <h1 id="title"> Create an alert </h1>

            <form class="ui form" method="post">
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

                <div class="grouped field" id="predicates-list-field">
                    <label>Predicates</label>
                    <div id="predicates-list">
                        <div></div>
                    </div>
                    <div id="add-predicate"></div>
                </div>

                <button class="ui primary button">
                    Save
                </button>

            </form>



        </div>
    </jsp:body>
</t:genericpage>