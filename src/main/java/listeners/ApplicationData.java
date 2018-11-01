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
    public static final Map<String, String> units;
    static
    {
        units = new HashMap<String, String>();
        units.put("temperature", "°C");
        units.put("pressure", "Pa");
        units.put("windspeed", "m/s");
        units.put("winddirection", "°");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("menus", MENUS);
        emf = Persistence.createEntityManagerFactory("~/DATABASE");
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