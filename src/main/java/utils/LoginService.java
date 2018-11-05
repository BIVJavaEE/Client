package utils;

import entity.User;
import listeners.ApplicationData;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginService {

    private EntityManager _em;

    public LoginService(EntityManager em) {
        _em = em;
    }

    public Optional<User> login(String username, String password) {
        String loweredUserName = username.toLowerCase().trim();
        String hashedPassword = DigestUtils.sha1Hex(password);
        try {
            User user = _em
                .createQuery("FROM User u WHERE u.name = :username AND u.password = :password", User.class)
                .setParameter("username", loweredUserName)
                .setParameter("password", hashedPassword)
                .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<User> getCurrentlyLoggedInUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Optional<String> username = Optional.ofNullable((String)session.getAttribute("username"));
        Optional<String> password = Optional.ofNullable((String)session.getAttribute("password"));

        if (!username.isPresent() || !password.isPresent()) {
            return Optional.empty();
        }

        return login(username.get(), password.get());
    }

    public void disconnect(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("username", null);
        session.setAttribute("password", null);
    }

}
