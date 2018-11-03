<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Alerts</title>
      <style type="text/css">

      </style>
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <h1 id="title">Alerts</h1>

        <a href="${pageContext.request.contextPath}/create-alert">
            <button class="ui primary button">
                New
            </button>
        </a>

        <table class="ui celled table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Priority</th>
                    <th>Begin date</th>
                    <th>End date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alert" items="${alertsList}">
                    <tr>
                        <td data-label="Name"><c:out value='${alert.getName()}'/></td>
                        <td data-label="Priority"><c:out value='${alert.getCriticity()}'/></td>
                        <td data-label="Priority"><c:out value='${alert.getBeginDate()}'/></td>
                        <td data-label="Priority"><c:out value='${alert.getEndDate()}'/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/edit-alert/${alert.getId()}">
                                <button class="ui button">
                                    Edit
                                </button>
                            </a>
                            <button class="ui red button">
                                Delete
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:genericpage>