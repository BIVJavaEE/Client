package servlets;

import com.google.gson.Gson;
import entity.AlertTriggered;
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

@WebServlet("/alertsTriggered")
public class AlertsTriggered extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ApplicationData.createEntityManager();
        try {
            String queryStr = "SELECT a FROM AlertTriggered a";
            Query query = em.createQuery(queryStr);

            List<AlertTriggered> alertsTriggered = query.getResultList();

            String alertsTriggeredJson = new Gson().toJson(alertsTriggered);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(alertsTriggeredJson);

        } catch(Exception e) {
            System.out.println(e.getMessage());
            //TODO : return no data
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> id = Optional.ofNullable(req.getParameter("id"));

        if (!id.isPresent()) {
            // TODO: Error message
            return;
        }

        EntityManager em = ApplicationData.createEntityManager();
        try {
            AlertTriggered alertTriggered = em.find(AlertTriggered.class, Long.parseLong(id.get()));

            em.getTransaction().begin();
            em.remove(alertTriggered);
            em.getTransaction().commit();

            String validJson = new Gson().toJson(null);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(validJson);

        } catch(Exception e) {
            System.out.println(e.getMessage());
            //TODO : return no data
            return;
        }
    }
}
