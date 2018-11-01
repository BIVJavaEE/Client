<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Sensors</title>
        <link rel="stylesheet" type="text/css" href="../css/main.css">
         <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css"
               integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA=="
               crossorigin=""/>
         <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"
                 integrity="sha512-nMMmRyTVoLYqjP9hrbed9S+FzjZHW5gY1TWCHA5ckwXZBadntCNs8kEqAWdrb9O7rxbCaA4lKTIWjDXZxflOcA=="
                 crossorigin=""></script>

        <style>
            #mapid {
                min-height: 90%;
            }

            .leaflet-tooltip-top:before, .leaflet-tooltip-bottom:before, .leaflet-tooltip-left:before, .leaflet-tooltip-right:before {
                position: unset;
            }

            .tooltip {
            }
        </style>
        <script>
            $(document).ready(function() {
                var mymap = L.map('mapid').setView([47.217222,-1.553169], 12);
                L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
                    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
                    maxZoom: 18,
                    id: 'mapbox.streets',
                    accessToken: 'pk.eyJ1IjoiYmF0bnl1IiwiYSI6ImNqbmkydjcwNDBnM2YzcnI3MW9xOXh0YWcifQ.RB7MusoI_Yob1-Vxq_JbVQ'
                }).addTo(mymap);

                var LeafIcon = L.Icon.extend({
                    options: {
                        // shadowUrl: 'leaf-shadow.png',
                        iconSize:     [48, 48],
                        shadowSize:   [50, 64],
                        iconAnchor:   [32, 46],
                        shadowAnchor: [4, 62],
                        popupAnchor:  [-3, -50]
                    }
                });

                var temperature = new LeafIcon({iconUrl: 'img/celsius.png'}),
                    pressure = new LeafIcon({iconUrl: 'img/pressure.png'}),
                    windspeed = new LeafIcon({iconUrl: 'img/windspeed.png'}),
                    winddirection = new LeafIcon({iconUrl: 'img/winddirection.png'});

                var sensors = JSON.parse('${sensors}');

                for (var i = 0; i < sensors.length; i++) {
                    var marker = L.marker([sensors[i].latitude, sensors[i].longitude], {icon: eval(sensors[i].type)}).addTo(mymap);
                    marker.bindPopup("<div class=\"ui active centered inline small loader\"></div>");

                    (function (sensorId) {
                        var interval = null;
                        marker.on('click', function(e) {
                            var popup = e.target.getPopup();
                            var url = "/lastMeasure?sensorId=" + sensorId;
                            getLastMeasure(popup, url);
                            interval = setInterval(function() {
                                getLastMeasure(popup, url);
                            }, 10000);
                        });
                        marker.on("popupclose",function() {
                            console.log("popup closed");
                            clearInterval(interval);
                        })
                    })(sensors[i].id);
                }

            } );

            function getLastMeasure(popup,url) {
                $.get(url).done(function (data) {
                    popup.setContent(data);
                    popup.update();
                });
            }

        </script>
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf" %>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%--<p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>--%>
    </jsp:attribute>
    <jsp:body>
        <div id="mapid"></div>
    </jsp:body>
</t:genericpage>