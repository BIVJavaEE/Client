<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error}">
    <div class="ui negative message">
        <div class="header">
            No data can be retrieve for this sensor
        </div>
        <p>Please contact your system administrator</p>
    </div>
</c:if>
<c:if test="${!error}">
    <div>
        <b>${lastMeasure.sensor.name}</b><br>
        <div class="ui horizontal statistic">
            <div class="value">${lastMeasure.value}</div>
            <div class="label">${unit}</div>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/detailSensor?sensorId=${lastMeasure.sensor.id}">See details</a>
        </div>
    </div>
</c:if>
