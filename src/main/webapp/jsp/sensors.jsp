<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="head">

        <title>Sensors</title>
      <style type="text/css">
          .ui.menu .item img.logo {
              margin-right: 1.5em;
          }

          .main.container {
              margin-top: 7em;
          }

          .wireframe {
              margin-top: 2em;
          }

          .ui.footer.segment {
              margin: 5em 0em 0em;
              padding: 5em 0em;
          }
      </style>
    </jsp:attribute>
    <jsp:attribute name="header">
      <%@ include file="../WEB-INF/jspf/header.jspf"%>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%--<p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>--%>
    </jsp:attribute>
    <jsp:body>
        <div class="ui main container">

        </div>
    </jsp:body>
</t:genericpage>