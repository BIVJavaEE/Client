package servlets;

import entity.Alert;
import entity.Sensor;
import listeners.ApplicationData;
import servlets.base.BaseServlet;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebServlet("/create-alert")
public class CreateAlert extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> name = getStringParameter(req, "name");
        Optional<String> priority = getStringParameter(req, "priority");
        Optional<Long> threshold = getLongParameter(req, "threshold");
        Optional<Long> sensorId = getLongParameter(req, "sensor");
        Optional<String> beginTime = getStringParameter(req, "begin-time");
        Optional<String> endTime = getStringParameter(req, "end-time");

        EntityManager em = ApplicationData.createEntityManager();

        boolean parameterIsMissing = !areAllPresent(
                name,
                priority,
                threshold,
                sensorId,
                beginTime,
                endTime
        );

        boolean alertWithSameNameExists = true;
        try {
                em
                    .createQuery("FROM Alert a WHERE a.name = :name")
                    .setParameter("name", name.get())
                    .getSingleResult();
        } catch (NoResultException ignored) {
            alertWithSameNameExists = false;
        }

        boolean priorityIsInvalid = !priority.isPresent() || !ApplicationData.PRIORITIES.contains(priority.get().toLowerCase());

        Sensor sensor;
        try {
            sensor = em
                    .createQuery("FROM Sensor s WHERE s.id = :id", Sensor.class)
                    .setParameter("id", sensorId.get())
                    .getSingleResult();
        } catch (NoResultException ignored) {
            sensor = null;
        }

        if (parameterIsMissing || alertWithSameNameExists || priorityIsInvalid || sensor == null) {
            // TODO: Error
            UtilsJsp.forwardToJsp("/jsp/create-alert.jsp", req, resp);
            return;
        }

        Alert newAlert = new Alert();
        newAlert.setName(name.get());
        newAlert.setCreationDate(new Timestamp(System.currentTimeMillis()));
        newAlert.setBeginDate(convertDateTimeLocal(beginTime.get()));
        newAlert.setEndDate(convertDateTimeLocal(endTime.get()));
        newAlert.setCriticity(priority.get());
        newAlert.setTreshold(threshold.get());
        newAlert.setSensor(sensor);

        em.getTransaction().begin();
        em.persist(newAlert);
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

        UtilsJsp.forwardToJsp("/jsp/create-alert.jsp", req, resp);
    }

    private Timestamp convertDateTimeLocal(String dateTimeLocal) {
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date date = formatter.parse(dateTimeLocal);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            return new Timestamp(System.currentTimeMillis());
        }
    }

}
