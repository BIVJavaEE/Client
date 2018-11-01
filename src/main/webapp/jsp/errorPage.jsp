<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%--<p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>--%>
    </jsp:attribute>
    <jsp:body>
        ${subtitle}
        <div class="ui negative message">
            <div class="header">
                ${headerError}
            </div>
            <p>${bodyError}</p>
        </div>
    </jsp:body>
</t:genericpage>