package servlets.alerts;

import entity.Alert;
import entity.Sensor;
import listeners.ApplicationData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DisplayedAlert {

    private Long _id;
    private String _name;
    private String _beginDate;
    private String _endDate;
    private String _priority;
    private String _threshold;
    private Sensor _sensor;

    public DisplayedAlert(Alert alert) {
        _id = alert.getId();

        _name = alert.getName();

        String loweredPriority = alert.getCriticity().toLowerCase();
        _priority = loweredPriority.substring(0, 1).toUpperCase() + loweredPriority.substring(1);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        _beginDate = dateFormat.format(alert.getBeginDate());
        _endDate = dateFormat.format(alert.getEndDate());

        _threshold = String.valueOf(alert.getTreshold()) + " " + ApplicationData.UNITS.get(alert.getSensor().getType());

        _sensor = alert.getSensor();
    }

    public String getName() {
        return _name;
    }

    public String getBeginDate() {
        return _beginDate;
    }

    public String getEndDate() {
        return _endDate;
    }

    public String getPriority() {
        return _priority;
    }

    public Long getId() {
        return _id;
    }

    public Sensor getSensor() {
        return _sensor;
    }

    public String getThreshold() {
        return _threshold;
    }
}
