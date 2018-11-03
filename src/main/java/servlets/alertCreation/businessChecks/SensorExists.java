package servlets.alertCreation.businessChecks;

import business.BusinessCheck;
import business.BusinessException;
import entity.Sensor;
import servlets.alertCreation.AlertCreationContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class SensorExists extends BusinessCheck<AlertCreationContext> {

    private EntityManager _em;

    public SensorExists(EntityManager em) {
        _em = em;
    }

    @Override
    public void run(AlertCreationContext context) throws BusinessException {
        try {
            _em
                .createQuery("FROM Sensor s WHERE s.id = :id", Sensor.class)
                .setParameter("id", context.getAlert().getSensor().getId())
                .getSingleResult();
        } catch (NoResultException ignored) {
            throw new BusinessException("The provided sensor doesn't exist");
        }

    }

}
