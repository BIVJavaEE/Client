package servlets.alertCreation;

import entity.Alert;
import entity.Sensor;
import listeners.ApplicationData;
import map.RequestMapper;
import map.RequestMapperException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlertRequestMapper extends RequestMapper<Alert> {

    public AlertRequestMapper(HttpServletRequest req) {
        super(req);
    }

    @Override
    public Alert map() throws RequestMapperException {
        Alert alert = new Alert();

        String name = getStringParameter("name").orElseThrow(() -> new RequestMapperException("Missing name"));
        alert.setName(name.trim());

        Long id = CreateAlert.isEdition(_req)
                ? CreateAlert.getAlertId(_req).orElseThrow(() -> new RequestMapperException("Missing id"))
                : null;
        alert.setId(id);

        String priority = getStringParameter("priority").orElseThrow(() -> new RequestMapperException("Missing priority"));
        String lowerCasePriority = priority.toLowerCase();
        if (!ApplicationData.PRIORITIES.contains(lowerCasePriority.toLowerCase())) {
            throw new RequestMapperException("Invalid priority");
        }
        alert.setCriticity(lowerCasePriority);

        alert.setTreshold(getLongParameter("threshold").orElseThrow(() -> new RequestMapperException("Missing threshold")));

        Long sensorId = getLongParameter("sensor").orElseThrow(() -> new RequestMapperException("Missing sensor"));
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        alert.setSensor(sensor);

        String formattedBeginTime = getStringParameter("begin-time").orElseThrow(() -> new RequestMapperException("Missing begin time"));
        alert.setBeginDate(convertDateTimeLocal(formattedBeginTime));

        String formattedEndTime = getStringParameter("end-time").orElseThrow(() -> new RequestMapperException("Missing end time"));
        alert.setEndDate(convertDateTimeLocal(formattedEndTime));

        return alert;
    }

    private Timestamp convertDateTimeLocal(String dateTimeLocal) {
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date date = formatter.parse(dateTimeLocal);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            return new Timestamp(System.currentTimeMillis());
        }
    }

}
