<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title>Alerts</title>
        <script src="${pageContext.request.contextPath}/js/alerts.js" text="text/babel"></script>
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
                    <th>Sensor</th>
                    <th>Begin date</th>
                    <th>End date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alert" items="${alertsList}">
                    <tr>
                        <td><c:out value='${alert.getName()}'/></td>
                        <td><c:out value='${alert.getPriority()}'/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/detailSensor?sensorId=${alert.getSensor().getId()}">
                                <c:out value='${alert.getSensor().getName()}'/>
                            </a>
                        </td>
                        <td><c:out value='${alert.getBeginDate()}'/></td>
                        <td><c:out value='${alert.getEndDate()}'/></td>

                        <td>
                            <a href="${pageContext.request.contextPath}/edit-alert/${alert.getId()}">
                                <button class="ui button">
                                    Edit
                                </button>
                            </a>
                            <button class="ui red button delete-alert" alert-id="${alert.getId()}" alert-name="${alert.getName()}">
                                Delete
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="ui small modal" id="delete-confirmation-modal">
            <i class="close icon"></i>
            <div class="header">
                Deleting an alert
            </div>
            <div class="content">
                <div class="description">
                    <p id="deletion-modal-content">Are you sure you want to delete <b>{{alertName}}</b>?
                    This action is irrevocable</p>
                </div>
            </div>
            <div class="actions">
                <div class="ui black deny button">
                    No
                </div>
                <div class="ui positive right labeled icon button" id="confirm-delete-button">
                    Delete
                </div>
            </div>
        </div>

    </jsp:body>
</t:genericpage>