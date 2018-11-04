package servlets.alerts;

import business.BusinessCheck;
import business.BusinessException;
import entity.Alert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AlertExistsBusinessCheck extends BusinessCheck<Long> {

    private EntityManager _em;

    public AlertExistsBusinessCheck(EntityManager em) {
        _em = em;
    }

    @Override
    public void run(Long alertId) throws BusinessException {
        try {
            _em
                .createQuery("FROM Alert a WHERE a.id = :id", Alert.class)
                .setParameter("id", alertId)
                .getSingleResult();
        } catch (NoResultException ignored) {
            throw new BusinessException("The provided alert doesn't exist");
        }
    }
}
