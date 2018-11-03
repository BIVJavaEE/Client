package map;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public abstract class RequestMapper<T> {

    protected HttpServletRequest _req;

    protected RequestMapper(HttpServletRequest req) {
        _req = req;
    }

    public abstract T map() throws RequestMapperException;

    protected Optional<Long> getLongParameter(String parameter) {
        Optional<String> str = getStringParameter(parameter);
        if (!str.isPresent())
            return Optional.empty();

        try {
            return Optional.of(Long.parseLong(str.get()));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    protected Optional<String> getStringParameter(String parameter) {
        return Optional.ofNullable(_req.getParameter(parameter));
    }

    protected boolean areAllPresent(Optional... optionals) {
        return Arrays
                .stream(optionals)
                .allMatch(Optional::isPresent);

    }
}
