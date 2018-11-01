package servlets;

import com.google.gson.Gson;
import entity.Sensor;
import listeners.ApplicationData;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sensors")
public class Sensors extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ApplicationData.createEntityManager();
        try {
            List<Sensor> sensors = em.createQuery("SELECT s FROM Sensor s").getResultList();
            String sensorsJson = new Gson().toJson(sensors);
            req.setAttribute("sensors",sensorsJson);
        } catch(NoResultException e) {
            UtilsJsp.forwardToErrorPage(
                    req,
                    resp,
                    "Sensors",
                    "",
                    "There were an error with your request",
                    "No sensor was found");
            return;
        }

        UtilsJsp.forwardToJsp("/jsp/sensors.jsp", req, resp);
    }
}
