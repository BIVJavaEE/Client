<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Sensors</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
        <script src="https://cdn.rawgit.com/mdehoog/Semantic-UI-Calendar/76959c6f7d33a527b49be76789e984a0a407350b/dist/calendar.min.js"></script>
        <link href="https://cdn.rawgit.com/mdehoog/Semantic-UI-Calendar/76959c6f7d33a527b49be76789e984a0a407350b/dist/calendar.min.css"
              rel="stylesheet" type="text/css"/>

    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf" %>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%--<p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>--%>
    </jsp:attribute>
    <jsp:body>
        Detail sensor : <b>${sensorName}</b>

        <div class="ui form">
            <div class="two fields">
                <div class="field">
                    <label>Start date</label>
                    <div class="ui calendar" id="rangestart">
                        <div class="ui input left icon">
                            <i class="calendar icon"></i>
                            <input type="text" placeholder="Start">
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>End date</label>
                    <div class="ui calendar" id="rangeend">
                        <div class="ui input left icon">
                            <i class="calendar icon"></i>
                            <input type="text" placeholder="End">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui container segment chart-container" style="position: relative; height: 60vh;">
            <canvas id="myChart"></canvas>
        </div>

        <script>
            var ctx1;
            $(document).ready(function () {
                $('#rangestart').calendar({
                    onHidden: function (date, text, mode) {
                        updateChart(sensorId,sensorType)
                    },
                }).calendar("set date", moment().subtract(2, 'hours').toDate());
                $('#rangeend').calendar({
                    onHidden: function (date, text, mode) {
                        updateChart(sensorId,sensorType)
                    },
                }).calendar("set date", moment().toDate());

                var sensorId = '${sensorId}';
                var sensorType = '${sensorType}';

                var canvas1 = $("#myChart")[0];

                ctx1 = new Chart(canvas1, {
                    type: 'line',
                    data: {
                        // labels: labels,
                        datasets: [{
                            label: 'Fluctuation',
                            // data: data,
                            borderWidth: 5,
                            fill: false,
                            backgroundColor: 'rgba(26, 105, 164,1)',
                            borderColor: 'rgba(26, 105, 164,1)',
                        }]
                    },
                    options: {
                        maintainAspectRatio: false,
                        elements: {
                            point: {
                                radius: 1
                            }
                        },
                        scales: {
                            xAxes: [{
                                type: 'time',
                                time: {
                                    unit: 'second',
                                    displayFormats: {
                                        hour: 'HH:mm:ss'
                                    },
                                    stepSize: 1900,
                                },
                            }]
                        }
                    }
                });

                updateChart(sensorId,sensorType);
            });

            function updateChart(sensorId, sensorType) {
                var beginDate = moment($('#rangestart').calendar("get date")).valueOf();
                var endDate = moment($('#rangeend').calendar("get date")).valueOf();

                var url = "/measures?sensorId=" + sensorId + "&beginDate=" + beginDate + "&endDate=" + endDate;
                $.get(url).done(function (data) {

                    var result = data.map(function (measure) {
                        return {
                            x: measure[0],
                            y: measure[1]
                        }
                    });

                    result = result.filter(function (element, index, array) {
                        return (index % 1 === 0);
                    });

                    console.log(result);

                    // parse labels and data
                    // var labels = result.map(function (e) {
                    //     return moment(e.x);
                    // });
                    // var data = result.map(function (e) {
                    //     return +e.y;
                    // });

                    removeDataset(ctx1);
                    addDataset(ctx1, sensorType + " fluctuation", result, "#007bff", "#007bff", false);
                    ctx1.update();
                });
            }

            function removeDataset(chart) {
                chart.data.datasets = [];
            };

            function addDataset(chart, name, data, background, border, fill) {
                var newDataset = {
                    label: name,
                    data: [],
                    backgroundColor: background,
                    borderColor: border,
                    fill: fill
                };
                for (var index = 0; index < data.length; ++index) {
                    newDataset.data.push(data[index]);
                }
                chart.data.datasets.push(newDataset);
                //TODO : calculer l'intervalle pour changer le stepsize :)
            };

        </script>
    </jsp:body>
</t:genericpage>