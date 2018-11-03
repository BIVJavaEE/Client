package servlets.alertCreation;

import business.BusinessCheck;
import business.BusinessCheckOrchestrator;
import business.BusinessException;
import entity.Alert;
import entity.Sensor;
import listeners.ApplicationData;
import servlets.alertCreation.businessChecks.AlertIsValid;
import servlets.alertCreation.businessChecks.AlertNameIsValid;
import servlets.alertCreation.businessChecks.SensorExists;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebServlet({"/create-alert", "/edit-alert/*"})
public class CreateAlert extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = ApplicationData.createEntityManager();

        AlertCreationContext context = AlertCreationContext.createFromRequest(req);
        BusinessCheck<AlertCreationContext> businessChecks = new BusinessCheckOrchestrator<>(
                new AlertIsValid(),
                new AlertNameIsValid(em),
                new SensorExists(em)
        );

        try {
            businessChecks.run(context);
        } catch (BusinessException businessException) {
            UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
            return;
        }

        Alert newAlert = context.getAlert();
        newAlert.setCreationDate(new Timestamp(System.currentTimeMillis()));

        em.getTransaction().begin();
        if (isEdition(req)) {
            Optional<Long> alertId = getAlertId(req);
            if (!alertId.isPresent()) {
                UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
                return;
            }
            newAlert.setId(alertId.get());
            em.merge(newAlert);
        } else {
            em.persist(newAlert);
        }
        em.flush();
        em.getTransaction().commit();

        UtilsJsp.forwardToJsp("/jsp/alerts.jsp", req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = ApplicationData.createEntityManager();

        List<Sensor> sensors = em
                .createQuery("SELECT s FROM Sensor s", Sensor.class)
                .getResultList();
        req.setAttribute("sensorsList", sensors);

        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String name = "";
        String priority = "high";
        String beginDate = dateFormat.format(today);
        String endDate = dateFormat.format(tomorrow);
        Long threshold = 0L;
        Long sensorId = -1L;

        boolean isEdition = isEdition(req);
        if (isEdition) {
            try {
                Alert alert = em
                        .createQuery("SELECT a FROM Alert a where id = :id", Alert.class)
                        .setParameter("id", getAlertId(req).orElseThrow(NoResultException::new))
                        .getSingleResult();
                name = alert.getName();
                priority = alert.getCriticity().toLowerCase();
                beginDate = dateFormat.format(alert.getBeginDate().getTime());
                endDate = dateFormat.format(alert.getEndDate().getTime());
                threshold = alert.getTreshold();
                sensorId = alert.getSensor().getId();
            } catch (NoResultException ignored) {
                resp.setStatus(404);
                UtilsJsp.forwardToJsp("/jsp/404.jsp", req, resp);
                return;
            }
        }

        req.setAttribute("name", name);
        req.setAttribute("priority", priority);
        req.setAttribute("beginDate", beginDate);
        req.setAttribute("endDate", endDate);
        req.setAttribute("threshold", threshold);
        req.setAttribute("sensorId", sensorId);

        String title = isEdition
                ? "Edit the alert " + name
                : "Create an alert";
        req.setAttribute("title", title);

        UtilsJsp.forwardToJsp("/jsp/create-alert.jsp", req, resp);
    }

    public static Optional<Long> getAlertId(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2) {
            return Optional.empty();
        }
        String id = pathParts[1];
        try {
            return Optional.of(Long.parseLong(id));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    public static boolean isEdition(HttpServletRequest req) {
        return req.getServletPath().equals("/edit-alert");
    }
}
