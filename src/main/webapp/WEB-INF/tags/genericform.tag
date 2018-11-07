<%@tag description="Signin/Signup template" pageEncoding="UTF-8" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="action" fragment="true" %>
<%@attribute name="buttontext" fragment="true" %>
<%@attribute name="bottominfo" fragment="true" %>
<%@attribute name="method" fragment="true" %>
<%@attribute name="forgot" fragment="true" %>

<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2 class="ui blue image header">
            <div class="content">
                <jsp:invoke fragment="title"/>
            </div>
        </h2>
        <form class="ui small form segment left aligned" action="<jsp:invoke fragment="action"/>" method="post">
            <div class="field">
                <label>Username</label>
                <input type="text" name="username">
            </div>
            <jsp:invoke fragment="forgot"/>
            <div class="field">
                <label>Password</label>
                <input type="password" name="password">
            </div>
            <div class="ui fluid large blue submit button">
                <jsp:invoke fragment="buttontext"/>
            </div>

            <div class="ui error message"></div>

        </form>

        <jsp:invoke fragment="bottominfo"/>
    </div>
</div>