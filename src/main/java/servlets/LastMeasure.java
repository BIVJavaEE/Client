package servlets;

import entity.Measure;
import listeners.ApplicationData;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/lastMeasure")
public class LastMeasure extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> sensorId = Optional.ofNullable(req.getParameter("sensorId"));

        if (!sensorId.isPresent()) {
            // TODO: Error message
            return;
        }

        EntityManager em = ApplicationData.createEntityManager();
        try {
            String queryStr = "SELECT m FROM Measure m WHERE m.sensor.id = :sensorId ORDER BY m.timestamp DESC";
            Query query = em.createQuery(queryStr).setMaxResults(1);
            query.setParameter("sensorId",Integer.parseInt(sensorId.get()));

            List<Measure> measures = query.getResultList();
            Measure lastMeasure = measures.get(0);

            req.setAttribute("lastMeasure",lastMeasure);
            req.setAttribute("unit", ApplicationData.UNITS.get(lastMeasure.getSensor().getType()));
        } catch(Exception e) {
            req.setAttribute("error",true);
        }

        UtilsJsp.forwardToJsp("/jsp/popup.jsp", req, resp);
    }
}
