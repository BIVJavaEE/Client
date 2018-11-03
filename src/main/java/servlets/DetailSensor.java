package servlets;

import utils.UtilsJsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/detailSensor")
public class DetailSensor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> sensorId = Optional.ofNullable(req.getParameter("sensorId"));
        Optional<String> sensorName = Optional.ofNullable(req.getParameter("sensorName"));
        Optional<String> sensorType = Optional.ofNullable(req.getParameter("sensorType"));

        if (!sensorId.isPresent() || ! sensorName.isPresent()) {
            UtilsJsp.forwardToErrorPage(
                    req,
                    resp,
                    "Sensors",
                    "Detail sensor",
                    "There were an error with your request",
                    "You can only access sensor details throught the sensors page");
            return;
        }

        req.setAttribute("sensorId",sensorId.get());
        req.setAttribute("sensorName",sensorName.get());
        req.setAttribute("sensorType",sensorType.get());

        UtilsJsp.forwardToJsp("/jsp/detailSensor.jsp", req, resp);
    }
}
