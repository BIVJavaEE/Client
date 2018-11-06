package servlets.dashboard;

import entity.Measure;
import listeners.ApplicationData;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ApplicationData.createEntityManager();

        Date twoHoursAgo = new Date(System.currentTimeMillis() - 3600 * 1000 * 2);

        String queryStr =
            "SELECT NEW servlets.dashboard.TypeAverage(AVG(m.value), s.type) FROM Measure m " +
            "INNER JOIN m.sensor s " +
            "WHERE m.timestamp > :minimumDate " +
            "GROUP BY s.type";

        List<TypeAverage> measures = em
            .createQuery(queryStr, TypeAverage.class)
            .setParameter("minimumDate", twoHoursAgo, TemporalType.TIMESTAMP)
            .getResultList();

        req.setAttribute("measures", measures);

        long measuresCount = em
                .createQuery("SELECT COUNT(*) FROM Measure m WHERE m.timestamp > :minimumDate", Long.class)
                .setParameter("minimumDate", twoHoursAgo, TemporalType.TIMESTAMP)
                .getSingleResult();
        req.setAttribute("measuresCount", measuresCount);

        if (!measures.isEmpty()) {
            Date mostRecentMeasureDate = em
                .createQuery("SELECT m.timestamp FROM Measure m ORDER BY m.timestamp DESC", Date.class)
                .setMaxResults(1)
                .getSingleResult();
            String mostRecentMeasureString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(mostRecentMeasureDate);
            req.setAttribute("mostRecentMeasureData", mostRecentMeasureString);
        }

        UtilsJsp.forwardToJsp("/jsp/dashboard.jsp", req, resp);
    }
}
