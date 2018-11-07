<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title>Sign in</title>
        <%@ include file="../WEB-INF/jspf/scriptForm.jspf"%>
    </jsp:attribute>
    <jsp:body>
        <t:genericform>
            <jsp:attribute name="title">
                Sign in to your account
            </jsp:attribute>
            <jsp:attribute name="action">/login</jsp:attribute>
            <jsp:attribute name="buttontext">
                Sign in
            </jsp:attribute>
            <jsp:attribute name="forgot">
                <a style="float: right; position: absolute; right: 5%;" href="#">Forgot password ?</a>
            </jsp:attribute>
            <jsp:attribute name="bottominfo">
                <div class="ui message">
                    New to this app? <a href="${pageContext.request.contextPath}/join">Create an account.</a>
                </div>
            </jsp:attribute>
        </t:genericform>
    </jsp:body>
</t:genericpage>