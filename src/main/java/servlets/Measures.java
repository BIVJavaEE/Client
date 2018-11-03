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
import java.util.List;
import java.util.Optional;

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
        try {
            String queryStr = "SELECT m.timestamp,m.value FROM Measure m WHERE m.sensor.id = :sensorId AND m.timestamp >= :begin AND m.timestamp <= :end";
            Query query = em.createQuery(queryStr);
            query.setParameter("sensorId",Long.parseLong(sensorId.get()));
            query.setParameter("begin",beginTimestamp);
            query.setParameter("end",endTimestamp);

            List<Measure> measures = query.getResultList();

            String measuresJson = new Gson().toJson(measures);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(measuresJson);

        } catch(Exception e) {
            System.out.println(e.getMessage());
            //TODO : return no data
            return;
        }
    }
}
