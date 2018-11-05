package servlets;

import entity.User;
import listeners.ApplicationData;
import org.apache.commons.codec.digest.DigestUtils;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/join")
public class Join extends HttpServlet {

    private enum JoinError {
        None,
        UserAlreadyExists
    }

    private static Map<JoinError, String> joinErrorMessages = new HashMap<>();
    static {
        joinErrorMessages.put(JoinError.None, "");
        joinErrorMessages.put(JoinError.UserAlreadyExists, "This user already exists");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("joinErrorMessage", JoinError.None);

        Optional<String> username = Optional.ofNullable(req.getParameter("username"));
        Optional<String> password = Optional.ofNullable(req.getParameter("username"));

        if (!username.isPresent() || !password.isPresent()) {
            // TODO: Error message
            UtilsJsp.forwardToJsp("/jsp/join.jsp", req, resp);
            return;
        }

        String loweredUserName = username.get().toLowerCase().trim();

        EntityManager em = ApplicationData.createEntityManager();
        boolean userExists = true;
        try {
            em.createQuery("SELECT u FROM User u WHERE u.name LIKE :providedName")
                    .setParameter("providedName", loweredUserName)
                    .getSingleResult();
        } catch(NoResultException e) {
            userExists = false;
        }

        if (userExists) {
            // TODO: Error message
            UtilsJsp.forwardToJsp("/jsp/join.jsp", req, resp);
            return;
        }

        String hashedPassword = DigestUtils.sha1Hex(password.get());
        User newUser = new User();
        newUser.setName(loweredUserName);
        newUser.setPassword(hashedPassword);

        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();

        UtilsJsp.forwardToJsp("/jsp/login.jsp", req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UtilsJsp.forwardToJsp("/jsp/join.jsp", req, resp);
    }


}
