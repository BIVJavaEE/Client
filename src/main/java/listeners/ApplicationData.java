package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationData implements ServletContextListener {

    private static final String[] MENUS = {"Dashboard", "Sensors", "Alerts"};

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("menus", MENUS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP.
    }

}