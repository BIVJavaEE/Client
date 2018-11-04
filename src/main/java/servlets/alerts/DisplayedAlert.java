package servlets.alerts;

import entity.Alert;
import entity.Sensor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DisplayedAlert {

    private Long _id;
    private String _name;
    private String _beginDate;
    private String _endDate;
    private String _priority;
    private Long _threshold;
    private Sensor _sensor;

    public DisplayedAlert(Alert alert) {
        _id = alert.getId();

        _name = alert.getName();

        String loweredPriority = alert.getCriticity().toLowerCase();
        _priority = loweredPriority.substring(0, 1).toUpperCase() + loweredPriority.substring(1);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        _beginDate = dateFormat.format(alert.getBeginDate());
        _endDate = dateFormat.format(alert.getEndDate());

        _threshold = alert.getTreshold();

        _sensor = alert.getSensor();
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getBeginDate() {
        return _beginDate;
    }

    public void setBeginDate(String beginDate) {
        _beginDate = beginDate;
    }

    public String getEndDate() {
        return _endDate;
    }

    public void setEndDate(String endDate) {
        _endDate = endDate;
    }

    public String getPriority() {
        return _priority;
    }

    public void setPriority(String priority) {
        _priority = priority;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Sensor getSensor() {
        return _sensor;
    }

    public void setSensor(Sensor sensor) {
        _sensor = sensor;
    }

    public Long getThreshold() {
        return _threshold;
    }

    public void setThreshold(Long threshold) {
        _threshold = threshold;
    }
}
