<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title>Sign up</title>
        <%@ include file="../WEB-INF/jspf/scriptForm.jspf"%>
    </jsp:attribute>
    <jsp:body>
        <t:genericform>
            <jsp:attribute name="title">
                Create an account
            </jsp:attribute>
            <jsp:attribute name="action">/join</jsp:attribute>
            <jsp:attribute name="buttontext">
                Create an account
            </jsp:attribute>
        </t:genericform>
    </jsp:body>
</t:genericpage>