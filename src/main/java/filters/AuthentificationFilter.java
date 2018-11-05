package filters;

import entity.User;
import listeners.ApplicationData;
import utils.LoginService;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns =
        {
                "/dashboard/*",
                "/create-alert/*",
                "/edit_alert/*",
                "/detailSensor/*",
                "/alerts/*",
                "/lastMeasure/*",
                "/sensors/*"
        })
public class AuthentificationFilter implements Filter {

    private LoginService _loginService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        EntityManager em = ApplicationData.createEntityManager();
        _loginService = new LoginService(em);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Optional<User> loggedInUser = _loginService.getCurrentlyLoggedInUser(req);
        if (loggedInUser.isPresent()) {
            req.setAttribute("user", loggedInUser.get());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            req.getSession().setAttribute("redirectTo", req.getRequestURI());
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
    }
}
