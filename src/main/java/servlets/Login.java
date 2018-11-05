package servlets;

import entity.User;
import listeners.ApplicationData;
import org.apache.commons.codec.digest.DigestUtils;
import utils.LoginService;
import utils.UtilsJsp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = ApplicationData.createEntityManager();
        LoginService loginService = new LoginService(em);
        loginService.disconnect(req);
        UtilsJsp.forwardToJsp("/jsp/login.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> username = Optional.ofNullable(req.getParameter("username"));
        Optional<String> password = Optional.ofNullable(req.getParameter("password"));

        if (!username.isPresent() || !password.isPresent()) {
            UtilsJsp.forwardToErrorPage(
                    req,
                    resp,
                    "Sensors",
                    "Detail sensor",
                    "There were an error with your request",
                    "You can only access sensor details throught the sensors page");
            return;
        }

        EntityManager em = ApplicationData.createEntityManager();
        LoginService loginService = new LoginService(em);
        Optional<User> loggedInUser = loginService.login(username.get(), password.get());

        if (!loggedInUser.isPresent()) {
            UtilsJsp.forwardToJsp("/jsp/login.jsp", req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("username", username.get());
        session.setAttribute("password", password.get());

        String redirectTo = Optional.ofNullable((String) session.getAttribute("redirectTo")).orElse("/dashboard");
        resp.sendRedirect(redirectTo);
    }
}
