package servlets.alerts;

import business.BusinessCheck;
import business.BusinessException;
import entity.Alert;
import entity.Sensor;
import listeners.ApplicationData;
import map.RequestMapper;
import map.RequestMapperException;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/alerts")
public class Alerts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = ApplicationData.createEntityManager();

        List<DisplayedAlert> alerts = em
                .createQuery("SELECT a FROM Alert a INNER JOIN a.sensor s", Alert.class)
                .getResultStream()
                .map(DisplayedAlert::new)
                .collect(Collectors.toList());

        req.setAttribute("alertsList", alerts);

        UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMapper<Long> mapper = new RequestMapper<Long>(req) {
            @Override
            public Long map() throws RequestMapperException {
                return getLongParameter("id").orElseThrow(() -> new RequestMapperException("Invalid id"));
            }
        };

        Long id;
        try {
            id = mapper.map();
        } catch (RequestMapperException e) {
            resp.setStatus(400);
            UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
            return;
        }

        EntityManager em = ApplicationData.createEntityManager();

        BusinessCheck<Long> businessCheck = new AlertExistsBusinessCheck(em);
        try {
            businessCheck.run(id);
        } catch (BusinessException businessException) {
            resp.setStatus(400);
            UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
            return;
        }


        em.getTransaction().begin();
        Alert alert = em.find(Alert.class, id);
        em.remove(alert);
        em.flush();
        em.getTransaction().commit();

        UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
    }
}
