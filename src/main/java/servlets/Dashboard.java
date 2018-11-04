package servlets;

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

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ApplicationData.createEntityManager();
        try {
            String queryStr = "SELECT AVG(measures.value) AS average, sensors.type AS type FROM measures " +
                    "INNER JOIN sensors ON sensors.id = measures.sensor_id " +
                    "GROUP BY sensors.type";
            Query query = em.createNativeQuery(queryStr);

            List<Object[]> measures = query.getResultList();
            for (Object[] obj : measures) {
                double value = Double.parseDouble(obj[0].toString());
                obj[0] = Math.round(value * 100.0) / 100.0;
                obj[1] = ApplicationData.UNITS.get(obj[1]);
            }

            req.setAttribute("measures",measures);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        UtilsJsp.forwardToJsp("/jsp/dashboard.jsp", req, resp);
    }
}
