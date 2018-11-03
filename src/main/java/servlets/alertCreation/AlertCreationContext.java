package servlets.alertCreation;

import entity.Alert;
import map.RequestMapper;
import map.RequestMapperException;

import javax.servlet.http.HttpServletRequest;

public class AlertCreationContext {

    private Alert _alert;
    private RequestMapperException _mapException;
    private boolean _editing;

    public boolean isEditing() {
        return _editing;
    }

    public void setEditing(boolean editing) {
        _editing = editing;
    }

    public Alert getAlert() {
        return _alert;
    }

    public void setAlert(Alert alert) {
        _alert = alert;
    }

    public RequestMapperException getMapException() {
        return _mapException;
    }

    public void setMapException(RequestMapperException mapException) {
        _mapException = mapException;
    }

    public static AlertCreationContext createFromRequest(HttpServletRequest req) {
        AlertCreationContext alertCreationContext = new AlertCreationContext();

        try {
            RequestMapper<Alert> requestMapper = new AlertRequestMapper(req);
            alertCreationContext.setAlert(requestMapper.map());
        } catch (RequestMapperException requestMapperException) {
            alertCreationContext.setMapException(requestMapperException);
        }

        alertCreationContext.setEditing(CreateAlert.isEdition(req));

        return alertCreationContext;
    }
}
