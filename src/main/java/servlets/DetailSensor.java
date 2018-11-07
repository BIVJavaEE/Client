package servlets;

import entity.Sensor;
import listeners.ApplicationData;
import map.RequestMapper;
import map.RequestMapperException;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        RequestMapper<Long> mapper = new RequestMapper<Long>(req) {
            @Override
            public Long map() throws RequestMapperException {
                return getLongParameter("sensorId").orElseThrow(() -> new RequestMapperException("Invalid sensor id"));
            }
        };

        Long sensorId;
        try {

            sensorId = mapper.map();
            EntityManager em = ApplicationData.createEntityManager();
            Sensor sensor = em
                    .createQuery("SELECT s FROM Sensor s where id = :id", Sensor.class)
                    .setParameter("id", sensorId)
                    .getSingleResult();
            req.setAttribute("sensor", sensor);

        } catch (RequestMapperException | NoResultException e) {
            UtilsJsp.forwardToErrorPage(
                    req,
                    resp,
                    "Sensors",
                    "Detail sensor",
                    "There were an error with your request",
                    "You can only access sensor details throught the sensors page");
            return;
        }

        UtilsJsp.forwardToJsp("/jsp/detailSensor.jsp", req, resp);
    }
}
