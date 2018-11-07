package servlets;

import com.google.gson.Gson;
import entity.Measure;
import listeners.ApplicationData;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/measures")
public class Measures extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> sensorId = Optional.ofNullable(req.getParameter("sensorId"));
        Optional<String> beginDate = Optional.ofNullable(req.getParameter("beginDate"));
        Optional<String> endDate = Optional.ofNullable(req.getParameter("endDate"));

        if (!sensorId.isPresent() || !beginDate.isPresent() || !endDate.isPresent()) {
            //TODO: return error in json
            return;
        }

        Timestamp beginTimestamp = new Timestamp(Long.parseLong(beginDate.get()));
        Timestamp endTimestamp = new Timestamp(Long.parseLong(endDate.get()));

        EntityManager em = ApplicationData.createEntityManager();

        String queryStr = "SELECT m " +
                "FROM Measure m " +
                "WHERE m.sensor.id = :sensorId " +
                "AND m.timestamp BETWEEN :timestampStart AND :timestampEnd";

        List<Measure> measures = em.createQuery(queryStr, Measure.class)
                .setParameter("sensorId",Long.parseLong(sensorId.get()))
                .setParameter("timestampStart",beginTimestamp)
                .setParameter("timestampEnd",endTimestamp)
                .getResultList();

        List<Long> timestamps = measures
                .stream()
                .mapToLong(m -> m.getTimestamp().getTime())
                .boxed()
                .collect(Collectors.toList());

        long min = Collections.min(timestamps);
        long max = Collections.max(timestamps);
        long distance = max - min;
        long step = distance / 1000 / 20;
        resp.setHeader("Time-Step", String.valueOf(step));

        String measuresJson = new Gson().toJson(measures);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(measuresJson);

    }
}
