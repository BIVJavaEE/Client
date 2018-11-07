<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title>Sign in</title>
        <%@ include file="../WEB-INF/jspf/scriptForm.jspf"%>
    </jsp:attribute>
    <jsp:body>
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h2 class="ui blue image header">
                    <div class="content">
                        Sign in to your account
                    </div>
                </h2>
                <form class="ui small form segment left aligned" action="login" method="post">
                    <c:if test="${not empty loginError}">
                        <div class="ui negative message">
                             ${loginError}
                        </div>
                    </c:if>
                    <div class="field">
                        <label>Username</label>
                        <input type="text" name="username">
                    </div>
                    <a style="float: right; position: absolute; right: 5%;" href="#">Forgot password ?</a>
                    <div class="field">
                        <label>Password</label>
                        <input type="password" name="password">
                    </div>
                    <div class="ui fluid large blue submit button">
                        Sign in
                    </div>

                    <div class="ui error message"></div>

                </form>

                <div class="ui message">
                    New to this app? <a href="${pageContext.request.contextPath}/join">Create an account.</a>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>