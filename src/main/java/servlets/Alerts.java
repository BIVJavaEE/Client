package servlets;

import entity.Alert;
import entity.Sensor;
import listeners.ApplicationData;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/alerts")
public class Alerts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = ApplicationData.createEntityManager();

        List<Alert> alerts = em
                .createQuery("SELECT a FROM Alert a", Alert.class)
                .getResultList();
        req.setAttribute("alertsList", alerts);

        UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
    }
}
