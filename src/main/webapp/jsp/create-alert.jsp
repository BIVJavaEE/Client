<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/create-alert.css">
    </jsp:attribute>

    <jsp:attribute name="header">
        <%@ include file="../WEB-INF/jspf/header.jspf"%>
        <script>
            var initialPriority = "${priority}";
            var sensorId = ${sensorId};
            var sensors = ${sensorsJson}
            var units = ${unitsJson}
        </script>
        <script src="${pageContext.request.contextPath}/js/create-alert.js" text="text/babel"></script>
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>

        <div id="title-header">
            <div class="left">
                <a href="${pageContext.request.contextPath}/alerts">
                    <button class="mini ui button">
                        Return to the alerts list
                    </button>
                </a>
            </div>
            <div class="center">
                <h1 id="title">${title}</h1>
            </div>
            <div class="right"></div>

        </div>

        <form class="ui form" method="post">

            <div class="field">
                <label>Name</label>
                <input type="text" name="name" value="${name}">
            </div>

            <div class="grouped fields">
                <label>Priority</label>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="priority" value="high" id="priority-high">
                        <label>High</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="priority" value="medium" id="priority-medium"/>
                        <label>Medium</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="priority" value="low" id="priority-low"/>
                        <label>Low</label>
                    </div>
                </div>
            </div>

            <div class="field">
                <label>Threshold</label>
                <div class="ui left labeled input">
                    <div class="ui basic label" id="unit-label"></div>
                    <input type="number" name="threshold" value="${threshold}">
                </div>
            </div>

            <div class="field">
                <label>Begin time</label>
                <div class="ui calendar" id="begin-time">
                    <div class="ui input left icon">
                        <i class="calendar icon"></i>
                        <input type="date" name="begin-time" value="${beginDate}">
                    </div>
                </div>
            </div>

            <div class="field">
                <label>End time</label>
                <div class="ui calendar" id="end-time">
                    <div class="ui input left icon">
                        <i class="calendar icon"></i>
                        <input type="date" name="end-time" value="${endDate}">
                    </div>
                </div>
            </div>

            <div class="field">
                <label>Sensor</label>
                <div class="ui selection dropdown" id="sensors-dropdown">
                    <input type="hidden" name="sensor">
                    <i class="dropdown icon"></i>
                    <div class="default text">Sensor</div>
                    <div class="menu">
                        <c:forEach var="sensor" items="${sensorsList}">
                            <div class="item" data-value="<c:out value='${sensor.getId()}'/>">
                                <b><c:out value='${sensor.getName()}'/></b> (<c:out value='${sensor.getType()}'/>)
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <button class="ui primary button">
                Save
            </button>

        </form>
    </jsp:body>

</t:genericpage>