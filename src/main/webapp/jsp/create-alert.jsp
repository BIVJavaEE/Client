<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Create an alert</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="bundles/create-alert.css">
    </jsp:attribute>

    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div id="create-alert-content"></div>
        <script type="text/javascript" src="bundles/create-alert.js"></script>
    </jsp:body>

</t:genericpage>