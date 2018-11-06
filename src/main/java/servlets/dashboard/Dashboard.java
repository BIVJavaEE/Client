package servlets.dashboard;

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

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ApplicationData.createEntityManager();

        String queryStr =
                "SELECT NEW servlets.dashboard.TypeAverage(AVG(m.value), s.type) FROM Measure m " +
                "INNER JOIN m.sensor s GROUP BY s.type";

        List<TypeAverage> measures = em
            .createQuery(queryStr, TypeAverage.class)
            .getResultList();

        req.setAttribute("measures", measures);

        UtilsJsp.forwardToJsp("/jsp/dashboard.jsp", req, resp);
    }
}
