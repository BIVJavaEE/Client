package filters;

import entity.User;
import listeners.ApplicationData;
import utils.LoginService;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebFilter("/*")
public class AuthentificationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        HttpSession session = httpRequest.getSession();
        Optional<String> username = Optional.ofNullable((String)session.getAttribute("username"));
        Optional<String> password = Optional.ofNullable((String)session.getAttribute("password"));

        if (!username.isPresent() || !password.isPresent()) {
            httpResponse.sendRedirect("/login");
            return;
        }

        EntityManager em = ApplicationData.createEntityManager();
        LoginService loginService = new LoginService(em);
        Optional<User> loggedInUser = loginService.login(username.get(), password.get());

        if (!loggedInUser.isPresent()) {
            httpResponse.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
