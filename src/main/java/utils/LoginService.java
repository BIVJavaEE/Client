package utils;

import entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

}
