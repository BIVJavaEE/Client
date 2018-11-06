<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Dashboard</title>
      <style type="text/css">

      </style>
        <link rel="stylesheet" type="text/css" href="../css/editor.semanticui.min.css">
        <link rel="stylesheet" type="text/css" href="../css/dashboard.css">

        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.semanticui.min.js"></script>
        <script src="../js/dataTables.editor.min.js"></script>
        <script src="../js/editor.semanticui.min.js"></script>
        <script>

            var editor;
            $(document).ready(function () {

                editor = new $.fn.dataTable.Editor({
                    ajax: {
                        remove: {
                            type: 'POST',
                            url: '/alertsTriggered?id=_id_'
                        }
                    },
                    "table": "#last-alerts",
                    idSrc: 'id'
                });

                // Delete a record
                $('#last-alerts').on('click', 'button.editor_remove', function (e) {
                    e.preventDefault();
                    editor.remove($(this).closest('tr'), {
                        title: 'Delete record',
                        message: 'Are you sure you wish to remove this record?',
                        buttons: 'Delete'
                    });
                });

                var table = $('#last-alerts').DataTable({
                    paging: false,
                    info: false,
                    "processing": false,
                    "ajax": {
                        "url": "/alertsTriggered",
                        dataSrc: ''
                    },
                    "columns": [
                        {
                            "data": "alert.name"
                        },
                        {
                            "data": "triggerDate"
                        },
                        {
                            "data": "alert.criticity"
                        },
                        {
                            "data": "alert.sensor.name",
                            "render": function (data, type, row, meta) {
                                if (type === 'display') {
                                    data = '<a href="/detailSensor?sensorId=' + row.alert.sensor.id + '">' + data + '</a>';
                                }
                                return data;
                            }
                        },
                        {
                            "data": "seen"
                        },
                        {
                            data: null,
                            className: "center",
                            defaultContent: '<button class="editor_remove ui red button">Delete</button>'
                        }
                    ]
                });


                setInterval(function () {
                    table.ajax.reload();
                }, 10000);
            });
        </script>
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf" %>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <h1 id="title">Dashboard</h1>

        <div class="ui raised very padded text segment">
            <div class="ui header grid-header">Average from the last 2 hours</div>
            <div id="general-info">
                <b>Latest measure received:</b> ${mostRecentMeasureData} <br/>
                <b>Measures count:</b> ${measuresCount}
            </div>
            <div class="ui centered grid">
                <c:forEach items="${measures}" var="item">
                    <div class="four wide column value-column">
                        <div class="ui tiny statistic">
                            <div class="value">
                                    ${item.getDisplayedAverage()}
                            </div>
                            <div class="label">
                                    ${item.getUnit()}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="ui raised very padded text segment">
            <h3 class="ui header grid-header">Latest alerts</h3>
            <table id="last-alerts" class="ui celled table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Trigger date</th>
                    <th>Criticity</th>
                    <th>Sensor</th>
                    <th>Seen</th>
                    <th>Action</th>
                </tr>
                </thead>
            </table>
        </div>
    </jsp:body>
</t:genericpage>