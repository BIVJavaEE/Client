package listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ApplicationData implements ServletContextListener {

    private static EntityManagerFactory emf;
    private static final String[] MENUS = {"Dashboard", "Sensors", "Alerts"};

    public static final Map<String, String> UNITS;
    static
    {
        UNITS = new HashMap<>();
        UNITS.put("temperature", "°C");
        UNITS.put("pressure", "Pa");
        UNITS.put("windspeed", "m/s");
        UNITS.put("winddirection", "°");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("menus", MENUS);
        emf = Persistence.createEntityManagerFactory("client_database");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }
}