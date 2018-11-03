package servlets.base;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public abstract class BaseServlet extends HttpServlet {

    protected Optional<Long> getLongParameter(HttpServletRequest req, String parameter) {
        Optional<String> str = getStringParameter(req, parameter);
        if (!str.isPresent())
            return Optional.empty();

        try {
            return Optional.of(Long.parseLong(str.get()));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    protected Optional<String> getStringParameter(HttpServletRequest req, String parameter) {
        return Optional.ofNullable(req.getParameter(parameter));
    }

    protected boolean areAllPresent(Optional... optionals) {
        return Arrays
                .stream(optionals)
                .allMatch(Optional::isPresent);

    }
}
