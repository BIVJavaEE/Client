<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Dashboard</title>
      <style type="text/css">

      </style>

        <%--<script src="https://code.jquery.com/jquery-3.3.1.js"></script>--%>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.semanticui.min.js"></script>
        <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.js"></script>--%>

        <script>
            $(document).ready(function() {
                $('#lastAlerts').DataTable( {
                    scrollCollapse: true,
                    paging:         false
                } );
            } );
        </script>
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%--<p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>--%>
    </jsp:attribute>
    <jsp:body>
        <div class="ui raised very padded text container segment">
            <h3 class="ui header">General information</h3>
            <p></p>
            <p></p>
        </div>
        <div class="ui raised very padded text container segment">
            <h3 class="ui header">Last alerts</h3>
            <table id="lastAlerts" class="ui celled table" style="width:100%">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    <th>Age</th>
                    <th>Start date</th>
                    <th>Salary</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${alertsTriggered}" var="item">
                    ${item}
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.triggerDate}</td>
                        <td>${item.alert.criticity}</td>
                        <td>${item.seen}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:genericpage>